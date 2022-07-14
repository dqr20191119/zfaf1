package com.cesgroup.prison.jswh.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.jswh.entity.JswhEntity;

public interface JswhMapper extends BaseDao<JswhEntity, String> {
	
	public JswhEntity getById(String id);
	
	public Page<JswhEntity> findList(JswhEntity jswhEntity, PageRequest pageRequest);
	
	public void deleteByIds(List<String> idList);

	public List<Map<String, Object>> findAllList(JswhEntity jswhEntity);
}
