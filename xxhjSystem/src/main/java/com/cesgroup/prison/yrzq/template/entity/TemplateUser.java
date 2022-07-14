package com.cesgroup.prison.yrzq.template.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 执勤模板与民警关联关系表实体类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
@Entity
@Table(name = "CDS_YRZQ_TEMPLATE_USER")
public class TemplateUser extends StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 执勤模板编号
	 */
    private String templateId;
    /**
     * 民警用户编号（对应CARDINFO.T_EMPLOYEE_VIEW表的UNIQUE_ID字段）
     */
    private String userId;
    /**
     * 数据状态(0:有效; 1:无效;)
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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