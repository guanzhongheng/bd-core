package com.xcd.bd.mode.vo;

/**
 * @Project : bd-core
 * @Description : TODO
 * @Author : lijinku
 * @Iteration : 1.0
 * @Date : 2019/6/22  11:08 PM
 * @ModificationHistory Who          When          What
 * ----------   ------------- -----------------------------------
 * lijinku          2019/06/22    create
 */
public class RecommRelVo {
    private String recommUserName;
    //激活、未激活
    private String userStatus;
    private String createTime;

    public String getRecommUserName() {
        return recommUserName;
    }

    public void setRecommUserName(String recommUserName) {
        this.recommUserName = recommUserName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}