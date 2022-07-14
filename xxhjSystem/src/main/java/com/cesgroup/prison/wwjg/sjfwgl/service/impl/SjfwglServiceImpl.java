package com.cesgroup.prison.wwjg.sjfwgl.service.impl;
 
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.wwjg.sjfwgl.dao.SjfwglMapper;
import com.cesgroup.prison.wwjg.sjfwgl.entity.SjfwglEntity;
import com.cesgroup.prison.wwjg.sjfwgl.service.SjfwglService;

@Service("sjfwglService")
public class SjfwglServiceImpl extends BaseDaoService<SjfwglEntity, String, SjfwglMapper> implements SjfwglService {
	
	@Resource
	private SjfwglMapper SjfwglMapper;
	
	@Override
	public SjfwglEntity getById(String id) { 
		
		SjfwglEntity SjfwglEntity = SjfwglMapper.getById(id);
		
		return SjfwglEntity;
	}

	@Override
	public Page<SjfwglEntity> findList(SjfwglEntity SjfwglEntity, PageRequest pageRequest) {
		return SjfwglMapper.findList(SjfwglEntity, pageRequest);
	}

	@Override
	public List<SjfwglEntity> findAllList(SjfwglEntity SjfwglEntity) {
		
		return SjfwglMapper.findAllList(SjfwglEntity);
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(SjfwglEntity SjfwglEntity) throws Exception {

		String id =SjfwglEntity.getId();		
		if(id != null && !"".equals(id)) {
			SjfwglMapper.update(SjfwglEntity);
		} else {			
			SjfwglMapper.insert(SjfwglEntity);			
		}
		
	}
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		SjfwglMapper.updateStatusByIds(Arrays.asList(idArr));
 		
	} 	
	
	@Override
	public SjfwglEntity getByCode(String code) { 
		
		SjfwglEntity SjfwglEntity = SjfwglMapper.getByCode(code);
		
		return SjfwglEntity;
	}
}
