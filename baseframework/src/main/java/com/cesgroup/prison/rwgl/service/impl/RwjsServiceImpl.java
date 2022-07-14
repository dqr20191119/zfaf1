package com.cesgroup.prison.rwgl.service.impl;
 
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.rwgl.dao.RwjsMapper;
import com.cesgroup.prison.rwgl.dao.RwxfMapper;
import com.cesgroup.prison.rwgl.entity.RwjsEntity;
import com.cesgroup.prison.rwgl.service.RwjsService;

@Service("RwjsService")
public class RwjsServiceImpl extends BaseDaoService<RwjsEntity, String, RwjsMapper> implements RwjsService {
	
	@Resource
	private RwjsMapper rwjsMapper;	
	
	@Resource
	private RwxfMapper rwxfMapper;
	
	@Override
	public RwjsEntity getById(String id) {
		
		return rwjsMapper.getById(id);
	}

	@Override
	public Page<RwjsEntity> findList(RwjsEntity rwjsEntity, PageRequest pageRequest) {
		
		return rwjsMapper.findList(rwjsEntity, pageRequest);
	}
	
	@Override
	public Page<RwjsEntity> findDbList(RwjsEntity rwjsEntity, PageRequest pageRequest) {
		
		return rwjsMapper.findDbList(rwjsEntity, pageRequest);
	}

	@Override
	public List<RwjsEntity> findAllList(RwjsEntity rwjsEntity) {
		
		return rwjsMapper.findAllList(rwjsEntity);
	}

	@Override
	@Transactional
	public RwjsEntity saveOrUpdate(RwjsEntity rwjsEntity) {
		
		String id = rwjsEntity.getId();		
		if(id != null && !"".equals(id)) {
			rwjsMapper.update(rwjsEntity);
		} else {			
			rwjsMapper.insert(rwjsEntity);			
		}
		
		return rwjsEntity;
	}	

	
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		for(String id : idArr) {
			rwjsMapper.delete(id);
		}
		
	}
	
	@Override
	@Transactional
	public void updateStatusByIds(String ids,UserBean user) {

		String[] idArr = ids.split(",");
		for(String id : idArr) {
			RwjsEntity rwjs = rwjsMapper.getById(id);
			
			rwjs.setJsStatus(Integer.parseInt(rwjs.getJsStatus())+1+"");
			rwjs.setRwjsUpdtTime(new Date());
			rwjs.setRwjsUpdtUserId(user.getUserName());
			rwjsMapper.update(rwjs);
			rwxfMapper.updateStatus(rwjs.getXfId());
		}
		
	}

	@Override
	@Transactional
	public void updatePlanStatus(RwjsEntity rwjsEntity) {
		
		RwjsEntity rwjs = rwjsMapper.getById(rwjsEntity.getId());
		rwjs.setRwjsUpdtTime(rwjsEntity.getRwjsUpdtTime());
		rwjs.setRwjsUpdtUserId(rwjsEntity.getRwjsUpdtUserId());
		rwjsMapper.update(rwjs);	
	}

	@Override
	public List<Map<String, Object>> searchSwdb(UserBean user) {
		return rwjsMapper.searchSwdb(user);
	}
	
	//监外就诊和住院情况 heqh 20190923
	@Override
	public List<Map<String, Object>> searchJwqk(UserBean user) {
		HashMap<String, String> map  = new HashMap<String, String>();
		String jyId = user.getCusNumber();
		String jqId = user.getDprtmntCode();
		String level = user.getUserLevel().toString();
		if(level.equals("2")) {
			map.put("jyId", jyId);
		} else if(level.equals("3")) {
			map.put("jyId", jyId);
			map.put("jqId", jqId);
		}
		return rwjsMapper.searchJwqk(map);
	}
	
}
