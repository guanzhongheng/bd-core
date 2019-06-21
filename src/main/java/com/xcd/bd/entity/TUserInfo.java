package com.xcd.bd.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Author ljk
 * Date  2019-06-20
 */
public class TUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String userName;
    private String password;
    private String address;
    private String invCode;
    private String attachUrl;
    private Date createTime;
    private Date updateTime;


    public TUserInfo() {
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode;
    }

    public String getInvCode() {
        return invCode;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

}