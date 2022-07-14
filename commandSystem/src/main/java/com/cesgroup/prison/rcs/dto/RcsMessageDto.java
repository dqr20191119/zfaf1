package com.cesgroup.prison.rcs.dto;

import java.io.Serializable;

/**
 * 消息参数DTO
 * @author lincoln
 *
 */
public class RcsMessageDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 终端标识
	 */
	private String terFlag;
	/**
	 * 用户号码
	 */
	private String[] userNum;
	/**
	 * 消息内容
	 */
	private String content;
	
	public String getTerFlag() {
		return terFlag;
	}
	public void setTerFlag(String terFlag) {
		this.terFlag = terFlag;
	}
	public String[] getUserNum() {
		return userNum;
	}
	public void setUserNum(String[] userNum) {
		this.userNum = userNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
