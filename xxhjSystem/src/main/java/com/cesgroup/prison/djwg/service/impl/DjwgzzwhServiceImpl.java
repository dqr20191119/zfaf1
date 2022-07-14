package com.cesgroup.prison.djwg.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.djwg.dao.DjwgzzwhMapper;
import com.cesgroup.prison.djwg.entity.DjwgzzwhEntity;
import com.cesgroup.prison.djwg.service.DjwgzzwhService;

@Service("djwgzzwhService")
public class DjwgzzwhServiceImpl extends BaseDaoService<DjwgzzwhEntity, String, DjwgzzwhMapper> implements DjwgzzwhService {

	@Resource
	private DjwgzzwhMapper djwgzzwhMapper;
	
	@Override
	public DjwgzzwhEntity getById(String id) {
		// TODO Auto-generated method stub
		DjwgzzwhEntity djwgzzwhEntity = djwgzzwhMapper.getById(id);
		return djwgzzwhEntity;
	}

	@Override
	public Page<DjwgzzwhEntity> findList(DjwgzzwhEntity djwgzzwhEntity, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String jyId = user.getOrgCode();
		djwgzzwhEntity.setJyId(jyId);
		return djwgzzwhMapper.findList(djwgzzwhEntity, pageRequest);
	}

	@Override
	public List<DjwgzzwhEntity> findAllList(DjwgzzwhEntity djwgzzwhEntity) {
		// TODO Auto-generated method stub
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String jyId = user.getOrgCode();
		djwgzzwhEntity.setJyId(jyId);
		return djwgzzwhMapper.findAllList(djwgzzwhEntity);
	}

	
	@Override
	@Transactional
	public void saveOrUpdate(DjwgzzwhEntity djwgzzwhEntity) throws Exception {
		// TODO Auto-generated method stub
		String id =djwgzzwhEntity.getId();		
		
		if(id != null && !"".equals(id)) {
			djwgzzwhMapper.update(djwgzzwhEntity);
		} else {	
			djwgzzwhEntity.setZzCode(djwgzzwhEntity.getJyId()+"_"+djwgzzwhEntity.getZzCode());
			djwgzzwhMapper.insert(djwgzzwhEntity);			
		}
	}

	@Override
	@Transactional
	public void deleteByIds(String idList) {
		// TODO Auto-generated method stub
		String[] idArr = idList.split(",");
		djwgzzwhMapper.updateStatusByIds(Arrays.asList(idArr));
	}

	@Override
	public DjwgzzwhEntity getByCode(String zzCode) {
		// TODO Auto-generated method stub
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String jyId = user.getOrgCode();
		DjwgzzwhEntity djwgzzwhEntity = djwgzzwhMapper.getByCode(jyId,zzCode);
		return djwgzzwhEntity;
	}

}
