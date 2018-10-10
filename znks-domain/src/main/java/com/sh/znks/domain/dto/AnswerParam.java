package com.sh.znks.domain.dto;

/**
 * Created by wuminggu on 2018/7/23.
 */
public class AnswerParam {
    private Long questionId;          //问题id
    private String answerDetail;        //提交答案描述
    private String userId;              //用户id/答题者id
    private String battleId;            //团id

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
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

    public String getBattleId() {
        return battleId;
    }

    public void setBattleId(String battleId) {
        this.battleId = battleId;
    }
}
