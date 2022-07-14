package com.cesgroup.prison.rcs.dto;

import java.io.Serializable;

/**
 * Description: 应急预案消息参数DTO
 * 
 * @author lincoln.cheng
 * 2019年2月17日
 *
 */
public class YjyaMessageDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 终端标识
	 */
	private String terFlag;
	/**
	 * 预案名称
	 */
	private String plan;
	/**
	 * 梯队名称
	 */
	private String echelon;
	/**
	 * 增补人员
	 */
	private String supplement;
	/**
	 * 通知语音  被叫接听后需要播放的语音文本（当该参数为空时，使用预案中预先设置的语音文本）。注：播放前使用后续参数替换文本中相应的标志处。
	 */
	private String voiceTxt;
    /**
     * 姓名    替换语音文本中（姓名）位置处的文字。
     */
    private String name;
    /**
     * 日期    替换语音文本中（日期）位置处的文字。
     */
    private String date;
    /**
     * 时间    替换语音文本中（时间）位置处的文字。
     */
    private String time;
    /**
     * 区域    替换语音文本中（区域）位置处的文字。
     */
    private String area;
    /**
     * 地点    替换语音文本中（地点）位置处的文字。
     */
    private String location;
    /**
     * 状态
     */
    private String sta;
    
	public String getTerFlag() {
		return terFlag;
	}
	public void setTerFlag(String terFlag) {
		this.terFlag = terFlag;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getEchelon() {
		return echelon;
	}
	public void setEchelon(String echelon) {
		this.echelon = echelon;
	}
	public String getSupplement() {
		return supplement;
	}
	public void setSupplement(String supplement) {
		this.supplement = supplement;
	}
	public String getVoiceTxt() {
		return voiceTxt;
	}
	public void setVoiceTxt(String voiceTxt) {
		this.voiceTxt = voiceTxt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSta() {
		return sta;
	}
	public void setSta(String sta) {
		this.sta = sta;
	}
}
