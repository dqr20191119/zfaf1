package com.cesgroup.prison.aqfk.monitor.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.aqfk.monitor.dao.MonitorDocMapper;
import com.cesgroup.prison.aqfk.monitor.entity.MonitorDoc;
import com.cesgroup.prison.aqfk.monitor.service.MonitorService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.entity.MessageEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.facade.MessageSendFacade;
import com.cesgroup.prison.common.service.MessageService;
import com.cesgroup.prison.utils.CommonUtil;

@Service
public class MonitorServiceImpl extends BaseDaoService<MonitorDoc,String,MonitorDocMapper> implements MonitorService{
	@Resource
	private MessageService messageService;
	
	public MonitorDoc findById(String id) {
		return this.selectOne(id);
	}
	
	public Page<Map<String, String>> searchMonitor(MonitorDoc monitorDoc_param,String startTime,String endTime,Pageable pageable) throws Exception {
		//UserBean userBean=AuthSystemFacade.getLoginUserInfo();
		//monitorDoc_param.setMdoCrteUserId(userBean.getUserId());
				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("monitor", monitorDoc_param);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return this.getDao().searchMonitor(map, pageable);
	}
	public Page<Map<String, String>> searchYFMonitor(MonitorDoc monitorDoc_param,String startTime,String endTime,Pageable pageable) throws Exception {
		UserBean userBean=AuthSystemFacade.getLoginUserInfo();
		String userLevel = userBean.getUserLevel().toString();
		String userId = userBean.getUserId();
		monitorDoc_param.setMdoCrteUserId(userId);
		monitorDoc_param.setMdoUpdtUserId(userId);	
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("monitor", monitorDoc_param);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("userLevel", userLevel);
		return this.getDao().searchYFMonitor(map, pageable);
	}
	//查询部门已接收监督单信息
	public Page<Map<String,String>> searchDeptReceivedMonitor(Map<String,Object> map, Pageable pageable) throws Exception {
		UserBean userBean=AuthSystemFacade.getLoginUserInfo();
		String cusNumber =  userBean.getOrgCode();
		String departmentId = userBean.getDprtmntCode();
		String userLevel = userBean.getUserLevel().toString();
		
		map.put("cusNumber", cusNumber);
		map.put("departmentId", departmentId);
		map.put("userLevel", userLevel);
		
		return this.getDao().searchDeptReceivedMonitor(map, pageable);
	}
	//查询已接收监督单信息
	public Page<Map<String,String>> searchReceivedMonitor(Map<String,Object> map, Pageable pageable) throws Exception {
		UserBean userBean=AuthSystemFacade.getLoginUserInfo();
		String cusNumber =  userBean.getOrgCode();
		String rcpntIdnty = userBean.getUserId();
		String userLevel = userBean.getUserLevel().toString();
		
		map.put("cusNumber", cusNumber);
		map.put("rcpntIdnty", rcpntIdnty);
		map.put("userLevel", userLevel);
		
		return this.getDao().searchReceivedMonitor(map, pageable);
	}
	//局部修改
	@Transactional
	public void updatePart(MonitorDoc monitorDoc_param,HttpServletRequest request) throws Exception{
		UserBean userBean=AuthSystemFacade.getLoginUserInfo();
		//用户等级
		String userLevel =userBean.getUserLevel().toString();
		String cusNumber = userBean.getOrgCode();
		//当修改推送状态时，才修改更新人、更新时间
		if(!(monitorDoc_param.getModSttsIndc()==null && "".equals(monitorDoc_param.getModSttsIndc()))) {
			String userId=userBean.getUserId();
			monitorDoc_param.setMdoUpdtUserId(userId);
			monitorDoc_param.setMdoUpdtTime(new Date());
		}
		this.getDao().updatePart(monitorDoc_param);
				
		MonitorDoc monitorDoc= this.selectOne(monitorDoc_param.getId());
		//当监督单推送或者下发时 提醒某个用户或某些用户
		//"MOD_STTS_INDC"	0 已创建 1已推送 2已下发
		/*if("1".equals(monitorDoc_param.getModSttsIndc()) || "2".equals(monitorDoc_param.getModSttsIndc())) {
			//省局(通过发布订阅方式，推给监狱用户)
			if("1".equals(userLevel)) {
				String url = request.getContextPath()+"/monitor/jdjc";
				Map<String, String> msgMap = new HashMap<String, String>();
				msgMap.put("msgType", "6001");
				msgMap.put("sendType", "1");
				msgMap.put("sendTo", monitorDoc.getMdoNoticeCusNumber());
				msgMap.put("content", "您有待查看的监督单");
	 			msgMap.put("url", url);
	 			msgMap.put("isSendToGzt", "1");
	 			msgMap.put("ywId", monitorDoc_param.getId());
	 			msgMap.put("cusNumber",cusNumber);
				MessageSendFacade.send(msgMap);
			}else{
				List<Map<String,Object>> list = searchRelationMonRec(null,monitorDoc_param.getId());
				String userIds="";
				for (int i = 0; i < list.size(); i++) {
					if(i==0) {
						userIds=(String)list.get(i).get("MRR_RCPNT_IDNTY");
					}else {
						userIds=userIds+","+(String)list.get(i).get("MRR_RCPNT_IDNTY");
					}
				}
				//提醒某个用户或某些用户
				String url = request.getContextPath()+"/monitor/jdjc";
				Map<String, String> msgMap = new HashMap<String, String>();
				msgMap.put("msgType", "6001");
				msgMap.put("sendType", "2");
				msgMap.put("sendTo", userIds);
				msgMap.put("content", "您有待查看的监督单");
	 			msgMap.put("url", url);
	 			msgMap.put("isSendToGzt", "1");
	 			msgMap.put("ywId", monitorDoc_param.getId());
	 			msgMap.put("cusNumber",cusNumber);
				MessageSendFacade.send(msgMap);
			}	
		}	
		//当监督单查阅后反馈（针对省局发送的监督单，监狱级用户的查阅状态）--  mdoConsultStatus 0，未查阅 1，已查阅 
		if("1".equals(monitorDoc_param.getMdoConsultStatus())) {
			//监狱级用户
			if("2".equals(userLevel)) {
				MessageEntity messageEntity=new MessageEntity();
				messageEntity.setYwId(monitorDoc.getId());
				messageEntity.setJyId(monitorDoc.getMdoNoticeCusNumber());
				messageService.updateReadByYwId(messageEntity);
			}
		}*/
		if("1".equals(monitorDoc_param.getModSttsIndc()) || "2".equals(monitorDoc_param.getModSttsIndc())) {
			//省局(通过发布订阅方式，推给监狱用户)
			if("1".equals(userLevel)) {
				String url = request.getContextPath()+"/monitor/jddlb";
				Map<String, String> msgMap = new HashMap<String, String>();
				msgMap.put("msgType", "6001");
				msgMap.put("sendType", "1");
				msgMap.put("sendTo", monitorDoc.getMdoNoticeCusNumber());
				msgMap.put("content", "您有待查看的监督单");
	 			msgMap.put("url", url);
	 			msgMap.put("isSendToGzt", "1");
	 			msgMap.put("ywId", monitorDoc_param.getId());
	 			msgMap.put("cusNumber",cusNumber);
				msgMap.put("noticeContent", "您有待查看的监督单");
				MessageSendFacade.send(msgMap);
			}else{
				String url = request.getContextPath()+"/monitor/jddlb";
				Map<String, String> msgMap = new HashMap<String, String>();
				msgMap.put("msgType", "6001");
				msgMap.put("sendType", "3");
				msgMap.put("sendTo", monitorDoc.getMdoNoticeDepartment());
				msgMap.put("content", "您有待查看的监督单");
	 			msgMap.put("url", url);
	 			msgMap.put("isSendToGzt", "1");
	 			msgMap.put("ywId", monitorDoc_param.getId());
	 			msgMap.put("cusNumber",cusNumber);
				msgMap.put("noticeContent", "您有待查看的监督单");
				MessageSendFacade.send(msgMap);
			}	
		}	
		//当监督单查阅后反馈（针对省局发送的监督单，监狱级用户的查阅状态）--  mdoConsultStatus 0，未查阅 1，已查阅 
		if("1".equals(monitorDoc_param.getMdoConsultStatus())) {
			MessageEntity messageEntity=new MessageEntity();
			messageEntity.setYwId(monitorDoc.getId());
			messageEntity.setJyId(monitorDoc.getMdoNoticeCusNumber());
			messageService.updateReadByYwId(messageEntity);
		}
	}
	//根据监督单Id修改关联的证据使用状态
	@Transactional
	public void updateEviStatusByMonId(Map<String, Object> map){
		this.getDao().updateEviStatusByMonId(map);
	}
	@Transactional
	public void batchInsertMonitorAndEvidence(List<Map<String,Object>> list) {
		if(list == null || list.size()==0) {
			return;
		}
		for (Map<String, Object> map : list) {
			map.put("id", CommonUtil.createUUID());
		}
		this.getDao().batchInsertMonitorAndEvidence(list);
	}
	@Transactional
	public void batchInsertMonitorAndRecipient(List<Map<String,Object>> list,HttpServletRequest request){
		for (Map<String, Object> map : list) {
			map.put("id", CommonUtil.createUUID());
		}
		this.getDao().batchInsertMonitorAndRecipient(list);
	}
	
	//查询监督单关联证据信息
    public List<Map<String,Object>> searchRelationMonEvi(String monitorSqno){
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("monitorSqno", monitorSqno);
    	return this.getDao().searchRelationMonEvi(map);
    }
    //查询监督单关联证据信息(图片url集合)
    public List<Map<String,Object>> searchRelationImgList(String monitorSqno){
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("monitorSqno", monitorSqno);
    	return this.getDao().searchRelationImgList(map);
    }
    //查询监督单关联接收人信息
    public List<Map<String,Object>> searchRelationMonRec(String cusNumber,String monitorSqno){
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("cusNumber", cusNumber);
		map.put("monitorSqno", monitorSqno);
    	return this.getDao().searchRelationMonRec(map);
    }
    //删除与监督关联的证据信息
    @Transactional
    public void delRelationMonEvi(String cusNumber,String monitorSqno){
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("cusNumber", cusNumber);
		map.put("monitorSqno", monitorSqno);
		this.getDao().delRelationMonEvi(map);
    }
    //删除与监督关联的接收人信息 
    @Transactional
    public void delRelationMonRec(String cusNumber,String monitorSqno){
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("cusNumber", cusNumber);
		map.put("monitorSqno", monitorSqno);
		this.getDao().delRelationMonRec(map);
    }
    //更改接收监督单的查看状态
	@Override
	@Transactional
	public void updateMrrStatus(Map<String, Object> map) {
		this.getDao().updateMrrStatus(map);	
		//当监督单查阅后反馈
		if("1".equals(map.get("mrrStatus"))){
			MessageEntity messageEntity=new MessageEntity();
			messageEntity.setYwId((String)map.get("mrrMonitorSqno"));
			messageEntity.setNoticeUserId((String)map.get("mrrRcpntIdnty"));
			messageService.updateReadByYwId(messageEntity);
		}
	}
	@Override
	public Page<Map<String, String>> searchMonitorByInspectId(String inspectId, Pageable pageable) throws Exception {
		return this.getDao().searchMonitorByInspectId(inspectId, pageable);
	}
	//根据网络督查通报id查询关联的监督单ID(通过创建时间升序排序)
	@Override
	public List<Map<String, String>> searchMonitorIdsByInspectId(String inspectId){
		return this.getDao().searchMonitorIdsByInspectId(inspectId);
	}
	@Override
	@Transactional
	public void batchInsertMonitorAndInspect(List<Map<String, Object>> list) throws Exception {
		UserBean userBean=AuthSystemFacade.getLoginUserInfo();
		for (Map<String, Object> map : list) {
			map.put("IMR_CUS_NUMBER", userBean.getOrgCode());
			map.put("IMR_REMARK", "");
			map.put("IMR_CRTE_TIME", new Date());
			map.put("IMR_CRTE_USER_ID", userBean.getUserId());
			map.put("IMR_UPDT_TIME", new Date());
			map.put("IMR_UPDT_USER_ID", userBean.getUserId());
		}
		this.getDao().batchInsertMonitorAndInspect(list);
	}
	//根据网络督查通报id删除关联的监督单信息
	@Override
	@Transactional
    public int deleteMonitorByInspectId(String inspectId) {
    	return this.getDao().deleteMonitorByInspectId(inspectId);
    }
	
	//查询监督单在巡查通报中被使用的次数
	@Override
    public int searchMonitorInXctbCount(Map<String, Object> map) {
    	return this.getDao().searchMonitorInXctbCount(map);
    }
	
	//删除已建监督单（现在把已建监督单作为监督单列表使用）
	@Override
	@Transactional
    public AjaxMessage deleteYjjdd(String cusNumber,String monitorSqno){
		try {
			//如果监督单已被巡查通报使用则不可以删除
			Map<String, Object> searchMonitorInXctbCount_map = new HashMap<String, Object>();
			searchMonitorInXctbCount_map.put("imrMonitorSqno", monitorSqno);
			int count = searchMonitorInXctbCount(searchMonitorInXctbCount_map);
			if(count>0) {
				return new AjaxMessage(false,null,"该监督单已被巡查通报使用，不可删除！");
			}
			
			//删除监督单
			this.getDao().delete(monitorSqno);
			//删除与监督关联的接收人信息 
			delRelationMonRec(cusNumber,monitorSqno);
			//根据监督单Id修改关联的证据使用状态
			//(必须先修改证据使用状态，再去删除与监督关联的证据信息，否则会找不到监督单关联的证据)
			Map<String, Object> updateEviStatus_map = new HashMap<String, Object>();
			updateEviStatus_map.put("einSttsIndc", "0");				// 0 - 未使用状态
			updateEviStatus_map.put("monitorId", monitorSqno);
			updateEviStatusByMonId(updateEviStatus_map);
			//删除与监督关联的证据信息
			delRelationMonEvi(cusNumber,monitorSqno);	
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
}
