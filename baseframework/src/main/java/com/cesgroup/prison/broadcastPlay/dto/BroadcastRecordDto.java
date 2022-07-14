package com.cesgroup.prison.broadcastPlay.dto;

import java.io.Serializable;

public class BroadcastRecordDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 广播播放记录主键
	 */
	private String id;
	/**
	 * 监狱编号
	 */
	private String cusNumber;
	/**
	 * 广播设备主键ID
	 */
	private String broadcastId;
	/**
	 * 播放类型（1：媒体库；2：文字转语音）
	 */
    private String contentType;
    /**
     * 播放内容
     */
    private String content;
	/**
	 * 播放类型为1时，根据播放内容对应的曲目ID，查找曲目名称，并以英文逗号分隔多个曲目名称，存入contentMappingName
	 */
	private String contentMappingName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCusNumber() {
		return cusNumber;
	}

	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}

	public String getBroadcastId() {
		return broadcastId;
	}

	public void setBroadcastId(String broadcastId) {
		this.broadcastId = broadcastId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentMappingName() {
		return contentMappingName;
	}

	public void setContentMappingName(String contentMappingName) {
		this.contentMappingName = contentMappingName;
	}
}