package com.cesgroup.cds.service.videoclient.bean;

/**
 * 视频文件播放-请求实体类
 * @author xie.yh
 */
public class PlayFileReq extends AbsClientReq{
	private Integer playType;	// 文件播放类型：8-海康、20-Eddns
	private String filePath;	// 播放的文件路径
	private String fileName;	// 播放的文件名称
	private Integer isExist;	// 播放的文件是否存在：0不存在、1存在

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
	public Integer getIsExist() {
		return isExist;
	}
	public void setIsExist(Integer isExist) {
		this.isExist = isExist;
	}
}
