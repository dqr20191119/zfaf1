package com.cesgroup.prison.jfsb.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.jfsb.entity.DoorOpenEntity;;

public interface DoorOpenMapper extends BaseDao<DoorOpenEntity, String> {

	void updateInfo(DoorOpenEntity entity);

	Page<Map<String,Object>> listAll(DoorOpenEntity entity, Pageable pageable);

	DoorOpenEntity getByjyid(@Param("jyid")String jyid);
	
}
