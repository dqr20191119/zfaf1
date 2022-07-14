package com.cesgroup.prison.yjct.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.WzglEntity;

public interface WzglMapper extends BaseDao<WzglEntity, String> {

	public WzglEntity getById(String id);

	public Page<WzglEntity> findList(WzglEntity wzglEntity, PageRequest pageRequest);

	public List<WzglEntity> findAllList(WzglEntity wzglEntity); 
	
	public void updateStatusByIds(List<String> idList);
}
