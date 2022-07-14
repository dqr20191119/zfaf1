package com.cesgroup.prison.yrzq.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
*一日执勤
*/
@Entity
@Table(name = "T_ZHAF_ONEDAY_DUTY")
public class YrzqEntity extends StringIDEntity {
	
	private String prisonId;
	private String departId;
	private String departName;
	
	private String startTime;
	
	private String endTime;
	
	@Column(updatable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	private String createUserId;
	private String dataType;
	private String state;
	private String isTimeout;
	private String title;
	
	private String finishDate;
	
	private String mark;
	private String finishUserName;
	private String finishUserId;
	private String fxcjId;
	private String cpsLx;
	private String sxsj;
	
	private String tzsj; //巡更通知时间
	private String tznr; //巡更通知内容
	private String zbr; //值班人姓名
	private String zbrId; //值班人id
	private String xgtzId;//巡更通知
	private String zbrz;//值班日志
	
	@Transient
	private String cs;
	
	
	public String getPrisonId() {
		return prisonId;
	}
	public void setPrisonId(String prisonId) {
		this.prisonId = prisonId;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIsTimeout() {
		return isTimeout;
	}
	public void setIsTimeout(String isTimeout) {
		this.isTimeout = isTimeout;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getFinishUserName() {
		return finishUserName;
	}
	public void setFinishUserName(String finishUserName) {
		this.finishUserName = finishUserName;
	}
	public String getFinishUserId() {
		return finishUserId;
	}
	public void setFinishUserId(String finishUserId) {
		this.finishUserId = finishUserId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getFxcjId() {
		return fxcjId;
	}
	public void setFxcjId(String fxcjId) {
		this.fxcjId = fxcjId;
	}
	public String getCpsLx() {
		return cpsLx;
	}
	public void setCpsLx(String cpsLx) {
		this.cpsLx = cpsLx;
	}
	public String getSxsj() {
		return sxsj;
	}
	public void setSxsj(String sxsj) {
		this.sxsj = sxsj;
	}
	public String getCs() {
		return cs;
	}
	public void setCs(String cs) {
		this.cs = cs;
	}
	public String getTzsj() {
		return tzsj;
	}
	public void setTzsj(String tzsj) {
		this.tzsj = tzsj;
	}
	public String getTznr() {
		return tznr;
	}
	public void setTznr(String tznr) {
		this.tznr = tznr;
	}
	public String getZbr() {
		return zbr;
	}
	public void setZbr(String zbr) {
		this.zbr = zbr;
	}
	public String getZbrId() {
		return zbrId;
	}
	public void setZbrId(String zbrId) {
		this.zbrId = zbrId;
	}
	public String getXgtzId() {
		return xgtzId;
	}
	public void setXgtzId(String xgtzId) {
		this.xgtzId = xgtzId;
	}
	public String getZbrz() {
		return zbrz;
	}
	public void setZbrz(String zbrz) {
		this.zbrz = zbrz;
	}
	
}
