package com.sh.znks.domain.dto;

/**
 * Created by wuminggu on 2018/7/23.
 */
public class AnswerParam {
    private String questionId;          //����id
    private String answerDetail;        //�ύ������
    private String userId;              //�û�id/������id

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
