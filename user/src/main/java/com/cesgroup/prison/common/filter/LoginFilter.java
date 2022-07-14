package com.cesgroup.prison.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.framework.util.IpUtil;
import com.cesgroup.prison.common.bean.user.utils.UserLoginManager;
import com.cesgroup.prison.db.service.RedisCache;

@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {		
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String ctx = request.getContextPath();
		String url = request.getServletPath();
		
		if("/lg/loginCtrl/ln".equals(url)||
				"/lg/loginCtrl/lnCa".equals(url) 
				|| "/lg/loginCtrl/ln3".equals(url) 
				|| "/lg/loginCtrl/login".equals(url)
				|| "/lg/loginCtrl/logincac".equals(url)
				|| "/lg/loginCtrl/logincacaca".equals(url)
				|| "/lg/loginCtrl/errorLogout".equals(url)
				|| "/lg/loginCtrl/fujian".equals(url)
				|| url.indexOf("/common") == 0 
				|| url.indexOf("/views/lg") == 0 
				|| url.indexOf("/upload") == 0 
				|| url.indexOf("/static") == 0 
				|| url.indexOf("/ws") == 0 
				|| url.indexOf("/security") == 0
				|| url.indexOf("/xctb/show") == 0 
				|| url.indexOf("/alarm/guest/showAlarmMsg") == 0
				|| url.indexOf("/xxhj/jndt/toShowScreen") == 0
				|| url.indexOf("/guest") == 0) {
			
			chain.doFilter(request, response);
		} else {
			
			Object loginObj = RedisCache.getObject(UserLoginManager.LOGIN_CACHE_IP_USERINFO, IpUtil.getIpAddress());
    		if(loginObj == null || "".equals(String.valueOf(loginObj))) {
    			
    			// 跳转到用户退出错误页
            	response.sendRedirect(ctx + "/lg/loginCtrl/ln");
            	//response.sendRedirect( "http://34.211.0.203/portal");
    		} else {
    			chain.doFilter(request, response);
    		}
		}		 
	}
 
	public void destroy() {
	}
}
