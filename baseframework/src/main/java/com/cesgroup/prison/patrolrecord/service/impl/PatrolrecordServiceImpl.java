package com.cesgroup.prison.patrolrecord.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.patrolrecord.dao.PatrolrecordMapper;
import com.cesgroup.prison.patrolrecord.dto.PatrolrecordDto;
import com.cesgroup.prison.patrolrecord.entity.PatrolrecordEntity;
import com.cesgroup.prison.patrolrecord.service.PatrolrecordService;

@Service("patrolrecordService")
public class PatrolrecordServiceImpl  extends BaseDaoService<PatrolrecordEntity, String, PatrolrecordMapper> implements PatrolrecordService {
	
	@Resource
	private PatrolrecordMapper patrolrecordMapper;
	
	/**
	 * 接收巡更记录
	 */
	@Override
	@Transactional
	public void processMessage(String cusNumber, JSONObject jsonObj) {
		String  patrolrecord = jsonObj.getString("body");
		//将body消息转化为dto
		PatrolrecordDto patrolrecordDto = Util.fromJson(patrolrecord, PatrolrecordDto.class);
		PatrolrecordEntity patrolrecordEntity = new PatrolrecordEntity();
		
		patrolrecordEntity.setDeptName(patrolrecordDto.getDeptName());
		patrolrecordEntity.setDeptNo(patrolrecordEntity.getDeptNo());
		patrolrecordEntity.setDevId(patrolrecordDto.getDevID());
		patrolrecordEntity.setDevName(patrolrecordDto.getDevName());
		patrolrecordEntity.setName(patrolrecordDto.getName());
		patrolrecordEntity.setPoliceNo(patrolrecordDto.getPoliceNo());
		patrolrecordEntity.setTime(patrolrecordDto.getTime());
		
		patrolrecordMapper.insert(patrolrecordEntity);
		
	}

}
