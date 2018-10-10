package com.sh.znks.domain.aq;

import java.util.Date;

/**
 * Created by wuminggu on 2018/9/21.
 */
public class Battle {
    private Long id;                            //主键、自增id
    private String userId;                      //UnionID
    private String battleId;                    //战团id
    private String battleName;                  //战团名称
    private String slogan;                      //口号
    private Integer difficultyLevel;            //难度系数（1-5颗星）
    private Integer questionArea;               //出题领域（小学、初中、高中、大学、英语等级、驾考、社会知识、益智类。。。）
    private Integer questionType;               //题型:0单选、1判断
    private Date startTime;                     //开团时间
    private Date battleTime;                    //答题截止时间
    private Integer battleCount;                //参团人数（设置上限）
    private Integer battleAmount;               //参团人数（实际人数）
    private String questionIds;                   //挑战题目List
    private Integer status;                     //开团状态：0未开团、1团战中、2已结束、-1开团失败
    private Date created;                       //创建时间
    private Date modified;                      //更新时间

    public Battle() {
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

    public String getBattleName() {
        return battleName;
    }

    public void setBattleName(String battleName) {
        this.battleName = battleName;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getQuestionArea() {
        return questionArea;
    }

    public void setQuestionArea(Integer questionArea) {
        this.questionArea = questionArea;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getBattleTime() {
        return battleTime;
    }

    public void setBattleTime(Date battleTime) {
        this.battleTime = battleTime;
    }

    public Integer getBattleCount() {
        return battleCount;
    }

    public void setBattleCount(Integer battleCount) {
        this.battleCount = battleCount;
    }

    public Integer getBattleAmount() {
        return battleAmount;
    }

    public void setBattleAmount(Integer battleAmount) {
        this.battleAmount = battleAmount;
    }

    public String getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(String questionIds) {
        this.questionIds = questionIds;
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
        return "Battle{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", battleId='" + battleId + '\'' +
                ", battleName='" + battleName + '\'' +
                ", slogan='" + slogan + '\'' +
                ", difficultyLevel=" + difficultyLevel +
                ", questionArea=" + questionArea +
                ", questionType=" + questionType +
                ", startTime=" + startTime +
                ", battleTime=" + battleTime +
                ", battleCount=" + battleCount +
                ", battleAmount=" + battleAmount +
                ", questionIds='" + questionIds + '\'' +
                ", status=" + status +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
