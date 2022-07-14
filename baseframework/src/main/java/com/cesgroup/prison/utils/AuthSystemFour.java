package com.cesgroup.prison.utils;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*import com.cesgroup.authen4.code.entity.Code;
import com.cesgroup.authen4.core.service.interf.ICoreBiz;
import com.cesgroup.authen4.organization.entity.Organization;
import com.cesgroup.authen4.organization.interf.IOrganizeBiz;
import com.cesgroup.authen4.resource.entity.Resource;
import com.cesgroup.authen4.role.entity.Role;
import com.cesgroup.authen4.user.entity.User;*/
import com.cesgroup.framework.base.security.service.IAuthenticationService;

@Service("authService")
public class AuthSystemFour implements AuthSystem {

	/*@Autowired
	private ICoreBiz coreService;
	@Autowired
	private IOrganizeBiz organizeService;
	@Autowired
    private IAuthenticationService authService;

	@Override
	public User getUserByLoginName(String loginName) {
		return coreService.queryUserByLoginName(loginName, 0);
	}

	@Override
	public User getUserById(String id) {
		return coreService.queryUserById(id, 0);
	}

	@Override
	public User getUserByIdCard(String idCard) {
		return coreService.getUserByIdCard(idCard);
	}

	@Override
	public List<Role> getRolesByUserId(String userId) {
		List<Role> roleList = coreService.queryRoleByCurrentUserId(userId);
		return roleList;
	}

	@Override
	public Organization getOrgByUserId(String userId) {
		return organizeService.queryOrgByUserId(userId);
	}

	@Override
	public Organization getTopOrgByUserId(String userId) {
		return organizeService.queryTopOrgByUserId(userId);
	}

	@Override
	public List<Resource> getResourcesByUserId(String userId, String systemCode) {
		List<Resource> resList = coreService.queryResourceByUserIdAndSystemCode(userId, systemCode);
		return resList;
		
	}

	@Override
	public List<User> getUsersByRoleId(String roleId, String tenantId) {
		 List<User> userList=coreService.queryUserByRoleId(roleId, tenantId);
		 return userList;
	}

	@Override
	public List<Resource> getResourcesByRoleId(String roleId) {
		List<Resource> reslist = coreService.queryResourceListByRoleId(roleId);
		return reslist;
	}

	@Override
	public List<Organization> getChildrenOrgByOrgId(String orgId) {
		return organizeService.queryOrganizationByParentId(orgId);
	}

	@Override
	public Organization getParentOrgByOrgId(String orgId) {
		return organizeService.queryParentOrganize(orgId);
	}

	@Override
	public List<User> getUsersByOrgId(String orgId) {
		List<User> userList = coreService.queryUsersByOrgId(orgId, 0);
		return userList;
	}

	@Override
	public List<User> getAllUsersByOrgId(String orgId) {
		List<User> listUser=organizeService.queryUsersByOrgId(orgId);
		return listUser;
	}

	@Override
	public List<Organization> getAllOrganizations(String tenantId) {
		List<Organization> orgList = organizeService.queryAllOrganization(tenantId);
		return orgList;
	}

	@Override
	public List<Code> getCodeListByCodeKey(String codeKey,String groupKey) {
		List<Code>codeList=coreService.queryCodeListByCodeKeyandGroupKey(codeKey,groupKey);
		return codeList;
	}

	@Override
	public void updateUser(User user) {
		coreService.updateUser(user);
		
	}

	@Override
	public List<Organization> getOrgTree() {
		List<Organization> orgList = organizeService.queryAllTenant();
		return orgList;
	}

	@Override
	public List<Organization> getOrgByLevel(String parentId, String organizationLevel) {
		List<Organization> orgList = organizeService
				.queryOrganizationByParentIdAndOrganizeLevel(parentId,
						organizationLevel);
		return orgList;
	}

	@Override
	public List<Resource> getResourcesByUserId(String userId) {
		// TODO Auto-generated method stub
		//return (List<Resource>) authService.getResourceInfoByUserId(userId);
		return null;
	}

	

	*/

}
