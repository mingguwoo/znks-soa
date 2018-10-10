package com.sh.znks.dao.impl;

import com.sh.znks.dao.QuestionDao;
import com.sh.znks.domain.aq.Answer;
import com.sh.znks.domain.aq.Memorandum;
import com.sh.znks.domain.aq.Question;
import com.sh.znks.domain.dto.AnswerCondition;
import com.sh.znks.domain.dto.HistoryQuestionCondition;
import com.sh.znks.domain.dto.QuestionCondition;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuminggu on 2018/7/5.
 */
@Repository("questionDao")
public class QuestionDaoImpl implements QuestionDao {
    private final static Logger log = LoggerFactory.getLogger(QuestionDaoImpl.class);

    @Autowired
    private SqlSessionTemplate znksSqlSession;

    @Override
    public int insertQuestion(Question question) {
        return znksSqlSession.insert("Question.insertQuestion", question);
    }

    @Override
    public Long getQuestionIdMax() {
        return znksSqlSession.selectOne("Question.getQuestionIdMax");
    }

    @Override
    public List<Question> getQuestionInfos(QuestionCondition condition) {
        return znksSqlSession.selectList("Question.getQuestionInfos", condition);
    }

    @Override
    public List<Question> getListByQuestionIds(List<Long> questionIds) {
        return znksSqlSession.selectList("Question.getListByQuestionIds", questionIds);
    }

    @Override
    public List<Question> getHistoryQuestions(HistoryQuestionCondition historyQuestionCondition) {
        return znksSqlSession.selectList("Question.getHistoryQuestions", historyQuestionCondition);
    }

    @Override
    public int insertMemorandum(Memorandum memorandum) {
        return znksSqlSession.insert("Question.insertMemorandum", memorandum);
    }

    @Override
    public Memorandum getMemorandumInfo(Long questionId, String userId) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("questionId", questionId);
        queryMap.put("userId", userId);
        return znksSqlSession.selectOne("Question.getMemorandumInfo", queryMap);
    }

    @Override
    public int updateMemorandumStatus(Memorandum memorandum) {
        return znksSqlSession.update("Question.updateMemorandumStatus", memorandum);
    }
}
