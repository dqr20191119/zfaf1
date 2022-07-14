package com.cesgroup.prison.zfxx.jyqk.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
@Entity
@Table(name = "ZFINFO.T_DH_JYQS")
public class DhJyqs extends StringIDEntity {

	private static final long serialVersionUID = 1L;

	private String nf;

    private BigDecimal jyrc;

    private String jyId;

    private String cjpc;

    private Date cjrq;

    public String getNf() {
        return nf;
    }

    public void setNf(String nf) {
        this.nf = nf == null ? null : nf.trim();
    }

    public BigDecimal getJyrc() {
        return jyrc;
    }

    public void setJyrc(BigDecimal jyrc) {
        this.jyrc = jyrc;
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