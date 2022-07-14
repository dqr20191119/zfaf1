package com.cesgroup.prison.httpclient.zfxx.dto;

import java.util.List;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
 * Description: 罪犯  特管等级 实体类
 * @author monkey
 *
 * 2019年3月3日
 */
public class ZfTgDto   extends StringIDEntity{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 罪犯标识
	 */
	private String CId;
	/**
	 * 罪犯编号
	 */
	private String CZfbh;
	/**
	 * 分管等级
	 */
	private String CFgdj;
	/**
	 * 改造岗位
	 */
	private String CQzfg;
	/**
	 * 是否顽危重控
	 */
	private String CSfww;

	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
	private String DUrlTime;
	public String getCId() {
		return CId;
	}
	public void setCId(String cId) {
		CId = cId;
	}
	public String getCZfbh() {
		return CZfbh;
	}
	public void setCZfbh(String cZfbh) {
		CZfbh = cZfbh;
	}
	public String getCFgdj() {
		return CFgdj;
	}
	public void setCFgdj(String cFgdj) {
		CFgdj = cFgdj;
	}
	public String getCQzfg() {
		return CQzfg;
	}
	public void setCQzfg(String cQzfg) {
		CQzfg = cQzfg;
	}
	public String getCSfww() {
		return CSfww;
	}
	public void setCSfww(String cSfww) {
		CSfww = cSfww;
	}


	public String getDUrlTime() {
		return DUrlTime;
	}
	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}

	
	
}
