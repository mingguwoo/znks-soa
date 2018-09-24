package com.sh.znks.common.base.util;

import com.alibaba.fastjson.JSONArray;
import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.base.Constant;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.aq.Answer;
import com.sh.znks.domain.aq.Question;
import com.sh.znks.domain.dto.AnswerParam;
import com.sh.znks.domain.dto.QuestionCondition;
import com.sh.znks.domain.dto.QuestionParam;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.domain.user.WxUser;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wuminggu on 2018/7/5.
 */
public class ParamEditUtils {

    /**
     * ������Ŀ��Ϣ�Ĳ����༭
     */
    public static Question editDeployQuestion(QuestionParam param, String questionId) {
        //�༭����
        Question question = new Question();
        if (StringUtils.isEmpty(questionId))
            questionId = "1";
        else
            questionId = (Integer.parseInt(questionId) + 1) + Constant.BLANK;
        question.setQuestionId(questionId);
        //��Ŀ״̬:0δͨ����1ͨ����2�����
        question.setStatus(Constant.TWO);
        if (param.getGrade() != null && Constant.GRADEK12.contains(param.getGrade()))
            question.setGrade(param.getGrade());
        if (param.getSubjectId() != null && Constant.SUBJECT.contains(param.getSubjectId()))
            question.setSubjectId(param.getSubjectId());
        if (param.getQuestionType() != null && Constant.QUESTIONTYPES.contains(param.getQuestionType()))
            question.setQuestionType(param.getQuestionType());
        if (StringUtils.isNotBlank(param.getTips()))
            question.setTips(param.getTips());
        if (param.getQuestionValue() != null && Constant.BLOOD.contains(param.getQuestionValue()))
            question.setQuestionValue(param.getQuestionValue());
        else
            question.setQuestionValue(Constant.ZERO);
        if (param.getDifficultyLevel() != null && Constant.DIFFICULTY_DEGREE.contains(param.getDifficultyLevel()))
            question.setDifficultyLevel(param.getDifficultyLevel());
        else
            question.setDifficultyLevel(Constant.ONE);
        /*if (StringUtils.isNotBlank(param.getExpertZn()))
            question.setExpertZn(param.getExpertZn());*/
        if (StringUtils.isNotBlank(param.getKnowledge()))
            question.setKnowledge(param.getKnowledge());
        question.setQuestionDescribe(param.getQuestionDescribe());
        question.setStandardAnswer(param.getStandardAnswer());
        question.setExpertId(param.getExpertId());
        question.setOption1(param.getOption1());
        question.setOption2(param.getOption2());
        question.setOption3(param.getOption3());
        question.setOption4(param.getOption4());
        question.setOption5(param.getOption5());
        question.setOption6(param.getOption6());
        question.setOption7(param.getOption7());
        question.setOption8(param.getOption8());
        question.setOption9(param.getOption9());
        question.setOption10(param.getOption10());

        return question;
    }

    /**
     * �༭��ѯ��Ŀ�����
     */
    public static QuestionCondition editGetQuestionList(QuestionCondition condition) {
        QuestionCondition qc = new QuestionCondition();
        //�༭����
        if (condition != null) {
            if (Constant.GRADEK12.contains(condition.getGrade()))
                qc.setGrade(condition.getGrade());
            if (Constant.SUBJECT.contains(condition.getSubjectId()))
                qc.setSubjectId(condition.getSubjectId());
            if (StringUtils.isNotBlank(condition.getQuestionDescribe()) && condition.getQuestionDescribe().length() > 0)
                qc.setQuestionDescribe(condition.getQuestionDescribe());
            if (Constant.QUESTIONTYPES.contains(condition.getQuestionType()))
                qc.setQuestionType(condition.getQuestionType());
            if (Constant.BLOOD.contains(condition.getQuestionValue()))
                qc.setQuestionValue(condition.getQuestionValue());
            if (Constant.DIFFICULTY_DEGREE.contains(condition.getDifficultyLevel()))
                qc.setDifficultyLevel(condition.getDifficultyLevel());
            if (condition.getQuestionId() != null)
                qc.setQuestionId(condition.getQuestionId());
            /*if (StringUtils.isNotBlank(condition.getExpertZn()))
                qc.setExpertZn(condition.getExpertZn());*/
            if (StringUtils.isNotBlank(condition.getExpertId()))
                qc.setExpertId(condition.getExpertId());
        }
        qc.setCreated(condition.getCreated());
        qc.setModified(condition.getModified());
        qc.setStatus(condition.getStatus());
        qc.setStart(condition.getStart());
        qc.setSize(condition.getSize());
        qc.setTaskStatus(condition.getTaskStatus());

        return qc;
    }

    /**
     * �������Ϣ�Ĳ����༭
     */
    public static Answer editDeployAnswer(AnswerParam param, Integer answerId) {
        //�༭����
        Answer an = new Answer();
        an.setAnswerId(answerId + Constant.BLANK);
        an.setUserId(param.getUserId());
//        an.setUserZn(param.getUserZn());
        an.setQuestionId(param.getQuestionId());
        an.setAnswerDetail(param.getAnswerDetail());
        an.setStatus(Constant.ONE);

        return an;
    }

    /**
     * ����΢�ŵ�¼ƾ֤У��ӿ�url
     * @param authorizationCode
     * @return
     */
    public static String getWxUrl(String wxurl, String appid, String secret, String authorizationCode) {
        StringBuilder wxUrlStr = new StringBuilder();
        wxUrlStr.append(wxurl);
        wxUrlStr.append("appid=");
        wxUrlStr.append(appid);
        wxUrlStr.append("&secret=");
        wxUrlStr.append(secret);
        wxUrlStr.append("&js_code=");
        wxUrlStr.append(authorizationCode);
        wxUrlStr.append("&grant_type=authorization_code");

        return wxUrlStr.toString();
    }

    /**
     * ��ȡ�û���¼��Ȩtoken
     * @param openid
     * @param sessionKey
     * @return
     */
    public static String getToken(String openid, String sessionKey) {
        Date now = new Date();
        String timeStamp = String.valueOf(now.getTime());
        String token = openid + "_" + sessionKey + "_" + timeStamp;
        return token;
    }

    /**
     * У���¼̬token
     * @param token
     * @return
     */
    public static boolean checkTokenAvailability(String token) {
        boolean isAvailable = false;
        if (StringUtils.isBlank(token)) {
            return isAvailable;
        }

        String[] tokenParam = token.split("_");
        String openid = tokenParam[0];
        String sessionKey = tokenParam[1];
        if (StringUtils.isBlank(openid) || StringUtils.isBlank(sessionKey)) {
            return isAvailable;
        }

        WxUser wxUser = AuthorHolder.getWxAuthor();
        if (wxUser != null && openid.equals(wxUser.getOpenid()) && sessionKey.equals(wxUser.getSessionKey())) {
            isAvailable = true;
        }

        return isAvailable;
    }

    /**
     * ��ȡ�����ύ�𰸽��
     * @param param
     * @return
     */
    public static List<AnswerParam> getAnswerParamList(String param) {
        if (StringUtils.isBlank(param)) {
            return null;
        }
        List<AnswerParam> params = new ArrayList<>();
        JSONArray jsonArray = JSONArray.parseArray(param);
        for (Object paramObj : jsonArray) {
            Map map = (Map) paramObj;
            if (map.get("questionId") == null || map.get("answerDetail") == null || map.get("userId") == null) {
                continue;
            }
            AnswerParam ap = new AnswerParam();
            ap.setQuestionId(map.get("questionId").toString());
            ap.setAnswerDetail(map.get("answerDetail").toString());
            ap.setUserId(map.get("userId").toString());
            params.add(ap);
        }
        return params;
    }
}
