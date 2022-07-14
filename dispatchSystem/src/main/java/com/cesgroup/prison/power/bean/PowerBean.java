package com.cesgroup.prison.power.bean;

import java.io.Serializable;

/**
 * 电网Bean
 * 
 * @author monkey
 *
 */
public class PowerBean  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 监狱编号
	 */
	private String cusNumber;
	
	/**
	 * 区域
	 */
	private String prisonNumber;
	/**
	 * 电力网络编号
	 */
	private String powerNetworkIdnty;
	/**
	 * 电力网络名称
	 */
	private String powerNetworkName;
	/**
	 * 
	 */
	private String ip;
	/**
	 * 高压电压A
	 */
	private String aBoxVoltage;
	/**
	 * 高压电压B
	 */
	private String bBoxVoltage;
	
	/**
	 * 电源电压
	 */
	private String powerSourceVoltage;
	/**
	 * 低压电流A
	 */
	private String aBoxPowerFlow;
	/**
	 * 低压电流B
	 */
	private String bBoxPowerFlow;
	/**
	 * 电源功率流
	 */
	private String powerSourcePowerFlow;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 修改时间
	 */
	private String updateTime;
	
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public String getPrisonNumber() {
		return prisonNumber;
	}
	public void setPrisonNumber(String prisonNumber) {
		this.prisonNumber = prisonNumber;
	}
	public String getPowerNetworkIdnty() {
		return powerNetworkIdnty;
	}
	public void setPowerNetworkIdnty(String powerNetworkIdnty) {
		this.powerNetworkIdnty = powerNetworkIdnty;
	}
	public String getPowerNetworkName() {
		return powerNetworkName;
	}
	public void setPowerNetworkName(String powerNetworkName) {
		this.powerNetworkName = powerNetworkName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getaBoxVoltage() {
		return aBoxVoltage;
	}
	public void setaBoxVoltage(String aBoxVoltage) {
		this.aBoxVoltage = aBoxVoltage;
	}
	public String getbBoxVoltage() {
		return bBoxVoltage;
	}
	public void setbBoxVoltage(String bBoxVoltage) {
		this.bBoxVoltage = bBoxVoltage;
	}
	public String getPowerSourceVoltage() {
		return powerSourceVoltage;
	}
	public void setPowerSourceVoltage(String powerSourceVoltage) {
		this.powerSourceVoltage = powerSourceVoltage;
	}
	public String getaBoxPowerFlow() {
		return aBoxPowerFlow;
	}
	public void setaBoxPowerFlow(String aBoxPowerFlow) {
		this.aBoxPowerFlow = aBoxPowerFlow;
	}
	public String getbBoxPowerFlow() {
		return bBoxPowerFlow;
	}
	public void setbBoxPowerFlow(String bBoxPowerFlow) {
		this.bBoxPowerFlow = bBoxPowerFlow;
	}
	public String getPowerSourcePowerFlow() {
		return powerSourcePowerFlow;
	}
	public void setPowerSourcePowerFlow(String powerSourcePowerFlow) {
		this.powerSourcePowerFlow = powerSourcePowerFlow;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
