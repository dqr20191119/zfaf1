package com.cesgroup.prison.yjct.service.impl;
 
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yjct.dao.ZjglMapper;
import com.cesgroup.prison.yjct.entity.ZjglEntity;
import com.cesgroup.prison.yjct.service.ZjglService;

@Service("zjglService")
public class ZjglServiceImpl extends BaseDaoService<ZjglEntity, String, ZjglMapper> implements ZjglService {
	
	@Resource
	private ZjglMapper zjglMapper;

	@Override
	public ZjglEntity getById(String id) {
		
		return zjglMapper.getById(id);
	}

	@Override
	public Page<ZjglEntity> findList(ZjglEntity zjglEntity, PageRequest pageRequest) {
		
		return zjglMapper.findList(zjglEntity, pageRequest);
	}

	@Override
	public List<ZjglEntity> findAllList(ZjglEntity zjglEntity) {
		
		return zjglMapper.findAllList(zjglEntity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(ZjglEntity zjglEntity) {

		// 工作组
		String id = zjglEntity.getId();		
		if(id != null && !"".equals(id)) {
			zjglMapper.update(zjglEntity);
		} else {			
			zjglMapper.insert(zjglEntity);			
		}
	}	
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		zjglMapper.updateStatusByIds(Arrays.asList(idArr));			 
	}
}
