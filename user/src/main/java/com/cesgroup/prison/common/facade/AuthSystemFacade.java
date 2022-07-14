package com.cesgroup.prison.common.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ces.authsystem.entity.OrgEntity;
import com.ces.authsystem.entity.UserEntity;
import com.cesgroup.framework.commons.SpringContextUtils;
import com.cesgroup.framework.util.IpUtil;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.common.bean.user.utils.UserLoginManager;
import com.cesgroup.prison.common.constant.AuthSystemConst;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.prison.plugins.authsystem.utils.FacadeUtil;

import ces.sdk.system.bean.OrgInfo;
import ces.sdk.system.dbbean.DbUserEntity;
import ces.sdk.system.facade.SystemFacadeException;

public class AuthSystemFacade {
	
	private static JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtils.getBean("jdbcTemplate");
	
	/**
	 * 获取当前登录用户信息
	 * @return
	 * @throws Exception
	 */
	public static UserBean getLoginUserInfo() {
		
		// redis缓存信息获取登录用户信息
		/*RedisCache.putHash(LOGIN_CACHE_USERID_IP, userId, loginIp);
		RedisCache.putHash(LOGIN_CACHE_USERNAME_IP, userName, loginIp);
		RedisCache.putHash(LOGIN_CACHE_IP_USERINFO, loginIp, JSON.toJSONString(userBean));*/
		
		// String loginIp = CookieUtil.getRequest().getRemoteAddr();
		try {
			String loginIp = IpUtil.getIpAddress();
			String loginInfo = String.valueOf(RedisCache.getObject(UserLoginManager.LOGIN_CACHE_IP_USERINFO, loginIp));
			UserBean userBean = JSON.toJavaObject(JSONObject.parseObject(loginInfo), UserBean.class);
			
			return userBean;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取当前登陆用户所在的监狱ID号，系统数据库主键，非  orgCode
	 * @return
	 */
	public static String getLoginUserOrgId() {
		try {
			UserBean  userBean = com.cesgroup.prison.common.facade.AuthSystemFacade.getLoginUserInfo();
			if(userBean != null) {
    			return userBean.getOrgId();
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	
	/**
	 * 判断当前用户是否为省局用户
	 * @return
	 * @throws Exception
	 */
	public static boolean isProvLevel() throws Exception {
		UserBean userBean = getLoginUserInfo();
		if(userBean !=null && userBean.getUserLevel().equals(EUserLevel.PROV))
			return true;
		else
			return false;
	}
	
	/**
	 * 获取当前登录用户信息-查询数据库
	 * @return
	 * @throws Exception
	 */
	public static DbUserEntity getLoginUserInfoDatabase(String loginName, String orgKey) throws Exception {
		return FacadeUtil.getUserInfoFacade().getUserInfoByLoginNameAndUnitKey(loginName, orgKey);
	}
	
	/**
	 * 判断当前登录用户数据权限是否省局级、监狱级、监区级（1、省局级  2、监狱级  3、监区级  4、分监区级）（分监区或其他未知级别的统一为监区级）
	 * @return
	 */
	public static int whatLevelForLoginUser() {
		
		try {			
			UserBean userBean = getLoginUserInfo();
			String orgUnitKey = userBean.getCusNumber();
			String orgKey = userBean.getDprtmntCode();
			
			if(orgUnitKey.equals(AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG)) {
				return 1;
			} else if(orgUnitKey.length() == 4 && orgKey.length() == 6 && !orgUnitKey.equals(AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG)) {
				return 2;
			} else if(orgUnitKey.length() == 4 && orgKey.length() == 8 && !orgUnitKey.equals(AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG)) {
				return 3;
			} else if(orgUnitKey.length() == 4 && orgKey.length() == 10 && !orgUnitKey.equals(AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG)) {
				return 3;
			} else {
				return 3;
			}
		} catch (Exception e) {
		}
		
		return 3;	
	}
	
	/**
	 * 获取所有监狱json信息
	 * @return
	 * @throws Exception
	 */
	public static String getAllJyJsonInfo() throws Exception {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		List<OrgEntity> orgList = getAllJyInfo();
		for(OrgEntity org : orgList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", org.getOrgKey());
			map.put("text", org.getOrgName());
			list.add(map);
		}
		
		return JSON.toJSONString(list);
	}

    /**
     * 获取湖南省所有监狱json信息 包括省局
     * @return
     * @throws Exception
     */
    public static String getHnAllJyJsonInfo() throws Exception {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> sjMap = new HashMap<>();
        sjMap.put("value","4300");
        sjMap.put("text","湖南省监狱管理局");
        list.add(sjMap);
        List<OrgEntity> orgList = getAllJyInfo();
        for(OrgEntity org : orgList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("value", org.getOrgKey());
            map.put("text", org.getOrgName());
            list.add(map);
        }

        return JSON.toJSONString(list);
    }
	
	/**
	 * 获取所有监狱信息
	 * @return
	 * @throws Exception
	 */
	public static List<OrgEntity> getAllJyInfo() throws Exception {
		
		List<OrgInfo> orgList = FacadeUtil.getOrgInfoFacade().getOrgInfosByClassifyKey(AuthSystemConst.AUTH_ORG_CLASSIFY_KEY_JY);
		List<OrgEntity> orgEntityList = new ArrayList<OrgEntity>();
		
		if(orgList.size()>0) {
			for(int i = 0; i < orgList.size(); i++) {				
				OrgInfo orgInfo = (OrgInfo)orgList.get(i);
				OrgEntity orgEntity = new OrgEntity();
							
				orgEntity.setOrgId(String.valueOf(orgInfo.getOrganizeID()));		// 组织ID				
				orgEntity.setOrgKey(orgInfo.getOrganizeCode());						// 组织key				
				orgEntity.setOrgName(orgInfo.getOrganizeName());					// 组织名				
				orgEntity.setParentId(String.valueOf(orgInfo.getParentID()));		// 组织父ID				
				orgEntity.setOrgTypeId(orgInfo.getOrganizeTypeID());				// 组织类型				
				orgEntity.setOrgClassify(orgInfo.getOrganizeClassifyID());			// 组织分类
				
				// 是否虚拟组织
				boolean isVirtual = false;
				if(orgInfo.getIsVirtual() != null) {
					if((orgInfo.getIsVirtual()).equals("0")){
						isVirtual = true;
					}else if((orgInfo.getIsVirtual()).equals("1")){
						isVirtual = false;
					}
				}
				
				orgEntity.setVirtualOrg(isVirtual);				
				orgEntity.setComments(orgInfo.getComments());						// 备注				
				orgEntityList.add(orgEntity);
			}
		}
		
		return orgEntityList;
	}
	
	/**
	 * 根据监狱key取该监狱所有监区信息
	 * @param orgKey
	 * @return
	 * @throws Exception
	 */
	public static List<OrgEntity> getAllJqInfoByJyKey(String orgKey) throws Exception{
		
		return getAllOrgInfoByOrgKeyAndOrgClassifyKeys(orgKey, 
									AuthSystemConst.AUTH_ORG_CLASSIFY_KEY_RJJQ, 
									AuthSystemConst.AUTH_ORG_CLASSIFY_KEY_PTJQ,
									AuthSystemConst.AUTH_ORG_CLASSIFY_KEY_GWJQ,
									AuthSystemConst.AUTH_ORG_CLASSIFY_KEY_LBCJQ,
									AuthSystemConst.AUTH_ORG_CLASSIFY_KEY_YZGLK);
	}
	
	/**
	 * 根据监狱key和组织分类key获取组织信息
	 * @param orgKey
	 * @param orgClassifyKey(必传)
	 * @return
	 * @throws Exception
	 */
	public static List<OrgEntity> getAllOrgInfoByOrgKeyAndOrgClassifyKeys(String orgKey, String... orgClassifyKey) throws Exception {
		
		List<OrgInfo> orgList = FacadeUtil.getOrgInfoFacade().getOrgsByKeyAndClassifyKey(orgKey, orgClassifyKey);
		List<OrgEntity> orgEntityList = new ArrayList<OrgEntity>();
		
		if(orgList.size() > 0) {
			for(int i = 0; i < orgList.size(); i++) {
				
				OrgInfo orgInfo=(OrgInfo)orgList.get(i);
				OrgEntity orgEntity=new OrgEntity();
								
				orgEntity.setOrgId(String.valueOf(orgInfo.getOrganizeID()));					// 组织ID			
				orgEntity.setOrgKey(orgInfo.getOrganizeCode());									// 组织key				
				orgEntity.setOrgName(orgInfo.getOrganizeName());								// 组织名				
				orgEntity.setParentId(String.valueOf(orgInfo.getParentID()));					// 组织父ID				
				orgEntity.setOrgTypeId(orgInfo.getOrganizeTypeID());							// 组织类型				
				orgEntity.setOrgClassify(orgInfo.getOrganizeClassifyID());						// 组织分类
				
				//是否虚拟组织
				boolean isVirtual = false;
				if(orgInfo.getIsVirtual() != null) {
					if((orgInfo.getIsVirtual()).equals("0")) {
						isVirtual = true;
					} else if((orgInfo.getIsVirtual()).equals("1")) {
						isVirtual = false;
					}
				}
				orgEntity.setVirtualOrg(isVirtual);
				
				orgEntity.setComments(orgInfo.getComments());									// 备注				
				orgEntityList.add(orgEntity);
			}
		}
		
		return orgEntityList;
	}
	
	/**
	 * 根据组织key获取所有子组织-如单位下所有部门
	 * @param orgKey
	 * @return
	 * @throws Exception 
	 */
	public static String getAllChildrenOrgJsonInfoByOrgKey(String orgKey) throws Exception {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();	
		
		if(orgKey == null || "".equals(orgKey)) {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			orgKey = user.getOrgCode();
		}
		
		List<OrgInfo> orgList = FacadeUtil.getOrgInfoFacade().getAllChildrenByParentKey(orgKey);
		
		if(orgList != null) {
			for(int i = 0; i < orgList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				OrgInfo orgInfo = (OrgInfo) orgList.get(i);
				
				map.put("value", orgInfo.getOrganizeCode());
				map.put("text", orgInfo.getOrganizeName());
				list.add(map);	
			}
		}
		
		return JSON.toJSONString(list);
	}
	
	/**
	 * 根据组织key获取所有子组织-如单位下所有部门
	 * @param orgKey
	 * @return
	 * @throws Exception 
	 */
	public static List<OrgEntity> getAllChildrenOrgInfoByOrgKey(String orgKey) throws Exception {
		
		List<OrgInfo> orgList = FacadeUtil.getOrgInfoFacade().getAllChildrenByParentKey(orgKey);
		List<OrgEntity> orgEntityList = new ArrayList<OrgEntity>();
		
		if(orgList.size() > 0) {
			for(int i = 0; i < orgList.size(); i++) {
				
				OrgInfo orgInfo=(OrgInfo)orgList.get(i);
				OrgEntity orgEntity=new OrgEntity();
								
				orgEntity.setOrgId(String.valueOf(orgInfo.getOrganizeID()));					// 组织ID			
				orgEntity.setOrgKey(orgInfo.getOrganizeCode());									// 组织key				
				orgEntity.setOrgName(orgInfo.getOrganizeName());								// 组织名				
				orgEntity.setParentId(String.valueOf(orgInfo.getParentID()));					// 组织父ID				
				orgEntity.setOrgTypeId(orgInfo.getOrganizeTypeID());							// 组织类型				
				orgEntity.setOrgClassify(orgInfo.getOrganizeClassifyID());						// 组织分类
				
				//是否虚拟组织
				boolean isVirtual = false;
				if(orgInfo.getIsVirtual() != null) {
					if((orgInfo.getIsVirtual()).equals("0")) {
						isVirtual = true;
					} else if((orgInfo.getIsVirtual()).equals("1")) {
						isVirtual = false;
					}
				}
				orgEntity.setVirtualOrg(isVirtual);
				
				orgEntity.setComments(orgInfo.getComments());									// 备注				
				orgEntityList.add(orgEntity);
			}
		}
		
		return orgEntityList;
	}
	
	
	/**
	 * 根据用户id获取用户信息
	 * @param userId
	 * @return
	 * @throws SystemFacadeException
	 */
	public static UserEntity getUserInfoByUserId(String userId) throws SystemFacadeException {
		
		UserEntity userEntity = FacadeUtil.getUserInfoFacade().getUserInfo_af(Integer.parseInt(userId));		
		return userEntity;
	}
	
	/**
	 * 获取某单位下某些角色的所有用户信息
	 * @param unitKey
	 * @param resourceKey
	 * @return
	 * @throws SystemFacadeException
	 */
	public static List<UserEntity> getAllUserInfoByUnitKeyAndResourceKey(String unitKey, String resourceKey) throws SystemFacadeException {

		List<UserEntity> userList = new ArrayList<UserEntity>();
		String allUser = ",";
		
		//SDK完成后替换方法============================================start//
		String sqlresource = "select t.resource_id from auth.t_resource t where t.url = '" + resourceKey + "'";
		List listresource = jdbcTemplate.queryForList(sqlresource);
		
		if(listresource.size() > 0) {
			
			String resourceId = ((Map) listresource.get(0)).get("RESOURCE_ID").toString();
			String sqlres = "select t.role_id from auth.t_role_res t where t.resource_id = " + resourceId;
			List list = jdbcTemplate.queryForList(sqlres);
			
			if(list.size() > 0) {
				for(int i = 0; i < list.size(); i++) {				
					Map map = (Map) list.get(i);
					String roleId = map.get("ROLE_ID").toString();
					String sqluser = "select t.user_id from auth.t_role_user t where t.role_id = " + roleId;
					List userEntityList = jdbcTemplate.queryForList(sqluser);
					
					if(userEntityList.size() > 0) {
						for(int u = 0; u < userEntityList.size(); u++) {
							Map mapUser = (Map)userEntityList.get(u);
							String userId = mapUser.get("USER_ID").toString();
							UserEntity us = getUserInfoByUserId(userId);
							if(us.getOrgUnitKey()!=null && !(us.getUserId()).equals("0") && (us.getOrgUnitKey()).equals(unitKey)
									&& allUser.indexOf(","+userId+",") == -1) {
								
								int level = whatLevelForLoginUser();
								UserBean use = new UserBean();
								try {
									use = getLoginUserInfo();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								if(level == 3) {
									if((use.getDprtmntCode()).equals(us.getOrgKey())) {
										userList.add(us);
										allUser += us.getUserId() + ",";
									}
								} else {
									userList.add(us);
									allUser += us.getUserId() + ",";
								}
							}
						}
					}
				}	
			}
		}
		//SDK完成后替换方法============================================end//
		
		return userList;
	}
	
	/**
	 * 获取某组织下的某些角色的所有用户（无用户控制，传入组织key）
	 * @param orgKey
	 * @param resourceKey
	 * @return
	 * @throws SystemFacadeException
	 */
	public static List<UserEntity> getAllUserInfoByOrgKeyAndResourceKeyForDSRW(String orgKey, String resourceKey) throws SystemFacadeException {
		
		List<UserEntity> userList = new ArrayList<UserEntity>();
		String allUser = ",";
		
		//SDK完成后替换方法============================================start//
		String sqlresource = "select t.resource_id from auth.t_resource t where t.url = '" + resourceKey + "'";
		List listresource = jdbcTemplate.queryForList(sqlresource);
		
		if(listresource.size() > 0) {
			String resourceId = ((Map) listresource.get(0)).get("RESOURCE_ID").toString();
			String sqlres = "select t.role_id from auth.t_role_res t where t.resource_id = " + resourceId;
			List list = jdbcTemplate.queryForList(sqlres);
			if(list.size() > 0) {
				for(int i = 0; i < list.size(); i++) {
					Map map = (Map)list.get(i);
					String roleId = map.get("ROLE_ID").toString();
					String sqluser = "select t.user_id from auth.t_role_user t where t.role_id = " + roleId;
					List userEntityList = jdbcTemplate.queryForList(sqluser);
					if(userEntityList.size() > 0) {
						for(int u = 0; u < userEntityList.size(); u++) {
							Map mapUser = (Map) userEntityList.get(u);
							String userId = mapUser.get("USER_ID").toString();
							UserEntity us = getUserInfoByUserId(userId);
							if(us.getOrgUnitKey() != null && !(us.getUserId()).equals("0") && (us.getOrgKey()).equals(orgKey) 
									&& allUser.indexOf(","+userId+",") == -1) {
								userList.add(us);
								allUser += us.getUserId() + ",";
							}
						}
					}
				}	
			}
		}
		//SDK完成后替换方法============================================end//
		
		return userList;
	}
	
	/**
	 * 获取某组织下的所有用户（无用户控制，传入组织key）
	 * @param orgKey
	 * @param
	 * @return
	 * @throws SystemFacadeException
	 */
	public static List<UserEntity> getAllUserInfoByOrgKey(String orgKey) throws SystemFacadeException {
		
		List<UserEntity> userList = new ArrayList<UserEntity>();
		String allUser = ",";
		
		//SDK完成后替换方法============================================start//		 
		String sqluser = "select a.user_id from auth.t_org_user a, auth.t_org b where a.organize_id = b.organize_id and b.organize_code = '" + orgKey + "'";
		List userEntityList = jdbcTemplate.queryForList(sqluser);
		if(userEntityList.size() > 0) {
			for(int u = 0; u < userEntityList.size(); u++) {
				Map mapUser = (Map) userEntityList.get(u);
				String userId = mapUser.get("USER_ID").toString();
				UserEntity us = getUserInfoByUserId(userId);
				if(us.getOrgUnitKey() != null && !(us.getUserId()).equals("0") && (us.getOrgKey()).equals(orgKey) 
						&& allUser.indexOf(","+userId+",") == -1) {
					userList.add(us);
					allUser += us.getUserId() + ",";
				}
			}
		}			 
		//SDK完成后替换方法============================================end//
		
		return userList;
	}
	
	/**
	 * 根据机构名称查询机构信息
	 * @param orgName
	 * @return
	 */
	public static OrgInfo getOrgByOrgName(String orgName) {
		try {
			OrgInfo org = FacadeUtil.getOrgInfoFacade().getOrgByOrgName(orgName);
			return org;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据机构名称查询机构信息
	 * @param orgName
	 * @return
	 */
	public static List<OrgEntity> getAllChildrenOrgInfoByOrgName(String orgName) {
		List<OrgEntity> orgEntityList = null;
		try {
			//OrgInfo org = FacadeUtil.getOrgInfoFacade().getOrgByOrgName(orgName);
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String code = user.getOrgCode().substring(0, 4) + "00";
			orgEntityList = AuthSystemFacade.getAllChildrenOrgInfoByOrgKey(code);
		} catch (Exception e) {
			return null;
		}
		return orgEntityList;
	}

	/**
	 * 根据机构编码查询机构信息
	 * @param orgCode
	 * @return
	 */
	public static OrgInfo getOrgByOrgCode(String orgCode) throws Exception {
		OrgInfo org = FacadeUtil.getOrgInfoFacade().getOrgInfoByKey(orgCode);
		return org;
	}

	/**
	 * 根据单位编码，获取其下属的市局、分局、监狱等机构编码
	 * @return
	 * @throws Exception
	 */
	public static List<String> getSjFjJyOrgKeyListByCusNumber(String cusNumber) throws Exception {
		List<String> list = new ArrayList<String>();

		List<OrgEntity> orgList = getAllOrgInfoByOrgKeyAndOrgClassifyKeys(cusNumber, AuthSystemConst.AUTH_ORG_CLASSIFY_KEY_JSSJYGLG, AuthSystemConst.AUTH_ORG_CLASSIFY_KEY_FJ, AuthSystemConst.AUTH_ORG_CLASSIFY_KEY_JY);
		for(OrgEntity org : orgList) {
			list.add(org.getOrgKey());
		}

		return list;
	}

	/**
	 * 根据登录用户信息，获取其可见的市局、分局、监狱等机构列表
	 * @return
	 * @throws Exception
	 */
	public static String getSjFjJyJsonInfoByLoginUser() throws Exception {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		if (user == null) {
			return null;
		}
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		List<OrgEntity> orgList = getAllOrgInfoByOrgKeyAndOrgClassifyKeys(user.getCusNumber(), AuthSystemConst.AUTH_ORG_CLASSIFY_KEY_JSSJYGLG, AuthSystemConst.AUTH_ORG_CLASSIFY_KEY_FJ, AuthSystemConst.AUTH_ORG_CLASSIFY_KEY_JY);
		for(OrgEntity org : orgList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", org.getOrgKey());
			map.put("text", org.getOrgName());
			list.add(map);
		}

		return JSON.toJSONString(list);
	}

	/**
	 * 根据机构编码查询机构所属部门信息
	 * @param orgCode
	 * @return
	 */
	public static OrgInfo getUnitOrgInfoByOrgCode(String orgCode) throws Exception {
		OrgInfo org = FacadeUtil.getOrgInfoFacade().getUnitOrgInfoByorgCode(orgCode);
		return org;
	}

}
