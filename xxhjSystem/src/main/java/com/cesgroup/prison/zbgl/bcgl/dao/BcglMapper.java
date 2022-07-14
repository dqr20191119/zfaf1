package com.cesgroup.prison.zbgl.bcgl.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.bcgl.entity.BcglEntity;

public interface BcglMapper extends BaseDao<BcglEntity, String> {

	public BcglEntity getById(String id);

	public Page<BcglEntity> findList(BcglEntity bcglEntity, PageRequest pageRequest);

	public List<BcglEntity> findAllList(BcglEntity bcglEntity); 
	
	public void updateStatusByIds(List<String> idList);
}
