package com.sh.znks.common.base.util;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    private final static Logger logger = LogManager.getLogger(DateUtil.class);
    public final static String FULLHOURTIME = " 23:59:59";
    private final static String dateformat = "yyyy-MM-dd";
    private final static String dateNOLineformat = "yyyyMMdd";
    private final static String dateformatYyyyMMddHH = "yyyyMMddHH";
    private final static String dateHourMinuteformat = "yyyy-MM-dd HH:mm";
    private final static String dateHourMinuteSecondformat = "yyyy-MM-dd HH:mm:ss";
    private final static String dateHourMinuteSecondNOLineformat = "yyyyMMddHHmmss";
    private final static String dateHourMinuteNOLineformat = "yyyyMMddHHmm";
    public final static String HH_MM = "HH:mm";
    private static final Map<String, ThreadLocal<SimpleDateFormat>> pool = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
    private static final Object lock = new Object();

    public static SimpleDateFormat getDateFormat(String pattern) {
        ThreadLocal<SimpleDateFormat> tl = pool.get(pattern);
        if (tl == null) {
            synchronized (lock) {
                tl = pool.get(pattern);
                if (tl == null) {
                    final String p = pattern;
                    tl = new ThreadLocal<SimpleDateFormat>() {
                        protected synchronized SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(p);
                        }
                    };
                    pool.put(p, tl);
                }
            }
        }
        return tl.get();
    }

    /**
     * �������ʱ��long����
     *
     * @return
     */
    public static long getNowTimeLong() {
        return System.currentTimeMillis();
    }

    /**
     * ��õ�ǰ����Date��
     *
     * @return
     */
    public static Date getNowDate() {
        Date date = new Date();
        String dateString = getDateFormat(dateformat).format(date);
        try {
            date = getDateFormat(dateformat).parse(dateString);
        } catch (ParseException e) {
            logger.error("ParseException", e);
        }
        return date;
    }

    /**
     * ��õ�ǰ����long����
     *
     * @return
     */
    public static long getNowDateLong() {
        return getNowDate().getTime();
    }

    public static String getTimeStringNoSecond(Date time) {
        return getDateFormat(dateHourMinuteformat).format(time);
    }

    /**
     * ��õ�ǰ����long����
     *
     * @return
     */
    public static long getDateLong(String dateString) {
        Date date = null;
        try {
            date = getDateFormat(dateformat).parse(dateString);
        } catch (ParseException e) {
            logger.error("ParseException", e);
        }
        return date.getTime();
    }

    /**
     * ��ø���ʱ��long����
     *
     * @return
     */
    public static long getTimeLong(String time) {
        Date date = null;
        try {
            date = getDateFormat(dateHourMinuteSecondformat).parse(time);
        } catch (ParseException e) {
            logger.error("ParseException", e);
        }
        return date.getTime();
    }


    /**
     * ���date and week
     *
     * @param beginTime
     * @return
     */
    public static DateWeekBean getDateWeekBean(Date beginTime) {
        Calendar calBegin = Calendar.getInstance();
        // ʹ�ø����� Date ���ô� Calendar ��ʱ��
        calBegin.setTime(beginTime);
        //��ӵ�һ��
        DateWeekBean bean = new DateWeekBean();
        bean.setDate(DateUtil.getDateString(beginTime));
        bean.setWeek(getWeekDay(calBegin.get(Calendar.DAY_OF_WEEK)));
        bean.setIsPast(ispast(getDateString(beginTime)));
        return bean;
    }

    /**
     * ��ȡ��ǰ���ڣ���������
     *
     * @return
     */
    public static String getNowDateStringNOLine() {
        return getDateFormat(dateNOLineformat).format(new Date());
    }

    /**
     * ��ȡָ�����ڣ���������
     *
     * @return
     */
    public static String getDateStringNOLine(Date date) {
        return getDateFormat(dateNOLineformat).format(date);
    }

    /**
     * getter of datestringyyyymmddhh
     *
     * @param date
     **/
    public static String getDateStringYyyyMMddHH(Date date) {
        return getDateFormat(dateformatYyyyMMddHH).format(date);
    }

    /**
     * ��ȡ��ǰʱ��
     *
     * @return
     */
    public static String getNowString() {
        return getDateFormat(dateHourMinuteSecondformat).format(new Date());
    }

    /**
     * ��ȡ��ǰʱ�䣬��������
     *
     * @return
     */
    public static String getNowStringNOLine() {
        return getDateFormat(dateHourMinuteSecondNOLineformat).format(new Date());
    }

    /**
     * ��ȡdate����������
     *
     * @return
     */
    public static String getNowStringNOLine(Date date) {
        return getDateFormat(dateHourMinuteSecondNOLineformat).format(date);
    }

    /**
     * ��ȡdate(���ȵ�����)����������
     *
     * @return
     */
    public static String getHourMinuteTimeStringNOLine(Date date) {
        return getDateFormat(dateHourMinuteNOLineformat).format(date);
    }

    /**
     * ��ȡ��ǰ����
     *
     * @return
     */
    public static String getNowDateString() {
        return getDateFormat(dateformat).format(new Date());
    }

    /**
     * ��ȡʱ���ַ���
     *
     * @param time
     * @return
     */
    public static String getTimeString(Date time) {
        return getDateFormat(dateHourMinuteSecondformat).format(time);
    }

    /**
     * ��ȡ�����ַ���
     *
     * @param time
     * @return
     */
    public static String getDateString(Date time) {
        return getDateFormat(dateformat).format(time);
    }

    /**
     * ��ȡ���ڸ�ʽyyyy-MM-dd
     *
     * @param time
     * @return
     */
    public static Date getDateDate(String time) {
        Date d = null;
        try {
            d = getDateFormat(dateformat).parse(time);
        } catch (ParseException e) {
            logger.error("ʱ���ʽת������", e);
        }
        return d;
    }

    /**
     * ��ȡ���ڸ�ʽ yyyy-MM-dd hh:mm:ss
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date getTimeDate(String time) {
        Date d = null;
        try {
            d = getDateFormat(dateHourMinuteSecondformat).parse(time);
        } catch (ParseException e) {
            logger.error("��ʽת������", e);
        }
        return d;
    }


    /**
     * ��ȡ���ڸ�ʽ yyyyMMddHHmmss
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date getTimeDate2(String time) {
        Date d = null;
        try {
            d = getDateFormat(dateHourMinuteSecondNOLineformat).parse(time);
        } catch (ParseException e) {
            logger.error("��ʽת������", e);
        }
        return d;
    }

    /**
     * ��ȡ���ڸ�ʽ yyyyMMdd
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date getTimeDate3(String time) {
        Date d = null;
        try {
            d = getDateFormat(dateNOLineformat).parse(time);
        } catch (ParseException e) {
            logger.error("��ʽת������", e);
        }
        return d;
    }

    /**
     * ��ȡ���ڸ�ʽ yyyy-MM-dd hh:mm
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date getTimeDateNoSecond(String time) {
        Date d = null;
        try {
            d = getDateFormat(dateHourMinuteformat).parse(time);
        } catch (ParseException e) {
            logger.error("��ʽת������", e);
        }
        return d;
    }

    /**
     * ��ȡHH:MM��ʽʱ��
     *
     * @param date
     * @return
     */
    public static String getTimeHHMM(Long date) {
        return getDateFormat(HH_MM).format(new Date(date));
    }

    /**
     * ��ȡYYYHHMM��ʽʱ��
     *
     * @param date
     * @return
     */
    public static String getTimeYYYYHHMMNoLine(Long date) {
        return getDateFormat(dateNOLineformat).format(new Date(date));
    }

    /**
     * ��ȡYYYY-HH-MM��ʽʱ��
     *
     * @param date
     * @return
     */
    public static String getTimeYYYYHHMM(Long date) {
        return getDateFormat(dateformat).format(new Date(date));
    }

    /**
     * ��ȡһ����֮ǰ������
     *
     * @param time
     * @return
     */
    public static Date getLastMonthTime(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    /**
     * ��ȡһָ������֮ǰ��ʱ��
     *
     * @param
     * @return
     * @throws ParseException
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day);
        return calendar.getTime();
    }

    /**
     * ��ȡһָ������֮���ʱ��
     *
     * @param
     * @return
     * @throws ParseException
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
        return calendar.getTime();
    }

    /**
     * getter of datedatebefore
     *
     * @param d
     * @param day
     **/
    public static Date getDateDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * getter of yesterdaytime
     **/
    public static Date getYesterdayTime() {
        return new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
    }

    /**
     * ��ȡĳ��ȫʱ�䣬date+" 23:59:59"
     *
     * @param date
     * @return
     */
    public static String getFullDayString(String date) {
        return date + FULLHOURTIME;
    }

    /**
     * ��ѯһ��ʱ������������
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static List<Date> findDates(Date beginTime, Date endTime) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginTime);
        Calendar calBegin = Calendar.getInstance();
        // ʹ�ø����� Date ���ô� Calendar ��ʱ��
        calBegin.setTime(beginTime);
        Calendar calEnd = Calendar.getInstance();
        // ʹ�ø����� Date ���ô� Calendar ��ʱ��
        calEnd.setTime(endTime);
        // ���Դ������Ƿ���ָ������֮��
        while (endTime.after(calBegin.getTime())) {
            // ���������Ĺ���Ϊ�����������ֶ���ӻ��ȥָ����ʱ����
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    /**
     * ��ѯһ��ʱ�����������ں�����
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static List<DateWeekBean> findDatesAndWeeks(Date beginTime, Date endTime) {
        List<DateWeekBean> lDate = new ArrayList<DateWeekBean>();
        Calendar calBegin = Calendar.getInstance();
        // ʹ�ø����� Date ���ô� Calendar ��ʱ��
        calBegin.setTime(beginTime);
        //��ӵ�һ��
        DateWeekBean bean = new DateWeekBean();
        bean.setDate(DateUtil.getDateString(beginTime));
        bean.setWeek(getWeekDay(calBegin.get(Calendar.DAY_OF_WEEK)));
        bean.setIsPast(DateUtil.ispast(DateUtil.getDateString(beginTime)));
        lDate.add(bean);
        Calendar calEnd = Calendar.getInstance();
        // ʹ�ø����� Date ���ô� Calendar ��ʱ��
        calEnd.setTime(endTime);
        // ���Դ������Ƿ���ָ������֮��
        while (endTime.after(calBegin.getTime())) {
            // ���������Ĺ���Ϊ�����������ֶ���ӻ��ȥָ����ʱ����
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            DateWeekBean tempBean = new DateWeekBean();
            tempBean.setDate(DateUtil.getDateString(calBegin.getTime()));
            tempBean.setWeek(getWeekDay(calBegin.get(Calendar.DAY_OF_WEEK)));
            tempBean.setIsPast(DateUtil.ispast(DateUtil.getDateString(calBegin.getTime())));
            lDate.add(tempBean);
        }
        return lDate;
    }

    /**
     * ��ȡ����ʱ���չʾ��ʽ�磺2015��2�µ�2��
     *
     * @return
     */
    public static String getNextWeekDateName() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        int year = cal.get(cal.YEAR);
        int month = cal.get(cal.MONTH) + 1;
        int week = cal.get(cal.DAY_OF_WEEK_IN_MONTH);
        return year + "��" + month + "�µ�" + week + "��";
    }

    /**
     * ���������һ
     *
     * @return
     */
    public static Date getNextWeekMonday() {
        Calendar cal = Calendar.getInstance();
        int n = 1;
        cal.add(Calendar.DATE, n * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * �����������
     *
     * @return
     */
    public static Date getNextWeekSunday() {
        Calendar cal = Calendar.getInstance();
        int n = 2;
        cal.add(Calendar.DATE, n * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return cal.getTime();
    }

    /**
     * �������ʱ���
     *
     * @return
     */
    public static List<DateWeekBean> getNextWeekDates() {
        List<DateWeekBean> dates = new ArrayList<DateWeekBean>();
        Calendar cal = Calendar.getInstance();
        int n = 1;
        cal.add(Calendar.DATE, n * 7);
        for (int i = Calendar.MONDAY; i <= Calendar.SATURDAY; i++) {
            DateWeekBean bean = new DateWeekBean();
            cal.set(Calendar.DAY_OF_WEEK, i);
            bean.setDate(getDateFormat(dateformat).format(cal.getTime()));
            bean.setWeek(getWeekDay(i));
            bean.setIsPast(DateUtil.ispast(getDateFormat(dateformat).format(cal.getTime())));
            dates.add(bean);
        }
        cal.add(Calendar.DATE, n * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        DateWeekBean bean = new DateWeekBean();
        bean.setDate(getDateFormat(dateformat).format(cal.getTime()));
        bean.setWeek(getWeekDay(Calendar.SUNDAY));
        bean.setIsPast(DateUtil.ispast(getDateFormat(dateformat).format(cal.getTime())));
        dates.add(bean);
        return dates;
    }

    /**
     * ���week day
     *
     * @param i
     * @return
     */
    public static String getWeekDay(int i) {
        String day;
        switch (i) {
            case 1:
                day = "������";
                break;
            case 2:
                day = "����һ";
                break;
            case 3:
                day = "���ڶ�";
                break;
            case 4:
                day = "������";
                break;
            case 5:
                day = "������";
                break;
            case 6:
                day = "������";
                break;
            case 7:
                day = "������";
                break;
            default:
                day = "������";
                break;
        }
        return day;
    }

    /**
     * @param date
     **/
    public static boolean ispast(String date) {
        long nowdate = getNowDateLong();
        long datelong = getDateLong(date);
        return nowdate > datelong;
    }

    /**
     * @param date
     * @param day
     **/
    public static boolean ispast(Date date, int day) {
        long nowdate = System.currentTimeMillis();
        long datelong = date.getTime();
        long pastTime = day * 24 * 60 * 60 * 1000L;
        return nowdate > (datelong + pastTime);
    }

    /**
     * ͨ����������ȡʱ��
     *
     * @return
     */
    public static Date getDateByMills(Long mills) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mills);
        return calendar.getTime();
    }

    /**
     * ��ȡʱ��d��mills����ǰ��date
     *
     * @param d
     * @param mills
     * @return
     */
    public static Date getDateBeforeMills(Date d, int mills) {
        Long dateMills = d.getTime();
        Long millsBefore = dateMills - mills;
        return getDateByMills(millsBefore);
    }

    /**
     * ��ȡsolr�������ʱ�䣬��ǰ����ʱ������8Сʱ
     *
     * @param date
     * @return
     */
    public static Date getSolrIndexTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 8);
        return calendar.getTime();
    }

    /**
     * ��ȡsolr��ѯ��ʱ��
     *
     * @param time
     * @return
     */
    public static String getSolrQueryTime(Long time) {
        return DateFormatUtils.format(time,
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    /**
     * ��ȡsolr��ѯ��ʱ��
     *
     * @param time
     * @return
     */
    public static String getSolrQueryTime(String time) {
        try {
            return DateFormatUtils.format(getDateFormat("yyyy-MM-dd HH:mm:ss").parse(time),
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ñ�Сʱ�Ŀ�ʼʱ�䣬��2012-01-01 01:00:00
     *
     * @return
     */
    public static Date getCurrentHourStartTime(Date date) {
        Date now = null;
        try {
            now = getDateFormat(dateHourMinuteSecondNOLineformat).parse(getDateFormat(dateformatYyyyMMddHH).format(date) + "0000");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * ��ñ�Сʱ�Ľ���ʱ�䣬��2012-01-01 23:59:59
     *
     * @return
     */
    public static Date getCurrentHourEndTime(Date date) {
        Date now = new Date();
        try {
            now = getDateFormat(dateHourMinuteSecondNOLineformat).parse(getDateFormat(dateformatYyyyMMddHH).format(date) + "5959");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }


    /**
     * ��ñ���Ŀ�ʼʱ�䣬��2012-01-01 01:00:00
     *
     * @return
     */
    public static Date getCurrentDayStartTime(Date date) {
        Date now = null;
        try {
            now = getDateFormat(dateHourMinuteSecondNOLineformat).parse(getDateFormat(dateNOLineformat).format(date) + "000000");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * ��ñ���Ľ���ʱ�䣬��2012-01-01 23:59:59
     *
     * @return
     */
    public static Date getCurrentDayEndTime(Date date) {
        Date now = new Date();
        try {
            now = getDateFormat(dateHourMinuteSecondNOLineformat).parse(getDateFormat(dateNOLineformat).format(date) + "235959");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * ��ȡ��һ������Ŀ�ʼʱ��
     *
     * @param now
     * @param interval ����
     * @return
     */
    public static Date getPrevSectionBegin(Date now, int interval) {
        return new Date(now.getTime() - (now.getTime() - DateUtil.getCurrentHourStartTime(now).getTime()) % (TimeUnit.MINUTES.toMillis(interval)) - TimeUnit.MINUTES.toMillis(interval));
    }

    /**
     * ��ȡ��һ������Ľ���ʱ��
     *
     * @param now
     * @param interval ����
     * @return
     */
    public static Date getPrevSectionEnd(Date now, int interval) {
        return new Date(now.getTime() - (now.getTime() - DateUtil.getCurrentHourStartTime(now).getTime()) % (TimeUnit.MINUTES.toMillis(interval)) - TimeUnit.SECONDS.toMillis(1));
    }

    /**
     * ��ȡ����Ŀ�ʼʱ��
     *
     * @param now
     * @param interval ����
     * @return
     */
    public static Date getSectionBegin(Date now, int interval) {
        return new Date(now.getTime() - (now.getTime() - DateUtil.getCurrentHourStartTime(now).getTime()) % (TimeUnit.MINUTES.toMillis(interval)));
    }

    /**
     * ��ȡ����Ľ���ʱ��
     *
     * @param now
     * @param interval ����
     * @return
     */
    public static Date getSectionEnd(Date now, int interval) {
        return new Date(now.getTime() - (now.getTime() - DateUtil.getCurrentHourStartTime(now).getTime()) % (TimeUnit.MINUTES.toMillis(interval)) + TimeUnit.MINUTES.toMillis(6) - TimeUnit.SECONDS.toMillis(1));
    }

    /**
     * LONGתDATE
     *
     * @param dateTime
     * @return
     */
    public static Date getDateFromLong(Long dateTime) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(dateTime);
        return c.getTime();
    }

    /**
     * ��̬ʱ��ת��
     *
     * @param time
     * @return
     */
    public static String getActivityTime(String time) {
        StringBuilder builder = new StringBuilder();
        try {
            Date d = getDateFormat(dateHourMinuteSecondformat).parse(time);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            builder.append(month);
            builder.append("��");
            builder.append(day);
            builder.append("��");
            if (hour == 0) {
                builder.append(0);
            }
            builder.append(hour);

            builder.append(":");
            if (minute == 0) {
                builder.append("00");
            } else if (minute < 10 && minute > 0) {
                builder.append(0);
                builder.append(minute);
            } else {
                builder.append(minute);
            }
        } catch (ParseException e) {
            logger.error("DateUtil->getActivityTime error", e);
        }
        return builder.toString();
    }

    /**
     * ���������ͺϲ�Ϊ��Сʱ
     * ���磺
     * 201605041539--->201605041530
     * 201605041509--->201605041500
     *
     * @param date
     * @return
     */
    public static String getDateHalfHourString(Date date) {
        String dateString = getHourMinuteTimeStringNOLine(date);
        String minuteString = dateString.substring(dateString.length() - 2, dateString.length() - 1);
        if ("0".equals(minuteString) || "1".equals(minuteString) || "2".equals(minuteString)) {
            dateString = dateString.substring(0, dateString.length() - 2) + "00";
        } else {
            dateString = dateString.substring(0, dateString.length() - 2) + "30";
        }
        return dateString;
    }

    /**
     * ��ȡʱ���24Сʱ��
     * 2016-06-13 00:00:00
     *
     * @param day
     * @return
     */
    public static List<Date> getDay24Hours(String day) {
        List<Date> hours = new ArrayList<Date>();
        Date t = getTimeDate(day);
        hours.add(t);
        for (int i = 1; i < 25; i++) {
            Date t1 = DateUtils.addHours(t, i);
            hours.add(t1);
        }
        return hours;
    }


    public static class DateWeekBean implements Serializable {
        private static final long serialVersionUID = -2523214322526862752L;

        private String date;
        private String week;
        private boolean isPast;

        public String getDate() {
            return date;
        }

        /**
         * setter of date
         *
         * @param date
         **/
        public void setDate(String date) {
            this.date = date;
        }

        /**
         * getter of week
         **/
        public String getWeek() {
            return week;
        }

        /**
         * setter of week
         *
         * @param week
         **/
        public void setWeek(String week) {
            this.week = week;
        }

        /**
         * getter of ispast
         **/
        public boolean getIsPast() {
            return isPast;
        }

        /**
         * setter of ispast
         *
         * @param isPast
         **/
        public void setIsPast(boolean isPast) {
            this.isPast = isPast;
        }
    }

    /**
     * ��ø���ʱ��long����
     *
     * @return
     */
    public static long getTimeLong1(String time) {
        Date date = null;
        try {
            date = DateUtil.getDateFormat(dateformat).parse(time);
        } catch (ParseException e) {
            logger.error("ParseException", e);
        }
        return date.getTime();
    }

    /**
     * ������ʱ���ȡ֮�����
     *
     * @param one
     * @param two
     * @param unit ��λ
     * @return
     */
    public static long getTheNumberBetween(Date one, Date two, TimeUnit unit) {
        long oneTime = one.getTime();
        long twoTime = two.getTime();
        long abs = Math.abs(oneTime - twoTime);
        return unit.convert(abs, TimeUnit.MILLISECONDS);
    }

    /**
     * ��ȡָ��ʱ���ʼ��ʱ��
     *
     * @param date
     * @return
     */
    public static Date getTheDateFirstTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * ��ȡָ��ʱ�����β��ʱ��
     *
     * @param date
     * @return
     */
    public static Date getTheDateLastTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

}