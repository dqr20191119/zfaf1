package com.cesgroup.cds.service.videoclient.bean;

/**
 * 视频操作的-请求实体抽象类
 * @author xie.yh
 */
public abstract class AbsClientReq {
	private Integer index;// 画面索引
	private Integer cameraId;// 摄像机编号

	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getCameraId() {
		return cameraId;
	}
	public void setCameraId(Integer cameraId) {
		this.cameraId = cameraId;
	}
}
