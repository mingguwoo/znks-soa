package com.sh.znks.domain.dto;

import java.util.List;

/**
 * Created by wuminggu on 2018/10/9.
 */
public class HistoryQuestionCondition {
    private Integer grade;              //所属年级（K1-K12）
    private Integer subjectId;          //所属科目:0数、1语、2外
    private Integer questionType;       //题型:0单选、1判断
    private Integer difficultyLevel;    //难度系数（1-5颗星）
    private Integer questionValue;      //问题价值（0-5滴血）
    private Integer questionArea;       //出题领域（小学、初中、高中、大学、英语等级、驾考、社会知识、益智类。。。）
    private List<Long> questionIds;   //问题idList
    private Integer start = 0;          //查询开始条数（默认:0开始）
    private Integer size = 10;          //查询限制条数（默认:10条）

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

    public Integer getQuestionArea() {
        return questionArea;
    }

    public void setQuestionArea(Integer questionArea) {
        this.questionArea = questionArea;
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
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
