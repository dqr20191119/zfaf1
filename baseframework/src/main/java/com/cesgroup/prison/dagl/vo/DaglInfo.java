
package com.cesgroup.prison.dagl.vo;

import com.cesgroup.framework.mybatis.entity.StringIDEntity;
public class DaglInfo  extends  StringIDEntity{
	private static final long serialVersionUID = 1L;

	/**
	 * 档号
	 */
	private String dh ;
	/**
	 * 保管期限
	 */
	private String bgqx;
	/**
	 * 题名
	 */
	private String tm;
	/**
	 * 责任者
	 */
	private String zrz;
	
	/**
	 * 年度
	 */
	private String nd;
	
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件大小
	 */
	private String fileSize;
	private String ownerId;
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	public String getBgqx() {
		return bgqx;
	}
	public void setBgqx(String bgqx) {
		this.bgqx = bgqx;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public String getZrz() {
		return zrz;
	}
	public void setZrz(String zrz) {
		this.zrz = zrz;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
}
