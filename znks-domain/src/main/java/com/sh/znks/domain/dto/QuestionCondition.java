package com.sh.znks.domain.dto;

import java.util.Date;

/**
 * Created by wuminggu on 2018/7/6.
 */
public class QuestionCondition {
    private Long questionId;            //问题id
    private Integer grade;              //所属年级（K1-K12）
    private Integer subjectId;          //所属科目:0数、1语、2外
    private Integer questionType;       //题型:0单选、1判断
    private String questionDescribe;    //题目描述
    private Integer difficultyLevel;    //难度系数（1-5颗星）
    private Integer questionValue;      //问题价值（0-5滴血）
    private String expertId;            //出题者id/专家id
    private String expertZn;            //出题者名称/专家名称
    private Integer status;             //题目状态:0未通过、1通过、2审核中
    private Integer taskStatus;         //题目状态:0未做题、1已做题
    private Integer questionArea;       //出题领域（小学、初中、高中、大学、英语等级、驾考、社会知识、益智类。。。）
    private Date created;               //创建时间
    private Date modified;              //更新时间
    private Integer start = 0;          //查询开始条数（默认:0开始）
    private Integer size = 10;          //查询限制条数（默认:10条）

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestionDescribe() {
        return questionDescribe;
    }

    public void setQuestionDescribe(String questionDescribe) {
        this.questionDescribe = questionDescribe;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getQuestionValue() {
        return questionValue;
    }

    public void setQuestionValue(Integer questionValue) {
        this.questionValue = questionValue;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getQuestionArea() {
        return questionArea;
    }

    public void setQuestionArea(Integer questionArea) {
        this.questionArea = questionArea;
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
