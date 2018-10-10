package com.sh.znks.dao;

import com.sh.znks.domain.aq.SignRecord;
import com.sh.znks.domain.user.WxUser;

import java.rmi.server.ServerRef;
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

    /**
     * 插入反馈信息
     * @param userId
     * @param content
     * @return
     */
    public int insertFeedBackInfo(String userId, String content);

    /**
     * 根据UnionId取得用户详细信息
     * @param unionIds
     * @return
     */
    public List<WxUser> getUserListByUnionIds(List<String> unionIds);

    /**
     * 插入连续打卡送礼物信息
     * @param signRecord
     * @return
     */
    public int insertSignRecord(SignRecord signRecord);

    /**
     * 更新打卡送礼物信息
     * @param signRecord
     * @return
     */
    public int updateSignRecord(SignRecord signRecord);

    /**
     * 通过用户id取得连续打卡送礼物信息
     * @param userId
     * @return
     */
    public SignRecord getSignRecordInfoByUserId(String userId);

    /**
     * 更新连续打卡获得礼物过期时间超过3天还未领取的礼物状态为无效
     * @return
     */
    public int updateExpirySignRecord();

    /**
     * 根据经纬度获取附近的熟人
     * @param latitude
     * @param longitude
     * @param start
     * @param size
     * @return
     */
    public List<WxUser> getTenUserInfoByRelation(String latitude, String longitude, Integer start, Integer size);
}
