package com.sh.znks.domain.aq;

import java.util.Date;

/**
 * Created by wuminggu on 2018/9/27.
 */
public class SignRecord {
    private Long id;                    //主键、自增id
    private String userId;              //用户id
    private Integer signCount;          //连续打卡天数
    private Long giftId;                //礼物id
    private Integer status;             //礼物状态：0未领取、1已领取、2已过期
    private Date effectiveTime;         //礼物有效时间
    private Date created;               //创建时间
    private Date modified;              //更新时间

    public SignRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
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
        return "SignRecord{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", signCount=" + signCount +
                ", giftId=" + giftId +
                ", status=" + status +
                ", effectiveTime=" + effectiveTime +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
