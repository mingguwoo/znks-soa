package com.sh.znks.domain.aq;

import java.util.Date;

/**
 * Created by wuminggu on 2018/9/25.
 */
public class BattleRecord {
    private Long id;                            //主键、自增id
    private String userId;                      //userId
    private String battleId;                    //战团id
    private String battleScore;                 //团战得分（正确率）
    private Long giftId;                        //礼物id
    private Integer ranking;                    //团战排名
    private Date created;                       //创建时间
    private Date modified;                      //更新时间

    public BattleRecord() {
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

    public String getBattleId() {
        return battleId;
    }

    public void setBattleId(String battleId) {
        this.battleId = battleId;
    }

    public String getBattleScore() {
        return battleScore;
    }

    public void setBattleScore(String battleScore) {
        this.battleScore = battleScore;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
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
        return "BattleRecord{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", battleId='" + battleId + '\'' +
                ", battleScore='" + battleScore + '\'' +
                ", giftId=" + giftId +
                ", ranking=" + ranking +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
