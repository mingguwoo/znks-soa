package com.sh.znks.web.controller.base;

import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.service.base.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuminggu on 2018/5/8.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private final static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/general/wxAuthorizationGenLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse wxAuthorizationGenLogin(String authorizationCode) {
        //校验必填项
        if (StringUtils.isBlank(authorizationCode)) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return userService.getxAuthorizationGenLoginInfo(authorizationCode);
    }
}
