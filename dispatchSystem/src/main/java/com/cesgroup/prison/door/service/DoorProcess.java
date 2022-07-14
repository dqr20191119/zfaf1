package com.cesgroup.prison.door.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.constant.AuthSystemConst;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.constants.socket.IMsgTypeConst;
import com.cesgroup.prison.common.facade.MessageSendFacade;
import com.cesgroup.prison.door.bean.DoorCardBean;
import com.cesgroup.prison.door.bean.DoorReturnStatusBean;
import com.cesgroup.prison.door.bean.PoliceInoutBean;
import com.cesgroup.prison.door.dao.DoorCardHisMapper;
import com.cesgroup.prison.door.dao.DoorCardMapper;
import com.cesgroup.prison.door.dao.PoliceInoutHisMapper;
import com.cesgroup.prison.door.dao.PoliceInoutMapper;
import com.cesgroup.prison.door.dao.PoliceInprsnMapper;
import com.cesgroup.prison.door.entity.DoorCardEntity;
import com.cesgroup.prison.door.entity.PoliceInoutEntity;
import com.cesgroup.prison.door.entity.PoliceInoutHisEntity;
import com.cesgroup.prison.door.entity.PoliceInprsnEntity;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.fm.util.MsgTypeConst;
import com.cesgroup.prison.jfsb.dao.DoorMapper;
import com.cesgroup.prison.jfsb.entity.DoorEntity;
import com.cesgroup.prison.location.entity.PoliceLocation;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.cesgroup.prison.orgMapping.dao.CescodeContrastDaqiDao;
import com.cesgroup.prison.orgMapping.entity.CescodeContrastDaqi;
import com.google.gson.JsonObject;

/**
 * 门禁消息的处理类
 * @author zxh
 *
 */
@Service
public class DoorProcess implements IMessageProcess {
	
	private final Logger logger = LoggerFactory.getLogger(DoorProcess.class);
	
	@Resource
	private PoliceInoutMapper policeInoutMapper;
	@Resource
	private PoliceInoutHisMapper policeInoutHisMapper;
	@Resource
	private PoliceInprsnMapper policeInprsnMapper;
	@Resource
	private DoorCardMapper doorCardMapper;
	@Resource
	private DoorCardHisMapper doorCardHisMapper;
	@Resource
	private MqMessageSender mqMessageSender;
	
	@Resource
	private DoorMapper doorMapper;
    /**
     * 中信与大旗机构编码映射Dao
     */
    @Resource
    private CescodeContrastDaqiDao cescodeContrastDaqiDao;

	@Transactional
	@Override
	public void processMessage(String cusNumber, JSONObject jsonObject) {
		// 将普通的JSONObject对象转化为JsonObject
    	JsonObject gsonObject = Util.fromJson(JSONObject.toJSONString(jsonObject), JsonObject.class);
    	
    	// 从gsonObject中取出header，并转化为MsgHeader兑现
        MsgHeader msgHead = gsonObject != null ? Util.fromJson(gsonObject.get("header"), MsgHeader.class) : null;
        
        // 从msgHead中获取msgType消息类型
        String msgType = msgHead != null ? msgHead.getMsgType() : "";
        
		if(MsgTypeConst.POLICE_INOUT.equals(msgType)) { 
			logger.info("收到警员进出监区信息：" + jsonObject.toJSONString());
			
			// 从gsonObject中获取body
			JsonObject body = gsonObject.getAsJsonObject("body");
        	if(body == null) {
        		return;
        	}
        	// json转化为PoliceInoutBean 
			PoliceInoutBean policeInoutBean = Util.fromJson(body, PoliceInoutBean.class); 
			if(policeInoutBean == null) {
				return;
			}
			
			String inoutFlag = policeInoutBean.getInOutFlag();		// 进出标识
			// 如果人员ID为空，则不继续执行
			if(policeInoutBean.getPeopleID() == null || "".equals(policeInoutBean.getPeopleID())) {
				return;
			}
			/*if(CommonConstant.InOutFlag.IN.equals(inoutFlag) && "转闸进".equals(policeInoutBean.getDoorName())) {
				// 监狱大门：转闸进，用于记录民警进入监狱
				this.policeEnterPrison(cusNumber, policeInoutBean);				
			} else if(CommonConstant.InOutFlag.OUT.equals(inoutFlag) && "转闸出".equals(policeInoutBean.getDoorName())) {
				// 监狱大门：转闸出，用于记录民警离开监狱
 				this.policeLeavePrison(cusNumber, policeInoutBean);		 
			} */
			
			//是否ab门，即进出监狱的门
			String aBFlag = "";
			
			/**
			 * 第一次改造，ab门标识由安防平台维护，但是发现，ab门的情况比较复杂，不能确认湖南各家监狱的进出监狱的门是哪一个，
			 * 另外一个问题，转闸门出，推过来的数据进出标识是进，因为前置机只有开关的概念
			 * **/
			/*List<DoorEntity> doorList = doorMapper.findByDoorIDAndCusNumber(policeInoutBean.getDoorID(), cusNumber);
			if(doorList != null && doorList.size() > 0) {
				aBFlag = doorList.get(0).getDcbIsAb();
			}
			if(CommonConstant.InOutFlag.IN.equals(inoutFlag) && CommonConstant.ABFlag.YES.equals(aBFlag)) {
				// 监狱大门：转闸进，用于记录民警进入监狱
				this.policeEnterPrison(cusNumber, policeInoutBean);				
			} else if(CommonConstant.InOutFlag.OUT.equals(inoutFlag) && CommonConstant.ABFlag.YES.equals(aBFlag)) {
				// 监狱大门：转闸出，用于记录民警离开监狱
 				this.policeLeavePrison(cusNumber, policeInoutBean);		 
			} 
			*/
			
	
			/**
			 * 第二次改造，湖南 这边没有ab门标识，所以把换证记录推送过来，当作进出ab门标识，换证推过来的数据门禁ID为 ABDoor, ABFlag为 1
			 * 但是换证数据里面没有民警编号，所以重名的会有问题
			 * 
			 * 女子监狱（纽贝尔门禁）特殊，没有换卡数据
			 * **/
			/*
			if("4307".equals(cusNumber)) {
				List<DoorEntity> doorList = doorMapper.findByDoorIDAndCusNumber(policeInoutBean.getDoorID(), cusNumber);
				if(doorList != null && doorList.size() > 0) {
					aBFlag = doorList.get(0).getDcbIsAb();
				}
				if(CommonConstant.InOutFlag.IN.equals(inoutFlag) && CommonConstant.ABFlag.YES.equals(aBFlag)) {
					// 监狱大门：转闸进，用于记录民警进入监狱
					this.policeEnterPrison(cusNumber, policeInoutBean);				
				} else if(CommonConstant.InOutFlag.OUT.equals(inoutFlag) && CommonConstant.ABFlag.YES.equals(aBFlag)) {
					// 监狱大门：转闸出，用于记录民警离开监狱
	 				this.policeLeavePrison(cusNumber, policeInoutBean);		 
				} 
			}else {
				aBFlag = policeInoutBean.getABFlag();
				if(CommonConstant.InOutFlag.IN.equals(inoutFlag) && CommonConstant.ABFlag.YES.equals(aBFlag)) {
					// 监狱大门：转闸进，用于记录民警进入监狱
					this.policeEnterPrison_hn(cusNumber, policeInoutBean);				
				} else if(CommonConstant.InOutFlag.OUT.equals(inoutFlag) && CommonConstant.ABFlag.YES.equals(aBFlag)) {
					// 监狱大门：转闸出，用于记录民警离开监狱
	 				this.policeLeavePrison_hn(cusNumber, policeInoutBean);		 
				} 
				
				else {
					// 监狱内普通门禁，用于记录民警位置变动
					
					// 将新的民警进出记录数据保存到民警进出门禁记录表
					PoliceInoutEntity policeInoutEntity_op = this.convertPoliceInoutBeanToPoliceInoutEntity(cusNumber, policeInoutBean);
					if(policeInoutEntity_op != null) {
						// 首先在民警在监记录表中查询民警进监区时间
						PoliceInprsnEntity policeInprsn = this.queryPoliceInprsnByCusNumberAndPoliceId(cusNumber, policeInoutBean.getPeopleID());
						if(policeInprsn != null) {
							policeInoutEntity_op.setPirEnterTime(policeInprsn.getCipEnterTime());
						}
						policeInoutEntity_op.setPirCrteTime(new Date());
						policeInoutEntity_op.setPirCrteUserId("0");
						policeInoutEntity_op.setPirUpdtTime(new Date());
						policeInoutEntity_op.setPirUpdtUserId("0");
						this.policeInoutMapper.insert(policeInoutEntity_op);
					}
					
					// 将新的民警进出记录数据保存到民警进出门禁记录表
					PoliceInoutEntity policeInoutEntity_op = this.convertPoliceInoutBeanToPoliceInoutEntity(cusNumber, policeInoutBean);
					if(policeInoutEntity_op != null) {
						
						policeInoutEntity_op.setPirCrteTime(new Date());
						policeInoutEntity_op.setPirCrteUserId("0");
						policeInoutEntity_op.setPirUpdtTime(new Date());
						policeInoutEntity_op.setPirUpdtUserId("0");
						this.policeInoutMapper.insert(policeInoutEntity_op);
					}
				}
			}*/
			List<DoorEntity> doorList = null;
			if("4312".equals(cusNumber)) {
				DoorEntity bean = new DoorEntity();
				bean.setDcbCusNumber(cusNumber);
				bean.setDcbName(policeInoutBean.getDoorID());
				doorList = doorMapper.selectByEntity(bean);
			} else {
				doorList = doorMapper.findByDoorIDAndCusNumber(policeInoutBean.getDoorID(), cusNumber);
			}
			
			if(doorList != null && doorList.size() > 0) {
				aBFlag = doorList.get(0).getDcbIsAb();
			} else if(policeInoutBean.getDoorID() != null && ( policeInoutBean.getDoorID().equals("ABDoor_in") || policeInoutBean.getDoorID().equals("ABDoor_out")) ){
				aBFlag = policeInoutBean.getInOutFlag();
			}
			//判断ab门的进出状态， 如果此门设置的是AB门进，那么进入进的方法 ； 如果是AB门出，那么进入chu的方法
			if( CommonConstant.ABFlag.NO.equals(aBFlag)) { 
				// 监狱大门：转闸进，用于记录民警进入监狱
				policeInoutBean.setInOutFlag(CommonConstant.InOutFlag.IN);
				this.policeEnterPrison(cusNumber, policeInoutBean);				
			} else if( CommonConstant.ABFlag.YES.equals(aBFlag)) {
				// 监狱大门：转闸出，用于记录民警离开监狱
				policeInoutBean.setInOutFlag(CommonConstant.InOutFlag.OUT);
 				this.policeLeavePrison(cusNumber, policeInoutBean);		 
			} else { //如果不是AB门进，  也不是AB门出 
				// 将新的民警进出记录数据保存到民警进出门禁记录表
				PoliceInoutEntity policeInoutEntity_op = this.convertPoliceInoutBeanToPoliceInoutEntity(cusNumber, policeInoutBean);
				if(policeInoutEntity_op != null) {
					
					policeInoutEntity_op.setPirCrteTime(new Date());
					policeInoutEntity_op.setPirCrteUserId("0");
					policeInoutEntity_op.setPirUpdtTime(new Date());
					policeInoutEntity_op.setPirUpdtUserId("0");
					this.policeInoutMapper.insert(policeInoutEntity_op);
				}
			}
			
			
			
			// 往前台推送消息---当前在监民警实时变动
			sendPoliceInprsnNum(cusNumber);
		} else if(MsgTypeConst.DOOR_CARD.equals(msgType)) {
			logger.info("收到门禁刷卡信息：" + jsonObject.toJSONString());

			// 从gsonObject中获取body
			JsonObject body = gsonObject.getAsJsonObject("body");
        	if(body == null) {
        		return;
        	}
        	// json转化为DoorCardBean
        	DoorCardBean doorCardBean = Util.fromJson(body, DoorCardBean.class);
			if(doorCardBean == null) {
				return;
			}
			
			//如果人员ID为空，则不继续执行
			if(doorCardBean.getPeopleID() == null || "".equals(doorCardBean.getPeopleID())) {
				return;
			}
 			insertDoorCard(cusNumber, doorCardBean);
		} else if(MsgTypeConst.DOOR_RETURN_STATUS.equals(msgType)) {
			logger.info("收到开关门返回指令信息：" + jsonObject.toJSONString());
			
			DoorReturnStatusBean doorReturnStatusBean = JSON.toJavaObject(jsonObject.getJSONObject("body"), DoorReturnStatusBean.class);
			
			try {
				// 向前台发送消息
				Map<String, String> msgMap = new HashMap<String, String>();
				msgMap.put("msgType", IMsgTypeConst.MSG_DOOR_RETURN_STATUS);
				msgMap.put("sendType", "2");
				msgMap.put("sendTo", doorReturnStatusBean.getUserID());
				msgMap.put("content", JSONObject.toJSONString(jsonObject.getJSONObject("body")));
				msgMap.put("isSendToGzt", "0");
				msgMap.put("url", null);
				msgMap.put("ywId", null);
				msgMap.put("cusNumber", cusNumber);
				msgMap.put("noticeContent", "收到开关门返回指令信息");
				MessageSendFacade.send(msgMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		 
	}
	
	/**
	 * 向前台发送民警在监数据
	 * @param cusNumber
	 */
	public void sendPoliceInprsnNum(String cusNumber) {
		
		try {
			PoliceInprsnEntity policeInprsnEntity = new PoliceInprsnEntity();
			policeInprsnEntity.setCipCusNumber(cusNumber);
			List<Map<String, Object>> rtnList = policeInprsnMapper.countPoliceInprsn(policeInprsnEntity);
			
			AjaxMessage ajaxMsg = new AjaxMessage();				
			JSONObject rtnData = new JSONObject();
			rtnData.put("data", rtnList);
			ajaxMsg.setObj(rtnData);
			ajaxMsg.setSuccess(true);
			
			// 向前台发送消息
			Map<String, String> msgMap = new HashMap<String, String>();
			msgMap.put("msgType", IMsgTypeConst.CURRENT_POLICE_COUNT);
			msgMap.put("sendType", "1");
			msgMap.put("sendTo", cusNumber + ","+AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG);
			msgMap.put("content", JSONObject.toJSONString(ajaxMsg));
			msgMap.put("isSendToGzt", "0");
			msgMap.put("url", null);
			msgMap.put("ywId", null);
			msgMap.put("cusNumber", cusNumber);
			msgMap.put("noticeContent", "当前在监民警数据");
			MessageSendFacade.send(msgMap);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	/**
	 * Description: 插入门禁刷卡记录
	 * @param cusNumber
	 * @param doorCardBean
	 */
	public void insertDoorCard(String cusNumber, DoorCardBean doorCardBean) {
		try {
			// 迁移刷卡历史数据
			DoorCardEntity doorCardEntity = new DoorCardEntity();
			doorCardEntity.setDrdCusNumber(cusNumber);
			doorCardEntity.setDrdPeopleTypeIndc(doorCardBean.getPeopleType());	
			doorCardEntity.setDrdPeopleIdnty(doorCardBean.getPeopleID());
			doorCardHisMapper.saveBySelect(doorCardEntity);
			
			// 删除刷卡数据
			doorCardMapper.deleteByCondition(doorCardEntity);
			
			// 插入刷卡数据
			DoorCardEntity saveDoorCard = new DoorCardEntity();
			saveDoorCard.setDrdCusNumber(cusNumber);
			saveDoorCard.setDrdBsnsDate(new Date());
			saveDoorCard.setDrdDoorIdnty(doorCardBean.getDoorID());
			saveDoorCard.setDrdDoorName(doorCardBean.getDoorName());
			saveDoorCard.setDrdCardIdnty(doorCardBean.getCardID());
			saveDoorCard.setDrdInOutIndc(doorCardBean.getInOutFlag());
			saveDoorCard.setDrdBrushCardTime(Util.toDate(doorCardBean.getBrushCardTime(), Util.DF_TIME));
			saveDoorCard.setDrdStatusIndc(doorCardBean.getStatus());
			saveDoorCard.setDrdPeopleTypeIndc(doorCardBean.getPeopleType());
			saveDoorCard.setDrdRemark(doorCardBean.getRemark());
			saveDoorCard.setDrdCrteTime(new Date());
			saveDoorCard.setDrdCrteUserId("0");	
			saveDoorCard.setDrdPeopleIdnty(doorCardBean.getPeopleID());
			saveDoorCard.setDrdPeopleName(doorCardBean.getPeopleName());
			saveDoorCard.setDrdDoorRoomId(doorCardBean.getJsNo());
			// 部门编号
	    	String deptNo = doorCardBean.getDeptID();
	    	// 根据部门编号，到中信与大旗映射表中查找安防平台对应的机构信息
	    	if(Util.notNull(deptNo)) {
	    		CescodeContrastDaqi cescodeContrastDaqi = this.cescodeContrastDaqiDao.findByDaqiKey(deptNo);
	    		if(cescodeContrastDaqi != null) {
	    			saveDoorCard.setDrdDoorDeptId(cescodeContrastDaqi.getCesKey());
	    			saveDoorCard.setDrdDoorDeptName(cescodeContrastDaqi.getCesName());
	    		}
	    	}
	    	saveDoorCard.setDrdPeopleDeptName(doorCardBean.getSecName());
	    	
			doorCardMapper.insert(saveDoorCard);
		} catch (Exception e) {
			logger.error(e.toString(), e.fillInStackTrace());
		}
	}
	
	/**
	 * 民警进入监狱
	 * 
	 * @param cusNumber
	 * @param policeInoutBean
	 */
	private void policeEnterPrison(String cusNumber, PoliceInoutBean policeInoutBean) {
		PoliceInoutEntity policeInoutEntity = new PoliceInoutEntity();
		policeInoutEntity.setPirCusNumber(cusNumber);
		policeInoutEntity.setPirPoliceIdnty(policeInoutBean.getPeopleID());
		policeInoutEntity.setNotNullEnterTime("1");
		policeInoutEntity.setNotNullLeaveTime("0");
		
		// 迁移民警进出门禁历史数据至民警进出门禁记录历史表
		policeInoutHisMapper.saveBySelect(policeInoutEntity);
		
		// 删除民警进出门禁记录表中的历史数据
		policeInoutMapper.deleteByCondition(policeInoutEntity);
		
		// 将新的民警进出记录数据保存到民警进出门禁记录表
		PoliceInoutEntity policeInoutEntity_op = this.convertPoliceInoutBeanToPoliceInoutEntity(cusNumber, policeInoutBean);
		if(policeInoutEntity_op != null) {
			policeInoutEntity_op.setPirCrteTime(new Date());
			policeInoutEntity_op.setPirCrteUserId("0");
			policeInoutEntity_op.setPirUpdtTime(new Date());
			policeInoutEntity_op.setPirUpdtUserId("0");
			this.policeInoutMapper.insert(policeInoutEntity_op);
		}
		
		// 判断民警当前所在监区记录表中是否有该民警的记录
		PoliceInprsnEntity policeInprsnEntity_op = this.queryPoliceInprsnByCusNumberAndPoliceId(cusNumber, policeInoutBean.getPeopleID());
		if(policeInprsnEntity_op != null) {
			// 更新进入时间
			policeInprsnEntity_op.setCipEnterTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
			policeInprsnMapper.update(policeInprsnEntity_op);
		} else {
			// 新增民警在监记录
			policeInprsnEntity_op = new PoliceInprsnEntity();
			policeInprsnEntity_op.setCipPoliceIdnty(policeInoutBean.getPeopleID());
			policeInprsnEntity_op.setCipCusNumber(cusNumber);
			policeInprsnEntity_op.setCipPoliceName(policeInoutBean.getPeopleName());
			policeInprsnEntity_op.setCipDoorCardIdnty(policeInoutBean.getCardID());
			policeInprsnEntity_op.setCipDoorIdnty(policeInoutBean.getDoorID());		
			policeInprsnEntity_op.setCipEnterTime(Util.toDate(policeInoutBean.getCompareTime(), Util.DF_TIME));
			policeInprsnEntity_op.setCipCrteTime(new Date());
			policeInprsnEntity_op.setCipCrteUserId("0");
			policeInprsnEntity_op.setCipUpdtTime(new Date());
			policeInprsnEntity_op.setCipUpdtUserId("0");
			policeInprsnEntity_op.setCipDataSource("1");	
			policeInprsnEntity_op.setCipPeopleTypeIndc(policeInoutBean.getPeopleType());
			policeInprsnMapper.insert(policeInprsnEntity_op);	
		}
	}
	
	/**
	 * 民警离开监狱
	 * 
	 * @param cusNumber
	 * @param policeInoutBean
	 */
	private void policeLeavePrison(String cusNumber, PoliceInoutBean policeInoutBean) {
		// 根据监狱编号、民警编号，查询该民警离开时间为空的进出记录数据
		PoliceInoutEntity policeInoutEntity = new PoliceInoutEntity();
		policeInoutEntity.setPirCusNumber(cusNumber);
		policeInoutEntity.setPirPoliceIdnty(policeInoutBean.getPeopleID());
		policeInoutEntity.setNotNullLeaveTime("0");
		List<PoliceInoutEntity> policeInoutList = policeInoutMapper.findAllList(policeInoutEntity);
		
		if(policeInoutList != null && policeInoutList.size() > 0) {
			// 更新离监时间
			policeInoutEntity.setPirLeaveTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
			policeInoutMapper.updateByCondition(policeInoutEntity);
		}
		
		// 根据监狱编号、民警编号，查询该民警离开时间为空的历史进出记录数据
		PoliceInoutHisEntity policeInoutHisEntity = new PoliceInoutHisEntity();
		policeInoutHisEntity.setPirCusNumber(cusNumber);
		policeInoutHisEntity.setPirPoliceIdnty(policeInoutBean.getPeopleID());
		policeInoutHisEntity.setNotNullLeaveTime("0");
		List<PoliceInoutHisEntity> policeInoutHisList = policeInoutHisMapper.findAllList(policeInoutHisEntity);

		if(policeInoutHisList != null && policeInoutHisList.size() > 0) {
			// 更新离监时间
			policeInoutHisEntity.setPirLeaveTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
			policeInoutHisMapper.updateByCondition(policeInoutHisEntity);
		}
		
		// 将新的民警进出记录数据保存到民警进出门禁记录表
		PoliceInoutEntity policeInoutEntity_op = this.convertPoliceInoutBeanToPoliceInoutEntity(cusNumber, policeInoutBean);
		if(policeInoutEntity_op != null) {
			policeInoutEntity_op.setPirLeaveTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
			policeInoutEntity_op.setPirCrteTime(new Date());
			policeInoutEntity_op.setPirCrteUserId("0");
			policeInoutEntity_op.setPirUpdtTime(new Date());
			policeInoutEntity_op.setPirUpdtUserId("0");
			this.policeInoutMapper.insert(policeInoutEntity_op);
		}
		
		// 删除在监民警纪录
		PoliceInprsnEntity policeInprsnEntity = new PoliceInprsnEntity();
		policeInprsnEntity.setCipCusNumber(cusNumber);
		policeInprsnEntity.setCipPoliceIdnty(policeInoutBean.getPeopleID());
		policeInprsnMapper.deleteByCondition(policeInprsnEntity);
	}
	/**
	 * 民警进入监狱
	 * 第二次改造，湖南 这边没有ab门标识，所以把换证记录推送过来，当作进出ab门标识，换证推过来的数据门禁ID为 ABDoor, ABFlag为 1
	 * 但是换证数据里面没有民警编号，所以重名的会有问题
	 * @param cusNumber
	 * @param policeInoutBean
	 */
	private void policeEnterPrison_hn(String cusNumber, PoliceInoutBean policeInoutBean) {
		PoliceInoutEntity policeInoutEntity = new PoliceInoutEntity();
		policeInoutEntity.setPirCusNumber(cusNumber);
		policeInoutEntity.setPirPoliceIdnty(policeInoutBean.getPeopleID());
		policeInoutEntity.setPirPoliceName(policeInoutBean.getPeopleName());
		policeInoutEntity.setNotNullEnterTime("1");
		policeInoutEntity.setNotNullLeaveTime("0");
		
		// 删除民警进出门禁记录表中的历史数据
		policeInoutMapper.deleteByCondition(policeInoutEntity);
		
		// 将新的民警进出记录数据保存到民警进出门禁记录表
		PoliceInoutEntity policeInoutEntity_op = this.convertPoliceInoutBeanToPoliceInoutEntity(cusNumber, policeInoutBean);
		if(policeInoutEntity_op != null) {
			policeInoutEntity_op.setPirCrteTime(new Date());
			policeInoutEntity_op.setPirCrteUserId("0");
			policeInoutEntity_op.setPirUpdtTime(new Date());
			policeInoutEntity_op.setPirUpdtUserId("0");
			this.policeInoutMapper.insert(policeInoutEntity_op);
		}
		
		// 删除在监民警纪录
		PoliceInprsnEntity policeInprsnEntity_old = new PoliceInprsnEntity();
		policeInprsnEntity_old.setCipCusNumber(cusNumber);
		policeInprsnEntity_old.setCipPoliceIdnty(policeInoutBean.getPeopleID());
		policeInprsnEntity_old.setCipPoliceName(policeInoutBean.getPeopleName());
		policeInprsnMapper.deleteByCondition(policeInprsnEntity_old);
		
		// 新增民警在监记录
		PoliceInprsnEntity policeInprsnEntity_op = new PoliceInprsnEntity();
		policeInprsnEntity_op.setCipPoliceIdnty(policeInoutBean.getPeopleID());
		policeInprsnEntity_op.setCipCusNumber(cusNumber);
		policeInprsnEntity_op.setCipPoliceName(policeInoutBean.getPeopleName());
		policeInprsnEntity_op.setCipDoorCardIdnty(policeInoutBean.getCardID());
		policeInprsnEntity_op.setCipDoorIdnty(policeInoutBean.getDoorID());		
		policeInprsnEntity_op.setCipEnterTime(Util.toDate(policeInoutBean.getCompareTime(), Util.DF_TIME));
		policeInprsnEntity_op.setCipCrteTime(new Date());
		policeInprsnEntity_op.setCipCrteUserId("0");
		policeInprsnEntity_op.setCipUpdtTime(new Date());
		policeInprsnEntity_op.setCipUpdtUserId("0");
		policeInprsnEntity_op.setCipDataSource("1");	
		policeInprsnEntity_op.setCipPeopleTypeIndc(policeInoutBean.getPeopleType());
		policeInprsnMapper.insert(policeInprsnEntity_op);	
	}
	
	/**
	 * 民警离开监狱
	 * 第二次改造，湖南 这边没有ab门标识，所以把换证记录推送过来，当作进出ab门标识，换证推过来的数据门禁ID为 ABDoor, ABFlag为 1
	 * 但是换证数据里面没有民警编号。
	 * @param cusNumber
	 * @param policeInoutBean
	 */
	private void policeLeavePrison_hn(String cusNumber, PoliceInoutBean policeInoutBean) {
		// 根据监狱编号、民警编号，查询该民警离开时间为空的进出记录数据
		PoliceInoutEntity policeInoutEntity = new PoliceInoutEntity();
		policeInoutEntity.setPirCusNumber(cusNumber);
		policeInoutEntity.setPirPoliceIdnty(policeInoutBean.getPeopleID());
		policeInoutEntity.setPirPoliceName(policeInoutBean.getPeopleName());
		policeInoutEntity.setNotNullLeaveTime("0");
		List<PoliceInoutEntity> policeInoutList = policeInoutMapper.findAllList(policeInoutEntity);
		
		if(policeInoutList != null && policeInoutList.size() > 0) {
			// 更新离监时间
			
			policeInoutEntity.setPirLeaveTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
			policeInoutMapper.updateByCondition(policeInoutEntity);
		}
		
		// 将新的民警进出记录数据保存到民警进出门禁记录表
		PoliceInoutEntity policeInoutEntity_op = this.convertPoliceInoutBeanToPoliceInoutEntity(cusNumber, policeInoutBean);
		if(policeInoutEntity_op != null) {
			policeInoutEntity_op.setPirLeaveTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
			policeInoutEntity_op.setPirCrteTime(new Date());
			policeInoutEntity_op.setPirCrteUserId("0");
			policeInoutEntity_op.setPirUpdtTime(new Date());
			policeInoutEntity_op.setPirUpdtUserId("0");
			this.policeInoutMapper.insert(policeInoutEntity_op);
		}
		
		// 删除在监民警纪录
		PoliceInprsnEntity policeInprsnEntity = new PoliceInprsnEntity();
		policeInprsnEntity.setCipCusNumber(cusNumber);
		policeInprsnEntity.setCipPoliceIdnty(policeInoutBean.getPeopleID());
		policeInprsnEntity.setCipPoliceName(policeInoutBean.getPeopleName());
		policeInprsnMapper.deleteByCondition(policeInprsnEntity);
	}

	/**
	 * 将PoliceInoutBean转化为PoliceInoutEntity
	 * 
	 * @param cusNumber
	 * @param policeInoutBean
	 * @return PoliceInoutEntity
	 */
	private PoliceInoutEntity convertPoliceInoutBeanToPoliceInoutEntity(String cusNumber, PoliceInoutBean policeInoutBean) {
		PoliceInoutEntity policeInoutEntity = null;
		if(policeInoutBean != null) {
			policeInoutEntity = new PoliceInoutEntity();
			policeInoutEntity.setPirCusNumber(cusNumber);
			policeInoutEntity.setPirBrushDate(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
			policeInoutEntity.setPirPoliceIdnty(policeInoutBean.getPeopleID());
			policeInoutEntity.setPirPoliceName(policeInoutBean.getPeopleName());
			policeInoutEntity.setPirPeopleTypeIndc(policeInoutBean.getPeopleType());
			//进入则设置进入时间
			if(CommonConstant.InOutFlag.IN.equals(policeInoutBean.getInOutFlag())) {
				//过滤进入时间为当前时间1分钟之后的，即未来时间
				Date wl_date = DateUtil.addsecound(new Date(),60);
				if(wl_date.after(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME))) {
					policeInoutEntity.setPirEnterTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
				}else {
					return null;
				}
			}
			//离开则设置离开时间
			else if(CommonConstant.InOutFlag.OUT.equals(policeInoutBean.getInOutFlag())){
				//过滤进入时间为当前时间1分钟之后的，即未来时间
				Date wl_date = DateUtil.addsecound(new Date(),60);
				if(wl_date.after(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME))) {
					policeInoutEntity.setPirLeaveTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
				}else {
					return null;
				}
			}	
			policeInoutEntity.setPirDoorCardIdnty(policeInoutBean.getCardID());
			policeInoutEntity.setPirDoorCardTypeIdnty(policeInoutBean.getCardType());
			policeInoutEntity.setPirDoorIdnty(policeInoutBean.getDoorID());
			//对于没有门禁名称的数据，关联查询下门禁名称再进行赋值
			if(StringUtils.isBlank(policeInoutBean.getDoorName())) {
				List<DoorEntity> doorEntityList = this.doorMapper.findByDoorIDAndCusNumber(policeInoutBean.getDoorID(),cusNumber);
	    		if(doorEntityList != null && doorEntityList.size() > 0) {
	    			policeInoutBean.setDoorName(doorEntityList.get(0).getDcbName());
	    		}
				
			}
			policeInoutEntity.setPirDoorName(policeInoutBean.getDoorName());
			// policeInoutEntity.setPirDprtmntId(pirDprtmntId);
			policeInoutEntity.setPirParentDprtmntId(policeInoutBean.getSjbmbh());
			policeInoutEntity.setPirParentDprtmntName(policeInoutBean.getSjbmmc());
			policeInoutEntity.setPirAbFlag(policeInoutBean.getABFlag());
			policeInoutEntity.setPirInOutIndc(policeInoutBean.getInOutFlag());
		}
		return policeInoutEntity;
	}
	
	/**
	 * 根据监狱编号、民警编号，查询在监民警信息
	 * 
	 * @param cusNumber
	 * @param policeId
	 * @return
	 */
	private PoliceInprsnEntity queryPoliceInprsnByCusNumberAndPoliceId(String cusNumber, String policeId) {
		List<PoliceInprsnEntity> policeInprsnList = this.queryPoliceInprsnListByCusNumberAndPoliceId(cusNumber, policeId);
		if(policeInprsnList != null && policeInprsnList.size() > 0) {
			return policeInprsnList.get(0);
		}
		return null;
	}

	/**
	 * 根据监狱编号、民警编号，查询在监民警信息
	 * 
	 * @param cusNumber
	 * @param policeId
	 * @return
	 */
	private List<PoliceInprsnEntity> queryPoliceInprsnListByCusNumberAndPoliceId(String cusNumber, String policeId) {
		if(Util.notNull(cusNumber) && Util.notNull(policeId)) {
			List<PoliceInprsnEntity> policeInprsnList = policeInprsnMapper.findByCipCusNumberAndCipPoliceIdnty(cusNumber, policeId);
			if(policeInprsnList != null && policeInprsnList.size() > 0) {
				return policeInprsnList;
			}
		}
		return null;
	}
}