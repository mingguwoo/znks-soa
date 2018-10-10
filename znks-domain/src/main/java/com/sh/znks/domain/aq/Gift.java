package com.sh.znks.domain.aq;

import java.util.Date;

/**
 * Created by wuminggu on 2018/9/26.
 */
public class Gift {
    private Long id;                    //主键、自增id
    private String giftName;            //礼物名称
    private Integer giftValue;          //礼物价值（元）
    private Integer rewardLevel;        //奖励档次
    private Integer status;             //状态：0无效、1有效
    private Date created;               //创建时间
    private Date modified;              //更新时间

    public Gift() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public Integer getGiftValue() {
        return giftValue;
    }

    public void setGiftValue(Integer giftValue) {
        this.giftValue = giftValue;
    }

    public Integer getRewardLevel() {
        return rewardLevel;
    }

    public void setRewardLevel(Integer rewardLevel) {
        this.rewardLevel = rewardLevel;
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
        return "Gift{" +
                "id=" + id +
                ", giftName='" + giftName + '\'' +
                ", giftValue=" + giftValue +
                ", rewardLevel=" + rewardLevel +
                ", status=" + status +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
