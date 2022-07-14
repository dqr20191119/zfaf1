package com.cesgroup.prison.zbgl.hbsq.entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;
/**
* @author lihong
* @date 创建时间：2020年5月15日 上午11:20:25
* 类说明:
*/
@Table(name = "CDS_DUTY_HBSQ")
public class HbsqEntity extends StringIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cusNumber ; 
	private String deptNumber;
	/**
	 * 换班人原值班班次
	 */
	private String  zbOrder;
	/**
	 * 换班人原值班日期
	 */
	private String dutyDate;
	/**
	 * 替代人
	 */
	private String tdr;
	/**
	 * 替代人原值班班次
	 */
	private String tdrzbOrder;
	/**
	 * 申请人
	 */
	private String sqr;
	/**
	 * 申请时间
	 */
	private String sqDate;
	/**
	 * 审批意见 0.未审批 1.同意  2.不同意
	 */
	private String spyj;
	/**
	 * 换班人
	 */
	private String hbr;
	/**
	 * 审批人
	 */
	private String spr;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 换班理由
	 */
	private String hbLy;
	/**
	 * 审批时间
	 */
	private String spDate;
	/**
	 *  替代人的原值班日期
	 */
	private String hbDate;
	/**
	 * 提交状态 0.未提交 1.已经提交
	 */
	private String zt;
	
	/**
	 * 审批人姓名
	 */
	private String sprName;
	/**
	 * 申请人姓名
	 */
	private String sqrName;
	/**
	 * 文件id
	 */
	@Transient
	private String files;
	
	@Transient
	private String startDate;
	
	@Transient
	private String endDate;
	@Transient
	private String  hbrName;
	
	@Transient
	private String  tdrName;
	
	
	
	public String getHbrName() {
		return hbrName;
	}
	public void setHbrName(String hbrName) {
		this.hbrName = hbrName;
	}
	public String getTdrName() {
		return tdrName;
	}
	public void setTdrName(String tdrName) {
		this.tdrName = tdrName;
	}
	public String getTdrzbOrder() {
		return tdrzbOrder;
	}
	public void setTdrzbOrder(String tdrzbOrder) {
		this.tdrzbOrder = tdrzbOrder;
	}
	public String getSprName() {
		return sprName;
	}
	public void setSprName(String sprName) {
		this.sprName = sprName;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public String getSqrName() {
		return sqrName;
	}
	public void setSqrName(String sqrName) {
		this.sqrName = sqrName;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getHbLy() {
		return hbLy;
	}
	public void setHbLy(String hbLy) {
		this.hbLy = hbLy;
	}
	public String getSpDate() {
		return spDate;
	}
	public void setSpDate(String spDate) {
		this.spDate = spDate;
	}
	public String getHbDate() {
		return hbDate;
	}
	public void setHbDate(String hbDate) {
		this.hbDate = hbDate;
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
	public String getZbOrder() {
		return zbOrder;
	}
	public void setZbOrder(String zbOrder) {
		this.zbOrder = zbOrder;
	}
	public String getDutyDate() {
		return dutyDate;
	}
	public void setDutyDate(String dutyDate) {
		this.dutyDate = dutyDate;
	}
	public String getTdr() {
		return tdr;
	}
	public void setTdr(String tdr) {
		this.tdr = tdr;
	}
	public String getSqr() {
		return sqr;
	}
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	public String getSqDate() {
		return sqDate;
	}
	public void setSqDate(String sqDate) {
		this.sqDate = sqDate;
	}
	public String getSpyj() {
		return spyj;
	}
	public void setSpyj(String spyj) {
		this.spyj = spyj;
	}
	public String getHbr() {
		return hbr;
	}
	public void setHbr(String hbr) {
		this.hbr = hbr;
	}
	public String getSpr() {
		return spr;
	}
	public void setSpr(String spr) {
		this.spr = spr;
	}
	
	
	
	
	
}
