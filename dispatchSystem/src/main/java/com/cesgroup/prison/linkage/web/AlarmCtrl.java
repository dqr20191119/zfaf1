package com.cesgroup.prison.linkage.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.alarm.messager.bean.AlarmMessageBean;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.bean.WebSocketMessage;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.framework.util.StringUtil;
import com.cesgroup.prison.common.constants.alarm.AlarmConstants;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.prison.door.bean.DoorCardBean;
import com.cesgroup.prison.door.bean.PoliceInoutBean;
import com.cesgroup.prison.fm.handle.door.DoorMessageHandle;
import com.cesgroup.prison.fm.util.MsgTypeConst;
import com.cesgroup.prison.framework.utils.FtpUtil;
import com.cesgroup.prison.linkage.service.IAlarmService;
import com.cesgroup.prison.messager.alarm.activator.AlarmMessageActivator;
import com.cesgroup.prison.messager.alarm.process.AlarmMessageProcess;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.cesgroup.prison.sql.service.IAddService;
import com.cesgroup.scrap.db.QueryProcess;
import com.cesgroup.scrap.db.SequenceUtil;

@Controller
@RequestMapping("/cdsAlarm")
public class AlarmCtrl extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AlarmCtrl.class);

	@Resource
	private MqMessageSender mqMessageSender;
	@Resource
	private QueryProcess queryProcess;
	@Resource
	private SequenceUtil sequenceUtil;
	@Resource
	private IAlarmService alarmService2;
	@Resource
	private IAddService addService;
	@Resource
	private FtpUtil  ftpUtil;
	@Resource
	private DoorMessageHandle doorMessageHandle;
	@Resource
	private AlarmMessageActivator alarmMessageActivator;

	@RequestMapping("testdoor")
	@ResponseBody
	public void testdoor() {
		
		MsgHeader header = new MsgHeader();
		header.setCusNumber("6506");
		header.setMsgID("201801100000000002");
		// header.setMsgType(MsgTypeConst.POLICE_INOUT);
		header.setMsgType(MsgTypeConst.DOOR_CARD);
		header.setSender("6506_门禁监控系统");
		header.setSendTime("2018-04-26 20:56:51");
		header.setRecevier("服务器");
		header.setLength(0);
		
		/*PoliceInoutBean PoliceInoutBean = new PoliceInoutBean();
		PoliceInoutBean.setPeopleID("6507129");
		PoliceInoutBean.setPeopleName("赵亮");
		PoliceInoutBean.setPeopleType("1");
		PoliceInoutBean.setCompareTime("2018-04-26 20:54:25");
		PoliceInoutBean.setInOutFlag("2");
		PoliceInoutBean.setCompareFlag("1");
		PoliceInoutBean.setDoorID("0028");
		PoliceInoutBean.setImage("");*/
		
		DoorCardBean doorCardBean = new DoorCardBean();
		doorCardBean.setDoorID("123");
		doorCardBean.setCardID("22");
		doorCardBean.setBrushCardTime("2018-04-26 12:12:12");
		doorCardBean.setPeopleType("1");
		doorCardBean.setInOutFlag("0");
		doorCardBean.setStatus("0");
		doorCardBean.setRemark("abc");
				
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("header", header);
		//jsonObject.put("body", PoliceInoutBean);	
		jsonObject.put("body", doorCardBean);	
 		doorMessageHandle.handle(jsonObject.toJSONString(), "6506");
	}
	
	@RequestMapping("testalarm")
	@ResponseBody
	public void testalarm() {
		
		MsgHeader header = new MsgHeader();
		header.setCusNumber("6506");
		header.setMsgID("201801100000000001");
		header.setMsgType(AlarmConstants.ALARM_MSG_001);
		header.setSender("6506_报警监控系统");
		header.setSendTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		header.setRecevier("服务器");
		header.setLength(0);
		
		AlarmMessageBean alarmMsg = new AlarmMessageBean();
		alarmMsg.setAlarmID("2071");
		alarmMsg.setAlarmDeviceType("6");
		alarmMsg.setAlarmType("");
		alarmMsg.setAlarmTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		alarmMsg.setAlarmAction("1");
		alarmMsg.setRemark("");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("header", header);
		jsonObject.put("body", alarmMsg);	
		alarmMessageActivator.handle(jsonObject.toJSONString(), "6506");
	}
	
	@RequestMapping("handle")
	@ResponseBody
	public AjaxMessage handle(@RequestParam() String args) {
		logger.debug("传进的参数===" + args);
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if (args != null && !args.trim().isEmpty()) {
				//将前台参数转换为json对象
				JSONObject params = JSONObject.parseObject(args);
				String cusNumber = params.getString("cusNumber");//机构编号
				String dvcIdnty = params.getString("dvcIdnty");//报警器编号
				String oprtnStatus = params.getString("oprtnStatus");//处理状态
				String alarmType= params.getString("alarmType");//报警类型
				String recordId= params.getString("recordId");//部门编号

				if (AlarmConstants.ALARM_DVC_TYPE_8.equals(alarmType)) {//人工报警
					result = alarmService2.manualHandle(cusNumber, dvcIdnty,recordId,oprtnStatus,alarmType);
				} else {
					result = alarmService2.handle(cusNumber, dvcIdnty,recordId,oprtnStatus,alarmType);
				}
				flag = true;
			}else{
				flag = false;
				ajaxMsg.setMsg("参数不能为空");
			}
			
		} catch (Exception e) {
			flag = false;
			logger.error("报警处置出现异常", e);
		}
		logger.debug("处理"+(flag?"成功":"失败")+",result="+result);
		if(flag){
			ajaxMsg.setObj(result);
		}else{
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		
		return ajaxMsg;
	}
	
	/**
	 * 省局处理
	 * @param args
	 * @return
	 */
	@RequestMapping("provinceHandle")
	@ResponseBody
	public AjaxMessage provinceHandle(@RequestParam() String args) {
		logger.debug("传进的参数===" + args);
		boolean flag = true;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			JSONObject params = JSONObject.parseObject(args);
			String cusNumber = params.getString("cusNumber");
			String alarmRecordId = params.getString("alarmRecordId");
			String alarmMsg = RedisCache.getHashMap(AlarmMessageProcess.alarmMsaMap, alarmRecordId).toString();
			WebSocketMessage webSocketMessage = new WebSocketMessage();
			webSocketMessage.setMsgType(AlarmConstants.ALARM_MSG_3007);
			webSocketMessage.setContent(JSONObject.toJSONString(alarmMsg));
			// 向前台发送消息
			mqMessageSender.sendNettyMessage(JSONObject.toJSONString(webSocketMessage),String.valueOf(cusNumber));
		} catch (Exception e) {
			flag = false;
			logger.error("报警上级处理异常", e);
		}
		ajaxMsg.setSuccess(flag);
		return ajaxMsg;
	}
	
	@RequestMapping("changeReceiveStatus")
	@ResponseBody
	public AjaxMessage changeReceiveStatus(@RequestParam() String args) {
		logger.debug("传进的参数===" + args);
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if(args != null && !args.trim().isEmpty()){
				//将前台参数转换为json对象
				JSONObject params = JSONObject.parseObject(args);
				String cusNumber = params.getString("cusNumber");//机构编号
				String userId = params.getString("userId");//用户编号
				String recordId = params.getString("alarmRecordId");//报警记录编号:ARD_RECORD_ID
				String receiveStatus = params.getString("receiveStatus");//接警状态
				result = alarmService2.changeReceiveStatus(cusNumber,userId,recordId,receiveStatus);
				flag = true;
			}else{
				flag = false;
				ajaxMsg.setMsg("参数不能为空");
			}
			
		} catch (Exception e) {
			flag = false;
			logger.error("报警处置：更改接警状态出现异常", e);
		}
		logger.debug("处理"+(flag?"成功":"失败")+",result="+result);
		if(flag){
			ajaxMsg.setObj(result);
		}else{
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		
		return ajaxMsg;
	}
	
	@RequestMapping("receive")
	@ResponseBody
	public AjaxMessage receiveAlarm(@RequestParam() String args) {
		logger.debug("传进的参数===" + args);
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if(args != null && !args.trim().isEmpty()){
				//将前台参数转换为json对象
				JSONObject params = JSONObject.parseObject(args);
				result = alarmService2.receive(params);
				flag = true;
			}else{
				flag = false;
				ajaxMsg.setMsg("参数不能为空");
			}
			
		} catch (Exception e) {
			flag = false;
			logger.error("报警处置：接警出现异常", e);
		}
		logger.debug("处理"+(flag?"成功":"失败")+",result="+result);
		if(flag){
			ajaxMsg.setObj(result);
		}else{
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		
		return ajaxMsg;
	}

	@RequestMapping("send")
	@ResponseBody
	public AjaxMessage saveAlarm(@RequestParam() String args) {
		logger.debug("传进的参数===" + args);
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if(args != null && !args.trim().isEmpty()){
				//将前台参数转换为json对象
				JSONObject params = JSONObject.parseObject(args);
				result = alarmService2.manualAlarm(params);
				flag = true;
			}else{
				flag = false;
				ajaxMsg.setMsg("参数不能为空");
			}
			
		} catch (Exception e) {
			flag = false;
			logger.error("报警处置：人工报警出现异常", e);
		}
		logger.debug("处理"+(flag?"成功":"失败")+",result="+result);
		if(flag){
			ajaxMsg.setObj(result);
		}else{
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		
		return ajaxMsg;
	}
	
/*	@RequestMapping("sendAlarmMsg")
	@ResponseBody
	public AjaxMessage sendAlarmMsg(@RequestParam() String args) {
		logger.debug("传进的参数===" + args);
		boolean flag = false;
		String result = "发送失败";
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if(args != null && !args.trim().isEmpty()){
				//将前台参数转换为json对象
				JSONObject params = JSONObject.parseObject(args);
				alertService.processMessage(params);
				result = "发送成功";
				flag = true;
			}else{
				flag = false;
				ajaxMsg.setMsg("参数不能为空");
			}
			
		} catch (Exception e) {
			flag = false;
			logger.error("报警处置：模拟发送报警出现异常", e);
		}
		logger.debug("处理"+(flag?"成功":"失败")+",result="+result);
		if(flag){
			ajaxMsg.setObj(result);
		}else{
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		
		return ajaxMsg;
	}*/

	@RequestMapping("addFlowData")
	@ResponseBody
	public AjaxMessage addFlowData(@RequestParam() String args) {
		logger.debug("传进的参数===" + args);
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if(args != null && !args.trim().isEmpty()){
				//将前台参数转换为json对象
				JSONObject params = JSONObject.parseObject(args);
				result = alarmService2.addFlowData(params);
				flag = true;
			}else{
				flag = false;
				ajaxMsg.setMsg("参数不能为空");
			}
			
		} catch (Exception e) {
			flag = false;
			logger.error("报警性质：数据保存出现异常", e);
		}
		logger.debug("处理"+(flag?"成功":"失败")+",result="+result);
		if(flag){
			ajaxMsg.setObj(result);
		}else{
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		
		return ajaxMsg;
	}
	
	@RequestMapping("addAlarmType")
	@ResponseBody
	public AjaxMessage addAlarmType(@RequestParam() String args) {
		logger.debug("传进的参数===" + args);
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if(args != null && !args.trim().isEmpty()){
				//将前台参数转换为json对象
				JSONObject params = JSONObject.parseObject(args);
				result = alarmService2.addAlarmType(params);
				flag = true;
			}else{
				flag = false;
				ajaxMsg.setMsg("参数不能为空");
			}
			
		} catch (Exception e) {
			flag = false;
			ajaxMsg.setMsg(e.getMessage().indexOf("ORA-00001")>=0?"该类型级别已存在":e.getMessage());
			logger.error("报警类别：数据保存出现异常", e);
		}
		logger.debug("处理"+(flag?"成功":"失败")+",result="+result);
		if(flag){
			ajaxMsg.setObj(result);
		}
		ajaxMsg.setSuccess(flag);
		
		return ajaxMsg;
	}
	
	@RequestMapping("updateAlarmType")
	@ResponseBody
	public AjaxMessage updateAlarmType(@RequestParam() String args) {
		logger.debug("传进的参数===" + args);
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if(args != null && !args.trim().isEmpty()){
				//将前台参数转换为json对象
				JSONObject params = JSONObject.parseObject(args);
				result = alarmService2.updateAlarmType(params);
				flag = true;
			}else{
				flag = false;
				ajaxMsg.setMsg("参数不能为空");
			}
			
		} catch (Exception e) {
			flag = false;
			logger.error("报警类别：修改数据出现异常", e);
		}
		logger.debug("处理"+(flag?"成功":"失败")+",result="+result);
		if(flag){
			ajaxMsg.setObj(result);
		}else{
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		
		return ajaxMsg;
	}
	
	@RequestMapping("deleteAlarmType")
	@ResponseBody
	public AjaxMessage deleteAlarmType(@RequestParam() String args) {
		logger.debug("传进的参数===" + args);
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if(args != null && !args.trim().isEmpty()){
				//将前台参数转换为json对象
				JSONObject params = JSONObject.parseObject(args);
				String cusNumber = params.getString("cusNumber");
				String alarmTypeId = params.getString("alarmTypeId");
				result = alarmService2.deleteAlarmType(cusNumber,alarmTypeId);
				flag = true;
			}else{
				flag = false;
				ajaxMsg.setMsg("参数不能为空");
			}
			
		} catch (Exception e) {
			flag = false;
			logger.error("报警类别：删除数据出现异常", e);
		}
		logger.debug("处理"+(flag?"成功":"失败")+",result="+result);
		if(flag){
			ajaxMsg.setObj(result);
		}else{
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		
		return ajaxMsg;
	}
	
	/*@RequestMapping("testSeq")
	@ResponseBody
	public AjaxMessage testSeq(@RequestParam() String args) {
		logger.debug("传进的参数===" + args);
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if(args != null && !args.trim().isEmpty()){
				//将前台参数转换为json对象
				result = Seq.getInstance().get();
				flag = true;
			}else{
				flag = false;
				ajaxMsg.setMsg("参数不能为空");
			}
			
		} catch (Exception e) {
			flag = false;
			logger.error("报警类别：删除数据出现异常", e);
		}
		logger.debug("处理"+(flag?"成功":"失败")+",result="+result);
		if(flag){
			ajaxMsg.setObj(result);
		}else{
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		
		return ajaxMsg;
	}*/
	
	/*@Resource
	private CallNameService callNameService;
	@RequestMapping("called")
	@ResponseBody
	public AjaxMessage called(@RequestParam() String args) {
		logger.debug("传进的参数===" + args);
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if(args != null && !args.trim().isEmpty()){
				//将前台参数转换为json对象
				JSONObject params = JSONObject.parseObject(args);
				callNameService.processMessage(params);
				flag = true;
			}else{
				flag = false;
				ajaxMsg.setMsg("参数不能为空");
			}
			
		} catch (Exception e) {
			flag = false;
			logger.error("模拟点名出现异常", e);
		}
		logger.debug("处理"+(flag?"成功":"失败")+",result="+result);
		if(flag){
			ajaxMsg.setObj(result);
		}else{
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		
		return ajaxMsg;
	}*/
	
	/*@Resource
	private ForeignCarRecordService foreignCarRecordService;
	@RequestMapping("foreignCar")
	@ResponseBody
	public AjaxMessage foreignCar(@RequestParam() String args) {
		logger.debug("传进的参数===" + args);
		boolean flag = false;
		String result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			if(args != null && !args.trim().isEmpty()){
				//将前台参数转换为json对象
				JSONObject params = JSONObject.parseObject(args);
				foreignCarRecordService.processMessage(params);
				flag = true;
			}else{
				flag = false;
				ajaxMsg.setMsg("参数不能为空");
			}
			
		} catch (Exception e) {
			flag = false;
			logger.error("模拟点名出现异常", e);
		}
		logger.debug("处理"+(flag?"成功":"失败")+",result="+result);
		if(flag){
			ajaxMsg.setObj(result);
		}else{
			ajaxMsg.setMsg(result);
		}
		ajaxMsg.setSuccess(flag);
		
		return ajaxMsg;
	}*/
	
	

	/**
	 * 图片插入到数据库
	 * @param fileInfo
	 * @param file
	 * @return
	 */
	@RequestMapping("AlarmPicUpload")
	@ResponseBody
	public AjaxMessage addFileInfo(
			@RequestParam(value="fileInfo") String fileInfo,
			@RequestParam(value="file") CommonsMultipartFile file) {
		logger.debug(fileInfo + " fileInfo");
		logger.debug(file + " file");
		JSONObject jsonObj = JSONObject.parseObject(fileInfo);
		String sqlid = jsonObj.getString("sqlid");
		JSONArray array = jsonObj.getJSONArray("parasList");
		String fileSeq = sequenceUtil.getSequence("CDS_ALERT_EVENT_FILE", "AEF_FILE_SEQ");
		array.set(2, fileSeq);
		//要上传的文件名
		String fileName = file.getOriginalFilename();
		array.set(4, fileName);
		byte[] imgFile = file.getBytes();
		array.set(5, imgFile);
		logger.debug(array.toJSONString() + " fileInfo");
		int i = addService.insert(sqlid, array);
		if(i>0){
			return new AjaxMessage(true, null, "");
		}else{
			return new AjaxMessage(false, null, "");
		}
	}
	
	
	/**
	 * 上传文件到FTP服务器
	 * @param fileName
	 * @return
	 */
	@RequestMapping("alarmFTPUpload")
	@ResponseBody
	public AjaxMessage uploadFile(@RequestParam(value="file")CommonsMultipartFile file,
			@RequestParam(value="fileInfo") String fileInfo) {
		try {
			//机构编号取得
			String cusNumber = JSONObject.parseObject(fileInfo).getJSONArray("parasList").getString(0);
			//查询FTP服务器信息
			Map<String,Object> ftpInfo = getFtpInfo(cusNumber);
			//获取FTP服务器信息
			String ip = ""; //IP
			String user = ""; //用户名
			String pwd = ""; //密码
			if(ftpInfo != null && ftpInfo.size() > 0){
				 ip = ftpInfo.get("fsc_server_ip").toString();
				 user = ftpInfo.get("fsc_user_name").toString();
				 pwd = ftpInfo.get("fsc_password").toString();
			}else{
				return new AjaxMessage(false, null, "未查询到FTP服务器信息,无法上传");
			}
			//要上传的文件名
			String fileName = file.getOriginalFilename();
			//连接FTP服务器
			FTPClient ftpClient = ftpUtil.connectFTPServer(ip,user,pwd);
			//上传文件
			if(ftpUtil.uploadForIn(file.getInputStream(),fileName,ftpClient)){
				//上传成功关闭FTP服务器连接
				ftpUtil.closeFTPConnect(ftpClient);
				//相关数据插入数据库
				JSONObject jsonObj = JSONObject.parseObject(fileInfo);
				String sqlid = jsonObj.getString("sqlid");
				JSONArray array = jsonObj.getJSONArray("parasList");
				String fileSeq = sequenceUtil.getSequence("CDS_ALERT_EVENT_FILE", "AEF_FILE_SEQ");
				array.set(2, fileSeq);
				array.set(4, fileName);
				logger.debug(array.toJSONString() + " fileInfo");
				int i = addService.insert(sqlid, array);
				if(i>0){
					return new AjaxMessage(true, null, "");
				}else{
					return new AjaxMessage(false, null, "");
				}
			}else{
				return new AjaxMessage(false, null, "");
			}
		} catch (IOException e) {
			logger.error("上传文件异常",e);
			return new AjaxMessage(false, null, "");
		}
	}
	/**
	 * 获取FTP服务器信息
	 * @param cusNumber
	 * @return
	 */
	public Map<String,Object> getFtpInfo(String cusNumber){
		List<Object> parasList=new ArrayList<Object>();
		parasList.add(cusNumber);
		List<Map<String,Object>> rtnList=new ArrayList<Map<String,Object>>();
		rtnList=queryProcess.process("sys_ftpServer_info_query","0",parasList);
		
		if(rtnList.size() == 0){
			return null;
		}
		
		return rtnList.get(0);
	}
	
	/**
	 * 从FTP服务器下载文件
	 * @param fileName
	 * @return
	 */
	@RequestMapping("alarmFTPDownload")
	@ResponseBody
	public AjaxMessage downloadFile(@RequestParam() String args) {
		JSONObject param = null;
		//机构编号
		String cusNumber = "";
		String ip = ""; //IP
		String user = ""; //用户名
		String pwd = ""; //密码
		if(!StringUtil.isNull(args)){
			param = JSONObject.parseObject(args);
			cusNumber = param.getString("cusNumber");
		}else{
			return new AjaxMessage(false, null, "参数列表为空");
		}
		//查询FTP服务器信息
		Map<String,Object> ftpInfo = getFtpInfo(cusNumber);
		//获取FTP服务器信息
		if(ftpInfo != null && ftpInfo.size() > 0){
			 ip = ftpInfo.get("fsc_server_ip").toString();
			 user = ftpInfo.get("fsc_user_name").toString();
			 pwd = ftpInfo.get("fsc_password").toString();
		}else{
			return new AjaxMessage(false, null, "未查询到FTP服务器信息,无法下载");
		}
		
		//连接FTP服务器
		FTPClient ftpClient = ftpUtil.connectFTPServer(ip,user,pwd);
		//上传文件
		if(ftpUtil.download(param.getString("fileName"), ftpClient,param.getString("fileDir"))){
			//上传成功关闭FTP服务器连接
			ftpUtil.closeFTPConnect(ftpClient);
			return new AjaxMessage(true, null, "");
		}else{
			return new AjaxMessage(false, null, "");
		}
	}
}
