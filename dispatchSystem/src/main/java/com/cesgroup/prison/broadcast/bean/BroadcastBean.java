package com.cesgroup.prison.broadcast.bean;

import java.io.Serializable;

/**
 * 广播返回消息Bean
 * 
 * @author
 *
 */
public class BroadcastBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 广播记录编号-安防平台
	 */
	private String rand;
	/**
	 * 广播任务编号-广播平台
	 */
	private String taskId;
	/**
	 * 广播播放、停止指令返回结果（0：失败；1：成功）
	 */
	private String relust;
	/**
	 * 播放类型（开始广播、停止广播）
	 */
	private String playtype;

	public String getRand() {
		return rand;
	}
	public void setRand(String rand) {
		this.rand = rand;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getRelust() {
		return relust;
	}
	public void setRelust(String relust) {
		this.relust = relust;
	}
	public String getPlaytype() {
		return playtype;
	}
	public void setPlaytype(String playtype) {
		this.playtype = playtype;
	}
}
