package com.cesgroup.prison.login2.service;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.ces.authsystem.entity.OrgEntity;
import com.ces.authsystem.entity.RoleEntity;
import com.ces.authsystem.entity.UserEntity;
import com.ces.authsystem.server.facade.AuthSystemFacade;
import com.ces.authsystem.util.FacadeUtil;
import com.ces.utils.TokenUtils;
import com.cesgroup.framework.commons.SpringContextUtils;
import com.cesgroup.framework.util.Tools;
import com.cesgroup.prison.common.bean.login2.LoginReqBean;
import com.cesgroup.prison.common.bean.login2.LoginRespBean;
import com.cesgroup.prison.common.bean.user.RoleBean;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.UserCodeUtil;

import ces.sdk.system.bean.OrgInfo;
import ces.sdk.system.bean.RoleInfo;
import ces.sdk.system.facade.RoleInfoFacade;
import ces.sdk.system.factory.SystemFacadeFactory;

/**
 * cesgroup
 * 系统管理平台用户登录服务
 * @author lihh
 *
 */
@Service
public class AuthLoginServiceImpl implements IAuthLoginService {

	// 登录日志
	private static final Logger log = LoggerFactory.getLogger(AuthLoginServiceImpl.class);

	private static JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtils.getBean("jdbcTemplate");
	/*
	 * 服务类对象
	 */
	//@Resource
	//private QueryProcess queryProcess;

	//private IAuthExtendService authExtendService;

	/**
	 * 授权登录
	 * @param reqBean 请求实体类对象
	 * @return
	 */
	public LoginRespBean login(LoginReqBean reqBean) {
		LoginRespBean respBean = new LoginRespBean();	// 登录响应实体类对象
		List<RoleEntity> roles = null;	// 系统管理平台的用户信息对象集合
		UserEntity userEntity = null;	// 系统管理平台的用户信息对象
		OrgEntity orgEntity = null;		// 系统管理平台的机构信息对象
		UserBean userBean = null;		// 用户信息对象
		RoleBean roleBean = null;		// 用户角色对象

		String[] tokenInfo = null;		// 令牌解密后存储的信息
		Integer userId = null;			// 用户ID/登录ID
		String userName = null;			// 用户名/登录名
		String policeNo = null;			// 民警警号
		String orgaCode = null;			// 监狱机构编码
		String orgaName = null;			// 监狱机构名称
		String dpttCode = null;			// 部门编码
		String isSpecialPolice = null;	// 是否特警队员

		try {
			try {
				// 解析token令牌
				/*tokenInfo = TokenDUtils.decodeTokenFromUrl(reqBean.getToken());*/
				
				//编码前的结构
				/*tokenInfo = new String[] {reqBean.getToken()};
				userName = tokenInfo[0].split("\\|")[0];
				orgaName = tokenInfo[0].split("\\|")[1];*/
				
				tokenInfo = TokenUtils.decodeToken(reqBean.getToken());
				userName = tokenInfo[0].split("\\|")[0];
				orgaName = tokenInfo[0].split("\\|")[1];
				
				log.debug("用户令牌解析成功: " + JSON.toJSONString(tokenInfo));
			} catch (Exception ex) {
				//respBean.setRespCode(UserCodeUtil.CODE_2001);
				//respBean.setRespDesc(ex.getMessage());
				//log.error(ex.getMessage());
				return respBean;
			}

			// 获取登录令牌中的用户信息
			userEntity = AuthSystemFacade.getUserInfoByLoginNameAndUnitName(userName, orgaName);
			if (userEntity != null && Tools.notEmpty(userEntity.getLoginName())) {

				// 系统管理平台状态等于1表示该用户已删除
				if (!"1".equals(userEntity.getStatus())) {
					userId = userEntity.getUserId();
					userName = userEntity.getLoginName();
					policeNo = userEntity.getJobNo();
					orgaCode = userEntity.getOrgUnitKey();
					dpttCode = userEntity.getOrgKey();
					orgEntity = AuthSystemFacade.getOrgEntityByKey(dpttCode);				// 获取组织机构

					// 检查用户信息
					//checkUser(userName);

					// 检查组织机构信息
					if (orgEntity != null) {
						// 设置用户实体对象参数
						userBean = new UserBean();
						userBean.setUserId(Tools.toStr(userId));
						userBean.setUserName(userName);
//						userBean.setPassword("");

						// 民警信息
						userBean.setLoginName(userName);
						userBean.setRealName(userEntity.getUserName());
						userBean.setPoliceNo(policeNo);
						userBean.setCusNumber(orgaCode);
						userBean.setOrgCode(orgaCode);
						userBean.setOrgName(orgaName);
//						userBean.setDprtmntId(orgEntity.getOrgId());
						userBean.setDprtmntCode(dpttCode);
						userBean.setDprtmntName(userEntity.getOrgName());
//						userBean.setDepartments(null);

						// 获取用户角色信息
						roles = AuthSystemFacade.getRolesByUserIdAndUnitKey(Long.valueOf(userId), orgaCode);
						if (roles != null && roles.size() > 0) {
							for (RoleEntity roleEntity : roles) {
								roleBean = new RoleBean();
								roleBean.setId(roleEntity.getRoleId());
								roleBean.setCode(roleEntity.getRoleKey());
								roleBean.setName(roleEntity.getRoleName());
								userBean.getRoles().add(roleBean);
							}

							// 扩展
							isSpecialPolice = AuthSystemFacade.getIsSpecialPoliceByUserID(userId);	// 是否特警队员
//							userBean.setUserAio(0);	// 在前面设置
							userBean.setOrgClassKey(orgEntity.getOrganizeTypeKey());
							userBean.setUserLevel(orgaCode, dpttCode);
							userBean.setIsSpecialPolice(isSpecialPolice);

							// 记录登录状态
							userBean.setLoginIp(reqBean.getLoginIp());
							userBean.setLoginMode(reqBean.getMode());
							userBean.setLoginTime(new Date().getTime());

							// 设置成功状态
							respBean.setRespCode(UserCodeUtil.CODE_0000);
							respBean.setUserBean(userBean);
						} else {
							respBean.setRespCode(UserCodeUtil.CODE_2005);
							respBean.setRespDesc("用户无权限或未授权");
                                        log.error("用户无权限或未授权");
						}

					} else {
						respBean.setRespCode(UserCodeUtil.CODE_2004);
						//respBean.setRespDesc("获取用户组织机构失败");
					}

				} else {
					respBean.setRespCode(UserCodeUtil.CODE_2003);
					//respBean.setRespDesc("用户信息已被删除（" + orgaName + " | " + userName + "）");
				}

			} else {
				respBean.setRespCode(UserCodeUtil.CODE_2002);
				//respBean.setRespDesc("获取用户信息失败（" + orgaName + " | " + userName + "）");
			}
		} catch (Exception e) {
			respBean.setRespCode(UserCodeUtil.CODE_0002);
			e.printStackTrace();
			log.error("系统管理平台用户登录异常：", e);
		}

		return respBean;
	}
	
	
	/**
	 * 重新开发的用户登录授权，切换成与大平台相同的SDK
	 * modified by lihh  at 2018-3-14 
	 * 授权登录
	 * @param reqBean 请求实体类对象
	 * @return
	 */
	public LoginRespBean login_new(LoginReqBean reqBean) {
		LoginRespBean respBean = new LoginRespBean();	// 登录响应实体类对象
		List<RoleEntity> roles = null;	// 系统管理平台的用户信息对象集合
		UserEntity userEntity = null;	// 系统管理平台的用户信息对象
		OrgEntity orgEntity = null;		// 系统管理平台的机构信息对象
		UserBean userBean = null;		// 用户信息对象
		RoleBean roleBean = null;		// 用户角色对象

		String[] tokenInfo = null;		// 令牌解密后存储的信息
		Integer userId = null;			// 用户ID/登录ID
		String userName = null;			// 用户名/登录名
		String policeNo = null;			// 民警警号
		String orgaCode = null;			// 监狱机构编码
		String orgaName = null;			// 监狱机构名称
		String dpttCode = null;			// 部门编码
		String isSpecialPolice = null;	// 是否特警队员

		try {
			try {
				// 解析token令牌
				/*tokenInfo = TokenDUtils.decodeTokenFromUrl(reqBean.getToken());*/
				
				//编码前的结构
				/*tokenInfo = new String[] {reqBean.getToken()};
				userName = tokenInfo[0].split("\\|")[0];
				orgaName = tokenInfo[0].split("\\|")[1];*/
				
				tokenInfo = TokenUtils.decodeToken(reqBean.getToken());
				userName = tokenInfo[0].split("\\|")[0];
				orgaName = tokenInfo[0].split("\\|")[1];
				
				log.debug("用户令牌解析成功: " + JSON.toJSONString(tokenInfo));
			} catch (Exception ex) {
				//respBean.setRespCode(UserCodeUtil.CODE_2001);
				//respBean.setRespDesc(ex.getMessage());
				//log.error(ex.getMessage());
				return respBean;
			}

			// 获取登录令牌中的用户信息
			userEntity = AuthSystemFacade.getUserInfoByLoginNameAndUnitName(userName, orgaName);
			
			
			if (userEntity != null && Tools.notEmpty(userEntity.getLoginName())) {

				// 系统管理平台状态等于1表示该用户已删除
				if (!"1".equals(userEntity.getStatus())) {
					userId = userEntity.getUserId();
					userName = userEntity.getLoginName();
					policeNo = userEntity.getJobNo();
					orgaCode = userEntity.getOrgUnitKey();
					dpttCode = userEntity.getOrgKey();
					orgEntity = AuthSystemFacade.getOrgEntityByKey(dpttCode);				// 获取当前所在部门组织机构
					OrgEntity unitEntity = AuthSystemFacade.getOrgEntityByKey(orgaCode);//当前监狱实体 added by lihh at 2018-3-14
					// 检查用户信息
					//checkUser(userName);
                            log.error("unitEntity: " + unitEntity.getOrgName()+"Orgid:"+unitEntity.getOrgId());
					// 检查组织机构信息
					if (orgEntity != null) {
						// 设置用户实体对象参数
						userBean = new UserBean();
						userBean.setUserId(Tools.toStr(userId));
						userBean.setUserName(userName);
//						userBean.setPassword("");

						// 民警信息
						userBean.setLoginName(userName);
						userBean.setRealName(userEntity.getUserName());
						userBean.setPoliceNo(policeNo);
						userBean.setCusNumber(orgaCode);
						//added by lihh at 2018-3-14
						userBean.setOrgId(unitEntity.getOrgId());//新增单位ID ，用于获取用户权限时使用
						//end
						userBean.setOrgCode(orgaCode);
						userBean.setOrgName(orgaName);
//						userBean.setDprtmntId(orgEntity.getOrgId());
						userBean.setDprtmntCode(dpttCode);
						userBean.setDprtmntName(userEntity.getOrgName());
//						userBean.setDepartments(null);

						// 获取用户角色信息
						//roles = AuthSystemFacade.getRolesByUserIdAndUnitKey(Long.valueOf(userId), orgaCode);//该方法只能获取差异性权限
						RoleInfoFacade roleFacade =  SystemFacadeFactory.newInstance().createRoleInfoFacade();
						List<RoleInfo> roleList = null;
						//OrgInfo orgInfo  = FacadeUtil.getOrgInfoFacade().getOrgByOrgName(orgaName);
						roleList = roleFacade.getRolesByUserIdAndUnitId(Long.valueOf(userId), Long.parseLong(unitEntity.getOrgId()));//根据用户ID 和单位ID ,获取该用户在该单位下的所有角色集合)

						if (roleList != null && roleList.size() > 0) {
							for (RoleInfo roleInfo : roleList) {
								roleBean = new RoleBean();
								roleBean.setId(roleInfo.getRoleID()+"");
								roleBean.setCode(roleInfo.getKey());
								roleBean.setName(roleInfo.getRoleName());
								userBean.getRoles().add(roleBean);
							}

							// 扩展
							//isSpecialPolice = AuthSystemFacade.getIsSpecialPoliceByUserID(userId);	// 是否特警队员
//							userBean.setUserAio(0);	// 在前面设置
							userBean.setOrgClassKey(orgEntity.getOrganizeTypeKey());
							userBean.setUserLevel(orgaCode, dpttCode);
							userBean.setIsSpecialPolice(isSpecialPolice);

							// 记录登录状态
							userBean.setLoginIp(reqBean.getLoginIp());
							userBean.setLoginMode(reqBean.getMode());
							userBean.setLoginTime(new Date().getTime());

							// 设置成功状态
							respBean.setRespCode(UserCodeUtil.CODE_0000);
							respBean.setUserBean(userBean);
						} else {
							respBean.setRespCode(UserCodeUtil.CODE_2005);
							respBean.setRespDesc("用户无权限或未授权");
                                       log.error("用户无权限或未授权");
						}

					} else {
						respBean.setRespCode(UserCodeUtil.CODE_2004);
						//respBean.setRespDesc("获取用户组织机构失败");
					}

				} else {
					respBean.setRespCode(UserCodeUtil.CODE_2003);
					//respBean.setRespDesc("用户信息已被删除（" + orgaName + " | " + userName + "）");
				}

			} else {
				respBean.setRespCode(UserCodeUtil.CODE_2002);
				//respBean.setRespDesc("获取用户信息失败（" + orgaName + " | " + userName + "）");
			}
		} catch (Exception e) {
			respBean.setRespCode(UserCodeUtil.CODE_0002);
			e.printStackTrace();
			log.error("系统管理平台用户登录异常：", e);
		}

		return respBean;
	}
	/**
	 * 
	 *本地登录
	 * @param reqBean 请求实体类对象
	 * @return
	 */
	public LoginRespBean loginLocal(LoginReqBean reqBean) {
		LoginRespBean respBean = new LoginRespBean();	// 登录响应实体类对象
		List<RoleEntity> roles = null;	// 系统管理平台的用户信息对象集合
		UserEntity userEntity = null;	// 系统管理平台的用户信息对象
		OrgEntity orgEntity = null;		// 系统管理平台的机构信息对象
		UserBean userBean = null;		// 用户信息对象
		RoleBean roleBean = null;		// 用户角色对象

		String[] tokenInfo = null;		// 令牌解密后存储的信息
		Integer userId = null;			// 用户ID/登录ID
		String userName = null;			// 用户名/登录名
		String policeNo = null;			// 民警警号
		String orgaCode = null;			// 监狱机构编码
		String orgaName = null;			// 监狱机构名称
		String dpttCode = null;			// 部门编码
		String isSpecialPolice = null;	// 是否特警队员

		try {
			try {
				userName =reqBean.getUserName();
				String sql="select T.USER_NAME ,T.ORGANIZE_NAME from(select U.LOGIN_NAME,U.PASSWORD,U.USER_ID, R.ORGANIZE_ID,USER_NAME ,R.ORGANIZE_NAME from AUTH.T_USER U ,AUTH.T_ORG_USER O ,AUTH.T_ORG R where U.USER_ID=O.USER_ID and R.ORGANIZE_ID=O.BINDORGID) T where T.LOGIN_NAME='"+userName+"' and T.PASSWORD=MD5('"+reqBean.getPassword()+"')";
				List list= jdbcTemplate.queryForList(sql);
				 Map map = (Map)list.get(0);
				 orgaName =map.get("ORGANIZE_NAME").toString();
						} catch (Exception ex) {
				//respBean.setRespCode(UserCodeUtil.CODE_2001);
				//respBean.setRespDesc(ex.getMessage());
				//log.error(ex.getMessage());
				return respBean;
			}

			// 获取登录令牌中的用户信息
			userEntity = AuthSystemFacade.getUserInfoByLoginNameAndUnitName(userName, orgaName);
			
			
			if (userEntity != null && Tools.notEmpty(userEntity.getLoginName())) {

				// 系统管理平台状态等于1表示该用户已删除
				if (!"1".equals(userEntity.getStatus())) {
					userId = userEntity.getUserId();
					userName = userEntity.getLoginName();
					policeNo = userEntity.getJobNo();
					orgaCode = userEntity.getOrgUnitKey();
					dpttCode = userEntity.getOrgKey();
					orgEntity = AuthSystemFacade.getOrgEntityByKey(dpttCode);				// 获取当前所在部门组织机构
					OrgEntity unitEntity = AuthSystemFacade.getOrgEntityByKey(orgaCode);//当前监狱实体 added by lihh at 2018-3-14
					// 检查用户信息
					//checkUser(userName);

					// 检查组织机构信息
					if (orgEntity != null) {
						// 设置用户实体对象参数
						userBean = new UserBean();
						userBean.setUserId(Tools.toStr(userId));
						userBean.setUserName(userName);
//						userBean.setPassword("");

						// 民警信息
						userBean.setLoginName(userName);
						userBean.setRealName(userEntity.getUserName());
						userBean.setPoliceNo(policeNo);
						userBean.setCusNumber(orgaCode);
						//added by lihh at 2018-3-14
						userBean.setOrgId(unitEntity.getOrgId());//新增单位ID ，用于获取用户权限时使用
						//end
						userBean.setOrgCode(orgaCode);
						userBean.setOrgName(orgaName);
//						userBean.setDprtmntId(orgEntity.getOrgId());
						userBean.setDprtmntCode(dpttCode);
						userBean.setDprtmntName(userEntity.getOrgName());
//						userBean.setDepartments(null);

						// 获取用户角色信息
						//roles = AuthSystemFacade.getRolesByUserIdAndUnitKey(Long.valueOf(userId), orgaCode);//该方法只能获取差异性权限
						RoleInfoFacade roleFacade =  SystemFacadeFactory.newInstance().createRoleInfoFacade();
						
						List<RoleInfo> roleList = null;
						//OrgInfo orgInfo  = FacadeUtil.getOrgInfoFacade().getOrgByOrgName(orgaName);
						roleList = roleFacade.getRolesByUserIdAndUnitId(Long.valueOf(userId), Long.parseLong(unitEntity.getOrgId()));//根据用户ID 和单位ID ,获取该用户在该单位下的所有角色集合)
						
						if (roleList != null && roleList.size() > 0) {
							for (RoleInfo roleInfo : roleList) {
								roleBean = new RoleBean();
								roleBean.setId(roleInfo.getRoleID()+"");
								roleBean.setCode(roleInfo.getKey());
								roleBean.setName(roleInfo.getRoleName());
								userBean.getRoles().add(roleBean);
							}

							// 扩展
							//isSpecialPolice = AuthSystemFacade.getIsSpecialPoliceByUserID(userId);	// 是否特警队员
//							userBean.setUserAio(0);	// 在前面设置
							userBean.setOrgClassKey(orgEntity.getOrganizeTypeKey());
							userBean.setUserLevel(orgaCode, dpttCode);
							userBean.setIsSpecialPolice(isSpecialPolice);

							// 记录登录状态
							userBean.setLoginIp(reqBean.getLoginIp());
							userBean.setLoginMode(reqBean.getMode());
							userBean.setLoginTime(new Date().getTime());

							// 设置成功状态
							respBean.setRespCode(UserCodeUtil.CODE_0000);
							respBean.setUserBean(userBean);
						} else {
							respBean.setRespCode(UserCodeUtil.CODE_2005);
							respBean.setRespDesc("用户无权限或未授权");
						}

					} else {
						respBean.setRespCode(UserCodeUtil.CODE_2004);
						//respBean.setRespDesc("获取用户组织机构失败");
					}

				} else {
					respBean.setRespCode(UserCodeUtil.CODE_2003);
					//respBean.setRespDesc("用户信息已被删除（" + orgaName + " | " + userName + "）");
				}

			} else {
				respBean.setRespCode(UserCodeUtil.CODE_2002);
				//respBean.setRespDesc("获取用户信息失败（" + orgaName + " | " + userName + "）");
			}
		} catch (Exception e) {
			respBean.setRespCode(UserCodeUtil.CODE_0002);
			e.printStackTrace();
			log.error("系统管理平台用户登录异常：", e);
		}

		return respBean;
	}

	/**
	 * 检查用户是否存在
	 * @param userName
	 */
	private void checkUser (String userName) throws Exception {
		/*
		 * 检查用户是否在本地系统存在
		 */
		/*List<Map<String, Object>> result = null;
		List<Object> args = null;
		Integer count = 0;

		args = new ArrayList<Object>();
		args.add(userName);

		result = queryProcess.process("sys_query_check_user_exist", "0", args);
		count = Tools.toInt( result.get(0).get("COUNT") );

		if (count == 0) {
			// TODO: 用户未同步，是否需要做用户同步处理
			boolean syncFlag = authExtendService.syncUserInfo(userName);
			if (!syncFlag) {
				throw new UserException(UserCodeUtil.CODE_0012);
			}
		}

		if (count > 1) {
			// TODO: 存在多个相同用户，这里如何处理比较合适
			throw new UserException(UserCodeUtil.CODE_0013);
		}*/
	}
}
