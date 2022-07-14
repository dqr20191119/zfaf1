package com.cesgroup.prison.yjct.service.impl;
 
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yjct.dao.WzglMapper;
import com.cesgroup.prison.yjct.entity.WzglEntity;
import com.cesgroup.prison.yjct.service.WzglService;

@Service("wzglService")
public class WzglServiceImpl extends BaseDaoService<WzglEntity, String, WzglMapper> implements WzglService {
	
	@Resource
	private WzglMapper wzglMapper;

	@Override
	public WzglEntity getById(String id) {
		
		return wzglMapper.getById(id);
	}

	@Override
	public Page<WzglEntity> findList(WzglEntity wzglEntity, PageRequest pageRequest) {
		
		return wzglMapper.findList(wzglEntity, pageRequest);
	}

	@Override
	public List<WzglEntity> findAllList(WzglEntity wzglEntity) {
		
		return wzglMapper.findAllList(wzglEntity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(WzglEntity wzglEntity) {

		// 工作组
		String id = wzglEntity.getId();		
		if(id != null && !"".equals(id)) {
			wzglMapper.update(wzglEntity);
		} else {			
			wzglMapper.insert(wzglEntity);			
		}
	}	
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
 		wzglMapper.updateStatusByIds(Arrays.asList(idArr));			 
	}
}
