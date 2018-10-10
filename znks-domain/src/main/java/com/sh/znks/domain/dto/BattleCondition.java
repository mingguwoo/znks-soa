package com.sh.znks.domain.dto;

import java.util.Date;

/**
 * Created by wuminggu on 2018/9/21.
 */
public class BattleCondition {
    private String userId;                      //UnionID
    private String battleId;                    //战团id
    private String battleName;                  //战团名称
    private String slogan;                      //口号
    private Integer difficultyLevel;            //难度系数（1-5颗星）
    private Integer questionArea;               //出题领域（小学、初中、高中、大学、英语等级、驾考、社会知识、益智类。。。）
    private Integer questionType;               //题型:0单选、1判断
    private Date startTime;                     //开团时间
    private Date battleTime;                    //答题截止时间
    private Integer status;                     //开团状态：0未开团、1团战中、2已结束、-1开团失败
    private Date created;                       //创建时间
    private Date modified;                      //更新时间
    private Integer start = 0;                  //查询开始条数（默认:0开始）
    private Integer size = 10;                  //查询限制条数（默认:10条）

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
