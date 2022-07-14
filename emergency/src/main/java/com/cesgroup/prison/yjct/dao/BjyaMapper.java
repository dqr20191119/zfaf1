package com.cesgroup.prison.yjct.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yjct.entity.BjyaEntity;

public interface BjyaMapper extends BaseDao<BjyaEntity, String> {

	public List<Map<String, Object>> findAllListForCombobox(BjyaEntity bjyaEntity);	
}
