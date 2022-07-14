package com.cesgroup.prison.doorstate.service;

/**
 * Created byzhouzc on2019/5/24
 **/

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.doorstate.entity.DoorStateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface DoorStateService extends IBaseCRUDService<DoorStateEntity, String>{

    public Integer getDoorNumByStatus(String cusNumber,String status);

    public Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

    List<DoorStateEntity> listAllByDoorStateEntity(DoorStateEntity doorStateEntity);
}
