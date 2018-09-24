package com.sh.znks.dao.impl;

import com.sh.znks.dao.UserDao;
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
}
