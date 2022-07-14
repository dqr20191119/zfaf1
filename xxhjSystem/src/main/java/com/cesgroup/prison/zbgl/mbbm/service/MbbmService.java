package com.cesgroup.prison.zbgl.mbbm.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zbgl.lbgl.entity.LbglEntity;
import com.cesgroup.prison.zbgl.mbbm.entity.MbbmEntity;

public interface MbbmService extends IBaseCRUDService<MbbmEntity, String> {
	
	public MbbmEntity getById(String id);

	public Page<MbbmEntity> findList(MbbmEntity mbbmEntity, PageRequest pageRequest);
	
	public List<Map<String, Object>> findAllList(MbbmEntity mbbmEntity);
	
	public void deleteByIds(String ids);

	public void deleteByConditions(MbbmEntity mbbmEntity);   //根据模板ID删除记录

	public void updateDmdZtById(MbbmEntity mbbmEntity);
	
}
