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


    //���˷���  �Ƿ�����ִ��
    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)arg0;
        HttpServletResponse response=(HttpServletResponse)arg1;

        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);

        //����ͨ��֤
        chain.doFilter(request, response);
    }

    //����web.xml�ļ������ý��г�ʼ��
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        this.encoding = arg0.getInitParameter("encoding");

    }
}
