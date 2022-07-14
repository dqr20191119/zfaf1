package com.cesgroup.prison.jfsb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.doorstate.entity.DoorStateEntity;
import com.cesgroup.prison.jfsb.entity.DoorEntity;

public interface DoorMapper extends BaseDao<DoorEntity, String> {

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteByIds(List<String> ids);

	/**
	 * Description: 根据多个门禁编号查询门禁列表
	 * @param ids
	 * @return
	 */
	List<DoorEntity> findByIds(List<String> ids);

	List<Map<String, Object>> findAllDoor(Map<String, Object> paramMap);

	List<Map<String, Object>> findAreaDepartment(Map<String, Object> paramMap);

	Map<String, Object> findDoorSum(Map<String, Object> paramMap);

	List<DoorEntity> findByDoorIDAndCusNumber(@Param("dcbIdnty") String dcbIdnty, @Param("cusNumber") String cusNumber);
}
