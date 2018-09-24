package com.sh.znks.common.base.util;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuminggu on 2018/6/15.
 */
public class JsonUtils {
    private final static Logger LOG = LoggerFactory.getLogger(JsonUtils.class);
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper(); // �°桢simleFactory�������ƣ�������

    private JsonUtils() {
    }

    static {
        // �����������������
        OBJECT_MAPPER.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        // ��������ʱ����JSON�ַ����д��ڶ�Java����ʵ��û�е�����
        OBJECT_MAPPER.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OBJECT_MAPPER.setDateFormat(df);
    }

    /**
     * ���JSON�ַ���ΪNull��"null"�ַ���,����Null. ���JSON�ַ���Ϊ"[]",���ؿռ���.
     *
     * @param jsonString
     * @param clazz
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return (T) OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (IOException e) {
            LOG.error("parse json string error:" + jsonString, e);
        }
        return null;
    }

    /**
     * ��ȡ������List/Map,�Ҳ���List<String>ʱ.
     *
     * @param jsonString
     * @param typeReference
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String jsonString, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return (T) OBJECT_MAPPER.readValue(jsonString, typeReference);
        } catch (IOException e) {
            LOG.error("parse json string error:" + jsonString, e);
        }
        return null;
    }

    /**
     * ������ת����json�ַ���,���ת��ʧ���򷵻�null
     *
     * @author zhaoyunxiao
     * @param o
     *            ��Ҫת��Ϊjson�Ķ���
     * @return String ת�����json�ַ���
     *
     *
     * */
    public static String write2JsonStr(Object o) {
        String jsonStr = "";
        try {
            jsonStr = OBJECT_MAPPER.writeValueAsString(o);
        } catch (IOException e) {
            LOG.error("write2JsonStr() exception: " + e.getMessage());
        }
        return jsonStr;
    }

    /**
     * �������ΪNull,����"null". �������Ϊ�ռ���,����"[]".
     */
    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            LOG.warn("write to json string error:" + object, e);
        }
        return null;
    }

    /**
     * ����תByte����
     *
     * @param obj
     * @return
     */
    public static byte[] writeToByteArr(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(obj);
        } catch (IOException e) {
            LOG.warn("write to byte error:" + obj.getClass().getName(), e);
        }
        return null;
    }

    public static Object readfromByteArr(byte[] byteArr, JavaType javaType) {
        if (null == byteArr || byteArr.length == 0) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(byteArr, javaType);
        } catch (Exception e) {
            LOG.warn("byte to Object error:", e);
        }
        return null;
    }

    public static Object readfromByteArr(byte[] byteArr, Class<?> clazz) {
        if (null == byteArr || byteArr.length == 0) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(byteArr, clazz);
        } catch (IOException e) {
            LOG.error("byte to Object error:" + clazz.getName(), e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> json2Map(String json) {
        try {
            if (StringUtils.isBlank(json)) {
                return new HashMap<String, Object>();
            }
            return OBJECT_MAPPER.readValue(json, Map.class);
        } catch (Exception e) {
            LOG.error("json2Map(), �����json����: {} exception {}", json, e.getMessage());
        }
        return new HashMap<String, Object>();
    }

    /**
     * ȡ��Mapper����һ�������û�ʹ���������л�API.
     */
    public static ObjectMapper getMapper() {
        return OBJECT_MAPPER;
    }


}
