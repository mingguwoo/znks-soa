package com.sh.znks.domain.user;

import com.sh.znks.domain.register.User;

/**
 * Created by wuminggu on 2018/5/8.
 */
public class ExpertUser extends User {
    private String expertId;            //ר��id
    private String expertNick;          //�û��ǳ�
    private Integer expertType;         //ר�����ͣ�0��ͨ��ʦ��1��ʦ��2ר�ҡ�3Ȩ��ר��
    private Integer level;              //Ȩ���ȼ���1�Ρ�2�Ρ�3�Ρ�4�Ρ�5��

    public String getExpertId() {
        return expertId;
    }

    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }

    public String getExpertNick() {
        return expertNick;
    }

    public void setExpertNick(String expertNick) {
        this.expertNick = expertNick;
    }

    public Integer getExpertType() {
        return expertType;
    }

    public void setExpertType(Integer expertType) {
        this.expertType = expertType;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
