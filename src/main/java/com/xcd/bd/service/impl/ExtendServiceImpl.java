package com.xcd.bd.service.impl;

import com.xcd.bd.dao.TAcctInfoMapper;
import com.xcd.bd.dao.TExportRecordMapper;
import com.xcd.bd.dao.TExtendMapper;
import com.xcd.bd.dao.TUserInfoMapper;
import com.xcd.bd.entity.TAcctInfo;
import com.xcd.bd.entity.TExportRecord;
import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.mode.vo.RecommRelVo;
import com.xcd.bd.mode.vo.RewardDetailVo;
import com.xcd.bd.mode.vo.RewardVo;
import com.xcd.bd.service.IExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project : bd-core
 * @Description : TODO
 * @Author : lijinku
 * @Iteration : 1.0
 * @Date : 2019/6/22  9:34 PM
 * @ModificationHistory Who          When          What
 * ----------   ------------- -----------------------------------
 * lijinku          2019/06/22    create
 */

@Service
public class ExtendServiceImpl implements IExtendService {
    @Autowired
    private TExtendMapper extendMapper;
    @Autowired
    private TAcctInfoMapper acctInfoMapper;
    @Autowired
    private TUserInfoMapper userInfoMapper;
    @Autowired
    private TExportRecordMapper exportRecordMapper;

    @Override
    public List<RewardDetailVo> selectGtZeroRewadsByUserStatus(char status) {
        List<RewardDetailVo> rewardDetailVos = extendMapper.selectGtZeroRewadsByStatus(status);
        if(rewardDetailVos!=null && !rewardDetailVos.isEmpty()){
            Date current = new Date();
            List<TExportRecord> exRecords = rewardDetailVos.stream().map(rd -> {
                TExportRecord record = new TExportRecord();
                record.setUserId(rd.getUserId());
                record.setAmount(rd.getAvalAmount());
                record.setStatus('0');
                record.setCreateTime(current);
                return record;
            }).collect(Collectors.toList());
            exportRecordMapper.insertBatch(exRecords);
        }
        return extendMapper.selectGtZeroRewadsByStatus(status);
    }


    @Override
    public List<RecommRelVo> findRecommInfoByUserId(Long userId) {
        return extendMapper.findRecommInfoByUserId(userId);
    }


    @Override
    public List<TUserInfo> findUnshipUserInf() {
        List<TUserInfo> usLst = userInfoMapper.findUserInfoByShipStatus('0');
        if (usLst != null && !usLst.isEmpty()) {
            Date current = new Date();
            List<Long> usIdList = usLst.stream().map(us -> us.getUserId()).collect(Collectors.toList());
            userInfoMapper.updateShipStatusBatchByPrimaryKey(usIdList, '1', current);
        }
        return usLst;
    }


    @Override
    public List<RewardDetailVo> selectListByUserStatus(String status) {
        if(StringUtils.isEmpty(status)){
            return extendMapper.selectGtZeroRewadsByStatus(null);
        } else {
            return extendMapper.selectGtZeroRewadsByStatus(status.toCharArray()[0]);
        }

    }

    @Override
    public List<RewardVo> findUndealRewards() {
        return extendMapper.findUndealRewardsByStatus('0');
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    @Override
    public int dealTransaction(Long id, Double amount, Long userId) {
        Date current = new Date();
        TExportRecord record = new TExportRecord();
        record.setId(id);
        record.setStatus('1');
        record.setUpdateTime(current);
        int res = exportRecordMapper.updateStatusById(record);
        if (res > 0) {
            //扣除余额
            TAcctInfo acctInfo = new TAcctInfo();
            acctInfo.setUpdateTime(current);
            acctInfo.setAvalAmount(-amount);
            acctInfo.setUserId(userId);
            res = acctInfoMapper.update(acctInfo);
        }
        return res;
    }


}