package com.cesgroup.prison.zbgl.jjb.entity;

import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
* @author lihong
* @date 创建时间：2020年5月15日 上午10:09:02
* 类说明:交接班值班日志表
*/
@Table(name = "CDS_DUTY_JJB_ZBRZ")
public class JjbRzglEntity extends StringIDEntity {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cusNumber;
	/**
	 * 部门code
	 */
	private String deptNumber;
	/**
	 * 时间
	 */
	private String time;
	/**
	 * 地点
	 */
	private String location;
	/**
	 * 值班详情
	 */
	private String zbDetial;
	/**
	 * 遗留问题
	 */
	private String ylWt;
	/**
	 * 创建日期
	 */
	private String cjrq;
	/**
	 * 创建人id
	 */
	private String cjrId;
	/**
	 * 更新人id
	 */
	private String gxrId;
	/**
	 * 更新人姓名
	 */
	private String gxrName;
	/**
	 * 更新时间
	 */
	private String gxRq;
	/**
	 * 创建人姓名
	 */
	private String cjrName;
	/**
	 * 关联业务交接班主表id
	 */
	private String ywId;
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public String getDeptNumber() {
		return deptNumber;
	}
	public void setDeptNumber(String deptNumber) {
		this.deptNumber = deptNumber;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getZbDetial() {
		return zbDetial;
	}
	public void setZbDetial(String zbDetial) {
		this.zbDetial = zbDetial;
	}
	public String getYlWt() {
		return ylWt;
	}
	public void setYlWt(String ylWt) {
		this.ylWt = ylWt;
	}
	public String getCjrq() {
		return cjrq;
	}
	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}
	public String getCjrId() {
		return cjrId;
	}
	public void setCjrId(String cjrId) {
		this.cjrId = cjrId;
	}
	public String getGxrId() {
		return gxrId;
	}
	public void setGxrId(String gxrId) {
		this.gxrId = gxrId;
	}
	public String getGxrName() {
		return gxrName;
	}
	public void setGxrName(String gxrName) {
		this.gxrName = gxrName;
	}
	public String getGxRq() {
		return gxRq;
	}
	public void setGxRq(String gxRq) {
		this.gxRq = gxRq;
	}
	public String getCjrName() {
		return cjrName;
	}
	public void setCjrName(String cjrName) {
		this.cjrName = cjrName;
	}
	public String getYwId() {
		return ywId;
	}
	public void setYwId(String ywId) {
		this.ywId = ywId;
	}
	
	
	
	
}
