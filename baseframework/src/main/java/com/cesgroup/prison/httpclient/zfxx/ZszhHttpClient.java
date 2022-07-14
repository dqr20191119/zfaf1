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
 * 				 --- 2.战时指挥 ---
 * @author zhou.jian
 *
 * @Date 2019年3月1日
 */
public class ZszhHttpClient {
    
	/**
     * 日志工具
     */
    private static final Logger logger = LoggerFactory.getLogger(ZszhHttpClient.class);
	
    /**
	 * Gson工具
	 */
	private final static Gson gson = new GsonBuilder().create();
	
	private static ZszhHttpClient currentWSHttpClient = new ZszhHttpClient();
	
	private ZszhHttpClient() {
		
	}
	
	public static synchronized ZszhHttpClient getWSHttpClient() {
		if(currentWSHttpClient != null) {
			return currentWSHttpClient;
		}
		return new ZszhHttpClient();
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
			
			// 3.请求体
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
	 * 1.战时指挥 - 现场警员
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityXcjy(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("ISpotPolice");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【ZszhHttpClient】推送【3.1.战时指挥 - 现场警员】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 2.战时指挥 - 待援警员
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityDyjy(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("ISupportPolice");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【ZszhHttpClient】推送【3.2.战时指挥 - 待援警员】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	/**
	 * 3.战时指挥 - 待命车辆
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityDmcl(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("ISupportVehicle");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【ZszhHttpClient】推送【1.3.战时指挥 - 待命车辆】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 4.战时指挥 - 警务装备
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityJwzb(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IPoliceEquipment");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【ZszhHttpClient】推送【3.4.战时指挥 - 警务装备】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 5.战时指挥 - 事件信息
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entitySjxx(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IAlarmEvent");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【ZszhHttpClient】推送【3.5.战时指挥 - 事件信息】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 6.战时指挥 - 事件流程
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entitySjlc(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IEventProcess");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【ZszhHttpClient】推送【3.6.战时指挥 - 事件流程】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 7.战时指挥 - 当前流程
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityDqlc(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/ICurrentProcess");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【ZszhHttpClient】推送【3.7.战时指挥 - 当前流程】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 8.战时指挥 - 涉事部位
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entitySsbm(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IEventArea");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【ZszhHttpClient】推送【3.8.战时指挥 - 涉事部位】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
}
