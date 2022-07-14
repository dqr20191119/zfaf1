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
import com.cesgroup.prison.yjct.dao.CzffczxMapper;
import com.cesgroup.prison.yjct.dao.CzffglMapper;
import com.cesgroup.prison.yjct.entity.CzffczxEntity;
import com.cesgroup.prison.yjct.entity.CzffglEntity;
import com.cesgroup.prison.yjct.service.CzffglService;

@Service("czffglService")
public class CzffglServiceImpl extends BaseDaoService<CzffglEntity, String, CzffglMapper> implements CzffglService {
	
	@Resource
	private CzffglMapper czffglMapper;
	@Resource
	private CzffczxMapper czffczxMapper;

	@Override
	public CzffglEntity getById(String id) {
		
		return czffglMapper.getById(id);
	}

	@Override
	public Page<CzffglEntity> findList(CzffglEntity czffglEntity, PageRequest pageRequest) {
		
		return czffglMapper.findList(czffglEntity, pageRequest);
	}

	@Override
	public List<CzffglEntity> findAllList(CzffglEntity czffglEntity) {
		
		return czffglMapper.findAllList(czffglEntity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(CzffglEntity czffglEntity) {

		String id = czffglEntity.getId();		
		if(id != null && !"".equals(id)) {
			czffglMapper.update(czffglEntity);
		} else {			
			czffglMapper.insert(czffglEntity);			
		}
		
		// 处置方法关联操作项
		CzffczxEntity czffczxEntity = new CzffczxEntity();
		czffczxEntity.setMraMethodFid(czffglEntity.getId());
		czffczxMapper.deleteByCondition(czffczxEntity);
		
		String[] mraArr = czffglEntity.getMethodRelAction().split(",");
		List<CzffczxEntity> czffczxList = new ArrayList<CzffczxEntity>();
		for(String mra : mraArr) {
			CzffczxEntity czffczx = new CzffczxEntity();
			czffczx.setMraCusNumber(czffglEntity.getDmiCusNumber());
			czffczx.setMraRelActionType(mra);
			czffczx.setMraMethodFid(czffglEntity.getId());
			czffczx.setMraCrteTime(czffglEntity.getDmiUpdtTime());
			czffczx.setMraCrteUserId(czffglEntity.getDmiUpdtUserId());
			czffczx.setMraUpdtTime(czffglEntity.getDmiUpdtTime());
			czffczx.setMraUpdtUserId(czffglEntity.getDmiUpdtUserId());
			czffczxList.add(czffczx);	 
		}
		czffczxMapper.insert(czffczxList);
	}	
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		czffglMapper.updateStatusByIds(Arrays.asList(idArr));			 
	}
}
