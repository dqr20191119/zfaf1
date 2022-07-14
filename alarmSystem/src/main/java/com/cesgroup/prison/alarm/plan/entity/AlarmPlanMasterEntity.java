package com.cesgroup.prison.alarm.plan.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**      
* @projectName：prison   
* @ClassName：AlarmPlanMasterEntity   
* @Description：   报警预案主表实体
* @author：Tao.xu   
* @date：2017年12月25日 下午6:17:38   
* @version        
*/
@Entity
@Table(name = "CDS_PLAN_MASTER")
public class AlarmPlanMasterEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = 1L;

	private String pmaCusNumber;

	private String pmaPlanName;

	private String pmaRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pmaCrteTime;

	private String pmaCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date pmaUpdtTime;

	private String pmaUpdtUserId;

	@Transient
	private List<AlarmPlanItemDtlsEntity> items;

	@Transient
	private List<AlertPlanRltnEntity> alerts;
	@Transient
	private List<AlarmPlanBroadcastPlan> broadcastPlan;

     @Transient
     private List<String> emerPlanNames;

    public void setEmerPlanNames(List<String> emerPlanNames) {
        this.emerPlanNames = emerPlanNames;
    }

    public List<String> getEmerPlanNames() {
        return emerPlanNames;
    }

    public List<AlarmPlanBroadcastPlan> getBroadcastPlan() {
		return broadcastPlan;
	}

	public void setBroadcastPlan(List<AlarmPlanBroadcastPlan> broadcastPlan) {
		this.broadcastPlan = broadcastPlan;
	}

	public List<AlertPlanRltnEntity> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<AlertPlanRltnEntity> alerts) {
		this.alerts = alerts;
	}

	public String getPmaCusNumber() {
		return pmaCusNumber;
	}

	public void setPmaCusNumber(String pmaCusNumber) {
		this.pmaCusNumber = pmaCusNumber;
	}

	public String getPmaPlanName() {
		return pmaPlanName;
	}

	public void setPmaPlanName(String pmaPlanName) {
		this.pmaPlanName = pmaPlanName;
	}

	public String getPmaRemark() {
		return pmaRemark;
	}

	public void setPmaRemark(String pmaRemark) {
		this.pmaRemark = pmaRemark;
	}

	public Date getPmaCrteTime() {
		return pmaCrteTime;
	}

	public void setPmaCrteTime(Date pmaCrteTime) {
		this.pmaCrteTime = pmaCrteTime;
	}

	public String getPmaCrteUserId() {
		return pmaCrteUserId;
	}

	public void setPmaCrteUserId(String pmaCrteUserId) {
		this.pmaCrteUserId = pmaCrteUserId;
	}

	public Date getPmaUpdtTime() {
		return pmaUpdtTime;
	}

	public void setPmaUpdtTime(Date pmaUpdtTime) {
		this.pmaUpdtTime = pmaUpdtTime;
	}

	public String getPmaUpdtUserId() {
		return pmaUpdtUserId;
	}

	public void setPmaUpdtUserId(String pmaUpdtUserId) {
		this.pmaUpdtUserId = pmaUpdtUserId;
	}

	public List<AlarmPlanItemDtlsEntity> getItems() {
		return items;
	}

	public void setItems(List<AlarmPlanItemDtlsEntity> items) {
		this.items = items;
	}

}
