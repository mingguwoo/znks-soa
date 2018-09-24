package com.sh.znks.dao;

import com.sh.znks.domain.user.WxUser;

import java.util.Date;
import java.util.List;

/**
 * Created by wuminggu on 2018/5/9.
 */
public interface UserDao {
    /**
     * 取得微信用户信息
     * @param unionId
     * @return
     */
    WxUser getWxUserByUnionid(String unionId);

    /**
     * 插入微信用户信息
     * @param wxUser
     * @return
     */
    int insertWxUser(WxUser wxUser);

    /**
     * 取得用户签到信息
     * @param userId
     * @param created
     * @return
     */
    List<Date> getSignInfo(String userId, String created);

    /**
     * 插入签到信息
     * @param userId
     * @return
     */
    public int insertSignInfo(String userId);

    /**
     * 删除签到信息
     * @param userId
     * @return
     */
    public int deleteSignInfoByUserId(String userId);
}
