package com.xcd.bd.service.impl;

import com.xcd.bd.dao.TUserInfoMapper;
import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* Author ljk
* Date  2019-06-20
*/
@Service
public class TUserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private TUserInfoMapper tUserInfoMapper;

    @Override
    public TUserInfo get(String id){
        return tUserInfoMapper.get(id);
    }

    @Override
    public List<TUserInfo> findList(TUserInfo tUserInfo) {
        return tUserInfoMapper.findList(tUserInfo);
    }

    @Override
    public List<TUserInfo> findAllList() {
        return tUserInfoMapper.findAllList();
    }

    @Override
    public int insert(TUserInfo tUserInfo) {
        return tUserInfoMapper.insert(tUserInfo);
    }

    @Override
    public int insertBatch(List<TUserInfo> tUserInfos){
        return tUserInfoMapper.insertBatch(tUserInfos);
    }

    @Override
    public int update(TUserInfo tUserInfo) {
        return tUserInfoMapper.update(tUserInfo);
    }

    @Override
    public int delete(TUserInfo tUserInfo) {
        return tUserInfoMapper.delete(tUserInfo);
    }

}
