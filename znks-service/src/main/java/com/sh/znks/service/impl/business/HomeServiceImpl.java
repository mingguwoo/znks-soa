package com.sh.znks.service.impl.business;

import com.sh.znks.common.base.AuthorHolder;
import com.sh.znks.common.base.Constant;
import com.sh.znks.common.base.PocketData;
import com.sh.znks.common.base.util.JsonUtils;
import com.sh.znks.common.base.util.ParamEditUtils;
import com.sh.znks.common.base.util.RedisKeyConstant;
import com.sh.znks.common.base.util.RedisUtils;
import com.sh.znks.common.result.ResultCodeEnum;
import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.dao.AnswerDao;
import com.sh.znks.dao.BattleDao;
import com.sh.znks.dao.QuestionDao;
import com.sh.znks.dao.UserDao;
import com.sh.znks.domain.aq.Battle;
import com.sh.znks.domain.aq.Memorandum;
import com.sh.znks.domain.aq.Question;
import com.sh.znks.domain.dto.BattleInfo;
import com.sh.znks.domain.dto.GoodFriends;
import com.sh.znks.domain.dto.HistoryQuestionCondition;
import com.sh.znks.domain.dto.HistoryResult;
import com.sh.znks.domain.dto.QuestionCondition;
import com.sh.znks.domain.user.WxUser;
import com.sh.znks.service.business.HomeService;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuminggu on 2018/10/9.
 */
@Service("homeService")
public class HomeServiceImpl implements HomeService {
    private final static Logger log = LoggerFactory.getLogger(HomeServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private BattleDao battleDao;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ResultResponse getHomeInfo() {
        try {
//            Map<String, Object> resRedis = (Map<String, Object>) redisUtils.get(RedisKeyConstant.KEY_HOMEINFO_MAP);
//            log.error("L49_getHomeInfo resRedis is{}", JsonUtils.toJson(resRedis));
//            if (resRedis != null) {
//                return new ResultResponse(ResultCodeEnum.ZN_OK, resRedis);
//            }

            WxUser wu = AuthorHolder.getWxAuthor();
            Map<String, Object> resMap = new HashMap<>();
            //取得与作者相关的10个好友
            List<GoodFriends> gfList = new ArrayList<>();
            String latitude = wu.getLatitude().substring(0, 3);
            String longitude = wu.getLongitude().substring(0, 4);
            List<WxUser> goodFriends = userDao.getTenUserInfoByRelation(latitude, longitude, Constant.ZERO, Constant.TEN);
            if (CollectionUtils.isEmpty(goodFriends) || goodFriends.size() < 3) {
                //兜底数据设置
                gfList = PocketData.getGoodFriends();
            } else {
                int current = 1;
                for (WxUser wxUser : goodFriends) {
                    if (current > 3) {
                        break;
                    }
                    GoodFriends gf = new GoodFriends();
                    gf.setUserId(wxUser.getUnionId());
                    gf.setName(wxUser.getNickName());
                    gf.setHeadUrl(wxUser.getAvatarUrl());
                    gfList.add(gf);
                    current++;
                }
            }

            //取得最新创建的10个团信息（团id、名称、参加人数）
            List<BattleInfo> biList = new ArrayList<>();
            //取得状态为(0等待团战、1团战中)的团战信息
            List<Battle> battles = battleDao.getTenBattleInfoByStatus(Constant.ZERO, Constant.TEN);
            if (CollectionUtils.isEmpty(battles) || battles.size() < 3) {
                //兜底数据设置
                biList = PocketData.getBattleInfo();
            } else {
                int current = 1;
                for (Battle battle : battles) {
                    if (current > 5) {
                        break;
                    }
                    BattleInfo bi = new BattleInfo();
                    bi.setBattleId(battle.getBattleId());
                    bi.setBattleName(battle.getBattleName());
                    bi.setBattleAmount(battle.getBattleAmount());
                    biList.add(bi);
                    current++;
                }
            }

            //取得当前一小时内提交答案的总人数（根据用户区分）
            Long activityCount = answerDao.getCountByOneHour();

            //取得历史的错题（按时间倒序）
            Long errorCount = answerDao.getErrorAnswerCount(wu.getUnionId());

            resMap.put("goodFriends", gfList);
            resMap.put("battleInfos", biList);
            resMap.put("activityCount", activityCount);
            resMap.put("errorCount", errorCount);

            //放在redis中保存1s,10秒内的请求返回值不变(即使查询条件变化也是一样)
//            redisUtils.set(RedisKeyConstant.KEY_QUESTIONS_LIST, resMap, Constant.TEN);

            return new ResultResponse(ResultCodeEnum.ZN_OK, resMap);
        } catch (Exception e) {
            log.error("L105_getHomeInfo e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse getHistoryQuestionInfo(HistoryQuestionCondition historyQuestionCondition) {
        try {
            WxUser wu = AuthorHolder.getWxAuthor();
            List<String> questionIds = answerDao.getQuestionIdsByAnswer(wu.getUnionId());
            if (CollectionUtils.isEmpty(questionIds)) {
                log.error("L133_getHistoryQuestionInfo questionIds is{}", JsonUtils.toJson(questionIds));
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);
            }

            //查询条件编辑
            historyQuestionCondition.setQuestionIds(questionIds);
            HistoryQuestionCondition hqc = ParamEditUtils.editGetHistoryQuestionList(historyQuestionCondition);
            List<Question> questions = questionDao.getHistoryQuestions(hqc);
            if (CollectionUtils.isEmpty(questions)) {
                log.error("L145_getHistoryQuestionInfo questions is{}", JsonUtils.toJson(questions));
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);
            }

            //编辑结果返回
            List<HistoryResult> results = new ArrayList<>();
            for (Question q : questions) {
                HistoryResult hr = new HistoryResult();
                hr.setQuestionId(q.getQuestionId());
                hr.setQuestionDescribe(q.getQuestionDescribe());
                results.add(hr);
            }

            return new ResultResponse(ResultCodeEnum.ZN_OK, results);
        } catch (Exception e) {
            log.error("L151_getHistoryQuestionInfo e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }

    @Override
    public ResultResponse collectionQuestion(Long questionId, Integer status) {
        try {
            WxUser user = AuthorHolder.getWxAuthor();
            //查询条件编辑
            QuestionCondition qc = new QuestionCondition();
            qc.setQuestionId(questionId);
            List<Question> queList = questionDao.getQuestionInfos(qc);
            if (CollectionUtils.isEmpty(queList))
                return new ResultResponse(ResultCodeEnum.ZN_NO_DATA);

            Memorandum memorandum = new Memorandum();
            memorandum.setQuestionId(questionId);
            memorandum.setUserId(user.getUnionId());
            //查询是否存在收藏题
            Memorandum memorandumInfo = questionDao.getMemorandumInfo(questionId, user.getUnionId());
            if (memorandum == null) {
                memorandum.setStatus(Constant.ONE);
                questionDao.insertMemorandum(memorandum);
            } else {
                memorandum.setStatus(status);
                questionDao.updateMemorandumStatus(memorandum);
            }

            return new ResultResponse(ResultCodeEnum.ZN_OK);
        } catch (Exception e) {
            log.error("L194_collectionQuestion e:", e);
            return new ResultResponse(ResultCodeEnum.ZN_SYS_ERR);
        }
    }


}
