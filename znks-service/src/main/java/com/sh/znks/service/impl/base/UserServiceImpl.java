package com.sh.znks.service.impl.base;

import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.base.Constant;
import com.sh.znks.common.base.http.HttpRequestUtils;
import com.sh.znks.common.base.util.DateUtil;
import com.sh.znks.common.base.util.DateUtils;
import com.sh.znks.common.base.util.JsonUtils;
import com.sh.znks.common.base.util.ParamEditUtils;
import com.sh.znks.common.base.util.RedisKeyConstant;
import com.sh.znks.common.base.util.RedisUtils;
import com.sh.znks.common.base.util.RegisterUtils;
import com.sh.znks.common.emoji.Emoji;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.dao.BattleDao;
import com.sh.znks.dao.UserDao;
import com.sh.znks.domain.aq.SignRecord;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.domain.user.WxUser;
import com.sh.znks.service.base.UserService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuminggu on 2018/5/9.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${zn.appid}")
    private String appid;
    @Value("${zn.appsecret}")
    private String secret;
    @Value("${zn.wx.url}")
    private String wxurl;

    @Autowired
    private UserDao userDao;
    @Autowired
    private BattleDao battleDao;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ResultResponse getxAuthorizationGenLoginInfo(String authorizationCode) {
        Map<String, Object> resultMap = new HashMap<>();
        // 取得请求微信登录凭证校验接口的url
        String wxUrl = ParamEditUtils.getWxUrl(wxurl, appid, secret, authorizationCode);
        try {
            Map resMap = HttpRequestUtils.httpGet(wxUrl);
//            Integer errcode = (Integer) resMap.get("errcode");
//            String errmsg = (String) resMap.get("errmsg");

            String openid = (String) resMap.get("openid");
            String sessionKey = (String) resMap.get("session_key");
            String unionId = (String) resMap.get("unionid");
            //测试数据
//            String openid = "1234567890";
//            String sessionKey = "key1234567890";
            if (StringUtils.isBlank(openid) || StringUtils.isBlank(sessionKey)) {
                log.error("L461_getxAuthorizationGenLoginInfo res is {}", JsonUtils.toJson(resMap));
                return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
            }

            boolean isRegistered = false;
            WxUser wxUser = new WxUser();
            if (StringUtils.isNotBlank(unionId)) {
                //查询是否已注册用户信息
                wxUser = userDao.getWxUserByUnionid(unionId);
                if (StringUtils.isNotBlank(wxUser.getNickName())) {
                    isRegistered = true;
                }
            }

            wxUser.setOpenid(openid);
            wxUser.setSessionKey(sessionKey);
            //将用户信息设置到全局变量中去
            AuthorHolder.setWxAuthor(wxUser);

            //取得授权登录token
            String token = ParamEditUtils.getToken(openid, sessionKey);

            resultMap.put("token", token);
            resultMap.put("isRegistered", isRegistered);
            return new ResultResponse(ResultCodeEnum.ZN_OK, resultMap);
        } catch (Exception e) {
            log.error("L485_getxAuthorizationGenLoginInfoExp e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse registerWxUser(WxUser user) {
        try {
            //查询用户是否已经注册(根据UnionId)
            WxUser wxUser = userDao.getWxUserByUnionid(user.getUnionId());
            //用户已注册
            if (wxUser != null && StringUtils.isNotBlank(wxUser.getNickName())) {
                //将用户信息设置到全局变量中去
                this.setAuthorHolderData(wxUser);
                return new ResultResponse(ResultCodeEnum.ZN_USER_REGISTERED);
            }

            //转换微信昵称带emoji表情
            String nickNameEmoji = Emoji.emojiConverterToAlias(user.getNickName());
            user.setNickName(nickNameEmoji);
            int res = userDao.insertWxUser(user);
            if (res <= Constant.ZERO)
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
            //将用户信息设置到全局变量中去
            this.setAuthorHolderData(user);
            return new ResultResponse(ResultCodeEnum.ZN_OK);
        } catch (Exception e) {
            log.error("L505_register e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    private void setAuthorHolderData (WxUser user) {
        //将用户信息设置到全局变量中去
        user.setOpenid(AuthorHolder.getWxAuthor().getOpenid());
        user.setSessionKey(AuthorHolder.getWxAuthor().getSessionKey());
        AuthorHolder.setWxAuthor(user);
    }

    @Override
    public ResultResponse wxLogoutGen() {
        try {
            //将用户信息置为null
            AuthorHolder.setWxAuthor(null);
            return new ResultResponse(ResultCodeEnum.ZN_OK);
        } catch (Exception e) {
            log.error("L514_wxLogoutGen e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse signIn(String unionId) {
        //当天日期
        String nowDateString = DateUtil.getNowDateString();
        //前一天日期
        Date yesterday = DateUtil.getDateBefore(new Date(), 1);
        String yesterdayStr = DateUtil.getDateString(yesterday);
        try {
            //查询当天是否已经签到
            List<Date> res = userDao.getSignInfo(unionId, nowDateString);
            if (CollectionUtils.isEmpty(res)) {
                List<Date> yesterdayRes = userDao.getSignInfo(unionId, yesterdayStr);
                if (CollectionUtils.isEmpty(yesterdayRes)) {
                    //如果昨天的数据不存在，则删除用户所有的签到数据
                    userDao.deleteSignInfoByUserId(unionId);
                }
                //最后插入今天的数据
                userDao.insertSignInfo(unionId);
            }

            //返回所有天数的日期
            List<Date> resAll = userDao.getSignInfo(unionId, null);
            //查询满足连续签到3天的送礼物3，连续7天的送礼物2，连续10天以上的送礼物1
            if (CollectionUtils.isNotEmpty(resAll) && resAll.size() >= Constant.THREE) {
                //取得要赠送的礼物
                List<Long> giftIds = new ArrayList<>();
                giftIds.add(battleDao.getGiftInfoByLevel(Constant.ONE));
                giftIds.add(battleDao.getGiftInfoByLevel(Constant.TWO));
                giftIds.add(battleDao.getGiftInfoByLevel(Constant.THREE));

                //设置礼物
                Long giftId;
                switch (resAll.size()) {
                    case 3:
                        giftId = giftIds.get(0);
                        break;
                    case 7:
                        giftId = giftIds.get(1);
                        break;
                    case 10:
                        giftId = giftIds.get(2);
                        break;
                    default:
                        giftId = null;
                }
                //查询是否存在记录
                SignRecord sr = userDao.getSignRecordInfoByUserId(unionId);
                if (sr == null) {
                    //插入
                    SignRecord srInsert = new SignRecord();
                    srInsert.setUserId(unionId);
                    srInsert.setSignCount(resAll.size());
                    srInsert.setGiftId(giftId);
                    srInsert.setStatus(Constant.ZERO);
                    srInsert.setEffectiveTime(DateUtils.getOffSetDateTime(Constant.TEN, new Date()));
                    userDao.insertSignRecord(srInsert);
                } else {
                    //更新
                    sr.setSignCount(resAll.size());
                    sr.setGiftId(giftId);
                    sr.setStatus(Constant.ZERO);
                    sr.setEffectiveTime(DateUtils.getOffSetDateTime(Constant.TEN, new Date()));
                    userDao.updateSignRecord(sr);
                }
            }

            return new ResultResponse(ResultCodeEnum.ZN_OK, resAll);
        } catch (Exception e) {
            log.error("L173_signIn e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse feedBack(String userId, String content) {
        try {
            //转换emoji表情
            String contentEmoji = Emoji.emojiConverterToAlias(content);
            int res = userDao.insertFeedBackInfo(userId, contentEmoji);
            if (res <= 0) {
                log.error("L178_feedBack insert error:", res);
                return ResultResponse.failed();
            }
            return new ResultResponse(ResultCodeEnum.ZN_OK);
        } catch (Exception e) {
            log.error("L187_feedBack e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

}
