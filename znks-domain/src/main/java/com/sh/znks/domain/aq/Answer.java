package com.sh.znks.domain.aq;

import java.util.Date;

/**
 * Created by wuminggu on 2018/5/9.
 */
public class Answer {
    private Long id;                    //主键、自增id
    private Long answerId;              //回答id
    private Long questionId;            //问题id
    private String answerDetail;        //提交答案
    private String userId;              //用户id/答题者id
    private String userZn;              //用户zn/答题者zn
    private Integer result;             //结果:0错误、1正确
    private String expertId;            //判断者id/专家id
    private String expertZn;            //出题者名称/专家名称
    private String basis;               //判断理由
    private Date judgeTime;             //判断时间
    private Integer status;             //答题状态：0未审核、1审核中、2审核完成、3已结算
    private String connectId;           //连接id（如：团id）
    private Date created;               //创建时间
    private Date modified;              //更新时间

    public Answer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

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

    public String getUserZn() {
        return userZn;
    }

    public void setUserZn(String userZn) {
        this.userZn = userZn;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getExpertZn() {
        return expertZn;
    }

    public void setExpertZn(String expertZn) {
        this.expertZn = expertZn;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public Date getJudgeTime() {
        return judgeTime;
    }

    public void setJudgeTime(Date judgeTime) {
        this.judgeTime = judgeTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getConnectId() {
        return connectId;
    }

    public void setConnectId(String connectId) {
        this.connectId = connectId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerId='" + answerId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", answerDetail='" + answerDetail + '\'' +
                ", userId='" + userId + '\'' +
                ", userZn='" + userZn + '\'' +
                ", result=" + result +
                ", expertId='" + expertId + '\'' +
                ", expertZn='" + expertZn + '\'' +
                ", basis='" + basis + '\'' +
                ", judgeTime=" + judgeTime +
                ", status=" + status +
                ", connectId='" + connectId + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
