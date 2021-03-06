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
 * ????????????????????????
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
     * ?????????????????????????????????Dao
     */
    @Resource
    private CescodeContrastDaqiDao cescodeContrastDaqiDao;

	@Transactional
	@Override
	public void processMessage(String cusNumber, JSONObject jsonObject) {
		// ????????????JSONObject???????????????JsonObject
    	JsonObject gsonObject = Util.fromJson(JSONObject.toJSONString(jsonObject), JsonObject.class);
    	
    	// ???gsonObject?????????header???????????????MsgHeader??????
        MsgHeader msgHead = gsonObject != null ? Util.fromJson(gsonObject.get("header"), MsgHeader.class) : null;
        
        // ???msgHead?????????msgType????????????
        String msgType = msgHead != null ? msgHead.getMsgType() : "";
        
		if(MsgTypeConst.POLICE_INOUT.equals(msgType)) { 
			logger.info("?????????????????????????????????" + jsonObject.toJSONString());
			
			// ???gsonObject?????????body
			JsonObject body = gsonObject.getAsJsonObject("body");
        	if(body == null) {
        		return;
        	}
        	// json?????????PoliceInoutBean 
			PoliceInoutBean policeInoutBean = Util.fromJson(body, PoliceInoutBean.class); 
			if(policeInoutBean == null) {
				return;
			}
			
			String inoutFlag = policeInoutBean.getInOutFlag();		// ????????????
			// ????????????ID???????????????????????????
			if(policeInoutBean.getPeopleID() == null || "".equals(policeInoutBean.getPeopleID())) {
				return;
			}
			/*if(CommonConstant.InOutFlag.IN.equals(inoutFlag) && "?????????".equals(policeInoutBean.getDoorName())) {
				// ?????????????????????????????????????????????????????????
				this.policeEnterPrison(cusNumber, policeInoutBean);				
			} else if(CommonConstant.InOutFlag.OUT.equals(inoutFlag) && "?????????".equals(policeInoutBean.getDoorName())) {
				// ?????????????????????????????????????????????????????????
 				this.policeLeavePrison(cusNumber, policeInoutBean);		 
			} */
			
			//??????ab???????????????????????????
			String aBFlag = "";
			
			/**
			 * ??????????????????ab????????????????????????????????????????????????ab?????????????????????????????????????????????????????????????????????????????????????????????
			 * ???????????????????????????????????????????????????????????????????????????????????????????????????????????????
			 * **/
			/*List<DoorEntity> doorList = doorMapper.findByDoorIDAndCusNumber(policeInoutBean.getDoorID(), cusNumber);
			if(doorList != null && doorList.size() > 0) {
				aBFlag = doorList.get(0).getDcbIsAb();
			}
			if(CommonConstant.InOutFlag.IN.equals(inoutFlag) && CommonConstant.ABFlag.YES.equals(aBFlag)) {
				// ?????????????????????????????????????????????????????????
				this.policeEnterPrison(cusNumber, policeInoutBean);				
			} else if(CommonConstant.InOutFlag.OUT.equals(inoutFlag) && CommonConstant.ABFlag.YES.equals(aBFlag)) {
				// ?????????????????????????????????????????????????????????
 				this.policeLeavePrison(cusNumber, policeInoutBean);		 
			} 
			*/
			
	
			/**
			 * ???????????????????????? ????????????ab????????????????????????????????????????????????????????????ab??????????????????????????????????????????ID??? ABDoor, ABFlag??? 1
			 * ????????????????????????????????????????????????????????????????????????
			 * 
			 * ????????????????????????????????????????????????????????????
			 * **/
			/*
			if("4307".equals(cusNumber)) {
				List<DoorEntity> doorList = doorMapper.findByDoorIDAndCusNumber(policeInoutBean.getDoorID(), cusNumber);
				if(doorList != null && doorList.size() > 0) {
					aBFlag = doorList.get(0).getDcbIsAb();
				}
				if(CommonConstant.InOutFlag.IN.equals(inoutFlag) && CommonConstant.ABFlag.YES.equals(aBFlag)) {
					// ?????????????????????????????????????????????????????????
					this.policeEnterPrison(cusNumber, policeInoutBean);				
				} else if(CommonConstant.InOutFlag.OUT.equals(inoutFlag) && CommonConstant.ABFlag.YES.equals(aBFlag)) {
					// ?????????????????????????????????????????????????????????
	 				this.policeLeavePrison(cusNumber, policeInoutBean);		 
				} 
			}else {
				aBFlag = policeInoutBean.getABFlag();
				if(CommonConstant.InOutFlag.IN.equals(inoutFlag) && CommonConstant.ABFlag.YES.equals(aBFlag)) {
					// ?????????????????????????????????????????????????????????
					this.policeEnterPrison_hn(cusNumber, policeInoutBean);				
				} else if(CommonConstant.InOutFlag.OUT.equals(inoutFlag) && CommonConstant.ABFlag.YES.equals(aBFlag)) {
					// ?????????????????????????????????????????????????????????
	 				this.policeLeavePrison_hn(cusNumber, policeInoutBean);		 
				} 
				
				else {
					// ??????????????????????????????????????????????????????
					
					// ?????????????????????????????????????????????????????????????????????
					PoliceInoutEntity policeInoutEntity_op = this.convertPoliceInoutBeanToPoliceInoutEntity(cusNumber, policeInoutBean);
					if(policeInoutEntity_op != null) {
						// ????????????????????????????????????????????????????????????
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
					
					// ?????????????????????????????????????????????????????????????????????
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
			//??????ab????????????????????? ????????????????????????AB????????????????????????????????? ??? ?????????AB?????????????????????chu?????????
			if( CommonConstant.ABFlag.NO.equals(aBFlag)) { 
				// ?????????????????????????????????????????????????????????
				policeInoutBean.setInOutFlag(CommonConstant.InOutFlag.IN);
				this.policeEnterPrison(cusNumber, policeInoutBean);				
			} else if( CommonConstant.ABFlag.YES.equals(aBFlag)) {
				// ?????????????????????????????????????????????????????????
				policeInoutBean.setInOutFlag(CommonConstant.InOutFlag.OUT);
 				this.policeLeavePrison(cusNumber, policeInoutBean);		 
			} else { //????????????AB?????????  ?????????AB?????? 
				// ?????????????????????????????????????????????????????????????????????
				PoliceInoutEntity policeInoutEntity_op = this.convertPoliceInoutBeanToPoliceInoutEntity(cusNumber, policeInoutBean);
				if(policeInoutEntity_op != null) {
					
					policeInoutEntity_op.setPirCrteTime(new Date());
					policeInoutEntity_op.setPirCrteUserId("0");
					policeInoutEntity_op.setPirUpdtTime(new Date());
					policeInoutEntity_op.setPirUpdtUserId("0");
					this.policeInoutMapper.insert(policeInoutEntity_op);
				}
			}
			
			
			
			// ?????????????????????---??????????????????????????????
			sendPoliceInprsnNum(cusNumber);
		} else if(MsgTypeConst.DOOR_CARD.equals(msgType)) {
			logger.info("???????????????????????????" + jsonObject.toJSONString());

			// ???gsonObject?????????body
			JsonObject body = gsonObject.getAsJsonObject("body");
        	if(body == null) {
        		return;
        	}
        	// json?????????DoorCardBean
        	DoorCardBean doorCardBean = Util.fromJson(body, DoorCardBean.class);
			if(doorCardBean == null) {
				return;
			}
			
			//????????????ID???????????????????????????
			if(doorCardBean.getPeopleID() == null || "".equals(doorCardBean.getPeopleID())) {
				return;
			}
 			insertDoorCard(cusNumber, doorCardBean);
		} else if(MsgTypeConst.DOOR_RETURN_STATUS.equals(msgType)) {
			logger.info("????????????????????????????????????" + jsonObject.toJSONString());
			
			DoorReturnStatusBean doorReturnStatusBean = JSON.toJavaObject(jsonObject.getJSONObject("body"), DoorReturnStatusBean.class);
			
			try {
				// ?????????????????????
				Map<String, String> msgMap = new HashMap<String, String>();
				msgMap.put("msgType", IMsgTypeConst.MSG_DOOR_RETURN_STATUS);
				msgMap.put("sendType", "2");
				msgMap.put("sendTo", doorReturnStatusBean.getUserID());
				msgMap.put("content", JSONObject.toJSONString(jsonObject.getJSONObject("body")));
				msgMap.put("isSendToGzt", "0");
				msgMap.put("url", null);
				msgMap.put("ywId", null);
				msgMap.put("cusNumber", cusNumber);
				msgMap.put("noticeContent", "?????????????????????????????????");
				MessageSendFacade.send(msgMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		 
	}
	
	/**
	 * ?????????????????????????????????
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
			
			// ?????????????????????
			Map<String, String> msgMap = new HashMap<String, String>();
			msgMap.put("msgType", IMsgTypeConst.CURRENT_POLICE_COUNT);
			msgMap.put("sendType", "1");
			msgMap.put("sendTo", cusNumber + ","+AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG);
			msgMap.put("content", JSONObject.toJSONString(ajaxMsg));
			msgMap.put("isSendToGzt", "0");
			msgMap.put("url", null);
			msgMap.put("ywId", null);
			msgMap.put("cusNumber", cusNumber);
			msgMap.put("noticeContent", "????????????????????????");
			MessageSendFacade.send(msgMap);
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	/**
	 * Description: ????????????????????????
	 * @param cusNumber
	 * @param doorCardBean
	 */
	public void insertDoorCard(String cusNumber, DoorCardBean doorCardBean) {
		try {
			// ????????????????????????
			DoorCardEntity doorCardEntity = new DoorCardEntity();
			doorCardEntity.setDrdCusNumber(cusNumber);
			doorCardEntity.setDrdPeopleTypeIndc(doorCardBean.getPeopleType());	
			doorCardEntity.setDrdPeopleIdnty(doorCardBean.getPeopleID());
			doorCardHisMapper.saveBySelect(doorCardEntity);
			
			// ??????????????????
			doorCardMapper.deleteByCondition(doorCardEntity);
			
			// ??????????????????
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
			// ????????????
	    	String deptNo = doorCardBean.getDeptID();
	    	// ??????????????????????????????????????????????????????????????????????????????????????????
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
	 * ??????????????????
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
		
		// ????????????????????????????????????????????????????????????????????????
		policeInoutHisMapper.saveBySelect(policeInoutEntity);
		
		// ???????????????????????????????????????????????????
		policeInoutMapper.deleteByCondition(policeInoutEntity);
		
		// ?????????????????????????????????????????????????????????????????????
		PoliceInoutEntity policeInoutEntity_op = this.convertPoliceInoutBeanToPoliceInoutEntity(cusNumber, policeInoutBean);
		if(policeInoutEntity_op != null) {
			policeInoutEntity_op.setPirCrteTime(new Date());
			policeInoutEntity_op.setPirCrteUserId("0");
			policeInoutEntity_op.setPirUpdtTime(new Date());
			policeInoutEntity_op.setPirUpdtUserId("0");
			this.policeInoutMapper.insert(policeInoutEntity_op);
		}
		
		// ?????????????????????????????????????????????????????????????????????
		PoliceInprsnEntity policeInprsnEntity_op = this.queryPoliceInprsnByCusNumberAndPoliceId(cusNumber, policeInoutBean.getPeopleID());
		if(policeInprsnEntity_op != null) {
			// ??????????????????
			policeInprsnEntity_op.setCipEnterTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
			policeInprsnMapper.update(policeInprsnEntity_op);
		} else {
			// ????????????????????????
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
	 * ??????????????????
	 * 
	 * @param cusNumber
	 * @param policeInoutBean
	 */
	private void policeLeavePrison(String cusNumber, PoliceInoutBean policeInoutBean) {
		// ??????????????????????????????????????????????????????????????????????????????????????????
		PoliceInoutEntity policeInoutEntity = new PoliceInoutEntity();
		policeInoutEntity.setPirCusNumber(cusNumber);
		policeInoutEntity.setPirPoliceIdnty(policeInoutBean.getPeopleID());
		policeInoutEntity.setNotNullLeaveTime("0");
		List<PoliceInoutEntity> policeInoutList = policeInoutMapper.findAllList(policeInoutEntity);
		
		if(policeInoutList != null && policeInoutList.size() > 0) {
			// ??????????????????
			policeInoutEntity.setPirLeaveTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
			policeInoutMapper.updateByCondition(policeInoutEntity);
		}
		
		// ????????????????????????????????????????????????????????????????????????????????????????????????
		PoliceInoutHisEntity policeInoutHisEntity = new PoliceInoutHisEntity();
		policeInoutHisEntity.setPirCusNumber(cusNumber);
		policeInoutHisEntity.setPirPoliceIdnty(policeInoutBean.getPeopleID());
		policeInoutHisEntity.setNotNullLeaveTime("0");
		List<PoliceInoutHisEntity> policeInoutHisList = policeInoutHisMapper.findAllList(policeInoutHisEntity);

		if(policeInoutHisList != null && policeInoutHisList.size() > 0) {
			// ??????????????????
			policeInoutHisEntity.setPirLeaveTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
			policeInoutHisMapper.updateByCondition(policeInoutHisEntity);
		}
		
		// ?????????????????????????????????????????????????????????????????????
		PoliceInoutEntity policeInoutEntity_op = this.convertPoliceInoutBeanToPoliceInoutEntity(cusNumber, policeInoutBean);
		if(policeInoutEntity_op != null) {
			policeInoutEntity_op.setPirLeaveTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
			policeInoutEntity_op.setPirCrteTime(new Date());
			policeInoutEntity_op.setPirCrteUserId("0");
			policeInoutEntity_op.setPirUpdtTime(new Date());
			policeInoutEntity_op.setPirUpdtUserId("0");
			this.policeInoutMapper.insert(policeInoutEntity_op);
		}
		
		// ????????????????????????
		PoliceInprsnEntity policeInprsnEntity = new PoliceInprsnEntity();
		policeInprsnEntity.setCipCusNumber(cusNumber);
		policeInprsnEntity.setCipPoliceIdnty(policeInoutBean.getPeopleID());
		policeInprsnMapper.deleteByCondition(policeInprsnEntity);
	}
	/**
	 * ??????????????????
	 * ???????????????????????? ????????????ab????????????????????????????????????????????????????????????ab??????????????????????????????????????????ID??? ABDoor, ABFlag??? 1
	 * ????????????????????????????????????????????????????????????????????????
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
		
		// ???????????????????????????????????????????????????
		policeInoutMapper.deleteByCondition(policeInoutEntity);
		
		// ?????????????????????????????????????????????????????????????????????
		PoliceInoutEntity policeInoutEntity_op = this.convertPoliceInoutBeanToPoliceInoutEntity(cusNumber, policeInoutBean);
		if(policeInoutEntity_op != null) {
			policeInoutEntity_op.setPirCrteTime(new Date());
			policeInoutEntity_op.setPirCrteUserId("0");
			policeInoutEntity_op.setPirUpdtTime(new Date());
			policeInoutEntity_op.setPirUpdtUserId("0");
			this.policeInoutMapper.insert(policeInoutEntity_op);
		}
		
		// ????????????????????????
		PoliceInprsnEntity policeInprsnEntity_old = new PoliceInprsnEntity();
		policeInprsnEntity_old.setCipCusNumber(cusNumber);
		policeInprsnEntity_old.setCipPoliceIdnty(policeInoutBean.getPeopleID());
		policeInprsnEntity_old.setCipPoliceName(policeInoutBean.getPeopleName());
		policeInprsnMapper.deleteByCondition(policeInprsnEntity_old);
		
		// ????????????????????????
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
	 * ??????????????????
	 * ???????????????????????? ????????????ab????????????????????????????????????????????????????????????ab??????????????????????????????????????????ID??? ABDoor, ABFlag??? 1
	 * ?????????????????????????????????????????????
	 * @param cusNumber
	 * @param policeInoutBean
	 */
	private void policeLeavePrison_hn(String cusNumber, PoliceInoutBean policeInoutBean) {
		// ??????????????????????????????????????????????????????????????????????????????????????????
		PoliceInoutEntity policeInoutEntity = new PoliceInoutEntity();
		policeInoutEntity.setPirCusNumber(cusNumber);
		policeInoutEntity.setPirPoliceIdnty(policeInoutBean.getPeopleID());
		policeInoutEntity.setPirPoliceName(policeInoutBean.getPeopleName());
		policeInoutEntity.setNotNullLeaveTime("0");
		List<PoliceInoutEntity> policeInoutList = policeInoutMapper.findAllList(policeInoutEntity);
		
		if(policeInoutList != null && policeInoutList.size() > 0) {
			// ??????????????????
			
			policeInoutEntity.setPirLeaveTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
			policeInoutMapper.updateByCondition(policeInoutEntity);
		}
		
		// ?????????????????????????????????????????????????????????????????????
		PoliceInoutEntity policeInoutEntity_op = this.convertPoliceInoutBeanToPoliceInoutEntity(cusNumber, policeInoutBean);
		if(policeInoutEntity_op != null) {
			policeInoutEntity_op.setPirLeaveTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
			policeInoutEntity_op.setPirCrteTime(new Date());
			policeInoutEntity_op.setPirCrteUserId("0");
			policeInoutEntity_op.setPirUpdtTime(new Date());
			policeInoutEntity_op.setPirUpdtUserId("0");
			this.policeInoutMapper.insert(policeInoutEntity_op);
		}
		
		// ????????????????????????
		PoliceInprsnEntity policeInprsnEntity = new PoliceInprsnEntity();
		policeInprsnEntity.setCipCusNumber(cusNumber);
		policeInprsnEntity.setCipPoliceIdnty(policeInoutBean.getPeopleID());
		policeInprsnEntity.setCipPoliceName(policeInoutBean.getPeopleName());
		policeInprsnMapper.deleteByCondition(policeInprsnEntity);
	}

	/**
	 * ???PoliceInoutBean?????????PoliceInoutEntity
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
			//???????????????????????????
			if(CommonConstant.InOutFlag.IN.equals(policeInoutBean.getInOutFlag())) {
				//?????????????????????????????????1?????????????????????????????????
				Date wl_date = DateUtil.addsecound(new Date(),60);
				if(wl_date.after(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME))) {
					policeInoutEntity.setPirEnterTime(Util.toDate(policeInoutBean.getEventTime(), Util.DF_TIME));
				}else {
					return null;
				}
			}
			//???????????????????????????
			else if(CommonConstant.InOutFlag.OUT.equals(policeInoutBean.getInOutFlag())){
				//?????????????????????????????????1?????????????????????????????????
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
			//??????????????????????????????????????????????????????????????????????????????
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
	 * ????????????????????????????????????????????????????????????
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
	 * ????????????????????????????????????????????????????????????
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