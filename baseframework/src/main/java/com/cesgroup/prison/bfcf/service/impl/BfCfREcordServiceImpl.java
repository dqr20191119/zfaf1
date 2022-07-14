package com.cesgroup.prison.bfcf.service.impl;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.util.MsgIdUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.frm.net.netty.bean.MsgBody;
import com.cesgroup.frm.net.netty.bean.MsgHeader;
import com.cesgroup.prison.bfcf.common.BfCfConstants;
import com.cesgroup.prison.bfcf.dao.BfCfREcordMapper;
import com.cesgroup.prison.bfcf.dto.StartBfCfTextMsgBody;
import com.cesgroup.prison.bfcf.entity.BfCfREcordEntity;
import com.cesgroup.prison.bfcf.service.BfCfREcordService;
import com.cesgroup.prison.jfsb.dao.AlertorMapper;
import com.cesgroup.prison.jfsb.entity.AlertorEntity;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import ces.sdk.util.StringUtil;
import io.netty.util.Timeout;
@Service
@Transactional(readOnly=false,rollbackFor= {BusinessLayerException.class,Exception.class})
public class BfCfREcordServiceImpl extends BaseDaoService<BfCfREcordEntity, String, BfCfREcordMapper> implements BfCfREcordService {
	private static final Logger logger = LoggerFactory.getLogger(BfCfREcordServiceImpl.class);
	/**
	 * gson工具类
	 */
	private static final Gson gson = new GsonBuilder().create();
	/**
	 * 消息发送接口
	 */
	@Resource
	private MqMessageSender mqMessageSender;
	
	@Resource
	private AlertorMapper alertorMapper;
	@Resource
	private BfCfREcordMapper bfCfREcordMapper;
	/**
	 * 布防撤防     type  1 布防  2.撤防   3.全部布防  4.全部撤防
	 * 			bjqId  报警器id
	 * 
	 *    监狱id   jyId
	 *    
	 * @param 
	 * @return
	 */
	@Override
	public void startBfCf(Map<String,Object> map) throws BusinessLayerException {
		//查询报警信息
		AlertorEntity alertorEntity = alertorMapper.selectOne(map.get("bjqId").toString());
		
		
		BfCfREcordEntity entity = new BfCfREcordEntity();
		entity.setFqBh(alertorEntity.getAbdIdnty());
		entity.setFqName(alertorEntity.getAbdName());
		entity.setJyId(alertorEntity.getAbdCusNumber());
		entity.setBjqId(map.get("bjqId").toString());
		entity.setType(map.get("type").toString());
		this.sendCommand(entity);
		
	}
	
	
	/**
	 * 发送
	 * 
	 * @param 
	 * @param 
	 * @throws BusinessLayerException
	 */
	
	public void sendCommand(BfCfREcordEntity entity) throws BusinessLayerException  {
		// 声明消息体
		MsgBody msgBody = this.convertToStartBfCfAudioMsgBody(entity);
		// 发送布防指令
		String msgType = BfCfConstants.ALARM_MSG_002;
		//生产消息序列
		String uuId = UUID.randomUUID().toString();
		String msgId =MsgIdUtil.getMsgSeq(uuId);
		//封装消息头
		String sendMsg = this.createMessage(entity.getJyId(), msgType, msgBody,msgId);
		mqMessageSender.sendBfCfMessage(sendMsg, entity.getJyId(), msgId);
		//logger.info("指令发送成功");
		//插入布撤防记录
		entity.setMsgId(msgId);
		String  nowDate  = Util.getCurrentDate();
		entity.setCreateTime(nowDate);
		//entity.setStatus("1");
		bfCfREcordMapper.insert(entity);//插入布防撤防记录
		try {
			Thread.sleep(1000);//休眠1秒处理回放消息
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		 * AlertorEntity alertorEntity = new AlertorEntity();
		 * alertorEntity.setAbdCusNumber(enetity.getJyId());
		 * alertorEntity.setId(entity.getBjqId()); if("1".equals(entity.getType())) {
		 * alertorEntity.setAbdType("1");//报警器布防状态
		 * bfCfREcordMapper.updateAbdTypeAlertorById(alertorEntity); }else
		 * if("2".equals(entity.getType())){ alertorEntity.setAbdType("0");//报警器撤防状态
		 * bfCfREcordMapper.updateAbdTypeAlertorById(alertorEntity); }else
		 * if("3".equals(entity.getType())) {//一键布防
		 * alertorEntity.setAbdType("1");//报警器布防状态 //更改所有防区报警器的状态为布防
		 * bfCfREcordMapper.updateAbdTypeAlertor(alertorEntity); }else
		 * if("4".equals(entity.getType())) {//一键撤防
		 * alertorEntity.setAbdType("0");//报警器布防状态 //更改所有防区报警器的状态为撤防
		 * bfCfREcordMapper.updateAbdTypeAlertor(alertorEntity);
		 * 
		 * }
		 */
		
		
	}
	
	
	
	/**
	 * 封装消息体
	 * @param entity
	 * @return
	 */
	public  StartBfCfTextMsgBody convertToStartBfCfAudioMsgBody(BfCfREcordEntity entity) {
		StartBfCfTextMsgBody msgBody = new StartBfCfTextMsgBody();
		msgBody.setAction(entity.getType());
		msgBody.setFqId(entity.getFqBh());
		msgBody.setFqName(entity.getFqName());
		return msgBody;
	}
	
	
	
	/**
	 * 封装消息头与消息内容
	 * @param cusNumber
	 * @param msgBody
	 * @return
	 */
	public String createMessage(String cusNumber, String msgType, MsgBody msgBody,String msgId) {
		// 消息头
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setCusNumber(cusNumber);// 新增的消息头
		msgHeader.setMsgID(msgId);
		msgHeader.setMsgType(msgType);
		msgHeader.setLength(0);
		msgHeader.setSender("SERVER");
		msgHeader.setRecevier("bfcf");
		msgHeader.setSendTime(Util.toStr(Util.DF_TIME));
		
		JsonObject out = new JsonObject();
		out.add("header", gson.fromJson(gson.toJson(msgHeader), JsonObject.class));
		out.add("body", gson.fromJson(gson.toJson(msgBody), JsonObject.class));
		
		return gson.toJson(out);
	}


}
