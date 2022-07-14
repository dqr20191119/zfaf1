package com.cesgroup.prison.foreign.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 外来车辆信息表实体类DTO
 * @author lincoln.cheng
 * 
 * 2019/02/20
 */
public class ForeignCarDtlsDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private String id;
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
     * 出入登记ID
     */
    private String fcdRegisterId;
    /**
     * 监狱编号
     */
    private String frCusNumber;
    /**
     * 车辆进出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date frTime;
    /**
     * 车辆进出类型
     */
    private String frType;
    /**
     * 值班人员
     */
    private String frCheckPeople;
    /**
     * 车辆进出位置
     */
    private String frLocation;
    /**
     * 车辆车牌照片
     */
    private String frPhoto1;
    /**
     * 车辆底盘照片
     */
    private String frPhoto2;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getFrCusNumber() {
		return frCusNumber;
	}
	public void setFrCusNumber(String frCusNumber) {
		this.frCusNumber = frCusNumber;
	}
	public Date getFrTime() {
		return frTime;
	}
	public void setFrTime(Date frTime) {
		this.frTime = frTime;
	}
	public String getFrType() {
		return frType;
	}
	public void setFrType(String frType) {
		this.frType = frType;
	}
	public String getFrCheckPeople() {
		return frCheckPeople;
	}
	public void setFrCheckPeople(String frCheckPeople) {
		this.frCheckPeople = frCheckPeople;
	}
	public String getFrLocation() {
		return frLocation;
	}
	public void setFrLocation(String frLocation) {
		this.frLocation = frLocation;
	}
	public String getFrPhoto1() {
		return frPhoto1;
	}
	public void setFrPhoto1(String frPhoto1) {
		this.frPhoto1 = frPhoto1;
	}
	public String getFrPhoto2() {
		return frPhoto2;
	}
	public void setFrPhoto2(String frPhoto2) {
		this.frPhoto2 = frPhoto2;
	}
}