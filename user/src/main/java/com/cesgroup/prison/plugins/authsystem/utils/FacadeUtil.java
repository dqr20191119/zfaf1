/**
 * <p>Copyright:Copyright(c) 2013</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.ces.xarch.plugins.authsystem.utils</p>
 * <p>文件名:BeanUtils.java</p>
 * <p>类更新历史信息</p>
 * @todo Administrator 创建于 2013-07-30 19:32:31
 */
package com.cesgroup.prison.plugins.authsystem.utils;

import java.sql.Connection;
import java.sql.SQLException;

import ces.sdk.sdk.db.SdkDataSource;
import ces.sdk.system.facade.EtsCodeFacade;
import ces.sdk.system.facade.OpLogInfoFacade;
import ces.sdk.system.facade.OrgInfoFacade;
import ces.sdk.system.facade.ResourceInfoFacade;
import ces.sdk.system.facade.RoleInfoFacade;
import ces.sdk.system.facade.SystemFacadeContext;
import ces.sdk.system.facade.UserInfoFacade;
import ces.sdk.system.factory.SystemFacadeFactory;

/**
 * .
 * <p>描述:获取接口对象</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * @author Administrator
 * @date 2013-08-22  13:17:43
 * @version 1.0.2013.0822
 */
public class FacadeUtil {
	
	/**
	 * .
	 * <p>描述:获取用户接口</p>
	 * @return
	 * @author Administrator
	 * @date 2013-08-22  13:17:39
	 */
	public static UserInfoFacade getUserInfoFacade(){
		SystemFacadeFactory factory =SystemFacadeFactory.newInstance();
		SystemFacadeContext sfContext = factory.createSystemFacadeContext();
		factory.initSystemFacadeContext(sfContext);
		UserInfoFacade  userfacade =factory.createUserInfoFacade();
		return userfacade;
	}
	
	/**
	 * .
	 * <p>描述:获取角色接口</p>
	 * @return
	 * @author Administrator
	 * @date 2013-08-22  13:17:35
	 */
	public static RoleInfoFacade getRoleInfoFacade(){
		SystemFacadeFactory factory=SystemFacadeFactory.newInstance();
		SystemFacadeContext sfContext=factory.createSystemFacadeContext();
		factory.initSystemFacadeContext(sfContext);
		RoleInfoFacade roleInfoFacade=factory.createRoleInfoFacade();
		return roleInfoFacade;		
	}
	
	/**
	 * .
	 * <p>描述:获取组织接口</p>
	 * @return
	 * @author Administrator
	 * @date 2013-08-22  13:17:32
	 */
	public static OrgInfoFacade getOrgInfoFacade(){
		SystemFacadeFactory factory=SystemFacadeFactory.newInstance();
		SystemFacadeContext sfContext=factory.createSystemFacadeContext();
		factory.initSystemFacadeContext(sfContext);
		OrgInfoFacade orgInfoFacade=factory.createOrgInfoFacade();		
		return orgInfoFacade;		
	}
	
	/**
	 * .
	 * <p>描述:获取资源接口</p>
	 * @return
	 * @author Administrator
	 * @date 2013-08-22  13:17:27
	 */
	public static ResourceInfoFacade getResourceInfoFacade(){
		SystemFacadeFactory factory=SystemFacadeFactory.newInstance();
		SystemFacadeContext sfContext=factory.createSystemFacadeContext();
		factory.initSystemFacadeContext(sfContext);
		ResourceInfoFacade resourceInfoFacade=factory.createResourceInfoFacade();
		return resourceInfoFacade;
	}
	
	/**
	 * 获取日志操作对象.
	 * @author Reamy(杨木江 yangmujiang@sohu.com)
	 * @date 2014-01-13  10:46:31
	 */
	public static OpLogInfoFacade getLogInfoFacade() {
		SystemFacadeFactory factory=SystemFacadeFactory.newInstance();
		SystemFacadeContext sfContext=factory.createSystemFacadeContext();
		factory.initSystemFacadeContext(sfContext);
		OpLogInfoFacade logInfoFacade=factory.createOpLogInfoFacade();
		return logInfoFacade;
	}	
	
	/**
	 * 获取系统管理平台中的编码对象
	 * @return
	 */
	public static EtsCodeFacade getEtsCodeFacade() {
		SystemFacadeFactory factory=SystemFacadeFactory.newInstance();
		SystemFacadeContext sfContext=factory.createSystemFacadeContext();
		factory.initSystemFacadeContext(sfContext);
		return factory.createEtsCodeFacade();
	}
	
	/**
	 * 获取到系统管理平台的Connection
	 * @return
	 */
	public static Connection getConnectionFacade() {
		//SystemFacadeFactory factory=SystemFacadeFactory.newInstance();
		//SystemFacadeContext sfContext=factory.createSystemFacadeContext();
		//factory.initSystemFacadeContext(sfContext);
		
		SdkDataSource sdkDataSource = SystemFacadeFactory.newInstance().createSdkDataSource();
		try {
			return sdkDataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
}
