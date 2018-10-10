package com.sh.znks.web.controller.business;

import com.sh.znks.common.base.util.ParamEditUtils;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.aq.Battle;
import com.sh.znks.domain.dto.BattleParam;
import com.sh.znks.service.business.BattleService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuminggu on 2018/6/14.
 */
@Controller
@RequestMapping("/battle")
public class BattleController {
    private final static Logger log = LoggerFactory.getLogger(BattleController.class);

    @Autowired
    private BattleService battleService;

    @RequestMapping(value = "/deployBattleGroup", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse deployBattleGroup(HttpServletRequest request) {
        //校验必填项
        Battle paramCheck = ParamEditUtils.checkBattleParam(request);
        if (paramCheck == null) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return battleService.deployBattleGroup(paramCheck);
    }

    @RequestMapping(value = "/getBattleList", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse getBattleList(String battleId, String battleName) {
        //校验必填项
        return battleService.getBattleList(battleId, battleName);
    }

    @RequestMapping(value = "/getBattleDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse getBattleDetail(String battleId) {
        //校验必填项
        if (StringUtils.isBlank(battleId)) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return battleService.getBattleDetail(battleId);
    }

    @RequestMapping(value = "/addToBattleGroup", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse addToBattleGroup(String battleId) {
        //校验必填项
        if (StringUtils.isBlank(battleId)) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }
        return battleService.addToBattleGroup(battleId);
    }

    @RequestMapping(value = "/deleteBattle", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse deleteBattle(String battleId) {
        //校验必填项
        if (StringUtils.isBlank(battleId)) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return battleService.deleteBattle(battleId);
    }

    @RequestMapping(value = "/getBattleResultDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse getBattleResultDetail(String battleId) {
        //校验必填项
        if (StringUtils.isBlank(battleId)) {
            return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
        }

        return battleService.getBattleResultDetail(battleId);
    }
}
