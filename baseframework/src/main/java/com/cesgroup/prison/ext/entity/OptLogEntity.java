package com.cesgroup.prison.ext.entity;

import java.util.Date;

import javax.persistence.Entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.framework.commons.CesDateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class OptLogEntity extends StringIDEntity {
	private static final long serialVersionUID = 3554055708686285024L;
	private String userId;
	private Date time;
	private String model;
	private String action;
	private String opTarget;
	private String result;
	private String ip;
	private String url;

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getOpTarget() {
		return this.opTarget;
	}

	public void setOpTarget(String opTarget) {
		this.opTarget = opTarget;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("--------------------------------------------------\r\n");
		sb.append("时间:")
				.append(CesDateUtils.toString(this.time, "yyyy-MM-dd HH:mm:ss"))
				.append("\r\n");
		sb.append("用户：").append((this.userId == null) ? "未登录" : this.userId)
				.append("\r\n");
		sb.append("IP:").append(this.ip).append("\r\n");
		sb.append("模块：").append(this.model).append("\r\n");
		sb.append("URL地址：").append(this.url).append("\r\n");
		sb.append("操作：").append(this.action).append("\r\n");
		sb.append("内容：").append(this.opTarget).append("\r\n");
		sb.append("结果：").append(this.result).append("\r\n");
		sb.append("===================================================");

		return sb.toString();
	}
}