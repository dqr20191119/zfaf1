package com.cesgroup.prison.xwzc.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import cn.hutool.core.bean.BeanUtil;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.foreign.entity.ForeignRegister;

import com.cesgroup.prison.xwzc.dao.XwzcNowMapper;
import com.cesgroup.prison.xwzc.entity.XwzcNowEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.xwzc.dao.XwzcMapper;
import com.cesgroup.prison.xwzc.entity.XwzcEntity;
import com.cesgroup.prison.xwzc.service.XwzcService;

@Service("xwzcService")
public class XwzcServiceImpl extends BaseDaoService<XwzcEntity, String, XwzcMapper> implements XwzcService {
	
	@Resource
	private XwzcMapper xwzcMapper;
	@Resource
	private XwzcNowMapper xwzcNowMapper;

	@Override
	public Page<XwzcEntity> searchXwzcList(XwzcEntity xwzcEntity, PageRequest pageRequest,String type) {
		if(type!=null&&type!=""){
			//一天的开始时间 yyyy:MM:dd 00:00:00  
			Calendar calendar = new GregorianCalendar();  
		    calendar.add(Calendar.DAY_OF_MONTH,-1);  
		    calendar.set(Calendar.HOUR_OF_DAY,23);  
		    calendar.set(Calendar.MINUTE,59);  
		    calendar.set(Calendar.SECOND,59);  
		    calendar.set(Calendar.MILLISECOND,0);  
		    Date dayStart = calendar.getTime();  
		   // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  //  String startStr = simpleDateFormat.format(dayStart);
		  //当前时间 yyyy:MM:dd 00:00:00  
		    //Date date = new Date();
		   // String Now = simpleDateFormat.format(date);
		    xwzcEntity.setStartTime(dayStart);
		}
		return xwzcNowMapper.searchXwzcList(xwzcEntity, pageRequest);
	}
	
	@Override
	public Map<String, Object> searchXwzcCount(){
		//一天的开始时间 yyyy:MM:dd 00:00:00  
		Calendar calendar = new GregorianCalendar();  
	    calendar.add(Calendar.DAY_OF_MONTH,-1);  
	    calendar.set(Calendar.HOUR_OF_DAY,23);  
	    calendar.set(Calendar.MINUTE,59);  
	    calendar.set(Calendar.SECOND,59);  
	    calendar.set(Calendar.MILLISECOND,0);  
	    Date dayStart = calendar.getTime();  
	  //  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   // String startStr = simpleDateFormat.format(dayStart);
	  //当前时间 yyyy:MM:dd 00:00:00  
	   // Date date = new Date();
		//String Now = simpleDateFormat.format(date);
	    Map<String, Object> map1 = new HashMap<String, Object>();
	    map1.put("StartTime", dayStart);

		UserBean user = AuthSystemFacade.getLoginUserInfo();
		map1.put("cusNumber", user.getCusNumber());

		return xwzcNowMapper.searchXwzcCount(map1);
	}

	@Override
	public Map<String, Object> dkImage(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		XwzcEntity f = xwzcMapper.selectOne(id);
		String zhp1 = f.getLogTxt();
		Map<String, Object> mapr = new HashMap<String, Object>();
		mapr.put("phono1", zhp1);
		return mapr;
	}
	
	
}
