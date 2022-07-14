package com.cesgroup.prison.zfxx.dzwp.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name = "ZFINFO.T_DZWP_ZFYSJG")
public class DzwpYsjg extends StringIDEntity {

	private static final long serialVersionUID = 1L;

    private String types;

    private BigDecimal stanpro;

    private BigDecimal autualpro;

    private String jyId;

    private String cjpc;

    private Date cjrq;

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types == null ? null : types.trim();
    }

    public BigDecimal getStanpro() {
        return stanpro;
    }

    public void setStanpro(BigDecimal stanpro) {
        this.stanpro = stanpro;
    }

    public BigDecimal getAutualpro() {
        return autualpro;
    }

    public void setAutualpro(BigDecimal autualpro) {
        this.autualpro = autualpro;
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