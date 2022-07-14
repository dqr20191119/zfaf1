package com.cesgroup.prison.aqfh.zfcjfh.entity;
/**
* @author lihong
* @date 创建时间：2020年6月4日 上午10:46:25
* 类说明:
*/

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
@Table(name = "CDS_AQFH_ZFCJFH")
@Entity
public class ZfcjfhEntity  extends StringIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String  cusNumber;
	private String  jqId;
	private String  name;
	private String  zfBh;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  csRq;
	/**
	 * 名族
	 */
	private String  mz;
	/**
	 * 籍贯
	 */
	private String  jg;
	private String  zm;
	private String  xz;
	private String  xq;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  xqQr;
	private String  xqZr;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  zxXq;
	private Date  zxXqZr;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date  sfRq;
	private String  sflb;
	/**
	 * 0.未复核1.已复核
	 */
	private String  zt;
	private String  shrId;
	private String  shRq;
	private String  shrName;
	public String getCusNumber() {
		return cusNumber;
	}
	public String getJqId() {
		return jqId;
	}
	public String getName() {
		return name;
	}
	public String getZfBh() {
		return zfBh;
	}
	public Date getCsRq() {
		return csRq;
	}
	public String getMz() {
		return mz;
	}
	public String getJg() {
		return jg;
	}
	public String getZm() {
		return zm;
	}
	public String getXz() {
		return xz;
	}
	public String getXq() {
		return xq;
	}
	public Date getXqQr() {
		return xqQr;
	}
	public String getXqZr() {
		return xqZr;
	}
	public Date getZxXq() {
		return zxXq;
	}
	public Date getZxXqZr() {
		return zxXqZr;
	}
	public Date getSfRq() {
		return sfRq;
	}
	public String getSflb() {
		return sflb;
	}
	public String getZt() {
		return zt;
	}
	public String getShrId() {
		return shrId;
	}
	public String getShRq() {
		return shRq;
	}
	public String getShrName() {
		return shrName;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public void setJqId(String jqId) {
		this.jqId = jqId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setZfBh(String zfBh) {
		this.zfBh = zfBh;
	}
	public void setCsRq(Date csRq) {
		this.csRq = csRq;
	}
	public void setMz(String mz) {
		this.mz = mz;
	}
	public void setJg(String jg) {
		this.jg = jg;
	}
	public void setZm(String zm) {
		this.zm = zm;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public void setXqQr(Date xqQr) {
		this.xqQr = xqQr;
	}
	public void setXqZr(String xqZr) {
		this.xqZr = xqZr;
	}
	public void setZxXq(Date zxXq) {
		this.zxXq = zxXq;
	}
	public void setZxXqZr(Date zxXqZr) {
		this.zxXqZr = zxXqZr;
	}
	public void setSfRq(Date sfRq) {
		this.sfRq = sfRq;
	}
	public void setSflb(String sflb) {
		this.sflb = sflb;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public void setShrId(String shrId) {
		this.shrId = shrId;
	}
	public void setShRq(String shRq) {
		this.shRq = shRq;
	}
	public void setShrName(String shrName) {
		this.shrName = shrName;
	}
	
	
	
	
}
