package com.cesgroup.prison.rlsb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.rlsb.entity.RlsbEntity;

public interface RlsbMapper  extends BaseDao<RlsbEntity, String> {
	
	public Page<RlsbEntity> searchRlsbList(RlsbEntity rlsbEntity, PageRequest pageRequest);
	
	public Map<String, Object> getUrl(Map<String, Object> map);
}
