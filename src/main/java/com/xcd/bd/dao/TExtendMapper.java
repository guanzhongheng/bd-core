package com.xcd.bd.dao;

import com.xcd.bd.mode.vo.RewardDetailVo;

import java.util.List;

public interface TExtendMapper {

    List<RewardDetailVo> selectRewadsByGtZero();
}
