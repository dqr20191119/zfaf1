package com.cesgroup.cds.service.videoclient.bean;

/**
 * 视频录像-请求实体类
 * @author xie.yh
 */
public class VideoRecordReq extends AbsClientReq{
	private String orgCode;// 机构号
	private String savePath;// 保存路径
	private String fileName;// 文件名称

	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
