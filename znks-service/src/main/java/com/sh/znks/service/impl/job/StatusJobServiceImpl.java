package com.sh.znks.service.impl.job;

import com.sh.znks.common.base.Constant;
import com.sh.znks.dao.BattleDao;
import com.sh.znks.domain.aq.Battle;
import com.sh.znks.service.impl.job.common.CommonJobService;
import com.sh.znks.service.job.JobService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时每分钟执行一次
 * 开团时间已到则自动将状态变为：团战中
 * 团战时间已到则自动将状态变为：团战结束
 * Created by wuminggu on 2018/9/25.
 */
public class StatusJobServiceImpl implements JobService {
    private final static Logger log = LoggerFactory.getLogger(StatusJobServiceImpl.class);

    @Autowired
    private BattleDao battleDao;
    @Autowired
    private CommonJobService commonJobService;

    @Override
    public void execute() {
        try {
            //取得状态为(0等待团战、1团战中)的团战信息
            List<Battle> battles = battleDao.getBattleListByStatus();
            if (CollectionUtils.isEmpty(battles)) {
                log.error("L27_JobServiceImpl battles is null");
                return;
            }

            //状态为0且需要更新的团list
            List<String> status0BattleIds = new ArrayList<>();
            //状态为1且需要更新的团list
            List<String> status1BattleIds = new ArrayList<>();
            //当前时间
            Date now = new Date();
            for (Battle b : battles) {
                if (b.getStatus() == 0 && b.getStartTime().getTime() <= now.getTime()) {
                    status0BattleIds.add(b.getBattleId());
                }
                if (b.getStatus() == 1 && b.getBattleTime().getTime() <= now.getTime()) {
                    status1BattleIds.add(b.getBattleId());
                }
            }

            if (status0BattleIds.size() > 0) {
                //分段更新0->1
                commonJobService.updateBattleStatus(Constant.ZERO, Constant.ONE, status0BattleIds);
            }
            if (status1BattleIds.size() > 0) {
                //分段更新1->2
                commonJobService.updateBattleStatus(Constant.ONE, Constant.TWO, status1BattleIds);
            }
        } catch (Exception e) {
            log.error("L59_JobServiceImpl e:", e);
            return;
        }
    }

}
