package com.sh.znks.domain.aq;

import java.util.Date;

/**
 * Created by wuminggu on 2018/5/9.
 */
public class Answer {
    private Long id;                    //����������id
    private String answerId;            //�ش�id
    private String questionId;          //����id
    private String answerDetail;        //�ύ��
    private String userId;              //�û�id/������id
    private String userZn;              //�û�zn/������zn
    private Integer result;             //���:0����1��ȷ
    private String expertId;            //�ж���id/ר��id
    private String expertZn;            //����������/ר������
    private String basis;               //�ж�����
    private Date judgeTime;             //�ж�ʱ��
    private Integer status;             //����״̬��0δ��ˡ�1����С�2�����ɡ�3�ѽ���
    private Date created;               //����ʱ��
    private Date modified;              //����ʱ��

    public Answer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

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
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
