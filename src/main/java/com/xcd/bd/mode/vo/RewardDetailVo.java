package com.xcd.bd.mode.vo;

/**
 * @Project : bd-core
 * @Description : TODO
 * @Author : lijinku
 * @Iteration : 1.0
 * @Date : 2019/6/22  9:12 PM
 * @ModificationHistory Who          When          What
 * ----------   ------------- -----------------------------------
 * lijinku          2019/06/22    create
 */
public class RewardDetailVo {
    private Long userId;
    private String userName;
    private Double avalAmount;
    private Double cnstAmount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getAvalAmount() {
        return avalAmount;
    }

    public void setAvalAmount(Double avalAmount) {
        this.avalAmount = avalAmount;
    }

    public Double getCnstAmount() {
        return cnstAmount;
    }

    public void setCnstAmount(Double cnstAmount) {
        this.cnstAmount = cnstAmount;
    }
}