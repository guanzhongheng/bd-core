package com.xcd.bd.mode.vo;

/**
 * @Project : bd-core
 * @Description : TODO
 * @Author : lijinku
 * @Iteration : 1.0
 * @Date : 2019/6/24  2:01 PM
 * @ModificationHistory Who          When          What
 * ----------   ------------- -----------------------------------
 * lijinku          2019/06/24    create
 */
public class AccountVo {
    private Double avalAmount;
    private Double avalCnstAmount;
    private Double totalAmount;

    public Double getAvalAmount() {
        return avalAmount;
    }

    public void setAvalAmount(Double avalAmount) {
        this.avalAmount = avalAmount;
    }

    public Double getAvalCnstAmount() {
        return avalCnstAmount;
    }

    public void setAvalCnstAmount(Double avalCnstAmount) {
        this.avalCnstAmount = avalCnstAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}