package com.cesgroup.prison.rcs.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.alibaba.fastjson.JSONObject;
import com.ces.authsystem.entity.UserEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.framework.util.MsgIdUtil;
import com.cesgroup.frm.net.netty.bean.MsgHeader;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.service.TalkBackServerService;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.cesgroup.prison.rcs.dao.RcsDao;
import com.cesgroup.prison.rcs.dto.RcsMessageBodyDto;
import com.cesgroup.prison.rcs.dto.RcsMessageDto;
import com.cesgroup.prison.rcs.dto.YjyaMessageDto;
import com.cesgroup.prison.rcs.entity.RcsEntity;
import com.cesgroup.prison.rcs.entity.YjyaEntity;
import com.cesgroup.prison.rcs.service.RcsService;
import com.cesgroup.prison.realTimeTalk.service.RealTimeTalkService;
import com.cesgroup.prison.yrzq.entity.YrzqEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Description: 融合通讯业务操作接口实现类
 * @author lincoln
 *
 */
@Service(value = "rcsService")
public class RcsServiceImpl extends BaseDaoService<RcsEntity, String, RcsDao> implements RcsService {
	
	/**
	 * gson工具类
	 */
	private static final Gson gson = new GsonBuilder().create();
	
	@Resource
	private MqMessageSender mqMessageSender;
	@Resource
	private RcsDao rcsMapper;
	
	@Resource
	private TalkBackServerService talkBackServerService;

	/*@Override
	@Transactional
	public AjaxResult startCall(String pcIp, RcsEntity rcsEntity) {
		try {
			rcsEntity.setSendTime(new Date());
			rcsMapper.insert(rcsEntity);
			RcsMessageBodyDto rcsBody = new RcsMessageBodyDto();
			rcsBody.setCmd(rcsEntity.getCmd());
			
			RcsMessageDto para = new RcsMessageDto();
			para.setUserNum(rcsEntity.getCellvalue() != null ? rcsEntity.getCellvalue().split(",") : null);
			para.setTerFlag(rcsEntity.getId());
			para.setContent(rcsEntity.getContent());
			rcsBody.setPara(gson.toJson(para));
			
			String sendMsg = this.createMessage(rcsEntity.getCusNumber(), rcsBody);
			mqMessageSender.sendRcsMessage(sendMsg, rcsEntity.getCusNumber(), "5");
			String msg = "呼叫指令发送成功";
			return AjaxResult.success(msg);
		} catch (Exception e) {
			String msg = "呼叫指令发送失败,发生异常 ：" + e.getMessage();
			return AjaxResult.error(msg);
		}
	}*/
	
	
	@Override
	@Transactional
	public AjaxResult startCall(String pcIp, RcsEntity rcsEntity) {
		String talkIDNTY = "";
		String talkBRAND = "";
		if(StringUtils.isBlank(rcsEntity.getCusNumber())) {
			return AjaxResult.error("监狱编号为空！");
		}
		try {
			// 根据当前用户的IP，查找当前IP所绑定的对讲主机
			List<Map<String, Object>> talkList = talkBackServerService.findInfoByCusNumberAndPcIpAndBaseIdnty(rcsEntity.getCusNumber(), pcIp,null); 
			if(talkList != null && talkList.size() > 0) {
				talkIDNTY = (String) talkList.get(0).get("TSE_IDNTY");
				talkBRAND = (String) talkList.get(0).get("TSE_BRAND");
    		}
			if(!StringUtils.isNotBlank(talkIDNTY)) {
				return AjaxResult.error("请求主机的IP没有关联对讲主机！");
			}
			rcsEntity.setSendTime(new Date());
			rcsMapper.insert(rcsEntity);
			RcsMessageBodyDto rcsBody = new RcsMessageBodyDto();
			rcsBody.setCmd(rcsEntity.getCmd());
			
			
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			JSONObject msgBody = new JSONObject();
			msgBody.put("talkID", talkIDNTY);
			msgBody.put("toID", rcsEntity.getCellvalue());
			msgBody.put("action", "1");
			msgBody.put("brand", talkBRAND);
			msgBody.put("userID", userId);
			String sendMsg = this.createMessage_talk(rcsEntity.getCusNumber(), msgBody);
			mqMessageSender.sendTalkMessage(sendMsg, rcsEntity.getCusNumber(), "");

			String msg = "呼叫指令发送成功";
			return AjaxResult.success(msg);
		} catch (Exception e) {
			String msg = "呼叫指令发送失败,发生异常 ：" + e.getMessage();
			return AjaxResult.error(msg);
		}
	}
	/**
	 * 挂断
	 * **/
	@Override
	@Transactional
	public AjaxResult hangUp(String pcIp, RcsEntity rcsEntity) {
		String talkIDNTY = "";
		String talkBRAND = "";
		if(StringUtils.isBlank(rcsEntity.getCusNumber())) {
			return AjaxResult.error("监狱编号为空！");
		}
		try {
			// 根据当前用户的IP，查找当前IP所绑定的对讲主机
			List<Map<String, Object>> talkList = talkBackServerService.findInfoByCusNumberAndPcIpAndBaseIdnty(rcsEntity.getCusNumber(), pcIp,null); 
			if(talkList != null && talkList.size() > 0) {
				talkIDNTY = (String) talkList.get(0).get("TSE_IDNTY");
				talkBRAND = (String) talkList.get(0).get("TSE_BRAND");
    		}
			if(!StringUtils.isNotBlank(talkIDNTY)) {
				return AjaxResult.error("请求主机的IP没有关联对讲主机！");
			}
			
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			JSONObject msgBody = new JSONObject();
			msgBody.put("talkID", talkIDNTY);
			msgBody.put("toID", "");
			//挂断 action 为 2
			msgBody.put("action", "2");
			msgBody.put("brand", talkBRAND);
			msgBody.put("userID", userId);
			String sendMsg = this.createMessage_talk(rcsEntity.getCusNumber(), msgBody);
			mqMessageSender.sendTalkMessage(sendMsg, rcsEntity.getCusNumber(), "");

			String msg = "挂断指令发送成功";
			return AjaxResult.success(msg);
		} catch (Exception e) {
			String msg = "挂断指令发送失败,发生异常 ：" + e.getMessage();
			return AjaxResult.error(msg);
		}
	}
	
	@Override
	@Transactional
	public void startCallDsq() {
		try {
			RcsEntity rcsEntity = new RcsEntity();
			String sxsj = getSxsj();
			List<Map<String, Object>> list = rcsMapper.getXgnr(sxsj);
			for(Map<String, Object> m:list) {
				String zbnr = m.get("TZNR").toString();
				String zbrId = m.get("ZBR_ID").toString();
				String[] userId = zbrId.split(",");
				rcsEntity.setCmd("2");
				rcsEntity.setContent(zbnr);
				String xgtzId = "";
				for(int i = 0;i < userId.length;i++){
					UserEntity user = AuthSystemFacade.getUserInfoByUserId(userId[i]);
					rcsEntity.setCellvalue(user.getPoliceaffair());
					rcsEntity.setCusNumber(user.getOrgUnitKey());
					rcsEntity.setJobNo(user.getJobNo());
					rcsEntity.setUserName(user.getUserName());
					rcsEntity.setSendTime(new Date());
					rcsMapper.insert(rcsEntity);
					if("".equals(xgtzId)){
						xgtzId = rcsEntity.getId();
					}else{
						xgtzId = xgtzId + "," + rcsEntity.getId();
					}
					RcsMessageBodyDto rcsBody = new RcsMessageBodyDto();
					rcsBody.setCmd(rcsEntity.getCmd());
					
					RcsMessageDto para = new RcsMessageDto();
					para.setUserNum(rcsEntity.getCellvalue() != null ? rcsEntity.getCellvalue().split(",") : null);
					para.setTerFlag(rcsEntity.getId());
					para.setContent(rcsEntity.getContent());
					rcsBody.setPara(gson.toJson(para));
					
					String sendMsg = this.createMessage(rcsEntity.getCusNumber(), rcsBody);
					mqMessageSender.sendRcsMessage(sendMsg, rcsEntity.getCusNumber(), "5");
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", m.get("ID").toString());
				map.put("xgtzId", xgtzId);
				rcsMapper.updateXgtzId(map);
			}
		} catch (Exception e) {
			System.out.println("呼叫指令发送失败,发生异常 ：" + e.getMessage());
		}
	}

	@Override
	@Transactional
	public AjaxResult startYjya(String pcIp, YjyaEntity yjyaEntity) {
		try {
			RcsMessageBodyDto yjyaBody = new RcsMessageBodyDto();
			yjyaBody.setCmd(yjyaEntity.getCmd());
			
			YjyaMessageDto para = new YjyaMessageDto();
			para.setTerFlag(yjyaEntity.getTerFlag());
			para.setPlan(yjyaEntity.getPlan());
			para.setEchelon(yjyaEntity.getEchelon());
			para.setSupplement(yjyaEntity.getSupplement());
			para.setVoiceTxt(yjyaEntity.getVoiceTxt());
			para.setName(yjyaEntity.getName());
			para.setDate(yjyaEntity.getDate());
			para.setTime(yjyaEntity.getTime());
			para.setArea(yjyaEntity.getArea());
			para.setLocation(yjyaEntity.getLocation());
			para.setSta(yjyaEntity.getSta());
			
			yjyaBody.setPara(gson.toJson(para));
			
			String sendMsg = this.createMessage(yjyaEntity.getCusNumber(), yjyaBody);
			mqMessageSender.sendRcsMessage(sendMsg, yjyaEntity.getCusNumber(), "5");
			String msg = "开启应急预案指令发送成功";
			return AjaxResult.success(msg);
		} catch (Exception e) {
			String msg = "开启应急预案指令发送失败，发生异常：" + e.getMessage();
			return AjaxResult.error(msg);
		}
	}

	/**
	 * 封装消息头与消息内容
	 * @param cusNumber
	 * @param msgBody
	 * @return
	 */
	private String createMessage(String cusNumber, RcsMessageBodyDto msgBody) {
		// 消息头
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setCusNumber(cusNumber);// 新增的消息头
		msgHeader.setMsgID(MsgIdUtil.getMsgSeq(""));
		msgHeader.setMsgType("DISPATCH001");
		msgHeader.setLength(0);
		msgHeader.setSender("Server");
		msgHeader.setRecevier("Server");
		msgHeader.setSendTime(DateUtil.getDateString(new Date(), DateUtil.sdf));
		
		JSONObject out = new JSONObject();
		out.put("header", msgHeader);
		out.put("body", msgBody);
		
		JsonObject outJson = new JsonObject();
		outJson.add("header", gson.fromJson(gson.toJson(msgHeader), JsonObject.class));
		outJson.add("body", gson.fromJson(gson.toJson(msgBody), JsonObject.class));
		
		return gson.toJson(outJson);
	}

	/**
	 * 封装消息头与消息内容--对讲
	 * @param cusNumber
	 * @param msgBody
	 * @return
	 */
	private String createMessage_talk(String cusNumber, JSONObject msgBody) {
		/*
		 * String head = "\"header\":{\"cusNumber\":\"" + cusNumber + "\",\"msgID\":\""
		 * + MsgIdUtil.getMsgSeq("") +
		 * "\",\"msgType\":\"TALK001\",\"sender\":\"Server\",\"sendTime\":\"" +
		 * currentTime + "\",\"recevier\":\"Server\",\"length\":0}";
		 */
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setCusNumber(cusNumber);// 新增的消息头
		msgHeader.setMsgID(MsgIdUtil.getMsgSeq(""));
		msgHeader.setMsgType("TALK001");
		msgHeader.setLength(0);
		msgHeader.setSender("Server");
		msgHeader.setRecevier("Server");
		msgHeader.setSendTime(DateUtil.getDateString(new Date(), DateUtil.sdf));
		JSONObject out = new JSONObject();
		out.put("header", msgHeader);
		out.put("body", msgBody);
		return out.toJSONString();
	}

	
	public Page<Map<String, String>> searchRcs(RcsEntity rcs, PageRequest pageRequest) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("rcs", rcs);
		return getDao().searchRcs(map,pageRequest);
	}
	public Page<Map<String, String>> searchJl(RcsEntity rcs, PageRequest pageRequest) {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("rcs", rcs);
		return getDao().searchJl(map,pageRequest);
	}
	

	@Override
	@Transactional
	public void processMessage(String cusNumber, JSONObject jsonObj) {
		RcsEntity rcsEntity = new RcsEntity();
		JSONObject json = JSONObject.parseObject(jsonObj.get("body").toString());
		String type = JSONObject.parseObject(json.get("msg").toString()).get("para2").toString();
		String id = JSONObject.parseObject(json.get("msg").toString()).get("terFlag").toString();
		String msgType = JSONObject.parseObject(json.get("msg").toString()).get("msgType").toString();
		if("5".equals(msgType)){
			rcsMapper.updateYrzq(id);
		}
		rcsEntity.setId(id);
		rcsEntity.setType(type);
		int count = this.getDao().updateType(rcsEntity);
		System.out.println("通信融合返回消息处理Service");
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
