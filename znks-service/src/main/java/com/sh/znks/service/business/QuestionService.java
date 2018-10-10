package com.sh.znks.service.business;

import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.dto.AnswerParam;
import com.sh.znks.domain.dto.QuestionCondition;
import com.sh.znks.domain.dto.QuestionParam;

import java.util.List;

/**
 * Created by wuminggu on 2018/7/5.
 */
public interface QuestionService {
    /**
     * 发布题目
     * @param param
     * @return
     */
    public ResultResponse deployQuestion(QuestionParam param);

    /**
     * 查询题目列表
     * 包括已做题、未做题筛选
     * @param condition
     * @return
     */
    public ResultResponse getQuestionList(QuestionCondition condition);

    /**
     * 取得指定题目的详情
     * @param questionId
     * @return
     */
    public ResultResponse questionDetail(Long questionId);

    /**
     * 提交答案
     * @param params
     * @return
     */
    public ResultResponse submitAnswer(List<AnswerParam> params);

    /**
     * 查询题目列表(错题)
     * @return
     */
    public ResultResponse getErrorQuestionList();
}
