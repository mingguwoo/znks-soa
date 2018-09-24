package com.sh.znks.web.controller.business;

import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.user.WxUser;
import com.sh.znks.service.base.UserService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuminggu on 2018/6/14.
 */
@Controller
@RequestMapping("/extra")
public class ExtraController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse signIn(String unionId) {
        //校验必填项
        if (StringUtils.isBlank(unionId)) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }
        //校验用户是否登录用户
        WxUser user = AuthorHolder.getWxAuthor();
        if (!user.getUnionId().equals(unionId)) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return userService.signIn(unionId);
    }
}
