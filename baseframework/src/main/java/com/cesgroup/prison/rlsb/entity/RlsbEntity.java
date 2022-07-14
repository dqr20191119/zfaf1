package com.cesgroup.prison.rlsb.entity;

import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "t_rlsb_hmd")
public class RlsbEntity extends StringIDEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String st; //事件时间
	private String srcIndexCode; //源CODE
	private String srcType; //源 类型 1：设备、 2：服务
	private String eventType; //事件类 型 131615
	private String channelIndexCode; //通道 INDEXCODE
	private String channelName; //通道名 称
	private String channelNo; //通道号
	private String locationIndexCode; //所属区域 INDEXCODE
	private String locationName; //所属区 域名称
	private String capSmallpicUrl;  //抓拍小图
	private String capBkgpicUrl; //抓 拍 背 景图
	private String capSmallpicUrlId;//抓拍小图ID
	private String capBkgpicUrlId; //抓 拍 背 景图ID
	private String relationId;
	private String capTime; //小图与背景图抓拍时间
	private String blacklistId; //名单库ID
	private String namelistId; //名单 ID
	private String facepicUrl; //人 脸 图 片地址
	private String facepicUrlId; //人 脸 图 片地址ID
	private String similarity; //相似度
	private String blacklistName; //名 单 库 名称
	private String namelistName; //罪犯名称
	private String certificateType; //证件类 型 1:居民身份证 2:士官证 3:学生证 4:驾驶证 5:护照 6:其他 默认为 1
	private String certificateNo; //证件号
	private String cardNo; //卡号
	private String gender; //性别  0男 1-女 2-其他
	private String nation; //民族
	private String createTime; //名单创建时间
	private String rectHeight; //人 脸 框 坐标 height
	private String rectWidth; //人 脸 框 坐标 WIDTH
	private String rectX; //人 脸 框 坐标 x
	private String rectY; //人 脸 框 坐标 y
	private String cusNumber;
	private String eventUuid;
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getSrcIndexCode() {
		return srcIndexCode;
	}
	public void setSrcIndexCode(String srcIndexCode) {
		this.srcIndexCode = srcIndexCode;
	}
	public String getSrcType() {
		return srcType;
	}
	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getChannelIndexCode() {
		return channelIndexCode;
	}
	public void setChannelIndexCode(String channelIndexCode) {
		this.channelIndexCode = channelIndexCode;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}
	public String getLocationIndexCode() {
		return locationIndexCode;
	}
	public void setLocationIndexCode(String locationIndexCode) {
		this.locationIndexCode = locationIndexCode;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getCapSmallpicUrl() {
		return capSmallpicUrl;
	}
	public void setCapSmallpicUrl(String capSmallpicUrl) {
		this.capSmallpicUrl = capSmallpicUrl;
	}
	public String getCapBkgpicUrl() {
		return capBkgpicUrl;
	}
	public void setCapBkgpicUrl(String capBkgpicUrl) {
		this.capBkgpicUrl = capBkgpicUrl;
	}
	public String getCapSmallpicUrlId() {
		return capSmallpicUrlId;
	}
	public void setCapSmallpicUrlId(String capSmallpicUrlId) {
		this.capSmallpicUrlId = capSmallpicUrlId;
	}
	public String getCapBkgpicUrlId() {
		return capBkgpicUrlId;
	}
	public void setCapBkgpicUrlId(String capBkgpicUrlId) {
		this.capBkgpicUrlId = capBkgpicUrlId;
	}
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public String getCapTime() {
		return capTime;
	}
	public void setCapTime(String capTime) {
		this.capTime = capTime;
	}
	public String getBlacklistId() {
		return blacklistId;
	}
	public void setBlacklistId(String blacklistId) {
		this.blacklistId = blacklistId;
	}
	public String getNamelistId() {
		return namelistId;
	}
	public void setNamelistId(String namelistId) {
		this.namelistId = namelistId;
	}
	public String getFacepicUrl() {
		return facepicUrl;
	}
	public void setFacepicUrl(String facepicUrl) {
		this.facepicUrl = facepicUrl;
	}
	public String getFacepicUrlId() {
		return facepicUrlId;
	}
	public void setFacepicUrlId(String facepicUrlId) {
		this.facepicUrlId = facepicUrlId;
	}
	public String getSimilarity() {
		return similarity;
	}
	public void setSimilarity(String similarity) {
		this.similarity = similarity;
	}
	public String getBlacklistName() {
		return blacklistName;
	}
	public void setBlacklistName(String blacklistName) {
		this.blacklistName = blacklistName;
	}
	public String getNamelistName() {
		return namelistName;
	}
	public void setNamelistName(String namelistName) {
		this.namelistName = namelistName;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRectHeight() {
		return rectHeight;
	}
	public void setRectHeight(String rectHeight) {
		this.rectHeight = rectHeight;
	}
	public String getRectWidth() {
		return rectWidth;
	}
	public void setRectWidth(String rectWidth) {
		this.rectWidth = rectWidth;
	}
	public String getRectX() {
		return rectX;
	}
	public void setRectX(String rectX) {
		this.rectX = rectX;
	}
	public String getRectY() {
		return rectY;
	}
	public void setRectY(String rectY) {
		this.rectY = rectY;
	}
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public String getEventUuid() {
		return eventUuid;
	}
	public void setEventUuid(String eventUuid) {
		this.eventUuid = eventUuid;
	}
	
	
}
