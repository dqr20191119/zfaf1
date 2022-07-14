package com.cesgroup.prison.wwjg.sjlywh.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.wwjg.sjlywh.entity.SjlywhEntity;

public interface SjlywhMapper  extends BaseDao<SjlywhEntity, String> {

	
	public SjlywhEntity getById(String id);

	public Page<SjlywhEntity> findList(SjlywhEntity sjlywhEntity, PageRequest pageRequest);

	public List<SjlywhEntity> findAllList(SjlywhEntity sjlywhEntity); 
	
	public void updateStatusByIds(List<String> idList);
	
	public SjlywhEntity getByCode(String code);
}
