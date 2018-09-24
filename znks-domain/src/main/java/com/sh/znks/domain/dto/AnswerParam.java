package com.sh.znks.domain.dto;

/**
 * Created by wuminggu on 2018/7/23.
 */
public class AnswerParam {
    private String questionId;          //问题id
    private String answerDetail;        //提交答案描述
    private String userId;              //用户id/答题者id

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(String answerDetail) {
        this.answerDetail = answerDetail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
