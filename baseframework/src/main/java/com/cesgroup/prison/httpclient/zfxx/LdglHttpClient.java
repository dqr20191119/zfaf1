package com.cesgroup.prison.httpclient.zfxx;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cesgroup.framework.exception.RESTHttpClientException;
import com.cesgroup.prison.httpclient.util.SzbbUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Description: 【数字冰雹】信息调用HttpClient工具类
 * 				 --- 4.领导管理驾驶舱 ---
 * @author zhou.jian
 *
 * @Date 2019年3月1日
 */
public class LdglHttpClient {

	/**
     * 日志工具
     */
    private static final Logger logger = LoggerFactory.getLogger(LdglHttpClient.class);
	
    /**
	 * Gson工具
	 */
	private final static Gson gson = new GsonBuilder().create();
	
	private static LdglHttpClient currentWSHttpClient = new LdglHttpClient();
	
	private LdglHttpClient() {
		
	}
	
	public static synchronized LdglHttpClient getWSHttpClient() {
		if(currentWSHttpClient != null) {
			return currentWSHttpClient;
		}
		return new LdglHttpClient();
	}
	
	/**
	 * post请求
	 * @param requestUrl
	 * @return
	 */
	public static HttpEntity httpPost(Map<String,Object> param, String requestUrl, JsonObject jsonObject) {
		HttpEntity httpEntity = null;
		String host = SzbbUtil.getUrlByJyCode((String)param.get("jyCode"));
		try {
			// 1.创建httpClient
			HttpClient httpClient = HttpClients.createDefault();
			
			// 2.声明并初始化request
			HttpPost request = new HttpPost(host + requestUrl);
			request.addHeader("content-type", "application/json");
			request.addHeader("Accept", "application/json");
			// System.out.println("executing request " + request.getURI());
			
			// 4.请求体
            StringEntity entity = new StringEntity(gson.toJson(jsonObject),"utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");    
            entity.setContentType("application/json");    
            request.setEntity(entity); 
			
			//4.设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
            request.setConfig(requestConfig);
            
            // 5.执行Http请求
            HttpResponse httpResponse = httpClient.execute(request);
			
			if(httpResponse != null) {
				httpEntity = httpResponse.getEntity();
			}
			
		} catch (ClientProtocolException e) {
			logger.error("请求" + host + requestUrl + "发生异常, Exception info is " + e);
		} catch (IOException e) {
			logger.error("请求" + host + requestUrl + "发生异常, Exception info is " + e);
		}
		return httpEntity;
	}
	
	
	/*==========================  封装数据    ===========================*/
	
	/**
	 * 1.领导管理驾驶舱 - 罪犯变化
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityZfbh(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("ICriminalChanged");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【LdglHttpClient】推送【4.1.领导管理驾驶舱 - 罪犯变化】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 2.领导管理驾驶舱 - 罪犯年龄对比
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityZfnldb(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("ICriminalAge");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【LdglHttpClient】推送【4.2.领导管理驾驶舱 - 罪犯年龄对比】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	/**
	 * 3.领导管理驾驶舱 - 罪犯类型
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityZflx(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("ICriminalType");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【LdglHttpClient】推送【4.3.领导管理驾驶舱 - 罪犯类型】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 4.领导管理驾驶舱 - 队伍建设
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityDwjs(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/ITeam");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【LdglHttpClient】推送【4.4.领导管理驾驶舱 - 队伍建设】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 5.领导管理驾驶舱 - 个别谈话
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityGbth(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/ITalk");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【LdglHttpClient】推送【4.5.领导管理驾驶舱 - 个别谈话】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 6.领导管理驾驶舱 - 亲情电话
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityQqdh(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IMeetAndCall");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【LdglHttpClient】推送【4.6.领导管理驾驶舱 - 亲情电话】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 7.领导管理驾驶舱 - 刑罚执行
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityXfzx(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IPenalty");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【LdglHttpClient】推送【4.7.领导管理驾驶舱 - 刑罚执行】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 8.领导管理驾驶舱 - 劳动工具
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityLdgj(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/ILaborTool");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【LdglHttpClient】推送【4.8.领导管理驾驶舱 - 劳动工具】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	/**
	 * 9.领导管理驾驶舱 - 大宗物品
	 *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityDzwp(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IGoods");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【LdglHttpClient】推送【4.9.领导管理驾驶舱 - 大宗物品】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	/**
	 * 10.领导管理驾驶舱 - 就医统计
	 *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityJytj(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IMedical");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【LdglHttpClient】推送【4.10.领导管理驾驶舱 - 就医统计】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	/**
	 * 11.领导管理驾驶舱 - 组织结构
	 *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityZzjg(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IOrganization");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【LdglHttpClient】推送【4.11.领导管理驾驶舱 - 组织结构】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
}
