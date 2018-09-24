package com.sh.znks.common.base.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpClientUtil {
    private static final String ENCODE = "UTF-8";
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String sendGetHttpRequest(String url, int timeOut) {
        CloseableHttpClient client = null;
        try {
            client = HttpClientBuilder.create().build();

            HttpGet httpGet = new HttpGet(url);

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();// 设置请求和传输超时时间
            httpGet.setConfig(requestConfig);

            HttpResponse response = client.execute(httpGet);

            if (null == response.getStatusLine() || 200 != response.getStatusLine().getStatusCode())
                return null;

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("invoke get interface error", e);
            return null;
        } finally {
            if (null != client) {
                try {
                    client.close();
                } catch (IOException e) {
                    LOGGER.error("close error", e);
                }
            }
        }
    }

    public static String sendPostHttpRequest(String url, List<NameValuePair> params, int timeOut) {
        CloseableHttpClient client = null;
        try {
            client = HttpClientBuilder.create().build();

            HttpPost httpPost = new HttpPost(url);

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();// 设置请求和传输超时时间
            httpPost.setConfig(requestConfig);

            if (null != params && !params.isEmpty()) {
                EntityBuilder create = EntityBuilder.create();
                HttpEntity build = create.setParameters(params).build();
                httpPost.setEntity(build);
            }

            HttpResponse response = client.execute(httpPost);

            if (null == response.getStatusLine() || 200 != response.getStatusLine().getStatusCode())
                return null;

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("invoke post interface error", e);
            return null;
        } finally {
            if (null != client) {
                try {
                    client.close();
                } catch (IOException e) {
                    LOGGER.error("close error", e);
                }
            }
        }
    }

    public static String sendPostHttpRequestWithRaw(String url, String stringJson, int timeOut) {
        CloseableHttpClient client = null;
        try {
            client = HttpClientBuilder.create().build();

            HttpPost httpPost = new HttpPost(url);

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();// 设置请求和传输超时时间
            httpPost.setConfig(requestConfig);
            //设置header
            httpPost.setHeader("Content-type", "application/json");
            //组织请求参数
            StringEntity stringEntity = new StringEntity(stringJson, ENCODE);
            httpPost.setEntity(stringEntity);

            HttpResponse response = client.execute(httpPost);

            if (null == response.getStatusLine() || 200 != response.getStatusLine().getStatusCode())
                return null;

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("invoke post interface error", e);
            return null;
        } finally {
            if (null != client) {
                try {
                    client.close();
                } catch (IOException e) {
                    LOGGER.error("close error", e);
                }
            }
        }
    }

//    public static void main(String[] args) {
//        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
//        nvps.add(new BasicNameValuePair("channelId", "3"));
//
//        Map<String, String> cookies = new HashMap<String, String>();
//        cookies.put("thor", "21B1473DFFA3E999B61FFF1C800679FD48D00EC830657FB7963F6F562B284C82975EA06267B3304B0EDAEE1B9BCC79AB79DE441099D3100A05335FAA529FD74C8AFCCFCC099F6EED31A422BB8D1B690F69F0221A673418B977CCE0B2BD1089574CDA6294670112105C05F83ECF6FCBA891FB7AF88C5C27AD1AF46780EFA5FD2242D670460B696DC14816B8C925213CEAEF5025FA7ECFD654ADCBB732D418CFDB");
////        cookies.put("", "");
////        cookies.put("", "");
//        String str = sendPostHttpRequestWithCookie("http://dr.jd.com/gw/getDrArticleStatData.do", nvps, cookies, 1000);
//
//        if ( str != null && ( !str.startsWith("{") || !str.endsWith("}")) ) {
//            int start = str.indexOf("{");
//            int end = str.lastIndexOf("}");
//            if ( start < end ) {
//                str = str.substring(start, end + 1);
//            }
//        }
//
//        System.out.println(str);
//    }

    /**
     * warning: url必须是http:// 这样带有协议头的   不然new URL(url)  这边会出错
     * @param url
     * @param params
     * @param cookies
     * @param timeOut
     * @param connectionManager
     * @return
     */
    public static String sendPostHttpRequestWithCookie(String url, List<NameValuePair> params, Map<String, String> cookies, int timeOut, HttpClientConnectionManager connectionManager) {
        CloseableHttpClient client = null;
        try {
            CookieStore basicCookieStore = new BasicCookieStore();
            HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(basicCookieStore);
            if ( connectionManager != null ) {
                builder = builder.setConnectionManager(connectionManager);
            }
            client = builder.build();

            HttpPost httpPost = new HttpPost(url);

            if (null != cookies && !cookies.isEmpty()) {
                BasicClientCookie cookie = null;
                for (Entry<String, String> entry : cookies.entrySet()) {
                    cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
                    cookie.setDomain(new URL(url).getHost());
                    cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "true");
                    basicCookieStore.addCookie(cookie);
                }
            }

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeOut).setConnectTimeout(timeOut).build();// 设置请求和传输超时时间
            httpPost.setConfig(requestConfig);

            if (null != params && !params.isEmpty()) {
                EntityBuilder create = EntityBuilder.create();
                HttpEntity build = create.setParameters(params).build();
                httpPost.setEntity(build);
            }

            HttpResponse response = client.execute(httpPost);

            if (null == response.getStatusLine() || 200 != response.getStatusLine().getStatusCode())
                return null;

            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("invoke post interface error", e);
            return null;
        } finally {
            if (null != client) {
                try {
                    client.close();
                } catch (IOException e) {
                    LOGGER.error("close error", e);
                }
            }
        }
    }
}
