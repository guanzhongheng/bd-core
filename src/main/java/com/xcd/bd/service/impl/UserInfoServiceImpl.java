package com.xcd.bd.service.impl;

import com.xcd.bd.constant.Constants;
import com.xcd.bd.dao.TAcctInfoMapper;
import com.xcd.bd.dao.TRecommRelInfoMapper;
import com.xcd.bd.dao.TUserInfoMapper;
import com.xcd.bd.entity.TAcctInfo;
import com.xcd.bd.entity.TRecommRelInfo;
import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.mode.vo.UserVo;
import com.xcd.bd.service.IUserInfoService;
import com.xcd.bd.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Author ljk
 * Date  2019-06-20
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private TUserInfoMapper tUserInfoMapper;
    @Autowired
    private TRecommRelInfoMapper tRecommRelInfoMapper;
    @Autowired
    private TAcctInfoMapper tAcctInfoMapper;

    @Override
    public List<TUserInfo> findList(TUserInfo tUserInfo) {
        return tUserInfoMapper.findList(tUserInfo);
    }

    @Override
    public List<TUserInfo> findListByStatus(String status) {
        if (StringUtils.isEmpty(status)) {
            return tUserInfoMapper.findListByStatus(null);
        } else {
            return tUserInfoMapper.findListByStatus(status.charAt(0));
        }
    }

    @Override
    public List<TUserInfo> findAllList() {
        return tUserInfoMapper.findAllList();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public int insert(UserVo vo) {
        Date current = new Date();
        TUserInfo tUserInfo = new TUserInfo();
        tUserInfo.setUserName(vo.getUserName());
        tUserInfo.setPassword(Md5Util.encryptionPassWord(vo.getUserName(), vo.getPassword()));
        tUserInfo.setShipStatus('0');
        tUserInfo.setStatus('0');
        tUserInfo.setUserType('1');
        tUserInfo.setCreateTime(current);
        int res = tUserInfoMapper.insert(tUserInfo);
        if (res > 0) {
            TAcctInfo acct = new TAcctInfo();
            acct.setUserId(tUserInfo.getUserId());
            acct.setAvalAmount(Constants.BSC_AMOUNT);
            acct.setTotalAmount(Constants.BSC_AMOUNT);
            acct.setUnAvalAmount(0d);
            acct.setRecommSum(0);
            acct.setRate(Constants.BSC_CNST_RATE);
            acct.setCreateTime(current);
            tAcctInfoMapper.insert(acct);
            if (!StringUtils.isEmpty(vo.getInvCode())) {
                Long recommUserId = tUserInfoMapper.findUserIdByInvCode(vo.getInvCode());
                if (recommUserId != null) {
                    TRecommRelInfo recommRel = new TRecommRelInfo();
                    recommRel.setRecommUserId(tUserInfo.getUserId());
                    recommRel.setUserId(recommUserId);
                    recommRel.setAmount(Constants.BSC_REWARD_AMOUNT);
                    recommRel.setCreateTime(current);
                    res = tRecommRelInfoMapper.insert(recommRel);
                    //更新推荐奖励
                    TAcctInfo tAcctInfo = new TAcctInfo();
                    tAcctInfo.setUserId(recommUserId);
                    tAcctInfo.setRecommSum(1);
                    tAcctInfo.setUnAvalAmount(Constants.BSC_REWARD_AMOUNT);
                    tAcctInfo.setUpdateTime(current);
                    if (res > 0) {
                        res = tAcctInfoMapper.update(tAcctInfo);
                    }
                }

            }
        }

        return res;
    }

    @Override
    public int insertBatch(List<TUserInfo> tUserInfos) {
        return tUserInfoMapper.insertBatch(tUserInfos);
    }

    @Override
    public int update(UserVo vo) {
        Date current = new Date();
        TUserInfo us = new TUserInfo();
        us.setUserId(vo.getUserId());
        us.setRealName(vo.getRealName());
        us.setPhone(vo.getPhone());
        us.setPassword(vo.getPassword());
        us.setRecieverName(vo.getRecieverName());
        us.setRecieverAddress(vo.getRecieverAddress());
        us.setRecieverPhone(vo.getRecieverPhone());
        us.setAttachUrl(vo.getAttachUrl());
        us.setStatus(vo.getStatus());
        us.setInvCode(vo.getInvCode());
        us.setUpdateTime(current);
        return tUserInfoMapper.update(us);
    }

    @Override
    public int delete(TUserInfo tUserInfo) {
        return tUserInfoMapper.delete(tUserInfo);
    }

    @Override
    public TUserInfo findByUserName(String userName) {
        return tUserInfoMapper.findByUserName(userName);
    }

    @Override
    public int activeUser(UserVo vo) {
        Date current = new Date();
        TUserInfo us = new TUserInfo();
        us.setUserId(vo.getUserId());
        us.setStatus(vo.getStatus());
        us.setInvCode(vo.getInvCode());
        us.setUpdateTime(current);
        int res = tUserInfoMapper.update(us);
        if(res>0){
           TRecommRelInfo relInfo = tRecommRelInfoMapper.findByRecommonId(vo.getUserId());
           if(relInfo!=null){
               TAcctInfo tAcctInfo = new TAcctInfo();
               tAcctInfo.setUserId(relInfo.getUserId());
               tAcctInfo.setAvalAmount(relInfo.getAmount());
               tAcctInfo.setTotalAmount(relInfo.getAmount());
               tAcctInfo.setUpdateTime(current);
               res = tAcctInfoMapper.update(tAcctInfo);
           }
        }
        return res;
    }

}
