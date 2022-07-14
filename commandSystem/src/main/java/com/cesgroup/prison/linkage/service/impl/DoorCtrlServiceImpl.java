package com.cesgroup.prison.linkage.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.framework.util.MsgIdUtil;
import com.cesgroup.frm.net.netty.bean.MsgHeader;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constants.socket.MsgUtils;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.entity.DoorControlEntity;
import com.cesgroup.prison.jfsb.entity.DoorEntity;
import com.cesgroup.prison.jfsb.service.DoorControlService;
import com.cesgroup.prison.jfsb.service.DoorService;
import com.cesgroup.prison.linkage.dto.DoorMessageDto;
import com.cesgroup.prison.linkage.service.DoorCtrlService;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.cesgroup.scrap.db.QueryProcess;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ces.sdk.util.Util;

@Service
public class DoorCtrlServiceImpl implements DoorCtrlService {

	private static final Logger logger = LoggerFactory.getLogger(DoorCtrlServiceImpl.class);

	/**
	 * gson工具类
	 */
	private static final Gson gson = new GsonBuilder().create();
	
	private String openMsgType = MsgUtils.MSG_TYPE_DOOR_002;

	@Resource
	private QueryProcess queryProcess;
	@Resource
	private MqMessageSender mqMessageSender;

	/**
	 * 门禁管理Service
	 */
	@Autowired
	private DoorService doorService;
	
	/**
	 * 门禁控制器管理Service
	 */
	@Autowired
	private DoorControlService doorControlService;

	/**
	* @methodName: openDoor
	* @Description: 门操作指令发送
	* @param cusNumber
	* @param doorID 门编号
	* @param action 动作  1 开门 2禁止开门 3恢复开门
	* @param brand 品牌
	* @param time 常闭操作有效时间 1-254（分钟）
	* @throws  
	*/
	private void openDoor(DoorEntity doorEntity, String action, String time, String userId) {
		//根据门禁数据对应的门禁控制器ID，查询门禁控制器信息
		if(doorEntity != null) {
			//门禁控制器IP地址
			String doorCtrlDevIP = "";
			//门禁控制器序列号
			String doorCtrlDevSN = "";
			if(Util.notNull(doorEntity.getDcbCtrlIdnty())) {
				DoorControlEntity doorControlEntity = this.doorControlService.findById(doorEntity.getDcbCtrlIdnty());
				if(doorControlEntity != null) {
					doorCtrlDevIP = doorControlEntity.getDcdIpAddrs();
					doorCtrlDevSN = doorControlEntity.getDcdSn();
				} else {
					logger.debug("doorControlEntity is null");
				}
			} else {
				logger.debug("doorEntity.dcbCtrlIdnty is null");
			}
			
			/*
			 * MsgBody msgBody = new MsgBody(); msgBody.setDoorID(doorID);
			 * msgBody.setAction(action); msgBody.setBrand(brand);
			 */
			JSONObject msgBody = new JSONObject();
			msgBody.put("doorID", doorEntity.getDcbIdnty());
			msgBody.put("action", action);
			msgBody.put("brand", doorEntity.getDcbBrandIndc());
			msgBody.put("userID", userId);
			msgBody.put("doorName", doorEntity.getDcbName());
			msgBody.put("doorType", doorEntity.getDcbTypeIndc());
			msgBody.put("deptId", doorEntity.getDcbDprtmntId());
			msgBody.put("roomId", doorEntity.getDcbRoomId());
			msgBody.put("doorCtrlDevIP", doorCtrlDevIP);
			msgBody.put("doorCtrlDevSN", doorCtrlDevSN);
			msgBody.put("doorReaderNo", doorEntity.getDcbChannelIdnty());
			msgBody.put("para", StringUtils.isNotBlank(time) ? time : "");
			String openStr = createMessage(doorEntity.getDcbCusNumber(), msgBody);
			mqMessageSender.sendDoorMessage(openStr, doorEntity.getDcbCusNumber(), openMsgType);
			logger.debug("开关门指令发送完成" + msgBody);
		} else {
			logger.debug("doorEntity is null");
		}
	}

	private String createMessage(String cusNumber, JSONObject msgBody) {
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setCusNumber(cusNumber);// 新增的消息头
		msgHeader.setMsgID(MsgIdUtil.getMsgSeq(""));
		msgHeader.setMsgType(openMsgType);
		msgHeader.setLength(0);
		msgHeader.setSender("SERVER");
		msgHeader.setRecevier("DOOR");
		msgHeader.setSendTime(DateUtil.getDateString(new Date(), DateUtil.sdf));
		JSONObject out = new JSONObject();
		out.put("header", msgHeader);
		out.put("body", msgBody);
		return out.toJSONString();
	}

	@Override
	public AjaxMessage openDoor(List<String> doorIds, String action, String time) {
		AjaxMessage ajaxMsg = new AjaxMessage();
		boolean flag = false;
		String msg = "";
		List<String> cameraIdList = new ArrayList<String>();
		try {
			List<DoorEntity> list = doorService.findByIds(doorIds);
			if (!list.isEmpty()) {
				UserBean userBean = AuthSystemFacade.getLoginUserInfo();
				String userId = userBean.getUserId();
				for (DoorEntity doorEntity : list) {
					if (doorEntity != null) {
						String cameraIds = doorEntity.getDcbCameraId();// 摄像机IDS
						if (cameraIds != null && StringUtils.isNotEmpty(cameraIds)) {
							cameraIdList.addAll(Arrays.asList(cameraIds.split(",")));
						}
						this.openDoor(doorEntity, action, time, userId);
						msg = "指令发送成功";
						flag = true;
					}

				}
			} else {
				msg = "参数错误，指令发送失败";
				flag = false;
			}
		} catch (Exception e) {
			flag = false;
			msg = "指令发送失败,发生异常:" + e.getMessage();
		}
		ajaxMsg.setMsg(msg);
		ajaxMsg.setSuccess(flag);
		ajaxMsg.setObj(cameraIdList);
		return ajaxMsg;
	}

	@Override
	public AjaxResult openDoorDemo(String pcIp, DoorMessageDto doorMessageDto) {
		try {
			JSONObject msgBody = new JSONObject();
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean != null ? userBean.getUserId() : "";
			msgBody.put("doorID", doorMessageDto.getDoorID());
			msgBody.put("action", doorMessageDto.getAction());
			msgBody.put("brand", doorMessageDto.getBrand());
			msgBody.put("userID", userId);
			msgBody.put("doorName", doorMessageDto.getDoorName());
			msgBody.put("doorType", doorMessageDto.getDoorType());
			msgBody.put("deptId", doorMessageDto.getDeptId());
			msgBody.put("roomId", doorMessageDto.getRoomId());
			msgBody.put("para", "");
			String openStr = createMessage(doorMessageDto.getCusNumber(), msgBody);
			mqMessageSender.sendDoorMessage(openStr, doorMessageDto.getCusNumber(), openMsgType);
			
			String msg = "开关门指令发送成功";
			return AjaxResult.success(msg);
		} catch (Exception e) {
			String msg = "开关门指令发送失败，发生异常: " + e.getMessage();
			return AjaxResult.error(msg);
		}
	}

}
