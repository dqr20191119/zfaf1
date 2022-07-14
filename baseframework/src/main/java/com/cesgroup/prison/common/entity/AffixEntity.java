package com.cesgroup.prison.common.entity;

import java.util.Date;

import javax.persistence.Lob;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "t_c_affix", schema = "portal")
public class AffixEntity extends StringIDEntity {
	
	private String fileName;				// 文件名
	private String fileExts;				// 文件名后缀
	private String fileSize;				// 文件大小
	private String filePath;				// 文件路径	
	@Lob
	private byte[] image;					// 文件内容
	private String fileType;				// 文件类型
	private String ywId;					// 业务id
	private Date scrq;						// 上传日期
	private String fjfl;					// 文件分类
	private String scrId;					// 上传人id
	private String linkUrl;					// 网络地址
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileExts() {
		return fileExts;
	}
	public void setFileExts(String fileExts) {
		this.fileExts = fileExts;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getYwId() {
		return ywId;
	}
	public void setYwId(String ywId) {
		this.ywId = ywId;
	}
	public Date getScrq() {
		return scrq;
	}
	public void setScrq(Date scrq) {
		this.scrq = scrq;
	}
	public String getFjfl() {
		return fjfl;
	}
	public void setFjfl(String fjfl) {
		this.fjfl = fjfl;
	}
	public String getScrId() {
		return scrId;
	}
	public void setScrId(String scrId) {
		this.scrId = scrId;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
}
