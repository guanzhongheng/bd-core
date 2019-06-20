package com.xcd.bd.entity;

import java.io.Serializable;
import java.util.Date;

/**
* Author ljk
* Date  2019-06-20
*/
public class TRecommRelInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long recommUserId;
    private Date createTime;


    public TRecommRelInfo(){
    }

    public void setUserId (Long userId) {this.userId = userId;} 
    public Long getUserId(){ return userId;} 
    public void setRecommUserId (Long recommUserId) {this.recommUserId = recommUserId;} 
    public Long getRecommUserId(){ return recommUserId;} 
    public void setCreateTime (Date createTime) {this.createTime = createTime;} 
    public Date getCreateTime(){ return createTime;} 

}