package com.cesgroup.prison.broadcastPlay.dto;

import com.cesgroup.frm.net.netty.bean.MsgBody;

/**
 * 开始播放音乐广播消息体
 * 
 * @author lincoln.cheng
 *
 */
public class StartPlayAudioMsgBody extends MsgBody {
	/**
	 * 32位16进制随机数
	 */
	private String rand;
	/**
	 * 音频文件编号
	 */
    private String medium_ids;
    /**
     * 播放模式
     */
    private String play_mode;
    /**
     * 音量
     */
    private String vol;
    /**
     * 播放音乐时长 取值单位为秒，0表为无效值，其他大于数字表播放的秒数。
     * -1表示永久播放，当设置为-1时，如果选的播放模式不是循环播放的话这时媒体播放完则停止。
     */
    private String duration;
    /**
     * 广播设备号
     */
    private String dial_nos;
    /**
     * 循环播放次数
     */
    private String cyc_count;
    
	public String getRand() {
		return rand;
	}
	public void setRand(String rand) {
		this.rand = rand;
	}
	public String getMedium_ids() {
		return medium_ids;
	}
	public void setMedium_ids(String medium_ids) {
		this.medium_ids = medium_ids;
	}
	public String getPlay_mode() {
		return play_mode;
	}
	public void setPlay_mode(String play_mode) {
		this.play_mode = play_mode;
	}
	public String getVol() {
		return vol;
	}
	public void setVol(String vol) {
		this.vol = vol;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDial_nos() {
		return dial_nos;
	}
	public void setDial_nos(String dial_nos) {
		this.dial_nos = dial_nos;
	}
	public String getCyc_count() {
		return cyc_count;
	}
	public void setCyc_count(String cyc_count) {
		this.cyc_count = cyc_count;
	}
}