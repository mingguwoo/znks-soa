package com.sh.znks.domain.dto;

/**
 * Created by wuminggu on 2018/10/10.
 */
public class HistoryResult {
    private Long questionId;            //问题id
    private String questionDescribe;    //题目描述

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionDescribe() {
        return questionDescribe;
    }

    public void setQuestionDescribe(String questionDescribe) {
        this.questionDescribe = questionDescribe;
    }
}
