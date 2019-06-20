package com.xcd.bd.dao;

import com.xcd.bd.entity.TAcctInfo;

import java.util.List;

/**
* Author ljk
* Date  2019-06-20
*/
public interface TAcctInfoMapper {

    TAcctInfo get(String id);

    List<TAcctInfo> findList(TAcctInfo tAcctInfo);

    List<TAcctInfo> findAllList();

    int insert(TAcctInfo tAcctInfo);

    int insertBatch(List<TAcctInfo> tAcctInfos);

    int update(TAcctInfo tAcctInfo);

    int delete(TAcctInfo tAcctInfo);

}