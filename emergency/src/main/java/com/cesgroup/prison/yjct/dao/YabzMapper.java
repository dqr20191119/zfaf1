package com.cesgroup.prison.yjct.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.YabzEntity;

public interface YabzMapper extends BaseDao<YabzEntity, String> {

	public YabzEntity getById(String id);
	
	public Page<YabzEntity> findList(YabzEntity yabzEntity, PageRequest pageRequest);

	public List<YabzEntity> findAllList(YabzEntity yabzEntity);	
}
