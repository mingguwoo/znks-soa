package com.sh.znks.common.base.util;

import java.util.regex.Pattern;

/**
 * Created by wuminggu on 2018/6/19.
 */
public class DataUtils {

    /**
     * �ж��Ƿ�Ϊ����
     * @param str ������ַ���
     * @return ����������true,���򷵻�false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
