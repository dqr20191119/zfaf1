package com.cesgroup.prison.jswh.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.jswh.entity.JswhEntity;


public interface JswhService extends IBaseCRUDService<JswhEntity, String> {

	public JswhEntity getById(String id);

	public Page<JswhEntity> findList(JswhEntity jswhEntity, PageRequest pageRequest);

	public void saveOrUpdate(List<Map<String, Object>> jswhs) throws Exception;

	public void deleteByIds(String ids);

	public List<Map<String, Object>> findAllList(JswhEntity jswhEntity);

}
