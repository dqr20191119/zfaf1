package com.cesgroup.prison.wwjg.wdgzwh.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name = "T_WDGZWH")
public class WdgzwhEntity extends  StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String gzlx;

    private String fzlx;

    private String gznr;

    private String fz;

    public String getGzlx() {
        return gzlx;
    }

    public void setGzlx(String gzlx) {
        this.gzlx = gzlx == null ? null : gzlx.trim();
    }

    public String getFzlx() {
        return fzlx;
    }

    public void setFzlx(String fzlx) {
        this.fzlx = fzlx == null ? null : fzlx.trim();
    }

    public String getGznr() {
        return gznr;
    }

    public void setGznr(String gznr) {
        this.gznr = gznr == null ? null : gznr.trim();
    }

    public String getFz() {
        return fz;
    }

    public void setFz(String fz) {
        this.fz = fz == null ? null : fz.trim();
    }

	@Override
	public String toString() {
		return "WdgzwhEntity [id=" + id + ", gzlx=" + gzlx + ", fzlx=" + fzlx + ", gznr=" + gznr + ", fz=" + fz + "]";
	}
    
}