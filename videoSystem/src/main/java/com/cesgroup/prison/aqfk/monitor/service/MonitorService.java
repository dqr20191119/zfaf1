package com.cesgroup.prison.aqfk.monitor.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.prison.aqfk.monitor.entity.MonitorDoc;

public interface MonitorService {

	public MonitorDoc findById(String id);
	
	public Page<Map<String, String>> searchMonitor(MonitorDoc monitorDoc_param,String startTime,String endTime,Pageable pageable)  throws Exception;

	public Page<Map<String, String>> searchYFMonitor(MonitorDoc monitorDoc_param,String startTime,String endTime,Pageable pageable)  throws Exception;

	public Page<Map<String, String>> searchMonitorByInspectId(String inspectId,Pageable pageable)  throws Exception;
	public List<Map<String, String>> searchMonitorIdsByInspectId(String inspectId);
	//查询已接收监督单信息
	public Page<Map<String,String>> searchReceivedMonitor(Map<String,Object> map, Pageable pageable) throws Exception;
	//查询已接收监督单信息
	public Page<Map<String,String>> searchDeptReceivedMonitor(Map<String,Object> map, Pageable pageable) throws Exception;
	//局部修改
	public void updatePart(MonitorDoc monitorDoc_param,HttpServletRequest request) throws Exception;
	//根据监督单Id修改关联的证据使用状态
	public void updateEviStatusByMonId(Map<String, Object> map);
	//更改接收监督单的查看状态
	public void updateMrrStatus(Map<String, Object> map);
	//批量新增监督单证据关联信息
	public void batchInsertMonitorAndEvidence(List<Map<String,Object>> list);
	//批量新增监督单接收人关联信息
	public void batchInsertMonitorAndRecipient(List<Map<String,Object>> list,HttpServletRequest request);
	//批量新增监督单和网络通报关联信息
	public void batchInsertMonitorAndInspect(List<Map<String,Object>> list) throws Exception;
	//查询监督单关联证据信息
    public List<Map<String,Object>> searchRelationMonEvi(String monitorSqno);
    //查询监督单关联证据信息(图片url集合)
    public List<Map<String,Object>> searchRelationImgList(String monitorSqno);
    //查询监督单关联接收人信息
    public List<Map<String,Object>> searchRelationMonRec(String cusNumber,String monitorSqno);
    //删除与监督关联的证据信息
    public void delRelationMonEvi(String cusNumber,String monitorSqno);
    //删除与监督关联的接收人信息 
    public void delRelationMonRec(String cusNumber,String monitorSqno);
    //根据网络督查通报id删除关联的监督单信息
    public int deleteMonitorByInspectId(String inspectId);
    //删除已建监督单（现在把已建监督单作为监督单列表使用）
    public AjaxMessage deleteYjjdd(String cusNumber,String monitorSqno);
    //查询监督单在巡查通报中被使用的次数
    public int searchMonitorInXctbCount(Map<String, Object> map);
}
