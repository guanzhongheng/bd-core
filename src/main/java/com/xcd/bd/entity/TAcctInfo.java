package com.xcd.bd.entity;

import java.io.Serializable;
import java.util.Date;

/**
* Author ljk
* Date  2019-06-20
*/
public class TAcctInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Double avalAmount;
    private Double totalAmount;
    private Double unAvalAmount;
    private Double rate;
    private Integer recommSum;
    private Date createTime;
    private Date updateTime;


    public TAcctInfo(){
    }

    public void setUserId (Long userId) {this.userId = userId;} 
    public Long getUserId(){ return userId;} 
    public void setAvalAmount (Double avalAmount) {this.avalAmount = avalAmount;} 
    public Double getAvalAmount(){ return avalAmount;} 
    public void setTotalAmount (Double totalAmount) {this.totalAmount = totalAmount;} 
    public Double getTotalAmount(){ return totalAmount;}

    public Double getUnAvalAmount() {
        return unAvalAmount;
    }

    public void setUnAvalAmount(Double unAvalAmount) {
        this.unAvalAmount = unAvalAmount;
    }

    public void setRate (Double rate) {this.rate = rate;}
    public Double getRate(){ return rate;} 
    public void setRecommSum (Integer recommSum) {this.recommSum = recommSum;} 
    public Integer getRecommSum(){ return recommSum;} 
    public void setCreateTime (Date createTime) {this.createTime = createTime;} 
    public Date getCreateTime(){ return createTime;} 
    public void setUpdateTime (Date updateTime) {this.updateTime = updateTime;} 
    public Date getUpdateTime(){ return updateTime;} 

}