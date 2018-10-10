package com.sh.znks.common.base.util;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by wuminggu on 2018/6/19.
 */
public class DataUtils {

    /**
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * string --> list
     * @param strs
     * @return
     */
    public static List<Long> string2List(String strs) {
        if (StringUtils.isNotBlank(strs)) {
            String[] str = strs.split(",");
            List<Long> res = new ArrayList<>();
            for (String s : str) {
                res.add(Long.valueOf(s));
            }
            return res;
        } else {
            return null;
        }
    }


}
