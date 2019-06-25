package com.xcd.bd.dao;

import com.xcd.bd.entity.TExportRecord;
import com.xcd.bd.mode.vo.RewardVo;

import java.util.List;

public interface TExportRecordMapper {

    int insertBatch(List<TExportRecord> exportRecords);

    int updateStatusById(TExportRecord exportRecord);

    List<RewardVo> findUndealRewardsByStatus(Character status);
}
