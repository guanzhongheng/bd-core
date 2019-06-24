package com.xcd.bd.dao;

import com.xcd.bd.mode.vo.RecommRelVo;
import com.xcd.bd.mode.vo.RewardDetailVo;
import com.xcd.bd.mode.vo.RewardVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TExtendMapper {

    List<RewardDetailVo> selectGtZeroRewadsByStatus(@Param("status") Character status);

    List<RecommRelVo> findRecommInfoByUserId(@Param("userId") Long userId);

    List<RewardVo> findUndealRewardsByStatus(@Param("status") Character status);
}
