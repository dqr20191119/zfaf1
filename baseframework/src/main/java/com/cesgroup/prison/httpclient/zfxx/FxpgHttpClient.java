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
 * 				 --- 3.安全风险评估 ---
 * @author zhou.jian
 *
 * @Date 2019年3月1日
 */
public class FxpgHttpClient {
	/**
     * 日志工具
     */
    private static final Logger logger = LoggerFactory.getLogger(FxpgHttpClient.class);
	
    /**
	 * Gson工具
	 */
	private final static Gson gson = new GsonBuilder().create();
	
	private static FxpgHttpClient currentWSHttpClient = new FxpgHttpClient();
	
	private FxpgHttpClient() {
		
	}
	
	public static synchronized FxpgHttpClient getWSHttpClient() {
		if(currentWSHttpClient != null) {
			return currentWSHttpClient;
		}
		return new FxpgHttpClient();
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
	 * 1.风险评估--监狱排名
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityJypm(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("IRanking");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【FxpgHttpClient】推送【3.1.风险评估--监狱排名】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 2.风险评估--风险清单
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityFxqd(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("IOccurredRisk");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【FxpgHttpClient】推送【3.2.风险评估--风险清单】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	/**
	 * 3.风险评估--频发风险
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityPffx(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("IFrequentlyRisk");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【FxpgHttpClient】推送【1.3.风险评估--今日概况】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 4.风险评估--风险等级
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityFxdj(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IRiskOverview");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【FxpgHttpClient】推送【3.4.风险评估--风险等级】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 5.风险评估--全部风险点
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityQbfxd(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IAllRiskPoint");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【FxpgHttpClient】推送【3.5.风险评估--全部风险点】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 6.风险评估--当前发生风险点
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityDqfxd(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/ICurrentRiskPoint");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【FxpgHttpClient】推送【3.6.风险评估--当前发生风险点】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 7.风险评估--当前发生风险详情
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityDqfxxq(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/ICurrentRiskDetail");
		
		String content = null;
		try {
			
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【FxpgHttpClient】推送【3.7.风险评估--当前发生风险详情】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 8.风险评估--网格风险
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityWgfx(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IGridRiskDetail");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【FxpgHttpClient】推送【3.8.风险评估--网格风险】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	/**
	 * 9.风险评估--网格风险评估
     *
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static String entityWgfxpg(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IGridRiskEvaluate");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【FxpgHttpClient】推送【3.9.风险评估--网格风险评估】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
	
	/**
	 * 10.风险评估--网格扣分
	 * @return 结果集
	 */
	public static String entityWgkf(Map<String,Object> param, JsonObject newParams) throws RESTHttpClientException {
		
		// Description: 请求Url拼接参数
		StringBuffer requestUrl = new StringBuffer();
		requestUrl.append("/IGridDeduction");
		
		String content = null;
		try {
			HttpEntity httpEntity = httpPost(param, requestUrl.toString(),newParams);
			content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
			System.out.println("推送数据返回值  ===> "+content);
			
		} catch (Exception e) {
			throw new RESTHttpClientException("调用【FxpgHttpClient】推送【3.10.风险评估--网格扣分】信息发生异常, Exception info is " + e);
		}
		return content;
	}
	
}
