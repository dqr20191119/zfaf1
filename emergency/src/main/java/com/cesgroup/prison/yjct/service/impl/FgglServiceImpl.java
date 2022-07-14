package com.cesgroup.prison.yjct.service.impl;
 
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yjct.dao.FgglMapper;
import com.cesgroup.prison.yjct.entity.FgglEntity;
import com.cesgroup.prison.yjct.entity.WzglEntity;
import com.cesgroup.prison.yjct.service.FgglService;

@Service("fgglService")
public class FgglServiceImpl extends BaseDaoService<FgglEntity, String, FgglMapper> implements FgglService {
	
	@Resource
	private FgglMapper fgglMapper;

	@Override
	public FgglEntity getById(String id) {
		
		return fgglMapper.getById(id);
	}

	@Override
	public Page<FgglEntity> findList(FgglEntity fgglEntity, PageRequest pageRequest) {
		
		return fgglMapper.findList(fgglEntity, pageRequest);
	}

	@Override
	public List<FgglEntity> findAllList(FgglEntity fgglEntity) {
		
		return fgglMapper.findAllList(fgglEntity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(FgglEntity fgglEntity) {

		String id = fgglEntity.getId();		
		if(id != null && !"".equals(id)) {
			fgglMapper.update(fgglEntity);
		} else {			
			fgglMapper.insert(fgglEntity);			
		}
	}	
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		fgglMapper.updateStatusByIds(Arrays.asList(idArr));			 
	}
}
