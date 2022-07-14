package com.cesgroup.prison.doorstate.service;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.doorstate.bean.DoorStateBean;
import com.cesgroup.prison.doorstate.dao.DoorStateMapper;
import com.cesgroup.prison.doorstate.entity.DoorStateEntity;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.fm.util.MsgTypeConst;
import com.cesgroup.prison.jfsb.dao.DoorMapper;
import com.cesgroup.prison.jfsb.entity.AlertorEntity;
import com.cesgroup.prison.jfsb.entity.DoorEntity;
import com.cesgroup.prison.utils.CommonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ Author     ：zhouzc.
 * @ Date       ：Created in 13:54 2019/5/22
 * @ Description：门禁状态
 */

@Service
@Transactional
public class DoorStateProcess implements IMessageProcess {
    private final Logger logger = LoggerFactory.getLogger(DoorStateProcess.class);

    @Resource
    private DoorStateMapper doorStateMapper;
    @Resource
    private DoorMapper doorMapper;

    /**
     * 门禁状态参数接收
     *
     * @param cusNumber  机构号
     * @param jsonObject 消息-JSON对象
     */
    /*@Transactional
    @Override
    public void processMessage(String cusNumber, JSONObject jsonObject) {

        // 将普通的阿里JSONObject对象转化为谷歌JsonObject
        JsonObject gsonObject = Util.fromJson(JSONObject.toJSONString(jsonObject), JsonObject.class);
        // 从gsonObject中取出header，并转化为MsgHeader兑现
        MsgHeader msgHead = gsonObject != null ? Util.fromJson(gsonObject.get("header"), MsgHeader.class) : null;
        // 从msgHead中获取msgType消息类型
        String msgType = msgHead != null ? msgHead.getMsgType() : "";
        if (MsgTypeConst.DOORSTATE_IN.equals(msgType)) {
            logger.info("收到门禁状态信息：" + jsonObject.toJSONString());
            try {
                //从gsonObject中获取body
                JsonArray body = gsonObject.getAsJsonArray("body");
                if (body == null) {
                    return;
                }
                // 删除
                doorStateMapper.deleteByCusNumber(cusNumber);
                for (int i = 0; i < body.size(); i++) {
                    JsonObject doorStateJson = body.get(i).getAsJsonObject();
                    if (doorStateJson == null) {
                        continue;
                    }
                    // 将body中的消息转化为DoorStateBean对象
                    DoorStateBean doorStateBean = Util.fromJson(doorStateJson, DoorStateBean.class);
                    if (doorStateBean == null) {
                        return;
                    }
                    
                    //完善门禁状态信息 add by zk
                    perfectDoorStateBean(doorStateBean,cusNumber);
                    
                    
                    //格式化为DoorStateEntity
                    DoorStateEntity doorStateEntity = this.convertDoorStateBeanToDoorStateEntity(doorStateBean);
                    doorStateEntity.setDsdCusNumber(cusNumber);
                    if (doorStateEntity != null) {
                        // 添加门禁
                       doorStateMapper.insertSelective(doorStateEntity);
                    }
                }

            } catch (Exception e) {
                    logger.error("更新门禁状态数据发生异常，原因："+e);
            }


        }


    }*/
    //因为前置机无法一次推送监狱全部的门禁状态，所以在此进行改造
    @Transactional
    @Override
    public void processMessage(String cusNumber, JSONObject jsonObject) { 

        // 将普通的阿里JSONObject对象转化为谷歌JsonObject
        JsonObject gsonObject = Util.fromJson(JSONObject.toJSONString(jsonObject), JsonObject.class);
        // 从gsonObject中取出header，并转化为MsgHeader兑现
        MsgHeader msgHead = gsonObject != null ? Util.fromJson(gsonObject.get("header"), MsgHeader.class) : null;
        // 从msgHead中获取msgType消息类型
        String msgType = msgHead != null ? msgHead.getMsgType() : "";
        if (MsgTypeConst.DOORSTATE_IN.equals(msgType)) {
            logger.info("收到门禁状态信息：" + jsonObject.toJSONString());
            try {
                //从gsonObject中获取body
                JsonArray body = gsonObject.getAsJsonArray("body");
                if (body == null) {
                    return;
                }
                
                for (int i = 0; i < body.size(); i++) {
                    JsonObject doorStateJson = body.get(i).getAsJsonObject();
                    if (doorStateJson == null) {
                        continue;
                    }
                    // 将body中的消息转化为DoorStateBean对象
                    DoorStateBean doorStateBean = Util.fromJson(doorStateJson, DoorStateBean.class);
                    if (doorStateBean == null) {
                        return;
                    }
                    
                    //查询门禁是否存在，存在则更新状态，不存在则新增状态
                    List<DoorStateEntity> doorStatelist = doorStateMapper.findByDsdCusNumberAndDsdDoorId(cusNumber,doorStateBean.getDoorID());
                    if(doorStatelist != null && doorStatelist.size() > 0) {
                    	DoorStateEntity doorStateEntity = new DoorStateEntity();
                        doorStateEntity.setId(doorStatelist.get(0).getId());
                        doorStateEntity.setDsdDoorState(doorStateBean.getState());//门禁状态
                        doorStateEntity.setDsdUpdtTime(new Date());
                        doorStateMapper.updateByPrimaryKeySelective(doorStateEntity);
                    }
                    else {
                    	 //完善门禁状态信息 add by zk
                        perfectDoorStateBean(doorStateBean,cusNumber);
                        
                        
                        //格式化为DoorStateEntity
                        DoorStateEntity doorStateEntity = this.convertDoorStateBeanToDoorStateEntity(doorStateBean);
                        doorStateEntity.setDsdCusNumber(cusNumber);
                        if (doorStateEntity != null) {
                            // 添加门禁
                           doorStateMapper.insertSelective(doorStateEntity);
                        }
                    }	
  
                }

            } catch (Exception e) {
                    logger.error("更新门禁状态数据发生异常，原因："+e);
            }


        }


    }

    /**
     * 门禁消息转为Entity
     */
    private DoorStateEntity convertDoorStateBeanToDoorStateEntity(DoorStateBean doorStateBean) {

        if (doorStateBean == null) {
            return null;
        }
        DoorStateEntity doorStateEntity = new DoorStateEntity();
        doorStateEntity.setId(CommonUtil.createUUID());
        doorStateEntity.setDsdDoorId(doorStateBean.getDoorID());//门禁编号
        doorStateEntity.setDsdDoorName(doorStateBean.getDoorName());//门禁名称
        doorStateEntity.setDsdRoomId(doorStateBean.getRoomId());//门禁所属房间编号
        doorStateEntity.setDsdDeptId(doorStateBean.getDept());//所属部门
        doorStateEntity.setDsdDoorState(doorStateBean.getState());//门禁状态
        doorStateEntity.setDsdUpdtTime(new Date());
        return doorStateEntity;
    }
    
    /**
     * 完善DoorStateBean，推送过来的数据只有，门的编号、状态、监狱编号
     */
    private void perfectDoorStateBean(DoorStateBean doorStateBean,String cusNumber) {
    	DoorEntity doorEntity = null;
		if(doorStateBean != null && Util.notNull(doorStateBean.getDoorID())) {
    		List<DoorEntity> doorEntityList = this.doorMapper.findByDoorIDAndCusNumber(doorStateBean.getDoorID(),cusNumber);
    		if(doorEntityList != null && doorEntityList.size() > 0) {
    			doorEntity = doorEntityList.get(0);
    			
    			doorStateBean.setDoorName(doorEntity.getDcbName());
    			doorStateBean.setRoomId(doorEntity.getDcbRoomId());
    			doorStateBean.setDept(doorEntity.getDcbDprtmnt());
    			
    		}
    	}
		
    }
}
