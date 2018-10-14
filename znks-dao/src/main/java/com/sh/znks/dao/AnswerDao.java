package com.sh.znks.dao;

import com.sh.znks.domain.aq.Answer;
import com.sh.znks.domain.dto.AnswerCondition;

import java.util.List;

/**
 * Created by wuminggu on 2018/7/13.
 */
public interface AnswerDao {
    /**
     * 查询单个题对应解析结果List
     * @param condition
     * @return
     */
    public List<Answer> getAnswerInfos(AnswerCondition condition);

    /**
     * 取得最大答案id值
     * @return
     */
    public String getAnswerIdMax();

    /**
     * 提交答案信息
     * @param answers
     * @return
     */
    public int insertAnswers(List<Answer> answers);

    /**
     * 根据questionIdList取得答案List
     * @param userId
     * @param userZn
     * @param questionIdList
     * @return
     */
    public List<Answer> getAnswerInfoByQueList(String userId, String userZn, String connectId, List<Long> questionIdList);

    /**
     * 取得一小时内提交答案的用户数量
     * @return
     */
    public Long getCountByOneHour();

    /**
     * 查询用户的错题数
     * @param userId
     * @return
     */
    public Long getErrorAnswerCount(String userId);

    /**
     * 取得10到错题id
     * @param userId
     * @param start
     * @param size
     * @return
     */
    public List<Long> getTenErrorAnswerInfo(String userId, Integer start, Integer size);

    /**
     * 查询30天内的历史题目id
     * @param userId
     * @return
     */
    public List<Long> getQuestionIdsByAnswer(String userId);
}
