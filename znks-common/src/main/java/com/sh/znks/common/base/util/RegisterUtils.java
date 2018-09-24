package com.sh.znks.common.base.util;

import com.sh.znks.common.base.Constant;
import com.sh.znks.domain.register.User;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.domain.user.WxUser;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuminggu on 2018/5/10.
 */
public class RegisterUtils {
    /**
     * �༭ע������(ѧ��)
     */
    public static GeneralUser InputGeneralParamSet(HttpServletRequest request) {

        GeneralUser user = new GeneralUser();
        //�״�ע��Ĭ��ֵ
        int rankings = Constant.ZERO;           //0������
        int status = Constant.TWO;              //2�����
        String zn = request.getParameter("zn");
        String nickName = request.getParameter("nickName");
        String sex = request.getParameter("sex");
        String age = request.getParameter("age");
        String headImg = request.getParameter("headImg");
        String phoneNumber = request.getParameter("phoneNumber");
        String userType = request.getParameter("userType");
        String signature = request.getParameter("signature");
        String userDescribe = request.getParameter("userDescribe");
        String grade = request.getParameter("grade");
        String company = request.getParameter("company");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");
        String accountNo = request.getParameter("accountNo");
        String accountType = request.getParameter("accountType");
        String additional = request.getParameter("additional");
        String onOffLine = request.getParameter("onOffLine");
        String source = request.getParameter("source");
        String registerType = request.getParameter("registerType");
        String password = request.getParameter("password");
        String passwordEncrypt = request.getParameter("passwordEncrypt");

        user.setRankings(rankings);
        user.setStatus(status);
        user.setZn(zn);
        user.setNickName(nickName);
        if (StringUtils.isNotBlank(sex)) {
            user.setSex(Integer.parseInt(sex));
        } else {
            user.setSex(Constant.EXC_NO);
        }
        if (StringUtils.isNotBlank(age)) {
            user.setAge(Integer.parseInt(age));
        } else {
            user.setAge(Constant.EXC_NO);
        }
        user.setHeadImg(headImg);
        user.setPhoneNumber(phoneNumber);
        if (StringUtils.isNotBlank(userType)) {
            user.setUserType(Integer.parseInt(userType));
        } else {
            user.setUserType(Constant.EXC_NO);
        }
        user.setSignature(signature);
        user.setUserDescribe(userDescribe);
        if (StringUtils.isNotBlank(grade)) {
            user.setGrade(Integer.parseInt(grade));
        } else {
            user.setGrade(Constant.EXC_NO);
        }
        user.setCompany(company);
        user.setProvince(province);
        user.setCity(city);
        user.setLongitude(longitude);
        user.setLatitude(latitude);
        user.setAccountNo(accountNo);
        if (StringUtils.isNotBlank(accountType)) {
            user.setAccountType(Integer.parseInt(accountType));
        } else {
            user.setAccountType(Constant.EXC_NO);
        }
        user.setAdditional(additional);
        if (StringUtils.isNotBlank(onOffLine)) {
            user.setOnOffLine(Integer.parseInt(onOffLine));
        } else {
            user.setOnOffLine(Constant.EXC_NO);
        }
        if (StringUtils.isNotBlank(source)) {
            user.setSource(Integer.parseInt(source));
        } else {
            user.setSource(Constant.EXC_NO);
        }
        user.setRegisterType(Integer.parseInt(registerType));
        user.setPassword(password);
        user.setPasswordEncrypt(passwordEncrypt);

        return user;
    }

    /**
     * �༭ע������(ר��)
     */
    public static ExpertUser InputExpertParamSet(HttpServletRequest request) {

        ExpertUser user = new ExpertUser();
        //�״�ע��Ĭ��ֵ
        int expertType = Constant.ZERO;         //0��ͨ��ʦ
        int status = Constant.TWO;              //2�����
        int level = Constant.ONE;               //1��
        String zn = request.getParameter("zn");
        String headImg = request.getParameter("headImg");
        String phoneNumber = request.getParameter("phoneNumber");
        String signature = request.getParameter("signature");
        String userDescribe = request.getParameter("userDescribe");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");
        String accountNo = request.getParameter("accountNo");
        String accountType = request.getParameter("accountType");
        String additional = request.getParameter("additional");
        String onOffLine = request.getParameter("onOffLine");
        String source = request.getParameter("source");
        String registerType = request.getParameter("registerType");
        String password = request.getParameter("password");
        String passwordEncrypt = request.getParameter("passwordEncrypt");

        user.setExpertType(expertType);
        user.setStatus(status);
        user.setLevel(level);
        user.setZn(zn);
        user.setHeadImg(headImg);
        user.setPhoneNumber(phoneNumber);
        user.setSignature(signature);
        user.setUserDescribe(userDescribe);
        user.setProvince(province);
        user.setCity(city);
        user.setLongitude(longitude);
        user.setLatitude(latitude);
        user.setAccountNo(accountNo);
        if (StringUtils.isNotBlank(accountType)) {
            user.setAccountType(Integer.parseInt(accountType));
        } else {
            user.setAccountType(Constant.EXC_NO);
        }
        user.setAdditional(additional);
        if (StringUtils.isNotBlank(onOffLine)) {
            user.setOnOffLine(Integer.parseInt(onOffLine));
        } else {
            user.setOnOffLine(Constant.EXC_NO);
        }
        if (StringUtils.isNotBlank(source)) {
            user.setSource(Integer.parseInt(source));
        } else {
            user.setSource(Constant.EXC_NO);
        }
        user.setRegisterType(Integer.parseInt(registerType));
        user.setPassword(password);
        user.setPasswordEncrypt(passwordEncrypt);

        return user;
    }

    /**
     * ѧ��ע����α༭
     * @param user
     * @param count
     * @return
     */
    public static GeneralUser editParamGU(GeneralUser user, Long count) {
        //�����趨
        String generalId = Constant.PJN + String.valueOf(Constant.BASE_NO + count).substring(Constant.ONE);
        String zn = user.getZn();
        String nickName = user.getNickName();
        String headImg = user.getHeadImg();
        if (StringUtils.isBlank(zn))
            zn = "X" + generalId;
        if (StringUtils.isBlank(nickName))
            nickName = "XS" + generalId;
        if (StringUtils.isBlank(headImg))
            headImg = "http://touxiang/moren/imag.jpg";

        //ע������
        GeneralUser generalUser = new GeneralUser();
        generalUser.setUserId(generalId);
        generalUser.setZn(zn);
        generalUser.setNickName(nickName);
        generalUser.setPassword(user.getPassword());
        generalUser.setPasswordEncrypt(user.getPasswordEncrypt());
        generalUser.setSex(user.getSex());
        generalUser.setAge(user.getAge());
        generalUser.setHeadImg(headImg);
        generalUser.setPhoneNumber(user.getPhoneNumber());
        generalUser.setUserType(user.getUserType());
        generalUser.setGrade(user.getGrade());
        generalUser.setCompany(user.getCompany());
        generalUser.setProvince(user.getProvince());
        generalUser.setCity(user.getCity());
        generalUser.setLongitude(user.getLongitude());
        generalUser.setLatitude(user.getLatitude());
        generalUser.setAccountNo(user.getAccountNo());
        generalUser.setAccountType(user.getAccountType());
        generalUser.setRankings(user.getRankings());
        generalUser.setSource(user.getSource());
        generalUser.setOnOffLine(user.getOnOffLine());
        generalUser.setStatus(user.getStatus());
        generalUser.setAdditional(user.getAdditional());
        generalUser.setSignature(user.getSignature());
        generalUser.setUserDescribe(user.getUserDescribe());

        return generalUser;
    }

    /**
     * ר��ע����α༭
     * @param user
     * @param count
     * @return
     */
    public static ExpertUser editParamEX(ExpertUser user, Long count) {
        //�����趨
        String expertId = Constant.PJN + String.valueOf(Constant.BASE_NO + count).substring(Constant.ONE);
        String zn = user.getZn();
        String expertNick = user.getExpertNick();
        String headImg = user.getHeadImg();
        if (StringUtils.isBlank(zn))
            zn = "Z" + expertId;
        if (StringUtils.isBlank(expertNick))
            expertNick = "ZJ" + expertId;
        if (StringUtils.isBlank(headImg))
            headImg = "http://touxiang/moren/imag.jpg";

        //ע������
        ExpertUser expertUser = new ExpertUser();
        expertUser.setExpertId(expertId);
        expertUser.setZn(zn);
        expertUser.setExpertNick(expertNick);
        expertUser.setPassword(user.getPassword());
        expertUser.setPasswordEncrypt(user.getPasswordEncrypt());
        expertUser.setHeadImg(headImg);
        expertUser.setPhoneNumber(user.getPhoneNumber());
        expertUser.setProvince(user.getProvince());
        expertUser.setCity(user.getCity());
        expertUser.setLongitude(user.getLongitude());
        expertUser.setLatitude(user.getLatitude());
        expertUser.setAccountNo(user.getAccountNo());
        expertUser.setAccountType(user.getAccountType());
        expertUser.setOnOffLine(user.getOnOffLine());
        expertUser.setExpertType(user.getExpertType());
        expertUser.setLevel(user.getLevel());
        expertUser.setStatus(user.getStatus());
        expertUser.setAdditional(user.getAdditional());
        expertUser.setSignature(user.getSignature());
        expertUser.setUserDescribe(user.getUserDescribe());

        return expertUser;
    }

    /**
     * �༭ע������(΢��)
     */
    public static WxUser InputWxParamSet(HttpServletRequest request) {

        WxUser user = new WxUser();
        String unionId = request.getParameter("unionId");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        String nickName = request.getParameter("nickName");
        String avatarUrl = request.getParameter("avatarUrl");
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phoneNumber");
        String purePhoneNumber = request.getParameter("purePhoneNumber");
        String countryCode = request.getParameter("countryCode");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String version = request.getParameter("version");
        String platform = request.getParameter("platform");
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String country = request.getParameter("country");

        user.setUnionId(unionId);
        user.setLatitude(latitude);
        user.setLongitude(longitude);
        user.setNickName(nickName);
        user.setAvatarUrl(avatarUrl);
        user.setGender(gender);
        user.setPhoneNumber(phoneNumber);
        user.setPurePhoneNumber(purePhoneNumber);
        user.setCountryCode(countryCode);
        user.setBrand(brand);
        user.setModel(model);
        user.setVersion(version);
        user.setPlatform(platform);
        user.setCity(city);
        user.setProvince(province);
        user.setCountry(country);

        return user;
    }
}
