package com.ces.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.cesgroup.framework.commons.SpringContextUtils;
import com.cesgroup.prison.authsystem.service.XarchInvocationSecurityMetadataSource;
import com.cesgroup.prison.common.bean.user.RoleBean;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;

/**
 * <p>描述:</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * @author lihonghui E-mail:li.honghui@cesgroup.com.cn
 * @date 2018-3-14 下午1:48:19 
 * @version V1.0 
 */
public class Authorize {
	
	/**
     * Make an authorization decision based on the URL and HTTP method attributes. True is returned if the user is
     * allowed to access the given URL as defined.
     *
     * @return the result of the authorization decision
     * @throws IOException
     */
  
    /*public static boolean urlCheck(String uri){
    	try{
    		 String contextPath = ((HttpServletRequest) ServletActionContext.getRequest()).getContextPath();
    	        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
    	        //boolean authorized = getPrivilegeEvaluator().isAllowed(contextPath, "", "GET", currentUser);
    	        boolean authorized = getPrivilegeEvaluator().isAllowed(contextPath, uri, "GET", currentUser);
    	        return authorized;
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	return false;
    	
    }
    */
	
	public static boolean isAllowed(String contextPath, String uri, String method, UserBean  userBean) {
        Assert.notNull(uri, "uri parameter is required");
        Collection<ConfigAttribute> attrs = null; 
        String currentLoginUserOrgId  = userBean.getOrgId();
		if(currentLoginUserOrgId != null) {
			XarchInvocationSecurityMetadataSource  xarchInvocationSecurityMetadataSource = (XarchInvocationSecurityMetadataSource)SpringContextUtils.getBean("XarchInvocationSecurityMetadataSource");
			attrs = xarchInvocationSecurityMetadataSource.getAttributes(currentLoginUserOrgId, uri);
		}
		
        if (attrs == null) {
            /*if (securityInterceptor.isRejectPublicInvocations()) {
                return false;
            }*/
            return false;
        }

        if (userBean.getRoles() == null) {
            return false;
        }

        try {
            decide(userBean.getRoles(), uri, attrs);
        } catch (AccessDeniedException unauthorized) {
            return false;
        }

        return true;
    }
	
	public static void decide(List<RoleBean> authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		
        // 所请求的资源拥有的权限(一个资源对多个权限)
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            // 访问所请求资源所需要的权限
            String needPermission = configAttribute.getAttribute();
            
            // 用户所拥有的权限authentication
            /*for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needPermission.equals(ga.getAuthority())) {
                    return;
                }
            }*/
            
            for (RoleBean ga : authentication) {
                if (needPermission.equals(ga.getCode())) {
                    return;
                }
            }
        }
        
        // 没有权限
        throw new AccessDeniedException("没有权限访问！ ");
	}
	
	
    private static Boolean security =null;
    /**
     * 是否启动系统中的权限验证，如果为启用，则对系统中的所有权限标签的资源进行验证；不启用，则统一忽略标签进行显示
     * 系统在spring 配置文件中进行初始化
     * @return
     */
    public  static boolean checkSecurity() {
    	if(security==null) {
    		return security = (Boolean)SpringContextUtils.getBean("com.cesgroup.security.environment.open");
    	}
    	return security;
    		
    	
    }
    public static boolean urlCheck(String uri){
    	if(!checkSecurity()) {
    		return true;
    	}
		
    	
    	
    	try{
    		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
    		HttpServletRequest request = attributes.getRequest();
    		
    		UserBean  userBean  = AuthSystemFacade.getLoginUserInfo();
    		if(userBean != null) {
    			
    			
    			String contextPath = request.getContextPath();
        	    //Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
    	        //boolean authorized = getPrivilegeEvaluator().isAllowed(contextPath, "", "GET", currentUser);
    	        boolean authorized = isAllowed(contextPath, uri, "GET", userBean);
    	        return authorized;
    		}
    		
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return false;
    	
    }
	
	/**
	 * 测试权限标签的
	 * 标签内容： authorized ：'/yzgl/abc/tt'
	 * 转化后内容  authorized ：boolean(true/false)
	 */
	public static void tagAuthorized(){
		String str = "a 正则表达式'authorized' : '/abc/d',正则表达式 Hello World,'authorized':'/xxx/xx' 正则表达式 Hello World a";
			//Pattern pattern = Pattern.compile("'auth'\\s*:\\s*('[^']+')");
			java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("'authorized'\\s*:\\s*'([^']+)'");
			java.util.regex.Matcher matcher = pattern.matcher(str);
			StringBuffer sbr = new StringBuffer();
			while (matcher.find()) {
				System.out.println("matcher:"+matcher.group(1));
			    matcher.appendReplacement(sbr, "'authorized':"+com.ces.security.Authorize.urlCheck(matcher.group(1)));
			}
			matcher.appendTail(sbr);// 将最后一次匹配工作后剩余的字符串添加到一个StringBuffer对象里
			System.out.println(sbr.toString());
			
	}

}
