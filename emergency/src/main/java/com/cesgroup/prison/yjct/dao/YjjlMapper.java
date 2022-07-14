package com.cesgroup.prison.yjct.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.YjjlEntity;

public interface YjjlMapper extends BaseDao<YjjlEntity, String> {

	public YjjlEntity getById(String id);

	public Page<YjjlEntity> findList(YjjlEntity yjjlEntity, PageRequest pageRequest);

	public List<YjjlEntity> tjByAll(YjjlEntity yjjlEntity);

	public List<YjjlEntity> tjByPlanType(YjjlEntity yjjlEntity);

	public Map<String, Object> findEventRecord(String id);
}
