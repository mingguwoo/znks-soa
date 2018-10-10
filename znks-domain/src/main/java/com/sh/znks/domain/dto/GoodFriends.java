package com.sh.znks.domain.dto;

/**
 * Created by wuminggu on 2018/10/9.
 */
public class GoodFriends {
    private String userId;              //UnionID
    private String name;                //姓名
    private String headUrl;             //头像链接

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
