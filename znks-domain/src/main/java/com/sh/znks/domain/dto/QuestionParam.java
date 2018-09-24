package com.sh.znks.domain.dto;

/**
 * Created by wuminggu on 2018/7/5.
 */
public class QuestionParam {
    private Integer grade;              //�����꼶��K1-K12��
    private Integer subjectId;          //������Ŀ:0����1�2��
    private Integer questionType;       //����:1��ѡ��2��ѡ��3�жϡ�4��ա�5���㡢6�ʴ�7���8������9������ա�10֤����11�Ķ���⡢12���ġ�13�����⡢�Զ���
    private String questionDescribe;    //��Ŀ����
    private String option1;             //ѡ������1
    private String option2;             //ѡ������2
    private String option3;             //ѡ������3
    private String option4;             //ѡ������4
    private String option5;             //ѡ������5
    private String option6;             //ѡ������6
    private String option7;             //ѡ������7
    private String option8;             //ѡ������8
    private String option9;             //ѡ������9
    private String option10;            //ѡ������10
    private String tips;                //��ʾ����
    private String standardAnswer;      //��׼��
    private Integer difficultyLevel;    //�Ѷ�ϵ����1-5���ǣ�
    private Integer questionValue;      //�����ֵ��0-5��Ѫ��
    private String expertId;            //������id/ר��id
    private String expertZn;            //������ZN/ר��ZN
    private String knowledge;           //֪ʶ��

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

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public String getOption6() {
        return option6;
    }

    public void setOption6(String option6) {
        this.option6 = option6;
    }

    public String getOption7() {
        return option7;
    }

    public void setOption7(String option7) {
        this.option7 = option7;
    }

    public String getOption8() {
        return option8;
    }

    public void setOption8(String option8) {
        this.option8 = option8;
    }

    public String getOption9() {
        return option9;
    }

    public void setOption9(String option9) {
        this.option9 = option9;
    }

    public String getOption10() {
        return option10;
    }

    public void setOption10(String option10) {
        this.option10 = option10;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getStandardAnswer() {
        return standardAnswer;
    }

    public void setStandardAnswer(String standardAnswer) {
        this.standardAnswer = standardAnswer;
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

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }
}
