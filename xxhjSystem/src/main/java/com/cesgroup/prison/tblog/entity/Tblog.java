package com.cesgroup.prison.tblog.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name = "ZFINFO.T_ZF_TBLOG")
public class Tblog extends StringIDEntity {
	private static final long serialVersionUID = 1L;

    private String jklx;

    private String jybm;

    private String czlx;

    private String czms;

    private Date cjrq;

    private String cjpc;
    
    public Tblog() {
    	
    }

	public Tblog(String jklx, String jybm, String czlx, String czms, Date cjrq, String cjpc) {
		this.jklx = jklx;
		this.jybm = jybm;
		this.czlx = czlx;
		this.czms = czms;
		this.cjrq = cjrq;
		this.cjpc = cjpc;
	}

	public String getJklx() {
		return jklx;
	}

	public void setJklx(String jklx) {
		this.jklx = jklx;
	}

	public String getJybm() {
		return jybm;
	}

	public void setJybm(String jybm) {
		this.jybm = jybm;
	}

	public String getCzlx() {
		return czlx;
	}

	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}

	public String getCzms() {
		return czms;
	}

	public void setCzms(String czms) {
		this.czms = czms;
	}

	public Date getCjrq() {
		return cjrq;
	}

	public void setCjrq(Date cjrq) {
		this.cjrq = cjrq;
	}

	public String getCjpc() {
		return cjpc;
	}

	public void setCjpc(String cjpc) {
		this.cjpc = cjpc;
	}

	@Override
	public String toString() {
		return "Tblog [jklx=" + jklx + ", jybm=" + jybm + ", czlx=" + czlx + ", czms=" + czms + ", cjrq=" + cjrq
				+ ", cjpc=" + cjpc + "]";
	}

}