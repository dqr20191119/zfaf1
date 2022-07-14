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
import org.apache.http.ParseException;
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
import com.cesgroup.prison.httpclient.zfxx.dto.ZfStatZyDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Description: 暂押罪犯统计信息调用HttpClient工具类
 * @author zhou.jian
 *
 * @Date 2019年2月21日
 */
public class ZfStatZyHttpClient {

	/**
	 * 罪犯信息接口地址
	 */
	private static String host = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.zfxx.url"); //"http://192.168.8.90:80/jy-query/api/v1"
    /**
     * 日志工具
     */
    private static final Logger logger = LoggerFactory.getLogger(ZfStatZyHttpClient.class);
	/**
	 * Gson工具
	 */
	private final static Gson gson = new GsonBuilder().create();
	
	private static ZfStatZyHttpClient currentWSHttpClient = new ZfStatZyHttpClient();
	
	private ZfStatZyHttpClient() {
		
	}
	
	private ZfStatZyHttpClient(String host) {
		ZfStatZyHttpClient.host = host;
	}
	
	public static synchronized ZfStatZyHttpClient getWSHttpClient(String host) {
		ZfStatZyHttpClient.host = host;
		return currentWSHttpClient;
	}
	
	public static synchronized ZfStatZyHttpClient getWSHttpClient() {
		if(currentWSHttpClient != null) {
			return currentWSHttpClient;
		}
		return new ZfStatZyHttpClient();
	}
	
	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		ZfStatZyHttpClient.host = host;
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
	 * 2、	统计信息：暂押
	 *		Type：stat_zfdd_zy
     *
	 * @param time 调用接口日期
	 * @param corp 监狱标识
	 * @param scorp 权限范围
	 * @param dept 监区标识
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static List<ZfStatZyDto> entityZfStatZy(String time, String corp,String scorp,String dept) throws RESTHttpClientException {
		
		List<ZfStatZyDto> dtoList = null;
		
		try {
			
			dtoList = stat(time,corp,scorp,dept,"stat_zfdd_zy");
			
		} catch (Exception e) {
			throw new RESTHttpClientException("2.调用ZfStatZyHttpClient，获取【暂押罪犯】统计信息发生异常, Exception info is " + e);
		}
		return dtoList;
	}
	
	
	/**
	 * 3、	统计信息：【罪犯调动】
	 *		Type：stat_zfdd
	 *
	 * @param time 调用接口日期
	 * @param corp 监狱标识
	 * @param scorp 权限范围
	 * @param dept 监区标识
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static List<ZfStatZyDto> entityZfStatZfdd(String time, String corp,String scorp,String dept) throws RESTHttpClientException {
		
		List<ZfStatZyDto> dtoList = null;
		
		try {
			
			dtoList = stat(time,corp,scorp,dept,"stat_zfdd");
			
		} catch (Exception e) {
			throw new RESTHttpClientException("3.调用ZfStatZyHttpClient，获取【罪犯调动】统计信息发生异常, Exception info is " + e);
		}
		return dtoList;
	}

	
	
	/**
	 * 4、	统计信息：在押情况
	 *		Type：stat_zf_zyqk_v1
     *
	 * @param time 调用接口日期
	 * @param corp 监狱标识
	 * @param scorp 权限范围
	 * @param dept 监区标识
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static List<ZfStatZyDto> entityZfStatZyqk(String time, String corp,String scorp,String dept) throws RESTHttpClientException {
		
		List<ZfStatZyDto> dtoList = null;
		
		try {
			
			dtoList = stat(time,corp,scorp,dept,"stat_zf_zyqk_v1");
			
		} catch (Exception e) {
			throw new RESTHttpClientException("4.调用ZfStatZyHttpClient，获取【在押罪犯】统计信息发生异常, Exception info is " + e);
		}
		return dtoList;
	}
	
	/**
	 * 重构方法
	 * @param time
	 * @param corp
	 * @param scorp
	 * @param dept
	 * @param type
	 * @return
	 */
	private static List<ZfStatZyDto> stat(String time, String corp,String scorp,String dept,String type) {
		List<ZfStatZyDto> dtoList = null;
		
		if(corp != null) {
			// Description: 请求Url拼接参数
			StringBuffer requestUrl = new StringBuffer("/zfxx/statistics/");
			requestUrl.append(type);
			
			// 参数封装
			JsonObject params = new JsonObject();
			params.addProperty("scope", scorp);
			params.addProperty("time", time);
			params.addProperty("corp", corp);
			params.addProperty("dept", dept);
			
			HttpEntity httpEntity = httpPost(requestUrl.toString(), params);
			
			String content;
			try {
				content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
				JsonObject contentJson = gson.fromJson(content, JsonObject.class);
//				System.out.println("contentJson = "+contentJson);
				
				// 返回结果编码
				if(contentJson != null) {
					String returnCode = Util.notNull(contentJson.get("code")) ? contentJson.get("code").getAsString() : "";
					System.out.println("returnCode ===> "+returnCode);
					
					// 200表示请求成功
					if("200".equals(returnCode)) {
						// 返回数据data
						JsonObject returnData = Util.notNull(contentJson.get("data")) ? contentJson.getAsJsonObject("data") : null;
						Integer count =  Integer.valueOf(returnData.get("count").getAsString());
						
						if(returnData != null) {
							
							JsonArray defaultZfStatObject = (JsonArray) returnData.get("items");
							dtoList = new ArrayList<ZfStatZyDto>();
							
							for(JsonElement defaultZfStatElement : defaultZfStatObject) {
								
								// 罪犯统计信息 转 罪犯统计信息DTO
								ZfStatZyDto dto = Util.fromJson(defaultZfStatElement, ZfStatZyDto.class);
								System.out.println("dto ===> " +dto);
								
								if(dto != null ) {
//									// 将url中的时间参数存入dto
									dto.setDUrlTime(time);
									dto.setCount(count);
									dtoList.add(dto);
									System.out.println("统计数据："+dtoList.size());
								}
							}
						}
					}
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dtoList;
	}
	
	
	private static List<ZfStatZyDto> statJyGynl(String type,String key) {
		List<ZfStatZyDto> dtoList = null;
		
		if(type != null) {
			// Description: 请求Url拼接参数
			StringBuffer requestUrl = new StringBuffer("/zfxx/statistics/");
			requestUrl.append(type);
			
			// 参数封装
			JsonObject params = new JsonObject();
			params.addProperty("user", key);
			
			HttpEntity httpEntity = httpPost(requestUrl.toString(), params);
			
			String content;
			try {
				content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
				JsonObject contentJson = gson.fromJson(content, JsonObject.class);
//				System.out.println("contentJson = "+contentJson);
				
				// 返回结果编码
				if(contentJson != null) {
					String returnCode = Util.notNull(contentJson.get("code")) ? contentJson.get("code").getAsString() : "";
					System.out.println("returnCode ===> "+returnCode);
					
					// 200表示请求成功
					if("200".equals(returnCode)) {
						// 返回数据data
						JsonObject returnData = Util.notNull(contentJson.get("data")) ? contentJson.getAsJsonObject("data") : null;
						Integer count =  Integer.valueOf(returnData.get("count").getAsString());
						
						if(returnData != null) {
							
							JsonArray defaultZfStatObject = (JsonArray) returnData.get("items");
							dtoList = new ArrayList<ZfStatZyDto>();
							
							for(JsonElement defaultZfStatElement : defaultZfStatObject) {
								
								// 罪犯统计信息 转 罪犯统计信息DTO
								ZfStatZyDto dto = Util.fromJson(defaultZfStatElement, ZfStatZyDto.class);
								System.out.println("dto ===> " +dto);
								
								if(dto != null ) {
//									// 将url中的时间参数存入dto
									dto.setDUrlTime(Util.toStr(Util.toDate(Util.getCurrentDate(), Util.DF_DATE), Util.DF_DATE));
									dto.setCount(count);
									dtoList.add(dto);
									System.out.println("统计数据："+dtoList.size());
								}
							}
						}
					}
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dtoList;
	}
	
	
	/**
	 * 5、	统计信息：性别情况
	 *		Type：stat_zf_xb
	 *
	 * @param time 调用接口日期
	 * @param corp 监狱标识
	 * @param scorp 权限范围
	 * @param dept 监区标识
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static List<ZfStatZyDto> entityZfStatZyXb(String time, String corp,String scorp,String dept) throws RESTHttpClientException {
		
		List<ZfStatZyDto> dtoList = null;
		
		try {
			dtoList = stat(time,corp,scorp,dept,"stat_zf_xb");
		} catch (Exception e) {
			throw new RESTHttpClientException("5.调用ZfStatZyHttpClient，获取【性别罪犯】统计信息发生异常, Exception info is " + e);
		}
		return dtoList;
	}
	
	/**
	 * 6、	统计信息：年龄情况
	 *		Type：stat_zf_nl
	 *
	 * @param time 调用接口日期
	 * @param corp 监狱标识
	 * @param scorp 权限范围
	 * @param dept 监区标识
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static List<ZfStatZyDto> entityZfStatZyNl(String time, String corp,String scorp,String dept) throws RESTHttpClientException {
		
		List<ZfStatZyDto> dtoList = null;
		
		try {
			dtoList = stat(time,corp,scorp,dept,"stat_zf_nl");
		} catch (Exception e) {
			throw new RESTHttpClientException("6.调用ZfStatZyHttpClient，获取【罪犯年龄】统计信息发生异常, Exception info is " + e);
		}
		return dtoList;
	}
	
	/**
	 * 7、	统计信息：罪名情况
	 *		Type：stat_zf_zm
	 *
	 * @param time 调用接口日期
	 * @param corp 监狱标识
	 * @param scorp 权限范围
	 * @param dept 监区标识
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static List<ZfStatZyDto> entityZfStatZyZm(String time, String corp,String scorp,String dept) throws RESTHttpClientException {
		
		List<ZfStatZyDto> dtoList = null;
		
		try {
			dtoList = stat(time,corp,scorp,dept,"stat_zf_zm");
		} catch (Exception e) {
			throw new RESTHttpClientException("7.调用ZfStatZyHttpClient，获取【罪名】统计信息发生异常, Exception info is " + e);
		}
		return dtoList;
	}
	
	/**
	 * 8、	统计信息：原判刑期
	 *		Type：stat_zf_ypxq
	 *
	 * @param time 调用接口日期
	 * @param corp 监狱标识
	 * @param scorp 权限范围
	 * @param dept 监区标识
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static List<ZfStatZyDto> entityZfStatZyYpxq(String time, String corp,String scorp,String dept) throws RESTHttpClientException {
		
		List<ZfStatZyDto> dtoList = null;
		
		try {
			dtoList = stat(time,corp,scorp,dept,"stat_zf_ypxq");
		} catch (Exception e) {
			throw new RESTHttpClientException("8.调用ZfStatZyHttpClient，获取【原判刑期】统计信息发生异常, Exception info is " + e);
		}
		return dtoList;
	}
	
	/**
	 * 9、	统计信息：剩余刑期
	 *		Type：stat_zf_syxq
	 *
	 * @param time 调用接口日期
	 * @param corp 监狱标识
	 * @param scorp 权限范围
	 * @param dept 监区标识
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static List<ZfStatZyDto> entityZfStatZySyxq(String time, String corp,String scorp,String dept) throws RESTHttpClientException {
		
		List<ZfStatZyDto> dtoList = null;
		
		try {
			dtoList = stat(time,corp,scorp,dept,"stat_zf_syxq");
		} catch (Exception e) {
			throw new RESTHttpClientException("9.调用ZfStatZyHttpClient，获取【剩余刑期】统计信息发生异常, Exception info is " + e);
		}
		return dtoList;
	}
	
	/**
	 * 10、	统计信息：【监狱关押能力】
	 *		Type：stat_zf_gynl
	 *
	 * @param time 调用接口日期
	 * @param corp 监狱标识
	 * @param scorp 权限范围
	 * @param dept 监区标识
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static List<ZfStatZyDto> entityZfStatZyJyGynl() throws RESTHttpClientException {
		
		List<ZfStatZyDto> dtoList = null;
		
		try {
			dtoList = statJyGynl("stat_zf_gynl","00000000000000000000000000000000");
		} catch (Exception e) {
			throw new RESTHttpClientException("10.调用ZfStatZyHttpClient，获取【监狱关押能力】统计信息发生异常, Exception info is " + e);
		}
		return dtoList;
	}
	
	/**
	 * 11、	统计信息：各监狱关押人数
	 *		Type：stat_zf_zyqk_v2
	 *
	 * @param time 调用接口日期
	 * @param corp 监狱标识
	 * @param scorp 权限范围
	 * @param dept 监区标识
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static List<ZfStatZyDto> entityZfStatZyJygyqk(String time, String corp,String scorp,String dept) throws RESTHttpClientException {
		
		List<ZfStatZyDto> dtoList = null;
		
		try {
			dtoList = stat(time,corp,scorp,dept,"stat_zf_zyqk_v2");
		} catch (Exception e) {
			throw new RESTHttpClientException("11.调用ZfStatZyHttpClient，获取【各监狱关押人数】统计信息发生异常, Exception info is " + e);
		}
		return dtoList;
	}
	
	/**
	 * 12、	统计信息：各监狱改造情况
	 *		Type：stat_zf_gzqk
	 *
	 * @param time 调用接口日期
	 * @param corp 监狱标识
	 * @param scorp 权限范围
	 * @param dept 监区标识
	 * @return
	 * @throws RESTHttpClientException
	 */
	public static List<ZfStatZyDto> entityZfStatZyJygzqk(String time, String corp,String scorp,String dept) throws RESTHttpClientException {
		
		List<ZfStatZyDto> dtoList = null;
		
		try {
			dtoList = stat(time,corp,scorp,dept,"stat_zf_gzqk");
		} catch (Exception e) {
			throw new RESTHttpClientException("12.调用ZfStatZyHttpClient，获取【各监狱改造情况】统计信息发生异常, Exception info is " + e);
		}
		return dtoList;
	}
	
	
}
