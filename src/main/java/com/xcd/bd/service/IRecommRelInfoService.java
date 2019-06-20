package com.xcd.bd.service;

import com.xcd.bd.entity.TRecommRelInfo;

import java.util.List;

/**
* Author ljk
* Date  2019-06-20
*/
public interface IRecommRelInfoService {
    TRecommRelInfo get(String id);

    List<TRecommRelInfo> findList(TRecommRelInfo tRecommRelInfo);

    List<TRecommRelInfo> findAllList();

    int insert(TRecommRelInfo tRecommRelInfo);

    int insertBatch(List<TRecommRelInfo> tRecommRelInfos);

    int update(TRecommRelInfo tRecommRelInfo);

    int delete(TRecommRelInfo tRecommRelInfo);

}
