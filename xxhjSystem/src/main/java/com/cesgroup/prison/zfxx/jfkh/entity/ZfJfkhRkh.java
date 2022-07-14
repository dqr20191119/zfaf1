package com.cesgroup.prison.zfxx.jfkh.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
@Entity
@Table(name = "ZFINFO.T_ZF_JFKH_RKH")
public class ZfJfkhRkh extends StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cId;

    private String cZfbh;

    /**
     * 考核日期
     */
    private String dKhrq;

    /**
     * 加扣分
     */
    private BigDecimal nJkf;

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

    public String getdKhrq() {
        return dKhrq;
    }

    public void setdKhrq(String dKhrq) {
        this.dKhrq = dKhrq == null ? null : dKhrq.trim();
    }

    public BigDecimal getnJkf() {
        return nJkf;
    }

    public void setnJkf(BigDecimal nJkf) {
        this.nJkf = nJkf;
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
		return "ZfJfkhRkh [cId=" + cId + ", cZfbh=" + cZfbh + ", dKhrq=" + dKhrq + ", nJkf=" + nJkf + ", dUrlTime="
				+ dUrlTime + ", dCjrq=" + dCjrq + ", cCjpc=" + cCjpc + "]";
	}
    
}