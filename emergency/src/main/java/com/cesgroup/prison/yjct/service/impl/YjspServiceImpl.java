package com.cesgroup.prison.yjct.service.impl;
 
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Transient;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.yjct.dao.YabzMapper;
import com.cesgroup.prison.yjct.dao.YjspMapper;
import com.cesgroup.prison.yjct.dao.YljhMapper;
import com.cesgroup.prison.yjct.entity.YabzEntity;
import com.cesgroup.prison.yjct.entity.YjspEntity;
import com.cesgroup.prison.yjct.entity.YljhEntity;
import com.cesgroup.prison.yjct.service.YjspService;

@Service("yjspService")
public class YjspServiceImpl extends BaseDaoService<YjspEntity, String, YjspMapper> implements YjspService {
	 
	@Resource
	private YjspMapper yjspMapper;
	@Resource
	private YabzMapper yabzMapper;
	@Resource
	private YljhMapper yljhMapper;

	@Override
	public List<YjspEntity> findAllList(YjspEntity yjspEntity) {

		return yjspMapper.findAllList(yjspEntity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(YjspEntity yjspEntity) {
		
		yjspMapper.insert(yjspEntity);	
				
		if("1".equals(yjspEntity.getEhaAppType())) {
			
			// 更新预案
			YabzEntity yabzEntity = yabzMapper.getById(yjspEntity.getEhaPhFid());
 			yabzEntity.setEpiAppFlow(yjspEntity.getEhaAppRole());
 			yabzEntity.setEpiPlanStatus("3");			 
			yabzEntity.setEpiUpdtTime(yjspEntity.getEhaUpdtTime());
			yabzEntity.setEpiUpdtUserId(yjspEntity.getEhaUpdtUserId());
			
			yabzMapper.update(yabzEntity);
		} else if("2".equals(yjspEntity.getEhaAppType())) {
			
			// 更新演练
			YljhEntity yljhEntity = new YljhEntity();
			yljhEntity.setId(yjspEntity.getEhaPhFid());
			YljhEntity yljh = yljhMapper.getById(yljhEntity);
			
			yljh.setEdpStatus("3");
			yljh.setEdpUpdtTime(yjspEntity.getEhaUpdtTime());
			yljh.setEdpUpdtUserId(yjspEntity.getEhaUpdtUserId());
			yljhMapper.update(yljh);
		}	
	} 
}