package com.cesgroup.prison.callNamesAl.bean;

import java.io.Serializable;
import java.util.Map;

public class CallNamesJsDataBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String jsh;// 监舍号
	private String jyh;// 监狱号
	private String lch;// 楼层号
	private String peopleTotal; // 罪犯总数
	private String arrivedTotal; // 点到罪犯数
	private String state; // 状态 0 未开始 1 点名中 2点名结束
	private Map<String, String> zfbhs;// 监舍内罪犯编号 key 罪犯编号-----------value 点名状态 ： 0未点到 1 点到
	private Map<String, Object> camera;
	private String matserId;// 点名主表id

	public CallNamesJsDataBean() {
	}

	public String getMatserId() {
		return matserId;
	}

	public void setMatserId(String matserId) {
		this.matserId = matserId;
	}

	public Map<String, String> getZfbhs() {
		return zfbhs;
	}

	public void setZfbhs(Map<String, String> zfbhs) {
		this.zfbhs = zfbhs;
	}

	public void setArrivedTotal(String arrivedTotal) {
		this.arrivedTotal = arrivedTotal;
	}

	public String getArrivedTotal() {
		return arrivedTotal;
	}

	public void setCamera(Map<String, Object> camera) {
		this.camera = camera;
	}

	public Map<String, Object> getCamera() {
		return camera;
	}

	public void setJsh(String jsh) {
		this.jsh = jsh;
	}

	public String getJsh() {
		return jsh;
	}

	public void setJyh(String jyh) {
		this.jyh = jyh;
	}

	public String getJyh() {
		return jyh;
	}

	public void setLch(String lch) {
		this.lch = lch;
	}

	public String getLch() {
		return lch;
	}

	public void setPeopleTotal(String peopleTotal) {
		this.peopleTotal = peopleTotal;
	}

	public String getPeopleTotal() {
		return peopleTotal;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

}
