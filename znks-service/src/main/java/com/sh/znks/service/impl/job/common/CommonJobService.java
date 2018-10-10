package com.sh.znks.service.impl.job.common;

import com.sh.znks.dao.BattleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by wuminggu on 2018/9/26.
 */
@Service("commonJobService")
public class CommonJobService {
    private final static Logger log = LoggerFactory.getLogger(CommonJobService.class);

    private static final Integer offset = 100;
    @Autowired
    private BattleDao battleDao;

    public void updateBattleStatus(int before, int after, List<String> battleIds) {
        try {
            //分段更新
            int totalCount = battleIds.size();
            int currentCount = 0;
            while (currentCount <= totalCount) {
                List<String> update0BattleIds;
                if (totalCount <= currentCount + offset) {
                    update0BattleIds = battleIds.subList(currentCount, totalCount);
                } else {
                    update0BattleIds = battleIds.subList(currentCount, currentCount + offset);
                }
                currentCount += offset;

                //0->1、1->2、2->3
                battleDao.updateBattleStatus(before, after, update0BattleIds);
            }
        } catch (Exception e) {
            log.error("L82_updateBattleStatus e:", e);
            return;
        }
    }
}
