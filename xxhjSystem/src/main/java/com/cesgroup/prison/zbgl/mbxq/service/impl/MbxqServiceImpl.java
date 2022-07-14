package com.cesgroup.prison.zbgl.mbxq.service.impl;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.zbgl.mbxq.dao.MbxqMapper;
import com.cesgroup.prison.zbgl.mbxq.entity.MbxqEntity;
import com.cesgroup.prison.zbgl.mbxq.service.MbxqService;

@Service("MbxqService")
public class MbxqServiceImpl extends BaseDaoService<MbxqEntity, String, MbxqMapper> implements MbxqService {
	
	@Resource   
	private MbxqMapper mbxqMapper;
	
	@Override
	public List<MbxqEntity> findAllList(MbxqEntity mbxqEntity) {
		
		return mbxqMapper.findAllList(mbxqEntity);
	}
	
}
