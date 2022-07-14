package com.cesgroup.prison.zbgl.pbtbhz.entity;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
* @author lihong
* @date 创建时间：2020年5月25日 下午4:12:18
* 类说明:
*/
@Table(name = "CDS_DUTY_JYZB_DETAIL")
public class JyzbDetailEntity extends StringIDEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 值班日期
	 */
	private String dutyDate;
	/**
	 * 指挥长
	 */
	private String zhz;
	/**
	 * 值班长
	 */
	private String zbz;
	private String zhzId;
	private String zbzId;
	private String zbyId;
	
	
	
	/**
	 * 值班员
	 */
	private String zby;
	private String cjrq;
	private String cjrId;
	private String cjrName;
	private String updateDate;
	private String updateId;
	private String updateName;
	/**
	 * CDS_DUTY_JYZB_BASE  监狱值班填报 主表id
	 */
	private String ywId;
	private String cusNumber;
	@Transient
	private String zbDh;//值班电话
	@Transient
	private String zbYf;
	
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
	public String getZhzId() {
		return zhzId;
	}
	public void setZhzId(String zhzId) {
		this.zhzId = zhzId;
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
	public String getZhz() {
		return zhz;
	}
	public void setZhz(String zhz) {
		this.zhz = zhz;
	}
	public String getZbz() {
		return zbz;
	}
	public void setZbz(String zbz) {
		this.zbz = zbz;
	}
	public String getZby() {
		return zby;
	}
	public void setZby(String zby) {
		this.zby = zby;
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
	public String getYwId() {
		return ywId;
	}
	public void setYwId(String ywId) {
		this.ywId = ywId;
	}
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	
	
}
