package com.cesgroup.prison.yrzq.template.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 一日执勤模板与执勤工作项关联关系实体类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
@Entity
@Table(name = "CDS_YRZQ_TEMPLATE_TASK")
public class TemplateTask extends StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 执勤模板编号
	 */
    private String templateId;
    /**
     * 执勤任务项编号
     */
    private String taskId;
    /**
     * 任务项开始时间
     */
    private Date startTaskTime;
    /**
     * 任务项结束时间
     */
    private Date endTaskTime;
    /**
     * 数据状态（0：有效；1：无效）
     */
    private String status;
    /**
     * 排序序号
     */
    private Integer showOrder;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 最近修改人
     */
    private String updateUser;
    /**
     * 最近修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public Date getStartTaskTime() {
		return startTaskTime;
	}
	public void setStartTaskTime(Date startTaskTime) {
		this.startTaskTime = startTaskTime;
	}
	public Date getEndTaskTime() {
		return endTaskTime;
	}
	public void setEndTaskTime(Date endTaskTime) {
		this.endTaskTime = endTaskTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}