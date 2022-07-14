package com.cesgroup.prison.callNamesAl.bean;

import java.io.Serializable;
import java.util.List;

public class CallNamesPrisonerAlBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int age;
	private String appKey;
	private double attractive;
	private List<Integer> axis;
	private int expression;
	private double expressionConf;
	private String faceId;
	private int glasses;
	private String groupId;
	private int hat;
	private double illumination;
	private String imageId;
	private String imageMd5;
	private List<List<Double>> landmarks;
	private boolean male;
	private int mask;
	private double pitch;
	private int quality;
	private double roll;
	private int sharpness;
	private String userId;
	private boolean validateForRegister;
	private double yaw;

	public CallNamesPrisonerAlBean() {
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAttractive(double attractive) {
		this.attractive = attractive;
	}

	public double getAttractive() {
		return attractive;
	}

	public void setAxis(List<Integer> axis) {
		this.axis = axis;
	}

	public List<Integer> getAxis() {
		return axis;
	}

	public void setExpression(int expression) {
		this.expression = expression;
	}

	public int getExpression() {
		return expression;
	}

	public void setExpressionConf(double expressionConf) {
		this.expressionConf = expressionConf;
	}

	public double getExpressionConf() {
		return expressionConf;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public String getFaceId() {
		return faceId;
	}

	public void setGlasses(int glasses) {
		this.glasses = glasses;
	}

	public int getGlasses() {
		return glasses;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setHat(int hat) {
		this.hat = hat;
	}

	public int getHat() {
		return hat;
	}

	public void setIllumination(double illumination) {
		this.illumination = illumination;
	}

	public double getIllumination() {
		return illumination;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageMd5(String imageMd5) {
		this.imageMd5 = imageMd5;
	}

	public String getImageMd5() {
		return imageMd5;
	}

	public void setLandmarks(List<List<Double>> landmarks) {
		this.landmarks = landmarks;
	}

	public List<List<Double>> getLandmarks() {
		return landmarks;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public boolean getMale() {
		return male;
	}

	public void setMask(int mask) {
		this.mask = mask;
	}

	public int getMask() {
		return mask;
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
	}

	public double getPitch() {
		return pitch;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getQuality() {
		return quality;
	}

	public void setRoll(double roll) {
		this.roll = roll;
	}

	public double getRoll() {
		return roll;
	}

	public void setSharpness(int sharpness) {
		this.sharpness = sharpness;
	}

	public int getSharpness() {
		return sharpness;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setValidateForRegister(boolean validateForRegister) {
		this.validateForRegister = validateForRegister;
	}

	public boolean getValidateForRegister() {
		return validateForRegister;
	}

	public void setYaw(double yaw) {
		this.yaw = yaw;
	}

	public double getYaw() {
		return yaw;
	}
}
