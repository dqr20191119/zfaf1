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
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.httpclient.util.SzbbUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Description: 【数字冰雹】信息调用HttpClient工具类 
 * 			     --- 1.日常管控 ---
 * @author zhou.jian
 *
 * @Date 2019年2月24日
 */
public class SzbbHttpClient {

	/**
     * 日志工具
     */
    private static final Logger logger = LoggerFactory.getLogger(SzbbHttpClient.class);
	
    /**
	 * Gson工具
	 */
	private final static Gson gson = new GsonBuilder().create();
	
	private static SzbbHttpClient currentWSHttpClient = new SzbbHttpClient();
	
	private SzbbHttpClient() {
		
	}
	
	public static synchronized SzbbHttpClient getWSHttpClient() {
		if(currentWSHttpClient != null) {
			return currentWSHttpClient;
		}
		return new SzbbHttpClient();
	}
	
	/*=====================================================*/
	
	
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
	
	
	
	/*==========================    业务数据推送     ===========================*/
	
	
	/**
	 * 1.日常管控--今日值班
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityJrzb(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("IDayDuty");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param,requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【SzbbHttpClient】推送【1.1.日常管控--今日值班】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 2.日常管控--今日概况
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityJrgk(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("IDayOverview");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param,requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【SzbbHttpClient】推送【1.2.日常管控--今日概况】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	/**
	 * 3.日常管控--区域管控
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityQygk(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("IRegionalManage");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param,requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【SzbbHttpClient】推送【1.3.日常管控--今日概况】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 4.日常管控--重点罪犯、重控犯信息接口
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityZdzf(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IImportantCriminal");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param,requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【SzbbHttpClient】推送【1.4.日常管控--今日概况】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 5.日常管控--大门出入管控信息接口
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityDmcr(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IGateManage");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param,requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【SzbbHttpClient】推送【1.5.日常管控--今日概况】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 6.日常管控--人脸识别信息接口
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityRlsb(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IFaceRecognition");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param,requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【SzbbHttpClient】推送【1.6.日常管控--今日概况】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 7.日常管控--周界安防信息接口
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityZjaf(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IBorderSecurity");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param,requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【SzbbHttpClient】推送【1.7.日常管控--今日概况】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 8.日常管控--手机管控
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entitySjgk(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("IPhoneManage");
		
		String content = null;
		try {
			
			//推送数据的当前时间
			String curDate = Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_TIME), Util.DF_TIME);;
			
			Gson gson = new Gson();
			
			if(requestUrl != null) {
				
//				Map<String,Object> map2 = new HashMap<String,Object>();
//				map2.put("MobileControlList", listMap);
//				map2.put("Time", curDate);
//			 
//				//最终数据结果转换成json格式
//				JsonObject newParams = new JsonObject();
//				String jsonMap2 = gson.toJson(map2);
//				newParams.addProperty("json", jsonMap2);
				
				HttpEntity httpEntity = httpPost(param,requestUrl.toString(),newParams);
				
				content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
				System.out.println("推送数据返回值  ===> "+content);
			}
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【SzbbHttpClient】推送【8.日常管控--手机管控】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	/**
	 * 9.日常管控--狱外押解任务信息接口
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityYwyjrw(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IEscortMission");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param,requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【SzbbHttpClient】推送【1.9.日常管控--今日概况】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 10.狱外押解车辆实时位置信息接口
	 * @return 结果集
	 */
	public static String entityYwyjclwz(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IEscortVehicleGps");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param,requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【SzbbHttpClient】推送【1.10.日常管控--今日概况】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
}
