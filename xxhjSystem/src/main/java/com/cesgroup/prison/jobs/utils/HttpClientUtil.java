package com.cesgroup.prison.jobs.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.client.utils.URIBuilder;  
import org.apache.http.entity.ContentType;  
import org.apache.http.entity.StringEntity;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  

public class HttpClientUtil {  

  public static String doGet(String url, Map<String, String> param) {  

      // 创建Httpclient对象  
      CloseableHttpClient httpclient = HttpClients.createDefault();  

      String resultString = "";  
      CloseableHttpResponse response = null;  
      try {  
          // 创建uri  
          URIBuilder builder = new URIBuilder(url);  
          if (param != null) {  
              for (String key : param.keySet()) {  
                  builder.addParameter(key, param.get(key));  
              }  
          }  
          URI uri = builder.build();  

          // 创建http GET请求  
          HttpGet httpGet = new HttpGet(uri);  
          httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1");
          httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
          httpGet.setHeader("Accept-Encoding", "gzip,deflate,sdch");	//需要加上这个头字段
          httpGet.addHeader("Content-type","application/json; charset=utf-8");
          // 执行请求  
          response = httpclient.execute(httpGet);  
          // 判断返回状态是否为200  
          if (response.getStatusLine().getStatusCode() == 200) {  
        	  InputStream in = null;
				HttpEntity entity = response.getEntity();
				Header header = entity.getContentEncoding();
				if(header != null && header.getValue().equalsIgnoreCase("gzip")){	//判断返回内容是否为gzip压缩格式
					GzipDecompressingEntity gzipEntity = new GzipDecompressingEntity(entity);
					in = gzipEntity.getContent();
				}else{
					in = entity.getContent();
				}
				resultString = getHTMLContent(in);
          }  
      } catch (Exception e) {  
          e.printStackTrace();  
      } finally {  
          try {  
              if (response != null) {  
                  response.close();  
              }  
              httpclient.close();  
          } catch (IOException e) {  
              e.printStackTrace();  
          }  
      }  
      return resultString;  
  }  

  public static String doGet(String url) {  
      return doGet(url, null);  
  }  

	private static String getHTMLContent(InputStream in) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
		 br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
	
			String line = null;
			while((line=br.readLine())!=null){
				sb.append(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return sb.toString();
	}
  public static String doPost(String url, Map<String, String> param) {  
      // 创建Httpclient对象  
      CloseableHttpClient httpClient = HttpClients.createDefault();  
      CloseableHttpResponse response = null;  
      String resultString = "";  
      try {  
          // 创建Http Post请求  
          HttpPost httpPost = new HttpPost(url);  
          // 创建参数列表  
          if (param != null) {  
              List<NameValuePair> paramList = new ArrayList<>();  
              for (String key : param.keySet()) {  
                  paramList.add(new BasicNameValuePair(key, param.get(key)));  
              }  
              // 模拟表单  
              UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
              httpPost.setEntity(entity);  
          }  
          // 执行http请求  
          response = httpClient.execute(httpPost);  
          resultString = EntityUtils.toString(response.getEntity(), "utf-8");  
      } catch (Exception e) {  
          e.printStackTrace();  
      } finally {  
          try {  
              response.close();  
          } catch (IOException e) {  
              // TODO Auto-generated catch block  
              e.printStackTrace();  
          }  
      }  

      return resultString;  
  }  

  public static String doPost(String url) {  
      return doPost(url, null);  
  }  
    
  public static String doPostJson(String url, String json) {  
      // 创建Httpclient对象  
      CloseableHttpClient httpClient = HttpClients.createDefault();  
      CloseableHttpResponse response = null;  
      String resultString = "";  
      try {  
          // 创建Http Post请求  
          HttpPost httpPost = new HttpPost(url);  
          // 创建请求内容  
          StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);  
          httpPost.setEntity(entity);  
          // 执行http请求  
          response = httpClient.execute(httpPost);  
          resultString = EntityUtils.toString(response.getEntity(), "utf-8");  
      } catch (Exception e) {  
          e.printStackTrace();  
      } finally {  
          try {  
              response.close();  
          } catch (IOException e) {  
              // TODO Auto-generated catch block  
              e.printStackTrace();  
          }  
      }  

      return resultString;  
  }  
}  