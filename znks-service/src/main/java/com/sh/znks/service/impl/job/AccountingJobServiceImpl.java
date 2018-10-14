package com.sh.znks.service.impl.job;

import com.sh.znks.common.base.Constant;
import com.sh.znks.dao.BattleDao;
import com.sh.znks.domain.aq.Battle;
import com.sh.znks.domain.aq.BattleRecord;
import com.sh.znks.domain.dto.BattleCondition;
import com.sh.znks.service.impl.job.common.CommonJobService;
import com.sh.znks.service.job.JobService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时刷团战的成绩排行和赠送礼物
 * 每分钟刷一次
 * 将排名和赠送的礼物送完后，更新团战表记录状态为3
 * Created by wuminggu on 2018/9/26.
 */
public class AccountingJobServiceImpl implements JobService {
    private final static Logger log = LoggerFactory.getLogger(AccountingJobServiceImpl.class);

    @Autowired
    private BattleDao battleDao;
    @Autowired
    private CommonJobService commonJobService;

    @Override
    public void execute() {
        try {
            //查询团id下的未赠送礼物的团信息
            BattleCondition condition = new BattleCondition();
            condition.setStatus(Constant.TWO);
            List<Battle> resList = battleDao.getBattleInfos(condition);
            if (CollectionUtils.isEmpty(resList)) {
                log.error("L34_AccountingJobServiceImpl result is null");
                return;
            }

            //通过BattleId分组取得所有团下的参战结果信息
            List<String> battleIds = new ArrayList<>();
            Map<String, List<BattleRecord>> battleRecordMap = new HashMap<>();
            String battleId;
            for (Battle battle : resList) {
                battleId = battle.getBattleId();
                battleIds.add(battleId);
                List<BattleRecord> brList = battleDao.getBattleRecordListByBattleId(battleId);
                battleRecordMap.put(battleId, brList);
            }

            //取得要赠送的礼物
            List<Long> giftIds = new ArrayList<>();
            giftIds.add(battleDao.getGiftInfoByLevel(Constant.ONE));
            giftIds.add(battleDao.getGiftInfoByLevel(Constant.TWO));
            giftIds.add(battleDao.getGiftInfoByLevel(Constant.THREE));

            //对前3名做分级赠送礼物，其他的只有排名。
            List<BattleRecord> resultList = new ArrayList<>();
            for (int i = 0; i < battleIds.size(); i++) {
                String bId = battleIds.get(i);
                List<BattleRecord> brlist = battleRecordMap.get(bId);
                int ranking = 1;
                for (int j = 0; j < brlist.size(); j++) {
                    BattleRecord br = brlist.get(j);
                    br.setRanking(ranking);
                    if (j + 1 < brlist.size()) {
                        BattleRecord brNext = brlist.get(j + 1);
                        //先取出每个battleId对应的参战人数，按分数排序取出前三名(分数一样的并列)
                        if (Integer.valueOf(brNext.getBattleScore()) < Integer.valueOf(br.getBattleScore())) {
                            ranking++;
                        }
                    }
                    //设置礼物
                    switch (ranking) {
                        case 1:
                            br.setGiftId(giftIds.get(0));
                            continue;
                        case 2:
                            br.setGiftId(giftIds.get(1));
                            continue;
                        case 3:
                            br.setGiftId(giftIds.get(2));
                            continue;
                        default:
                            continue;
                    }
                }
                //最后结果都放在一起
                resultList.addAll(brlist);
            }

            //循环更新记录表
            for (BattleRecord br : resultList) {
                BattleRecord brNew = new BattleRecord();
                brNew.setUserId(br.getUserId());
                brNew.setBattleId(br.getBattleId());
                brNew.setGiftId(br.getGiftId());
                brNew.setRanking(br.getRanking());
                battleDao.updateBattleRecordInfo(br);
            }

            //分段更新2->3(礼物赠送完成)
            commonJobService.updateBattleStatus(Constant.TWO, Constant.THREE, battleIds);
        } catch (Exception e) {
            log.error("L97_AccountingJobServiceImpl e:", e);
            return;
        }
    }
}
