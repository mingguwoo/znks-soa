package com.sh.znks.service.impl.business;

import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.base.Constant;
import com.sh.znks.common.base.util.JsonUtils;
import com.sh.znks.common.base.util.ParamEditUtils;
import com.sh.znks.common.base.util.RedisUtils;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.dao.AnswerDao;
import com.sh.znks.dao.BattleDao;
import com.sh.znks.dao.QuestionDao;
import com.sh.znks.domain.aq.Answer;
import com.sh.znks.domain.aq.BattleRecord;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
//    @Autowired
//    private RedisUtils redisUtils;
    @Autowired
    private BattleDao battleDao;

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
            //查询是否已存在同一题目
            QuestionCondition condition = new QuestionCondition();
            condition.setQuestionDescribe(param.getQuestionDescribe());
            condition.setSize(1);
            List<Question> resQ = questionDao.getQuestionInfos(condition);
            if (CollectionUtils.isNotEmpty(resQ) || resQ.size() > 0)
                return new ResultResponse(ResultCodeEnum.ZN_QUESTION_EXIST);

            //编辑参数
            Long questionId = questionDao.getQuestionIdMax();
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
            //查询条件编辑
            QuestionCondition qc = ParamEditUtils.editGetQuestionList(condition);
            List<Question> resQueList = questionDao.getQuestionInfos(qc);
            if (CollectionUtils.isEmpty(resQueList))
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);

            List<Long> questionIdList = new ArrayList<Long>();
            for (Question question : resQueList)
                questionIdList.add(question.getQuestionId());
            //放在redis中保存1s,一秒内的请求返回值不变(即使查询条件变化也是一样)
//            redisUtils.set(RedisKeyConstant.KEY_QUESTIONS_LIST, resList, Constant.TEN);
            //可以用定时任务不断的刷数据,将返回结果直接放在内存中,这样每次请求都能拿到最新的
//                resRedis = resList;
//            }

            //返回值编辑
            List<Question> resultList = new ArrayList<Question>();
            if (qc.getTaskStatus() != null) {
                GeneralUser user = AuthorHolder.getGeneralAuthor();
                List<Answer> resAnsList = answerDao.getAnswerInfoByQueList(user.getUserId(), user.getZn(), null, questionIdList);
                List<Long> questionIdByAnswerList = new ArrayList<Long>();
                if (CollectionUtils.isNotEmpty(resAnsList)) {
                    for (Answer aa : resAnsList)
                        questionIdByAnswerList.add(aa.getQuestionId());
                }

                for (Question ql : resQueList) {
                    //未做题筛选
                    if (qc.getTaskStatus() == 0) {
                        if (CollectionUtils.isNotEmpty(questionIdByAnswerList) && questionIdByAnswerList.contains(ql.getQuestionId()))
                            continue;
                    }
                    //已做题筛选
                    if (qc.getTaskStatus() == 1) {
                        if (CollectionUtils.isNotEmpty(questionIdByAnswerList) && !questionIdByAnswerList.contains(ql.getQuestionId()))
                            continue;
                    }
                    resultList.add(ql);
                }
            } else {
                resultList = resQueList;
            }

            // 全局变量
            this.setQuestionList(resultList);

            return new ResultResponse(ResultCodeEnum.ZN_OK, resultList);
        } catch (Exception e) {
            log.error("L140_getQuestionList e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse questionDetail(Long questionId) {
        try {
            WxUser user = AuthorHolder.getWxAuthor();
            //查询条件编辑
            QuestionCondition qc = new QuestionCondition();
            qc.setQuestionId(questionId);
            List<Question> queList = questionDao.getQuestionInfos(qc);
            if (CollectionUtils.isEmpty(queList))
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);

            //返回值编辑，拿到作者信息查询对应的提交答案记录List
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
        //参数编辑
        try {
            Long answerIdInt;
            String answerId = answerDao.getAnswerIdMax();
            if (StringUtils.isEmpty(answerId)) {
                answerIdInt = 1L;
            } else {
                answerIdInt = Long.valueOf(answerId);
            }

            WxUser user = AuthorHolder.getWxAuthor();
            String userId = user.getUnionId();
            List<Answer> answers = new ArrayList<Answer>();
            Integer score = 0;
            boolean isBattle = false;
            for (AnswerParam param : params) {
                if (!param.getUserId().equals(userId)) {
                    return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
                }

                //获取问题详情
                List<Question> queList = questionDao.getListByQuestionIds(new ArrayList<Long>(Arrays.asList(param.getQuestionId())));
                String commitAnswer = param.getAnswerDetail();
                String standardAnswer = queList.get(0).getStandardAnswer();

                answerIdInt++;
                Answer an = ParamEditUtils.editDeployAnswer(param, answerIdInt);
                //判断答案
                if (commitAnswer.equals(standardAnswer)) {
                    an.setResult(Constant.ONE);
                    score++;
                } else {
                    an.setResult(Constant.ZERO);
                }

                answers.add(an);
                //判断是否是团战结果提交
                if (StringUtils.isNotBlank(param.getBattleId())) {
                    isBattle = true;
                }
            }
            //插入答案结果
            int res = answerDao.insertAnswers(answers);
            if (res <= Constant.ZERO){
                return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
            }

            //团战结果提交时需对团战记录做更新
            if (isBattle) {
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                String rate = df.format((float)score/params.size());//返回的是String类型
                //更新记录表团战得分字段
                BattleRecord br = new BattleRecord();
                br.setUserId(userId);
                br.setBattleId(params.get(0).getBattleId());
                br.setBattleScore(rate);
                battleDao.updateBattleRecordInfo(br);
            }

            return new ResultResponse(ResultCodeEnum.ZN_OK);
        } catch (Exception e) {
            log.error("L158_submitAnswer e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse getErrorQuestionList() {
        try {
            WxUser wu = AuthorHolder.getWxAuthor();
            List<Long> resultList = answerDao.getTenErrorAnswerInfo(wu.getUnionId(), Constant.ZERO, Constant.TEN);
            if (CollectionUtils.isEmpty(resultList)) {
                log.error("L248_getErrorQuestionList resultList is{}", JsonUtils.toJson(resultList));
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);
            }

            return new ResultResponse(ResultCodeEnum.ZN_OK, resultList);
        } catch (Exception e) {
            log.error("L255_getErrorQuestionList e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

}
