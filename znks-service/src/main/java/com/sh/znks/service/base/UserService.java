package com.sh.znks.service.base;

import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.register.User;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.domain.user.WxUser;

/**
 * Created by wuminggu on 2018/5/9.
 */
public interface UserService {
    /**
     * 获取登录态token
     * @param authorizationCode
     * @return
     */
    ResultResponse getxAuthorizationGenLoginInfo(String authorizationCode);

    /**
     * 微信授权后获取用户信息注册到DB里
     * @param user
     * @return
     */
    ResultResponse registerWxUser(WxUser user);

    /**
     * 退出登录(微信)
     * @return
     */
    ResultResponse wxLogoutGen();

    /**
     * 用户签到
     * @param unionId
     * @return
     */
    ResultResponse signIn(String unionId);
}
