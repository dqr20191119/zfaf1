package com.cesgroup.prison.vehicle.bean;

import java.io.Serializable;

/**
 * 
 * @author lincoln.cheng
 *
 */
public class VehicleBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 车牌号
	 */
	private String CarNo;
	/**
	 * 车辆进出状态：进车/出车
	 */
	private String Status;
	/**
	 * 照片1
	 */
	private String Photo1;
	/**
	 * 照片2
	 */
	private String Photo2;
	/**
	 * 车辆进出时间
	 */
	private String Time;
	/**
	 * 车辆放行结果
	 */
	private String Result;
	/**
	 * 门禁值班人
	 */
	private String Watch;
	/**
	 * 门禁所在位置
	 */
	private String Location;
	
	public String getCarNo() {
		return CarNo;
	}
	public void setCarNo(String carNo) {
		CarNo = carNo;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPhoto1() {
		return Photo1;
	}
	public void setPhoto1(String photo1) {
		Photo1 = photo1;
	}
	public String getPhoto2() {
		return Photo2;
	}
	public void setPhoto2(String photo2) {
		Photo2 = photo2;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getResult() {
		return Result;
	}
	public void setResult(String result) {
		Result = result;
	}
	public String getWatch() {
		return Watch;
	}
	public void setWatch(String watch) {
		Watch = watch;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
}