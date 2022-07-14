package com.cesgroup.prison.common.bean;

import com.cesgroup.prison.common.bean.user.UserCodeUtil;

/**
 * cesgroup
 * 统一响应格式
 * @author lihh
 *
 */
public abstract class ARespBean {
	private boolean result = false;		// 响应结果
	private String respCode = "";		// 响应码
	private String respDesc = "";		// 响应描述

	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}

	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
		//this.respDesc = UserCodeUtil.getDesc(respCode, "");
		if(respCode.equals(UserCodeUtil.CODE_0000))
			this.respDesc ="操作成功";
		else
			this.respDesc ="操作异常，工程师需要debug";
		
		this.result = UserCodeUtil.CODE_0000.equals(this.respCode);
	}

	public String getRespDesc() {
		return respDesc;
	}
	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
}
