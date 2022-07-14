package com.cesgroup.prison.zfxx.zfdm.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 巡视点名实体类
 * 
 * @author lincoln.cheng
 *
 */
@Entity
@Table(name = "T_ZX_XSDM", schema = "ZFINFO")
public class ZfXsdm extends StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 点名时间
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dmsj;
	/**
	 * 总人数
	 */
    private Integer zrs;
    /**
     * 缺勤人数
     */
    private Integer qqrs;
    /**
     * 单位编号
     */
    private String corpid;
    /**
     * 部门编号
     */
    private String deptid;
    /**
     * 单位名称
     */
    private String corpname;
    /**
     * 部门名称
     */
    private String deptname;
    /**
     * 单位排序
     */
    private Integer corporder;
    /**
     * 部门排序
     */
    private Integer deptorder;
    
	public Date getDmsj() {
		return dmsj;
	}
	public void setDmsj(Date dmsj) {
		this.dmsj = dmsj;
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
	public String getCorpid() {
		return corpid;
	}
	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public Integer getCorporder() {
		return corporder;
	}
	public void setCorporder(Integer corporder) {
		this.corporder = corporder;
	}
	public Integer getDeptorder() {
		return deptorder;
	}
	public void setDeptorder(Integer deptorder) {
		this.deptorder = deptorder;
	}
}