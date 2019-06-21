package com.xcd.bd.dao;

import com.xcd.bd.entity.TUserInfo;

import java.util.List;

/**
* Author ljk
* Date  2019-06-20
*/
public interface TUserInfoMapper {

    TUserInfo get(String id);

    List<TUserInfo> findList(TUserInfo tUserInfo);

    List<TUserInfo> findAllList();

    int insert(TUserInfo tUserInfo);

    int insertBatch(List<TUserInfo> tUserInfos);

    int update(TUserInfo tUserInfo);

    int delete(TUserInfo tUserInfo);

    TUserInfo findByUserName(String userName);

    Long findUserIdByInvCode(String invCode);

}