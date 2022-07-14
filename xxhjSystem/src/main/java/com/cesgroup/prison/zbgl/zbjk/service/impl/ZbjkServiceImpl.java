package com.cesgroup.prison.zbgl.zbjk.service.impl;
 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.zbgl.zbcx.dao.ZbcxMapper;
import com.cesgroup.prison.zbgl.zbjk.dao.ZbjkMapper;
import com.cesgroup.prison.zbgl.zbjk.entity.ZbjkEntity;
import com.cesgroup.prison.zbgl.zbjk.service.ZbjkService;

@Service("zbjkService")
public class ZbjkServiceImpl extends BaseDaoService<ZbjkEntity, String, ZbjkMapper> implements ZbjkService {

	@Resource
	private ZbcxMapper zbcxMapper;
	
	public Page<Map<String, Object>> findList(HttpServletRequest request, PageRequest pageRequest) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Page<Map<String, Object>> dataArray = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		paramMap.put("dbdCusNumber", request.getParameter("cusNumber"));
		paramMap.put("dmdDprtmntId", request.getParameter("dmdDprtmntId"));
		paramMap.put("mojOrderId", request.getParameter("mojOrderId"));
		paramMap.put("mojJobId", request.getParameter("mojJobId"));
		paramMap.put("dbdStaffId", request.getParameter("dutyPolice"));
		paramMap.put("startDate", request.getParameter("startDate"));
		paramMap.put("endDate", request.getParameter("endDate"));
		
		Page<Map<String, Object>> dutyBaseList = zbcxMapper.findList(paramMap, pageRequest);
		List<Map<String, Object>> inoutRecordList = zbcxMapper.findInoutRecord(paramMap);
		
		for (int i = 0; i < dutyBaseList.getSize(); i++) {
			Map<String, Object> dutyBase = dutyBaseList.getContent().get(i);
			Object staffId = dutyBase.get("DBD_STAFF_ID");
			staffId = staffId.toString();
			String staffName = dutyBase.get("DBD_STAFF_NAME").toString();
			String dprtmntName = dutyBase.get("DBD_DPRTMNT_NAME").toString();
			String dutyDate = dutyBase.get("DBD_DUTY_DATE").toString();
			String startTime = dutyBase.get("DOR_START_TIME").toString();
			String endTime = dutyBase.get("DOR_END_TIME").toString();
			
			Date startDate;
			try {
				startDate = df.parse(dutyDate + " " + startTime);
			
			Date endDate = df.parse(dutyDate + " " + endTime);
			if(endDate.getTime() < startDate.getTime()){
				Calendar cld = Calendar.getInstance();
				cld.setTime(endDate);
				cld.add(Calendar.DATE, 1);
				endDate = cld.getTime();
			}
			
			Map<String, Object> map = new HashMap<>();
			map.put("DBD_STAFF_ID", staffId);
			map.put("DBD_STAFF_NAME", staffName);
			map.put("DBD_DPRTMNT_NAME", dprtmntName);
			map.put("DBD_DUTY_DATE", dutyDate);
			map.put("DOR_START_TIME", startTime);
			map.put("DOR_END_TIME", endTime);
			boolean flag = true;
			Date nowDate = new Date();
			for (int j = 0; j < inoutRecordList.size(); j++) {
				Map<String, Object> inoutRecord = inoutRecordList.get(j);
				String brushDate = inoutRecord.get("PIR_BRUSH_DATE").toString();  //刷卡日期
				if(dutyDate.equals(brushDate)){
					String pir_policeId = inoutRecord.get("PIR_POLICE_IDNTY").toString();
					String enterTime = inoutRecord.get("PIR_ENTER_TIME").toString();
					Object leaveTime = inoutRecord.get("PIR_LEAVE_TIME");
					Date enterDate = df.parse(enterTime);
					Date leaveDate = leaveTime == null ? null : df.parse(leaveTime.toString());
					if(staffId.equals(pir_policeId)){
						flag = false;
						if(enterDate.getTime() > startDate.getTime()){
							map.put("STATUS", "迟到");
						}else if(nowDate.getTime() >= endDate.getTime()){
							if(leaveDate != null && leaveDate.getTime() < endDate.getTime()){
								map.put("STATUS", "早退");
							}else{
								map.put("STATUS", "正常值班");
							}
						}else{
							map.put("STATUS", "正常值班");
						}
					}	
				}
			}
			/*if(flag){
				for (int j = 0; j < checkWorkList.size(); j++) {
					Map<String, Object> checkWork = checkWorkList.get(j);
					String cwoDate = checkWork.get("CWO_DATE").toString();
					if(dutyDate.equals(cwoDate)){
						String cwo_policeId = checkWork.get("CWO_POLICE_IDNTY").toString();
						String signInTime = checkWork.get("CWD_SIGN_IN_TIME").toString();
						Object signOutTime = checkWork.get("CWD_SIGN_OUT_TIME");
						Date signInDate = df.parse(signInTime);
						Date signOutDate = signOutTime == null ? null : df.parse(signOutTime.toString());
						if(staffId.equals(cwo_policeId)){
							flag = false;
							if(signInDate.getTime() > startDate.getTime()){
								map.put("STATUS", "迟到");
							}else if(nowDate.getTime() >= endDate.getTime()){
								if(signOutTime != null && signOutDate.getTime() < endDate.getTime()){
									map.put("STATUS", "早退");
								}else{
									map.put("STATUS", "正常值班");
								}
							}else{
								map.put("STATUS", "正常值班");
							}
						}	
					}
				}
			}*/
			if(flag){
				map.put("STATUS", "未值班");
			}else{
				flag = true;
			}
		//	dataArray.add(map);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataArray;
		
	}
		return dutyBaseList;
	
}}