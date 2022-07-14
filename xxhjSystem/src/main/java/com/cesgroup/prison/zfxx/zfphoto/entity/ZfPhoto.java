package com.cesgroup.prison.zfxx.zfphoto.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 罪犯照片表实体类
 * 
 * @author lincoln.cheng
 *
 *         2019年1月17日
 */
@Entity
@Table(name = "ZFINFO.T_ZF_PHOTO")
public class ZfPhoto extends StringIDEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Desc: 罪犯编号
	 */
	private String cZfbh;
	/**
	 * Desc: 罪犯标识
	 */
	private String cId;
	/**
	 * Desc: 罪犯姓名
	 */
	private String cXm;
	/**
	 * Desc: 照片类别
	 */
	private String cZplb;
	/**
	 * Desc: 照片存储地址对应ID（访问华宇提供的照片接口用到）
	 */
	private String cStorageid;
	/**
	 * Desc: 采集时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dCjsj;
	/**
	 * Desc: 照片在本系统的存储名称（安防平台）
	 */
	private String cFileName;
	/**
	 * Desc: 照片在本系统的存储地址（安防平台）
	 */
	private String cFilePath;
	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dUrlTime;

	/**
	 * 创建日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date dCjrq;

	/**
	 * 创建批次
	 */
	private String cCjpc;

	public String getcZfbh() {
		return cZfbh;
	}

	public void setcZfbh(String cZfbh) {
		this.cZfbh = cZfbh;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getcXm() {
		return cXm;
	}

	public void setcXm(String cXm) {
		this.cXm = cXm;
	}

	public String getcZplb() {
		return cZplb;
	}

	public void setcZplb(String cZplb) {
		this.cZplb = cZplb;
	}

	public String getcStorageid() {
		return cStorageid;
	}

	public void setcStorageid(String cStorageid) {
		this.cStorageid = cStorageid;
	}

	public Date getdCjsj() {
		return dCjsj;
	}

	public void setdCjsj(Date dCjsj) {
		this.dCjsj = dCjsj;
	}

	public String getcFileName() {
		return cFileName;
	}

	public void setcFileName(String cFileName) {
		this.cFileName = cFileName;
	}

	public String getcFilePath() {
		return cFilePath;
	}

	public void setcFilePath(String cFilePath) {
		this.cFilePath = cFilePath;
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
		this.cCjpc = cCjpc;
	}

	@Override
	public String toString() {
		return "ZfPhoto [cZfbh=" + cZfbh + ", cId=" + cId + ", cXm=" + cXm + ", cZplb=" + cZplb + ", cStorageid="
				+ cStorageid + ", dCjsj=" + dCjsj + ", cFileName=" + cFileName + ", cFilePath=" + cFilePath
				+ ", dUrlTime=" + dUrlTime + ", dCjrq=" + dCjrq + ", cCjpc=" + cCjpc + "]";
	}

}