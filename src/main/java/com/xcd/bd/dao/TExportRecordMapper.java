package com.xcd.bd.dao;

import com.xcd.bd.entity.TExportRecord;

import java.util.List;

public interface TExportRecordMapper {

    int insertBatch(List<TExportRecord> exportRecords);
}
