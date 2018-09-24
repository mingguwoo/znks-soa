package com.sh.znks.domain.register;

import java.util.Date;

/**
 * Created by wuminggu on 2018/5/10.
 */
public class User {
    private Long id;                    //����������id
    private String zn;                  //ZN
    private String headImg;             //ͷ������
    private String phoneNumber;         //�绰���루�ֻ���
    private String signature;           //����ǩ��
    private String userDescribe;        //�û����
    private String province;            //ʡ��
    private String city;                //����
    private String longitude;           //����
    private String latitude;            //��γ
    private String accountNo;           //�����˻���
    private Integer accountType;        //�˻�����:0���п���1֧������2΢��
    private String additional;          //������Ϣ
    private Integer source;             //�û���Դ:0���ע�ᡢ1�Ƽ���2�������롢3����
    private Integer status;             //�û�״̬:0��Ч��1��Ч��2�����
    private Integer onOffLine;          //��¼״̬:0δ��¼��1�ѵ�¼
    private Date created;               //����ʱ��
    private Date modified;              //����ʱ��
    private Integer registerType;       //ע������:0ѧ��ע�ᡢ1ר��ע��
    private String password;            //��������
    private String passwordEncrypt;     //��������

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZn() {
        return zn;
    }

    public void setZn(String zn) {
        this.zn = zn;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUserDescribe() {
        return userDescribe;
    }

    public void setUserDescribe(String userDescribe) {
        this.userDescribe = userDescribe;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOnOffLine() {
        return onOffLine;
    }

    public void setOnOffLine(Integer onOffLine) {
        this.onOffLine = onOffLine;
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

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordEncrypt() {
        return passwordEncrypt;
    }

    public void setPasswordEncrypt(String passwordEncrypt) {
        this.passwordEncrypt = passwordEncrypt;
    }
}
