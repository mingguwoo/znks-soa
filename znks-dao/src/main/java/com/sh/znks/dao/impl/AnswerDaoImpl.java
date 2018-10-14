package com.sh.znks.dao.impl;

import com.sh.znks.dao.AnswerDao;
import com.sh.znks.domain.aq.Answer;
import com.sh.znks.domain.dto.AnswerCondition;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuminggu on 2018/7/13.
 */
@Repository("AnswerDao")
public class AnswerDaoImpl implements AnswerDao {
    private final static Logger log = LoggerFactory.getLogger(AnswerDaoImpl.class);

    @Autowired
    private SqlSessionTemplate znksSqlSession;

    @Override
    public List<Answer> getAnswerInfos(AnswerCondition condition) {
        return znksSqlSession.selectList("Answer.getAnswerInfos", condition);
    }

    @Override
    public String getAnswerIdMax() {
        return znksSqlSession.selectOne("Answer.getAnswerIdMax");
    }

    @Override
    public int insertAnswers(List<Answer> answers) {
        return znksSqlSession.insert("Answer.insertAnswers", answers);
    }

    @Override
    public List<Answer> getAnswerInfoByQueList(String userId, String userZn, String connectId, List<Long> questionIdList) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("userId", userId);
        queryMap.put("userZn", userZn);
        queryMap.put("connectId", connectId);
        queryMap.put("questionIdList", questionIdList);
        return znksSqlSession.selectList("Answer.getAnswerInfoByQueList", queryMap);
    }

    @Override
    public Long getCountByOneHour() {
        return znksSqlSession.selectOne("Answer.getCountByOneHour");
    }

    @Override
    public Long getErrorAnswerCount(String userId) {
        return znksSqlSession.selectOne("Answer.getErrorAnswerCount", userId);
    }

    @Override
    public List<Long> getTenErrorAnswerInfo(String userId, Integer start, Integer size) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("userId", userId);
        queryMap.put("start", start);
        queryMap.put("size", size);
        return znksSqlSession.selectList("Answer.getTenErrorAnswerInfo", queryMap);
    }

    @Override
    public List<Long> getQuestionIdsByAnswer(String userId) {
        return znksSqlSession.selectList("Answer.getQuestionIdsByAnswer", userId);
    }

}
