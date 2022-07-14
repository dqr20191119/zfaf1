package com.cesgroup.prison.httpclient.zfxx.dto;



public class ZfJhzsDto {
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
	 * 公函字号
	 */
	private String CGhzh;
	/**
	 * 提回单位
	 */
	private String CThdw;
	/**
	 * 提回期限
	 */
	private String CThqx;
	/**
	 * 提回日期
	 */
	private String DThrq;
	/**
	 * 终止日期
	 */
	private String  DZzrq;
	/**
	 * 经办干警
	 */
	private String CJbgjMc;
	/**
	 * 联系电话
	 */
	private String CLxdh;
	/**
	 * 提回原因
	 */
	private String CThyy;
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
	public String getCGhzh() {
		return CGhzh;
	}
	public void setCGhzh(String cGhzh) {
		CGhzh = cGhzh;
	}
	public String getCThdw() {
		return CThdw;
	}
	public void setCThdw(String cThdw) {
		CThdw = cThdw;
	}
	public String getCThqx() {
		return CThqx;
	}
	public void setCThqx(String cThqx) {
		CThqx = cThqx;
	}
	public String getDThrq() {
		return DThrq;
	}
	public void setDThrq(String dThrq) {
		DThrq = dThrq;
	}
	public String getDZzrq() {
		return DZzrq;
	}
	public void setDZzrq(String dZzrq) {
		DZzrq = dZzrq;
	}
	public String getCJbgjMc() {
		return CJbgjMc;
	}
	public void setCJbgjMc(String cJbgjMc) {
		CJbgjMc = cJbgjMc;
	}
	public String getCLxdh() {
		return CLxdh;
	}
	public void setCLxdh(String cLxdh) {
		CLxdh = cLxdh;
	}
	public String getCThyy() {
		return CThyy;
	}
	public void setCThyy(String cThyy) {
		CThyy = cThyy;
	}
	public String getDUrlTime() {
		return DUrlTime;
	}
	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}

	
	
}
