/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.ces.prison.portal.entity</p>
 * <p>文件名:SjzljcEntity.java</p>
 * <p>类更新历史信息</p>
 * @todo Administrator(徐冰  E-mail:xu.bing@cesgroup.com.cn) 
 * 创建于 2016-3-22 下午1:30:04
 */
package com.cesgroup.prison.wwjg.fxgkgl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name ="T_AQFX_FXGK")
public class FxgkglEntity extends  StringIDEntity {

	private String code;
	private String name;
	private String bz;
	@Column(updatable = false)
	private String cCjr ;//创建人
	@Column(updatable = false)
	private String cCjrId;//创建人Id
	@Column(updatable = false)
	private Date cCjRq ;//创建日期
	private String cGxrId;		// 更新人ID
	private String cGxr;			// 更新人
	private Date cGxRq;			// 更新日期
	private String scflg;
	
	private String sjfwId;
	private String sjfwName;
	private String wwjgId;
	private String wwjgName;
	
	private String fxdId;
	private String fxdName;
	private String sxsm;
	
	private String fxdjId;
	private String fxdjName;
	private String kfz;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getcCjr() {
		return cCjr;
	}
	public void setcCjr(String cCjr) {
		this.cCjr = cCjr;
	}
	public String getcCjrId() {
		return cCjrId;
	}
	public void setcCjrId(String cCjrId) {
		this.cCjrId = cCjrId;
	}
	public Date getcCjRq() {
		return cCjRq;
	}
	public void setcCjRq(Date cCjRq) {
		this.cCjRq = cCjRq;
	}
	public String getcGxrId() {
		return cGxrId;
	}
	public void setcGxrId(String cGxrId) {
		this.cGxrId = cGxrId;
	}
	public String getcGxr() {
		return cGxr;
	}
	public void setcGxr(String cGxr) {
		this.cGxr = cGxr;
	}
	public Date getcGxRq() {
		return cGxRq;
	}
	public void setcGxRq(Date cGxRq) {
		this.cGxRq = cGxRq;
	}
	public String getScflg() {
		return scflg;
	}
	public void setScflg(String scflg) {
		this.scflg = scflg;
	}
	public String getSjfwId() {
		return sjfwId;
	}
	public void setSjfwId(String sjfwId) {
		this.sjfwId = sjfwId;
	}
	public String getSjfwName() {
		return sjfwName;
	}
	public void setSjfwName(String sjfwName) {
		this.sjfwName = sjfwName;
	}
	public String getWwjgId() {
		return wwjgId;
	}
	public void setWwjgId(String wwjgId) {
		this.wwjgId = wwjgId;
	}
	public String getWwjgName() {
		return wwjgName;
	}
	public void setWwjgName(String wwjgName) {
		this.wwjgName = wwjgName;
	}
	public String getFxdId() {
		return fxdId;
	}
	public void setFxdId(String fxdId) {
		this.fxdId = fxdId;
	}
	public String getFxdName() {
		return fxdName;
	}
	public void setFxdName(String fxdName) {
		this.fxdName = fxdName;
	}
	public String getSxsm() {
		return sxsm;
	}
	public void setSxsm(String sxsm) {
		this.sxsm = sxsm;
	}
	public String getFxdjId() {
		return fxdjId;
	}
	public void setFxdjId(String fxdjId) {
		this.fxdjId = fxdjId;
	}
	public String getFxdjName() {
		return fxdjName;
	}
	public void setFxdjName(String fxdjName) {
		this.fxdjName = fxdjName;
	}
	public String getKfz() {
		return kfz;
	}
	public void setKfz(String kfz) {
		this.kfz = kfz;
	}
	 
	 
	
	
	
}