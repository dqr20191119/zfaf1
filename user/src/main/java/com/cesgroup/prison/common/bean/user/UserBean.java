package com.cesgroup.prison.common.bean.user;

/**
 * cesgroup
 * 用户实体
 */
import java.util.ArrayList;
import java.util.List;

import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.common.bean.user.utils.UserConfigUtil;

public class UserBean {
	// 用户基本信息
	private String userId = null; 		// 用户ID
	private String userName = null; 	// 用户名
	private String password = null; 	// 密码

	// 民警信息
	private String loginName = null; 	// 用户登录名
	private String realName = null; 	// 用户真实姓名
	private String policeNo = null; 	// 用户编码(警号)
	private String cusNumber = null; 	// 机构号如果是省局这里的机构号不填
	private String orgId = null;		//organizeID  系统对应的主键ID added by lihh at 2018-3-14 单位ID
	private String orgCode = null; 		// 机构号和cusNumber同义
	private String orgName = null; 		// 机构名称
	private String dprtmntId = null; 	// 部门ID
	private String dprtmntCode = null; 	// 部门编码
	private String dprtmntName = null; 	// 部门名称
	private List<DepartmentBean> departments = new ArrayList<DepartmentBean>();	// 用户所属部门
	private List<RoleBean> roles = new ArrayList<RoleBean>();// 用户角色集合

	// 扩展
	private String userAio = "0";			// 使用分控一体功能：0不使用、1使用
	private String orgClassKey = null; 		// 组织类别ID
	private EUserLevel userLevel = null;	// 用户等级: 1省局、2监狱、3监区
	private String isSpecialPolice = "0";	// 是否特警队员：0否、1是?	

	// 登录信息
	private String loginIp = null; 			// 用户登录IP
	private String loginMode = null;		// 用户登录模式：1用户密码登录、2.授权登录
	private long loginTime = 0;				// 用户登录时间


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPoliceNo() {
		return policeNo;
	}
	public void setPoliceNo(String policeCode) {
		this.policeNo = policeCode;
	}
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDprtmntId() {
		return dprtmntId;
	}
	public void setDprtmntId(String dprtmntId) {
		this.dprtmntId = dprtmntId;
	}
	public String getDprtmntCode() {
		return dprtmntCode;
	}
	public void setDprtmntCode(String dprtmntCode) {
		this.dprtmntCode = dprtmntCode;
	}
	public String getDprtmntName() {
		return dprtmntName;
	}
	public void setDprtmntName(String dprtmntName) {
		this.dprtmntName = dprtmntName;
	}
	public List<DepartmentBean> getDepartments() {
		return departments;
	}
	public void setDepartments(List<DepartmentBean> departments) {
		this.departments = departments;
	}
	public List<RoleBean> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleBean> roles) {
		this.roles = roles;
	}
	public String getUserAio() {
		return userAio;
	}
	public void setUserAio(String userAio) {
		this.userAio = userAio;
	}
	public String getOrgClassKey() {
		return orgClassKey;
	}
	public void setOrgClassKey(String orgClassKey) {
		this.orgClassKey = orgClassKey;
	}
	public EUserLevel getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(EUserLevel userLevel) {
		this.userLevel = userLevel;
	}
	public void setUserLevel(String orgCode, String dprtmntCode) {
		if (UserConfigUtil.get(UserConfigUtil.AUTH_SYSTEM_BASE_ORGCODE).equals(orgCode)) {
			this.userLevel = EUserLevel.PROV;
		} else if(orgCode.length() == 4 && dprtmntCode.length() == 6) {
			this.userLevel = EUserLevel.PRIS;
		} else if(orgCode.length() == 4 && dprtmntCode.length() == 8) {
			this.userLevel = EUserLevel.AREA;
		} else {
			this.userLevel = EUserLevel.AREA;
		}
	}
	public String getIsSpecialPolice() {
		return isSpecialPolice;
	}
	public void setIsSpecialPolice(String isSpecialPolice) {
		this.isSpecialPolice = isSpecialPolice;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getLoginMode() {
		return loginMode;
	}
	public void setLoginMode(String loginMode) {
		this.loginMode = loginMode;
	}
	public long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
