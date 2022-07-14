package com.cesgroup.prison.zfxx.zfxfbd.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ZFINFO.T_ZF_XFBD_JS")
public class ZfXfbdJs extends StringIDEntity {

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
     * 离监日期
     */
    private String dLjrq;
    /**
     * 呈报日期
     */
    private String dCbrq;
    /**
     * 执行日期
     */
    private String dZxrq;
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

	/**
	 * 监狱编码
	 */
	private String cJyId;
	
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

    public String getdLjrq() {
        return dLjrq;
    }

    public void setdLjrq(String dLjrq) {
        this.dLjrq = dLjrq == null ? null : dLjrq.trim();
    }

    public String getdCbrq() {
        return dCbrq;
    }

    public void setdCbrq(String dCbrq) {
        this.dCbrq = dCbrq == null ? null : dCbrq.trim();
    }

    public String getdZxrq() {
        return dZxrq;
    }

    public void setdZxrq(String dZxrq) {
        this.dZxrq = dZxrq == null ? null : dZxrq.trim();
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

	public String getcJyId() {
		return cJyId;
	}

	public void setcJyId(String cJyId) {
		this.cJyId = cJyId;
	}

	@Override
	public String toString() {
		return "ZfXfbdJs [cId=" + cId + ", cZfbh=" + cZfbh + ", dLjrq=" + dLjrq + ", dCbrq=" + dCbrq + ", dZxrq="
				+ dZxrq + ", dUrlTime=" + dUrlTime + ", dCjrq=" + dCjrq + ", cCjpc=" + cCjpc + ", cJyId=" + cJyId + "]";
	}
    
}