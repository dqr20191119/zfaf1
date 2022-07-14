/**
 * <p>Copyright:Copyright(c) 2013</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.ces.xarch.plugins.authsystem.service</p>
 * <p>文件名:XarchInvocationSecurityMetadataSource.java</p>
 * <p>类更新历史信息</p>
 * @todo Reamy(杨木江 yangmujiang@sohu.com) 创建于 2013-07-23 10:10:21
 */
package com.cesgroup.prison.authsystem.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ces.xarch.core.utils.AntPathRequestMatcher;
import com.cesgroup.prison.common.bean.user.RoleBean;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.rmi.client.ClientAuthServer;

/**
 * Spring Security3 资源权限管理类.
 * <p>描述:负责根据访问资源构造其权限</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * @author Reamy(杨木江 yangmujiang@sohu.com)
 * @date 2013-07-23  10:10:21
 * @version 1.0.2013.0723
 */
@Service("XarchInvocationSecurityMetadataSource")
public class XarchInvocationSecurityMetadataSource {///implements FilterInvocationSecurityMetadataSource {
	/** SAME_LEVEL_TYPE(String): 忽略参数. */
	private static final String SAME_LEVEL_TYPE = "02";
	/** ALL_TYPE(String):忽略子目录. */
	private static final String ALL_TYPE = "03";
	/** USER_TYPE(String):登录后可访问. */
	private static final String USER_TYPE = "04";
	
	/** resourceMap(Map<String,Collection<ConfigAttribute>>):资源权限缓存集合. */
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	
	/** resourceMap(Map<String,Collection<ConfigAttribute>>):单位差异性 资源权限缓存集合. */
	private static Map<String,Map<String, Collection<ConfigAttribute>>> orgDiffResourceMap = null;//Map<OrgId ,Map<url,attr>>
	
	
	
	/** resourceMap(Map<String,Collection<ConfigAttribute>>):资源权限缓存集合.最新副本，为了保证系统不被全局刷角色死锁，在更新flush状态之前预加载资源权限缓存集合，当副本刷新成功后，更新flush状态 */
	private static Map<String, Collection<ConfigAttribute>> resourceMapClonePrepare = null;
	
	/** resourceMap(Map<String,Collection<ConfigAttribute>>):单位差异性 资源权限缓存集合. .最新副本，为了保证系统不被全局刷角色死锁，在更新flush状态之前预加载资源权限缓存集合，当副本刷新成功后，更新flush状态 */
	private static Map<String,Map<String, Collection<ConfigAttribute>>> orgDiffResourceMapClonePrepare = null;//Map<OrgId ,Map<url,attr>>
	
	/**
	 * 刷新全新，新增权限副本（外部调用）
	 */
	/*public  void reflushResourceMapClone(){
		this.prepareResourceMapClone();
		this.prepareOrgDiffResourceMapClone();
		reFlush();
		reFlushDiff();
	}*/
	
	
	/**
	 * 继续沿用
	 */
	long resourceVersion=0;//当前资源权限版本号
	private long[] prepareStatus= new long[]{0,0};//是否已经缓存好0:没有缓存 1：正在缓存 2：已经缓存
	public boolean task =false;
	public void reflushResourceMapClone(){
		int i=0;
		try{
		Thread.sleep(1000*10);//目前系统资源加载需要1分30秒，先暂停10秒（系统管理平台推送rmi server 消息滞后），保证每次都能最快加载到最新的数据
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(" ERROR : XarchInvocationSecurityMetadataSource sleep error");
		}
		
		try{
			ClientAuthServer clientAuthServer = new ClientAuthServer();
			clientAuthServer.loadAuthResource(resourceVersion);
			if(clientAuthServer.getVersion() ==0){//调用本地从数据库加载权限
				/*this.prepareResourceMapClone();
				this.prepareOrgDiffResourceMapClone();
				reFlush();
				reFlushDiff();*/
				task = false;
				//System.out.println("由于远程RMI server未被发现，系统已自动切换为从本机加载资源权限成功！");
				System.out.println("资源加载失败，由于远程RMI server未被发现，系统暂不支持自动切换为从本机加载资源权限！");
				
			}else if(clientAuthServer.getStatus()==1){
				task = true;
			}else if(clientAuthServer.getStatus()==2 && clientAuthServer.getVersion()==resourceVersion){//如果版本没有发生改变则不更新
				task = false;
			}else if(clientAuthServer.getStatus()==2 && clientAuthServer.getVersion()>resourceVersion){//如果版本号增加则需要更新
				long l = System.currentTimeMillis();
				
				
				this.resourceMapClonePrepare = clientAuthServer.getLoadResourceMap();//如果RMI Server 服务返回的是Null,将直接赋予null值，因为执行了reFlush(),本地系统在判断权限时将直接从数据库中直接加载
				//System.out.println(l+"更新中2-1：resourceMapClonePrepare.size:"+resourceMapClonePrepare.size());
				this.orgDiffResourceMapClonePrepare = clientAuthServer.getLoadOrgDiffResourceMap();//同上
				//System.out.println(l+"更新中2-2：resourceMapClonePrepare.size:"+resourceMapClonePrepare.size()+"\torgDiffResourceMapClonePrepare.size:"+orgDiffResourceMapClonePrepare.size());
				this.resourceVersion = clientAuthServer.getVersion();//赋予新的版本号
				reFlush();
				reFlushDiff();
				
				
				/*System.out.println("======================================");
				Map<String, Collection<ConfigAttribute>> x = orgDiffResourceMapClonePrepare.get("103");
				if(x != null){
					Collection n = x.get("/jggz/rj/xfsj/xsjd");
					System.out.println(n);
					
				}*/
				
				checkIfNeedDoFlush();
				checkIfNeedDoFlush2();
				this.task = false;
				
				
				
			}else{
				task = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	public long[] getPrepareStatus(){
		return prepareStatus;
	}
	
	

	private static final String SEPRATE = "!_!";
	/** 用户与单位MAP 集合 */
	private static Map<String, String> userOrgMap = new HashMap<String ,String>();//map.put(userName , orgId);
	
	/**根据用户ID 获取单位ID*/
	private String loadOrgIdByLoginName(String loginName){
		/*if(loginName != null && !loginName.equals("")){
			String[] userMessage = loginName.split(SEPRATE);
			if(userMessage != null && userMessage.length>0)
				return userMessage[1];
		}*/
		return null;
	}
	
	/**根据登陆用户 获取单位ID*/
	private String loadOrgIdByLogin(){
		return com.cesgroup.prison.common.facade.AuthSystemFacade.getLoginUserOrgId();
		
	}
	
	
	private String loadLoginName(){
		
		/*HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession()
		
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			return SecurityContextHolder.getContext().getAuthentication().getName();
        }*/
		
		
	
	
		return null;
	}
	
	
	
	
	
	/** denyAll(boolean):默认拒绝所有访问. */
	private boolean denyAll = true;
	/** 每次都从数据库获取资源权限. */
	private boolean awaysLoadFromDB = false;
	/** 进行一次刷新. */
	private static boolean doFlush = false;
	private static boolean doFlushDiff = false;
	/** denyAttr(Collection<ConfigAttribute>):拒绝访问时返回的权限. */
	private static Collection<ConfigAttribute> denyAttr = null;
	
	/** 系统编号. */
	private String appKey = "";
	
	/**
	 * 清除缓存.
	 * @author Reamy(杨木江 yangmujiang@sohu.com)
	 * @date 2013-04-09  14:34:12
	 */
	private Map<String, Collection<ConfigAttribute>> clearCache() {
		try {
			
			//added by lihonghui at 20160413 
			if(resourceMapClonePrepare != null)
				return resourceMapClonePrepare;
			//added end
			
			
			
			//return loadResourceDefine();
			return resourceMap;//返回之前版本
		} catch (Exception e) {
			throw new RuntimeException("从系统管理平台获取角色及资源信息出错", e);
		}
	}
	private Map<String,Map<String, Collection<ConfigAttribute>>> clearDiffCache() {
		try {
			
			//added by lihonghui at 20160413 
			if(orgDiffResourceMapClonePrepare != null)
				return orgDiffResourceMapClonePrepare;
			//added end
			
			
			//return loadOrgDiffResourceDefine();
			return orgDiffResourceMap;//返回之前版本
		} catch (Exception e) {
			throw new RuntimeException("从系统管理平台获取--差异性--角色及资源信息出错", e);
		}
	}
	
	/**
	 * 刷新缓存.
	 * @author Reamy(杨木江 yangmujiang@sohu.com)
	 * @date 2013-07-02  16:59:50
	 */
	public static synchronized void reFlush() {
		doFlush = true;
	}
	
	/**
	 * 刷新缓存.
	 * @author Reamy(杨木江 yangmujiang@sohu.com)
	 * @date 2013-07-02  16:59:50
	 */
	public static synchronized void reFlushDiff() {
		doFlushDiff = true;
	}
	
	
	/**
	 * 检查是否要刷新缓存.
	 * @author Reamy(杨木江 yangmujiang@sohu.com)
	 * @date 2013-07-02  17:02:01
	 */
	private synchronized Map<String, Collection<ConfigAttribute>> checkIfNeedDoFlush() {
		if (doFlush) {
			doFlush = false;
			resourceMap = clearCache();
			
			resourceMapClonePrepare = null;//added by lihonghui at 20160413
		}
		
		if (resourceMap != null && resourceMap.size() > 0) {
			Map<String, Collection<ConfigAttribute>> resourceMapClone = new HashMap<String, Collection<ConfigAttribute>>(resourceMap.size());
			resourceMapClone.putAll(resourceMap);
			return resourceMapClone;
		}
		
		return null;
	}
	private synchronized Map<String,Map<String, Collection<ConfigAttribute>>> checkIfNeedDoFlush2() {
		if (doFlushDiff) {
			doFlushDiff = false;
			orgDiffResourceMap = clearDiffCache();
			
			orgDiffResourceMapClonePrepare = null;//added by lihonghui at 20160413
			
		}
		if (orgDiffResourceMap != null && orgDiffResourceMap.size() > 0) {
			Map<String,Map<String, Collection<ConfigAttribute>>> orgDeffResourceMapClone = new HashMap<String,Map<String, Collection<ConfigAttribute>>>(orgDiffResourceMap.size());
			orgDeffResourceMapClone.putAll(orgDiffResourceMap);
			return orgDeffResourceMapClone;
		}
		return null;
	}
	
	@Autowired(required=false)
	@Qualifier("securityDenyAll")
	public void setDenyAll(Boolean denyAll) {
		this.denyAll = denyAll;
	}
	
	public Collection<ConfigAttribute> getAttributes(String orgId , Object object) throws IllegalArgumentException {
		Map<String, Collection<ConfigAttribute>> resourceMap = null;
		
  		Map<String,Map<String, Collection<ConfigAttribute>>> orgDiffResourceMap = null;//Map<OrgId ,Map<url,attr>>
		
		if (awaysLoadFromDB) {
			resourceMap = clearCache();
			orgDiffResourceMap = clearDiffCache();
		} else {
			resourceMap = checkIfNeedDoFlush();
			orgDiffResourceMap = checkIfNeedDoFlush2();
		}
		//System.out.println(object);
		
		Collection<ConfigAttribute> atts = null;
		
		if (orgDiffResourceMap != null) {
			//String orgId = loadOrgIdByLoginName(loadLoginName());//用户单位来自当前登陆用户
			//String orgId = loadOrgIdByLogin();//用户单位来自当前登陆用户----------------------------------直接作为参数传入
			
			//System.out.println("当前用户所在单位id:"+orgId);
			if(orgId !=null){
				Map<String, Collection<ConfigAttribute>> diffResourceMap = orgDiffResourceMap.get(orgId);
				
				
				if(diffResourceMap != null){
					Iterator<String> ite = diffResourceMap.keySet().iterator();
		        	while (ite.hasNext()) {
		                String resURL = ite.next();
		                //System.out.println(resURL);
		                AntPathRequestMatcher matcher = new AntPathRequestMatcher(resURL);
		                
		                if (matcher.matches(object)) {
		                	if (atts == null) {
		                		atts = new ArrayList<ConfigAttribute>();
		                	}
		                	
		                	atts.addAll(diffResourceMap.get(resURL));
		                }
		            }
		        	
		        	/*if (atts != null) {
		        		return atts;
		        	}*/
				}
				
			}
        	
        }
		
        if (resourceMap != null) {
        	Iterator<String> ite = resourceMap.keySet().iterator();
        	//Collection<ConfigAttribute> atts = null;
        	
        	while (ite.hasNext()) {
                String resURL = ite.next();
                AntPathRequestMatcher matcher = new AntPathRequestMatcher(resURL);
                
                if (matcher.matches(object)) {
                	if (atts == null) {
                		atts = new ArrayList<ConfigAttribute>();
                	}
                	
                	atts.addAll(resourceMap.get(resURL));
                }
            }
        	
        	if (atts != null) {
        		for(ConfigAttribute c : atts)
        			//System.out.println(c.getAttribute());
        		return atts;
        	}
        }
        
        if (denyAll) {
        	return denyAttr;
        }
        
		return null;
		
	}
	
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		return  getAttributes(null   , object);

	}

	
	

}
