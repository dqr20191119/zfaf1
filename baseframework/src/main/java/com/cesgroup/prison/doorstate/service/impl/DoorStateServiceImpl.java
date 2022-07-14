package com.cesgroup.prison.doorstate.service.impl;

import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.doorstate.dao.DoorStateMapper;
import com.cesgroup.prison.doorstate.entity.DoorStateEntity;
import com.cesgroup.prison.doorstate.service.DoorStateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：zhouzc.
 * @ Date       ：Created in 10:54 2019/5/24
 * @ Description：门禁状态实现类
 */
@Service("doorStateService")
public class DoorStateServiceImpl implements DoorStateService {

    @Resource
    private DoorStateMapper doorStateMapper;

    @Override
    public Integer getDoorNumByStatus(String cusNumber,String status) {
        UserBean userBean = AuthSystemFacade.getLoginUserInfo();
        if (userBean != null) {
            cusNumber =userBean.getCusNumber();
        }
        return doorStateMapper.getDoorNumByStatus(cusNumber,status);
    }

    //加载list列表
    @Override
    public Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable) {
        try {
            UserBean userBean = AuthSystemFacade.getLoginUserInfo();
            if (userBean != null) {
                map.put("dsdCusNumber", userBean.getCusNumber());
            }
            map.put("dsdUpdtTime", Util.toStr(Util.DF_DATE));
            return doorStateMapper.listAll(map, pageable);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<DoorStateEntity> listAllByDoorStateEntity(DoorStateEntity doorStateEntity) {
        //进行状态转码
        List<DoorStateEntity> doorStateEntities = doorStateMapper.listAllByDoorStateEntity(doorStateEntity);
        List<DoorStateEntity> doorList = new ArrayList<DoorStateEntity>();
        for(DoorStateEntity door:doorStateEntities){
            //门禁状态:0开1关2超时3戒严4离线5其它
            if("0".equals(door.getDsdDoorState())){
                door.setDsdDoorState("开");
            }else if("1".equals(door.getDsdDoorState())){
                door.setDsdDoorState("关");
            }else if("2".equals(door.getDsdDoorState())){
                door.setDsdDoorState("超时");
            }else if("3".equals(door.getDsdDoorState())){
                door.setDsdDoorState("戒严");
            }else if("4".equals(door.getDsdDoorState())){
                door.setDsdDoorState("离线");
            }else if("5".equals(door.getDsdDoorState())){
                door.setDsdDoorState("其它");
            }
            doorList.add(door);
        }

        return doorList;
    }

    @Override
    public DoorStateEntity insert(DoorStateEntity entity) {
        return null;
    }

    @Override
    public DoorStateEntity update(DoorStateEntity entity) {
        return null;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public List<DoorStateEntity> selectAll() {
        return null;
    }

    @Override
    public DoorStateEntity selectOne(String s) {
        return null;
    }

    @Override
    public long selectCount() {
        return 0;
    }

    @Override
    public List<DoorStateEntity> findAll(Specification<DoorStateEntity> spec) {
        return null;
    }

    @Override
    public Page<DoorStateEntity> findPage(Specification<DoorStateEntity> spec, Pageable page) {
        return null;
    }
}
