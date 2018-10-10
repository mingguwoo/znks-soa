package com.sh.znks.common.base;

import com.sh.znks.domain.dto.BattleInfo;
import com.sh.znks.domain.dto.GoodFriends;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuminggu on 2018/10/9.
 */
public class PocketData {

    public static List<GoodFriends> getGoodFriends() {
        List<GoodFriends> gfList = new ArrayList<>();
        GoodFriends gf1 = new GoodFriends();
        GoodFriends gf2 = new GoodFriends();
        GoodFriends gf3 = new GoodFriends();
        gf1.setName("王源");
        gf1.setHeadUrl("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E7%8E%8B%E6%BA%90&step_word=&hs=0&pn=12&spn=0&di=94480159180&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=333061036%2C1747696100&os=1048671646%2C3501469955&simid=3413486788%2C214731477&adpicid=0&lpn=0&ln=1927&fr=&fmq=1539066252598_R&fm=result&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fstatics.qdxin.cn%2Fuploadfile%2F2016%2F0316%2F20160316034716671.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bq1xtg_z%26e3BvgAzdH3Fuwfit5gAzdH3Fda8mAzdH3Fm088m_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=");
        gf2.setName("王俊凯");
        gf2.setHeadUrl("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E7%8E%8B%E4%BF%8A%E5%87%AF&step_word=&hs=0&pn=18&spn=0&di=105476082720&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=3787888502%2C1614061720&os=1912293417%2C990301394&simid=0%2C0&adpicid=0&lpn=0&ln=1884&fr=&fmq=1539066845526_R&fm=result&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=11&oriquery=&objurl=http%3A%2F%2Fpic1.win4000.com%2Fmobile%2F2018-09-20%2F5ba2fd16b268b.jpg%3Fdown&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Botg9aaa_z%26e3Bv54AzdH3F45ktsj_1jpwts_8cam8d_9_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=");
        gf3.setName("易烊千玺");
        gf3.setHeadUrl("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E6%98%93%E7%83%8A%E5%8D%83%E7%8E%BA&step_word=&hs=0&pn=4&spn=0&di=55552619020&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2793564130%2C2183903438&os=967946168%2C156288831&simid=0%2C0&adpicid=0&lpn=0&ln=1900&fr=&fmq=1539066947765_R&fm=result&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=11&oriquery=&objurl=http%3A%2F%2F04imgmini.eastday.com%2Fmobile%2F20180918%2F20180918100859_8e42862f0f09b320dbc5eff7ef57adc6_2.jpeg&fromurl=ippr_z2C%24qAzdH3FAzdH3F4tgt_z%26e3Bjwfp1wy_z%26e3Bv54AzdH3FwAzdH3F8bal8b8aabcla80_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=");
        gfList.add(gf1);
        gfList.add(gf2);
        gfList.add(gf3);
        return gfList;
    }

    public static List<BattleInfo> getBattleInfo() {
        List<BattleInfo> bi = new ArrayList<>();
        BattleInfo bi1 = new BattleInfo();
        BattleInfo bi2 = new BattleInfo();
        BattleInfo bi3 = new BattleInfo();
        BattleInfo bi4 = new BattleInfo();
        BattleInfo bi5 = new BattleInfo();
        bi1.setBattleName("突击团");
        bi1.setBattleAmount(100);
        bi2.setBattleName("精英团");
        bi2.setBattleAmount(200);
        bi3.setBattleName("挑战团");
        bi3.setBattleAmount(300);
        bi4.setBattleName("骚荡团");
        bi4.setBattleAmount(400);
        bi5.setBattleName("拉练团");
        bi5.setBattleAmount(500);
        bi.add(bi1);
        bi.add(bi2);
        bi.add(bi3);
        bi.add(bi4);
        bi.add(bi5);
        return bi;
    }
}
