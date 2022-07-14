package com.cesgroup.prison.jobs.utils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.prison.jobs.constants.Synchro;

public class ZfywHttpClientUtil {

	public static List<Map<String, Object>> getData(String type, String corp, String time) {
		List<Map<String, Object>> dataList = null;
		String _root = HttpClientUtil.doGet(Synchro.HOST_ZFYW + type + "?corp=" + corp + "&time=" + time);
		if (_root != null) {
			JSONObject jsonObject = JSONObject.parseObject(_root);
		    Map<String, Object> root = (Map<String, Object>)jsonObject;
		    if (root != null) {
		    	String code = (String)root.get("code");
			    if (Synchro.RETURN_CODE_SUCCESS.equals(code)) {
			    	int count = (Integer)root.get("count");
			    	if (count > 0) {
			    		Map<String, Object> data = (Map<String, Object>)root.get("data");
			    		if (data != null) {
			    			dataList = (List<Map<String, Object>>)data.get("data");
			    		}
			    	}
			    }
		    }
		}
	    return dataList;
	}
	
	public static Map<String, Object> getDzwp(String type, String secCode, String period) {
		Map<String, Object> data = null;
		String requestUrl = Synchro.HOST_DZWP  + "?type=" + type + "&secCode=" + secCode;
		if (period != null) {
			requestUrl = requestUrl + "&period=" + period;
		}
		String _root = HttpClientUtil.doGet(requestUrl);
		if (_root != null) {
			JSONArray jsonArray = JSONArray.parseArray(_root);
			List list = (List)jsonArray;
			if (list != null && list.size() > 0) {
				data = (Map<String, Object>)list.get(0);
			}
		}
	    return data;
	}
	
	public static void main(String[] args) throws Exception {
		//photo();
		getDzwp(null, null, null);
	}
	
	public static void synchroJyList() {
		String corpsUrl = "http://192.168.8.39:6789/uim-api/api/corps/";
		System.out.println(HttpClientUtil.doGet(corpsUrl));
	}
	
	
	public static void synchro() {
		System.out.println("========start=======");
		List<String> corps = new ArrayList<String>();
		corps.add("73878031185de329fa6876725c100000");
		corps.add("73878031185de329fa6876725c100001");
		corps.add("73878031185de329fa6876725c100002");
		corps.add("73878031185de329fa6876725c100003");
		corps.add("73878031185de329fa6876725c100004");
		corps.add("73878031185de329fa6876725c100006");
		corps.add("73878031185de329fa6876725c100005");
		corps.add("73878031185de329fa6876725c100007");
		corps.add("73878031185de329fa6876725c100104");
		corps.add("73878031185de329fa6876725c100101");
		corps.add("73878031185de329fa6876725c100105");
		corps.add("73878031185de329fa6876725c100103");
		corps.add("73878031185de329fa6876725c100107");
		List<Map<String, Object>> list = null;
		for (String corp : corps) {
			list = getData("entity_zf_ttbh_sj", corp, "2019-04-24");
			System.out.println("============>" + list);
		}
		System.out.println("========end=======");
	}
	
	public static void photo() throws Exception {
		HttpEntity httpEntity = httpGet("http://192.168.8.242:7070/jy-storage/storage/displayZfzp/6EE87FE8275CE42F8CA014D958B6108F");
		InputStream inputStream = httpEntity.getContent();
		FileUtils.copyToFile(inputStream, new File("C:\\work\\zfPhoto\\6EE87FE8275CE42F8CA014D958B6108F.jpg"));
		inputStream.close();
	}
	
	public static HttpEntity httpGet(String requestUrl) {
		HttpEntity httpEntity = null;
		try {
			// 创建httpClient
			HttpClient httpClient = HttpClients.createDefault();
			
			// 声明并初始化request
			HttpGet request = new HttpGet(requestUrl);
			
			//设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
            request.setConfig(requestConfig);
            
            // 执行Http请求
            HttpResponse httpResponse = httpClient.execute(request);
			
			if (httpResponse != null) {
				httpEntity = httpResponse.getEntity();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpEntity;
	}
	
}
