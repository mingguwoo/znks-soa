package com.sh.znks.service.business;

import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.aq.Battle;

/**
 * Created by wuminggu on 2018/9/21.
 */
public interface BattleService {
    /**
     * 创建新团
     * @param param
     * @return
     */
    public ResultResponse deployBattleGroup(Battle param);

    /**
     * 根据团名称/团id搜索开团列表
     * @param battleName
     * @return
     */
    public ResultResponse getBattleList(String battleId, String battleName);

    /**
     * 根据团id查询开团详情
     * @param battleId
     * @return
     */
    public ResultResponse getBattleDetail(String battleId);

    /**
     * 申请加入团战
     * @param battleId
     * @return
     */
    public ResultResponse addToBattleGroup(String battleId);

    /**
     * 解除团战
     * @param battleId
     * @return
     */
    public ResultResponse deleteBattle(String battleId);

    /**
     * 取得团战结果详情
     * @param battleId
     * @return
     */
    public ResultResponse getBattleResultDetail(String battleId);
}
