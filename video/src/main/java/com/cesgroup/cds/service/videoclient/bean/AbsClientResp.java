package com.cesgroup.cds.service.videoclient.bean;

/**
 * 视频操作的-响应实体抽象类
 * @author xie.yh
 */
public abstract class AbsClientResp {
	private Integer index;// 画面索引
	private Integer cameraId;// 摄像机编号
	private String 	cameraName = "";// 摄像机名称
	private boolean result = false;// 回放结果
	private String msg = "";// 说明


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
	public String getCameraName() {
		return cameraName;
	}
	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
