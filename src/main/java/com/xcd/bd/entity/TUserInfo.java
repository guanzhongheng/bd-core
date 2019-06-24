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
    private String realName;
    //联系人电话
    private String phone;
    private String recieverAddress;
    //收货人姓名
    private String recieverName;
    //收货人联系电话
    private String recieverPhone;
    private String invCode;
    private String attachUrl;
    private Character status;
    //发货状态
    private Character shipStatus;
    private Date createTime;
    private Date updateTime;
    private Character userType;

    public Character getUserType() {
        return userType;
    }

    public void setUserType(Character userType) {
        this.userType = userType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Character getShipStatus() {
        return shipStatus;
    }

    public void setShipStatus(Character shipStatus) {
        this.shipStatus = shipStatus;
    }

    public String getRecieverPhone() {
        return recieverPhone;
    }

    public void setRecieverPhone(String recieverPhone) {
        this.recieverPhone = recieverPhone;
    }

    public String getRecieverAddress() {
        return recieverAddress;
    }

    public void setRecieverAddress(String recieverAddress) {
        this.recieverAddress = recieverAddress;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
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