package com.cesgroup.prison.securityCheck.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.securityCheck.entity.SecurityCheck;

public interface SecurityCheckDao extends BaseDao<SecurityCheck, String> {
	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);
}