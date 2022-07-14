package com.cesgroup.prison.plugins.authsystem.utils;


import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ces.authsystem.entity.UserSimpleEntity;

import net.sf.json.JSONObject;

/**
 * Cookie处理类
 *
 */
public class CookieUtil {
	
	private static final String LOGINUSERUUO_COOKIEKEY = "_xarch_loginuser_uuo_";  	
	private static Boolean useSecureCookie = null;
	
	public static UserSimpleEntity loadUserSimpleEntityFromCookie() {
		
		UserSimpleEntity userSimpleEntity = null;
		String userUnitOrgStr;
		try {
			
			userUnitOrgStr = extractUserUnitOrgCookie(getRequest());
			if(userUnitOrgStr != null && !userUnitOrgStr.equals("")) {
				JSONObject obj = new JSONObject().fromObject(userUnitOrgStr);
				userSimpleEntity = (UserSimpleEntity) JSONObject.toBean(obj, UserSimpleEntity.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("试图从COOkie中获取用户失败");
		}
		
		return userSimpleEntity;
	}
	
	private static String extractUserUnitOrgCookie(HttpServletRequest request) throws Exception {
		
        Cookie[] cookies = request.getCookies();
        if((cookies == null) || (cookies.length == 0)) {
            return null;
        }
        
        for (Cookie cookie : cookies) {
            if(LOGINUSERUUO_COOKIEKEY.equals(cookie.getName())) {
            	return URLDecoder.decode(cookie.getValue(), "UTF-8");
            }
        }
        
        return null;
    }

	public static void setUserUnitOrgCookie(UserSimpleEntity userSimpleEntity) throws Exception {
		
 		if(userSimpleEntity != null ) {
			JSONObject json = JSONObject.fromObject(userSimpleEntity);
			String userUnitOrg = json.toString();
			setCookie(LOGINUSERUUO_COOKIEKEY, URLEncoder.encode((userUnitOrg), "UTF-8"), -1);
		}
	}	
	
	public static void setCookie(String name, String value, int maxAge) {
		
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");

		if(useSecureCookie == null) {
			cookie.setSecure(getRequest().isSecure());
		} else {
			cookie.setSecure(useSecureCookie);
		}
		
		getResponse().addCookie(cookie);		
	}
	
	public static HttpServletRequest getRequest() {
		
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();  
	}
	
	public static HttpServletResponse getResponse() {
		
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();  
	}
}
