package com.cesgroup.framework.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class IpUtil {
	/**
	 * 获取远程用户的IP
	 * @param request
	 * @return
	 */
	public static String currentRemoteIp(HttpServletRequest request) {
		String ip  = IpUtil.getIpAddress();
		if(ip.equals("0:0:0:0:0:0:0:1"))
			ip="127.0.0.1";
		return ip ;		
	}
	
	public static HttpServletRequest getHttpServletRequest() {
	    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public static String getIpAddress() {
    	String ip = null;
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        try {
			HttpServletRequest request = getHttpServletRequest();
			ip = request.getHeader("X-Forwarded-For");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			        ip = request.getHeader("Proxy-Client-IP");
			    }
			    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			        ip = request.getHeader("WL-Proxy-Client-IP");
			    }
			    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			        ip = request.getHeader("HTTP_CLIENT_IP");
			    }
			    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			    }
			    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			        ip = request.getRemoteAddr();
			    }
			} else if (ip.length() > 15) {
			    String[] ips = ip.split(",");
			    for (int index = 0; index < ips.length; index++) {
			        String strIp = (String) ips[index];
			        if (!("unknown".equalsIgnoreCase(strIp))) {
			            ip = strIp;
			            break;
			        }
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ip;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
