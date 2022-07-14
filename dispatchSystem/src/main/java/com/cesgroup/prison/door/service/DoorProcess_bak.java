package com.cesgroup.prison.door.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.frm.net.netty.util.DateUtil;
import com.cesgroup.prison.common.constant.AuthSystemConst;
import com.cesgroup.prison.common.constants.socket.IMsgTypeConst;
import com.cesgroup.prison.common.facade.MessageSendFacade;
import com.cesgroup.prison.door.bean.DoorCardBean;
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
import com.cesgroup.prison.netamq.service.MqMessageSender;

/**
 * 门禁消息的处理类
 * @author zxh
 *
 */
@Service
public class DoorProcess_bak implements IMessageProcess {
	
	private final Logger logger = LoggerFactory.getLogger(DoorProcess_bak.class);
	
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
	
	@Override
	@Transactional
	public void processMessage(String cusNumber, JSONObject jsonObject) {
		
		MsgHeader msgHead = JSON.toJavaObject(jsonObject.getJSONObject("header"), MsgHeader.class);		
		String msgType = msgHead.getMsgType();
		
		if(MsgTypeConst.POLICE_INOUT.equals(msgType)) {			
			logger.info("收到警员进出监区信息：" + jsonObject.toJSONString());
			
			PoliceInoutBean policeInoutBean = JSON.toJavaObject(jsonObject.getJSONObject("body"), PoliceInoutBean.class);
			String inoutFlag = policeInoutBean.getInOutFlag();		// 进出标识
			
			// 通过警号获取用户id
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pbdCusNumber", cusNumber);
			paramMap.put("pbdPoliceIdnty", policeInoutBean.getPeopleID());
			List<Map<String, Object>> policeList = policeInoutMapper.findUserIdByPoliceIdnty(paramMap);
			
			if(policeList != null && policeList.size() > 0) {
				policeInoutBean.setPeopleID(String.valueOf(policeList.get(0).get("PBD_USER_ID")));
			}
			
			if("1".equals(inoutFlag)) {
				
				// 进入监区
				savePoliceInout(cusNumber, policeInoutBean);				
			} else if("2".equals(inoutFlag)) {
				
				// 离开监区
 				updatePoliceInout(cusNumber, policeInoutBean);		 
			}
			
			// 往前台推送消息---当前在监民警实时变动
			sendPoliceInprsnNum(cusNumber);	
		} else if(MsgTypeConst.DOOR_CARD.equals(msgType)) {
			logger.info("收到门禁刷卡信息：" + jsonObject.toJSONString());
				
			DoorCardBean doorCardBean = JSON.toJavaObject(jsonObject.getJSONObject("body"), DoorCardBean.class);
			
			// 通过警号获取用户id
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pbdCusNumber", cusNumber);
			paramMap.put("pbdPoliceIdnty", doorCardBean.getCardID());
			List<Map<String, Object>> policeList = policeInoutMapper.findUserIdByPoliceIdnty(paramMap);
			
			if(policeList != null && policeList.size() > 0) {
				doorCardBean.setCardID(String.valueOf(policeList.get(0).get("PBD_USER_ID")));
			}
			
 			insertDoorCard(cusNumber, doorCardBean);			 		
		}		 
	}
	
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
						
			/*WebSocketMessage webSocketMessage = new WebSocketMessage();
			webSocketMessage.setSendType("1");
			webSocketMessage.setSendTo(cusNumber);
			webSocketMessage.setMsgType(IMsgTypeConst.CURRENT_POLICE_COUNT);
			webSocketMessage.setContent(JSONObject.toJSONString(ajaxMsg));
			mqMessageSender.sendNettyMessage(JSONObject.toJSONString(webSocketMessage), cusNumber);*/
			
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
	
	public void savePoliceInout(String cusNumber, PoliceInoutBean policeInoutBean) {
		 
		try {
			PoliceInoutEntity policeInoutEntity = new PoliceInoutEntity();
			policeInoutEntity.setPirCusNumber(cusNumber);
			policeInoutEntity.setPirPoliceIdnty(policeInoutBean.getPeopleID());
			policeInoutEntity.setNotNullEnterTime("1");
			List<PoliceInoutEntity> policeInoutList = policeInoutMapper.findAllList(policeInoutEntity);
			
			if(policeInoutList != null && policeInoutList.size() > 0) {			
				String enterTime = policeInoutBean.getCompareTime();
				String enterTime_h = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(policeInoutList.get(0).getPirEnterTime());
				
				if(DateUtil.timeDiff(enterTime, enterTime_h, 60)) {
					logger.info("本次进入时间:" + enterTime + ",上次进入时间:" + enterTime_h);
					logger.info("两次刷卡间隔小于一分钟,更新进入时间");
					
					PoliceInoutEntity policeInout = policeInoutList.get(0);
				    policeInout.setPirEnterTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(enterTime));
					policeInoutMapper.update(policeInout);
					return;
				}		
			}
				
			// 迁移历史数据
			policeInoutHisMapper.saveBySelect(policeInoutEntity);
			
			// 删除历史数据
			policeInoutMapper.deleteByCondition(policeInoutEntity);
			
			// 插入民警进记录
			PoliceInoutEntity savePoliceInout = new PoliceInoutEntity();
			savePoliceInout.setPirCusNumber(cusNumber);
			savePoliceInout.setPirBrushDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(policeInoutBean.getCompareTime()));
			savePoliceInout.setPirPoliceIdnty(policeInoutBean.getPeopleID());
			savePoliceInout.setPirPoliceName(policeInoutBean.getPeopleName());
			savePoliceInout.setPirEnterTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(policeInoutBean.getCompareTime()));
			savePoliceInout.setPirDoorCardIdnty(policeInoutBean.getDoorID());
			savePoliceInout.setPirPeopleTypeIndc(policeInoutBean.getPeopleType());
			savePoliceInout.setPirCrteTime(new Date());
			savePoliceInout.setPirCrteUserId("0");
			savePoliceInout.setPirUpdtTime(new Date());
			savePoliceInout.setPirUpdtUserId("0");
			policeInoutMapper.insert(savePoliceInout);
							
			// 插入在监民警纪录
			PoliceInprsnEntity savePoliceInprsn = new PoliceInprsnEntity();
			savePoliceInprsn.setCipCusNumber(cusNumber);
			savePoliceInprsn.setCipPoliceIdnty(policeInoutBean.getPeopleID());
			savePoliceInprsn.setCipPoliceName(policeInoutBean.getPeopleName());
			savePoliceInprsn.setCipDoorCardIdnty(policeInoutBean.getDoorID());
			//savePoliceInprsn.setCipDoorName(policeInoutBean.getDoorID());		
			savePoliceInprsn.setCipEnterTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(policeInoutBean.getCompareTime()));
			savePoliceInprsn.setCipCrteTime(new Date());
			savePoliceInprsn.setCipCrteUserId("0");
			savePoliceInprsn.setCipUpdtTime(new Date());
			savePoliceInprsn.setCipUpdtUserId("0");
			savePoliceInprsn.setCipDataSource("1");	
			policeInprsnMapper.insert(savePoliceInprsn);			
		} catch (ParseException e) {
			logger.error(e.toString(), e.fillInStackTrace());
		}
	}
		
	public void updatePoliceInout(String cusNumber, PoliceInoutBean policeInoutBean) {
		
		try {
			PoliceInoutEntity policeInoutEntity = new PoliceInoutEntity();
			policeInoutEntity.setPirCusNumber(cusNumber);
			policeInoutEntity.setPirPoliceIdnty(policeInoutBean.getPeopleID());
			policeInoutEntity.setNotNullLeaveTime("0");
			List<PoliceInoutEntity> policeInoutList = policeInoutMapper.findAllList(policeInoutEntity);
			
			PoliceInoutHisEntity policeInoutHisEntity = new PoliceInoutHisEntity();
			policeInoutHisEntity.setPirCusNumber(cusNumber);
			policeInoutHisEntity.setPirPoliceIdnty(policeInoutBean.getPeopleID());
			policeInoutHisEntity.setNotNullLeaveTime("0");
			List<PoliceInoutHisEntity> policeInoutHisList = policeInoutHisMapper.findAllList(policeInoutHisEntity);
			
			if((policeInoutList != null && policeInoutList.size() > 0) 
					|| (policeInoutHisList != null && policeInoutHisList.size() > 0)) {
				
				// 更新离监时间
				policeInoutEntity.setPirLeaveTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(policeInoutBean.getCompareTime()));
				policeInoutMapper.updateByCondition(policeInoutEntity);
				
				policeInoutHisEntity.setPirLeaveTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(policeInoutBean.getCompareTime()));
				policeInoutHisMapper.updateByCondition(policeInoutHisEntity);			
			} else {
				
				//  插入民警出记录
				PoliceInoutEntity savePoliceInout = new PoliceInoutEntity();
				savePoliceInout.setPirCusNumber(cusNumber);
				savePoliceInout.setPirBrushDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(policeInoutBean.getCompareTime()));
				savePoliceInout.setPirPoliceIdnty(policeInoutBean.getPeopleID());
				savePoliceInout.setPirPoliceName(policeInoutBean.getPeopleName());
				savePoliceInout.setPirLeaveTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(policeInoutBean.getCompareTime()));
				savePoliceInout.setPirDoorCardIdnty(policeInoutBean.getDoorID());
				savePoliceInout.setPirPeopleTypeIndc(policeInoutBean.getPeopleType());
				savePoliceInout.setPirCrteTime(new Date());
				savePoliceInout.setPirCrteUserId("0");
				savePoliceInout.setPirUpdtTime(new Date());
				savePoliceInout.setPirUpdtUserId("0");
				policeInoutMapper.insert(savePoliceInout);
			}
			
			// 删除在监民警纪录
			PoliceInprsnEntity policeInprsnEntity = new PoliceInprsnEntity();
			policeInprsnEntity.setCipCusNumber(cusNumber);
			policeInprsnEntity.setCipPoliceIdnty(policeInoutBean.getPeopleID());
			policeInprsnMapper.deleteByCondition(policeInprsnEntity);
		} catch (ParseException e) {
			logger.error(e.toString(), e.fillInStackTrace());
		}
	}
		
	public void insertDoorCard(String cusNumber, DoorCardBean doorCardBean) {
		
		try {
			// 迁移刷卡历史数据
			DoorCardEntity doorCardEntity = new DoorCardEntity();
			doorCardEntity.setDrdCusNumber(cusNumber);
			doorCardEntity.setDrdDoorIdnty(doorCardBean.getDoorID());
			doorCardEntity.setDrdCardIdnty(doorCardBean.getCardID());
			doorCardEntity.setDrdPeopleTypeIndc(doorCardBean.getPeopleType());	
			doorCardHisMapper.saveBySelect(doorCardEntity);
			
			// 删除刷卡数据
			doorCardMapper.deleteByCondition(doorCardEntity);
			
			// 插入刷卡数据
			DoorCardEntity saveDoorCard = new DoorCardEntity();
			saveDoorCard.setDrdCusNumber(cusNumber);
			saveDoorCard.setDrdBsnsDate(new Date());
			saveDoorCard.setDrdDoorIdnty(doorCardBean.getDoorID());
			saveDoorCard.setDrdCardIdnty(doorCardBean.getCardID());
			saveDoorCard.setDrdInOutIndc(doorCardBean.getInOutFlag());
			saveDoorCard.setDrdBrushCardTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(doorCardBean.getBrushCardTime()));
			saveDoorCard.setDrdStatusIndc(doorCardBean.getStatus());
			saveDoorCard.setDrdPeopleTypeIndc(doorCardBean.getPeopleType());
			saveDoorCard.setDrdRemark(doorCardBean.getRemark());
			saveDoorCard.setDrdCrteTime(new Date());
			saveDoorCard.setDrdCrteUserId("0");		
			doorCardMapper.insert(saveDoorCard);
		} catch (ParseException e) {
			logger.error(e.toString(), e.fillInStackTrace());
		}
	}
}