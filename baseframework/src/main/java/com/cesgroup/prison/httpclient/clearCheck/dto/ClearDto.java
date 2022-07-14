package com.cesgroup.prison.httpclient.clearCheck.dto;

import java.io.Serializable;


public class ClearDto  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cretime;
	private String id;
	private String js;
	private String policename;
	private String status;
	private String syouname;
	private String dUrlTime;
	public String getdUrlTime() {
		return dUrlTime;
	}
	public void setdUrlTime(String dUrlTime) {
		this.dUrlTime = dUrlTime;
	}
	public String getCretime() {
		return cretime;
	}
	public void setCretime(String cretime) {
		this.cretime = cretime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJs() {
		return js;
	}
	public void setJs(String js) {
		this.js = js;
	}
	public String getPolicename() {
		return policename;
	}
	public void setPolicename(String policename) {
		this.policename = policename;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSyouname() {
		return syouname;
	}
	public void setSyouname(String syouname) {
		this.syouname = syouname;
	}
}
