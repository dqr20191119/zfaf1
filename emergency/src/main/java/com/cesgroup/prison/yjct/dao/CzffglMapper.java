package com.cesgroup.prison.yjct.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.CzffglEntity;

public interface CzffglMapper extends BaseDao<CzffglEntity, String> {

	public CzffglEntity getById(String id);

	public Page<CzffglEntity> findList(CzffglEntity czffglEntity, PageRequest pageRequest);

	public List<CzffglEntity> findAllList(CzffglEntity czffglEntity); 
	
	public void updateStatusByIds(List<String> idList);
}
