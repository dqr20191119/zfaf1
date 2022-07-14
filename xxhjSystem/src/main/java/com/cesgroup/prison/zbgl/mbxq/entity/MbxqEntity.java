package com.cesgroup.prison.zbgl.mbxq.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

/*值班模板详情表*/
@Table(name = "cds_duty_mode_order_job")
public class MbxqEntity extends StringIDEntity {
	
	private String mojCusNumber;
	
	private String mojModeId;                      // 模板编号
	
	private String mojJobId;                       //岗位ID
	
	private String mojOrderId;                    //班次ID
	
	private String mojUpdtUserId;                 //更新人ID
	
	private Date   mojUpdtTime;                     //更新时间

      private Integer mojSqlno;  //每次排模板的序号

	@Column(updatable = false)
	private Date mojCrteTime;						// 创建时间
	
	@Column(updatable = false)						
	private String mojCrteUserId;                   // 创建人
	
	@Transient
	private String dorDutyOrderName;                //班次名称
	
	@Transient
	private String dorStartTime;
	
	@Transient
	private String dorEndTime;
	
	@Transient
	private String cdjJobName;

    public Integer getMojSqlno() {
        return mojSqlno;
    }

    public void setMojSqlno(Integer mojSqlno) {
        this.mojSqlno = mojSqlno;
    }

    public String getDorDutyOrderName() {
		return dorDutyOrderName;
	}

	public void setDorDutyOrderName(String dorDutyOrderName) {
		this.dorDutyOrderName = dorDutyOrderName;
	}

	public String getDorStartTime() {
		return dorStartTime;
	}

	public void setDorStartTime(String dorStartTime) {
		this.dorStartTime = dorStartTime;
	}

	public String getDorEndTime() {
		return dorEndTime;
	}

	public void setDorEndTime(String dorEndTime) {
		this.dorEndTime = dorEndTime;
	}

	public String getCdjJobName() {
		return cdjJobName;
	}

	public void setCdjJobName(String cdjJobName) {
		this.cdjJobName = cdjJobName;
	}

	public String getMojCusNumber() {
		return mojCusNumber;
	}

	public void setMojCusNumber(String mojCusNumber) {
		this.mojCusNumber = mojCusNumber;
	}

	public String getMojModeId() {
		return mojModeId;
	}

	public void setMojModeId(String mojModeId) {
		this.mojModeId = mojModeId;
	}

	public String getMojJobId() {
		return mojJobId;
	}

	public void setMojJobId(String mojJobId) {
		this.mojJobId = mojJobId;
	}

	public String getMojOrderId() {
		return mojOrderId;
	}

	public void setMojOrderId(String mojOrderId) {
		this.mojOrderId = mojOrderId;
	}

	public String getMojUpdtUserId() {
		return mojUpdtUserId;
	}

	public void setMojUpdtUserId(String mojUpdtUserId) {
		this.mojUpdtUserId = mojUpdtUserId;
	}

	public Date getMojUpdtTime() {
		return mojUpdtTime;
	}

	public void setMojUpdtTime(Date mojUpdtTime) {
		this.mojUpdtTime = mojUpdtTime;
	}

	public Date getMojCrteTime() {
		return mojCrteTime;
	}

	public void setMojCrteTime(Date mojCrteTime) {
		this.mojCrteTime = mojCrteTime;
	}

	public String getMojCrteUserId() {
		return mojCrteUserId;
	}

	public void setMojCrteUserId(String mojCrteUserId) {
		this.mojCrteUserId = mojCrteUserId;
	}
	
}
