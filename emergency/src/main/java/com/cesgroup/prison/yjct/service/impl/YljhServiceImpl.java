package com.cesgroup.prison.yjct.service.impl;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yjct.dao.YljhMapper;
import com.cesgroup.prison.yjct.entity.YljhEntity;
import com.cesgroup.prison.yjct.service.YljhService;

@Service("yljhService")
public class YljhServiceImpl extends BaseDaoService<YljhEntity, String, YljhMapper> implements YljhService {
	
	@Resource
	private YljhMapper yljhMapper;

	@Override
	public YljhEntity getById(YljhEntity yljhEntity) {
		
		return yljhMapper.getById(yljhEntity);
	}

	@Override
	public Page<YljhEntity> findList(YljhEntity yljhEntity, PageRequest pageRequest) {
		
		return yljhMapper.findList(yljhEntity, pageRequest);
	}

	@Override
	public List<YljhEntity> findAllList(YljhEntity yljhEntity) {

		return yljhMapper.findAllList(yljhEntity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(YljhEntity yljhEntity) {

		String id = yljhEntity.getId();		
		if(id != null && !"".equals(id)) {
			yljhMapper.update(yljhEntity);
		} else {			
			yljhMapper.insert(yljhEntity);			
		}
	}	
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		for(String id : idArr) {
			yljhMapper.delete(id);
		}
	}

	@Override
	@Transactional
	public void updateStatusById(YljhEntity yljhEntity) {
		
		YljhEntity Yljh = yljhMapper.getById(yljhEntity);
		Yljh.setEdpStatus(yljhEntity.getEdpStatus());
		Yljh.setEdpUpdtTime(yljhEntity.getEdpUpdtTime());
		Yljh.setEdpUpdtUserId(yljhEntity.getEdpUpdtUserId());
		
		yljhMapper.update(Yljh);
	}
}