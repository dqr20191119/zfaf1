package com.cesgroup.prison.zfxx.jyqk.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name = "ZFINFO.T_DH_MBFX")
public class DhMbqk extends StringIDEntity {

	private static final long serialVersionUID = 1L;

	private String mblx;

    private BigDecimal mbrc;

    private String jyId;

    private String cjpc;

    private Date cjrq;

    public String getMblx() {
        return mblx;
    }

    public void setMblx(String mblx) {
        this.mblx = mblx == null ? null : mblx.trim();
    }

    public BigDecimal getMbrc() {
        return mbrc;
    }

    public void setMbrc(BigDecimal mbrc) {
        this.mbrc = mbrc;
    }

    public String getJyId() {
        return jyId;
    }

    public void setJyId(String jyId) {
        this.jyId = jyId == null ? null : jyId.trim();
    }

    public String getCjpc() {
        return cjpc;
    }

    public void setCjpc(String cjpc) {
        this.cjpc = cjpc == null ? null : cjpc.trim();
    }

    public Date getCjrq() {
        return cjrq;
    }

    public void setCjrq(Date cjrq) {
        this.cjrq = cjrq;
    }
}