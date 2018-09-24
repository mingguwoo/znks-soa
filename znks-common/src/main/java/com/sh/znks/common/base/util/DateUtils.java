package com.sh.znks.common.base.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by wuminggu on 2018/6/15.
 */
public class DateUtils {
    /**
     * dateתString
     * format��ת����ʽ ��"yyyy-MM-dd"
     * @param date
     * @param format
     * @return
     */
    public static String dateFormatYYYYMMDD(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * stringתdate����
     * format��strDate�ĸ�ʽ;strDate�� 2018-02-26, format����"yyyy-MM-dd"
     * @param strDate
     * @param format
     * @return
     */
    public static Date strToDate(String strDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * ��ȡĳ������֮ǰ����֮���ĳ������
     * @param nowdate
     * @param delay �������
     * @param formatter
     * @return
     */
    public static String getNextDay(String nowdate, String delay, String formatter) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatter);
            Date d = strToDate(nowdate, formatter);
            long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            return format.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     *��ȡdate����offsetʱ��
     * @param offset
     * @param date
     * @return Date
     * */
    public static Date getOffSetDateTime(Integer offset,Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, offset);
        return calendar.getTime();
    }

    /**
     *��ȡdate����offsetʱ��
     * @param offset
     * @param date
     * @return String yyyy-MM-dd
     * */
    public static String getOffSetStrTime(Integer offset,Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, offset);
        String offsetDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        return offsetDate;
    }

}
