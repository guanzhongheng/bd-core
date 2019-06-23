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
import com.xcd.bd.service.IExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    @Override
    public List<RewardDetailVo> selectGtZeroRewadsByUserStatus(char status) {
        List<RewardDetailVo> rewardDetailVos = extendMapper.selectGtZeroRewadsByStatus(status);
        if(rewardDetailVos!=null && !rewardDetailVos.isEmpty()){
            Date current = new Date();
            List<TExportRecord> exRecords = new ArrayList<>(rewardDetailVos.size());
            List<TAcctInfo> acctList = rewardDetailVos.stream().map(rd -> {
                TExportRecord record = new TExportRecord();
                record.setUserId(rd.getUserId());
                record.setAmount(rd.getAvalAmount());
                record.setCreateTime(current);
                exRecords.add(record);
                TAcctInfo acctInfo = new TAcctInfo();
                acctInfo.setUpdateTime(current);
                acctInfo.setAvalAmount(-rd.getAvalAmount());
                acctInfo.setUserId(rd.getUserId());
                return acctInfo;
            }).collect(Collectors.toList());
            acctInfoMapper.updateBatch(acctList);
            exportRecordMapper.insertBatch(exRecords);
        }
        return rewardDetailVos;
    }

    @Override
    public List<RecommRelVo> findRecommInfoByUserId(Long userId) {
        return extendMapper.findRecommInfoByUserId(userId);
    }

    @Override
    public List<TUserInfo> findUnshipUserInf() {
        List<TUserInfo> usLst = userInfoMapper.findUserInfoByShipStatus('0');
        if(usLst!=null && !usLst.isEmpty()){
            Date current = new Date();
            List<Long> usIdList = usLst.stream().map(us -> us.getUserId()).collect(Collectors.toList());
            userInfoMapper.updateShipStatusBatchByPrimaryKey(usIdList,'1',current);
        }
        return usLst;
    }

    
}