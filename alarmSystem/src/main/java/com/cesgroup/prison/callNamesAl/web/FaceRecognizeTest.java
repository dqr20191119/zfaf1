//package com.cesgroup.prison.callNamesAl.web;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.http.converter.FormHttpMessageConverter;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.DefaultResponseErrorHandler;
//import org.springframework.web.client.RestTemplate;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.cesgroup.prison.callNamesAl.bean.CallNamesAlBean;
//import com.cesgroup.prison.callNamesAl.bean.CallNamesPrisonerAlBean;
//
///**
// * User: liuqiao.lq
// * Date: 2017/7/12
// * Time: 18:37
// */
//public class FaceRecognizeTest {
//
//	private static final String FACE_HOST = "http://33.168.120.245/face/api";
//	private static final String APPKEY = "app-xjjy";
//	private static final String GROUP_NAME = "6506";
////	private static final String FACE_HOST = "http://47.94.117.205/face/api";
////	private static final String APPKEY = "Test";
////	private static final String GROUP_NAME = "TestGroup";
//
//	private static RestTemplate restTemplate;
//
//	@BeforeClass
//	public static void init() {
//		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//		requestFactory.setReadTimeout(5000);
//		requestFactory.setConnectTimeout(5000);
//
//		// 添加转换器
//		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
//		messageConverters.add(new FormHttpMessageConverter());
//		messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
//		messageConverters.add(new MappingJackson2HttpMessageConverter());
//
//		restTemplate = new RestTemplate(messageConverters);
//		restTemplate.setRequestFactory(requestFactory);
//		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
//
//		System.err.println("SimpleRestClient初始化完成");
//	}
//
//	public Object send(String giveMoneyUrl, Object request, String type) {
//
//		/*
//		 * HttpHeaders headers = new HttpHeaders();
//		 * headers.setContentType(MediaType.APPLICATION_JSON); if (request instanceof
//		 * MultiValueMap) { HttpEntity<MultiValueMap<String, String>> requestEntity =
//		 * new HttpEntity<MultiValueMap<String, String>>( (MultiValueMap<String,
//		 * String>) request, headers); }
//		 */
//		String jsonString = null;
//		if (type.equals("1")) {
//			jsonString = restTemplate.postForObject(giveMoneyUrl, request, String.class);
//
//		}
//		if (type.equals("2")) {
//			jsonString = restTemplate.getForObject(giveMoneyUrl, String.class, request);
//		}
//		return jsonString;
//	}
//
//	@Test
//	public void detectFace() throws UnsupportedEncodingException {
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//		params.add("appKey", APPKEY);
//		params.add("requestId", UUID.randomUUID().toString());
//		params.add("method", "DETECT");
//		params.add("picUrls", "[\"https://img.alicdn.com/tfs/TB1Z.8meDvI8KJjSspjXXcgjXXa-600-600.jpg\"]");
//		// String temp = "raw://1920,1080," +
//		// Base64.getEncoder().encodeToString(HttpConnector.get("http://video-analysis-huabei2.oss-cn-beijing.aliyuncs.com/test/gray_data"));
//		// param.add("picData", "[\"" + temp + "\"]");
//		params.add("isDeepAnalysis", "true");
//		System.out.println(this.send(FACE_HOST, params, "1"));
//	}
//
//	@Test
//	public void compareFace() throws UnsupportedEncodingException {
//		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
//		param.add("appKey", APPKEY);
//		param.add("requestId", UUID.randomUUID().toString());
//		param.add("method", "COMPARE");
//		JSONObject obj = new JSONObject();
//
//		/*
//		 * String temp = Base64.getEncoder().encodeToString( HttpConnector.get(
//		 * "https://img.alicdn.com/tfs/TB1Z.8meDvI8KJjSspjXXcgjXXa-600-600.jpg"));
//		 * String temp2 = Base64.getEncoder().encodeToString( HttpConnector.get(
//		 * "https://img.alicdn.com/tfs/TB1wQFbeBDH8KJjSspnXXbNAVXa-800-800.jpg"));
//		 * obj.add(temp, temp2);
//		 */
//
//		// obj.add("https://img.alicdn.com/tfs/TB1Z.8meDvI8KJjSspjXXcgjXXa-600-600.jpg",
//		// "https://img.alicdn.com/tfs/TB1wQFbeBDH8KJjSspnXXbNAVXa-800-800.jpg");
//		param.add("picUrls", obj.toJSONString());
//		param.add("isIdcSmall", "true");
//		System.out.println(this.send(FACE_HOST, param, "1"));
//	}
//
//	// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
//	private String ImageToBase64ByLocal(String imgFile) {
//		InputStream in = null;
//		byte[] data = null;
//		// 读取图片字节数组
//		try {
//			in = new FileInputStream(imgFile);
//			data = new byte[in.available()];
//			in.read(data);
//			in.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		// 对字节数组Base64编码
//		return Base64.getEncoder().encodeToString(data);// 返回Base64编码过的字节数组字符串
//	}
//
//	@Test
//	public void registerFace() throws UnsupportedEncodingException {
//		for (int index = 0; index < 1; index++) {
//			MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
//			param.add("appKey", APPKEY);
//			param.add("requestId", "requestIdWangQin");
//			param.add("method", "REGISTER");
//			param.add("groupName", GROUP_NAME);
//			param.add("isQualityLimit", "false");
//			List<JSONObject> picInfos = new LinkedList<>();
//			JSONObject picInfo = new JSONObject();
//			// String temp =
//
//			// picInfo.add("picture", temp);
//			picInfo.put("imageId", "imageIdWangqiang");
//			picInfo.put("picture", ImageToBase64ByLocal("C:\\Users\\Tory\\Desktop\\FaceRegister\\6506815707.jpg"));
//			// picInfo.put("picture",
//			// "https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=15dcae6ebb7eca8012053ee1a918f0e0/ac4bd11373f08202fd62997240fbfbedaa641b8e.jpg");
//			picInfo.put("userId", "6506815707");
//			picInfos.add(picInfo);
//
///*			JSONObject picInfo1 = new JSONObject();
//			picInfo1.put("imageId", "imageIdQinyu");
//			picInfo1.put("picture", ImageToBase64ByLocal("C:\\Users\\Tory\\Desktop\\FaceRegister\\qinyu.jpg"));
//			picInfo1.put("userId", "userIdQinyu");
//			picInfos.add(picInfo1);*/
//
///*			JSONObject picInfo2 = new JSONObject();
//			picInfo2.put("imageId", "7894");
//			picInfo2.put("picture", ImageToBase64ByLocal("C:\\Users\\Tao\\Desktop\\人脸识别\\222.jpg"));
//			picInfo2.put("userId", "7894");
//			picInfos.add(picInfo2);*/
//
//			param.add("picInfo", JSON.toJSONString(picInfos));
////			System.out.println(param);
//			System.out.println(this.send(FACE_HOST, param, "1"));
//		}
//	}
//
//	@Test
//	public void queryFace() throws UnsupportedEncodingException {
//		HashMap<String, String> param = new HashMap<>();
//		param.put("appKey", APPKEY);
//		param.put("requestId", UUID.randomUUID().toString());
//		param.put("method", "QUERY_USER");
//		param.put("groupName", GROUP_NAME);
//		param.put("userId", "userIdQinyu");
//		// param.add("cursor", "0");
//		// param.add("imageId", "DDD");
//		// param.add("type", QueryInfoType.IMAGE");
//		// System.out.println(this.send(FACE_HOST +
//		// "?appKey={appKey}&requestId={requestId}&method={method}&groupName={groupName}&userId={userId}",
//		// param, "2"));
//		System.out.println(this.send(FACE_HOST + "?appKey=" + APPKEY + "&requestId=" + UUID.randomUUID().toString()
//				+ "&method=QUERY_USER&groupName=" + GROUP_NAME + "&userId=6506906450", null, "2"));
//	}
//
//	@Test
//	public void searchFace() throws UnsupportedEncodingException {
//		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
//		param.add("appKey", APPKEY);
//		param.add("requestId", UUID.randomUUID().toString());
//		param.add("method", "SEARCH_FACE");
//		param.add("groupName", GROUP_NAME);
//		param.add("isThreshold", "false");
//		param.add("resultNum", "1");
//		List<String> picInfos = new LinkedList<>();
////		picInfos.add(ImageToBase64ByLocal("C:\\Users\\Tao\\Desktop\\456.jpg"));
//		picInfos.add(ImageToBase64ByLocal("C:\\Users\\Tory\\Desktop\\FaceRegister\\qinyu.jpg"));
//		// picInfos.add(ImageToBase64ByLocal("C:\\Users\\Tao\\Desktop\\timg.jpg"));
//		param.add("picData", JSON.toJSONString(picInfos));
////		System.out.println(ImageToBase64ByLocal("C:\\Users\\Tao\\Desktop\\456.jpg"));
//		System.out.println(param.toString());
//		System.out.println(this.send(FACE_HOST, param, "1"));
//	}
//
//	@Test
//	public void unRegisterFace() throws UnsupportedEncodingException {
//		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
//		param.add("appKey", APPKEY);
//		param.add("requestId", UUID.randomUUID().toString());
//		param.add("method", "DELETE_GROUP");
//		param.add("groupName", GROUP_NAME);
//		// param.add("userId", "AAA");
//		// param.add("imageId", "02866e87-fbc4-4f36-a06f-65ff7c397f48");
//		// param.add("faceId", "90:90:228:272");
//		System.out.println(this.send(FACE_HOST + "?appKey=" + APPKEY + "&requestId=" + UUID.randomUUID().toString()
//				+ "&method=DELETE_GROUP&groupName=" + GROUP_NAME, null, "2").toString());
//	}
//
//	@Test
//	public void delFace() throws UnsupportedEncodingException {
//
//		System.out.println(this.send(FACE_HOST + "?appKey=" + APPKEY + "&requestId=" + UUID.randomUUID().toString()
//				+ "&method=DELETE_USER&groupName=" + GROUP_NAME + "&userId=userIdWangqiang ", null, "2").toString());
//	}
//
//	@Test
//	public void debugImage() {
//		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
//		param.add("appKey", "OLYMPIC_WINTER");
//		param.add("requestId", "ff00c222-1d25-4882-8ba1-b775669af278");
//		param.add("groupId", "FG_OLYMPIC_WINTER_4");
//		param.add("imageId", "2ecd5da8-fea6-4afc-b1d9-7813aed79099");
//		System.out.println(this.send(FACE_HOST, param, "2"));
//	}
//
//	@Test
//	public void demoTest() {
//		Map<String, Object> map = new HashMap<>();
//		for (int i = 0; i < 5; i++) {
//			List<Object> list = new ArrayList<>();
//			for (int j = 0; j < 10; j++) {
//				Map<String, Object> mapJS = new HashMap<>();
//				mapJS.put("监舍" + j, "摄像头数据json字符串");
//				list.add(mapJS);
//			}
//			map.put("楼层号（区域号）" + i, list);
//		}
//		System.err.println(JSON.toJSONString(map));
//	}
//
//}
