package com.cesgroup.prison.jfsb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.jfsb.entity.AlertorEntity;

/**      
* @projectName：prison   
* @ClassName：AlertorMapper   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月12日 下午3:45:53   
* @version        
*/
public interface AlertorMapper extends BaseDao<AlertorEntity, String> {

	/**
	 * 根据主键ID，查询报警设备实体类
	 * @param id
	 * @return
	 */
	AlertorEntity findById(@Param("id") String id);

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteByIds(List<String> ids);

	List<Map<String, Object>> searchCombData(Map<String, Object> map);

	Map<String, Object> findAlertorSum(Map<String, Object> paramMap);
	
	/**
	 * 根据第三方报警设备ID，查询该设备在本系统的存储数据
	 * @param abdIdnty
	 * @return
	 */
	List<AlertorEntity> findByAbdIdnty(@Param("abdIdnty") String abdIdnty, @Param("cusNumber") String cusNumber);
}
