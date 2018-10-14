package com.sh.znks.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.base.util.JsonUtils;
import com.sh.znks.common.base.util.ParamEditUtils;
import com.sh.znks.common.base.util.UrlUtils;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.user.WxUser;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wuminggu on 2018/9/5.
 */
public class WxAccessInterceptor extends HandlerInterceptorAdapter {
    private final static Logger log = LoggerFactory.getLogger(WxAccessInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //预处理
        boolean res = false;
//        boolean res = true;
        String url = request.getRequestURL().toString();
        log.error("url is {}", url);
        String path = request.getServletPath();
        if (StringUtils.isNotBlank(path) && path.startsWith("/")) {
            //过滤掉浏览器自动发送的favicon.ico请求
            if (UrlUtils.FAVICON.equals(path)) {
                res = true;
            }

            //参数校验
            WxUser user = AuthorHolder.getWxAuthor();
            if (user == null) {
                this.responseInfoSet(ResultCodeEnum.ZN_NO_LOGIN, response);
                return res;
            }

            //校验登录态是否正确
            String token = request.getParameter("token");
            if (ParamEditUtils.checkTokenAvailability(token)) {
                log.error("L35 token is {}, wxUser is{}", token, JsonUtils.toJson(AuthorHolder.getWxAuthor()));
                res = true;
            }
        }

        if (!res) {
            this.responseInfoSet(ResultCodeEnum.ZN_TOKEN_ERR, response);
        }

        return res;
    }

    private void responseInfoSet(ResultCodeEnum en, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject result = new JSONObject();
        result.put("code", en.getCode());
        result.put("msg", en.getMsg());
        PrintWriter out = response.getWriter();
        out.append(result.toString());
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
