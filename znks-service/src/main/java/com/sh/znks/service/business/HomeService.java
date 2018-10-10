package com.sh.znks.service.business;

import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.dto.HistoryQuestionCondition;

/**
 * Created by wuminggu on 2018/10/9.
 */
public interface HomeService {
    /**
     * 取得首页展示信息
     * @return
     */
    ResultResponse getHomeInfo();

    /**
     * 查询奋斗史详情
     * @param historyQuestionCondition
     * @return
     */
    ResultResponse getHistoryQuestionInfo(HistoryQuestionCondition historyQuestionCondition);

    /**
     * 收藏题
     * @param questionId
     * @param status（1已收藏、0取消收藏）
     * @return
     */
    ResultResponse collectionQuestion(Long questionId, Integer status);
}
