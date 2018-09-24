package com.sh.znks.common.base;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wuminggu on 2018/5/10.
 */
public class Constant {
    //���ַ���
    public static final String BLANK = "";
    //��Ŀ����д
    public static final String PJN = "zn";
    //MD5
    public static final String MD5 = "MD5";
    //ע��Ż���
    public static final int BASE_NO = 100000000;
    //�쳣Ĭ��ֵ
    public static final int EXC_NO = -1;
    //����0
    public static final int ZERO = 0;
    //����1
    public static final int ONE = 1;
    //����2
    public static final int TWO = 2;
    //����3
    public static final int THREE = 3;
    //����10
    public static final int TEN = 10;
    //�꼶K12
    public static final List<Integer> GRADEK12 = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12));
    //��Ŀ��0����1�2�⣩
    public static final List<Integer> SUBJECT = new ArrayList<Integer>(Arrays.asList(0,1,2));
    //����
    public static final List<Integer> QUESTIONTYPES = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16));
    //�����ֵ��0-5��Ѫ��
    public static final List<Integer> BLOOD = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5));
    //�Ѷ�ϵ����1-5���ǣ�
    public static final List<Integer> DIFFICULTY_DEGREE = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
    //�����߸�����ϢJSON�ֶ���
    public static final String GENERAL_USER_INFO = "generalUserInfo";
    //ר�Ҹ�����ϢJSON�ֶ���
    public static final String EXPERT_USER_INFO = "expertUserInfo";
}
