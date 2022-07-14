package com.cesgroup.prison.zfxx.zfxfbd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "ZFINFO.T_ZF_XFBD_JX")
public class ZfXfbdJx extends StringIDEntity {

    /**
	 * 
	 */
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
     * 判裁日期
     */
    private String dPcrq;

    /**
     * 变动类别
     */
    private String cBdlb;

    /**
     * 变动幅度
     */
    private String cBdfd;

    /**
     * 变动原因
     */
    private String cBdyy;

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
        this.cId = cId == null ? null : cId.trim();
    }

    public String getcZfbh() {
        return cZfbh;
    }

    public void setcZfbh(String cZfbh) {
        this.cZfbh = cZfbh == null ? null : cZfbh.trim();
    }

    public String getdPcrq() {
        return dPcrq;
    }

    public void setdPcrq(String dPcrq) {
        this.dPcrq = dPcrq == null ? null : dPcrq.trim();
    }

    public String getcBdlb() {
        return cBdlb;
    }

    public void setcBdlb(String cBdlb) {
        this.cBdlb = cBdlb == null ? null : cBdlb.trim();
    }

    public String getcBdfd() {
        return cBdfd;
    }

    public void setcBdfd(String cBdfd) {
        this.cBdfd = cBdfd == null ? null : cBdfd.trim();
    }

    public String getcBdyy() {
        return cBdyy;
    }

    public void setcBdyy(String cBdyy) {
        this.cBdyy = cBdyy == null ? null : cBdyy.trim();
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
        this.cCjpc = cCjpc == null ? null : cCjpc.trim();
    }

	@Override
	public String toString() {
		return "ZfXfbdJx [cId=" + cId + ", cZfbh=" + cZfbh + ", dPcrq=" + dPcrq + ", cBdlb=" + cBdlb + ", cBdfd="
				+ cBdfd + ", cBdyy=" + cBdyy + ", dUrlTime=" + dUrlTime + ", dCjrq=" + dCjrq + ", cCjpc=" + cCjpc + "]";
	}
    
}