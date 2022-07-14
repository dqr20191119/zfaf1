package com.cesgroup.prison.doorstate.dao;


import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.doorstate.entity.DoorStateEntity;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface DoorStateMapper extends BaseDao<DoorStateEntity, String> {
    int deleteByPrimaryKey(String id);

    int deleteByCusNumber(String num);

    void insert(DoorStateEntity record);

    void insertSelective(DoorStateEntity record);

    DoorStateEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DoorStateEntity record);

    int updateByPrimaryKey(DoorStateEntity record);
    //通过门禁状态查询门禁的数量
    int getDoorNumByStatus (@Param("cusNumber") String cusNumber,@Param("status") String status);
        //列表
    Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);
    
    List<DoorStateEntity> findByDsdCusNumberAndDsdDoorId(@Param("dsdCusNumber") String dsdCusNumber, @Param("dsdDoorId") String dsdDoorId);

    List<DoorStateEntity> listAllByDoorStateEntity(DoorStateEntity doorStateEntity);
}