package com.sh.znks.domain.dto;

import java.util.Date;

/**
 * Created by wuminggu on 2018/9/21.
 */
public class BattleParam {
    private String battleId;                    //战团id
    private String battleName;                  //战团名称
    private String slogan;                      //口号
    private Integer difficultyLevel;            //难度系数（1-5颗星）
    private Integer questionArea;               //出题领域（小学、初中、高中、大学、英语等级、驾考、社会知识、益智类。。。）
    private Integer questionType;               //题型:0单选、1判断
    private Date startTime;                     //开团时间
    private Date battleTime;                     //答题截止时间(Date)
    private Integer battleTimeSecond;           //答题截止时间(分钟)
    private Integer battleCount;                //参团人数（设置上限）

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

    public Integer getBattleTimeSecond() {
        return battleTimeSecond;
    }

    public void setBattleTimeSecond(Integer battleTimeSecond) {
        this.battleTimeSecond = battleTimeSecond;
    }

    public Integer getBattleCount() {
        return battleCount;
    }

    public void setBattleCount(Integer battleCount) {
        this.battleCount = battleCount;
    }
}
