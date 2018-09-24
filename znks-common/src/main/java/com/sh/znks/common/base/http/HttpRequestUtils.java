package com.sh.znks.common.base.http;

import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wuminggu on 2018/6/15.
 */
public class HttpRequestUtils {
    private static Log logger = LogFactory.getLog(HttpRequestUtils.class);    //��־��¼

    /**
     * httpPost
     * @param url  ·��
     * @param jsonParam ����
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam){
        return httpPost(url, jsonParam, false);
    }

    /**
     * post����
     * @param url         url��ַ
     * @param jsonParam     ����
     * @param noNeedResponse    ����Ҫ���ؽ��
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam, boolean noNeedResponse){
        //post���󷵻ؽ��
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        // method.addHeader("Content-type","application/json; charset=utf-8");
        //method.setHeader("Accept", "application/json");
        try {
            if (null != jsonParam) {
                //���������������
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                //entity.setContentEncoding("utf-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**�����ͳɹ������õ���Ӧ**/
            Integer resultCode=result.getStatusLine().getStatusCode();
            if (resultCode == 200||resultCode == 201) {
                String str = "";
                try {
                    /**��ȡ���������ع�����json�ַ�������**/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /**��json�ַ���ת����json����**/
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                    logger.error("post�����ύʧ��:" + url, e);
                }
            }else{
                logger.error("post�����ύʧ��:"+url+" data:"+jsonParam.toString()+"statusCode:"+result.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            logger.error("post�����ύʧ��:" + url, e);
        }finally {
            method.releaseConnection();
        }
        return jsonResult;
    }

    /**
     * ����put����
     * @param url    ·��
     * @param  jsonParam �������,json��ʽ
     * @return
     */
    public static JSONObject httpPut(String url, JSONObject jsonParam){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
        JSONObject jsonResult = null;
        HttpPut putMethod = new HttpPut(url);
        try {
            if (null != jsonParam) {
                //���������������
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                //entity.setContentEncoding("utf-8");
                entity.setContentType("application/json");
                putMethod.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(putMethod);
            url = URLDecoder.decode(url, "UTF-8");
            /**�����ͳɹ������õ���Ӧ**/
            Integer resultCode=result.getStatusLine().getStatusCode();
            if (resultCode == 200||resultCode == 201) {
                String str = "";
                try {
                    /**��ȡ���������ع�����json�ַ�������**/
                    str = EntityUtils.toString(result.getEntity());
                    /**��json�ַ���ת����json����**/
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                    //System.out.println(e);
                    logger.error("put�����ύʧ��:" + url, e);
                }
            }
        } catch (IOException e) {
            //System.out.println(e);
            logger.error("put�����ύʧ��:" + url, e);
        }finally {
            putMethod.releaseConnection();
        }
        return jsonResult;
    }

    public static JSONObject httpDelete(String url){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
        JSONObject jsonResult = null;
        HttpDelete deleteMethod = new HttpDelete(url);
        try {
            HttpResponse result = httpClient.execute(deleteMethod);
            url = URLDecoder.decode(url, "UTF-8");
            /**�����ͳɹ������õ���Ӧ**/
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**��ȡ���������ع�����json�ַ�������**/
                String strResult = EntityUtils.toString(result.getEntity());
                /**��json�ַ���ת����json����**/
                jsonResult = JSONObject.fromObject(strResult);
            } else {
                logger.error("delete�����ύʧ��:" + url);
            }
        } catch (IOException e) {
            logger.error("delete�����ύʧ��:" + url, e);
        }finally {
            deleteMethod.releaseConnection();
        }
        return jsonResult;
    }

    /**
     * ����get����
     * @param url    ·��
     * @return
     */
    public static JSONObject httpGet(String url){
        //get���󷵻ؽ��
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //����get����
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**�����ͳɹ������õ���Ӧ**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**��ȡ���������ع�����json�ַ�������**/
                String strResult = EntityUtils.toString(response.getEntity());
                /**��json�ַ���ת����json����**/
                jsonResult = JSONObject.fromObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.error("get�����ύʧ��:" + url);
            }
        } catch (IOException e) {
            logger.error("get�����ύʧ��:" + url, e);
        }
        return jsonResult;
    }

    /**
     * ����get����
     * @param url    ·��
     * @return
     */
    public static JSONObject httpGetJSONP(String url){
        //get���󷵻ؽ��
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            //����get����
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**�����ͳɹ������õ���Ӧ**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**��ȡ���������ع�����json�ַ�������**/
                String strResult = JSONP2JSON(EntityUtils.toString(response.getEntity()));
                /**��json�ַ���ת����json����**/
                jsonResult = JSONObject.fromObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.error("get�����ύʧ��:" + url);
            }
        } catch (IOException e) {
            logger.error("get�����ύʧ��:" + url, e);
        }
        return jsonResult;
    }

    private static String JSONP2JSON(String jsonp) {
        int startIndex = jsonp.indexOf("(");
        int endIndex = jsonp.lastIndexOf(")");
        return jsonp.substring(startIndex+1, endIndex);
    }

    public static JSONObject httpPost2(String url, JSONObject jsonParam,
                                       boolean noNeedResponse) {
        // post���󷵻ؽ��
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost httppost = new HttpPost(url);

        try {
            if (null != jsonParam) {

                List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();

                Iterator it = jsonParam.keys();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    nvps.add(new BasicNameValuePair(key, jsonParam
                            .getString(key)));
                }

                httppost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }
            HttpResponse result = httpClient.execute(httppost);
            url = URLDecoder.decode(url, "UTF-8");
            /** �����ͳɹ������õ���Ӧ **/
            Integer resultCode = result.getStatusLine().getStatusCode();
            if (resultCode == 200 || resultCode == 201) {
                String str = "";
                try {
                    /** ��ȡ���������ع�����json�ַ������� **/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /** ��json�ַ���ת����json���� **/
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                    // System.out.println(e);
                    logger.error("post�����ύʧ��:" + url, e);
                }
            } else {
                logger.error("post�����ύʧ��:" + url + " data:"
                        + jsonParam.toString() + "statusCode:"
                        + result.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            // System.out.println(e);
            logger.error("post�����ύʧ��:" + url, e);
        } finally {
            httppost.releaseConnection();
        }
        return jsonResult;
    }


}
