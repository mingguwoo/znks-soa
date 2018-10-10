package com.sh.znks.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wuminggu on 2018/5/9.
 */
public class EncoidingFilter implements Filter {

    private String encoding;
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    //过滤方法  是否往下执行
    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)arg0;
        HttpServletResponse response=(HttpServletResponse)arg1;

        request.setCharacterEncoding(encoding);//只对post有效
        response.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("content-type","text/html;charset=UTF-8");
        //过滤通行证
        chain.doFilter(request, response);
    }

    //根据web.xml文件的配置进行初始化
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        this.encoding = arg0.getInitParameter("encoding");

    }
}
