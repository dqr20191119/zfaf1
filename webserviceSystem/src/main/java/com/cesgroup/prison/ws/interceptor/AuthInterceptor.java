package com.cesgroup.prison.ws.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
	
	private Map<String, String> userKeyMap = new HashMap<String, String>();
	
	public AuthInterceptor() {
		super(Phase.PRE_INVOKE);
		
		// 初始化监狱同步数据所需的用户名和密码信息
		userKeyMap.put("6501", "650112345678");		
		userKeyMap.put("6502", "650221345678");
		userKeyMap.put("6503", "650331245678");
		userKeyMap.put("6504", "650441235678");
		userKeyMap.put("6505", "650551234678");
		userKeyMap.put("6506", "650661234578");
		userKeyMap.put("6507", "650771234568");
		userKeyMap.put("6508", "650881234567");
		userKeyMap.put("6510", "65100156");
		userKeyMap.put("6511", "65111156");
		userKeyMap.put("6513", "65133156");
		userKeyMap.put("6514", "65144156");
		userKeyMap.put("6515", "65155156");
		userKeyMap.put("6516", "65166156");
		userKeyMap.put("6517", "65177156");
		userKeyMap.put("6518", "65188156");
		userKeyMap.put("6519", "65199156");
		userKeyMap.put("6520", "65200256");
		userKeyMap.put("6521", "65211256");
		userKeyMap.put("6530", "65300356");
		userKeyMap.put("6531", "65311356");
		userKeyMap.put("6532", "65322356");
		userKeyMap.put("6542", "65422456");
		userKeyMap.put("6500", "65000056");
		userKeyMap.put("6599", "65999956");
		userKeyMap.put("6523", "65233256");
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		 
		// InputStream is = message.getContent(InputStream.class);
				
		List<Header> headerList = message.getHeaders();
		if(headerList == null || headerList.size() == 0) {
            throw new Fault(new Exception("找不到Header，无法验证用户信息"));
        } 
		
		Header header = headerList.get(0);
		Element el = (Element) header.getObject();
        NodeList users = el.getElementsByTagName("username");
        NodeList passwords = el.getElementsByTagName("password");
        
        if(users == null || users.getLength() == 0) {
        	throw new Fault(new Exception("找不到用户名"));
        }
        
        if(passwords == null || passwords.getLength() == 0) {
            throw new Fault(new Exception("找不到用户密码"));
        }
        
        String username = users.item(0).getTextContent().trim();
        String password = passwords.item(0).getTextContent().trim();
        
        if("".equals(username)) {
        	throw new Fault(new Exception("用户名为空"));
        }
        
        if("".equals(password)) {
        	throw new Fault(new Exception("用户密码为空"));
        }
        
        if(!userKeyMap.containsKey(username)) {
        	throw new Fault(new Exception("用户名错误"));
        }

        if(!userKeyMap.get(username).equals(password)) {  
            throw new Fault(new Exception("用户密码错误"));  
        }
	}
}