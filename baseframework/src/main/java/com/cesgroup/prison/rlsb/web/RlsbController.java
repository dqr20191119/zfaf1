package com.cesgroup.prison.rlsb.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.rlsb.dao.RlsbMapper;
import com.cesgroup.prison.rlsb.entity.RlsbEntity;
import com.cesgroup.prison.rlsb.service.RlsbService;
import com.cesgroup.prison.utils.DataUtils;


/**
 * 人脸识别
 * @author admin
 *
 */
@Controller
@RequestMapping(value = "/rlsb")
public class RlsbController extends BaseEntityDaoServiceCRUDController<RlsbEntity, String, RlsbMapper, RlsbService>{
	
	private final Logger logger = LoggerFactory.getLogger(RlsbController.class);
	static ServletOutputStream sout;
	@Resource
	private RlsbService rlsbService;
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("rlsb/index");
		return mv;
	}
	
	@RequestMapping(value = "/toView")
	public ModelAndView toView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("rlsb/view");
		mv.addObject("facepic", request.getParameter("facepic"));
		mv.addObject("capSmallpic", request.getParameter("capSmallpic"));
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	@RequestMapping(value = "/searchRlsbList")
	@ResponseBody
	public Map<String, Object> searchRlsbList(RlsbEntity rlsbEntity, HttpServletRequest request,HttpServletResponse response) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<RlsbEntity> page= rlsbService.searchRlsbList(rlsbEntity, pageRequest);
		
		return DataUtils.pageToMap(page);
	}
	/*
	 * 获取抓拍照片
	 */
	@RequestMapping(value = "/downloadImage")
	@ResponseBody
	@ModelAttribute
	public void downloadImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			String type = request.getParameter("type");
			String id = request.getParameter("id");
			String url = null;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			if("1".equals(type)){
				Map<String, Object> image = rlsbService.getUrl(map);
				url = image.get("FACEPIC_URL").toString();
			}else if("2".equals(type)){
				Map<String, Object> image = rlsbService.getUrl(map);
				url = image.get("CAP_SMALLPIC_URL").toString();
			}
        	CloseableHttpClient closeableHttpClient = createSSLClientDefault();// HttpClients.createDefault(); //1、创建实例
            HttpGet httpGet = new HttpGet(url); //2、创建请求
            
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间

            httpGet.setConfig(requestConfig);
            
	        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet); //3、执行
	        HttpEntity httpEntity = closeableHttpResponse.getEntity(); //4、获取实体
	        if(httpEntity != null) {
	            System.out.println("ContentType:" + httpEntity.getContentType().getValue());
	            sout = response.getOutputStream();
	            InputStream inputStream = httpEntity.getContent();
	            byte b[] = new byte[1024];
//				int len = inputStream.read(b);
				int len = 0;
				while((len = inputStream.read(b)) > 0){
					sout.write(b, 0, len);
                }
//				while(len!=-1){
//					sout.write(b);
//					len = inputStream.read(b);
//				}
				sout.flush();
				sout.close();
				inputStream.close();
	        }
	             
	        closeableHttpResponse.close();
	        closeableHttpClient.close();
        } catch (Exception e) {
        	System.out.println("e  " + e);
        }
	}
	public static final String get(final String url, final Map<String, Object> params) {
        StringBuilder sb = new StringBuilder("");

        if (null != params && !params.isEmpty()) {
            int i = 0;
            for (String key : params.keySet()) {
                if (i == 0) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key).append("=").append(params.get(key));
                i++;
            }
        }

        CloseableHttpClient httpClient = createSSLClientDefault();

        CloseableHttpResponse response = null;
        HttpGet get = new HttpGet(url + sb.toString());
        String result = "";

        try {
            response = httpClient.execute(get);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
            }
        } catch (IOException ex) {
            // Logger.getLogger(HttpsUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException ex) {
                	System.out.println(ex);
                    // Logger.getLogger(HttpsUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return result;
    }
    public static final String post(final String url, final Map<String, Object> params) {
        CloseableHttpClient httpClient = createSSLClientDefault();
        HttpPost post = new HttpPost(url);

        CloseableHttpResponse response = null;

        if (null != params && !params.isEmpty()) {
            List<NameValuePair> nvpList = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair nvp = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                nvpList.add(nvp);
            }
            post.setEntity(new UrlEncodedFormEntity(nvpList, Charset.forName("UTF-8")));
        }

        String result = "";

        try {
            response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
            }
        } catch (IOException ex) {
        	System.out.println(ex);
            // Logger.getLogger(HttpsUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException ex) {
                	System.out.println(ex);
                    // Logger.getLogger(HttpsUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return result;
    }


    private static CloseableHttpClient createSSLClientDefault() {

        SSLContext sslContext;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                @Override
                public boolean isTrusted(X509Certificate[] xcs, String string){
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyStoreException ex) {
        	System.out.println(ex);
            // Logger.getLogger(HttpsUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
        	System.out.println(ex);
            // Logger.getLogger(HttpsUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
        	System.out.println(ex);
            // Logger.getLogger(HttpsUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return HttpClients.createDefault();
    }  
	
	
}
