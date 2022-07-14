package com.cesgroup.prison.jfsb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.jfsb.dao.PowerNetworkMapper;
import com.cesgroup.prison.jfsb.entity.PowerNetwork;
import com.cesgroup.prison.jfsb.service.PowerNetworkService;


/**   
*      
* @ClassName：PowerNetworkServiceImpl   
* @Description：   
* @author：zhengk   
* @date：2018年1月16日 下午1:32:24        
*/
@Service
public class PowerNetworkServiceImpl extends BaseDaoService<PowerNetwork,String,PowerNetworkMapper> implements PowerNetworkService{

	public Page<Map<String,String>> searchPowerNetwork(PowerNetwork powerNetwork_param,PageRequest pageRequest){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("powerNetwork", powerNetwork_param);
		return getDao().searchPowerNetwork(map,pageRequest);
	}
	
	//局部修改
	@Transactional
	public int updatePart(PowerNetwork powerNetwork_param){
		return getDao().updatePart(powerNetwork_param);
	}

	public List<Map<String, Object>> findPowerNetworkInfo(PowerNetwork powerNetwork_param){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("powerNetwork", powerNetwork_param);
		return getDao().findPowerNetworkInfo(map);
	}
	public Integer findCountByPnbCusNumber(String pnbCusNumber) {
		return getDao().findCountByPnbCusNumber(pnbCusNumber);
	}
}
