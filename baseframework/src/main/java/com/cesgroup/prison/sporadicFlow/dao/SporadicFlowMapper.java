package com.cesgroup.prison.sporadicFlow.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.sporadicFlow.entity.SporadicFlowRegisterEntity;

/**      
* @projectName：prison   
* @ClassName：SporadicFlowRegisterMapper   
* @Description：   零星流动
* @author：Tao.xu   
* @date：2017年12月7日 下午3:53:36   
* @version        
*/
public interface SporadicFlowMapper extends BaseDao<SporadicFlowRegisterEntity, String> {

	/**
	* @methodName: listAll
	* @Description: TODO
	* @param map
	* @param pageable
	* @return Page<Map<String,Object>>    
	* @throws
	*/
	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	/**
	* @methodName: updateSoradicFlowInfo
	* @Description: 更新订单状态
	* @param map void    
	* @throws
	*/
	void updateSoradicFlowInfo(Map<String, Object> map);

	/**
	* @methodName: deleteById
	* @Description:根据id删记录
	* @param list void    
	* @throws
	*/
	void deleteByIds(List<String> list);

	/**
	* @methodName: findById
	* @Description: 根据id查询
	* @param id void    
	* @throws
	*/
	Map<String, Object> findById(Map<String, Object> map);

	void insertSporadicFlowRegister(SporadicFlowRegisterEntity entity);

}
