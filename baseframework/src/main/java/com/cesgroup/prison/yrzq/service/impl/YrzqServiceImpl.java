package com.cesgroup.prison.yrzq.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.ces.authsystem.entity.UserEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.yrzq.dao.YrzqMapper;
import com.cesgroup.prison.yrzq.entity.YrzqEntity;
import com.cesgroup.prison.yrzq.service.YrzqService;

@Service("yrzqService")
public class YrzqServiceImpl extends BaseDaoService<YrzqEntity, String, YrzqMapper> implements YrzqService {
	
	@Resource
	private YrzqMapper yrzqMapper;
	
	@Resource(name = "dataSource")
	private DataSource dataSource;

	@Override
	public YrzqEntity getById(String id) {
		// TODO Auto-generated method stub
		return yrzqMapper.getById(id);
	}

	@Override
	@Transactional
	public List<Map<String, Object>> findList(YrzqEntity yrzqEntity) {
		// 查询交班记录或一日执勤列表
		List<Map<String, Object>> jb = new ArrayList<Map<String, Object>>();
		
		List<Map<String, Object>> jieb = new ArrayList<Map<String, Object>>();
		try {
			String sxsj = getSxsj();
			yrzqEntity.setSxsj(sxsj);
			
			// 查询一日执勤的生效日期内是否有交班记录
			jb = yrzqMapper.getJb(yrzqEntity);
			// 查询当天未接班情况
			jieb = yrzqMapper.getJieb(yrzqEntity);
			// 若无交班记录，查询当前的一日执勤数据
			if((jb == null || jb.size() == 0)&&jieb.size() == 0) {
				List<Map<String, Object>> list = yrzqMapper.findList(yrzqEntity);
				
				// 如果一日执勤数据不为空
				if(list != null && list.size() > 0) {
					for(Map<String, Object> map : list) {
						String endTime = map.get("END_TIME").toString();
						String state = map.get("STATE").toString();
						String isTimeout = map.get("IS_TIMEOUT").toString();
						String id = map.get("ID").toString();
						String dataType = map.get("DATA_TYPE").toString();
						// 将超过处理时间而未处理的任务项设为超时
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
						if(StringUtils.isNotBlank(endTime) && "0".equals(dataType)) {
							boolean b = Util.isAffterDate(new Date(),sdf.parse(endTime));
							if(b && (state.equals("0") && isTimeout.equals("0"))) {
								map.put("IS_TIMEOUT", "1");
								yrzqMapper.updateIsTimeout(id);
							}
						}
					}
				}
				// 如果一日执勤数据为空
				else {
					// 查询本部门的交班记录
					List<Map<String, Object>> tempJb = this.yrzqMapper.findByDepartIdAndStateAndTitleOrderBySxsjDesc(yrzqEntity.getDepartId(), "0", "交班");

					// 并将最近的一条交班记录赋予jb列表
					if(tempJb != null && tempJb.size() > 0) {
						jb.add(tempJb.get(0));
					}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(jb.size() == 0&&jieb.size() == 0) {
			return yrzqMapper.findList(yrzqEntity);
		}
		if(jieb.size() != 0) {
			return jieb;
		}
		return jb;
	}
	
	public Page<YrzqEntity> findListPage(YrzqEntity yrzqEntity, PageRequest pageRequest) {
		String sxsj = getSxsj();
		yrzqEntity.setSxsj(sxsj);
		List<Map<String, Object>> jb = yrzqMapper.getJb(yrzqEntity);
		if(jb.size() == 0) {
			return yrzqMapper.findListPage(yrzqEntity,pageRequest);
		}
		return null;
	}
	
	public Page<YrzqEntity> findAllListPage(YrzqEntity yrzqEntity, PageRequest pageRequest) {
		Page<YrzqEntity> map = yrzqMapper.findAllListPage(yrzqEntity,pageRequest);
		return map;
	}

	@Override
	@Transactional
	public void saveOrUpdate(String id,String mark,String fxcjId,String zbrz) throws Exception {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		YrzqEntity yrzq = yrzqMapper.getById(id);
		String isTimeout = yrzq.getIsTimeout();
		String endTime = yrzq.getEndTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
		if(StringUtils.isNotBlank(endTime) && "0".equals(yrzq.getDataType())) {
			boolean b = Util.isAffterDate(new Date(),sdf.parse(endTime));
			if(b && isTimeout.equals("0")) {
				yrzq.setIsTimeout("1");
			}
		}
		yrzq.setFinishUserId(user.getUserId());
		yrzq.setFinishUserName(user.getUserName());
		yrzq.setState("2");
		yrzq.setMark(mark);
		yrzq.setFinishDate(sdf.format(new Date()));
		yrzq.setZbrz(zbrz);
		yrzqMapper.update(yrzq);
		yrzqMapper.updateFxcj(fxcjId);
		yrzqMapper.updateRwjs(fxcjId);
		yrzqMapper.updateRwxf(fxcjId);

	}
	
	public List<Map<String, Object>> searchYrzq(String prisonId, String departId, String pDate) {
		String sxsj = getSxsj();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("prisonId", prisonId);
		paramMap.put("departId", departId);
		paramMap.put("sxsj",sxsj);
		List<Map<String, Object>> list  = yrzqMapper.searchYrzq(paramMap);
		return list;
	}
	
	@Override
	public Page<YrzqEntity> findDataList(YrzqEntity yrzqEntity, PageRequest pageRequest) {
		
		return yrzqMapper.findDataList(yrzqEntity, pageRequest);
	}
	

	@Override
	public void deleteByIds(String ids) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 插入一日执勤信息
	 * @param userId
	 * @param startTime	开始时间
	 * @param endTime	结束时间
	 * @param dataType	数据来源; 0本部门的计划日程; 1事务督办; 2任务下达; 3视频督察; 4设备维修; 5风险防控措施;
	 * @param title	标题
	 */
	public void insertOnedayDutyDB(String userId, String cpsCusNumber, String cpsDrpmntId, String cpsDrpmntName, String startTime,
			String endTime, String dataType, String title, String cpsLx, String tzsj, String tznr, String zbr, String zbrId) {
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			String start = startTime.substring(0,10);
	        String str = start + " 08:00:00"; 
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date startDate = sdf1.parse(str);
	        Date startTime1 = sdf1.parse(startTime);
	        /*
	         * 判断执勤开始时间是在当天的8点前还是8点后,
	         * 8点前生效日期就为前一天日期，否则就是当天。
	         */
	        int i = startTime1.compareTo(startDate);
	      //  System.out.println("i ===== " + i);
	        if(i == -1) {
	        	Calendar c = Calendar.getInstance();  
	            c.setTime(sdf.parse(start));  
	            c.add(Calendar.DAY_OF_MONTH, -1);        
	            start = sdf.format(c.getTime());
	        }
			
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			String insertSql = "insert into T_ZHAF_ONEDAY_DUTY(ID,PRISON_ID,DEPART_ID,DEPART_NAME,START_TIME,END_TIME,CREATE_TIME,CREATE_USER_ID,	"
					+ "	DATA_TYPE,STATE,IS_TIMEOUT,TITLE,FINISH_DATE,MARK,FINISH_USER_NAME,FINISH_USER_ID,CPS_LX,SXSJ,TZSJ,TZNR,ZBR,ZBR_ID) "
					+ "	values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)	";
			pstat = conn.prepareStatement(insertSql);
			UserEntity user = AuthSystemFacade.getUserInfoByUserId(userId);
				
			pstat.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
			pstat.setString(2, cpsCusNumber);
			pstat.setString(3, cpsDrpmntId);
			pstat.setString(4, cpsDrpmntName);
			pstat.setString(5, startTime);
			pstat.setString(6, endTime);
			pstat.setTimestamp(7, new Timestamp(new Date().getTime()));
			pstat.setString(8, user.getUserId() + "");
			pstat.setString(9, dataType);
			pstat.setString(10, "0");
			pstat.setString(11, "0");
			pstat.setString(12, title);
			pstat.setString(13, "");
			pstat.setString(14, "");
			pstat.setString(15, "");
			pstat.setString(16, "");
			pstat.setString(17, cpsLx);
			pstat.setString(18, start);
			pstat.setString(19, tzsj);
			pstat.setString(20, tznr);
			pstat.setString(21, zbr);
			pstat.setString(22, zbrId);
				
			pstat.addBatch();
			pstat.executeBatch();
			conn.commit();
		} catch (Exception e) {
			logger.error("工作台消息错误：" + e.toString(), e.fillInStackTrace());
		} finally {
			if(pstat != null) {
				try {
					pstat.close();
				} catch (SQLException e) {
					logger.error("工作台消息错误：" + e.toString(), e.fillInStackTrace());
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("工作台消息错误：" + e.toString(), e.fillInStackTrace());
				}
			}
		}
	}


	@Override
	public Page<YrzqEntity> searchSwdbPage(YrzqEntity yrzqEntity, PageRequest pageRequest) {
		yrzqEntity.setPrisonId(AuthSystemFacade.getLoginUserInfo().getCusNumber());
		return yrzqMapper.searchSwdbPage(yrzqEntity, pageRequest);
	}


	@Override
	public List<Map<String, Object>> openCamare(String xlId) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = this.getDao().openCamare(xlId);
		return list;
	}
	

	
	@Override
	public Page<YrzqEntity> searchSwdbList(String departId, String dateTime, String state, PageRequest pageRequest) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("departId", departId);
		paramMap.put("dateTime", dateTime);
		paramMap.put("state", state);
		return yrzqMapper.searchSwdbList(paramMap, pageRequest);
	}
	
	/**
	 * 获取一日执勤的生效日期
	 * @return
	 */
	public String getSxsj() {
		String time = "";
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			time = sdf1.format(date);
			String str = time + " 08:00:00"; 
			int i = sdf.parse(str).compareTo(date);
		    if(i <= 0) {
		    } else {
		       Calendar c = Calendar.getInstance();
		       c.setTime(sdf1.parse(time));
		       c.add(Calendar.DAY_OF_MONTH, -1);
		       time = sdf1.format(c.getTime());
		    }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}


	@Override
	public List<Map<String, Object>> getYwyth() {
		List list = new ArrayList();
		CloseableHttpClient httpClient = HttpClients.createDefault();  
  		CloseableHttpResponse response = null;  
  		CloseableHttpResponse response2 = null;  
		try{
		// TODO Auto-generated method stub
		//登陆
		Map<String, String> map = new HashMap<String, String>();
		String url = "http://206.0.8.223/servlet/login";
		map.put("action", "login");
		map.put("userlogin", "administrator");
		map.put("password", "tekinfo");
        
        //请求故障单总数
        Map<String, String> map2 = new HashMap<String, String>();
		String url2 = "http://206.0.8.223/servlet/tobject";
		map2.put("objectName", "ITILIncident");
		map2.put("action", "getTotal");
         
  		
  		String resultString = "";  
  		String resultString2 = "";  
    
	      // 创建Http Post请求  
	    HttpPost httpPost = new HttpPost(url);  
	    HttpPost httpPost2 = new HttpPost(url2);  
	      // 创建参数列表  
	    if (map != null) {  
	    	List<NameValuePair> paramList = new ArrayList<>();  
	    	for (String key : map.keySet()) {  
	    		paramList.add(new BasicNameValuePair(key, map.get(key)));  
         	}  
          // 模拟表单  
          UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
          httpPost.setEntity(entity);  
       }  
	      
	   // 创建参数列表  
	      if (map2 != null) {  
	          List<NameValuePair> paramList = new ArrayList<>();  
	          for (String key : map2.keySet()) {  
	              paramList.add(new BasicNameValuePair(key, map2.get(key)));  
	          }  
	          // 模拟表单  
	          UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
	          httpPost2.setEntity(entity);  
	      } 
	      
	      
	      // 执行http请求  
	      response = httpClient.execute(httpPost);  
	      resultString = EntityUtils.toString(response.getEntity(), "utf-8");  
	      
	   // 执行http请求  
	      response2 = httpClient.execute(httpPost2);  
	      resultString2 = EntityUtils.toString(response2.getEntity(), "utf-8");  
	  
	      
	      Map mapstr = new HashMap();
	      mapstr = (Map) JSON.parse(resultString2);
	      list.add(mapstr);
	  	
		}catch(Exception e){
			e.printStackTrace();
		} finally {  
	          try {  
	              if (response != null) {  
	                  response.close();  
	              }  
	              if (response2 != null) {  
	                  response2.close();  
	              }
	              httpClient.close();  
	          } catch (IOException e) {  
	              e.printStackTrace();  
	          }  
	      } 
		 
        return list; 
	}

	@Override
	public List<Map<String, Object>> getsbxx() {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		CloseableHttpClient httpClient = HttpClients.createDefault();  
		CloseableHttpResponse response = null; 
		CloseableHttpResponse response3 = null; 
		CloseableHttpResponse response3h = null; 
		CloseableHttpResponse response4 = null;
		CloseableHttpResponse response4h = null; 
		CloseableHttpResponse response5 = null; 
		CloseableHttpResponse response5h = null; 
		CloseableHttpResponse response6 = null; 
		CloseableHttpResponse response6h = null; 
		CloseableHttpResponse response7 = null; 
		CloseableHttpResponse response7h = null;
		CloseableHttpResponse response8 = null; 
		CloseableHttpResponse response8h = null; 
		CloseableHttpResponse response9 = null; 
		CloseableHttpResponse response9h = null;
		CloseableHttpResponse response10 = null;
		CloseableHttpResponse response10h = null;
		CloseableHttpResponse response11 = null;
		CloseableHttpResponse response11h = null;
		CloseableHttpResponse response12 = null;
		CloseableHttpResponse response12h = null;
		CloseableHttpResponse response13 = null; 
		CloseableHttpResponse response13h = null; 
		CloseableHttpResponse response14 = null;
		CloseableHttpResponse response14h = null; 
		try{
			
			
			
			//登陆
			
			
			String resultString = "";  
			Map<String, String> map = new HashMap<String, String>();
			String url = "http://206.0.8.223/servlet/login";
			map.put("action", "login");
			map.put("userlogin", "administrator");
			map.put("password", "tekinfo");
			
			// 创建Http Post请求  
			HttpPost httpPost = new HttpPost(url); 
			// 创建参数列表  
			if (map != null) {  
				List<NameValuePair> paramList = new ArrayList<>();  
				for (String key : map.keySet()) {  
					paramList.add(new BasicNameValuePair(key, map.get(key)));  
			 	}  
			  // 模拟表单  
			      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost.setEntity(entity);  
			   }  
			 // 执行http请求  
			response = httpClient.execute(httpPost);  
			resultString = EntityUtils.toString(response.getEntity(), "utf-8"); 
			
			
			  //摄像 
			
			String resultString3 = "";  
			Map<String, String> map3 = new HashMap<String, String>();
			String url3 = "http://206.0.8.223/servlet/tobject";
			map3.put("objectName", "HostSite");
			map3.put("action", "getTotal");
			map3.put("condition", URLEncoder.encode("host_site_type='摄像'","utf-8"));
			HttpPost httpPost3 = new HttpPost(url3);  
			  // 创建参数列表  
			if (map3 != null) {  
			  List<NameValuePair> paramList = new ArrayList<>();  
			  for (String key : map3.keySet()) {  
			      paramList.add(new BasicNameValuePair(key, map3.get(key)));  
			  }  
			  // 模拟表单  
			      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost3.setEntity(entity);  
			    } 
			 // 执行http请求  
			response3 = httpClient.execute(httpPost3);  
			resultString3 = EntityUtils.toString(response3.getEntity(), "utf-8");  
			 
			      
			
			String resultString3h = "";  
			Map<String, String> map3h = new HashMap<String, String>();
			String url3h = "http://206.0.8.223/servlet/tobject";
			map3h.put("objectName", "HostSite");
			map3h.put("action", "getTotal");
			map3h.put("condition", URLEncoder.encode("host_site_type='摄像'","utf-8"));
			map3h.put("host_site_status", "0");
			HttpPost httpPost3h = new HttpPost(url3h);  
			  // 创建参数列表  
			if (map3h != null) {  
			    List<NameValuePair> paramList = new ArrayList<>();  
			    for (String key : map3h.keySet()) {  
			        paramList.add(new BasicNameValuePair(key, map3h.get(key)));  
			    }  
			  // 模拟表单  
			     UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost3h.setEntity(entity);  
			} 
			 // 执行http请求  
			response3h = httpClient.execute(httpPost3h);  
			resultString3h = EntityUtils.toString(response3h.getEntity(), "utf-8");
			
			//报警 
			
			String resultString4 = "";  
			Map<String, String> map4 = new HashMap<String, String>();
			String url4 = "http://206.0.8.223/servlet/tobject";
			map4.put("objectName", "HostSite");
			map4.put("action", "getTotal");
			map4.put("condition", URLEncoder.encode("host_site_type='报警'","utf-8"));
			HttpPost httpPost4 = new HttpPost(url4);  
			  // 创建参数列表  
			if (map4 != null) {  
			  List<NameValuePair> paramList = new ArrayList<>();  
			  for (String key : map4.keySet()) {  
			      paramList.add(new BasicNameValuePair(key, map4.get(key)));  
			  }  
			  // 模拟表单  
			      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost4.setEntity(entity);  
			    } 
			 // 执行http请求  
			response4 = httpClient.execute(httpPost4);  
			resultString4 = EntityUtils.toString(response4.getEntity(), "utf-8");  
			 
			      
			
			String resultString4h = "";  
			Map<String, String> map4h = new HashMap<String, String>();
			String url4h = "http://206.0.8.223/servlet/tobject";
			map4h.put("objectName", "HostSite");
			map4h.put("action", "getTotal");
			map4h.put("condition", URLEncoder.encode("host_site_type='报警'","utf-8"));
			map4h.put("host_site_status", "0");
			HttpPost httpPost4h = new HttpPost(url4h);  
			  // 创建参数列表  
			if (map4h != null) {  
			    List<NameValuePair> paramList = new ArrayList<>();  
			    for (String key : map4h.keySet()) {  
			        paramList.add(new BasicNameValuePair(key, map4h.get(key)));  
			    }  
			  // 模拟表单  
			     UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost4h.setEntity(entity);  
			} 
			 // 执行http请求  
			response4h = httpClient.execute(httpPost4h);  
			resultString4h = EntityUtils.toString(response4h.getEntity(), "utf-8");
			
			
			//物联网终端  
			
			String resultString5 = "";  
			Map<String, String> map5 = new HashMap<String, String>();
			String url5 = "http://206.0.8.223/servlet/tobject";
			map5.put("objectName", "HostSite");
			map5.put("action", "getTotal");
			map5.put("condition", URLEncoder.encode("host_site_type='物联网终端'","utf-8"));
			HttpPost httpPost5 = new HttpPost(url5);  
			  // 创建参数列表  
			if (map5 != null) {  
			  List<NameValuePair> paramList = new ArrayList<>();  
			  for (String key : map5.keySet()) {  
			      paramList.add(new BasicNameValuePair(key, map5.get(key)));  
			  }  
			  // 模拟表单  
			      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost5.setEntity(entity);  
			    } 
			 // 执行http请求  
			response5 = httpClient.execute(httpPost5);  
			resultString5 = EntityUtils.toString(response5.getEntity(), "utf-8");  
			 
			      
			
			String resultString5h = "";  
			Map<String, String> map5h = new HashMap<String, String>();
			String url5h = "http://206.0.8.223/servlet/tobject";
			map5h.put("objectName", "HostSite");
			map5h.put("action", "getTotal");
			map5h.put("condition", URLEncoder.encode("host_site_type='物联网终端'","utf-8"));
			map5h.put("host_site_status", "0");
			HttpPost httpPost5h = new HttpPost(url5h);  
			  // 创建参数列表  
			if (map5h != null) {  
			    List<NameValuePair> paramList = new ArrayList<>();  
			    for (String key : map5h.keySet()) {  
			        paramList.add(new BasicNameValuePair(key, map5h.get(key)));  
			    }  
			  // 模拟表单  
			     UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost5h.setEntity(entity);  
			} 
			 // 执行http请求  
			response5h = httpClient.execute(httpPost5h);  
			resultString5h = EntityUtils.toString(response5h.getEntity(), "utf-8");
			
			//广播
			
			String resultString6 = "";  
			Map<String, String> map6 = new HashMap<String, String>();
			String url6 = "http://206.0.8.223/servlet/tobject";
			map6.put("objectName", "HostSite");
			map6.put("action", "getTotal");
			map6.put("condition", URLEncoder.encode("host_site_type='广播'","utf-8"));
			HttpPost httpPost6 = new HttpPost(url6);  
			  // 创建参数列表  
			if (map6 != null) {  
			  List<NameValuePair> paramList = new ArrayList<>();  
			  for (String key : map6.keySet()) {  
			      paramList.add(new BasicNameValuePair(key, map6.get(key)));  
			  }  
			  // 模拟表单  
			      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost6.setEntity(entity);  
			    } 
			 // 执行http请求  
			response6 = httpClient.execute(httpPost6);  
			resultString6 = EntityUtils.toString(response6.getEntity(), "utf-8");  
			 
			      
			
			String resultString6h = "";  
			Map<String, String> map6h = new HashMap<String, String>();
			String url6h = "http://206.0.8.223/servlet/tobject";
			map6h.put("objectName", "HostSite");
			map6h.put("action", "getTotal");
			map6h.put("condition", URLEncoder.encode("host_site_type='广播'","utf-8"));
			map6h.put("host_site_status", "0");
			HttpPost httpPost6h = new HttpPost(url6h);  
			  // 创建参数列表  
			if (map6h != null) {  
			    List<NameValuePair> paramList = new ArrayList<>();  
			    for (String key : map6h.keySet()) {  
			        paramList.add(new BasicNameValuePair(key, map6h.get(key)));  
			    }  
			  // 模拟表单  
			     UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost6h.setEntity(entity);  
			} 
			 // 执行http请求  
			response6h = httpClient.execute(httpPost6h);  
			resultString6h = EntityUtils.toString(response6h.getEntity(), "utf-8");
			//对讲
			
			String resultString7 = "";  
			Map<String, String> map7 = new HashMap<String, String>();
			String url7 = "http://206.0.8.223/servlet/tobject";
			map7.put("objectName", "HostSite");
			map7.put("action", "getTotal");
			map7.put("condition", URLEncoder.encode("host_site_type='对讲'","utf-8"));
			HttpPost httpPost7 = new HttpPost(url7);  
			  // 创建参数列表  
			if (map7 != null) {  
			  List<NameValuePair> paramList = new ArrayList<>();  
			  for (String key : map7.keySet()) {  
			      paramList.add(new BasicNameValuePair(key, map7.get(key)));  
			  }  
			  // 模拟表单  
			      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost7.setEntity(entity);  
			    } 
			 // 执行http请求  
			response7 = httpClient.execute(httpPost7);  
			resultString7 = EntityUtils.toString(response7.getEntity(), "utf-8");  
			 
			      
			
			String resultString7h = "";  
			Map<String, String> map7h = new HashMap<String, String>();
			String url7h = "http://206.0.8.223/servlet/tobject";
			map7h.put("objectName", "HostSite");
			map7h.put("action", "getTotal");
			map7h.put("condition", URLEncoder.encode("host_site_type='对讲'","utf-8"));
			map7h.put("host_site_status", "0");
			HttpPost httpPost7h = new HttpPost(url7h);  
			  // 创建参数列表  
			if (map7h != null) {  
			    List<NameValuePair> paramList = new ArrayList<>();  
			    for (String key : map7h.keySet()) {  
			        paramList.add(new BasicNameValuePair(key, map7h.get(key)));  
			    }  
			  // 模拟表单  
			     UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost7h.setEntity(entity);  
			} 
			 // 执行http请求  
			response7h = httpClient.execute(httpPost7h);  
			resultString7h = EntityUtils.toString(response7h.getEntity(), "utf-8");
			//亲情电话 
			
			String resultString8 = "";  
			Map<String, String> map8 = new HashMap<String, String>();
			String url8 = "http://206.0.8.223/servlet/tobject";
			map8.put("objectName", "HostSite");
			map8.put("action", "getTotal");
			map8.put("condition", URLEncoder.encode("host_site_type='亲情电话'","utf-8"));
			HttpPost httpPost8 = new HttpPost(url8);  
			  // 创建参数列表  
			if (map8 != null) {  
			  List<NameValuePair> paramList = new ArrayList<>();  
			  for (String key : map8.keySet()) {  
			      paramList.add(new BasicNameValuePair(key, map8.get(key)));  
			  }  
			  // 模拟表单  
			      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost8.setEntity(entity);  
			    } 
			 // 执行http请求  
			response8 = httpClient.execute(httpPost8);  
			resultString8 = EntityUtils.toString(response8.getEntity(), "utf-8");  
			 
			      
			
			String resultString8h = "";  
			Map<String, String> map8h = new HashMap<String, String>();
			String url8h = "http://206.0.8.223/servlet/tobject";
			map8h.put("objectName", "HostSite");
			map8h.put("action", "getTotal");
			map8h.put("condition", URLEncoder.encode("host_site_type='亲情电话'","utf-8"));
			map8h.put("host_site_status", "0");
			HttpPost httpPost8h = new HttpPost(url8h);  
			  // 创建参数列表  
			if (map8h != null) {  
			    List<NameValuePair> paramList = new ArrayList<>();  
			    for (String key : map8h.keySet()) {  
			        paramList.add(new BasicNameValuePair(key, map8h.get(key)));  
			    }  
			  // 模拟表单  
			     UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost8h.setEntity(entity);  
			} 
			 // 执行http请求  
			response8h = httpClient.execute(httpPost8h);  
			resultString8h = EntityUtils.toString(response8h.getEntity(), "utf-8");
			//电源/电池
			
			String resultString9 = "";  
			Map<String, String> map9 = new HashMap<String, String>();
			String url9 = "http://206.0.8.223/servlet/tobject";
			map9.put("objectName", "HostSite");
			map9.put("action", "getTotal");
			map9.put("condition", URLEncoder.encode("host_site_type='电源/电池'","utf-8"));
			HttpPost httpPost9 = new HttpPost(url9);  
			  // 创建参数列表  
			if (map9 != null) {  
			  List<NameValuePair> paramList = new ArrayList<>();  
			  for (String key : map9.keySet()) {  
			      paramList.add(new BasicNameValuePair(key, map9.get(key)));  
			  }  
			  // 模拟表单  
			      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost9.setEntity(entity);  
			    } 
			 // 执行http请求  
			response9 = httpClient.execute(httpPost9);  
			resultString9 = EntityUtils.toString(response9.getEntity(), "utf-8");  
			 
			      
			
			String resultString9h = "";  
			Map<String, String> map9h = new HashMap<String, String>();
			String url9h = "http://206.0.8.223/servlet/tobject";
			map9h.put("objectName", "HostSite");
			map9h.put("action", "getTotal");
			map9h.put("condition", URLEncoder.encode("host_site_type='电源/电池'","utf-8"));
			map9h.put("host_site_status", "0");
			HttpPost httpPost9h = new HttpPost(url9h);  
			  // 创建参数列表  
			if (map9h != null) {  
			    List<NameValuePair> paramList = new ArrayList<>();  
			    for (String key : map9h.keySet()) {  
			        paramList.add(new BasicNameValuePair(key, map9h.get(key)));  
			    }  
			  // 模拟表单  
			     UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost9h.setEntity(entity);  
			} 
			 // 执行http请求  
			response9h = httpClient.execute(httpPost9h);  
			resultString9h = EntityUtils.toString(response9h.getEntity(), "utf-8");
			
			
			
			//服务器
			
			String resultString10 = "";  
			Map<String, String> map10 = new HashMap<String, String>();
			String url10 = "http://206.0.8.223/servlet/tobject";
			map10.put("objectName", "HostSite");
			map10.put("action", "getTotal");
			map10.put("condition", URLEncoder.encode("host_site_type='服务器'","utf-8"));
			HttpPost httpPost10 = new HttpPost(url10);  
			  // 创建参数列表  
			if (map10 != null) {  
			  List<NameValuePair> paramList = new ArrayList<>();  
			  for (String key : map10.keySet()) {  
			      paramList.add(new BasicNameValuePair(key, map10.get(key)));  
			  }  
			  // 模拟表单  
			      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost10.setEntity(entity);  
			    } 
			 // 执行http请求  
			response10 = httpClient.execute(httpPost10);  
			resultString10 = EntityUtils.toString(response10.getEntity(), "utf-8");  
			 
			      
			
			String resultString10h = "";  
			Map<String, String> map10h = new HashMap<String, String>();
			String url10h = "http://206.0.8.223/servlet/tobject";
			map10h.put("objectName", "HostSite");
			map10h.put("action", "getTotal");
			map10h.put("condition", URLEncoder.encode("host_site_type='服务器'","utf-8"));
			map10h.put("host_site_status", "0");
			HttpPost httpPost10h = new HttpPost(url10h);  
			  // 创建参数列表  
			if (map10h != null) {  
			    List<NameValuePair> paramList = new ArrayList<>();  
			    for (String key : map10h.keySet()) {  
			        paramList.add(new BasicNameValuePair(key, map10h.get(key)));  
			    }  
			  // 模拟表单  
			     UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost10h.setEntity(entity);  
			} 
			 // 执行http请求  
			response10h = httpClient.execute(httpPost10h);  
			resultString10h = EntityUtils.toString(response10h.getEntity(), "utf-8");
			//交换机
			
			String resultString11 = "";  
			Map<String, String> map11 = new HashMap<String, String>();
			String url11 = "http://206.0.8.223/servlet/tobject";
			map11.put("objectName", "HostSite");
			map11.put("action", "getTotal");
			map11.put("condition", URLEncoder.encode("host_site_type='交换机'","utf-8"));
			HttpPost httpPost11 = new HttpPost(url11);  
			  // 创建参数列表  
			if (map11 != null) {  
			  List<NameValuePair> paramList = new ArrayList<>();  
			  for (String key : map11.keySet()) {  
			      paramList.add(new BasicNameValuePair(key, map11.get(key)));  
			  }  
			  // 模拟表单  
			      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost11.setEntity(entity);  
			    } 
			 // 执行http请求  
			response11 = httpClient.execute(httpPost11);  
			resultString11 = EntityUtils.toString(response11.getEntity(), "utf-8");  
			 
			      
			
			String resultString11h = "";  
			Map<String, String> map11h = new HashMap<String, String>();
			String url11h = "http://206.0.8.223/servlet/tobject";
			map11h.put("objectName", "HostSite");
			map11h.put("action", "getTotal");
			map11h.put("condition", URLEncoder.encode("host_site_type='交换机'","utf-8"));
			map11h.put("host_site_status", "0");
			HttpPost httpPost11h = new HttpPost(url11h);  
			  // 创建参数列表  
			if (map11h != null) {  
			    List<NameValuePair> paramList = new ArrayList<>();  
			    for (String key : map11h.keySet()) {  
			        paramList.add(new BasicNameValuePair(key, map11h.get(key)));  
			    }  
			  // 模拟表单  
			     UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost11h.setEntity(entity);  
			} 
			 // 执行http请求  
			response11h = httpClient.execute(httpPost11h);  
			resultString11h = EntityUtils.toString(response11h.getEntity(), "utf-8");
			//电网
			
			String resultString12 = "";  
			Map<String, String> map12 = new HashMap<String, String>();
			String url12 = "http://206.0.8.223/servlet/tobject";
			map12.put("objectName", "HostSite");
			map12.put("action", "getTotal");
			map12.put("condition", URLEncoder.encode("host_site_type='电网'","utf-8"));
			HttpPost httpPost12 = new HttpPost(url12);  
			  // 创建参数列表  
			if (map12 != null) {  
			  List<NameValuePair> paramList = new ArrayList<>();  
			  for (String key : map12.keySet()) {  
			      paramList.add(new BasicNameValuePair(key, map12.get(key)));  
			  }  
			  // 模拟表单  
			      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost12.setEntity(entity);  
			    } 
			 // 执行http请求  
			response12 = httpClient.execute(httpPost12);  
			resultString12 = EntityUtils.toString(response12.getEntity(), "utf-8");  
			 
			      
			
			String resultString12h = "";  
			Map<String, String> map12h = new HashMap<String, String>();
			String url12h = "http://206.0.8.223/servlet/tobject";
			map12h.put("objectName", "HostSite");
			map12h.put("action", "getTotal");
			map12h.put("condition", URLEncoder.encode("host_site_type='电网'","utf-8"));
			map12h.put("host_site_status", "0");
			HttpPost httpPost12h = new HttpPost(url12h);  
			  // 创建参数列表  
			if (map12h != null) {  
			    List<NameValuePair> paramList = new ArrayList<>();  
			    for (String key : map12h.keySet()) {  
			        paramList.add(new BasicNameValuePair(key, map12h.get(key)));  
			    }  
			  // 模拟表单  
			     UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost12h.setEntity(entity);  
			} 
			 // 执行http请求  
			response12h = httpClient.execute(httpPost12h);  
			resultString12h = EntityUtils.toString(response12h.getEntity(), "utf-8");
			//门禁
			
			String resultString13 = "";  
			Map<String, String> map13 = new HashMap<String, String>();
			String url13 = "http://206.0.8.223/servlet/tobject";
			map13.put("objectName", "HostSite");
			map13.put("action", "getTotal");
			map13.put("condition", URLEncoder.encode("host_site_type='门禁'","utf-8"));
			HttpPost httpPost13 = new HttpPost(url13);  
			  // 创建参数列表  
			if (map13 != null) {  
			  List<NameValuePair> paramList = new ArrayList<>();  
			  for (String key : map13.keySet()) {  
			      paramList.add(new BasicNameValuePair(key, map13.get(key)));  
			  }  
			  // 模拟表单  
			      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost13.setEntity(entity);  
			    } 
			 // 执行http请求  
			response13 = httpClient.execute(httpPost13);  
			resultString13 = EntityUtils.toString(response13.getEntity(), "utf-8");  
			 
			      
			
			String resultString13h = "";  
			Map<String, String> map13h = new HashMap<String, String>();
			String url13h = "http://206.0.8.223/servlet/tobject";
			map13h.put("objectName", "HostSite");
			map13h.put("action", "getTotal");
			map13h.put("condition", URLEncoder.encode("host_site_type='门禁'","utf-8"));
			map13h.put("host_site_status", "0");
			HttpPost httpPost13h = new HttpPost(url13h);  
			  // 创建参数列表  
			if (map13h != null) {  
			    List<NameValuePair> paramList = new ArrayList<>();  
			    for (String key : map13h.keySet()) {  
			        paramList.add(new BasicNameValuePair(key, map13h.get(key)));  
			    }  
			  // 模拟表单  
			     UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost13h.setEntity(entity);  
			} 
			 // 执行http请求  
			response13h = httpClient.execute(httpPost13h);  
			resultString13h = EntityUtils.toString(response13h.getEntity(), "utf-8");
			//PCM
			
			String resultString14 = "";  
			Map<String, String> map14 = new HashMap<String, String>();
			String url14 = "http://206.0.8.223/servlet/tobject";
			map14.put("objectName", "HostSite");
			map14.put("action", "getTotal");
			map14.put("condition", URLEncoder.encode("host_site_type='PCM'","utf-8"));
			HttpPost httpPost14 = new HttpPost(url14);  
			  // 创建参数列表  
			if (map14 != null) {  
			  List<NameValuePair> paramList = new ArrayList<>();  
			  for (String key : map14.keySet()) {  
			      paramList.add(new BasicNameValuePair(key, map14.get(key)));  
			  }  
			  // 模拟表单  
			      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost14.setEntity(entity);  
			    } 
			 // 执行http请求  
			response14 = httpClient.execute(httpPost14);  
			resultString14 = EntityUtils.toString(response14.getEntity(), "utf-8");  
			 
			      
			
			String resultString14h = "";  
			Map<String, String> map14h = new HashMap<String, String>();
			String url14h = "http://206.0.8.223/servlet/tobject";
			map14h.put("objectName", "HostSite");
			map14h.put("action", "getTotal");
			map14h.put("condition", URLEncoder.encode("host_site_type='PCM'","utf-8"));
			map14h.put("host_site_status", "0");
			HttpPost httpPost14h = new HttpPost(url14h);  
			  // 创建参数列表  
			if (map14h != null) {  
			    List<NameValuePair> paramList = new ArrayList<>();  
			    for (String key : map14h.keySet()) {  
			        paramList.add(new BasicNameValuePair(key, map14h.get(key)));  
			    }  
			  // 模拟表单  
			     UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);  
			      httpPost14h.setEntity(entity);  
			} 
			 // 执行http请求  
			response14h = httpClient.execute(httpPost14h);  
			resultString14h = EntityUtils.toString(response14h.getEntity(), "utf-8"); 
			
			
			
			
			
			
			Map mapzjg3 = new HashMap();
			Map mapstr = new HashMap();
			mapstr = (Map) JSON.parse(resultString3);
			String jg3 = mapstr.get("value").toString();
			Map mapstr3h = new HashMap();
			mapstr3h = (Map) JSON.parse(resultString3h);
			String jg3h = mapstr3h.get("value").toString();
			mapzjg3.put("name", "摄像");
			mapzjg3.put("tb", "icon-camera");
			mapzjg3.put("zc", jg3);
			mapzjg3.put("yc", jg3h);
			
			
			Map mapzjg4 = new HashMap();
			Map mapstr4 = new HashMap();
			mapstr4 = (Map) JSON.parse(resultString4);
			String jg4 = mapstr4.get("value").toString();
			Map mapstr4h = new HashMap();
			mapstr4h = (Map) JSON.parse(resultString4h);
			String jg4h = mapstr4h.get("value").toString();
			mapzjg4.put("name", "报警");
			mapzjg4.put("tb", "icon-alarm");
			mapzjg4.put("zc", jg4);
			mapzjg4.put("yc", jg4h);
			
			Map mapzjg5 = new HashMap();
			Map mapstr5 = new HashMap();
			mapstr5 = (Map) JSON.parse(resultString5);
			String jg5 = mapstr5.get("value").toString();
			Map mapstr5h = new HashMap();
			mapstr5h = (Map) JSON.parse(resultString5h);
			String jg5h = mapstr5h.get("value").toString();
			mapzjg5.put("name", "物联网终端");
			mapzjg5.put("tb", "icon-terminal");
			mapzjg5.put("zc", jg5);
			mapzjg5.put("yc", jg5h);
			
			Map mapzjg6 = new HashMap();
			Map mapstr6 = new HashMap();
			mapstr6 = (Map) JSON.parse(resultString6);
			String jg6 = mapstr6.get("value").toString();
			Map mapstr6h = new HashMap();
			mapstr6h = (Map) JSON.parse(resultString6h);
			String jg6h = mapstr6h.get("value").toString();
			mapzjg6.put("name", "广播");
			mapzjg6.put("tb", "icon-broadcast");
			mapzjg6.put("zc", jg6);
			mapzjg6.put("yc", jg6h);
			
			Map mapzjg7 = new HashMap();
			Map mapstr7 = new HashMap();
			mapstr7 = (Map) JSON.parse(resultString7);
			String jg7 = mapstr7.get("value").toString();
			Map mapstr7h = new HashMap();
			mapstr7h = (Map) JSON.parse(resultString7h);
			String jg7h = mapstr7h.get("value").toString();
			mapzjg7.put("name", "对讲");
			mapzjg7.put("tb", "icon-talk1");
			mapzjg7.put("zc", jg7);
			mapzjg7.put("yc", jg7h);
			
			Map mapzjg8 = new HashMap();
			Map mapstr8 = new HashMap();
			mapstr8 = (Map) JSON.parse(resultString8);
			String jg8 = mapstr8.get("value").toString();
			Map mapstr8h = new HashMap();
			mapstr8h = (Map) JSON.parse(resultString8h);
			String jg8h = mapstr8h.get("value").toString();
			mapzjg8.put("name", "亲情电话");
			mapzjg8.put("tb", "icon-phone");
			mapzjg8.put("zc", jg8);
			mapzjg8.put("yc", jg8h);
			
			Map mapzjg9 = new HashMap();
			Map mapstr9 = new HashMap();
			mapstr9 = (Map) JSON.parse(resultString9);
			String jg9 = mapstr9.get("value").toString();
			Map mapstr9h = new HashMap();
			mapstr9h = (Map) JSON.parse(resultString9h);
			String jg9h = mapstr9h.get("value").toString();
			mapzjg9.put("name", "电源/电池");
			mapzjg9.put("tb", "icon-battery");
			mapzjg9.put("zc", jg9);
			mapzjg9.put("yc", jg9h);
			
			Map mapzjg10 = new HashMap();
			Map mapstr10 = new HashMap();
			mapstr10 = (Map) JSON.parse(resultString10);
			String jg10 = mapstr10.get("value").toString();
			Map mapstr10h = new HashMap();
			mapstr10h = (Map) JSON.parse(resultString10h);
			String jg10h = mapstr10h.get("value").toString();
			mapzjg10.put("name", "服务器");
			mapzjg10.put("tb", "icon-server");
			mapzjg10.put("zc", jg10);
			mapzjg10.put("yc", jg10h);
			
			Map mapzjg11 = new HashMap();
			Map mapstr11 = new HashMap();
			mapstr11 = (Map) JSON.parse(resultString11);
			String jg11 = mapstr11.get("value").toString();
			Map mapstr11h = new HashMap();
			mapstr11h = (Map) JSON.parse(resultString11h);
			String jg11h = mapstr11h.get("value").toString();
			mapzjg11.put("name", "交换机");
			mapzjg11.put("tb", "icon-exchange");
			mapzjg11.put("zc", jg11);
			mapzjg11.put("yc", jg11h);
			
			Map mapzjg12 = new HashMap();
			Map mapstr12 = new HashMap();
			mapstr12 = (Map) JSON.parse(resultString12);
			String jg12 = mapstr12.get("value").toString();
			Map mapstr12h = new HashMap();
			mapstr12h = (Map) JSON.parse(resultString12h);
			String jg12h = mapstr12h.get("value").toString();
			mapzjg12.put("name", "电网");
			mapzjg12.put("tb", "icon-electron");
			mapzjg12.put("zc", jg12);
			mapzjg12.put("yc", jg12h);
			
			Map mapzjg13 = new HashMap();
			Map mapstr13 = new HashMap();
			mapstr13 = (Map) JSON.parse(resultString13);
			String jg13 = mapstr13.get("value").toString();
			Map mapstr13h = new HashMap();
			mapstr13h = (Map) JSON.parse(resultString13h);
			String jg13h = mapstr13h.get("value").toString();
			mapzjg13.put("name", "门禁");
			mapzjg13.put("tb", "icon-limit");
			mapzjg13.put("zc", jg13);
			mapzjg13.put("yc", jg13h);
			 
			Map mapzjg14 = new HashMap();
			Map mapstr14 = new HashMap();
			mapstr14 = (Map) JSON.parse(resultString14);
			String jg14 = mapstr14.get("value").toString();
			Map mapstr14h = new HashMap();
			mapstr14h = (Map) JSON.parse(resultString14h);
			String jg14h = mapstr14h.get("value").toString();
			mapzjg14.put("name", "PCM");
			mapzjg14.put("tb", "icon-pcm");
			mapzjg14.put("zc", jg14);
			mapzjg14.put("yc", jg14h);
			list.add(mapzjg3);
			list.add(mapzjg4);
			list.add(mapzjg5);
			list.add(mapzjg6);
			list.add(mapzjg7);
			list.add(mapzjg8);
			list.add(mapzjg9);
			list.add(mapzjg10);
			list.add(mapzjg11);
			list.add(mapzjg12);
			list.add(mapzjg13);
			list.add(mapzjg14);
		
		}catch(Exception e){
			e.printStackTrace();
		} finally {  
	          try {  
	              if (response != null) {  
	                  response.close();  
	              }  
	              if (response3 != null) {  
	                  response3.close();  
	              }
	              if (response3h != null) {  
	                  response3h.close();  
	              }
	              if (response4 != null) {  
	                  response4.close();  
	              }
	              if (response4h != null) {  
	                  response4h.close();  
	              }
	              if (response5 != null) {  
	                  response5.close();  
	              }
	              if (response5h != null) {  
	                  response5h.close();  
	              }
	              if (response6 != null) {  
	                  response6.close();  
	              }
	              if (response6h != null) {  
	                  response6h.close();  
	              }
	              if (response7 != null) {  
	                  response7.close();  
	              }
	              if (response7h != null) {  
	                  response7h.close();  
	              }
	              if (response8 != null) {  
	                  response8.close();  
	              }
	              if (response8h != null) {  
	                  response8h.close();  
	              }
	              if (response9 != null) {  
	                  response9.close();  
	              }
	              if (response9h != null) {  
	                  response9h.close();  
	              }
	              if (response10 != null) {  
	                  response10.close();  
	              }
	              if (response10h != null) {  
	                  response10h.close();  
	              }
	              if (response11 != null) {  
	                  response11.close();  
	              }
	              if (response11h != null) {  
	                  response11h.close();  
	              }
	              if (response12 != null) {  
	                  response12.close();  
	              }
	              if (response12h != null) {  
	                  response12h.close();  
	              }
	              if (response13 != null) {  
	                  response13.close();  
	              }
	              if (response13h != null) {  
	                  response13h.close();  
	              }
	              if (response14 != null) {  
	                  response14.close();  
	              }if (response14h != null) {  
	                  response14h.close();  
	              }
	              httpClient.close();  
	          } catch (IOException e) {  
	              e.printStackTrace();  
	          }  
	      }
			 
		    
		return list; 
	}

	@Override
	public Map<String, Object> getFxcj(String id) {
		return yrzqMapper.getFxcj(id);
	}
	@Override
	public String getMj(String departId){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> user = yrzqMapper.getMj(departId);
		for(Map<String, Object> m : user) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", m.get("USERID"));
			map.put("text", m.get("USERNAME"));
			list.add(map);
		}
		return JSON.toJSONString(list);
	}
	@Override
	public String searchZbrz(String departId,String sx_sj){
		String zbrz = "";
		try {
			List<Map<String, Object>> list = yrzqMapper.searchZbrz(departId, sx_sj);
			for(Map map:list) {
				String sxsj = map.containsKey("SXSJ")?map.get("SXSJ").toString():"无";
				String title = map.containsKey("TITLE")?map.get("TITLE").toString():"无";
				String state = map.containsKey("STATE")?map.get("STATE").toString():"无";
				String mark = map.containsKey("MARK")?map.get("MARK").toString():"无";
				if("2".equals(state)||("0".equals(state)&&"交班".equals(title))) {
					state = "已完成";
				}else {
					state = "未完成";
				}
				zbrz =zbrz + sxsj + "-" + title + "-" + state + "-" + mark + ";";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return zbrz;
	}
	
	@Override
	public String getZbrz(String departId){
		
		String zbrz = "";
		try {
			List<Map<String, Object>> list = yrzqMapper.getZbrz(departId);
			zbrz = list.get(0).get("ZBRZ").toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return zbrz;
	}
	
	@Override
	@Transactional
	public void updateZbr(Map<String, Object> map) {
		String id = map.get("id").toString();
		String zbrVal = map.get("zbr").toString();
		String zbrText = map.get("zbrText").toString();
		String userId = map.get("userId").toString();
		String userName = map.get("userName").toString();
		YrzqEntity yrzqEntity = yrzqMapper.getById(id);
		String zbr = yrzqEntity.getZbr();
		String zbrId = yrzqEntity.getZbrId();
		zbr = zbr.replaceAll(userName, zbrText);
		zbrId = zbrId.replaceAll(userId, zbrVal);
		yrzqEntity.setZbr(zbr);
		yrzqEntity.setZbrId(zbrId);
		yrzqMapper.updateZbr(yrzqEntity);
	}

}
