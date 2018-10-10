package com.sh.znks.common.base.util;

import com.alibaba.fastjson.JSONArray;
import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.base.Constant;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.aq.Answer;
import com.sh.znks.domain.aq.Battle;
import com.sh.znks.domain.aq.Question;
import com.sh.znks.domain.dto.AnswerParam;
import com.sh.znks.domain.dto.BattleParam;
import com.sh.znks.domain.dto.HistoryQuestionCondition;
import com.sh.znks.domain.dto.QuestionCondition;
import com.sh.znks.domain.dto.QuestionParam;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.domain.user.WxUser;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuminggu on 2018/7/5.
 */
public class ParamEditUtils {

    /**
     * 插入题目信息的参数编辑
     */
    public static Question editDeployQuestion(QuestionParam param, Long questionId) {
        //编辑参数
        Question question = new Question();
        if (questionId == null)
            questionId = 1L;
        else
            questionId = questionId + 1;
        question.setQuestionId(questionId);
        //题目状态:0未通过、1通过、2审核中
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
        question.setQuestionArea(param.getQuestionArea());
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
     * 编辑查询题目的入参
     */
    public static QuestionCondition editGetQuestionList(QuestionCondition condition) {
        QuestionCondition qc = new QuestionCondition();
        //编辑参数
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
        qc.setQuestionArea(condition.getQuestionArea());
        qc.setStart(condition.getStart());
        qc.setSize(condition.getSize());
        qc.setTaskStatus(condition.getTaskStatus());

        return qc;
    }

    /**
     * 插入答案信息的参数编辑
     */
    public static Answer editDeployAnswer(AnswerParam param, Long answerId) {
        //编辑参数
        Answer an = new Answer();
        an.setAnswerId(answerId);
        an.setUserId(param.getUserId());
//        an.setUserZn(param.getUserZn());
        an.setQuestionId(param.getQuestionId());
        an.setAnswerDetail(param.getAnswerDetail());
        //答案审核中
        an.setStatus(Constant.ONE);
        an.setBasis(Constant.AUTO_JUDGE);
        an.setJudgeTime(new Date());
        an.setExpertId(Constant.SYS);
        an.setConnectId(param.getBattleId());

        return an;
    }

    /**
     * 生成微信登录凭证校验接口url
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
     * 获取用户登录授权token
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
     * 校验登录态token
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
     * 获取批量提交答案结果
     * @param param
     * "params":[{
                    "questionId":1，                              //题目ID
                    "answerDetail":"不会做"，                      //提交答案内容或者下载照片链接url
                    "unionId":"wuii",                             //unionId
                    "battleId":"15089809003"                      //团id
                },....]
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
            String questionIdStr = (String) map.get("questionId");
            ap.setQuestionId(Long.valueOf(questionIdStr));
            ap.setAnswerDetail(map.get("answerDetail").toString());
            ap.setUserId(map.get("userId").toString());
            if (map.get("battleId") != null) {
                ap.setBattleId(map.get("battleId").toString());
            }
            params.add(ap);
        }
        return params;
    }

    /**
     * 开团入参编辑
     * @param request
     * @return
     */
    public static Battle checkBattleParam(HttpServletRequest request) {
        Battle battle = new Battle();
        String battleName = request.getParameter("battleName");
        String slogan = request.getParameter("slogan");
        String difficultyLevelStr = request.getParameter("difficultyLevel");
        String questionAreaStr = request.getParameter("questionArea");
        String questionTypeStr = request.getParameter("questionType");
        String startTimeStr = request.getParameter("startTime");
        String battleCountStr = request.getParameter("battleCount");
        String battleTimeSecondStr = request.getParameter("battleTimeSecond");

        if (StringUtils.isBlank(battleName)
                || StringUtils.isBlank(difficultyLevelStr)
                || StringUtils.isBlank(questionAreaStr)
                || StringUtils.isBlank(questionTypeStr)
                || StringUtils.isBlank(startTimeStr)
                || StringUtils.isBlank(battleCountStr)
                || StringUtils.isBlank(battleTimeSecondStr))
            return null;

        Integer difficultyLevel = Integer.valueOf(difficultyLevelStr);
        Integer questionArea = Integer.valueOf(questionAreaStr);
        Integer questionType = Integer.valueOf(questionTypeStr);
        Date startTime = DateUtils.strToDate(startTimeStr, DateUtil.dateHourMinuteSecondformat);
        Integer battleCount = Integer.valueOf(battleCountStr);
        Integer battleTimeSecond = Integer.valueOf(battleTimeSecondStr);


        //开始时间必须大于现在时间
        Date now = new Date();
        if (startTime.getTime() < now.getTime())
            return null;

        //答题截止日期分三档
        Integer offsetTime = Constant.THIRTY;
        switch (battleTimeSecond) {
            case Constant.ONE:
                break;
            case Constant.TWO:
                offsetTime = Constant.SIXTY;
                break;
            case Constant.THREE:
                offsetTime = Constant.NINETY;
                break;
            default:
        }
        battle.setBattleTime(DateUtils.getOffSetSecondTime(offsetTime, startTime));

        //开团默认限制100人
        if (battleCount == null || battleCount < Constant.ONE)
            battle.setBattleCount(Constant.HUNDRED);
        else
            battle.setBattleCount(battleCount);

        //默认口号
        if (StringUtils.isBlank(slogan))
            battle.setSlogan(Constant.BATTLE_SLOGAN);
        else
            battle.setSlogan(slogan);

        //创建团战id（当前的时间戳）
        String battleId = String.valueOf(new Date().getTime());
        battle.setBattleId(battleId);
        //创建者
        WxUser wu = AuthorHolder.getWxAuthor();
        battle.setUserId(wu.getUnionId());
        battle.setStartTime(startTime);
        battle.setBattleAmount(Constant.ZERO);
        battle.setBattleName(battleName);
        battle.setDifficultyLevel(difficultyLevel);
        battle.setQuestionType(questionType);
        battle.setQuestionArea(questionArea);
        battle.setStatus(Constant.ZERO);

        return battle;
    }

    /**
     * 编辑查询历史题目记录的入参
     */
    public static HistoryQuestionCondition editGetHistoryQuestionList(HistoryQuestionCondition condition) {
        HistoryQuestionCondition hqc = new HistoryQuestionCondition();
        //编辑参数
        if (condition != null) {
            if (Constant.GRADEK12.contains(condition.getGrade()))
                hqc.setGrade(condition.getGrade());
            if (Constant.SUBJECT.contains(condition.getSubjectId()))
                hqc.setSubjectId(condition.getSubjectId());
            if (Constant.QUESTIONTYPES.contains(condition.getQuestionType()))
                hqc.setQuestionType(condition.getQuestionType());
            if (Constant.BLOOD.contains(condition.getQuestionValue()))
                hqc.setQuestionValue(condition.getQuestionValue());
            if (Constant.DIFFICULTY_DEGREE.contains(condition.getDifficultyLevel()))
                hqc.setDifficultyLevel(condition.getDifficultyLevel());
            if (CollectionUtils.isNotEmpty(condition.getQuestionIds()))
                hqc.setQuestionIds(condition.getQuestionIds());
        }
        hqc.setQuestionArea(condition.getQuestionArea());
        hqc.setStart(condition.getStart());
        hqc.setSize(condition.getSize());

        return hqc;
    }
}
