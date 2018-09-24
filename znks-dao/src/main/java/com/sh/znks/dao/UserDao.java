package com.sh.znks.dao;

import com.sh.znks.domain.user.WxUser;

import java.util.Date;
import java.util.List;

/**
 * Created by wuminggu on 2018/5/9.
 */
public interface UserDao {
    /**
     * ȡ��΢���û���Ϣ
     * @param unionId
     * @return
     */
    WxUser getWxUserByUnionid(String unionId);

    /**
     * ����΢���û���Ϣ
     * @param wxUser
     * @return
     */
    int insertWxUser(WxUser wxUser);

    /**
     * ȡ���û�ǩ����Ϣ
     * @param userId
     * @param created
     * @return
     */
    List<Date> getSignInfo(String userId, String created);

    /**
     * ����ǩ����Ϣ
     * @param userId
     * @return
     */
    public int insertSignInfo(String userId);

    /**
     * ɾ��ǩ����Ϣ
     * @param userId
     * @return
     */
    public int deleteSignInfoByUserId(String userId);
}
