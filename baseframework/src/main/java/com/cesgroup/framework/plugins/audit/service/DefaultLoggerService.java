package com.cesgroup.framework.plugins.audit.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.framework.plugins.audit.entity.LogEntity;
import com.cesgroup.prison.ext.dao.OptLogMapper;
import com.cesgroup.prison.ext.entity.OptLogEntity;

@Service
@Transactional
public class DefaultLoggerService extends BaseService<LogEntity, String> implements ILoggerService {
	
	@Autowired
	private OptLogMapper optLogMapper;
	
	public LogEntity save(LogEntity log) {
		OptLogEntity temp = new OptLogEntity();
		temp.setId(UUID.randomUUID().toString());
		temp.setAction(log.getAction());
		temp.setIp(log.getIp());
		temp.setModel(log.getModel());
		temp.setOpTarget(log.getOpTarget());
		temp.setResult(log.getResult());
		temp.setTime(log.getTime());
		temp.setUrl(log.getUrl());
		temp.setUserId(log.getUserId());
		optLogMapper.insertLog(temp);
		return log;
	}
	
}