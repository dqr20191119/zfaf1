package com.cesgroup.prison.yjct.service.impl;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yjct.dao.YapzMapper;
import com.cesgroup.prison.yjct.entity.YapzEntity;
import com.cesgroup.prison.yjct.service.YapzService;

@Service("yapzService")
public class YapzServiceImpl extends BaseDaoService<YapzEntity, String, YapzMapper> implements YapzService {
	 
	@Resource
	private YapzMapper yapzMapper;

	@Override
	public List<YapzEntity> findAllList(YapzEntity yapzEntity) {

		return yapzMapper.findAllList(yapzEntity);
	} 
}
