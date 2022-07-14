package com.cesgroup.prison.zfxx.zfshgx.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * Description: 罪犯老病残 实体类
 * @author monkey
 *
 * 2019年3月11日
 */

@Entity
@Table(name = "ZFINFO.T_ZF_SHGX")
public class ZfShgx  extends StringIDEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 罪犯标识
	 */
	private String cId;
	/**
	 * 罪犯编号
	 */
	private String cZfbh;
	
	/**
	 * 关系人姓名
	 */
	private String cXm;
	/**
	 * 性别
	 */
	private String cXb;
	/**
	 * 出生日期
	 */
	private String dCsrq;
	/**
	 * 证件类别
	 */
	private String cZjlb;
	
	/**
	 * 证件号码
	 */
	private String cZjhm;
	/**
	 * 职业
	 */
	private String cZy;
	/**
	 * 政治面貌
	 */
	private String cZzmm;
	/**
	 * 电话
	 */
	private String cLxdh;
	/**
	 * 主联系人
	 */
	private String nZlxr;
	
	/**
	 * 家庭住址
	 */
	private String cJtzz;

	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dUrlTime;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dCjrq;

    /**
     * 创建批次
     */
    private String cCjpc;
    
	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getcZfbh() {
		return cZfbh;
	}

	public void setcZfbh(String cZfbh) {
		this.cZfbh = cZfbh;
	}

	public String getcXm() {
		return cXm;
	}

	public void setcXm(String cXm) {
		this.cXm = cXm;
	}

	public String getcXb() {
		return cXb;
	}

	public void setcXb(String cXb) {
		this.cXb = cXb;
	}

	public String getdCsrq() {
		return dCsrq;
	}

	public void setdCsrq(String dCsrq) {
		this.dCsrq = dCsrq;
	}

	public String getcZjlb() {
		return cZjlb;
	}

	public void setcZjlb(String cZjlb) {
		this.cZjlb = cZjlb;
	}

	public String getcZjhm() {
		return cZjhm;
	}

	public void setcZjhm(String cZjhm) {
		this.cZjhm = cZjhm;
	}

	public String getcZy() {
		return cZy;
	}

	public void setcZy(String cZy) {
		this.cZy = cZy;
	}

	public String getcLxdh() {
		return cLxdh;
	}

	public void setcLxdh(String cLxdh) {
		this.cLxdh = cLxdh;
	}

	public String getnZlxr() {
		return nZlxr;
	}

	public void setnZlxr(String nZlxr) {
		this.nZlxr = nZlxr;
	}

	public String getcJtzz() {
		return cJtzz;
	}

	public void setcJtzz(String cJtzz) {
		this.cJtzz = cJtzz;
	}

	public Date getdUrlTime() {
		return dUrlTime;
	}

	public void setdUrlTime(Date dUrlTime) {
		this.dUrlTime = dUrlTime;
	}

	public String getcZzmm() {
		return cZzmm;
	}

	public void setcZzmm(String cZzmm) {
		this.cZzmm = cZzmm;
	}

	public Date getdCjrq() {
		return dCjrq;
	}

	public void setdCjrq(Date dCjrq) {
		this.dCjrq = dCjrq;
	}

	public String getcCjpc() {
		return cCjpc;
	}

	public void setcCjpc(String cCjpc) {
		this.cCjpc = cCjpc;
	}

	@Override
	public String toString() {
		return "ZfShgx [cId=" + cId + ", cZfbh=" + cZfbh + ", cXm=" + cXm + ", cXb=" + cXb + ", dCsrq=" + dCsrq
				+ ", cZjlb=" + cZjlb + ", cZjhm=" + cZjhm + ", cZy=" + cZy + ", cZzmm=" + cZzmm + ", cLxdh=" + cLxdh
				+ ", nZlxr=" + nZlxr + ", cJtzz=" + cJtzz + ", dUrlTime=" + dUrlTime + ", dCjrq=" + dCjrq + ", cCjpc="
				+ cCjpc + "]";
	}
    
}
