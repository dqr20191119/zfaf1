package com.cesgroup.prison.yjct.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.GzzglEntity;

public interface GzzglMapper extends BaseDao<GzzglEntity, String> {

	public GzzglEntity getById(String id);

	public Page<GzzglEntity> findList(GzzglEntity gzzglEntity, PageRequest pageRequest);
	
	public List<GzzglEntity> findAllList(GzzglEntity gzzglEntity);	
	
	public void updateStatusByIds(List<String> idList);	
}
