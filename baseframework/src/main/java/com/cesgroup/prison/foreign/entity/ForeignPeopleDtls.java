package com.cesgroup.prison.foreign.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
 * Description: 外来人员信息表实体类
 * 
 * @author lincoln.cheng
 *
 */
@Entity
@Table(name = "CDS_FOREIGN_PEOPLE_DTLS")
public class ForeignPeopleDtls extends StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 证件类型 1、 身份证  2、驾驶证
	 */
	private String fpdIdCardTypeIndc;
	/**
	 * 证件号
	 */
    private String fpdIdCardCode;
    /**
     * 姓名
     */
    private String fpdPeopleName;
    /**
     * 性别
     */
    private String fpdSexIndc;
    /**
     * 年龄
     */
    private String fpdAge;
    /**
     * 电话/手机
     */
    private String fpdPhone;
    /**
     * 家庭住址
     */
    private String fpdFamilyAddrs;
    /**
     * 出入登记ID
     */
    private String fpdRegisterId;
    
	public String getFpdIdCardTypeIndc() {
		return fpdIdCardTypeIndc;
	}
	public void setFpdIdCardTypeIndc(String fpdIdCardTypeIndc) {
		this.fpdIdCardTypeIndc = fpdIdCardTypeIndc;
	}
	public String getFpdIdCardCode() {
		return fpdIdCardCode;
	}
	public void setFpdIdCardCode(String fpdIdCardCode) {
		this.fpdIdCardCode = fpdIdCardCode;
	}
	public String getFpdPeopleName() {
		return fpdPeopleName;
	}
	public void setFpdPeopleName(String fpdPeopleName) {
		this.fpdPeopleName = fpdPeopleName;
	}
	public String getFpdSexIndc() {
		return fpdSexIndc;
	}
	public void setFpdSexIndc(String fpdSexIndc) {
		this.fpdSexIndc = fpdSexIndc;
	}
	public String getFpdAge() {
		return fpdAge;
	}
	public void setFpdAge(String fpdAge) {
		this.fpdAge = fpdAge;
	}
	public String getFpdPhone() {
		return fpdPhone;
	}
	public void setFpdPhone(String fpdPhone) {
		this.fpdPhone = fpdPhone;
	}
	public String getFpdFamilyAddrs() {
		return fpdFamilyAddrs;
	}
	public void setFpdFamilyAddrs(String fpdFamilyAddrs) {
		this.fpdFamilyAddrs = fpdFamilyAddrs;
	}
	public String getFpdRegisterId() {
		return fpdRegisterId;
	}
	public void setFpdRegisterId(String fpdRegisterId) {
		this.fpdRegisterId = fpdRegisterId;
	}
}