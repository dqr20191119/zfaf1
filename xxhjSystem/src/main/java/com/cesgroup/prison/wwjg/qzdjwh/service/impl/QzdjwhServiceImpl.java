package com.cesgroup.prison.wwjg.qzdjwh.service.impl;
 
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.wwjg.qzdjwh.dao.QzdjwhMapper;
import com.cesgroup.prison.wwjg.qzdjwh.entity.QzdjwhEntity;
import com.cesgroup.prison.wwjg.qzdjwh.service.QzdjwhService;

@Service("qzdjwhService")
public class QzdjwhServiceImpl extends BaseDaoService<QzdjwhEntity, String, QzdjwhMapper> implements QzdjwhService {
	
	@Resource
	private QzdjwhMapper qzdjwhMapper;
	 
	
	@Override
	public QzdjwhEntity getById(String id) { 
		
		QzdjwhEntity qzdjwhEntity = qzdjwhMapper.getById(id);
		
		return qzdjwhEntity;
	}

	@Override
	public Page<QzdjwhEntity> findList(QzdjwhEntity qzdjwhEntity, PageRequest pageRequest) {
		return qzdjwhMapper.findList(qzdjwhEntity, pageRequest);
	}

	@Override
	public List<QzdjwhEntity> findAllList(QzdjwhEntity qzdjwhEntity) {
		
		return qzdjwhMapper.findAllList(qzdjwhEntity);
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(QzdjwhEntity qzdjwhEntity) throws Exception {

		String id =qzdjwhEntity.getId();		
		if(id != null && !"".equals(id)) {
			qzdjwhMapper.update(qzdjwhEntity);
		} else {			
			qzdjwhMapper.insert(qzdjwhEntity);			
		}
		
	}
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		qzdjwhMapper.updateStatusByIds(Arrays.asList(idArr));
 		
	} 		
	
	@Override
	public QzdjwhEntity getByCode(String code) { 
		
		QzdjwhEntity qzdjwhEntity = qzdjwhMapper.getByCode(code);
		
		return qzdjwhEntity;
	}
}
