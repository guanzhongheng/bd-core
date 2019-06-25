package com.xcd.bd.dao;

import com.xcd.bd.entity.TRecommRelInfo;

import java.util.List;

/**
* Author ljk
* Date  2019-06-20
*/
public interface TRecommRelInfoMapper {

    TRecommRelInfo get(String id);

    List<TRecommRelInfo> findList(TRecommRelInfo tRecommRelInfo);

    List<TRecommRelInfo> findAllList();

    int insert(TRecommRelInfo tRecommRelInfo);

    int insertBatch(List<TRecommRelInfo> tRecommRelInfos);

    TRecommRelInfo findByRecommonId(Long userId);
}