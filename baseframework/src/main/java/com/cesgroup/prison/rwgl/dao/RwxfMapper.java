package com.cesgroup.prison.rwgl.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.rwgl.entity.RwxfEntity;

public interface RwxfMapper extends BaseDao<RwxfEntity, String> {

	public RwxfEntity getById(String id);
	
	public Page<RwxfEntity> findList(RwxfEntity rwxfEntity, PageRequest pageRequest);

	public List<RwxfEntity> findAllList(RwxfEntity rwxfEntity);	
	
	public void updateStatus(String id);
	
}
