package com.cesgroup.prison.broadcastPlay.dto;

import com.cesgroup.frm.net.netty.bean.MsgBody;

/**
 * 停止播放音乐广播消息体
 * 
 * @author lincoln.cheng
 *
 */
public class StopPlayAudioMsgBody extends MsgBody {
	/**
	 * 广播播放记录ID
	 */
	private String rand;
	/**
	 * 广播平台回传过来的任务ID
	 */
	private String taskId;

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
}