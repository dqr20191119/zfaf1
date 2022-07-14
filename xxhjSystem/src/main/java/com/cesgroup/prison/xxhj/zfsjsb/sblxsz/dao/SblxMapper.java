package com.cesgroup.prison.xxhj.zfsjsb.sblxsz.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity.SblxEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface SblxMapper extends BaseDao<SblxEntity, String> {

    Page<Map<String, Object>> listAll(SblxEntity sblxEntity, Pageable pageable);

    void insertInfo(SblxEntity record);

    int updateInfo(SblxEntity record);

    SblxEntity findById(Map<String, Object> map);

    void deleteByIds(List<String> ids);
}
