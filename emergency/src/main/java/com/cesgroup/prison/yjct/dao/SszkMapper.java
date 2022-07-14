package com.cesgroup.prison.yjct.dao;

import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.SszkEntity;

public interface SszkMapper extends BaseDao<SszkEntity, String> {

	public Map<String, Object> findConfig(Map<String, Object> paramMap);
}
