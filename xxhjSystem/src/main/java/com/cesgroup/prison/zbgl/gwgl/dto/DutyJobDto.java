package com.cesgroup.prison.zbgl.gwgl.dto;

import java.io.Serializable;

/**
 * 值班岗位DTO
 * 
 * @author lincoln.cheng
 *
 */
public class DutyJobDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 值班岗位编号
	 */
	private String id;
	/**
	 * 监狱编号
	 */
	private String cdjCusNumber;
	/**
	 * 值班岗位名称
	 */
	private String cdjJobName;
	/**
	 * 值班类别编号
	 */
	private String cdjCategoryId;
	/**
	 * 值班类别名称
	 */
	private String dcaCategoryName;
	/**
	 * 值班表部门编号
	 */
	private String dcdDprtmntId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCdjCusNumber() {
		return cdjCusNumber;
	}
	public void setCdjCusNumber(String cdjCusNumber) {
		this.cdjCusNumber = cdjCusNumber;
	}
	public String getCdjJobName() {
		return cdjJobName;
	}
	public void setCdjJobName(String cdjJobName) {
		this.cdjJobName = cdjJobName;
	}
	public String getCdjCategoryId() {
		return cdjCategoryId;
	}
	public void setCdjCategoryId(String cdjCategoryId) {
		this.cdjCategoryId = cdjCategoryId;
	}
	public String getDcaCategoryName() {
		return dcaCategoryName;
	}
	public void setDcaCategoryName(String dcaCategoryName) {
		this.dcaCategoryName = dcaCategoryName;
	}
	public String getDcdDprtmntId() {
		return dcdDprtmntId;
	}
	public void setDcdDprtmntId(String dcdDprtmntId) {
		this.dcdDprtmntId = dcdDprtmntId;
	}
}
