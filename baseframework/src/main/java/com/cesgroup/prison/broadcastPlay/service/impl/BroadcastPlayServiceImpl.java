package com.cesgroup.prison.broadcastPlay.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.util.MsgIdUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.frm.net.netty.bean.MsgHeader;
import com.cesgroup.prison.broadcastPlay.dao.BroadcastPlayDao;
import com.cesgroup.prison.broadcastPlay.dto.StartPlayTextMsgBody;
import com.cesgroup.prison.broadcastPlay.entity.BroadcastPlay;
import com.cesgroup.prison.broadcastPlay.service.BroadcastPlayService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Service("broadcastPlayService")
public class BroadcastPlayServiceImpl extends BaseDaoService<BroadcastPlay, String, BroadcastPlayDao> implements BroadcastPlayService {

	/**
	 * gson工具类
	 */
	private static final Gson gson = new GsonBuilder().create();
	
	@Resource
	private MqMessageSender mqMessageSender;
	
	@Transactional
	@Override
	public void deleteByIds(List<String> ids) {
		this.getDao().deleteByIds(ids);
	}

	@Transactional
	@Override
	public AjaxMessage addInfo(BroadcastPlay entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			
			entity.setTtsId(UUID.randomUUID().toString());
			entity.setCusNumber(userBean.getCusNumber());
			entity.setCreateTime(date);
			entity.setCreateUserId(userId);
			entity.setUpdateTime(date);
			entity.setUpdateUserId(userId);
			entity.setPlayStatus(CommonConstant.BroadcastPlayStatus.STOP);
			this.getDao().insert(entity);
			flag = true;
			obj = "保存成功";
		} catch (Exception e) {
			flag = false;
			obj = "保存广播发生异常！";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@Transactional
	@Override
	public void updateInfo(BroadcastPlay entity) throws Exception {
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setUpdateTime(date);
			entity.setUpdateUserId(userId);
			this.getDao().update(entity);
		}
	}

	@Override
	public Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable) {
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			if(userBean != null) {
				map.put("cusNumber", userBean.getCusNumber());
			}
			return this.getDao().listAll(map, pageable);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public BroadcastPlay findById(String id) {
		return this.getDao().selectOne(id);
	}

	@Override
	public AjaxResult startPlay(String playId) {
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			if(userBean == null) {
				String msg = "获取用户信息失败";
				return AjaxResult.error(msg);
			}
			
			BroadcastPlay broadcastPlay = this.getDao().selectOne(playId);
			
			if(broadcastPlay == null) {
				String msg = "广播数据不存在，请刷新后重试";
				return AjaxResult.error(msg);
			}
			StartPlayTextMsgBody messageBody = this.convertBroadcastPlayToStartPlayTextMsgBody(broadcastPlay);
			
			String sendMsg = this.createMessage(userBean.getCusNumber(), messageBody);
			mqMessageSender.sendBroadcastMessage(sendMsg, userBean.getCusNumber(), "5");
			String msg = "广播播放指令发送成功";
			return AjaxResult.success(msg);
		} catch (Exception e) {
			String msg = "广播播放指令发送失败，发生异常：" + e.getMessage();
			return AjaxResult.error(msg);
		}
	}

	/**
	 * 封装消息头与消息内容
	 * @param cusNumber
	 * @param msgBody
	 * @return
	 */
	private String createMessage(String cusNumber, StartPlayTextMsgBody msgBody) {
		// 消息头
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setCusNumber(cusNumber);// 新增的消息头
		msgHeader.setMsgID(MsgIdUtil.getMsgSeq(""));
		msgHeader.setMsgType("Broadcast001");
		msgHeader.setLength(0);
		msgHeader.setSender("SERVER");
		msgHeader.setRecevier("Broadcast");
		msgHeader.setSendTime(Util.toStr(Util.DF_TIME));
		
		JSONObject out = new JSONObject();
		out.put("header", msgHeader);
		out.put("body", msgBody);
		
		JsonObject outJson = new JsonObject();
		outJson.add("header", gson.fromJson(gson.toJson(msgHeader), JsonObject.class));
		outJson.add("body", gson.fromJson(gson.toJson(msgBody), JsonObject.class));
		
		return gson.toJson(outJson);
	}

	/**
	 * 类型转换
	 * 
	 * @param broadcastPlay
	 * @return
	 */
	private StartPlayTextMsgBody convertBroadcastPlayToStartPlayTextMsgBody(BroadcastPlay broadcastPlay) {
		StartPlayTextMsgBody messageBody = null;
		if(broadcastPlay != null) {
			messageBody = new StartPlayTextMsgBody();
			messageBody.setTts_id(broadcastPlay.getTtsId());
			messageBody.setVoice(broadcastPlay.getVoice());
			messageBody.setVoice_name(broadcastPlay.getVoiceName());
			messageBody.setText(broadcastPlay.getText());
			messageBody.setPlay_mode(broadcastPlay.getPlayMode());
			messageBody.setVol(broadcastPlay.getVol());
			messageBody.setDial_nos(broadcastPlay.getDialNos());
			messageBody.setSpeed(broadcastPlay.getSpeed());
			messageBody.setVolume(broadcastPlay.getVolume());
		}
		return messageBody;
	}
	
	@Override
	public void startPlayDsq() {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String cusNumber = userBean.getCusNumber();
		try {
			BroadcastPlay broadcastPlay = new BroadcastPlay();
			String sxsj = getSxsj();
			List<Map<String, Object>> list = this.getDao().getXgnr(sxsj);
			for(Map<String, Object> m:list) {
				String zbnr = m.get("TZNR").toString();
				broadcastPlay.setTtsId(UUID.randomUUID().toString());
				broadcastPlay.setVoice("Mandarin");
				broadcastPlay.setVoiceName("woman");
				broadcastPlay.setText(zbnr);
				broadcastPlay.setPlayMode("1");
				broadcastPlay.setVol("80");
				broadcastPlay.setDialNos("1003");
				broadcastPlay.setSpeed("38");
				broadcastPlay.setVolume("100");
				StartPlayTextMsgBody messageBody = this.convertBroadcastPlayToStartPlayTextMsgBody(broadcastPlay);
				String sendMsg = this.createMessage(cusNumber, messageBody);
				mqMessageSender.sendBroadcastMessage(sendMsg, cusNumber, "5");
			}
		} catch (Exception e) {
			String msg = "广播播放指令发送失败，发生异常：" + e.getMessage();
		}
	}
	
	public String getSxsj() {
		String time = "";
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			time = sdf1.format(date);
			String str = time + " 08:00:00"; 
			int i = sdf.parse(str).compareTo(date);
		    System.out.println("i ===== " + i);
		    if(i == -1 || i == 0) {
		       System.out.println("time1 ====== " + time);
		    }else {
		       Calendar c = Calendar.getInstance();  
		       c.setTime(sdf1.parse(time));  
		       c.add(Calendar.DAY_OF_MONTH, -1);        
		       time = sdf1.format(c.getTime());
		       System.out.println("time2 ====== " + time);
		    }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
	
}
