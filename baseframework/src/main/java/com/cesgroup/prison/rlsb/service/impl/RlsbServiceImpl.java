package com.cesgroup.prison.rlsb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.rlsb.dao.RlsbMapper;
import com.cesgroup.prison.rlsb.entity.RlsbEntity;
import com.cesgroup.prison.rlsb.service.RlsbService;

@Service("rlsbService")
public class RlsbServiceImpl extends BaseDaoService<RlsbEntity, String, RlsbMapper> implements RlsbService {
	
	@Resource
	private RlsbMapper rlsbMapper;
	
	@Override
	public Page<RlsbEntity> searchRlsbList(RlsbEntity rlsbEntity, PageRequest pageRequest) {
		return rlsbMapper.searchRlsbList(rlsbEntity, pageRequest);
	}

	@Override
	public Map<String, Object> getUrl(Map<String, Object> map) {
		return rlsbMapper.getUrl(map);
	}

}
