package com.cesgroup.prison.zfxx.jfkh.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
@Entity
@Table(name = "ZFINFO.T_ZF_JFKH_YHZ")
public class ZfJfkhYhz extends StringIDEntity {

	private static final long serialVersionUID = 1L;

	private String cId;

    private String cZfbh;

    /**
     * 考核年份
     */
    private String nKhnf;

    /**
     * 考核月份
     */
    private String nKhyf;

    /**
     * 月考核得分
     */
    private BigDecimal nYkhdf;

    /**
     * 累计积分
     */
    private BigDecimal nLjjf;

    private Date dUrlTime;

    private Date dCjrq;

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

    public String getnKhnf() {
        return nKhnf;
    }

    public void setnKhnf(String nKhnf) {
        this.nKhnf = nKhnf == null ? null : nKhnf.trim();
    }

    public String getnKhyf() {
        return nKhyf;
    }

    public void setnKhyf(String nKhyf) {
        this.nKhyf = nKhyf == null ? null : nKhyf.trim();
    }

    public BigDecimal getnYkhdf() {
        return nYkhdf;
    }

    public void setnYkhdf(BigDecimal nYkhdf) {
        this.nYkhdf = nYkhdf;
    }

    public BigDecimal getnLjjf() {
        return nLjjf;
    }

    public void setnLjjf(BigDecimal nLjjf) {
        this.nLjjf = nLjjf;
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
		return "ZfJfkhYhz [cId=" + cId + ", cZfbh=" + cZfbh + ", nKhnf=" + nKhnf + ", nKhyf=" + nKhyf + ", nYkhdf="
				+ nYkhdf + ", nLjjf=" + nLjjf + ", dUrlTime=" + dUrlTime + ", dCjrq=" + dCjrq + ", cCjpc=" + cCjpc
				+ "]";
	}
    
}