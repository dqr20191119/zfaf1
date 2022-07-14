package com.cesgroup.prison.rlsb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.rlsb.entity.RlsbEntity;

public interface RlsbService extends IBaseCRUDService<RlsbEntity, String> {
	
	public Page<RlsbEntity> searchRlsbList(RlsbEntity rlsbEntity, PageRequest pageRequest);
	
	public Map<String, Object> getUrl(Map<String, Object> map);
	
}
