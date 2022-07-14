package com.cesgroup.prison.common.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.common.entity.PoliceEntity;

public interface PoliceMapper extends BaseDao<PoliceEntity, String> {

	public List<PoliceEntity> findAllList(PoliceEntity policeEntity);

	public List<Map<String, Object>> findPoliceByAreaIdForCombobox(Map<String, Object> paramMap);

	List<Map<String, Object>> findAllPoliceForAutocomplete(Map<String, Object> paramMap);
}
