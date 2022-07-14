package com.cesgroup.prison.bfcf.dto;

import com.cesgroup.frm.net.netty.bean.MsgBody;

public class StartBfCfTextMsgBody extends MsgBody {
	private String action ;//1.布防 2. 撤防  3.一键布防 4.一键撤防
	
	private String fqId;//防区编号
	
	private String fqName;//防区名称

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFqId() {
		return fqId;
	}

	public void setFqId(String fqId) {
		this.fqId = fqId;
	}

	public String getFqName() {
		return fqName;
	}

	public void setFqName(String fqName) {
		this.fqName = fqName;
	}
	
}
