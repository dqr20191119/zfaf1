package com.cesgroup.prison.xxhj.zfsjsb.sjsb.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.zfsjsb.sjsb.entity.SjsbEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface SjsbMapper extends BaseDao<SjsbEntity, String> {

    Page<Map<String, Object>> listAll(SjsbEntity sblxEntity, Pageable pageable);

    Page<Map<String, Object>> findByTimeAndDprtmntId(Map<String, Object> map);

    void deleteReportData(Map<String, Object> record);

    void deleteReportDataByEntity(SjsbEntity record);

    void insertInfo(SjsbEntity record);

    List<String> getAllReportDprt(Map<String, Object> record);

    List<String> getAllReportDprtByCprTime(Map<String, Object> record);

    Page<Map<String, Object>> getReportSjhz(Map<String, Object> record);
}
