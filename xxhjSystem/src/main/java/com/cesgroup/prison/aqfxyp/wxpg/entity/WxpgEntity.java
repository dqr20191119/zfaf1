package com.cesgroup.prison.aqfxyp.wxpg.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 危险评估
 * @author admin
 *
 */
@Entity
@Table(name = "ZFINFO.T_HY_WXPG")
public class WxpgEntity extends StringIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
    private String zfxm;
    private String zfbh;
    private String zfxb;
    private String dqgydw;
    private String pglx;
    private String pgdw;
    private String pgr;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date pgsj;
    private Double blwxpgdf;
    private String blwxpgjl;
    private Double zswxpgdf;
    private String zswxpgjl;
    private Double ttwxpgdf;
    private String TTWXPGJL;
    @Transient
    private String zt;
	public String getZfxm() {
		return zfxm;
	}
	public void setZfxm(String zfxm) {
		this.zfxm = zfxm;
	}
	public String getZfbh() {
		return zfbh;
	}
	public void setZfbh(String zfbh) {
		this.zfbh = zfbh;
	}
	public String getZfxb() {
		return zfxb;
	}
	public void setZfxb(String zfxb) {
		this.zfxb = zfxb;
	}
	public String getDqgydw() {
		return dqgydw;
	}
	public void setDqgydw(String dqgydw) {
		this.dqgydw = dqgydw;
	}
	public String getPglx() {
		return pglx;
	}
	public void setPglx(String pglx) {
		this.pglx = pglx;
	}
	public String getPgdw() {
		return pgdw;
	}
	public void setPgdw(String pgdw) {
		this.pgdw = pgdw;
	}
	public String getPgr() {
		return pgr;
	}
	public void setPgr(String pgr) {
		this.pgr = pgr;
	}
	public Date getPgsj() {
		return pgsj;
	}
	public void setPgsj(Date pgsj) {
		this.pgsj = pgsj;
	}
	public Double getBlwxpgdf() {
		return blwxpgdf;
	}
	public void setBlwxpgdf(Double blwxpgdf) {
		this.blwxpgdf = blwxpgdf;
	}
	public String getBlwxpgjl() {
		return blwxpgjl;
	}
	public void setBlwxpgjl(String blwxpgjl) {
		this.blwxpgjl = blwxpgjl;
	}
	public Double getZswxpgdf() {
		return zswxpgdf;
	}
	public void setZswxpgdf(Double zswxpgdf) {
		this.zswxpgdf = zswxpgdf;
	}
	public String getZswxpgjl() {
		return zswxpgjl;
	}
	public void setZswxpgjl(String zswxpgjl) {
		this.zswxpgjl = zswxpgjl;
	}
	public Double getTtwxpgdf() {
		return ttwxpgdf;
	}
	public void setTtwxpgdf(Double ttwxpgdf) {
		this.ttwxpgdf = ttwxpgdf;
	}
	public String getTTWXPGJL() {
		return TTWXPGJL;
	}
	public void setTTWXPGJL(String tTWXPGJL) {
		TTWXPGJL = tTWXPGJL;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
    
    
    
}
