package com.cesgroup.prison.login2.service;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.commons.SpringContextUtils;
import com.cesgroup.framework.util.Tools;
import com.cesgroup.prison.common.bean.login2.LoginReqBean;
import com.cesgroup.prison.common.bean.login2.LoginRespBean;
import com.cesgroup.prison.common.bean.login2.LogoutReqBean;
import com.cesgroup.prison.common.bean.login2.LogoutRespBean;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.UserCodeUtil;
import com.cesgroup.prison.common.bean.user.utils.UserLoginManager;
import com.cesgroup.prison.db.service.RedisCache;

/**
 * cesgroup 用户登录服务
 * 
 * @author lihh
 */
@Service
public class LoginServiceImpl implements ILoginService {
	// 登录日志
	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

	/*
	 * 查询服务对象
	 */
	// @Resource
	// private QueryProcess queryProcess;

	/*
	 * 授权登录服务对象
	 */
	@Resource
	private IAuthLoginService authLoginService;

	/**
	 * 本地登录
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param verifyCode
	 *            验证码
	 * @return
	 */
	/*
	 * @Override public LoginRespBean localLogin(LoginReqBean reqBean) { String
	 * userName = reqBean.getUserName(); // 登录用户 String password =
	 * reqBean.getPassword(); // 登录密码
	 * 
	 * LoginRespBean respBean = new LoginRespBean(); // 登录响应实体对象 Map<String,
	 * Object> userMap = null; // 用户信息 UserBean userBean = null; // 用户信息实体对象
	 * String policeNo = null; // 民警编号 String orgaCode = null; // 监狱机构编码 String
	 * dpttCode = null; // 部门编码 String status = null; // 用户状态
	 * 
	 * try { // 验证强制登录和检查用户登录情况 if (compelLogin(reqBean) || checkLogin(reqBean,
	 * respBean)) { userMap = getUserInfo(userName, password); status =
	 * Tools.toStr(userMap.get("STATUS"), "1");
	 * 
	 * // 判断用户状态 if ("1".equals(status)) { orgaCode =
	 * Tools.toStr(userMap.get(" ")); dpttCode =
	 * Tools.toStr(userMap.get("DPTT_CODE")); policeNo =
	 * Tools.toStr(userMap.get("POLICE_NO"));
	 * 
	 * // 用户信息 userBean = new UserBean();
	 * userBean.setUserId(Tools.toStr(userMap.get("USER_ID")));
	 * userBean.setUserName(userName); // userBean.setPassword("");
	 * 
	 * // 民警信息 userBean.setLoginName(userName);
	 * userBean.setRealName(Tools.toStr(userMap.get("REAL_NAME")));
	 * userBean.setPoliceNo(policeNo); userBean.setCusNumber(orgaCode);
	 * userBean.setOrgCode(orgaCode);
	 * userBean.setOrgName(Tools.toStr(userMap.get("ORGA_NAME"))); //
	 * userBean.setDprtmntId(dpttCode); userBean.setDprtmntCode(dpttCode);
	 * userBean.setDprtmntName(Tools.toStr(userMap.get("DPTT_NAME"))); //
	 * userBean.setDepartments(null);
	 * 
	 * // 扩展 setRoleOfAIO(userBean); // userBean.setOrgClassKey("");
	 * userBean.setUserLevel(orgaCode, dpttCode);
	 * 
	 * // 记录登录状态 userBean.setLoginIp(reqBean.getLoginIp());
	 * userBean.setLoginMode(reqBean.getMode()); userBean.setLoginTime(new
	 * Date().getTime());
	 * 
	 * // 设置成功状态 respBean.setRespCode(UserCodeUtil.CODE_0000);
	 * respBean.setUserBean(userBean);
	 * 
	 * // 成功处理 UserLoginManager.login(userBean); } else {
	 * respBean.setRespCode(UserCodeUtil.CODE_1002); // 该用户已被禁用 } } } catch
	 * (UserException e) { respBean.setRespCode(e.getMsgCode());// 系统内部错误
	 * log.error("用户登录获取用户信息错误：", respBean.getRespDesc()); } catch (Exception e)
	 * { respBean.setRespCode(UserCodeUtil.CODE_0002);// 系统内部错误
	 * log.error("用户本地系统登录内部处理错误：", e); }
	 * 
	 * return respBean; }
	 */

	/**
	 * 授权登录
	 * 
	 * @param token
	 *            登录令牌
	 * @param verifyCode
	 *            验证码
	 * @return
	 */
	@Override
	public LoginRespBean authLogin(LoginReqBean reqBean) {
		LoginRespBean respBean = null;
		UserBean userBean = null; // 用户信息对象

		try {
			// 第三方授权登录
			// respBean = authLoginService.login(reqBean);
			respBean = authLoginService.login_new(reqBean);
               // log.error("respBean:"+JSON.toJSONString(respBean)+"userBean:"+JSON.toJSONString(respBean.getUserBean()));
			if (respBean != null) {
				if (respBean.isResult()) {
					userBean = respBean.getUserBean();
					reqBean.setUserName(userBean.getUserName());

					// 如果用户已在其他IP登录
					Object loginIp = RedisCache.getObject(UserLoginManager.LOGIN_CACHE_USERID_IP, userBean.getUserId());
                            //log.error("loginIp:"+String.valueOf(loginIp));
					if (loginIp != null && !"".equals(String.valueOf(loginIp))) {
						// 登出系统
						/*LogoutReqBean reqOutBean = new LogoutReqBean();
						reqOutBean.setUserId(userBean.getUserId());
						reqOutBean.setLogoutIp(String.valueOf(loginIp));
						logout(reqOutBean);*/
						//将其他ip登录强制退出
                                  UserBean userBeanOUt = new UserBean();
                                  userBeanOUt.setLoginIp(String.valueOf(loginIp));
                                  userBeanOUt.setUserName(userBean.getUserName());
                                  userBeanOUt.setUserId(userBean.getUserId());
                                  UserLoginManager.logout(userBeanOUt);
					}

					// 验证强制登录和检查用户登录情况
					//if (compelLogin(reqBean) || checkLogin(reqBean, respBean)) {
						userBean.setLoginMode(reqBean.getMode());
						// setRoleOfAIO(userBean);// 获取二级分控使用权限
						UserLoginManager.login(userBean);// 成功处理
					//}
				}
			} else {
				respBean = new LoginRespBean();
				respBean.setRespCode(UserCodeUtil.CODE_0004); // 授权登录异常
			}
		} catch (Exception e) {
			respBean = new LoginRespBean();
			respBean.setRespCode(UserCodeUtil.CODE_0002); // 系统内部错误
			log.error("授权登录处理异常：", e);
		}

		return respBean;
	}

	/**
	 * 用户登出
	 */
	@Override
	public LogoutRespBean logout(LogoutReqBean reqBean) {
		LogoutRespBean respBean = null; // 用户登出响应对象
		UserBean userBean = null; // 用户信息实体类对象
		String logoutIp = null; // 用户请求登出的IP
		try {
			// 实例化对象
			respBean = new LogoutRespBean();
			logoutIp = Tools.toStr(reqBean.getLogoutIp(), "");
			// userBean =
			// UserLoginManager.getUserByUserName(reqBean.getUserName());
			userBean = UserLoginManager.getUserByUserId(reqBean.getUserId());// .getUserByUserName(reqBean.getUserName());

			// 本机登录用户才能做登出
			if (userBean != null && logoutIp.equals(userBean.getLoginIp())) {
				if (UserLoginManager.logout(userBean)) {
					respBean.setRespCode(UserCodeUtil.CODE_0000);
				} else {
					respBean.setRespCode(UserCodeUtil.CODE_0005);
				}
			} else {
				respBean.setRespCode(UserCodeUtil.CODE_0006);
			}
		} catch (Exception e) {
			respBean.setRespCode(UserCodeUtil.CODE_0002);
			log.error("用户登录异常：", e);
		}

		return respBean;
	}

	/**
	 * 检查用户登录
	 * 
	 * @param reqBean
	 *            登录请求实体对象
	 * @param respBean
	 *            登录响应实体对象
	 * @return
	 */
	private boolean checkLogin(LoginReqBean reqBean, LoginRespBean respBean) {
		UserBean userBean = null; // 用户信息实体对象
		String loginedIp = null; // 登录的用户IP
		String loginIp = reqBean.getLoginIp(); // 请求登录的用户IP
		String userName = reqBean.getUserName(); // 请求登录的用户名
		String verifyCode = reqBean.getVerifyCode(); // 请求登录的用户强制登录验证码

		try {
			// 根据用户民获取用户登录信息
			userBean = UserLoginManager.getUserByUserName(userName);

			// 用户已登录，判断登录地点
			if (userBean != null) {
				loginedIp = UserLoginManager.getLoginIpByUserName(userName);

				// 本机登录，提示用户已登录（暂时去掉）
				if (loginedIp.equals(loginIp)) {
					// respBean.setRespCode(UserCodeUtil.CODE_1003);
					// return false;
					return true;
				} else {
					// 其它地方登录，生成一个验证码（用于用户强制登录验证）
					verifyCode = UserLoginManager.createVerifyCode(loginIp, userName);
					respBean.setVerifyCode(verifyCode);
					respBean.setRespCode(UserCodeUtil.CODE_1005);
					respBean.setRespDesc("用户已在" + userBean.getLoginIp() + "登录");
					return false;
				}
			} else {
				// 本机其它用户登录，提示不能同时登录多个用户（暂时去掉）
				userBean = UserLoginManager.getUserByLoginIp(loginIp);
				if (userBean != null) {
					// respBean.setRespCode(UserCodeUtil.CODE_1004);
					// return false;
					return true;
				}
			}
		} catch (Exception e) {
			respBean.setRespCode(UserCodeUtil.CODE_0002);
			log.error(respBean.getRespDesc() + "：", e);
			return false;
		}
		// 返回结果
		return true;
	}

	/**
	 * 判断是否强制登录
	 * 
	 * @param
	 * @param
	 * @return
	 */
	private boolean compelLogin(LoginReqBean reqBean) {
		String verifyCode = reqBean.getVerifyCode();
          log.error("verifyCode:"+verifyCode);
		String cacheCode = UserLoginManager.getVerifyCode(reqBean.getLoginIp());
		if (Tools.notEmpty(verifyCode)) {
			return verifyCode.equals(cacheCode);
		} else {
			return false;
		}
	}

	/**
	 * 设置分控一体系统使用权限
	 * 
	 * @param
	 *
	 * @param
	 *
	 * @return
	 */
	private void setRoleOfAIO(UserBean userBean) {
		/*
		 * List<Map<String, Object>> result = null; List<Object> args = null;
		 * String useAio = UserConfigUtil.get(UserConfigUtil.DEF_AIO_USE_ROLE);
		 * 
		 * // 设置查询条件 args = new ArrayList<Object>();
		 * args.add(userBean.getOrgCode()); args.add(userBean.getPoliceNo());
		 * 
		 * // 查询并获取结果集 result = queryProcess.process("sys_query_user_aio_role",
		 * args); if (result != null && result.size() > 0){ useAio =
		 * Tools.toStr(result.get(0).get("USER_AIO"), useAio); }
		 * userBean.setUserAio(useAio);
		 */
	}

	@Override
	public LoginRespBean localLogin(LoginReqBean reqBean) {
		LoginRespBean respBean = null;
		UserBean userBean = null; // 用户信息对象

		try {
			// 第三方授权登录
			// respBean = authLoginService.login(reqBean);
			respBean = authLoginService.loginLocal(reqBean);

			if (respBean != null) {
				if (respBean.isResult()) {
					userBean = respBean.getUserBean();
					reqBean.setUserName(userBean.getUserName());

					// 如果用户已在其他IP登录
					Object loginIp = RedisCache.getObject(UserLoginManager.LOGIN_CACHE_USERID_IP, userBean.getUserId());
					if (loginIp != null && !"".equals(String.valueOf(loginIp))) {

						// 登出系统
						LogoutReqBean reqOutBean = new LogoutReqBean();
						reqOutBean.setUserId(userBean.getUserId());
						reqOutBean.setLogoutIp(String.valueOf(loginIp));
						logout(reqOutBean);
					}

					// 验证强制登录和检查用户登录情况
					if (compelLogin(reqBean) || checkLogin(reqBean, respBean)) {
						userBean.setLoginMode(reqBean.getMode());
						// setRoleOfAIO(userBean);// 获取二级分控使用权限
						UserLoginManager.login(userBean);// 成功处理
					}
				}
			} else {
				respBean = new LoginRespBean();
				respBean.setRespCode(UserCodeUtil.CODE_0004); // 授权登录异常
			}
		} catch (Exception e) {
			respBean = new LoginRespBean();
			respBean.setRespCode(UserCodeUtil.CODE_0002); // 系统内部错误
			log.error("授权登录处理异常：", e);
		}

		return respBean;
	}

	private static JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtils.getBean("jdbcTemplate");

	@Override
	public String loginName(String caId) {
		// TODO Auto-generated method stub
		String sqlresource = " select organize_name,login_name from AUTH.T_ORG gu,( "
				+ " select parent_id,login_name from AUTH.T_ORG g,( "
				+ " select organize_id,login_name from AUTH.T_ORG_USER r ,( "
				+ " select user_id,login_name from AUTH.T_USER where ic_no='" + caId + "' "
				+ " ) u where u.user_id = r.user_id " + " ) ouu where g.organize_id = ouu.organize_id "
				+ " ) guu where gu.organize_id = guu.parent_id ";
		List listresource = jdbcTemplate.queryForList(sqlresource);
		String jg = "";
		for (int i = 0; i < listresource.size(); i++) {
			Map map = (Map) listresource.get(i);
			String organize_name = map.get("ORGANIZE_NAME").toString();
			String login_name = map.get("LOGIN_NAME").toString();
			jg = login_name + "|" + organize_name;
			break;
		}

		return jg;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws Exception
	 */
	/*
	 * private Map<String, Object> getUserInfo (String userName, String
	 * password) throws UserException { List<Map<String, Object>> retList =
	 * null; // 查询结果 List<Object> params = null; // 查询参数
	 * 
	 * // 登录查询参数 params = new ArrayList<Object>(); params.add(userName);
	 * params.add(password);
	 * 
	 * // 查询登录用户信息 retList = queryProcess.process("user_query_login_info", "0",
	 * params); if (retList != null && retList.size() > 0) { if (retList.size()
	 * == 1) { return retList.get(0); } throw new
	 * UserException(UserCodeUtil.CODE_1006); // 存在多个用户 } throw new
	 * UserException(UserCodeUtil.CODE_1001); // 用户密码错误 }
	 */
}
