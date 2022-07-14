package com.cesgroup.prison.foreign.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 外来车辆信息表实体类
 * @author lincoln
 *
 */
@Entity
@Table(name = "CDS_FOREIGN_CAR_DTLS")
public class ForeignCarDtls extends StringIDEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 车牌号
	 */
	private String fcdCarLcnsIdnty;
	/**
	 * 车辆所属单位
	 */
    private String fcdCarCmpny;
    /**
     * 车辆类型 1、小型车 2、中型车 3、大型车
     */
    private String fcdCarTypeIndc;
    /**
     * 车身颜色
     */
    private String fcdCarColor;
    /**
     * 车内人数
     */
    private Integer fcdPeopleCount;
    /**
     * 所载货物
     */
    private String fcdCargo;
    /**
     * 进车登记ID
     */
    private String fcdRegisterId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fcdCrteTime;
    /**
     * 创建人ID
     */
    private String fcdCrteUserId;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fcdUpdtTime;
    /**
     * 更新人ID
     */
    private String fcdUpdtUserId;
    
	public String getFcdCarLcnsIdnty() {
		return fcdCarLcnsIdnty;
	}
	public void setFcdCarLcnsIdnty(String fcdCarLcnsIdnty) {
		this.fcdCarLcnsIdnty = fcdCarLcnsIdnty;
	}
	public String getFcdCarCmpny() {
		return fcdCarCmpny;
	}
	public void setFcdCarCmpny(String fcdCarCmpny) {
		this.fcdCarCmpny = fcdCarCmpny;
	}
	public String getFcdCarTypeIndc() {
		return fcdCarTypeIndc;
	}
	public void setFcdCarTypeIndc(String fcdCarTypeIndc) {
		this.fcdCarTypeIndc = fcdCarTypeIndc;
	}
	public String getFcdCarColor() {
		return fcdCarColor;
	}
	public void setFcdCarColor(String fcdCarColor) {
		this.fcdCarColor = fcdCarColor;
	}
	public Integer getFcdPeopleCount() {
		return fcdPeopleCount;
	}
	public void setFcdPeopleCount(Integer fcdPeopleCount) {
		this.fcdPeopleCount = fcdPeopleCount;
	}
	public String getFcdCargo() {
		return fcdCargo;
	}
	public void setFcdCargo(String fcdCargo) {
		this.fcdCargo = fcdCargo;
	}
	public String getFcdRegisterId() {
		return fcdRegisterId;
	}
	public void setFcdRegisterId(String fcdRegisterId) {
		this.fcdRegisterId = fcdRegisterId;
	}
	public Date getFcdCrteTime() {
		return fcdCrteTime;
	}
	public void setFcdCrteTime(Date fcdCrteTime) {
		this.fcdCrteTime = fcdCrteTime;
	}
	public String getFcdCrteUserId() {
		return fcdCrteUserId;
	}
	public void setFcdCrteUserId(String fcdCrteUserId) {
		this.fcdCrteUserId = fcdCrteUserId;
	}
	public Date getFcdUpdtTime() {
		return fcdUpdtTime;
	}
	public void setFcdUpdtTime(Date fcdUpdtTime) {
		this.fcdUpdtTime = fcdUpdtTime;
	}
	public String getFcdUpdtUserId() {
		return fcdUpdtUserId;
	}
	public void setFcdUpdtUserId(String fcdUpdtUserId) {
		this.fcdUpdtUserId = fcdUpdtUserId;
	}
}