package com.cesgroup.prison.yjct.service.impl;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yjct.dao.GzzcyMapper;
import com.cesgroup.prison.yjct.dao.GzzglMapper;
import com.cesgroup.prison.yjct.entity.GzzcyEntity;
import com.cesgroup.prison.yjct.entity.GzzglEntity;
import com.cesgroup.prison.yjct.service.GzzglService;

@Service("gzzglService")
public class GzzglServiceImpl extends BaseDaoService<GzzglEntity, String, GzzglMapper> implements GzzglService {
	
	@Resource
	private GzzglMapper gzzglMapper;
	@Resource
	private GzzcyMapper gzzcyMapper;
	
	@Override
	public GzzglEntity getById(String id) {
		
		return gzzglMapper.getById(id);
	}

	@Override
	public Page<GzzglEntity> findList(GzzglEntity gzzglEntity, PageRequest pageRequest) {
		
		return gzzglMapper.findList(gzzglEntity, pageRequest);
	}

	@Override
	public List<GzzglEntity> findAllList(GzzglEntity gzzglEntity) {
		
		return gzzglMapper.findAllList(gzzglEntity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(GzzglEntity gzzglEntity) {

		// 工作组
		String id = gzzglEntity.getId();		
		if(id != null && !"".equals(id)) {
			gzzglMapper.update(gzzglEntity);
		} else {			
			gzzglMapper.insert(gzzglEntity);			
		}
		
		// 工作组成员
		List<GzzcyEntity> gzzcyEntityList = gzzglEntity.getGzzcyEntityList();
		List<GzzcyEntity> saveList = new ArrayList<GzzcyEntity>();
		List<GzzcyEntity> updateList = new ArrayList<GzzcyEntity>();		
		if(gzzcyEntityList != null && gzzcyEntityList.size() > 0) {			
			for(GzzcyEntity gzzcyEntity : gzzcyEntityList) {
				
				String cyId = gzzcyEntity.getId();
				if(cyId != null && !"".equals(cyId)) {
					
					gzzcyEntity.setWgmUpdtTime(gzzglEntity.getWgiUpdtTime());
					gzzcyEntity.setWgmUpdtUserId(gzzglEntity.getWgiUpdtUserId());
					updateList.add(gzzcyEntity);
				} else {
					
					gzzcyEntity.setWgmCusNumber(gzzglEntity.getWgiCusNumber());
					gzzcyEntity.setWgmWorkgroupFid(gzzglEntity.getId());					
					gzzcyEntity.setWgmCrteTime(gzzglEntity.getWgiUpdtTime());
					gzzcyEntity.setWgmCrteUserId(gzzglEntity.getWgiUpdtUserId());											
					gzzcyEntity.setWgmUpdtTime(gzzglEntity.getWgiUpdtTime());
					gzzcyEntity.setWgmUpdtUserId(gzzglEntity.getWgiUpdtUserId());
					saveList.add(gzzcyEntity);
				}				
			}
			
			if(saveList.size() > 0) {
				gzzcyMapper.insert(saveList);
			}
			
			if(updateList.size() > 0) {
				gzzcyMapper.update(updateList);
			}
		}		
	}	
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		gzzglMapper.updateStatusByIds(Arrays.asList(idArr));		
	}
}
