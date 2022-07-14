package com.cesgroup.prison.zbgl.mbsz.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.mbsz.entity.MbszEntity;

public interface MbszMapper extends BaseDao<MbszEntity, String> {

	public MbszEntity getById(String id);

	public Page<MbszEntity> findList(MbszEntity mbszEntity, PageRequest pageRequest);

	public List<MbszEntity> findAllList(MbszEntity mbszEntity); 
	
	public void deleteById(@Param("id") String id);

	public void updateSfqyById(MbszEntity mbszEntity);

	List<Map<String, Object>> findOrderData(String dbdDutyModeDepartmentId);

}
