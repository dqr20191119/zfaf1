package com.cesgroup.prison.wwjg.fxdjwh.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.wwjg.fxdjwh.entity.FxdjwhEntity;

public interface FxdjwhMapper  extends BaseDao<FxdjwhEntity, String> {

	
	public FxdjwhEntity getById(String id);

	public Page<FxdjwhEntity> findList(FxdjwhEntity FxdjwhEntity, PageRequest pageRequest);

	public List<FxdjwhEntity> findAllList(FxdjwhEntity FxdjwhEntity); 
	
	public void updateStatusByIds(List<String> idList);
	
	public FxdjwhEntity getByCode(String code);
}
