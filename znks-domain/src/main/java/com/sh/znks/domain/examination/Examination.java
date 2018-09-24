package com.sh.znks.domain.examination;

import java.util.Date;
import java.util.List;

import javax.jws.Oneway;

/**
 * Created by wuminggu on 2018/5/9.
 */
public class Examination {
    private Long id;                    //����������id
    private String testId;              //�Ծ�id
    private Integer totalScore;         //�ܷ�
    private Integer totalQuestions;     //��Ŀ����
    private Integer testTime;           //����ʱ�䣨���ӣ�
    private String questionList;        //��ĿList(String)
    private List<Long> questions;       //��ĿList
    private String scoreList;           //��ֵList(String)
    private List<Integer> scores;       //��ֵList
    private Integer status;             //�Ծ�״̬:0δͨ����1ͨ����2�����
    private Date created;               //����ʱ��
    private Date modified;              //����ʱ��

    public Examination() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Integer getTestTime() {
        return testTime;
    }

    public void setTestTime(Integer testTime) {
        this.testTime = testTime;
    }

    public String getQuestionList() {
        return questionList;
    }

    public void setQuestionList(String questionList) {
        this.questionList = questionList;
    }

    public List<Long> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Long> questions) {
        this.questions = questions;
    }

    public String getScoreList() {
        return scoreList;
    }

    public void setScoreList(String scoreList) {
        this.scoreList = scoreList;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
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
        return "Examination{" +
                "id=" + id +
                ", testId='" + testId + '\'' +
                ", totalScore=" + totalScore +
                ", totalQuestions=" + totalQuestions +
                ", testTime=" + testTime +
                ", questionList='" + questionList + '\'' +
                ", questions=" + questions +
                ", scoreList='" + scoreList + '\'' +
                ", scores=" + scores +
                ", status=" + status +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
