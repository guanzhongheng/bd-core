package com.xcd.bd.dao;

import com.xcd.bd.mode.vo.RecommRelVo;
import com.xcd.bd.mode.vo.RewardDetailVo;

import java.util.List;

public interface TExtendMapper {

    List<RewardDetailVo> selectGtZeroRewadsByStatus(Character status);

    List<RecommRelVo> findRecommInfoByUserId(Long userId);
}
