package com.cesgroup.prison.jfsb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**   
*      
* @ClassName：PowerNetwork   
* @Description：   
* @author：zhengk   
* @date：2018年1月16日 下午1:30:36       
*  
*/
@Entity
@Table(name="DVC_POWER_NETWORK_BASE_DTLS")
public class PowerNetwork extends StringIDEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 监狱编号
	 */
	private String pnbCusNumber;
	/**
	 * 电网编号
	 */
    private String pnbIdnty;
    /**
     * 电网名称
     */
    private String pnbName;
    /**
     * 电网品牌
     */
    private String pnbBrand;
    /**
     * （暂未使用）
     */
    private String pnbModel;
    /**
     * IP地址
     */
    private String pnbIp;
    /**
     * 端口号
     */
    private String pnbPort;
    /**
     * 设备安装地址
     */
    private String pnbAddrs;
    /**
     * （暂未使用）
     */
    private String pnbDprtmnt;
    /**
     * 所属区域编码
     */
    private String pnbArea;
    /**
     * （暂未使用）
     */
    private String pnbView;
    /**
     * A相电压
     */
    private Double pnbABoxVoltage;
    /**
     * B相电压
     */
    private Double pnbBBoxVoltage;
    /**
     * 电源电压
     */
    private Double pnbPowerSourceVoltage;
    /**
     * A相电流
     */
    private Double pnbABoxPowerFlow;
    /**
     * B相电流
     */
    private Double pnbBBoxPowerFlow;
    /**
     * 电源电流
     */
    private Double pnbPowerSourceFlow;
    /**
     * 时间（暂未使用）
     */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date pnbTime;
    /**
     * 状态
     */
    private String pnbSttsIndc;
    /**
     * 备注
     */
    private String pnbRemark;
    /**
     * 创建时间
     */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date pnbCrteTime;
	/**
	 * 创建人ID
	 */
    private String pnbCrteUserId;
    /**
     * 更新时间
     */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date pnbUpdtTime;
	/**
	 * 更新人ID
	 */
    private String pnbUpdtUserId;
    /**
     * 所属区域名称
     */
    private String pnbAreaName;
    /**
     * 排序
     */
    private Integer pnbOrderId;
    
	public String getPnbCusNumber() {
		return pnbCusNumber;
	}
	public void setPnbCusNumber(String pnbCusNumber) {
		this.pnbCusNumber = pnbCusNumber;
	}
	public String getPnbIdnty() {
		return pnbIdnty;
	}
	public void setPnbIdnty(String pnbIdnty) {
		this.pnbIdnty = pnbIdnty;
	}
	public String getPnbName() {
		return pnbName;
	}
	public void setPnbName(String pnbName) {
		this.pnbName = pnbName;
	}
	public String getPnbBrand() {
		return pnbBrand;
	}
	public void setPnbBrand(String pnbBrand) {
		this.pnbBrand = pnbBrand;
	}
	public String getPnbModel() {
		return pnbModel;
	}
	public void setPnbModel(String pnbModel) {
		this.pnbModel = pnbModel;
	}
	public String getPnbIp() {
		return pnbIp;
	}
	public void setPnbIp(String pnbIp) {
		this.pnbIp = pnbIp;
	}
	public String getPnbPort() {
		return pnbPort;
	}
	public void setPnbPort(String pnbPort) {
		this.pnbPort = pnbPort;
	}
	public String getPnbAddrs() {
		return pnbAddrs;
	}
	public void setPnbAddrs(String pnbAddrs) {
		this.pnbAddrs = pnbAddrs;
	}
	public String getPnbDprtmnt() {
		return pnbDprtmnt;
	}
	public void setPnbDprtmnt(String pnbDprtmnt) {
		this.pnbDprtmnt = pnbDprtmnt;
	}
	public String getPnbArea() {
		return pnbArea;
	}
	public void setPnbArea(String pnbArea) {
		this.pnbArea = pnbArea;
	}
	public String getPnbView() {
		return pnbView;
	}
	public void setPnbView(String pnbView) {
		this.pnbView = pnbView;
	}
	public Double getPnbABoxVoltage() {
		return pnbABoxVoltage;
	}
	public void setPnbABoxVoltage(Double pnbABoxVoltage) {
		this.pnbABoxVoltage = pnbABoxVoltage;
	}
	public Double getPnbBBoxVoltage() {
		return pnbBBoxVoltage;
	}
	public void setPnbBBoxVoltage(Double pnbBBoxVoltage) {
		this.pnbBBoxVoltage = pnbBBoxVoltage;
	}
	public Double getPnbPowerSourceVoltage() {
		return pnbPowerSourceVoltage;
	}
	public void setPnbPowerSourceVoltage(Double pnbPowerSourceVoltage) {
		this.pnbPowerSourceVoltage = pnbPowerSourceVoltage;
	}
	public Double getPnbABoxPowerFlow() {
		return pnbABoxPowerFlow;
	}
	public void setPnbABoxPowerFlow(Double pnbABoxPowerFlow) {
		this.pnbABoxPowerFlow = pnbABoxPowerFlow;
	}
	public Double getPnbBBoxPowerFlow() {
		return pnbBBoxPowerFlow;
	}
	public void setPnbBBoxPowerFlow(Double pnbBBoxPowerFlow) {
		this.pnbBBoxPowerFlow = pnbBBoxPowerFlow;
	}
	public Double getPnbPowerSourceFlow() {
		return pnbPowerSourceFlow;
	}
	public void setPnbPowerSourceFlow(Double pnbPowerSourceFlow) {
		this.pnbPowerSourceFlow = pnbPowerSourceFlow;
	}
	public Date getPnbTime() {
		return pnbTime;
	}
	public void setPnbTime(Date pnbTime) {
		this.pnbTime = pnbTime;
	}
	public String getPnbSttsIndc() {
		return pnbSttsIndc;
	}
	public void setPnbSttsIndc(String pnbSttsIndc) {
		this.pnbSttsIndc = pnbSttsIndc;
	}
	public String getPnbRemark() {
		return pnbRemark;
	}
	public void setPnbRemark(String pnbRemark) {
		this.pnbRemark = pnbRemark;
	}
	public Date getPnbCrteTime() {
		return pnbCrteTime;
	}
	public void setPnbCrteTime(Date pnbCrteTime) {
		this.pnbCrteTime = pnbCrteTime;
	}
	public String getPnbCrteUserId() {
		return pnbCrteUserId;
	}
	public void setPnbCrteUserId(String pnbCrteUserId) {
		this.pnbCrteUserId = pnbCrteUserId;
	}
	public Date getPnbUpdtTime() {
		return pnbUpdtTime;
	}
	public void setPnbUpdtTime(Date pnbUpdtTime) {
		this.pnbUpdtTime = pnbUpdtTime;
	}
	public String getPnbUpdtUserId() {
		return pnbUpdtUserId;
	}
	public void setPnbUpdtUserId(String pnbUpdtUserId) {
		this.pnbUpdtUserId = pnbUpdtUserId;
	}
	public String getPnbAreaName() {
		return pnbAreaName;
	}
	public void setPnbAreaName(String pnbAreaName) {
		this.pnbAreaName = pnbAreaName;
	}
	public Integer getPnbOrderId() {
		return pnbOrderId;
	}
	public void setPnbOrderId(Integer pnbOrderId) {
		this.pnbOrderId = pnbOrderId;
	}
}
