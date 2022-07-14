package com.cesgroup.prison.route.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
 *     
* 创建人：mq  
* 创建时间：2017年12月20日 下午5:57:01   
* @version
 */
@Entity
@Table(name = "CDS_ROAM_PATH_INFO")
public class Route extends StringIDEntity {
	private static final long serialVersionUID = 1L;
	//路线名称
	private String rpiName;
	//监狱编码
	private String rpiCusNumber;
	//备注
	private String rpiRemark;
	//创建时间
	private Date rpiCreateTime;
	//创建人id
	private String rpiCreateUserId;
	//修改人id
	private String rpiUpdateUserId;
	//修改时间
	private Date rpiUpdateTime;
    //巡视速度
	private Double rpiSpeed;
	//路线类型（1-公共，2-私人）
	private Integer rpiRouteType;
	//路线所属部门
	private String rpiDepartCode;
	//部门名称		
	private String rpiDepartName;
	//巡视路线水平高度
	private Double rpiHorizonHeight;
	//巡视路线上是否显示箭头指示(0:否; 1:是;)
	private String rpiShowArrow;
	
 	public String getRpiDepartName() {
		return rpiDepartName;
	}

	public void setRpiDepartName(String rpiDepartName) {
		this.rpiDepartName = rpiDepartName;
	}

	public Integer getRpiRouteType() {
		return rpiRouteType;
	}

	public void setRpiRouteType(Integer rpiRouteType) {
		this.rpiRouteType = rpiRouteType;
	}

	public String getRpiDepartCode() {
		return rpiDepartCode;
	}

	public void setRpiDepartCode(String rpiDepartCode) {
		this.rpiDepartCode = rpiDepartCode;
	}

	public Double getRpiSpeed() {
		return rpiSpeed;
	}
	
	public void setRpiSpeed(Double rpiSpeed) {
		this.rpiSpeed = rpiSpeed;
	}

	public String getRpiName() {
		return rpiName;
	}

	public void setRpiName(String rpiName) {
		this.rpiName = rpiName;
	}

	public String getRpiCusNumber() {
		return rpiCusNumber;
	}

	public void setRpiCusNumber(String rpiCusNumber) {
		this.rpiCusNumber = rpiCusNumber;
	}

	public String getRpiRemark() {
		return rpiRemark;
	}

	public void setRpiRemark(String rpiRemark) {
		this.rpiRemark = rpiRemark;
	}

	public Date getRpiCreateTime() {
		return rpiCreateTime;
	}

	public void setRpiCreateTime(Date rpiCreateTime) {
		this.rpiCreateTime = rpiCreateTime;
	}

	public String getRpiCreateUserId() {
		return rpiCreateUserId;
	}

	public void setRpiCreateUserId(String rpiCreateUserId) {
		this.rpiCreateUserId = rpiCreateUserId;
	}

	public String getRpiUpdateUserId() {
		return rpiUpdateUserId;
	}

	public void setRpiUpdateUserId(String rpiUpdateUserId) {
		this.rpiUpdateUserId = rpiUpdateUserId;
	}

	public Date getRpiUpdateTime() {
		return rpiUpdateTime;
	}

	public void setRpiUpdateTime(Date rpiUpdateTime) {
		this.rpiUpdateTime = rpiUpdateTime;
	}

	public Double getRpiHorizonHeight() {
		return rpiHorizonHeight;
	}

	public void setRpiHorizonHeight(Double rpiHorizonHeight) {
		this.rpiHorizonHeight = rpiHorizonHeight;
	}

	public String getRpiShowArrow() {
		return rpiShowArrow;
	}

	public void setRpiShowArrow(String rpiShowArrow) {
		this.rpiShowArrow = rpiShowArrow;
	}
}
