package com.cesgroup.prison.zbgl.pbtbhz.entity;

import java.util.List;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
* @author lihong
* @date 创建时间：2020年5月25日 下午4:07:18
* 类说明:
*/
@Table(name = "CDS_DUTY_JYZB_BASE")
public class JyzbBaseEntity extends StringIDEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cusNumber;
	/**
	 * 值班月份
	 */
	private String zbYf;
	/**
	 * 值班电话
	 */
	private String zbDh;
	
	private String cjrq;
	private String cjrId;
	private String cjrName;
	/**
	 * 0.未上报 1. 已上报 2.已审核
	 */
	private String zt;
	private String updateDate;
	private String updateId;
	private String updateName;
	@Transient
	private String param;
	@Transient
	private String jyzbDetails;
	
	public String getJyzbDetails() {
		return jyzbDetails;
	}
	public void setJyzbDetails(String jyzbDetails) {
		this.jyzbDetails = jyzbDetails;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public String getZbYf() {
		return zbYf;
	}
	public void setZbYf(String zbYf) {
		this.zbYf = zbYf;
	}
	public String getZbDh() {
		return zbDh;
	}
	public void setZbDh(String zbDh) {
		this.zbDh = zbDh;
	}
	public String getCjrq() {
		return cjrq;
	}
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}
	public String getCjrId() {
		return cjrId;
	}
	public void setCjrId(String cjrId) {
		this.cjrId = cjrId;
	}
	public String getCjrName() {
		return cjrName;
	}
	public void setCjrName(String cjrName) {
		this.cjrName = cjrName;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	
	
}
