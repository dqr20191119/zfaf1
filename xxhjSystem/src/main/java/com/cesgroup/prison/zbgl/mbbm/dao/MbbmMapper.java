package com.cesgroup.prison.zbgl.mbbm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.mbbm.entity.MbbmEntity;

public interface MbbmMapper extends BaseDao<MbbmEntity, String> {

	public MbbmEntity getById(String id);
	
	public Page<MbbmEntity> findList(MbbmEntity mbbmEntity, PageRequest pageRequest);

	public List<Map<String, Object>> findAllList(MbbmEntity mbbmEntity); 
	
	public void updateStatusByIds(List<String> idList);
	
	public void deleteByConditions(MbbmEntity mbbmEntity);

	public void updateDmdZtById(MbbmEntity mbbmEntity);

	
}
