package com.cesgroup.prison.yjct.service.impl;
 
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yjct.dao.BjyaMapper;
import com.cesgroup.prison.yjct.entity.BjyaEntity;
import com.cesgroup.prison.yjct.service.BjyaService;

@Service("bjyaService")
public class BjyaServiceImpl extends BaseDaoService<BjyaEntity, String, BjyaMapper> implements BjyaService {
	 
	@Resource
	private BjyaMapper bjyaMapper;

	@Override
	public List<Map<String, Object>> findAllListForCombobox(BjyaEntity bjyaEntity) {

		return bjyaMapper.findAllListForCombobox(bjyaEntity);
	} 
}
