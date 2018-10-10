package com.sh.znks.dao.impl;

import com.sh.znks.dao.BattleDao;
import com.sh.znks.domain.aq.Battle;
import com.sh.znks.domain.aq.BattleRecord;
import com.sh.znks.domain.aq.Gift;
import com.sh.znks.domain.aq.Question;
import com.sh.znks.domain.dto.BattleCondition;
import com.sh.znks.domain.dto.QuestionCondition;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuminggu on 2018/9/21.
 */
@Repository("battleDao")
public class BattleDaoImpl implements BattleDao {
    private final static Logger log = LoggerFactory.getLogger(BattleDaoImpl.class);

    @Autowired
    private SqlSessionTemplate znksSqlSession;

    @Override
    public int insertBattle(Battle battle) {
        return znksSqlSession.insert("Battle.insertBattle", battle);
    }

    @Override
    public List<Battle> getBattleInfos(BattleCondition condition) {
        return znksSqlSession.selectList("Battle.getBattleInfos", condition);
    }

    @Override
    public int insertBattleRecord(BattleRecord battleRecord) {
        return znksSqlSession.insert("Battle.insertBattleRecord", battleRecord);
    }

    @Override
    public List<String> getBattleRecordInfos(String battleId) {
        return znksSqlSession.selectList("Battle.getBattleUserListByBattleId", battleId);
    }

    @Override
    public int updateBattleAmount(String battleId) {
        return znksSqlSession.update("Battle.updateBattleAmount", battleId);
    }

    @Override
    public List<Battle> getBattleListByStatus() {
        return znksSqlSession.selectList("Battle.getBattleListByStatus");
    }

    @Override
    public int updateBattleStatus(Integer statusBef, Integer statusAft, List<String> battleIds) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("statusBef", statusBef);
        queryMap.put("statusAft", statusAft);
        queryMap.put("battleIds", battleIds);
        return znksSqlSession.update("Battle.updateBattleStatus", queryMap);
    }

    @Override
    public BattleRecord getBattleRecordInfo(String battleId, String userId) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("battleId", battleId);
        queryMap.put("userId", userId);
        return znksSqlSession.selectOne("Battle.getBattleRecordInfo", queryMap);
    }

    @Override
    public int updateBattleRecordInfo(BattleRecord battleRecord) {
        return znksSqlSession.update("Battle.updateBattleRecordInfo", battleRecord);
    }

    @Override
    public Gift getGiftInfoById(Long giftId) {
        return znksSqlSession.selectOne("Battle.getGiftInfoById", giftId);
    }

    @Override
    public List<BattleRecord> getBattleRecordListByBattleId(String battleId) {
        return znksSqlSession.selectList("Battle.getBattleRecordListByBattleId", battleId);
    }

    @Override
    public Long getGiftInfoByLevel(Integer rewardLevel) {
        return znksSqlSession.selectOne("Battle.getGiftInfoByLevel", rewardLevel);
    }

    @Override
    public List<Battle> getTenBattleInfoByStatus(Integer start, Integer size) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("start", start);
        queryMap.put("size", size);
        return znksSqlSession.selectOne("Battle.getTenBattleInfoByStatus", queryMap);
    }

}
