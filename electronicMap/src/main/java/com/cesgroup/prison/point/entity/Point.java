package com.cesgroup.prison.point.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 点位管理
 * 
 * @author mxz
 * @date 2017-12-20
 *
 */
@Entity
@Table(name = "CDS_ARCGIS_LAYER_POINTS")
public class Point extends StringIDEntity{
	private static final long serialVersionUID = 1L;

	//点位id
	@Column(name="ID")
	private String id;

    //监狱id
	@Column(name="ALP_CUS_NUMBER")
	private String alpCusNumber;

	//设备类型
	@Column(name="ALP_DEVICE_TYPE")
    private String alpDeviceType;
    //设备类型
	@Column(name="ALP_DEVICE_MODEL")
	private String alpDeviceModel;
    //设备id
	@Column(name="ALP_DEVICE_IDNTY")
    private String alpDeviceIdnty;

	//设备中文名
	@Column(name="ALP_DEVICE_NAME")
    private String alpDeviceName;

	//模型X轴位置
	@Column(name="ALP_X_POINT_IDNTY")
    private String alpXPointIdnty;

	//模型Y轴位置
	@Column(name="ALP_Y_POINT_IDNTY")
    private String alpYPointIdnty;

	//模型Z轴位置
	@Column(name="ALP_Z_POINT_IDNTY")
	private String alpZPointIdnty;

	//点位是否有效
	@Column(name="ALP_STATU_IDNC")
    private int alpStatuIdnc;

	//创建日期
	@Column(name="ALP_CRTE_TIME")
    private Date alpCrteTime;

	//创建人
	@Column(name="ALP_CRTE_USER_ID")
    private String alpCrteUserId;

	//修改日期
	@Column(name="ALP_UPDT_TIME")
    private Date alpUpdtTime;

	//修改人
	@Column(name="ALP_UPDT_USER_ID")
    private String alpUpdtUserId;


	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getAlpCusNumber() {
		return alpCusNumber;
	}

	public void setAlpCusNumber(String alpCusNumber) {
		this.alpCusNumber = alpCusNumber;
	}

	public String getAlpDeviceType() {
		return alpDeviceType;
	}

	public void setAlpDeviceType(String alpDeviceType) {
		this.alpDeviceType = alpDeviceType;
	}

	public String getAlpDeviceIdnty() {
		return alpDeviceIdnty;
	}

	public void setAlpDeviceIdnty(String alpDeviceIdnty) {
		this.alpDeviceIdnty = alpDeviceIdnty;
	}

	public String getAlpDeviceName() {
		return alpDeviceName;
	}

	public void setAlpDeviceName(String alpDeviceName) {
		this.alpDeviceName = alpDeviceName;
	}

	public String getAlpXPointIdnty() {
		return alpXPointIdnty;
	}

	public void setAlpXPointIdnty(String alpXPointIdnty) {
		this.alpXPointIdnty = alpXPointIdnty;
	}

	public String getAlpYPointIdnty() {
		return alpYPointIdnty;
	}

	public void setAlpYPointIdnty(String alpYPointIdnty) {
		this.alpYPointIdnty = alpYPointIdnty;
	}

	public int getAlpStatuIdnc() {
		return alpStatuIdnc;
	}

	public void setAlpStatuIdnc(int alpStatuIdnc) {
		this.alpStatuIdnc = alpStatuIdnc;
	}

	public Date getAlpCrteTime() {
		return alpCrteTime;
	}

	public void setAlpCrteTime(Date alpCrteTime) {
		this.alpCrteTime = alpCrteTime;
	}

	public String getAlpCrteUserId() {
		return alpCrteUserId;
	}

	public void setAlpCrteUserId(String alpCrteUserId) {
		this.alpCrteUserId = alpCrteUserId;
	}

	public Date getAlpUpdtTime() {
		return alpUpdtTime;
	}

	public void setAlpUpdtTime(Date alpUpdtTime) {
		this.alpUpdtTime = alpUpdtTime;
	}

	public String getAlpUpdtUserId() {
		return alpUpdtUserId;
	}

	public void setAlpUpdtUserId(String alpUpdtUserId) {
		this.alpUpdtUserId = alpUpdtUserId;
	}

	public String getAlpZPointIdnty() {
		return alpZPointIdnty;
	}

	public void setAlpZPointIdnty(String alpZPointIdnty) {
		this.alpZPointIdnty = alpZPointIdnty;
	}

	public String getAlpDeviceModel() {
		return alpDeviceModel;
	}

	public void setAlpDeviceModel(String alpDeviceModel) {
		this.alpDeviceModel = alpDeviceModel;
	}
}