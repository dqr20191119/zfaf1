package com.cesgroup.prison.yjct.service.impl;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yjct.dao.YaczMapper;
import com.cesgroup.prison.yjct.entity.YaczEntity;
import com.cesgroup.prison.yjct.service.YaczService;

@Service("yaczService")
public class YaczServiceImpl extends BaseDaoService<YaczEntity, String, YaczMapper> implements YaczService {
	 
	@Resource
	private YaczMapper yaczMapper;

	@Override
	public List<YaczEntity> findAllList(YaczEntity yaczEntity) {

		return yaczMapper.findAllList(yaczEntity);
	} 
}
