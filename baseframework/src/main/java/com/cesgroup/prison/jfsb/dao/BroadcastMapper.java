package com.cesgroup.prison.jfsb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.jfsb.entity.BroadcastEntity;

/**      
* @projectName：prison   
* @ClassName：BroadcastMapper   
* @Description：  广播
* @author：Tao.xu   
* @date：2017年12月20日 下午3:30:11   
* @version        
*/
public interface BroadcastMapper extends BaseDao<BroadcastEntity, String> {

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteByIds(List<String> ids);

	/**
	 * 根据主键ID， 查询广播设备数据
	 * @param id
	 * @return
	 */
	BroadcastEntity findById(@Param("id") String id);
	
	/**
	 * 根据主键ID列表，查询广播设备列表
	 * @param idList
	 * @return
	 */
	List<BroadcastEntity> findByIdList(@Param("idList") List<String> idList);
}
