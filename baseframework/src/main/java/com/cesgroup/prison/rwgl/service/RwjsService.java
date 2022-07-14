package com.cesgroup.prison.rwgl.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.rwgl.entity.RwjsEntity;

public interface RwjsService extends IBaseCRUDService<RwjsEntity, String> {
	
	public RwjsEntity getById(String id);

	public Page<RwjsEntity> findList(RwjsEntity rwjsEntity, PageRequest pageRequest);
	
	public Page<RwjsEntity> findDbList(RwjsEntity rwjsEntity, PageRequest pageRequest);

	public List<RwjsEntity> findAllList(RwjsEntity rwjsEntity);
	
	public RwjsEntity saveOrUpdate(RwjsEntity rwjsEntity);
	
	public void deleteByIds(String ids);
	
	public void updateStatusByIds(String ids,UserBean user);

	public void updatePlanStatus(RwjsEntity rwjsEntity);
	
	public List<Map<String, Object>> searchSwdb(UserBean user);
	
	public List<Map<String, Object>> searchJwqk(UserBean user);

}
