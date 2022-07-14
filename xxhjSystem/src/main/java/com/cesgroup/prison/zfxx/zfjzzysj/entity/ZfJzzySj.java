package com.cesgroup.prison.zfxx.zfjzzysj.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 罪犯收监 狱外就诊/住院 实体类
 * 
 * @author monkey
 *
 *         2019年3月5日
 */

@Entity
@Table(name = "ZFINFO.T_ZF_JZZY_SJ")
public class ZfJzzySj extends StringIDEntity {
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
	 * 就医医院
	 */
	private String cYymc;
	/**
	 * 就诊/住院类别
	 */
	private String cJzlb;
	/**
	 * 就诊/住院时间
	 */
	private String cJzzysj;
	/**
	 * 结束日期
	 */
	private String dJsrq;
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

	public String getcYymc() {
		return cYymc;
	}

	public void setcYymc(String cYymc) {
		this.cYymc = cYymc;
	}

	public String getcJzlb() {
		return cJzlb;
	}

	public void setcJzlb(String cJzlb) {
		this.cJzlb = cJzlb;
	}

	public String getcJzzysj() {
		return cJzzysj;
	}

	public void setcJzzysj(String cJzzysj) {
		this.cJzzysj = cJzzysj;
	}

	public String getdJsrq() {
		return dJsrq;
	}

	public void setdJsrq(String dJsrq) {
		this.dJsrq = dJsrq;
	}

	public Date getdUrlTime() {
		return dUrlTime;
	}

	public void setdUrlTime(Date dUrlTime) {
		this.dUrlTime = dUrlTime;
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
		return "ZfJzzySj [cId=" + cId + ", cZfbh=" + cZfbh + ", cYymc=" + cYymc + ", cJzlb=" + cJzlb + ", cJzzysj="
				+ cJzzysj + ", dJsrq=" + dJsrq + ", dUrlTime=" + dUrlTime + ", dCjrq=" + dCjrq + ", cCjpc=" + cCjpc
				+ "]";
	}

}
