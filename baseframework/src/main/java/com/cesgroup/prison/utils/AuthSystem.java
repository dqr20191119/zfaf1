package com.cesgroup.prison.utils;

import java.util.List;
import java.util.Map;

/*import com.cesgroup.authen4.code.entity.Code;
import com.cesgroup.authen4.organization.entity.Organization;
import com.cesgroup.authen4.resource.entity.Resource;
import com.cesgroup.authen4.role.entity.Role;
import com.cesgroup.authen4.user.entity.User;*/

public interface AuthSystem {
/***********************用户相关接口开始*************************************************/
	/** 根据登录名获取用户 **/
	//public User getUserByLoginName(String loginName);

	/** 根据ID获取用户 **/
	//public User getUserById(String id);

	/** 根据身份证号获取用户 **/
	//public User getUserByIdCard(String idCard);
	
	/** 根据角色ID和租戶获取该具有该角色的所有用户 **/
	//public List<User> getUsersByRoleId(String roleId, String tenantId);
	
	/** 根据组织ID获取该组织下的用户（只取一层） **/
	//public List<User> getUsersByOrgId(String orgId);

	/** 根据组织ID获取该组织下的所有用户（包括子组织下的用户） **/
	//public List<User> getAllUsersByOrgId(String orgId);
	
	/** 修改用户 **/
	//public void updateUser(User user);

/***********************用户相关接口结束*************************************************/

	
/***********************角色相关接口开始*************************************************/
	/** 根据用户ID获取他所拥有的角色列表 **/
	//public List<Role> getRolesByUserId(String userId);
/***********************角色相关接口结束*************************************************/
	
	
/***********************资源相关接口开始*************************************************/
	/** 根据用户ID和系统编码获取他当前所拥有的资源 **/
	//public List<Resource> getResourcesByUserId(String userId, String systemCode);
	/** 根据用户ID获取他当前所拥有的资源 **/
	//public List<Resource> getResourcesByUserId(String userId);
	
	/** 根据角色ID获取该该角色所关联的资源 **/
	//public List<Resource> getResourcesByRoleId(String roleId);
/***********************资源相关接口结束*************************************************/
	
	
/***********************组织相关接口开始*************************************************/
	/** 根据用户ID获取他当前所在的组织 **/
	//public Organization getOrgByUserId(String userId);

	/** 根据用户ID获取他当前所在的顶层组织 **/
	//public Organization getTopOrgByUserId(String userId);
	
	/** 根据组织ID获取所有下级组织 **/
	//public List<Organization> getChildrenOrgByOrgId(String orgId);

	/** 根据组织ID获取上级级组织 **/
	//public Organization getParentOrgByOrgId(String orgId);
	
	/** 根据租户获取组织结构 **/
	//public List<Organization> getAllOrganizations(String tenantId);
	
	/**	获取组织架构  **/
	//public List<Organization> getOrgTree();
	///
	/**	根据级别 获取组织架构  **/
	//public List<Organization> getOrgByLevel(String parentId,String organizationLevel);

/***********************组织相关接口结束*************************************************/
	

/***********************编码相关接口开始*************************************************/
	/**根据父节点的编码key查询子节点编码对象的集合**/
	//public List<Code> getCodeListByCodeKey(String codeKey,String groupKey);
/***********************编码相关接口结束*************************************************/
	

	

	

	

	

	

	
	
	
	
	
}
