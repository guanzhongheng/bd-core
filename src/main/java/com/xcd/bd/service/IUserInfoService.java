package com.xcd.bd.service;

import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.mode.vo.UserVo;

import java.util.List;

/**
 * Author ljk
 * Date  2019-06-20
 */
public interface IUserInfoService {

    List<TUserInfo> findList(TUserInfo tUserInfo);

    List<TUserInfo> findListByStatus(String status);

    List<TUserInfo> findAllList();

    int insert(UserVo vo);

    int insertBatch(List<TUserInfo> tUserInfos);

    int update(UserVo vo);

    int delete(TUserInfo tUserInfo);

    TUserInfo findByUserName(String userName);


    int activeUser(UserVo vo);

}
