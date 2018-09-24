package com.sh.znks.web.controller.business;

import com.sh.znks.common.base.util.ParamEditUtils;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.dto.AnswerParam;
import com.sh.znks.domain.dto.QuestionCondition;
import com.sh.znks.domain.dto.QuestionParam;
import com.sh.znks.service.business.QuestionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuminggu on 2018/6/14.
 */
@Controller
@RequestMapping("/business")
public class TrainingController {
    private final static Logger log = LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/deployQuestion", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse deployQuestion(QuestionParam param) {
        //У�������
        if (param == null
                || StringUtils.isBlank(param.getQuestionDescribe())
                || StringUtils.isBlank(param.getStandardAnswer())
                || StringUtils.isBlank(param.getExpertId())) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return questionService.deployQuestion(param);
    }

    @RequestMapping(value = "/getQuestionList", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse getQuestionList(QuestionCondition condition) {
        //У�������(��)
        return questionService.getQuestionList(condition);
    }

    @RequestMapping(value = "/questionDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse questionDetail(String questionId) {
        //У�������
        if (StringUtils.isBlank(questionId))
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);

        return questionService.questionDetail(questionId);
    }

    @RequestMapping(value = "/submitAnswer", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse submitAnswer(HttpServletRequest request) {
        //�༭����
        List<AnswerParam> params = ParamEditUtils.getAnswerParamList(request.getParameter("params"));

        //У�������
        if (CollectionUtils.isEmpty(params)) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return questionService.submitAnswer(params);
    }



}
