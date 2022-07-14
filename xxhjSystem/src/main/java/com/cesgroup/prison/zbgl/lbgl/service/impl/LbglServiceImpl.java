package com.cesgroup.prison.zbgl.lbgl.service.impl;
 
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.zbgl.gwgl.dao.GwglMapper;
import com.cesgroup.prison.zbgl.lbbm.dao.LbbmMapper;
import com.cesgroup.prison.zbgl.lbbm.entity.LbbmEntity;
import com.cesgroup.prison.zbgl.lbgl.dao.LbglMapper;
import com.cesgroup.prison.zbgl.lbgl.entity.LbglEntity;
import com.cesgroup.prison.zbgl.lbgl.service.LbglService;

@Service("lbglService")
public class LbglServiceImpl extends BaseDaoService<LbglEntity, String, LbglMapper> implements LbglService {
	
	@Resource
	private LbglMapper lbglMapper;
	@Resource
	private LbbmMapper lbbmMapper;
	@Resource
	private GwglMapper gwglMapper;
	
	@Override
	public LbglEntity getById(String id) { 
		
		LbglEntity lbgl = lbglMapper.getById(id);
		
		return lbgl;
	}

	@Override
	public Page<LbglEntity> findList(LbglEntity lbglEntity, PageRequest pageRequest) {
		
		return lbglMapper.findList(lbglEntity, pageRequest);
	}

	@Override
	public List<LbglEntity> findAllList(LbglEntity lbglEntity) {
		
		return lbglMapper.findAllList(lbglEntity);
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(LbglEntity lbglEntity) throws Exception {

		String id = lbglEntity.getId();		
		if(id != null && !"".equals(id)) {
			lbglMapper.update(lbglEntity);
		} else {			
			lbglMapper.insert(lbglEntity);			
		}
		
		if(id != null && !"".equals(id)) {
			lbbmMapper.deleteByConditions(id);
		}
				
		List<LbbmEntity> lbbmEntityList = lbglEntity.getLbbmEntityList();
		String drpmntIdList = lbglEntity.getDcaDprtmntId();
		String[] drpmntId = drpmntIdList.split(",");
		
		if(drpmntIdList != null && !"".equals(drpmntIdList)) {
			
			for(int i = 0; i < drpmntId.length; i++) {
				
				LbbmEntity lbbm = new LbbmEntity();
				lbbm.setDcdCusNumber(lbglEntity.getDcaCusNumber());
				lbbm.setDcdCategoryId(lbglEntity.getId());	
				lbbm.setDcdDprtmntId(drpmntId[i]);
				lbbm.setDcdCrteTime(new Date());
				lbbm.setDcdCrteUserId(lbglEntity.getDcaCrteUserId());											
				lbbm.setDcdUpdtTime(new Date());
				lbbm.setDcdUpdtUserId(lbglEntity.getDcaUpdtUserId());
				lbbmEntityList.add(lbbm);
			}
			
			if(lbbmEntityList.size() > 0) {
				lbbmMapper.insert(lbbmEntityList);
			}
		}
	}
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
 		lbglMapper.updateStatusByIds(Arrays.asList(idArr));
 		for(int i =0;i<idArr.length;i++) {
 			lbbmMapper.deleteByConditions(idArr[i]);
 		}
 		
	} 		
}
