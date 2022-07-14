package com.cesgroup.prison.broadcastPlay.dto;

import com.cesgroup.frm.net.netty.bean.MsgBody;

/**
 * 停止播放文字转语音广播消息体
 * 
 * @author lincoln.cheng
 *
 */
public class StopPlayTextMsgBody extends MsgBody {
	/**
	 * 广播记录编号
	 */
	private String rand;
	/**
	 * 文字转语音TTD编号
	 */
	private String tts_id;

	public String getRand() {
		return rand;
	}

	public void setRand(String rand) {
		this.rand = rand;
	}

	public String getTts_id() {
		return tts_id;
	}

	public void setTts_id(String tts_id) {
		this.tts_id = tts_id;
	}
}