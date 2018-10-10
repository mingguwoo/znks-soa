package com.sh.znks.service.impl.business;

import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.base.Constant;
import com.sh.znks.common.base.util.DataUtils;
import com.sh.znks.common.base.util.JsonUtils;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.dao.AnswerDao;
import com.sh.znks.dao.BattleDao;
import com.sh.znks.dao.QuestionDao;
import com.sh.znks.dao.UserDao;
import com.sh.znks.domain.aq.Answer;
import com.sh.znks.domain.aq.Battle;
import com.sh.znks.domain.aq.BattleRecord;
import com.sh.znks.domain.aq.Gift;
import com.sh.znks.domain.aq.Question;
import com.sh.znks.domain.dto.BattleCondition;
import com.sh.znks.domain.dto.BattleResult;
import com.sh.znks.domain.dto.QuestionCondition;
import com.sh.znks.domain.user.WxUser;
import com.sh.znks.service.business.BattleService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuminggu on 2018/9/21.
 */
@Service("battleService")
public class BattleServiceImpl implements BattleService {
    private final static Logger log = LoggerFactory.getLogger(BattleServiceImpl.class);

    @Autowired
    private BattleDao battleDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AnswerDao answerDao;

    @Override
    public ResultResponse deployBattleGroup(Battle param) {
        //查询团id是否已创建，如果已存在则时间加5再检查
        try {
            List<Battle> resList = getBattleInfo(param.getBattleId(), null, null);
            if (resList != null) {
                log.error("L35_deployBattleGroup insertBattle is failed", JsonUtils.toJson(resList));
                return new ResultResponse(ResultCodeEnum.ZN_BATTLE_IS_EXIST);
            }

            //查出满足设定条件的10道题
            QuestionCondition qc = new QuestionCondition();
            qc.setDifficultyLevel(param.getDifficultyLevel());
            qc.setQuestionType(param.getQuestionType());
            qc.setQuestionArea(param.getQuestionArea());
            List<Question> questions = questionDao.getQuestionInfos(qc);
            if (CollectionUtils.isEmpty(questions)) {
                log.error("L53_deployBattleGroup questions is null");
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);
            }
            //设置10道题在一个字段
            StringBuilder sb = new StringBuilder();
            for (Question question : questions) {
                Long questionId = question.getQuestionId();
                sb.append(questionId);
                sb.append(Constant.COMMA);
            }
            param.setQuestionIds(sb.substring(0, sb.length() - 1));

            //插入
            battleDao.insertBattle(param);

            //把自己家人团中
            addToBattleGroup(param.getBattleId());

            return new ResultResponse(ResultCodeEnum.ZN_OK);
        } catch (Exception e) {
            log.error("L44_deployBattleGroup e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse getBattleList(String battleId, String battleName) {
        try {
            List<Battle> resList = getBattleInfo(battleId, null, battleName);
            if (resList == null) {
                log.error("L83_getBattleList is null");
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);
            }

            return new ResultResponse(ResultCodeEnum.ZN_OK, resList);
        } catch (Exception e) {
            log.error("L89_getBattleList e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse getBattleDetail(String battleId) {
        //开团详情
        try {
            List<Battle> resList = getBattleInfo(battleId, null, null);
            if (resList == null) {
                log.error("L112_getBattleDetail is null");
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);
            }
            //参团人员详情
            Battle battle = resList.get(0);
            Date startTime = battle.getStartTime();
            Date battleTime = battle.getBattleTime();
            String bId = battle.getBattleId();
            //当前时间
            Date now = new Date();
            //开团状态(To前端)：0申请加入(defaults)、1等待开始、2团战中、3解除团战、4查看详情
            Integer status = 0;
            //用户id
            String userId = AuthorHolder.getWxAuthor().getUnionId();

            List<WxUser> resUserList = null;
            //取得报名的所有用户
            List<String> userList = battleDao.getBattleRecordInfos(bId);
            if (CollectionUtils.isNotEmpty(userList)) {
                resUserList = userDao.getUserListByUnionIds(userList);
            }
            if (userId.equals(battle.getUserId()) && resUserList.size() == 1) {
                //未有人加入时创建者可解除团战
                status = 3;
            }
            if (resUserList.contains(userId)) {
                if (now.getTime() < startTime.getTime()) {
                    status = 1;
                } else if (startTime.getTime() < now.getTime() && now.getTime() < battleTime.getTime()) {
                    status = 2;
                } else {
                    status = 4;
                }
            }

            Map<String, Object> resMap = new HashMap<String, Object>();
            resMap.put("battleInfo", battle);
            //设置返回10个用户信息
            resMap.put("userInfo", resUserList.subList(Constant.ZERO, Constant.TEN));
            resMap.put("status", status);

            return new ResultResponse(ResultCodeEnum.ZN_OK, resMap);
        } catch (Exception e) {
            log.error("L155_getBattleDetail e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse addToBattleGroup(String battleId) {
        try {
            //查询团是否存在
            List<Battle> resList = getBattleInfo(battleId, Constant.ZERO, null);
            if (resList == null) {
                log.error("L168_addToBattleGroup is null");
                return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
            }

            Battle battle = resList.get(0);
            //如果参团人数上限已达到，则提示不可以再加入
            if (battle.getBattleCount() <= battle.getBattleAmount()) {
                log.error("L176_addToBattleGroup battleAmount is out of bound");
                return new ResultResponse(ResultCodeEnum.ZN_OUT_OF_BOUND);
            }
            //取得报名的所有用户
            WxUser user = AuthorHolder.getWxAuthor();
            List<String> userList = battleDao.getBattleRecordInfos(battleId);
            if (CollectionUtils.isNotEmpty(userList) && userList.contains(user.getUnionId())) {
                log.error("L184_addToBattleGroup user has joined");
                return new ResultResponse(ResultCodeEnum.ZN_USER_JOINED);
            }

            //更新团战表实际参团人数字段
            battleDao.updateBattleAmount(battleId);

            //设置参数
            BattleRecord br = new BattleRecord();
            br.setUserId(user.getUnionId());
            br.setBattleId(battleId);
            //插入团战记录表
            battleDao.insertBattleRecord(br);

            return new ResultResponse(ResultCodeEnum.ZN_OK);
        } catch (Exception e) {
            log.error("L200_addToBattleGroup e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse deleteBattle(String battleId) {
        try {
            //查询团是否存在
            List<Battle> resList = getBattleInfo(battleId, Constant.ZERO, null);
            if (resList == null) {
                log.error("L216_deleteBattle is null");
                return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
            }
            //用户id
            String userId = AuthorHolder.getWxAuthor().getUnionId();
            if (userId.equals(resList.get(0).getUserId()) && resList.get(0).getBattleAmount() == 1) {
                //未有人加入时创建者可解除团战
                List<String> battleIds = new ArrayList<>();
                battleIds.add(battleId);
                battleDao.updateBattleStatus(Constant.ZERO, Constant.EXC_NO, battleIds);
            }
            return new ResultResponse(ResultCodeEnum.ZN_OK);
        } catch (Exception e) {
            log.error("L229_deleteBattle e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse getBattleResultDetail(String battleId) {
        try {
            List<Battle> resList = getBattleInfo(battleId, Constant.TWO, null);
            if (resList == null) {
                log.error("L238_getBattleResultDetail is null");
                return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
            }
            //用户id
            String userId = AuthorHolder.getWxAuthor().getUnionId();
            //开团详情
            Battle battle = resList.get(0);
            String questionIds = battle.getQuestionIds();
            List<Long> questionIdList = DataUtils.string2List(questionIds);
            //获取问题详情
            List<Question> queList = questionDao.getListByQuestionIds(questionIdList);
            Map<String, Question> queMap = new HashMap<>();
            for (Question q : queList) {
                queMap.put(String.valueOf(q.getQuestionId()), q);
            }
            //获取答案详情
            List<Answer> ansList = answerDao.getAnswerInfoByQueList(userId, null, battleId, questionIdList);
            if (CollectionUtils.isEmpty(ansList)) {
                log.error("L252_getBattleResultDetail is null");
                return new ResultResponse(ResultCodeEnum.ZN_PARAM_ERR);
            }

            Map<String, Answer> ansMap = new HashMap<>();
            for (Answer a : ansList) {
                ansMap.put(String.valueOf(a.getQuestionId()), a);
            }
            //获取团战记录表信息
            BattleRecord brd = battleDao.getBattleRecordInfo(battleId, userId);

            //题号、提交答案、正确答案、结果
            Integer serialNumber = 1;
            List<BattleResult> battleResults = new ArrayList<>();
            for (Long queId : questionIdList) {
                BattleResult br = new BattleResult();
                if (ansMap.get(queId) == null) {
                    continue;
                }
                String commitAnswer = ansMap.get(queId).getAnswerDetail();
                String standardAnswer = queMap.get(queId).getStandardAnswer();
                br.setSerialNumber(serialNumber);
                br.setQuestionId(queId);
                br.setCommitAnswer(commitAnswer);
                br.setStandardAnswer(standardAnswer);
                if (commitAnswer.equals(standardAnswer)) {
                    br.setResult(true);
                } else {
                    br.setResult(false);
                }
                battleResults.add(br);
                serialNumber++;
            }

            //难度系数、答题总数、参团人数、正确率、排名、奖励
            Map<String, Object> resMap = new HashMap<String, Object>();
            resMap.put("resultList", battleResults);
            resMap.put("difficultyLevel", battle.getDifficultyLevel());
            resMap.put("questionTotal", questionIdList.size());
            resMap.put("battleAmount", battle.getBattleAmount());
            if (brd != null) {
                resMap.put("rate", brd.getBattleScore());
                resMap.put("ranking", brd.getRanking());
                //取得礼物
                if (brd.getGiftId() != null) {
                    Gift gift = battleDao.getGiftInfoById(brd.getGiftId());
                    resMap.put("giftName", gift.getGiftName());
                }
            }

            return new ResultResponse(ResultCodeEnum.ZN_OK, resMap);
        } catch (Exception e) {
            log.error("L235_getBattleResultDetail e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    private List<Battle> getBattleInfo(String battleId, Integer status, String battleName) throws Exception {
        BattleCondition condition = new BattleCondition();
        condition.setBattleId(battleId);
        condition.setStatus(status);
        condition.setBattleName(battleName);
        List<Battle> resList = battleDao.getBattleInfos(condition);
        if (CollectionUtils.isEmpty(resList)) {
            log.error("L256_getBattleInfo result is null");
            return null;
        }

        return resList;
    }

}
