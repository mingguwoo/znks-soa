package com.sh.znks.domain.dto;

/**
 * Created by wuminggu on 2018/10/9.
 */
public class BattleInfo {
    private String battleId;                    //战团id
    private String battleName;                  //战团名称
    private Integer battleAmount;               //参团人数（实际人数）

    public String getBattleId() {
        return battleId;
    }

    public void setBattleId(String battleId) {
        this.battleId = battleId;
    }

    public String getBattleName() {
        return battleName;
    }

    public void setBattleName(String battleName) {
        this.battleName = battleName;
    }

    public Integer getBattleAmount() {
        return battleAmount;
    }

    public void setBattleAmount(Integer battleAmount) {
        this.battleAmount = battleAmount;
    }
}
