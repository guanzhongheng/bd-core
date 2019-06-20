package com.xcd.bd.service.impl;

import com.xcd.bd.dao.TRecommRelInfoMapper;
import com.xcd.bd.entity.TRecommRelInfo;
import com.xcd.bd.service.IRecommRelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Author ljk
* Date  2019-06-20
*/
@Service
public class TRecommRelInfoServiceImpl implements IRecommRelInfoService {
    @Autowired
    private TRecommRelInfoMapper tRecommRelInfoMapper;

    @Override
    public TRecommRelInfo get(String id){
        return tRecommRelInfoMapper.get(id);
    }

    @Override
    public List<TRecommRelInfo> findList(TRecommRelInfo tRecommRelInfo) {
        return tRecommRelInfoMapper.findList(tRecommRelInfo);
    }

    @Override
    public List<TRecommRelInfo> findAllList() {
        return tRecommRelInfoMapper.findAllList();
    }

    @Override
    public int insert(TRecommRelInfo tRecommRelInfo) {
        return tRecommRelInfoMapper.insert(tRecommRelInfo);
    }

    @Override
    public int insertBatch(List<TRecommRelInfo> tRecommRelInfos){
        return tRecommRelInfoMapper.insertBatch(tRecommRelInfos);
    }

    @Override
    public int update(TRecommRelInfo tRecommRelInfo) {
        return tRecommRelInfoMapper.update(tRecommRelInfo);
    }

    @Override
    public int delete(TRecommRelInfo tRecommRelInfo) {
        return tRecommRelInfoMapper.delete(tRecommRelInfo);
    }

}
