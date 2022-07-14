package com.cesgroup.prison.zbgl.bcgl.service.impl;
 
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.zbgl.bcgl.dao.BcglMapper;
import com.cesgroup.prison.zbgl.bcgl.entity.BcglEntity;
import com.cesgroup.prison.zbgl.bcgl.service.BcglService;

@Service("bcglService")
public class BcglServiceImpl extends BaseDaoService<BcglEntity, String, BcglMapper> implements BcglService {
	
	@Resource
	private BcglMapper bcglMapper;

	@Override
	public BcglEntity getById(String id) {
		
		return bcglMapper.getById(id);
	}

	@Override
	public Page<BcglEntity> findList(BcglEntity bcglEntity, PageRequest pageRequest) {
		
		return bcglMapper.findList(bcglEntity, pageRequest);
	}

	@Override
	public List<BcglEntity> findAllList(BcglEntity bcglEntity) {
		
		return bcglMapper.findAllList(bcglEntity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(BcglEntity bcglEntity) {

		String id = bcglEntity.getId();		
		if(id != null && !"".equals(id)) {
			bcglMapper.update(bcglEntity);
		} else {			
			bcglMapper.insert(bcglEntity);			
		}
	}	
	
	@Override
	@Transactional
	public void deleteById(String id) {

		String[] idArr = id.split(",");
 		bcglMapper.updateStatusByIds(Arrays.asList(idArr));			 
	}
}
