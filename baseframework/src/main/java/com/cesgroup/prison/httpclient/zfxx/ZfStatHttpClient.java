package com.cesgroup.prison.httpclient.zfxx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cesgroup.framework.exception.RESTHttpClientException;
import com.cesgroup.framework.utils.PropertiesUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.httpclient.zfxx.dto.ZfStatDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Description: 罪犯统计信息调用HttpClient工具类
 * @author zhou.jian
 *
 * @Date 2019年1月17日
 */
public class ZfStatHttpClient {

	/**
	 * 罪犯信息接口地址
	 */
	private static String host = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.zfxx.url"); //"http://192.168.8.90:80/jy-query/api/v1"
    /**
     * 日志工具
     */
    private static final Logger logger = LoggerFactory.getLogger(ZfJbxxHttpClient.class);
	/**
	 * Gson工具
	 */
	private final static Gson gson = new GsonBuilder().create();
	
	private static ZfStatHttpClient currentWSHttpClient = new ZfStatHttpClient();
	
	private ZfStatHttpClient() {
		
	}
	
	private ZfStatHttpClient(String host) {
		ZfStatHttpClient.host = host;
	}
	
	public static synchronized ZfStatHttpClient getWSHttpClient(String host) {
		ZfStatHttpClient.host = host;
		return currentWSHttpClient;
	}
	
	public static synchronized ZfStatHttpClient getWSHttpClient() {
		if(currentWSHttpClient != null) {
			return currentWSHttpClient;
		}
		return new ZfStatHttpClient();
	}
	
	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		ZfStatHttpClient.host = host;
	}
	
	public String httpUpdate(String requestUrl, String parameters) {
		String data = "";
		HttpClient client = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(host + requestUrl);
		httpPut.addHeader("content-type", "application/json");
		httpPut.addHeader("Accept", "application/json");
		System.out.println("executing request " + httpPut.getURI());
		try {
			httpPut.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
			HttpResponse response = client.execute(httpPut);
			System.out.println("statusCode：" + response.getStatusLine().getStatusCode());
			Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++)
				System.out.println(headers[i].getName() + ":" + headers[i].getValue());
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println("--------------------------------------");
					data = EntityUtils.toString(entity, "UTF-8");
					System.out.println("Response content: " + data);
					System.out.println("--------------------------------------");
				}
			} finally {
				System.out.println();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public String httpUpdate(String requestUrl) {
		String data = "";
		HttpClient client = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut(host + requestUrl);
		httpPut.addHeader("content-type", "application/json");
		httpPut.addHeader("Accept", "application/json");
		System.out.println("executing request " + httpPut.getURI());
		try {
			HttpResponse response = client.execute(httpPut);
			System.out.println("statusCode：" + response.getStatusLine().getStatusCode());
			Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++)
				System.out.println(headers[i].getName() + ":" + headers[i].getValue());
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println("--------------------------------------");
					data = EntityUtils.toString(entity, "UTF-8");
					System.out.println("Response content: " + data);
					System.out.println("--------------------------------------");
				}
			} finally {
				System.out.println();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public Integer httpDelete(String requestUrl) {
		Integer data = Integer.valueOf(0);
		HttpClient client = HttpClients.createDefault();
		HttpDelete delete = new HttpDelete(host + requestUrl);
		delete.addHeader("content-type", "application/json");
		delete.addHeader("Accept", "application/json");
		System.out.println("executing request " + delete.getURI());
		try {
			HttpResponse response = client.execute(delete);
			System.out.println("statusCode：" + response.getStatusLine().getStatusCode());
			Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++)
				System.out.println(headers[i].getName() + ":" + headers[i].getValue());
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println("--------------------------------------");
					String text = EntityUtils.toString(entity, "UTF-8");
					System.out.println("Response content: " + text);
					System.out.println("--------------------------------------");
					if (StringUtils.isNotBlank(text))
						data = Integer.valueOf(text);
				}
			} finally {
				System.out.println();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	public String postForm(String requestUrl, String parameters) {
		String result = "";

		HttpClient client = HttpClients.createDefault();

		HttpPost httppost = new HttpPost(host + requestUrl);

		httppost.addHeader("content-type", "application/json");
		httppost.addHeader("Accept", "application/json");
		try {
			httppost.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
			System.out.println("executing request " + httppost.getURI());
			HttpResponse response = client.execute(httppost);
			System.out.println("statusCode：" + response.getStatusLine().getStatusCode());
			Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++)
				System.out.println(headers[i].getName() + ":" + headers[i].getValue());
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
					System.out.println("--------------------------------------" + result);
					System.out.println("--------------------------------------");
					System.out.println("--------------------------------------");
				}
			} finally {
				System.out.println();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println();
		}
		return result;
	}

	public String postForm(String requestUrl) {
		String result = "";

		HttpClient client = HttpClients.createDefault();

		HttpGet request = new HttpGet(host + requestUrl);
		request.addHeader("content-type", "application/json");
		request.addHeader("Accept", "application/json");
		try {
			System.out.println("executing request " + request.getURI());
			HttpResponse response = client.execute(request);
			System.out.println("statusCode：" + response.getStatusLine().getStatusCode());
			Header[] headers = response.getAllHeaders();
			for (int i = 0; i < headers.length; i++)
				System.out.println(headers[i].getName() + ":" + headers[i].getValue());
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
					System.out.println("--------------------------------------" + result);
					System.out.println("--------------------------------------");
					System.out.println("--------------------------------------");
				}
			} finally {
				System.out.println();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println();
		}
		return result;
	}

	/**
	 * get请求
	 * @param requestUrl
	 * @return
	 */
	public static HttpEntity httpGet(String requestUrl) {
		HttpEntity httpEntity = null;
		try {
			// 创建httpClient
			HttpClient httpClient = HttpClients.createDefault();
			
			// 声明并初始化request
			HttpGet request = new HttpGet(host + requestUrl);
			request.addHeader("content-type", "application/json");
			request.addHeader("Accept", "application/json");
			// System.out.println("executing request " + request.getURI());
			
			//设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
            request.setConfig(requestConfig);
            
            // 执行Http请求
            HttpResponse httpResponse = httpClient.execute(request);
			
			if(httpResponse != null) {
				httpEntity = httpResponse.getEntity();
			}
		} catch (ClientProtocolException e) {
			logger.error("Get请求" + host + requestUrl + "发生异常, Exception info is " + e);
		} catch (IOException e) {
			logger.error("Get请求" + host + requestUrl + "发生异常, Exception info is " + e);
		}
		return httpEntity;
	}

	/**
	 * post请求
	 * @param requestUrl
	 * @return
	 */
	public static HttpEntity httpPost(String requestUrl) {
		HttpEntity httpEntity = null;
		try {
			// 创建httpClient
			HttpClient httpClient = HttpClients.createDefault();
			
			// 声明并初始化request
			HttpPost request = new HttpPost(host + requestUrl);
			request.addHeader("content-type", "application/json");
			request.addHeader("Accept", "application/json");
			// System.out.println("executing request " + request.getURI());
			
			//设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
            request.setConfig(requestConfig);
            
            // 执行Http请求
            HttpResponse httpResponse = httpClient.execute(request);
			
			if(httpResponse != null) {
				httpEntity = httpResponse.getEntity();
			}
		} catch (ClientProtocolException e) {
			logger.error("Post请求" + host + requestUrl + "发生异常, Exception info is " + e);
		} catch (IOException e) {
			logger.error("Post请求" + host + requestUrl + "发生异常, Exception info is " + e);
		}
		return httpEntity;
	}
	
	/**
	 * post请求
	 * @param requestUrl
	 * @return
	 */
	public static HttpEntity httpPost(String requestUrl, JsonObject jsonObject) {
		HttpEntity httpEntity = null;
		try {
			// 创建httpClient
			HttpClient httpClient = HttpClients.createDefault();
			
			// 声明并初始化request
			HttpPost request = new HttpPost(host + requestUrl);
			request.addHeader("content-type", "application/json");
			request.addHeader("Accept", "application/json");
			// System.out.println("executing request " + request.getURI());
			
			// 请求体
            StringEntity entity = new StringEntity(gson.toJson(jsonObject),"utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");    
            entity.setContentType("application/json");    
            request.setEntity(entity); 
			
			//设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
            request.setConfig(requestConfig);
            
            // 执行Http请求
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
	
	
	/**
	 * 1、	统计信息：暂予监外执行、社会医院、寄押、
	 *		离监探亲、脱逃捕回、解回再审
	 *		Type：stat_zfxx
     *
	 * @param time 调用接口日期
	 * @param corp 监狱标识
	 * @param scorp 权限范围
	 * @param dept 监区标识
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static List<ZfStatDto> entityZfStat(String time, String corp,String scorp,String dept) throws RESTHttpClientException {
		
		List<ZfStatDto> zfStatDtoList = null;
		
		try {
			if(time != null && corp != null) {
				// Description: 请求Url拼接参数
				StringBuffer requestUrl = new StringBuffer("/zfxx/statistics/stat_zfxx");
//				
//				// 参数封装
				JsonObject params = new JsonObject();
				params.addProperty("scope", scorp);
				params.addProperty("time", time);
				params.addProperty("corp", corp);
				params.addProperty("dept", dept);
				
				HttpEntity httpEntity = httpPost(requestUrl.toString(), params);
				
				String content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
//				String content = "{\"code\":\"200\",\"msg\":\"统计耗时[35]毫秒\",\"data\":{\"count\":899,\"type\":\"stat_zfxx\",\"items\":[{\"id\":\"ZFXX_JWZX\",\"name\":\"监外执行人数\",\"value\":6,\"percent\":\"0.67%\"},{\"id\":\"ZFXX_JYZF\",\"name\":\"寄押人数\",\"value\":3,\"percent\":\"0.33%\"},{\"id\":\"ZFXX_LJTQ\",\"name\":\"离监探亲人数\",\"value\":0,\"percent\":\"0.00%\"},{\"id\":\"ZFXX_TTBH\",\"name\":\"脱逃人数\",\"value\":0,\"percent\":\"0.00%\"},{\"id\":\"ZFXX_THDJ\",\"name\":\"解回再审人数\",\"value\":1,\"percent\":\"0.11%\"},{\"id\":\"ZFXX_JZZY\",\"name\":\"就诊住院人数\",\"value\":1,\"percent\":\"0.11%\"}]}}";
//				System.out.println("content = "+content);
				
				JsonObject contentJson = gson.fromJson(content, JsonObject.class);
//				System.out.println("contentJson = "+contentJson);
				
				// 返回结果编码
				if(contentJson != null) {
					String returnCode = Util.notNull(contentJson.get("code")) ? contentJson.get("code").getAsString() : "";
//					String returnCode = "200";
					System.out.println("returnCode ===> "+returnCode);
					
					// 200表示请求成功
					if("200".equals(returnCode)) {
						// 返回数据data
						JsonObject returnData = Util.notNull(contentJson.get("data")) ? contentJson.getAsJsonObject("data") : null;
//						JsonObject returnData = gson.fromJson("{\"count\":899,\"type\":\"stat_zfxx\",\"items\":[{\"id\":\"ZFXX_JWZX\",\"name\":\"监外执行人数\",\"value\":6,\"percent\":\"0.67%\"},{\"id\":\"ZFXX_JYZF\",\"name\":\"寄押人数\",\"value\":3,\"percent\":\"0.33%\"},{\"id\":\"ZFXX_LJTQ\",\"name\":\"离监探亲人数\",\"value\":0,\"percent\":\"0.00%\"},{\"id\":\"ZFXX_TTBH\",\"name\":\"脱逃人数\",\"value\":0,\"percent\":\"0.00%\"},{\"id\":\"ZFXX_THDJ\",\"name\":\"解回再审人数\",\"value\":1,\"percent\":\"0.11%\"},{\"id\":\"ZFXX_JZZY\",\"name\":\"就诊住院人数\",\"value\":1,\"percent\":\"0.11%\"}]}", JsonObject.class);
						Integer count =  Integer.valueOf(returnData.get("count").getAsString());
						
						if(returnData != null) {
							
							JsonArray defaultZfStatObject = (JsonArray) returnData.get("items");
							zfStatDtoList = new ArrayList<ZfStatDto>();
							
							for(JsonElement defaultZfStatElement : defaultZfStatObject) {
//								System.out.println("defaultZfStatElement ---》"+defaultZfStatElement);
								
								// 罪犯统计信息 转 罪犯统计信息DTO
								ZfStatDto zfStatDto = Util.fromJson(defaultZfStatElement, ZfStatDto.class);
								System.out.println("zfStatDto = " +zfStatDto);
								
								if(zfStatDto != null ) {
//									// 将url中的时间参数存入dto
									zfStatDto.setDUrlTime(time);
									zfStatDto.setCount(count);
									zfStatDtoList.add(zfStatDto);
									System.out.println("统计数据："+zfStatDtoList.size());
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RESTHttpClientException("调用ZfStatHttpClient获取罪犯统计信息发生异常, Exception info is " + e);
		}
		return zfStatDtoList;
	}
}
