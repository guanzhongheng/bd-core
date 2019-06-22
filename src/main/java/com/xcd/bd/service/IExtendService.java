package com.xcd.bd.service;

import com.xcd.bd.mode.vo.RewardDetailVo;

import java.util.List;

public interface IExtendService {

    List<RewardDetailVo> selectRewadsByGtZero();
}
