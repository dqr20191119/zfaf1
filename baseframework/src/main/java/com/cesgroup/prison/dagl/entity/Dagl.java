package com.cesgroup.prison.dagl.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.mybatis.entity.StringIDEntity;

@Entity
@Table(name = "T_FW_ARCHIVE")
public class Dagl extends StringIDEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 档号
	 */
	private String dh;
	/**
	 * 保管期限
	 */
	private String bgqx;
	/**
	 * 题名
	 */
	private String tm;
	/**
	 * 责任者
	 */
	private String zrz;

	/**
	 * 年度
	 */
	private String nd;

	/**
	 * /删除状态
	 */
	private int delStatus;

	/**
	 * 管理保存库标志
	 */
	private String tempForm;
	/**
	 * 全宗号
	 */
	private String qzh;
	/**
	 * 目录号
	 */
	private String mlh;
	/**
	 * 页数
	 */
	private String ys;
	/**
	 * 件号
	 */
	private String jh;
	/**
	 * 文号
	 */
	private String wh;
	/**
	 * 主题词
	 */
	private String ztc;
	/**
	 * 密级
	 */
	private String mj;
	/**
	 * 文件起始时间
	 */
	private String wjqssj;
	/**
	 * 文件终止时间
	 */
	private String wjzzsj;
	private String filename;
	@Transient
	private List<Atth> atths;

	public List<Atth> getAtths() {
		return atths;
	}

	public void setAtths(List<Atth> atths) {
		this.atths = atths;
	}

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getBgqx() {
		return bgqx;
	}

	public void setBgqx(String bgqx) {
		this.bgqx = bgqx;
	}

	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	public String getZrz() {
		return zrz;
	}

	public void setZrz(String zrz) {
		this.zrz = zrz;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public int getDelStatus() {
		return delStatus;
	}

	@Column(name = "del_status")
	public void setDelStatus(int delStatus) {
		this.delStatus = delStatus;
	}

	public String getTempForm() {
		return tempForm;
	}

	@Column(name = "temp_form")
	public void setTempForm(String tempForm) {
		this.tempForm = tempForm;
	}

	public String getQzh() {
		return qzh;
	}

	public void setQzh(String qzh) {
		this.qzh = qzh;
	}

	public String getMlh() {
		return mlh;
	}

	public void setMlh(String mlh) {
		this.mlh = mlh;
	}

	public String getYs() {
		return ys;
	}

	public void setYs(String ys) {
		this.ys = ys;
	}

	public String getJh() {
		return jh;
	}

	public void setJh(String jh) {
		this.jh = jh;
	}

	public String getWh() {
		return wh;
	}

	public void setWh(String wh) {
		this.wh = wh;
	}

	public String getZtc() {
		return ztc;
	}

	public void setZtc(String ztc) {
		this.ztc = ztc;
	}

	public String getMj() {
		return mj;
	}

	public void setMj(String mj) {
		this.mj = mj;
	}

	public String getWjqssj() {
		return wjqssj;
	}

	public void setWjqssj(String wjqssj) {
		this.wjqssj = wjqssj;
	}

	public String getWjzzsj() {
		return wjzzsj;
	}

	public void setWjzzsj(String wjzzsj) {
		this.wjzzsj = wjzzsj;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
