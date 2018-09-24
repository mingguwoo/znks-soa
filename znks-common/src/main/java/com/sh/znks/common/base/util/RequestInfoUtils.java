package com.sh.znks.common.base.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuminggu on 2018/9/14.
 * ��ȡ�ͻ��������ip�� �˿�
 */
public class RequestInfoUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInfoUtils.class);

    /**
     * ��ȡ�ͻ��������ip
     * proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
     * Nginx���������,�������ͨ��,�ָ�������x-forwarded-for HEADERͷ��
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ip = "";
        try {
            ip = request.getHeader("j-forwarded-for");
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if(ip.equals("127.0.0.1")){
                    //��������ȡ�������õ�IP
                    InetAddress inet=null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        LOGGER.error("��ȡ�ͻ���ip����", e);
                    }
                    if (inet != null)
                        ip= inet.getHostAddress();
                }
            }
            // ����ͨ�����������������һ��IPΪ�ͻ�����ʵIP,���IP����','�ָ�
            if(ip != null && ip.length() > 15){
                if(ip.indexOf(",")>0){
                    ip = ip.substring(0,ip.indexOf(","));
                }
            }
        } catch (Exception e) {
            LOGGER.error("��ȡԶ��IP�쳣", e);
        }
        return ip;
    }

    /**
     * ��ȡ�ͻ�������Ķ˿�
     * @param request
     * @return
     */
    public static String getRemotePort(HttpServletRequest request) {
        String port = "";
        try {
            port = request.getHeader("X-Jdlb-Client-Port");
            if (StringUtils.isBlank(port))
                port = "80";
        } catch (Exception e) {
            LOGGER.error("��ȡԶ�̶˿��쳣", e);
        }
        return port;
    }
}
