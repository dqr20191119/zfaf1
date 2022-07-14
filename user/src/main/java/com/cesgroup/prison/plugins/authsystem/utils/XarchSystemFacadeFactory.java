package com.cesgroup.prison.plugins.authsystem.utils;

import ces.sdk.sdk.db.SdkDataSource;
import ces.sdk.system.conf.SystemConf;
import ces.sdk.system.dbfactory.DbSystemFacadeFactory;
import ces.sdk.system.factory.SystemFacadeFactory;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.cesgroup.framework.commons.SpringContextUtils;

/**
 * 重写系统管理sdk的DbSystemFacadeFactory
 * 使用数据源
 * 
 */
@Component
public class XarchSystemFacadeFactory extends DbSystemFacadeFactory implements ApplicationListener<ApplicationEvent> {
	
	private static boolean init = true;
		
	/** 默认实体工厂类. */
	private final String DEFAULT_ENTITY_FACTORY_CLASS = "ces.sdk.system.dbfactory.DbEntityFactory";
	/** 默认代理工厂类. */
	private final String DEFAULT_FACADE_FACTORY_CLASS = "com.cesgroup.prison.plugins.authsystem.utils.XarchSystemFacadeFactory";
	/** 默认权限引擎类. */
	private final String DEFAULT_AUTH_ENGINE_CLASS = "ces.sdk.system.engine.NoCacheAuthEngine";
			
	@Override
	public SdkDataSource createSdkDataSource() {
		
		return (SdkDataSource) SpringContextUtils.getBean(AuthsystemDataSource.class);
	}
	
	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		
		if(init) {
			
			SystemConf conf = null;
			
			try {
				conf = SystemConf.getInstance();
			} catch (Exception e) {
				// 无需抛出异常，此处故意让系统管理平台进行一次实例化，因为配置文件不存在故一定会出现异常
			}
			
			//初始化参数			
			conf.setEntityFactoryClassName(DEFAULT_ENTITY_FACTORY_CLASS);
			conf.setFacadeFactoryClassName(DEFAULT_FACADE_FACTORY_CLASS);
			conf.setAuthEngineClassName(DEFAULT_AUTH_ENGINE_CLASS);
			
			//初始化实例
			SystemFacadeFactory.newInstance();
			
			init = false;
		}		
	}
}
