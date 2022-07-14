package com.cesgroup.prison.yjct.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.YljhEntity;

public interface YljhMapper extends BaseDao<YljhEntity, String> {

	public YljhEntity getById(YljhEntity yljhEntity);

	public Page<YljhEntity> findList(YljhEntity yljhEntity, PageRequest pageRequest);
	
	public List<YljhEntity> findAllList(YljhEntity yljhEntity);
}
