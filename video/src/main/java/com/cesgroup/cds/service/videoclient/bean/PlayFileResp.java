package com.cesgroup.cds.service.videoclient.bean;

/**
 * 视频文件播放-响应实体类
 * @author xie.yh
 */
public class PlayFileResp extends AbsClientResp{
	private Integer playType;
	private String filePath;
	private String fileName;

	public Integer getPlayType() {
		return playType;
	}
	public void setPlayType(Integer playType) {
		this.playType = playType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
