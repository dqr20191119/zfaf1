package com.cesgroup.prison.zfxx.dzwp.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name = "ZFINFO.T_DZWP_STZC")
public class DzwpStzc extends StringIDEntity {

	private static final long serialVersionUID = 1L;

	private String types;

    private BigDecimal amount;

    private String jyId;

    private String cjpc;

    private Date cjrq;

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types == null ? null : types.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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