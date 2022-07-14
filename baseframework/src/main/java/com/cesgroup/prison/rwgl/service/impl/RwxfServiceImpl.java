package com.cesgroup.prison.rwgl.service.impl;
 
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.rwgl.dao.RwjsMapper;
import com.cesgroup.prison.rwgl.dao.RwxfMapper;
import com.cesgroup.prison.rwgl.entity.RwjsEntity;
import com.cesgroup.prison.rwgl.entity.RwxfEntity;
import com.cesgroup.prison.rwgl.service.RwxfService;

@Service("RwxfService")
public class RwxfServiceImpl extends BaseDaoService<RwxfEntity, String, RwxfMapper> implements RwxfService {
	
	@Resource
	private RwxfMapper rwxfMapper;	
	@Resource
	private RwjsMapper rwjsMapper;	
	
	@Override
	public RwxfEntity getById(String id) {
		
		return rwxfMapper.getById(id);
	}

	@Override
	public Page<RwxfEntity> findList(RwxfEntity rwxfEntity, PageRequest pageRequest) {
		
		return rwxfMapper.findList(rwxfEntity, pageRequest);
	}

	@Override
	public List<RwxfEntity> findAllList(RwxfEntity rwxfEntity) {
		
		return rwxfMapper.findAllList(rwxfEntity);
	}

	@Override
	@Transactional
	public RwxfEntity saveOrUpdate(RwxfEntity rwxfEntity) {
		
		String id = rwxfEntity.getId();		
		if(id != null && !"".equals(id)) {
			rwxfMapper.update(rwxfEntity);
		} else {			
			rwxfMapper.insert(rwxfEntity);			
		}
		
		if("1".equals(rwxfEntity.getRwStatus())) {
			RwjsEntity rwjs = new RwjsEntity();
			rwjs.setXfId(rwxfEntity.getId());
			rwjs.setJyId(rwxfEntity.getJyId());
			rwjs.setJqId(rwxfEntity.getJqId());
			rwjs.setXfPolice(rwxfEntity.getXfPolice());
			rwjs.setXfTime(rwxfEntity.getXfTime());
			rwjs.setRwTitle(rwxfEntity.getRwTitle());
			rwjs.setRwContent(rwxfEntity.getRwContent());
			rwjs.setClDeadline(rwxfEntity.getClDeadline());
			rwjs.setJsStatus("0");
			rwjs.setFxcjId(rwxfEntity.getFxcjId());
			rwjsMapper.insert(rwjs);
		}
		
		return rwxfEntity;
	}	

	
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		for(String id : idArr) {
			rwxfMapper.delete(id);
		}
		
	}
	
	@Override
	@Transactional
	public void updateStatusByIds(String ids,UserBean user) {

		String[] idArr = ids.split(",");
		for(String id : idArr) {
			RwxfEntity rwxf = rwxfMapper.getById(id);
			rwxf.setRwStatus("1");
			rwxf.setRwxfUpdtTime(new Date());
			rwxf.setRwxfUpdtUserId(user.getUserName());
			rwxfMapper.update(rwxf);
			
			RwjsEntity rwjs = new RwjsEntity();
			rwjs.setXfId(rwxf.getId());
			rwjs.setJyId(rwxf.getJyId());
			rwjs.setJqId(rwxf.getJqId());
			rwjs.setXfPolice(rwxf.getXfPolice());
			rwjs.setXfTime(rwxf.getXfTime());
			rwjs.setRwTitle(rwxf.getRwTitle());
			rwjs.setRwContent(rwxf.getRwContent());
			rwjs.setClDeadline(rwxf.getClDeadline());
			rwjs.setJsStatus("0");
			rwjsMapper.insert(rwjs);
			
		}
		
	}

	@Override
	@Transactional
	public void updatePlanStatus(RwxfEntity rwxfEntity) {
		
		RwxfEntity rwxf = rwxfMapper.getById(rwxfEntity.getId());
		rwxf.setRwStatus("1");
		rwxf.setRwxfUpdtTime(rwxfEntity.getRwxfUpdtTime());
		rwxf.setRwxfUpdtUserId(rwxfEntity.getRwxfUpdtUserId());
		rwxfMapper.update(rwxf);	
	}
	
}
