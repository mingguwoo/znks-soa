package com.sh.znks.domain.aq;

import java.util.Date;

/**
 * Created by wuminggu on 2018/5/9.
 */
public class Memorandum {
    private Long id;                    //����������id
    private String questionId;          //����id
    private String answerId;            //�ش�id
    private String userId;              //�û�id/������id
    private Integer type;               //�ղ�����:0���⡢1������
    private Integer status;             //״̬:0���ղء�1ȡ���ղ�
    private Date created;               //����ʱ��
    private Date modified;              //����ʱ��

    public Memorandum() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
        return "Memorandum{" +
                "id=" + id +
                ", questionId='" + questionId + '\'' +
                ", answerId='" + answerId + '\'' +
                ", userId='" + userId + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
