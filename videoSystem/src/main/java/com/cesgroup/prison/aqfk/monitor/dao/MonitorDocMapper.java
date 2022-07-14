package com.cesgroup.prison.aqfk.monitor.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.aqfk.monitor.entity.MonitorDoc;

public interface MonitorDocMapper extends BaseDao<MonitorDoc,String>{

	public Page<Map<String,String>> searchMonitor(Map<String,Object> map, Pageable pageable);
	
	public Page<Map<String,String>> searchYFMonitor(Map<String,Object> map, Pageable pageable);
	
	//根据网络督查通报id查询关联的监督单信息
	public Page<Map<String, String>> searchMonitorByInspectId(String inspectId,Pageable pageable)  throws Exception;
	//根据网络督查通报id查询关联的监督单ID(通过创建时间升序排序)
	public List<Map<String, String>> searchMonitorIdsByInspectId(String inspectId);
	//查询已接收监督单信息
	public Page<Map<String,String>> searchReceivedMonitor(Map<String,Object> map, Pageable pageable);
	//查询部门已接收监督单信息
	public Page<Map<String,String>> searchDeptReceivedMonitor(Map<String,Object> map, Pageable pageable);
	public int updatePart(MonitorDoc record);
	//根据监督单Id修改关联的证据使用状态
	public void updateEviStatusByMonId(Map<String, Object> map);
	//更改接收监督单的查看状态
	public void updateMrrStatus(Map<String, Object> map);
    public void batchInsertMonitorAndEvidence(List<Map<String,Object>> list);
    public void batchInsertMonitorAndRecipient(List<Map<String,Object>> list);
    //批量新增监督单和网络通报关联信息
  	public void batchInsertMonitorAndInspect(List<Map<String,Object>> list);
    //查询监督单关联证据信息
    public List<Map<String,Object>> searchRelationMonEvi(Map<String, Object> map);
    //查询监督单关联证据信息(图片url集合)
    public List<Map<String,Object>> searchRelationImgList(Map<String, Object> map);
    //查询监督单关联接收人信息
    public List<Map<String,Object>> searchRelationMonRec(Map<String, Object> map);
    //删除与监督关联的证据信息
    public void delRelationMonEvi(Map<String, Object> map);
    //删除与监督关联的接收人信息 
    public void delRelationMonRec(Map<String, Object> map);
    //根据网络督查通报id删除关联的监督单信息
    public int deleteMonitorByInspectId(String inspectId);
    //查询监督单在巡查通报中被使用的次数
    public int searchMonitorInXctbCount(Map<String, Object> map);
}