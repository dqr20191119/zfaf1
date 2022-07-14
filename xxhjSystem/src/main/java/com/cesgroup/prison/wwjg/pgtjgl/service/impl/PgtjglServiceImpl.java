package com.cesgroup.prison.wwjg.pgtjgl.service.impl;
 
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.wwjg.pgtjgl.dao.PgtjglMapper;
import com.cesgroup.prison.wwjg.pgtjgl.entity.PgtjglEntity;
import com.cesgroup.prison.wwjg.pgtjgl.service.PgtjglService;

@Service("pgtjglService")
public class PgtjglServiceImpl extends BaseDaoService<PgtjglEntity, String, PgtjglMapper> implements PgtjglService {
	
	@Resource
	private PgtjglMapper PgtjglMapper;
	
	@Override
	public PgtjglEntity getById(String id) { 
		
		PgtjglEntity PgtjglEntity = PgtjglMapper.getById(id);
		
		return PgtjglEntity;
	}

	@Override
	public Page<PgtjglEntity> findList(PgtjglEntity PgtjglEntity, PageRequest pageRequest) {
		return PgtjglMapper.findList(PgtjglEntity, pageRequest);
	}

	@Override
	public List<PgtjglEntity> findAllList(PgtjglEntity PgtjglEntity) {
		
		return PgtjglMapper.findAllList(PgtjglEntity);
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(PgtjglEntity PgtjglEntity) throws Exception {

		String id =PgtjglEntity.getId();		
		if(id != null && !"".equals(id)) {
			PgtjglMapper.update(PgtjglEntity);
		} else {			
			PgtjglMapper.insert(PgtjglEntity);			
		}
		
	}
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		PgtjglMapper.updateStatusByIds(Arrays.asList(idArr));
 		
	} 
	
	@Override
	public PgtjglEntity getByCode(String code) { 
		
		PgtjglEntity PgtjglEntity = PgtjglMapper.getByCode(code);
		
		return PgtjglEntity;
	}
}
