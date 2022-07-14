package com.cesgroup.prison.yjct.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.FgglEntity;

public interface FgglMapper extends BaseDao<FgglEntity, String> {

	public FgglEntity getById(String id);

	public Page<FgglEntity> findList(FgglEntity fgglEntity, PageRequest pageRequest);

	public List<FgglEntity> findAllList(FgglEntity fgglEntity); 
	
	public void updateStatusByIds(List<String> idList);
}
