package com.sh.znks.domain.user;

import com.sh.znks.domain.register.User;

/**
 * Created by wuminggu on 2018/5/8.
 */
public class GeneralUser extends User {
    private String userId;              //�û�id
    private String nickName;            //�û��ǳ�
    private Integer sex;                //�Ա�0Ů��1��
    private Integer age;                //����
    private Integer userType;           //�û�����:0����ˡ�1ѧ����2��ʦ��3����
    private Integer grade;              //�꼶��K1-K12��
    private String company;             //�û�������λ
    private Integer rankings;           //��λ:8������ǿ��7սʥ��6ս��5Ӣ�ۡ�4��ͷ��硢3�����ҡ�2���ܡ�1С�ܡ�0������

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getRankings() {
        return rankings;
    }

    public void setRankings(Integer rankings) {
        this.rankings = rankings;
    }
}
