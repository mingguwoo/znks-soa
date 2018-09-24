package com.sh.znks.service.impl.base;

import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.base.Constant;
import com.sh.znks.common.base.http.HttpRequestUtils;
import com.sh.znks.common.base.util.DateUtil;
import com.sh.znks.common.base.util.JsonUtils;
import com.sh.znks.common.base.util.ParamEditUtils;
import com.sh.znks.common.base.util.RedisKeyConstant;
import com.sh.znks.common.base.util.RedisUtils;
import com.sh.znks.common.base.util.RegisterUtils;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.dao.UserDao;
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
        } catch (Exception e) {
            log.error("L485_getxAuthorizationGenLoginInfoExp e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK, resultMap);
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

            int res = userDao.insertWxUser(user);
            if (res <= Constant.ZERO)
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
            //将用户信息设置到全局变量中去
            this.setAuthorHolderData(user);
        } catch (Exception e) {
            log.error("L505_register e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK);

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
        } catch (Exception e) {
            log.error("L514_wxLogoutGen e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
        return new ResultResponse(ResultCodeEnum.ZN_OK);
    }

    @Override
    public ResultResponse signIn(String unionId) {
        //当天日期
        String nowDateString = DateUtil.getNowDateString();
        //前一天日期
        Date yesterday = DateUtil.getDateBefore(new Date(), 1);
        String yesterdayStr = DateUtil.getDateString(yesterday);

        //查询当天是否已经签到
        List<Date> res = userDao.getSignInfo(unionId, nowDateString);
        if (CollectionUtils.isEmpty(res)) {
            //判断昨天的数据是否存在
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
        return new ResultResponse(ResultCodeEnum.ZN_OK, resAll);
    }

}
