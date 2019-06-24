package com.xcd.bd.service;

import com.xcd.bd.entity.TUserInfo;
import com.xcd.bd.mode.vo.RecommRelVo;
import com.xcd.bd.mode.vo.RewardDetailVo;
import com.xcd.bd.mode.vo.RewardVo;

import java.util.List;

public interface IExtendService {

    /**
     * @Description: 查询可用bsc大于零的所有奖励信息，并扣除可用余额
     * @Param: [status] 0-待激活；1-正常用户
     * @return: java.util.List<com.xcd.bd.mode.vo.RewardDetailVo>
     * @Author: lijinku
     * @Iteration : 1.0
     * @Date: 2019/6/22 11:16 PM
     */
    List<RewardDetailVo> selectGtZeroRewadsByUserStatus(char status);

    /**
     * @Description: 查看该用户的所有推荐关系
     * @Param: [userId]
     * @return: java.util.List<com.xcd.bd.mode.vo.RecommRelVo>
     * @Author: lijinku
     * @Iteration : 1.0
     * @Date: 2019/6/22 11:16 PM
     */
    List<RecommRelVo> findRecommInfoByUserId(Long userId);

    /**
     * @Description: 查询未发货的用户信息，并设置发货状态为已发货
     * @Param: []
     * @return: java.util.List<com.xcd.bd.entity.TUserInfo>
     * @Author: lijinku
     * @Iteration : 1.0
     * @Date: 2019/6/23 12:35 AM
     */
    List<TUserInfo> findUnshipUserInf();


    List<RewardDetailVo> selectListByUserStatus(String status);
    /**
     * @Description: 获取未处理的奖金信息
     * @Param: []
     * @return: java.util.List<com.xcd.bd.mode.vo.RewardVo>
     * @Author: lijinku
     * @Iteration : 1.0
     * @Date: 2019/6/24 3:46 PM
     */
    List<RewardVo> findUndealRewards();

    /**
     * @Description: 处理交易
     * @Param: [id]
     * @return: int
     * @Author: lijinku
     * @Iteration : 1.0
     * @Date: 2019/6/24 4:01 PM
     */
    int dealTransaction(Long id,Double amount,Long userId);



}
