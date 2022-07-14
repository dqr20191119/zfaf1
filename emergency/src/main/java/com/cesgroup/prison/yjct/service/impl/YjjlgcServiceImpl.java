package com.cesgroup.prison.yjct.service.impl;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yjct.dao.YjjlMapper;
import com.cesgroup.prison.yjct.dao.YjjlgcMapper;
import com.cesgroup.prison.yjct.entity.YjjlEntity;
import com.cesgroup.prison.yjct.entity.YjjlgcEntity;
import com.cesgroup.prison.yjct.service.YjjlgcService;

@Service("yjjlgcService")
public class YjjlgcServiceImpl extends BaseDaoService<YjjlgcEntity, String, YjjlgcMapper> implements YjjlgcService {
	 
	@Resource
	private YjjlgcMapper yjjlgcMapper;
	@Resource
	private YjjlMapper yjjlMapper;

	@Override
	public List<YjjlgcEntity> findAllList(YjjlgcEntity yjjlgcEntity) {

		return yjjlgcMapper.findAllList(yjjlgcEntity);
	}

	@Override
	@Transactional
	public String saveOrUpdate(YjjlgcEntity yjjlgcEntity) {
		
		String msg = "";
		String sfUpdateStatus = yjjlgcEntity.getSfUpdateStatus();
		
		if(sfUpdateStatus != null && "1".equals(sfUpdateStatus)) {
			// 校验处置流程是否全部填写
			int czlcNum = yjjlgcEntity.getCzlcNum();
			
			YjjlgcEntity yjjlgc = new YjjlgcEntity();
			yjjlgc.setEhpHandleFid(yjjlgcEntity.getEhpHandleFid());
			List<YjjlgcEntity> yjjlgcList = yjjlgcMapper.findAllList(yjjlgc);
			
			if(yjjlgcList != null && yjjlgcList.size() == (czlcNum - 1)) {
				
				String id = yjjlgcEntity.getId();		
				if(id != null && !"".equals(id)) {
					yjjlgcMapper.update(yjjlgcEntity);
				} else {		
					yjjlgcMapper.insert(yjjlgcEntity);	
					
					YjjlEntity yjjl = yjjlMapper.getById(yjjlgcEntity.getEhpHandleFid());
					yjjl.setEhrStatus("2");
					yjjl.setEhrUpdtTime(yjjlgcEntity.getEhpUpdtTime());
					yjjl.setEhrUpdtUserId(yjjlgcEntity.getEhpUpdtUserId());
					yjjlMapper.update(yjjl);
				}				
			} else {
				msg = "请记录所有流程情况！";
			}
		} else {
			
			String id = yjjlgcEntity.getId();		
			if(id != null && !"".equals(id)) {
				yjjlgcMapper.update(yjjlgcEntity);
			} else {		
				yjjlgcMapper.insert(yjjlgcEntity);				
			}
		}
			
		return msg;	
	} 
	@Override
	@Transactional
	public void updateAlarmRecord(String id, String cusNumber, String userName, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("cusNumber", cusNumber);
		map.put("userName", userName);
		map.put("userId", userId);
		yjjlgcMapper.updateAlarmRecord(map);
	}
}
