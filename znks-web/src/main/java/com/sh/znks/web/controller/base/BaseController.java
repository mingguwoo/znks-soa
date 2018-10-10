package com.sh.znks.web.controller.base;

import com.sh.znks.common.base.util.RegisterUtils;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.user.WxUser;
import com.sh.znks.service.base.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wuminggu on 2018/9/20.
 */
@Controller
@RequestMapping("/base")
public class BaseController {
    private final static Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login/wxAuthorizationGenLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse wxAuthorizationGenLogin(String authorizationCode) {
        //校验必填项
        if (StringUtils.isBlank(authorizationCode)) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return userService.getxAuthorizationGenLoginInfo(authorizationCode);
    }

    @RequestMapping(value = "/register/wxAuthorizationGenRegiste", method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse wxAuthorizationGenRegiste(HttpServletRequest request) {
        //编辑专家注册信息
        WxUser user = RegisterUtils.InputWxParamSet(request);

        //校验必填项
        if (StringUtils.isBlank(user.getUnionId())) {
            return new ResultResponse(ResultCodeEnum.ZN_UNIONID_IS_NULL);
        }

        //注册
        return userService.registerWxUser(user);
    }

    @RequestMapping(value = "/logout/wxLogoutGen", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse wxLogoutGen() {
        return userService.wxLogoutGen();
    }
}
