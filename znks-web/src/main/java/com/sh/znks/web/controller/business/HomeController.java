package com.sh.znks.web.controller.business;

import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.dto.HistoryQuestionCondition;
import com.sh.znks.service.business.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wuminggu on 2018/9/27.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    private final static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "/getHomeInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse getHomeInfo() {
        //校验必填项(无)
        return homeService.getHomeInfo();
    }

    @RequestMapping(value = "/getHistoryQuestionInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse getHistoryQuestionInfo(HistoryQuestionCondition historyQuestionCondition) {
        //校验必填项(无)
        return homeService.getHistoryQuestionInfo(historyQuestionCondition);
    }

    @RequestMapping(value = "/collectionQuestion", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse collectionQuestion(Long questionId, Integer status) {
        //校验必填项(无)
        return homeService.collectionQuestion(questionId, status);
    }
}
