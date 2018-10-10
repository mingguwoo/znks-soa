package com.sh.znks.domain.aq;

import java.util.Date;

/**
 * Created by wuminggu on 2018/5/9.
 */
public class Memorandum {
    private Long id;                    //主键、自增id
    private Long questionId;          //问题id
    private String userId;              //用户id/答题者id
    private Integer status;             //状态:0已收藏、1取消收藏
    private Date created;               //创建时间
    private Date modified;              //更新时间

    public Memorandum() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
                ", userId='" + userId + '\'' +
                ", status=" + status +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
