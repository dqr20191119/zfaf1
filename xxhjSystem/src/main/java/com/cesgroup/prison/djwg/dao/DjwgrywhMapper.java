package com.cesgroup.prison.djwg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.djwg.entity.DjwgrywhEntity;

public interface DjwgrywhMapper  extends BaseDao<DjwgrywhEntity, String> {

	public DjwgrywhEntity getById(String id);

	public Page<DjwgrywhEntity> findList(DjwgrywhEntity djwgrywhEntity, PageRequest pageRequest);

	public List<DjwgrywhEntity> findAllList(DjwgrywhEntity djwgrywhEntity); 
	
	public void updateStatusByIds(List<String> idList);
	
	public List<DjwgrywhEntity> findRywh(DjwgrywhEntity djwgrywhEntity); 
	
	public List<Map<String ,Object>> findMax(Map<String ,Object> map); 
	
	public List<DjwgrywhEntity> findfej(DjwgrywhEntity djwgrywhEntity); 
}
