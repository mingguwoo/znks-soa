package com.sh.znks.dao.impl;

import com.sh.znks.dao.UserDao;
import com.sh.znks.domain.aq.SignRecord;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.domain.user.WxUser;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuminggu on 2018/5/9.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    private final static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private SqlSessionTemplate znksSqlSession;

    @Override
    public WxUser getWxUserByUnionid(String unionId) {
        return znksSqlSession.selectOne("WxUser.getWxUserByUnionid", unionId);
    }

    @Override
    public int insertWxUser(WxUser wxUser) {
        return znksSqlSession.insert("WxUser.insertWxUser", wxUser);
    }

    @Override
    public List<Date> getSignInfo(String userId, String created) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("userId", userId);
        queryMap.put("created", created);
        return znksSqlSession.selectList("WxUser.getSignInfo", queryMap);
    }

    @Override
    public int insertSignInfo(String userId) {
        return znksSqlSession.insert("WxUser.insertSignInfo", userId);
    }

    @Override
    public int deleteSignInfoByUserId(String userId) {
        return znksSqlSession.delete("WxUser.deleteSignInfoByUserId", userId);
    }

    @Override
    public int insertFeedBackInfo(String userId, String content) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("userId", userId);
        queryMap.put("content", content);
        return znksSqlSession.insert("WxUser.insertFeedBackInfo", queryMap);
    }

    @Override
    public List<WxUser> getUserListByUnionIds(List<String> unionIds) {
        return znksSqlSession.selectList("WxUser.getUserListByUnionIds", unionIds);
    }

    @Override
    public int insertSignRecord(SignRecord signRecord) {
        return znksSqlSession.insert("WxUser.insertSignRecord", signRecord);
    }

    @Override
    public int updateSignRecord(SignRecord signRecord) {
        return znksSqlSession.update("WxUser.updateSignRecord", signRecord);
    }

    @Override
    public SignRecord getSignRecordInfoByUserId(String userId) {
        return znksSqlSession.selectOne("WxUser.getSignRecordInfoByUserId", userId);
    }

    @Override
    public int updateExpirySignRecord() {
        return znksSqlSession.update("WxUser.updateExpirySignRecord");
    }

    @Override
    public List<WxUser> getTenUserInfoByRelation(String latitude, String longitude, Integer start, Integer size) {
        Map<String, Object> queryMap = new HashMap();
        queryMap.put("latitude", latitude);
        queryMap.put("longitude", longitude);
        queryMap.put("start", start);
        queryMap.put("size", size);
        return znksSqlSession.selectList("WxUser.getTenUserInfoByRelation", queryMap);
    }

}
