package com.cesgroup.prison.aqfk.monitor.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.aqfk.monitor.entity.Evidence;
import com.cesgroup.prison.aqfk.monitor.entity.MonitorDoc;

public interface EvidenceMapper extends BaseDao<Evidence, String> {
	public Page<Map<String, String>> listEvidence(Map<String, Object> map, Pageable pageable);

	public int updatePart(Evidence record);

	public Map<String, Object> searchVideo(Map<String, Object> map);

	public Map<String, Object> searchImage(Map<String, Object> map);

	// 批量删除
	public int batchDelete(List<String> list);

	/**
	* @methodName: findByIds 
	* @Description: 根据ids查找记录 add by tao
	* @param ids
	* @return List<Map<String,Object>>
	* @throws  
	*/
	List<Map<String, Object>> findByIds(List<String> ids);

    Evidence getNowEvidence(@Param("cusNumber") String cusNumber);
}
