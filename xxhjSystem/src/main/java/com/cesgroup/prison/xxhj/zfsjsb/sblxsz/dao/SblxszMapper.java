package com.cesgroup.prison.xxhj.zfsjsb.sblxsz.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity.SblxszEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface SblxszMapper extends BaseDao<SblxszEntity, String> {

    void insertInfo(SblxszEntity record);

    int updateInfo(SblxszEntity record);

    int deleteByTypeId(String id);

    Page<Map<String, Object>> findByTypeCode(Map<String, Object> map);

    void deleteByTypeCodes(List<String> ids);
}
