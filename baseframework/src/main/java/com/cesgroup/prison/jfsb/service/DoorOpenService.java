package com.cesgroup.prison.jfsb.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.jfsb.entity.DoorOpenEntity;

public interface DoorOpenService  extends IBaseCRUDService<DoorOpenEntity, String>{

	void updateInfo(DoorOpenEntity entity) throws Exception;
	
	Page<Map<String, Object>> listAll(DoorOpenEntity entity, Pageable pageable)throws Exception;

	DoorOpenEntity getByjyid(String jyid);
	
}
