package com.cesgroup.prison.screenSwitch.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.framework.util.MsgIdUtil;
import com.cesgroup.frm.net.netty.bean.MsgHeader;
import com.cesgroup.prison.common.constants.socket.MsgUtils;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.cesgroup.prison.screen.dao.ScreenAreaCameraMapper;
import com.cesgroup.prison.screen.dao.ScreenAreaWindowMapper;
import com.cesgroup.prison.screen.dao.ScreenPlanAreaRltnMapper;
import com.cesgroup.prison.screen.entity.ScreenPlanEntity;
import com.cesgroup.prison.screen.service.ScreenPlanService;
import com.cesgroup.prison.screenSwitch.service.ScreenSwitchService;

@Service
public class ScreenSwitchServiceImpl implements ScreenSwitchService {
	@Autowired
	private ScreenPlanService screenPlanService;

	@Resource
	private MqMessageSender mqMessageSender;

	/**
	* @Fields sparMapper : 区域
	*/
	@Autowired
	private ScreenPlanAreaRltnMapper sparMapper;

	/**
	* @Fields sawMapper : 窗口
	*/
	@Autowired
	private ScreenAreaWindowMapper sawMapper;

	/**
	* @Fields sacMapper : 信号源
	*/
	@Autowired
	private ScreenAreaCameraMapper sacMapper;

	private String createMessage(String cusNumber, JSONObject msgBody) {
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setCusNumber(cusNumber);// 新增的消息头
		msgHeader.setMsgID(MsgIdUtil.getMsgSeq(""));
		msgHeader.setMsgType(MsgUtils.MSG_TYPE_SCREEN_002);
		msgHeader.setLength(0);
		msgHeader.setSender("SERVER");
		msgHeader.setRecevier("SCREEN");
		msgHeader.setSendTime(DateUtil.getDateString(new Date(), DateUtil.sdf));
		JSONObject out = new JSONObject();
		out.put("header", msgHeader);
		out.put("body", msgBody);
		return out.toJSONString();
	}

	private AjaxMessage startScreenSwitch(ScreenPlanEntity screenPlanEntity, String type, List<String> cameraId,
			String alarmAddress) {
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			String cusNumber = screenPlanEntity.getSplCusNumber();// 监狱编号
			String planIndc = screenPlanEntity.getSplPlanIndc();// 预案编号
			String planID = screenPlanEntity.getId();// 预案id
			String manufacturers = screenPlanEntity.getSplManufacturersId();// 大屏厂家id
			if (StringUtils.isBlank(type)) {
				int isRound = screenPlanService.isRound(planID);
				type = isRound == 0 ? "1" : "2";
			}
			JSONObject msgBody = new JSONObject();
			msgBody.put("cusNumber", cusNumber);
			msgBody.put("screenID", planID);
			msgBody.put("screenIndc", planIndc);
			msgBody.put("brand", manufacturers);
			msgBody.put("type", type);
			if (type.equals("3") || type.equals("4")) {
				msgBody.put("list", getScreenPlanList(cusNumber, screenPlanEntity.getId(), cameraId));
			}

			if (type.equals("3") && StringUtils.isNotBlank(alarmAddress)) {
				msgBody.put("alarmMsg", alarmAddress + "发生报警！！！");
			}

			String openStr = createMessage(cusNumber, msgBody);
			mqMessageSender.sendScreenMessage(openStr, cusNumber, MsgUtils.MSG_TYPE_SCREEN_002);

			ajaxMsg.setMsg("操作成功");
			ajaxMsg.setSuccess(true);

		} catch (Exception e) {
			ajaxMsg.setSuccess(false);
			ajaxMsg.setMsg("出现异常:" + e.getMessage());
		}
		return ajaxMsg;
	}

	/**
	* @methodName: getScreenPlanList
	* @Description:  获取大屏预案区域窗口号信号源json字符串
	* @param cusNumber 监狱号
	* @param screenPlanId 大屏预案id
	* @param cameraId 摄像头ids
	* @return String
	* @throws  
	*/
	private String getScreenPlanList(String cusNumber, String screenPlanId, List<String> cameraId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", cusNumber);
		paramMap.put("screenPlanId", screenPlanId);

		List<Map<String, Object>> screenPlanAreaList = sparMapper.getScreenPlanAreaListByPalnId(paramMap);

		if (screenPlanAreaList != null && !screenPlanAreaList.isEmpty()) {

			String cusNumber_ = (String) screenPlanAreaList.get(0).get("cusNumber");
			String screenPlanId_ = (String) screenPlanAreaList.get(0).get("screenPlanId");
			String screenPlanAreaId = (String) screenPlanAreaList.get(0).get("screenPlanAreaId");

			Map<String, Object> paramMap_ = new HashMap<String, Object>();
			paramMap_.put("cusNumber", cusNumber_);
			paramMap_.put("screenPlanId", screenPlanId_);
			paramMap_.put("screenPlanAreaId", screenPlanAreaId);

			List<Map<String, Object>> screenPlanWindowList = sawMapper.getScreenPlanWindowListByAreaId(paramMap_);
			screenPlanAreaList.get(0).put("screenPlanWindowList", screenPlanWindowList);

			List<Map<String, Object>> screenPlanCameraList = sacMapper.findCameraList(cameraId);
			screenPlanAreaList.get(0).put("screenPlanCameraList", screenPlanCameraList);
		}

		return JSON.toJSONString(screenPlanAreaList);
	}

	@Override
	public AjaxMessage startScreenSwitch(String planId, String type, List<String> cameraId, String alarmAddress) {
		if (StringUtils.isNotBlank(planId)) {
			ScreenPlanEntity screenPlanEntity = screenPlanService.selectOne(planId);
			if (screenPlanEntity != null) {
				if (StringUtils.isNotBlank(type) && screenPlanEntity.getSplIsDynamic().equals("0")) {
					type = null;
				}
				return this.startScreenSwitch(screenPlanEntity, type, cameraId, alarmAddress);
			}
		}
		return new AjaxMessage(false, null, "参数错误，切换预案失败");
	}
}
