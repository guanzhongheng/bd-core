package com.xcd.bd.service;

import com.xcd.bd.entity.TUserInfo;

import java.util.List;

/**
* Author ljk
* Date  2019-06-20
*/
public interface IUserInfoService {
    TUserInfo get(String id);

    List<TUserInfo> findList(TUserInfo tUserInfo);

    List<TUserInfo> findAllList();

    int insert(TUserInfo tUserInfo);

    int insertBatch(List<TUserInfo> tUserInfos);

    int update(TUserInfo tUserInfo);

    int delete(TUserInfo tUserInfo);

}
