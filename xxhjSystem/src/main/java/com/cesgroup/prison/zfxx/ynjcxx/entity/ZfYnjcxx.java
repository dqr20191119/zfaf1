package com.cesgroup.prison.zfxx.ynjcxx.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
@Entity
@Table(name = "ZFINFO.T_ZF_YNJCXX")
public class ZfYnjcxx extends StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String cId;

    private String cZfbh;

    /**
     * 批准日期
     */
    private String dSpsj;
    
    /**
     * 奖励类别
     */
    private String cJllb;

    /**
     * 处罚类别
     */
    private String cCflb;

    /**
     * 奖惩依据
     */
    private String cJcyj;

    /**
     * 备注
     */
    private String cBz;

    private Date dUrlTime;

    private Date dCjrq;

    private String cCjpc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

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

    public String getdSpsj() {
        return dSpsj;
    }

    public void setdSpsj(String dSpsj) {
        this.dSpsj = dSpsj == null ? null : dSpsj.trim();
    }

    public String getcJllb() {
        return cJllb;
    }

    public void setcJllb(String cJllb) {
        this.cJllb = cJllb == null ? null : cJllb.trim();
    }

    public String getcCflb() {
        return cCflb;
    }

    public void setcCflb(String cCflb) {
        this.cCflb = cCflb == null ? null : cCflb.trim();
    }

    public String getcJcyj() {
        return cJcyj;
    }

    public void setcJcyj(String cJcyj) {
        this.cJcyj = cJcyj == null ? null : cJcyj.trim();
    }

    public String getcBz() {
        return cBz;
    }

    public void setcBz(String cBz) {
        this.cBz = cBz == null ? null : cBz.trim();
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
}