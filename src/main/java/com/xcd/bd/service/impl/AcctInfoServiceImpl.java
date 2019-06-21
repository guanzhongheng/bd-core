package com.xcd.bd.service.impl;

import com.xcd.bd.dao.TAcctInfoMapper;
import com.xcd.bd.entity.TAcctInfo;
import com.xcd.bd.service.IAcctInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Author ljk
* Date  2019-06-20
*/
@Service
public class AcctInfoServiceImpl implements IAcctInfoService {
    @Autowired
    private TAcctInfoMapper tAcctInfoMapper;

    @Override
    public TAcctInfo get(String id){
        return tAcctInfoMapper.get(id);
    }

    @Override
    public List<TAcctInfo> findList(TAcctInfo tAcctInfo) {
        return tAcctInfoMapper.findList(tAcctInfo);
    }

    @Override
    public List<TAcctInfo> findAllList() {
        return tAcctInfoMapper.findAllList();
    }

    @Override
    public int insert(TAcctInfo tAcctInfo) {
        return tAcctInfoMapper.insert(tAcctInfo);
    }

    @Override
    public int insertBatch(List<TAcctInfo> tAcctInfos){
        return tAcctInfoMapper.insertBatch(tAcctInfos);
    }

    @Override
    public int update(TAcctInfo tAcctInfo) {
        return tAcctInfoMapper.update(tAcctInfo);
    }

    @Override
    public int delete(TAcctInfo tAcctInfo) {
        return tAcctInfoMapper.delete(tAcctInfo);
    }

}
