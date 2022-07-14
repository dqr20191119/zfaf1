package com.cesgroup.prison.zbgl.jjb.entity;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
* @author lihong
* @date 创建时间：2020年5月15日 上午9:57:22
* 类说明: 交接班实体类主表
*/
@Table(name = "cds_duty_jjb_base")
public class JjbEntity extends StringIDEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 机构号
	 */
	private String cusNumber;
	/**
	 * 部门号
	 */
	private String deptNumber;
	/**
	 * 值班长姓名
	 */
	private String zbzName;
	/**
	 * 值班员姓名
	 */
	private String zbyName;
	/**
	 * 值班长id
	 */
	private String zbzId;
	/**
	 * 值班员id
	 */
	private String zbyId;
	/**
	 * 值班日期
	 */
	private String dutyDate;
	/**
	 * 值班时间  
	 */
	private String dutyTime;
	/**
	 * 值班班次
	 */
	private String orderName;
	/**
	 * 创建时间
	 */
	private String cjTime;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 更新人id
	 */
	private String gxrId;
	/**
	 * 更新人姓名
	 */
	private String gxrName;
	/**
	 * 状态 0.值班中  1.待值班 2.交接班 3.已完成
	 */
	private String zt;
	/**
	 * 交班时间
	 */
	private String jbTime;
	/**
	 * 值班开始时间
	 */
	private String startTime;
	/**
	 * 值班结束时间
	 */
	private String endTime;
	@Transient
	private String param ;
	
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getJbTime() {
		return jbTime;
	}
	public void setJbTime(String jbTime) {
		this.jbTime = jbTime;
	}
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public String getDeptNumber() {
		return deptNumber;
	}
	public void setDeptNumber(String deptNumber) {
		this.deptNumber = deptNumber;
	}
	public String getZbzName() {
		return zbzName;
	}
	public void setZbzName(String zbzName) {
		this.zbzName = zbzName;
	}
	public String getZbyName() {
		return zbyName;
	}
	public void setZbyName(String zbyName) {
		this.zbyName = zbyName;
	}
	public String getZbzId() {
		return zbzId;
	}
	public void setZbzId(String zbzId) {
		this.zbzId = zbzId;
	}
	public String getZbyId() {
		return zbyId;
	}
	public void setZbyId(String zbyId) {
		this.zbyId = zbyId;
	}
	public String getDutyDate() {
		return dutyDate;
	}
	public void setDutyDate(String dutyDate) {
		this.dutyDate = dutyDate;
	}
	public String getDutyTime() {
		return dutyTime;
	}
	public void setDutyTime(String dutyTime) {
		this.dutyTime = dutyTime;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getCjTime() {
		return cjTime;
	}
	public void setCjTime(String cjTime) {
		this.cjTime = cjTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getGxrId() {
		return gxrId;
	}
	public void setGxrId(String gxrId) {
		this.gxrId = gxrId;
	}
	public String getGxrName() {
		return gxrName;
	}
	public void setGxrName(String gxrName) {
		this.gxrName = gxrName;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	
	
	
}
