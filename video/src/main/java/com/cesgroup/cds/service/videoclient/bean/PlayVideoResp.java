package com.cesgroup.cds.service.videoclient.bean;

/**
 * 视频播放-响应实体类
 * @author xie.yh
 */
public class PlayVideoResp extends AbsClientResp{
	private Integer streamType = 1;// 码流类型：0主码流、1辅码流（默认）

	public Integer getStreamType() {
		return streamType;
	}

	public void setStreamType(Integer streamType) {
		this.streamType = streamType;
	}
}
