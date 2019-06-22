package com.xcd.bd.service.impl;

import com.xcd.bd.dao.TExtendMapper;
import com.xcd.bd.mode.vo.RewardDetailVo;
import com.xcd.bd.service.IExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project : bd-core
 * @Description : TODO
 * @Author : lijinku
 * @Iteration : 1.0
 * @Date : 2019/6/22  9:34 PM
 * @ModificationHistory Who          When          What
 * ----------   ------------- -----------------------------------
 * lijinku          2019/06/22    create
 */

@Service
public class ExtendServiceImpl implements IExtendService {
    @Autowired
    private TExtendMapper extendMapper;
    @Override
    public List<RewardDetailVo> selectRewadsByGtZero() {
        return extendMapper.selectRewadsByGtZero();
    }
}