package com.cesgroup.prison.xwzc.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xwzc.entity.XwzcEntity;

public interface XwzcMapper extends BaseDao<XwzcEntity, String> {
	
	public Page<XwzcEntity> searchXwzcList(XwzcEntity xwzcEntity, PageRequest pageRequest);
	
	public Map<String, Object> searchXwzcCount(Map<String, Object> map);
}
