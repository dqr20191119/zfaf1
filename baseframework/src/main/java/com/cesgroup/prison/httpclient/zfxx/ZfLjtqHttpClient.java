package com.cesgroup.prison.httpclient.zfxx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cesgroup.prison.httpclient.zfxx.dto.ZfJbxxDto;
import com.cesgroup.prison.httpclient.zfxx.dto.ZfLjtqDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ZfLjtqHttpClient {
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
	
	private static ZfLjtqHttpClient currentWSHttpClient = new ZfLjtqHttpClient();
	
	private ZfLjtqHttpClient() {
		
	}
	
	private ZfLjtqHttpClient(String host) {
		ZfLjtqHttpClient.host = host;
	}
	
	public static synchronized ZfLjtqHttpClient getWSHttpClient(String host) {
		ZfLjtqHttpClient.host = host;
		return currentWSHttpClient;
	}
	
	public static synchronized ZfLjtqHttpClient getWSHttpClient() {
		if(currentWSHttpClient != null) {
			return currentWSHttpClient;
		}
		return new ZfLjtqHttpClient();
	}
	
	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		ZfLjtqHttpClient.host = host;
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
        /*    StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");    
            entity.setContentType("application/json");    
            request.setEntity(entity); */
			
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

	
	public static List<ZfLjtqDto> entityZfLjtq(String time, String corp, String scorp, String dept) throws RESTHttpClientException {
		// 罪犯离监探亲信息DTO
		List<ZfLjtqDto> zfLjtqDtoList = new ArrayList<ZfLjtqDto>();
		try {
			if(time != null && corp != null) {
				// Description: 请求Url拼接参数
				StringBuffer requestUrl = new StringBuffer("/zfxx/entity/entity_zf_ljtq_sj");
				
				// 参数封装
				JsonObject params = new JsonObject();
				params.addProperty("time", time);
				params.addProperty("corp", corp);
				Map<String,String> map = new HashMap<String,String>();
				map.put("corp", corp);
				map.put("time", time);
				HttpEntity httpEntity = httpPost(requestUrl.toString()+"?corp="+corp+"&time="+time, params);
				
				String content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";

				JsonObject contentJson = gson.fromJson(content, JsonObject.class);
				
				// 返回结果编码
				if(contentJson != null) {
					String returnCode = Util.notNull(contentJson.get("code")) ? contentJson.get("code").getAsString() : "";
					System.out.println("returnCode ===> "+returnCode);

					if("200".equals(returnCode)) {
						// 返回数据data
						JsonObject returnData = Util.notNull(contentJson.get("data")) ? contentJson.getAsJsonObject("data") : null;
						
						if(returnData != null) {
							// 罪犯基本信息字段说明与数据信息（字段说明：members；数据信息：data）
							JsonArray defaultZfJbxxArray = Util.notNull(returnData.get("data")) ? returnData.getAsJsonArray("data") : null;
							
							if(defaultZfJbxxArray != null && defaultZfJbxxArray.size() > 0) {
								// 罪犯离监探亲信息Dto list初始化
								for(JsonElement defaultZfJbxxElement : defaultZfJbxxArray) {
									// 罪犯离监探亲信息
									JsonObject defaultZfJbxxObject = Util.notNull(defaultZfJbxxElement.getAsJsonObject().get("Default_ZfJbxx")) ? defaultZfJbxxElement.getAsJsonObject().getAsJsonObject("Default_ZfJbxx") : null;
									
									// 罪犯离监探亲信息转罪犯离监探亲信息DTO
									ZfJbxxDto zfJbxxDto = Util.fromJson(defaultZfJbxxObject, ZfJbxxDto.class);
									
									if(zfJbxxDto != null && Util.notNull(zfJbxxDto.getCId())) {
										// 将url中的时间参数存入dto
										zfJbxxDto.setDUrlTime(time);
										
										// 罪犯离监探亲信息Json数组
										JsonArray defaultZfLjtqArray = Util.notNull(defaultZfJbxxObject.get("Default_ZfLjtq")) ? defaultZfJbxxObject.getAsJsonArray("Default_ZfLjtq") : null;
										
										// 罪犯离监探亲信息Json数组转化为罪犯离监探亲DtoList
										List<ZfLjtqDto> zfLjtqDtoTempList = ZfLjtqHttpClient.zfLjtqJsonArrayToZfLjtqDtoList(zfJbxxDto, defaultZfLjtqArray);
										
										// 罪犯离监探亲信息Dto list存值
										zfLjtqDtoList.addAll(zfLjtqDtoTempList);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RESTHttpClientException("调用ZfLbcHttpClient获取罪犯离监探亲信息发生异常, Exception info is " + e);
		}
		return zfLjtqDtoList;
	}

	/**
	 * 罪犯离监探亲信息Json数组转化为罪犯离监探亲信息DtoList
	 * 
	 * @param defaultZfLbcArray
	 * @return
	 */
	private static List<ZfLjtqDto> zfLjtqJsonArrayToZfLjtqDtoList(ZfJbxxDto zfJbxxDto, JsonArray defaultZfJzzyArray) {
		List<ZfLjtqDto> zfLjtqDtoList = new ArrayList<ZfLjtqDto>();
		if(zfJbxxDto != null && defaultZfJzzyArray != null && defaultZfJzzyArray.size() > 0) {
			for(JsonElement defaultZfLjtqElement : defaultZfJzzyArray) {
				ZfLjtqDto zfLjtqDto = Util.fromJson(defaultZfLjtqElement, ZfLjtqDto.class);
				if(zfLjtqDto != null) {
					zfLjtqDto.setCZfbh(zfJbxxDto.getCZfbh());
					zfLjtqDto.setCId(zfJbxxDto.getCId());
					zfLjtqDto.setDUrlTime(zfJbxxDto.getDUrlTime());
					zfLjtqDtoList.add(zfLjtqDto);
				}
			}
		}
		return zfLjtqDtoList;
	}
}
