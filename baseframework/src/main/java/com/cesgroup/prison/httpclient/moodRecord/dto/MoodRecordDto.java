package com.cesgroup.prison.httpclient.moodRecord.dto;

import java.io.Serializable;

public class MoodRecordDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;//记录id
	private String name;//罪犯姓名
	private String bh;//罪犯编号
	private String photo;//罪犯照片 ，
	private String syouname;//单位名称
	private String js;//监舍名称
	private String spstatus;//审批状态(0：待审批   1:审批通过   2：审批不通过）
	private String mood;//心情
	private String status;//心情标志（H/N/S）开心快乐/正常平和/伤心低落
	private String cretime;//登记时间" ，
	private String username;//审批人
	private String opinion;//审批意见
	private String sptime;//审批时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getSyouname() {
		return syouname;
	}
	public void setSyouname(String syouname) {
		this.syouname = syouname;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	public String getSpstatus() {
		return spstatus;
	}
	public void setSpstatus(String spstatus) {
		this.spstatus = spstatus;
	}
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCretime() {
		return cretime;
	}
	public void setCretime(String cretime) {
		this.cretime = cretime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getSptime() {
		return sptime;
	}
	public void setSptime(String sptime) {
		this.sptime = sptime;
	}
	
}
