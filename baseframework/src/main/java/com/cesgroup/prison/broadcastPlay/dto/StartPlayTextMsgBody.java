package com.cesgroup.prison.broadcastPlay.dto;

import com.cesgroup.frm.net.netty.bean.MsgBody;

/**
 * 开始播放文字转语音广播消息体
 * 
 * @author lincoln.cheng
 *
 */
public class StartPlayTextMsgBody extends MsgBody {
	/**
	 * 文字转语音TTS编号
	 */
	private String tts_id;
	/**
	 * 语言类型(Mandarin:普通话;Cantonese:粤语;English:英语)
	 */
    private String voice;
    /**
     * 合成音频的语音类型（man:男声;woman:女声）
     */
    private String voice_name;
    /**
     * 合成音频对应的语速 可选范围 -50~300，默认38
     */
    private String speed;
    /**
     * 合成音频的音量 可选范围 -100~100，默认100
     */
    private String volume;
    /**
     * 文字转语音内容
     */
    private String text;
    /**
     * 播放模式(1:顺序播放; 2:循环播放; 3:随机播放)默认1
     */
    private String play_mode;
    /**
     * 播放媒体音量 取值范围为0-100,0表静音。默认80
     */
    private String vol;
    /**
     * 设备在广播平台的ID
     */
    private String dial_nos;
    
	public String getTts_id() {
		return tts_id;
	}
	public void setTts_id(String tts_id) {
		this.tts_id = tts_id;
	}
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public String getVoice_name() {
		return voice_name;
	}
	public void setVoice_name(String voice_name) {
		this.voice_name = voice_name;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public String getDial_nos() {
		return dial_nos;
	}
	public void setDial_nos(String dial_nos) {
		this.dial_nos = dial_nos;
	}
}