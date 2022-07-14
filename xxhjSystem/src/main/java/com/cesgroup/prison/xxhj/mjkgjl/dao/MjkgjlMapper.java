package com.cesgroup.prison.xxhj.mjkgjl.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.mjkgjl.entity.MjkgjlEntity;

public interface MjkgjlMapper extends BaseDao<MjkgjlEntity, String> {

	public Page<MjkgjlEntity> findList(MjkgjlEntity mjkgjlEntity, PageRequest pageRequest);

	public Page<MjkgjlEntity> searchSwdbPage(MjkgjlEntity mjkgjlEntity,PageRequest pageRequest);
}
