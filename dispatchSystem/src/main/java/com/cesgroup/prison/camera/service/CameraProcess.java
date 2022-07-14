package com.cesgroup.prison.camera.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.prison.camera.bean.CameraStatusBean;
import com.cesgroup.prison.camera.dao.CameraMsgMapper;
import com.cesgroup.prison.camera.entity.CameraMsgEntity;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.fm.util.MsgTypeConst;

/**
 * 摄像机消息的处理类
 * @author zxh
 *
 */
@Service
public class CameraProcess implements IMessageProcess {
	
	private final Logger logger = LoggerFactory.getLogger(CameraProcess.class);
	
	@Resource
	private CameraMsgMapper cameraMsgMapper;
	
	@Override
	@Transactional
	public void processMessage(String cusNumber, JSONObject jsonObject) {
		
		MsgHeader msgHead = JSON.toJavaObject(jsonObject.getJSONObject("header"), MsgHeader.class);		
		String msgType = msgHead.getMsgType();
		
		if(MsgTypeConst.CAMERA_STATUS.equals(msgType)) {
			logger.info("收到摄像机状态信息：" + jsonObject.toJSONString());
				
			CameraStatusBean cameraStatusBean = JSON.toJavaObject(jsonObject.getJSONObject("body"), CameraStatusBean.class);
 			updateCameraStatus(cusNumber, cameraStatusBean);			 		
		}		 
	}
	
	public void updateCameraStatus(String cusNumber, CameraStatusBean cameraStatusBean) {
		
		try {
			CameraMsgEntity cameraMsgEntity = new CameraMsgEntity();
			cameraMsgEntity.setId(cameraStatusBean.getCameraId());
			cameraMsgEntity.setCbdSttsIndc(cameraStatusBean.getStatus());
			cameraMsgMapper.updateCameraStatus(cameraMsgEntity);
		} catch (Exception e) {
			logger.error(e.toString(), e.fillInStackTrace());
		}
	}
}
