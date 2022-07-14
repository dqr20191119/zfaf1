package com.cesgroup.prison.zbgl.lbgl.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.lbgl.entity.LbglEntity;

public interface LbglMapper extends BaseDao<LbglEntity, String> {

	public LbglEntity getById(String id);

	public Page<LbglEntity> findList(LbglEntity lbglEntity, PageRequest pageRequest);

	public List<LbglEntity> findAllList(LbglEntity lbglEntity); 
	
	public void updateStatusByIds(List<String> idList);
	
}
