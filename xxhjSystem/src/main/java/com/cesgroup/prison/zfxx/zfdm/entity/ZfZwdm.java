package com.cesgroup.prison.zfxx.zfdm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "T_ZX_ZWDM", schema = "ZFINFO")
public class ZfZwdm extends StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 点名类型（早点名；晚点名）
	 */
	private String dmlx;
	/**
	 * 点名时间
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dtdmsj;
    /**
     * 总人数
     */
    private Integer zrs;
    /**
     * 缺勤人数
     */
    private Integer qqrs;
    /**
     * 实到人数
     */
    private Integer sdrs;
    /**
     * 单位ID
     */
    private String corpid;
    /**
     * 单位名称
     */
    private String corpname;
    /**
     * 部门ID
     */
    private String deptid;
    /**
     * 部门名称
     */
    private String deptname;
    /**
     * 单位排序
     */
    private Integer corpnorder;
    /**
     * 部门排序
     */
    private Integer deptnorder;
    
	public String getDmlx() {
		return dmlx;
	}
	public void setDmlx(String dmlx) {
		this.dmlx = dmlx;
	}
	public Date getDtdmsj() {
		return dtdmsj;
	}
	public void setDtdmsj(Date dtdmsj) {
		this.dtdmsj = dtdmsj;
	}
	public Integer getZrs() {
		return zrs;
	}
	public void setZrs(Integer zrs) {
		this.zrs = zrs;
	}
	public Integer getQqrs() {
		return qqrs;
	}
	public void setQqrs(Integer qqrs) {
		this.qqrs = qqrs;
	}
	public Integer getSdrs() {
		return sdrs;
	}
	public void setSdrs(Integer sdrs) {
		this.sdrs = sdrs;
	}
	public String getCorpid() {
		return corpid;
	}
	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public Integer getCorpnorder() {
		return corpnorder;
	}
	public void setCorpnorder(Integer corpnorder) {
		this.corpnorder = corpnorder;
	}
	public Integer getDeptnorder() {
		return deptnorder;
	}
	public void setDeptnorder(Integer deptnorder) {
		this.deptnorder = deptnorder;
	}
}