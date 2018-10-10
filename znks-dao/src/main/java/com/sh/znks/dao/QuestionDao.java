package com.sh.znks.dao;

import com.sh.znks.domain.aq.Memorandum;
import com.sh.znks.domain.aq.Question;
import com.sh.znks.domain.dto.HistoryQuestionCondition;
import com.sh.znks.domain.dto.QuestionCondition;
import java.util.List;

/**
 * Created by wuminggu on 2018/7/5.
 */
public interface QuestionDao {
    /**
     * 插入问题信息
     * @param question
     * @return
     */
    public int insertQuestion(Question question);

    /**
     * 取得最大的问题id值
     * @return
     */
    public Long getQuestionIdMax();

    /**
     * 查询题目详情List
     * @return
     */
    public List<Question> getQuestionInfos(QuestionCondition condition);

    /**
     * 通过问题ids获取问题详情list
     * @param questionIds
     * @return
     */
    public List<Question> getListByQuestionIds(List<Long> questionIds);

    /**
     * 根据条件查询历史做题记录
     * @param historyQuestionCondition
     * @return
     */
    public List<Question> getHistoryQuestions(HistoryQuestionCondition historyQuestionCondition);

    /**
     * 插入收藏题目信息
     * @param memorandum
     * @return
     */
    public int insertMemorandum(Memorandum memorandum);

    /**
     * 查询收藏题信息
     * @param questionId
     * @param userId
     * @return
     */
    public Memorandum getMemorandumInfo(Long questionId, String userId);

    /**
     * 收藏、取消收藏
     * @param memorandum
     * @return
     */
    public int updateMemorandumStatus(Memorandum memorandum);
}
