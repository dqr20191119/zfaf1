package com.cesgroup.prison.callNames.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.callNames.entity.CallNamesUndoneEntity;

public interface CallNamesUndoneMapper extends BaseDao<CallNamesUndoneEntity, String> {
	public Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);
}
