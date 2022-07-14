package com.cesgroup.prison.zfxx.jyqk.util;


import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;

import com.cesgroup.framework.utils.PropertiesUtil;

public class DhJyqkHttpclient {
	
	private static final String WSDL = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.jyqk.url");
	
	public static String pub(String code, String startDate, String endDate, String prisonAreaID) throws Exception {
		String data = null;
		try {
			int timeout = 10000;
			StringBuffer sb = new StringBuffer("");
			sb.append("<?xml version=\"1.0\" encoding=\"utf-16\"?>");  
			sb.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">");  
			sb.append("<soap:Body>");  
			sb.append("<HIPMessageServer xmlns=\"http://tempuri.org\">");  
			sb.append("<input1>" + code + "</input1>");  
			sb.append("<input2>&lt;Request&gt; &lt;StartDate&gt;" + startDate + "&lt;/StartDate&gt; &lt;EndDate&gt;" + endDate 
					+ "&lt;/EndDate&gt;&lt;PrisonAreaID&gt;" + prisonAreaID + "&lt;/PrisonAreaID&gt;&lt;/Request&gt;</input2>");  
			sb.append("</HIPMessageServer>");  
			sb.append("</soap:Body>");  
			sb.append("</soap:Envelope>");     
			
			HttpClient client = new HttpClient();
			PostMethod postMethod = new PostMethod(WSDL);
			// 设置连接超时
			client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
			// 设置读取时间超时
			client.getHttpConnectionManager().getParams().setSoTimeout(timeout);
			// 然后把Soap请求数据添加到PostMethod中
			RequestEntity requestEntity = new StringRequestEntity(sb.toString(), "text/xml", "UTF-8");
			//设置请求头部，否则可能会报 “no SOAPAction header” 的错误
			postMethod.setRequestHeader("SOAPAction","http://tempuri.org/DHC.Published.PUB002.BS.PUB002.HIPMessageServer");
			// 设置请求体
			postMethod.setRequestEntity(requestEntity);
			int status = client.executeMethod(postMethod);
			if (status == 200) {
				// 获取响应体输入流
			    InputStream is = postMethod.getResponseBodyAsStream();
			    // 获取请求结果字符串
			    String result = IOUtils.toString(is);
			    int start = result.indexOf("<Response>") + 10;
			    int end = result.indexOf("</Response>");
			    data = result.substring(start, end);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return data;
	}

}