package com.cesgroup.prison.httpclient.clearCheck;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.exception.RESTHttpClientException;
import com.cesgroup.framework.utils.PropertiesUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.httpclient.clearCheck.dto.ClearDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Description: 清监检查记录调用HttpClient工具类
 * @author lincoln.cheng
 *
 * 2019年1月17日
 */
public class ClearCheckHttpClient {
	/**
	 * 罪犯信息接口地址
	 */
	private static String host = PropertiesUtil.getValueByKeyUnchanged("application", "clearCheck.url"); //"http://192.168.8.90:80/jy-query/api/v1"
    /**
     * 日志工具
     */
	private static final Logger logger = LoggerFactory.getLogger(ClearCheckHttpClient.class);
	/**
	 * Gson工具
	 */
	private final static Gson gson = new GsonBuilder().create();

	private static ClearCheckHttpClient currentWSHttpClient = new ClearCheckHttpClient();

	private ClearCheckHttpClient() {
	}
	
	private ClearCheckHttpClient(String host) {
		ClearCheckHttpClient.host = host;
	}

	public static synchronized ClearCheckHttpClient getWSHttpClient(String host) {
		ClearCheckHttpClient.host = host;
		return currentWSHttpClient;
	}

	public static synchronized ClearCheckHttpClient getWSHttpClient() {
		if (currentWSHttpClient != null) {
			return currentWSHttpClient;
		}
		return new ClearCheckHttpClient();
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		ClearCheckHttpClient.host = host;
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
					if (text != null && !text.isEmpty())
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
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		try {
			// 创建httpClient
			httpClient = HttpClients.createDefault();
			
			// 声明并初始化request
			HttpGet request = new HttpGet(host + requestUrl);
			request.addHeader("content-type", "application/json");
			request.addHeader("Accept", "application/json");
			// System.out.println("executing request " + request.getURI());
			
			//设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
            request.setConfig(requestConfig);
            
            // 执行Http请求
			httpResponse = httpClient.execute(request);
			
			if(httpResponse != null) {
				httpEntity = httpResponse.getEntity();
			}
		} catch (ClientProtocolException e) {
			logger.error("请求" + host + requestUrl + "发生异常, Exception info is " + e);
		} catch (IOException e) {
			logger.error("请求" + host + requestUrl + "发生异常, Exception info is " + e);
		} finally {
			if(httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					logger.error("关闭CloseableHttpClient发生异常, Exception info is " + e);
				}
			}
			if(httpResponse != null) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					logger.error("关闭CloseableHttpResponse发生异常, Exception info is " + e);
				}
			}
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
			logger.error("请求" + host + requestUrl + "发生异常, Exception info is " + e);
		} catch (IOException e) {
			logger.error("请求" + host + requestUrl + "发生异常, Exception info is " + e);
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
			request.addHeader("Content-Type", "application/json");
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
	 * Description: 获取罪犯基本信息
	 * @param time 查询日期
	 * @param corp 监狱标识
	 * @param qjName 
	 * @param statues 
	 * @param eTime 
	 * @param sTime 
	 * @return
	 * @throws RESTHttpClientException 
	 */
	public static PageInfo<ClearDto> entityQjjcjl(String time, String corp,PageRequest pageRequest, String sTime, String eTime, String statues, String qjName) throws RESTHttpClientException {
		PageInfo<ClearDto> clearDtoPage = new PageInfo<ClearDto>();
		int pageNum = 0,pageSize=0,pages=0,total=0;
		List<ClearDto> qjjcjlDtoList = null;
		try {
			if(time != null && corp != null) {
				// Description: 请求Url拼接参数
				StringBuffer requestUrl = new StringBuffer("/infoexc/clearcheck.do?action=getClearcheckList");
				
				// 查询条件
				JsonObject filter = new JsonObject();
				filter.addProperty("name", qjName);
				filter.addProperty("status", statues);

				filter.addProperty("starttime", sTime);
				filter.addProperty("endtime", eTime);
				
				// 分页参数
				JsonObject params = new JsonObject();
				params.addProperty("pageSize", pageRequest.getPageSize());
				params.addProperty("pageNo", pageRequest.getPageNumber());
				
				// 参数封装
				JsonObject data = new JsonObject();
				data.add("filter", filter);
				data.add("params", params);
				
				JsonObject json = new JsonObject();
				json.add("data", data);
				
				HttpEntity httpEntity = httpPost(requestUrl.toString(), json);
				String content = httpEntity != null ? EntityUtils.toString(httpEntity) : "";
				

				JsonObject contentJson = gson.fromJson(content, JsonObject.class);
				
				// 返回结果编码
				if(contentJson != null) {
					String returnCode = Util.notNull(contentJson.get("message")) ? contentJson.get("message").getAsString() : "";
					// "数据获取成功"表示请求成功
					if("数据获取成功".equals(returnCode)) {
						// 返回数据data
						JsonObject returnData = Util.notNull(contentJson.get("result")) ? contentJson.getAsJsonObject("result") : null;
						
						if(returnData != null) {
							
							JsonArray defaultQjjcjlArray = Util.notNull(returnData.get("rows")) ? returnData.getAsJsonArray("rows") : null;
							JsonObject pageInfo = Util.notNull(returnData.get("params")) ? returnData.getAsJsonObject("params") : null;//获取page信息
							if(pageInfo!=null) {
								pageNum=Util.notNull(pageInfo.get("pageNo")) ? pageInfo.get("pageNo").getAsInt() : 0;	
								pageSize=Util.notNull(pageInfo.get("pageSize")) ? pageInfo.get("pageSize").getAsInt() : 0;	
								pages=Util.notNull(pageInfo.get("totalPage")) ? pageInfo.get("totalPage").getAsInt() : 0;	
								total=Util.notNull(pageInfo.get("totalSize")) ? pageInfo.get("totalSize").getAsInt() : 0;
							}
							
							if(defaultQjjcjlArray != null && defaultQjjcjlArray.size() > 0) {
								// qj检查记录Dto list初始化
								
								qjjcjlDtoList = new ArrayList<ClearDto>();
								for(JsonElement defaultQjjcElement : defaultQjjcjlArray) {
									
									JsonObject defaultZfJbxxObject = Util.notNull(defaultQjjcElement.getAsJsonObject()) ? defaultQjjcElement.getAsJsonObject() : null;
								
									ClearDto ClearDto = Util.fromJson(defaultZfJbxxObject, ClearDto.class);
									if(ClearDto.getStatus().equals("Y")) {
										ClearDto.setStatus("正常");
									}else if(ClearDto.getStatus().equals("N")) {
										ClearDto.setStatus("异常");
									}
									if(ClearDto != null && Util.notNull(ClearDto.getId())) {
										qjjcjlDtoList.add(ClearDto);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RESTHttpClientException("调用ZfLjHttpClient获取罪犯离监信息发生异常, Exception info is " + e);
		}
		clearDtoPage.setList(qjjcjlDtoList);
		clearDtoPage.setPageNum(pageNum);
		clearDtoPage.setPages(pages);
		clearDtoPage.setPageSize(pageSize);
		clearDtoPage.setTotal(total);
		return clearDtoPage;
	}

	
}
