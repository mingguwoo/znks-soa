package com.sh.znks.domain.dto;

/**
 * Created by wuminggu on 2018/9/26.
 */
public class BattleResult {
    private Integer serialNumber;           //题目序号
    private String commitAnswer;            //提交的答案
    private String standardAnswer;          //标准答案
    private boolean result;                 //判断结果
    private Long questionId;              //问题id

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getCommitAnswer() {
        return commitAnswer;
    }

    public void setCommitAnswer(String commitAnswer) {
        this.commitAnswer = commitAnswer;
    }

    public String getStandardAnswer() {
        return standardAnswer;
    }

    public void setStandardAnswer(String standardAnswer) {
        this.standardAnswer = standardAnswer;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
