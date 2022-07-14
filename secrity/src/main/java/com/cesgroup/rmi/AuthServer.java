package com.cesgroup.rmi;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

public interface AuthServer {

	
	public long[] getPrepareStatus();
	public Map<String, Collection<ConfigAttribute>> loadResourceMap();
	
	
	public Map<String,Map<String, Collection<ConfigAttribute>>> loadOrgDiffResourceMap();
	
	/**
	 * 获取公共角色 和私有角色
	 * @return
	 */
	public Object[] loadResource();
}
