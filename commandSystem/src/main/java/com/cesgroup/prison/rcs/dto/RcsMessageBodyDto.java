package com.cesgroup.prison.rcs.dto;

import java.io.Serializable;

/**
 * 消息体DTO
 * @author lincoln
 *
 */
public class RcsMessageBodyDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 指令代码
	 * 1:启动预案
	 * 2:语音通知
	 * 3:发送短信
	 * 4:取消呼叫
	 * 5:修改位置信息
	 */
	private String cmd;
	/**
	 * 消息参数
	 */
	private String para;
	
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
}
