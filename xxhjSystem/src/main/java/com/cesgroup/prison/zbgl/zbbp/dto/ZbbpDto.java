package com.cesgroup.prison.zbgl.zbbp.dto;
/**
* @author lihong
* @date 创建时间：2020年5月22日 上午9:35:51
* 类说明:
*/
public class ZbbpDto {
	private String id ;//是模板部门表的主键
	private String categoryId;
	private String dmdDprtmntId;
	private String dmdModeId;//模板Id
	private String dmdStartDate;//值班开始时间
	private String dmdEndDate;//值班结束时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getDmdDprtmntId() {
		return dmdDprtmntId;
	}
	public void setDmdDprtmntId(String dmdDprtmntId) {
		this.dmdDprtmntId = dmdDprtmntId;
	}
	public String getDmdModeId() {
		return dmdModeId;
	}
	public void setDmdModeId(String dmdModeId) {
		this.dmdModeId = dmdModeId;
	}
	public String getDmdStartDate() {
		return dmdStartDate;
	}
	public void setDmdStartDate(String dmdStartDate) {
		this.dmdStartDate = dmdStartDate;
	}
	public String getDmdEndDate() {
		return dmdEndDate;
	}
	public void setDmdEndDate(String dmdEndDate) {
		this.dmdEndDate = dmdEndDate;
	}

}
