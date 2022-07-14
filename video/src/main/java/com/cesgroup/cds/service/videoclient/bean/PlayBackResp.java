package com.cesgroup.cds.service.videoclient.bean;

/**
 * 视频回放-响应实体类
 * @author xie.yh
 */
public class PlayBackResp extends AbsClientResp {
	private String beginTime;// 回放开始时间
	private String endtime;// 回放结束时间

	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
}
