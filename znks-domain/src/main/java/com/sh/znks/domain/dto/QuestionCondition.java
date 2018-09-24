package com.sh.znks.domain.dto;

import java.util.Date;

/**
 * Created by wuminggu on 2018/7/6.
 */
public class QuestionCondition {
    private String questionId;          //����id
    private Integer grade;              //�����꼶��K1-K12��
    private Integer subjectId;          //������Ŀ:0����1�2��
    private Integer questionType;       //����:1��ѡ��2��ѡ��3�жϡ�4��ա�5���㡢6�ʴ�7���8������9������ա�10֤����11�Ķ���⡢12���ġ�13�����⡢�Զ���
    private String questionDescribe;    //��Ŀ����
    private Integer difficultyLevel;    //�Ѷ�ϵ����1-5���ǣ�
    private Integer questionValue;      //�����ֵ��0-5��Ѫ��
    private String expertId;            //������id/ר��id
    private String expertZn;            //����������/ר������
    private Integer status;             //��Ŀ״̬:0δͨ����1ͨ����2�����
    private Integer taskStatus;         //��Ŀ״̬:0δͨ����1ͨ����2�����
    private Date created;               //����ʱ��
    private Date modified;              //����ʱ��
    private Integer start = 0;          //��ѯ��ʼ������Ĭ��:0��ʼ��
    private Integer size = 10;          //��ѯ����������Ĭ��:10����

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
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
