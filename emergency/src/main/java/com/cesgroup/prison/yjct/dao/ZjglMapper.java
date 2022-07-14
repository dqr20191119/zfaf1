package com.cesgroup.prison.yjct.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.ZjglEntity;

public interface ZjglMapper extends BaseDao<ZjglEntity, String> {

	public ZjglEntity getById(String id);

	public Page<ZjglEntity> findList(ZjglEntity zjglEntity, PageRequest pageRequest);

	public List<ZjglEntity> findAllList(ZjglEntity zjglEntity);
	
	public void updateStatusByIds(List<String> idList);
}
