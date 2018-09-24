package com.sh.znks.service.impl.business;

import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.base.Constant;
import com.sh.znks.common.base.util.ParamEditUtils;
import com.sh.znks.common.base.util.RedisUtils;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.dao.AnswerDao;
import com.sh.znks.dao.QuestionDao;
import com.sh.znks.domain.aq.Answer;
import com.sh.znks.domain.aq.Question;
import com.sh.znks.domain.dto.AnswerCondition;
import com.sh.znks.domain.dto.AnswerParam;
import com.sh.znks.domain.dto.QuestionCondition;
import com.sh.znks.domain.dto.QuestionParam;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.domain.user.WxUser;
import com.sh.znks.service.business.QuestionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by wuminggu on 2018/7/5.
 */
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    private final static Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private RedisUtils redisUtils;

    private List<Question> questionList = new ArrayList<Question>();

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    @Override
    public ResultResponse deployQuestion(QuestionParam param) {
        try {
            //��ѯ�Ƿ��Ѵ���ͬһ��Ŀ
            QuestionCondition condition = new QuestionCondition();
            condition.setQuestionDescribe(param.getQuestionDescribe());
            condition.setSize(1);
            List<Question> resQ = questionDao.getQuestionInfos(condition);
            if (CollectionUtils.isNotEmpty(resQ) || resQ.size() > 0)
                return new ResultResponse(ResultCodeEnum.ZN_QUESTION_EXIST);

            //�༭����
            String questionId = questionDao.getQuestionIdMax();
            Question question = ParamEditUtils.editDeployQuestion(param,questionId);

            int res = questionDao.insertQuestion(question);
            if (res <= Constant.ZERO)
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        } catch (Exception e) {
            log.error("L65_deployQuestion e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }

        return new ResultResponse(ResultCodeEnum.ZN_OK);
    }

    @Override
    public ResultResponse getQuestionList(QuestionCondition condition) {
        try {
//            List<Question> resRedis = (List<Question>) redisUtils.get(RedisKeyConstant.KEY_QUESTIONS_LIST);
//            log.error("L65_getQuestionList resRedis is{}", JsonUtils.toJson(resRedis));
//            if (CollectionUtils.isEmpty(resRedis)) {
            //��ѯ�����༭
            QuestionCondition qc = ParamEditUtils.editGetQuestionList(condition);
            List<Question> resQueList = questionDao.getQuestionInfos(qc);
            if (CollectionUtils.isEmpty(resQueList))
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);

            List<String> questionIdList = new ArrayList<String>();
            for (Question question : resQueList)
                questionIdList.add(question.getQuestionId());
            //����redis�б���1s,һ���ڵ����󷵻�ֵ����(��ʹ��ѯ�����仯Ҳ��һ��)
//            redisUtils.set(RedisKeyConstant.KEY_QUESTIONS_LIST, resList, Constant.TEN);
            //�����ö�ʱ���񲻶ϵ�ˢ����,�����ؽ��ֱ�ӷ����ڴ���,����ÿ���������õ����µ�
//                resRedis = resList;
//            }

            //����ֵ�༭
            List<Question> resultList = new ArrayList<Question>();
            if (qc.getTaskStatus() != null) {
                GeneralUser user = AuthorHolder.getGeneralAuthor();
                if (user == null)
                    return new ResultResponse(ResultCodeEnum.ZN_NO_LOGIN);

                List<Answer> resAnsList = answerDao.getAnswerInfoByQueList(user.getUserId(), user.getZn(), questionIdList);
                List<String> questionIdByAnswerList = new ArrayList<String>();
                if (CollectionUtils.isNotEmpty(resAnsList)) {
                    for (Answer aa : resAnsList)
                        questionIdByAnswerList.add(aa.getQuestionId());
                }

                for (Question ql : resQueList) {
                    //δ����ɸѡ
                    if (qc.getTaskStatus() == 0) {
                        if (CollectionUtils.isNotEmpty(questionIdByAnswerList) && questionIdByAnswerList.contains(ql.getQuestionId()))
                            continue;
                    }
                    //������ɸѡ
                    if (qc.getTaskStatus() == 1) {
                        if (CollectionUtils.isNotEmpty(questionIdByAnswerList) && !questionIdByAnswerList.contains(ql.getQuestionId()))
                            continue;
                    }
                    resultList.add(ql);
                }
            } else {
                resultList = resQueList;
            }

            // ȫ�ֱ���
            this.setQuestionList(resultList);

            return new ResultResponse(ResultCodeEnum.ZN_OK, resultList);
        } catch (Exception e) {
            log.error("L140_getQuestionList e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse questionDetail(String questionId) {
        try {
            WxUser user = AuthorHolder.getWxAuthor();
            if (user == null)
                return new ResultResponse(ResultCodeEnum.ZN_NO_LOGIN);
            //��ѯ�����༭
            QuestionCondition qc = new QuestionCondition();
            qc.setQuestionId(questionId);
            List<Question> queList = questionDao.getQuestionInfos(qc);
            if (CollectionUtils.isEmpty(queList))
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);

            //����ֵ�༭���õ�������Ϣ��ѯ��Ӧ���ύ�𰸼�¼List
            AnswerCondition ac = new AnswerCondition();
            ac.setQuestionId(questionId);
            ac.setUserId(user.getUnionId());
            List<Answer> ansList = answerDao.getAnswerInfos(ac);

            Map<String, Object> resmap = new HashedMap();
            resmap.put("questionInfo", queList.get(0));
            resmap.put("answerInfo", ansList);

            return new ResultResponse(ResultCodeEnum.ZN_OK, resmap);
        } catch (Exception e) {
            log.error("L114_questionDetail e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse submitAnswer(List<AnswerParam> params) {
        //����У��
        WxUser user = AuthorHolder.getWxAuthor();
        if (user == null)
            return new ResultResponse(ResultCodeEnum.ZN_NO_LOGIN);

        //�����༭
        try {
            Integer answerIdInt;
            String answerId = answerDao.getAnswerIdMax();
            if (StringUtils.isEmpty(answerId))
                answerIdInt = 1;
            else
                answerIdInt = Integer.parseInt(answerId);
            List<Answer> answers = new ArrayList<Answer>();
            for (AnswerParam param : params) {
                if (!param.getUserId().equals(user.getUnionId()))
                    return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
                answerIdInt++;
                Answer an = ParamEditUtils.editDeployAnswer(param, answerIdInt);
                answers.add(an);
            }

            int res = answerDao.insertAnswers(answers);
            if (res <= Constant.ZERO)
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
            return new ResultResponse(ResultCodeEnum.ZN_OK);
        } catch (Exception e) {
            log.error("L158_submitAnswer e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

}
