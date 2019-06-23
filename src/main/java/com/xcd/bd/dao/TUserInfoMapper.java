package com.xcd.bd.dao;

import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.mode.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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

    List<TUserInfo> findUserInfoByShipStatus(char shipStatus);

    int updateShipStatusBatchByPrimaryKey(@Param("idList") List<Long> idList,@Param("shipStatus") Character shipStatsu,@Param("updateTime") Date updateTime);
}