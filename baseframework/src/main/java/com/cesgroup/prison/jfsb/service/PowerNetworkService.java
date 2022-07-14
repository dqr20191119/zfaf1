package com.cesgroup.prison.jfsb.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.prison.jfsb.entity.PowerNetwork;

public interface PowerNetworkService {

	public Page<Map<String,String>> searchPowerNetwork(PowerNetwork powerNetwork_param,PageRequest pageRequest);
	//局部修改
	public int updatePart(PowerNetwork powerNetwork);
	
	public List<Map<String, Object>> findPowerNetworkInfo(PowerNetwork powerNetwork_param);
	
	Integer findCountByPnbCusNumber(String pnbCusNumber);
}
