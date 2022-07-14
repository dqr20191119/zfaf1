package com.cesgroup.prison.jfsb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 广播曲目实体类
 * @author lincoln.cheng
 *
 */
@Entity
@Table(name = "DVC_BROADCAST_FILE_DTLS")
public class BroadcastFile extends StringIDEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 监狱编号
	 */
	private String bfdCusNumber;
	/**
	 * 曲目编号
	 */
    private String bfdIdnty;
    /**
     * 曲目名称
     */
    private String bfdName;
    /**
     * 备注
     */
    private String bfdRemark;
    /**
     * 状态（0：有效；1：无效）
     */
    private String bfdSttsIndc;
    /**
     * 排序
     */
    private Long bfdOrder;
    /**
     * 数据创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bfdCrteTime;
    /**
     * 数据创建人
     */
    private String bfdCrteUserId;
    /**
     * 数据更新人
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bfdUpdtTime;
    /**
     * 数据更新人
     */
    private String bfdUpdtUserId;
    
	public String getBfdCusNumber() {
		return bfdCusNumber;
	}
	public void setBfdCusNumber(String bfdCusNumber) {
		this.bfdCusNumber = bfdCusNumber;
	}
	public String getBfdIdnty() {
		return bfdIdnty;
	}
	public void setBfdIdnty(String bfdIdnty) {
		this.bfdIdnty = bfdIdnty;
	}
	public String getBfdName() {
		return bfdName;
	}
	public void setBfdName(String bfdName) {
		this.bfdName = bfdName;
	}
	public String getBfdRemark() {
		return bfdRemark;
	}
	public void setBfdRemark(String bfdRemark) {
		this.bfdRemark = bfdRemark;
	}
	public String getBfdSttsIndc() {
		return bfdSttsIndc;
	}
	public void setBfdSttsIndc(String bfdSttsIndc) {
		this.bfdSttsIndc = bfdSttsIndc;
	}
	public Long getBfdOrder() {
		return bfdOrder;
	}
	public void setBfdOrder(Long bfdOrder) {
		this.bfdOrder = bfdOrder;
	}
	public Date getBfdCrteTime() {
		return bfdCrteTime;
	}
	public void setBfdCrteTime(Date bfdCrteTime) {
		this.bfdCrteTime = bfdCrteTime;
	}
	public String getBfdCrteUserId() {
		return bfdCrteUserId;
	}
	public void setBfdCrteUserId(String bfdCrteUserId) {
		this.bfdCrteUserId = bfdCrteUserId;
	}
	public Date getBfdUpdtTime() {
		return bfdUpdtTime;
	}
	public void setBfdUpdtTime(Date bfdUpdtTime) {
		this.bfdUpdtTime = bfdUpdtTime;
	}
	public String getBfdUpdtUserId() {
		return bfdUpdtUserId;
	}
	public void setBfdUpdtUserId(String bfdUpdtUserId) {
		this.bfdUpdtUserId = bfdUpdtUserId;
	}
}