package com.cesgroup.prison.xxhj.mjxcjl.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.mjxcjl.entity.MjxcjlEntity;

public interface MjxcjlMapper extends BaseDao<MjxcjlEntity, String>{
	public Page<MjxcjlEntity> findList(MjxcjlEntity MjxcjlEntity, PageRequest pageRequest);

	public Page<MjxcjlEntity> searchSwdbPage(MjxcjlEntity MjxcjlEntity,PageRequest pageRequest);
}