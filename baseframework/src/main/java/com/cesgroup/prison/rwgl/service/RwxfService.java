package com.cesgroup.prison.rwgl.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.rwgl.entity.RwxfEntity;

public interface RwxfService extends IBaseCRUDService<RwxfEntity, String> {
	
	public RwxfEntity getById(String id);

	public Page<RwxfEntity> findList(RwxfEntity rwxfEntity, PageRequest pageRequest);

	public List<RwxfEntity> findAllList(RwxfEntity rwxfEntity);
	
	public RwxfEntity saveOrUpdate(RwxfEntity rwxfEntity);
	
	public void deleteByIds(String ids);
	
	public void updateStatusByIds(String ids,UserBean user);

	public void updatePlanStatus(RwxfEntity rwxfEntity);

}
