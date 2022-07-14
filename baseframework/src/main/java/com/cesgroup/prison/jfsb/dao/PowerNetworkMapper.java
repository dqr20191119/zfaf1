package com.cesgroup.prison.jfsb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.jfsb.entity.PowerNetwork;


/**   
*      
* @ClassName：PowerNetworkMapper   
* @Description：   
* @author：zhengk   
* @date：2018年1月16日 下午1:31:21        
*/
public interface PowerNetworkMapper extends BaseDao<PowerNetwork, String>{
	
	public Page<Map<String,String>> searchPowerNetwork(Map<String,Object> map,PageRequest pageRequest);
	//局部修改
	public int updatePart(PowerNetwork powerNetwork);
	
	public List<Map<String, Object>> findPowerNetworkInfo(Map<String,Object> map);

	/**
	 * 根据监狱编号、电力网络编号，查询电网数据列表
	 * 
	 * @param pnbCusNumber
	 * @param pnbIdnty
	 * @return
	 */
	public List<PowerNetwork> findByPnbCusNumberAndPnbIdnty(@Param("pnbCusNumber") String pnbCusNumber, @Param("pnbIdnty") String pnbIdnty);
	
	Integer findCountByPnbCusNumber(@Param("pnbCusNumber") String pnbCusNumber);
}
