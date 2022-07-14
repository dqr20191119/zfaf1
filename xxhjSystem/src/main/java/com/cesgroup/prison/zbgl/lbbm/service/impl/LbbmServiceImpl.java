package com.cesgroup.prison.zbgl.lbbm.service.impl;
 
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.zbgl.lbbm.dao.LbbmMapper;
import com.cesgroup.prison.zbgl.lbbm.entity.LbbmEntity;
import com.cesgroup.prison.zbgl.lbbm.service.LbbmService;

@Service("lbbmService")
public class LbbmServiceImpl extends BaseDaoService<LbbmEntity, String, LbbmMapper> implements LbbmService {
	
	@Resource
	private LbbmMapper lbbmMapper;

	@Override
	public List<Map<String, Object>> findAllList(LbbmEntity lbbmEntity) {
		
		return lbbmMapper.findAllList(lbbmEntity);
	}

	@Override
	@Transactional
	public void deleteByConditions(String dcdCategoryId) {
		
		 lbbmMapper.deleteByConditions(dcdCategoryId);
		
	}
}
