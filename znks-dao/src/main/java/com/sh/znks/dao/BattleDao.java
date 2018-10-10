package com.sh.znks.dao;

import com.sh.znks.domain.aq.Battle;
import com.sh.znks.domain.aq.BattleRecord;
import com.sh.znks.domain.aq.Gift;
import com.sh.znks.domain.dto.BattleCondition;

import java.util.List;

/**
 * Created by wuminggu on 2018/9/21.
 */
public interface BattleDao {
    /**
     * 插入团信息
     * @param battle
     * @return
     */
    public int insertBattle(Battle battle);

    /**
     * 根据条件查询团战信息
     * @param condition
     * @return
     */
    public List<Battle> getBattleInfos(BattleCondition condition);

    /**
     * 插入参团记录信息
     * @param battleRecord
     * @return
     */
    public int insertBattleRecord(BattleRecord battleRecord);

    /**
     * 根据battleId查询参团的所有用户id
     * (分页)
     * @param battleId
     * @return
     */
    public List<String> getBattleRecordInfos(String battleId);

    /**
     * 更新实际参团人数加一
     * @param battleId
     * @return
     */
    public int updateBattleAmount(String battleId);

    /**
     * 获取状态为(0等待团战、1团战中)的开团信息
     * @return
     */
    public List<Battle> getBattleListByStatus();

    /**
     * 根据状态分类更新团战的状态
     * 0->1、1->2
     * @param statusBef
     * @param statusAft
     * @param battleIds
     * @return
     */
    public int updateBattleStatus(Integer statusBef, Integer statusAft, List<String> battleIds);

    /**
     * 根据用户id+团Id定位取得团战记录详情
     * @param battleId
     * @param userId
     * @return
     */
    public BattleRecord getBattleRecordInfo(String battleId, String userId);

    /**
     * 更新团战记录信息
     * @param battleRecord
     * @return
     */
    public int updateBattleRecordInfo(BattleRecord battleRecord);

    /**
     * 通过礼物id取得礼物详情
     * @param giftId
     * @return
     */
    public Gift getGiftInfoById(Long giftId);

    /**
     * 按照成绩倒序取出团战记录信息
     * @param battleId
     * @return
     */
    public List<BattleRecord> getBattleRecordListByBattleId(String battleId);

    /**
     * 按照奖励档次寻找礼物id
     * @param rewardLevel
     * @return
     */
    public Long getGiftInfoByLevel(Integer rewardLevel);

    /**
     * 取得最新创建的10个未开始团战信息
     * @param start
     * @param size
     * @return
     */
    public List<Battle> getTenBattleInfoByStatus(Integer start, Integer size);
}
