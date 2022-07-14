package com.cesgroup.prison.broadcastPlan.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.prison.jfsb.entity.BroadcastEntity;

/**
 * 
 *广播预案
 */
@Entity
@Table(name = "DVC_BROADCAST_PLAN")
public class BroadcastPlan extends StringIDEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 监狱编号
	 */
	private String dbpCusNumber;
	/**
	 * 广播id
	 */
    private String dbpBroadcastId;
    /**
     * 曲目id
     * 
     */
    private String dbpBroadcastFileDtlsId;
    private String dbpCreateUserId ;
    private String dbpUpdateUserId;
    private String dbpCreateTime;
    private String dbpUpdateTime;
    private String dbpBroadcastPlanName;
    private String dbpBroadcastName;
    private String dbpBroadcastFileDtlsName;
    
    @Transient
    private List<Map<String,Object>>  broadcasts;
	
    
	
	
	public List<Map<String, Object>> getBroadcasts() {
		return broadcasts;
	}
	public void setBroadcasts(List<Map<String, Object>> broadcasts) {
		this.broadcasts = broadcasts;
	}
	public String getDbpBroadcastPlanName() {
		return dbpBroadcastPlanName;
	}
	public void setDbpBroadcastPlanName(String dbpBroadcastPlanName) {
		this.dbpBroadcastPlanName = dbpBroadcastPlanName;
	}
	public String getDbpBroadcastName() {
		return dbpBroadcastName;
	}
	public void setDbpBroadcastName(String dbpBroadcastName) {
		this.dbpBroadcastName = dbpBroadcastName;
	}
	public String getDbpBroadcastFileDtlsName() {
		return dbpBroadcastFileDtlsName;
	}
	public void setDbpBroadcastFileDtlsName(String dbpBroadcastFileDtlsName) {
		this.dbpBroadcastFileDtlsName = dbpBroadcastFileDtlsName;
	}
	public String getDbpCusNumber() {
		return dbpCusNumber;
	}
	public void setDbpCusNumber(String dbpCusNumber) {
		this.dbpCusNumber = dbpCusNumber;
	}
	public String getDbpBroadcastId() {
		return dbpBroadcastId;
	}
	public void setDbpBroadcastId(String dbpBroadcastId) {
		this.dbpBroadcastId = dbpBroadcastId;
	}
	public String getDbpBroadcastFileDtlsId() {
		return dbpBroadcastFileDtlsId;
	}
	public void setDbpBroadcastFileDtlsId(String dbpBroadcastFileDtlsId) {
		this.dbpBroadcastFileDtlsId = dbpBroadcastFileDtlsId;
	}
	public String getDbpCreateUserId() {
		return dbpCreateUserId;
	}
	public void setDbpCreateUserId(String dbpCreateUserId) {
		this.dbpCreateUserId = dbpCreateUserId;
	}
	public String getDbpUpdateUserId() {
		return dbpUpdateUserId;
	}
	public void setDbpUpdateUserId(String dbpUpdateUserId) {
		this.dbpUpdateUserId = dbpUpdateUserId;
	}
	public String getDbpCreateTime() {
		return dbpCreateTime;
	}
	public void setDbpCreateTime(String dbpCreateTime) {
		this.dbpCreateTime = dbpCreateTime;
	}
	public String getDbpUpdateTime() {
		return dbpUpdateTime;
	}
	public void setDbpUpdateTime(String dbpUpdateTime) {
		this.dbpUpdateTime = dbpUpdateTime;
	}
    
    
	
}