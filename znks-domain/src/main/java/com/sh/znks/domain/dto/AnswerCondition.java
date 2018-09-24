package com.sh.znks.domain.dto;

import java.util.Date;

/**
 * Created by wuminggu on 2018/7/13.
 */
public class AnswerCondition {
    private String answerId;            //�ش�id
    private String questionId;          //����id
    private String userId;              //�û�id/������id
    private String userZn;              //�û�zn/������zn
    private String answerDetail;        //�ύ������
    private Integer result;             //���:0����1��ȷ
    private String expertId;            //������id/ר��id
    private String expertZn;            //����������/ר������
    private String basis;               //�ж�����
    private Date judgeTime;             //�ж�ʱ��
    private Date created;               //����ʱ��
    private Date modified;              //����ʱ��
    private Integer status;             //����״̬��0δ��ˡ�1����С�2�����ɡ�3�ѽ���
    private Integer start = 0;          //��ѯ��ʼ������Ĭ��:0��ʼ��
    private Integer size = 10;          //��ѯ����������Ĭ��:10����

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

    public String getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(String answerDetail) {
        this.answerDetail = answerDetail;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
