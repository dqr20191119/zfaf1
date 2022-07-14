package com.cesgroup.prison.xxhj.jhrc.service;
 
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ces.authsystem.entity.UserEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.plugins.jobs.entity.ScheduleJob;
import com.cesgroup.framework.plugins.jobs.service.ScheduleJobService;
import com.cesgroup.prison.code.utils.CodeFacade;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.xxhj.jhrc.dao.JhrcMapper;
import com.cesgroup.prison.xxhj.jhrc.entity.JhrcEntity;
import com.cesgroup.prison.yrzq.entity.YrzqEntity;
import com.cesgroup.prison.yrzq.service.YrzqService;

import ces.sdk.util.StringUtil;

@Service("jhrcService")
public class JhrcServiceImpl extends BaseDaoService<JhrcEntity, String, JhrcMapper> implements JhrcService {
	
	@Resource
	private JhrcMapper jhrcMapper;
	
	@Resource
	private YrzqService yrzqService;
	
	@Resource
	private ScheduleJobService scheduleJobService;
	
	@Override
	public JhrcEntity getById(String id) {
		
		return jhrcMapper.getById(id);
	}
	
	@Override
	public Page<JhrcEntity> findList(JhrcEntity JhrcEntity, PageRequest pageRequest) {
		Page<JhrcEntity> map = jhrcMapper.findList(JhrcEntity, pageRequest);
		return map;
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(JhrcEntity jhrcEntity) throws Exception {
		
		String id = jhrcEntity.getId();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		jhrcEntity.setCpsCusNumber(user.getOrgCode());
		jhrcEntity.setCpsCrteUserId(user.getUserId());
		jhrcEntity.setCpsCrteTime(new Date());
		jhrcEntity.setCpsUpdtUserId(user.getUserId());
		jhrcEntity.setCpsUpdtTime(new Date());
		
		if(id != null && !"".equals(id)) {
			jhrcMapper.update(jhrcEntity);
		} else {
			jhrcMapper.insert(jhrcEntity);
		}
	}	
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {
		String[] idArr = ids.split(",");
		jhrcMapper.deleteByIds(Arrays.asList(idArr));			 
	}
	
	public List<Map<String, Object>> searchRcByDay(String cpsCusNumber,String cpsDrpmntId,String day){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cpsCusNumber", cpsCusNumber);
		map.put("cpsDrpmntId", cpsDrpmntId);
		map.put("day", day);
/*		List<Map<String, Object>> jhrcList = jhrcMapper.searchRcByDay(map);
		List<Map<String, Object>> jndtList = jhrcMapper.searchJndtByDay(map);
		jhrcList.addAll(jndtList);
		return jhrcList;*/
		return jhrcMapper.searchRcByDay(map);
	}
	
	@Transactional
	@Override
	public void inserYrzq(String zbrVal, String zbrText, String sxsj,String departId) {
		Page<JhrcEntity> page = jhrcMapper.searchJhrc(departId);
		List<JhrcEntity> list = page.getContent();
		jhrcMapper.deleteDsq();
		YrzqEntity yrzqEntity = new YrzqEntity();
		try {
			Date now = new Date();
//			System.out.println(now);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(sxsj);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DAY_OF_MONTH, 1);//进行当前日期加1
			while(c.getTime().before(now)){
				sxsj = sdf.format(c.getTime());
//				System.out.println(sxsj);//输出日期结果
				
				for(JhrcEntity entity:list) {
					String cpsStartDate = entity.getCpsStartDate();//开始日期
					String cpsEndDate = entity.getCpsEndDate();//结束日期
					if(isEffectiveDate(sxsj,cpsStartDate,cpsEndDate)) {//判断生效日期是否在事务开始与结束时间之间
						String cpsScheduleTime = entity.getCpsScheduleTime();//计划开始时间
						String  cDate=getDate(sxsj,cpsScheduleTime);//获取事务执行日期
						String startTime = cDate+" "+cpsScheduleTime;
						String endTime = cDate+" "+entity.getCpsScheduleEndTime();
						String title=CodeFacade.getCodeNameByCodeKey("4.20.60", entity.getCpsPlanDetail());
						String cpsLx = entity.getCpsLx();
						String tzsj = entity.getTzsj();
						String tznr = entity.getTznr();
						String tzsb = entity.getTzsb();
						String timeout = "1";
						String state = "1";//1 代表生效日期不是当天的
						String zbr = "";
						String zbrId = "";
						if(isThisTime(sxsj,"yyyy-MM-dd")) {
							timeout = "0";
							state = "0";
							zbr = zbrText;
							zbrId = zbrVal;
						}
						yrzqEntity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
						yrzqEntity.setPrisonId(entity.getCpsCusNumber());
						yrzqEntity.setDepartId(entity.getCpsDrpmntId());
						yrzqEntity.setDepartName(entity.getCpsDrpmntName());
						yrzqEntity.setStartTime(startTime);
						yrzqEntity.setEndTime(endTime);
						yrzqEntity.setCreateTime(new Timestamp(new Date().getTime()));
						yrzqEntity.setCreateUserId(entity.getCpsCrteUserId());
						yrzqEntity.setDataType("0");
						yrzqEntity.setState(state);
						yrzqEntity.setIsTimeout(timeout);
						yrzqEntity.setTitle(title);
						yrzqEntity.setCpsLx(cpsLx);
						yrzqEntity.setSxsj(sxsj);
						yrzqEntity.setTzsj(tzsj);
						yrzqEntity.setTznr(tznr);
						yrzqEntity.setZbr(zbr);
						yrzqEntity.setZbrId(zbrId);
						
						if(StringUtils.isNotBlank(tzsb)&&isThisTime(sxsj,"yyyy-MM-dd")) {
							String[] sb = tzsb.split(",");
							if(StringUtil.isNotBlank(tzsj)){
								for(int i = 0;i < sb.length;i++) {
									if("0".equals(sb[i])){
										insertDsq(tzsj);//定时任务
									}else if("1".equals(sb[i])) {
										insertDsqGb(tzsj);//定时任务
									}
								}
							}
						}
						yrzqService.insert(yrzqEntity);
					}
				}
				
				c.add(Calendar.DAY_OF_MONTH, 1);//进行当前日期加1
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//判断选择的日期是否是今天
	public static boolean isThisTime(String param, String pattern) {
	      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	      String now = sdf.format(new Date());//当前时间
	      if (param.equals(now)) {
	          return true;
	      }
	      return false;
	}
	/**
	* 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
	* 
	* @param now 当前时间
	* @param start 开始时间
	* @param end 结束时间
	* @return
	 * @throws ParseException 
	*/
	public static boolean isEffectiveDate(String now, String start, String ends) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotBlank(ends)){
			Date nowTime = sdf.parse(now);
			Date startTime = sdf.parse(start);
			Date endTime = sdf.parse(ends);
			if (nowTime.getTime() == startTime.getTime()
			|| nowTime.getTime() == endTime.getTime()) {
				return true;
			}
		
			Calendar date = Calendar.getInstance();
			date.setTime(nowTime);
		
			Calendar begin = Calendar.getInstance();
			begin.setTime(startTime);
		
			Calendar end = Calendar.getInstance();
			end.setTime(endTime);
		
			if (date.after(begin) && date.before(end)) {
				return true;
			} else {
				return false;
			}
		}else{
			return true;
		}
		
	}
//	@Transactional
//	@Override
//	public void inserYrzq(String zbrVal, String zbrText, String sxsj) {
//		Page<JhrcEntity> page = jhrcMapper.searchJhrc();
//		List<JhrcEntity> list = page.getContent();
//		jhrcMapper.deleteDsq();
//		YrzqEntity yrzqEntity = new YrzqEntity();
//		try {
//			for(JhrcEntity entity:list) {
//				String userId = entity.getCpsCrteUserId();
//				String cpsCusNumber = entity.getCpsCusNumber();
//				String cpsDrpmntId = entity.getCpsDrpmntId();
//				String cpsDrpmntName = entity.getCpsDrpmntName();
//				String cpsScheduleTime = entity.getCpsScheduleTime();//计划开始时间
//				String cpsStartDate = entity.getCpsStartDate();//开始日期
//				String cpsEndDate = entity.getCpsEndDate();//结束日期
//				String  cDate=getDate(cpsStartDate,cpsEndDate,cpsScheduleTime);
//				String startTime = cDate+" "+cpsScheduleTime;
//				String endTime = cDate+" "+entity.getCpsScheduleEndTime();
//				String title=CodeFacade.getCodeNameByCodeKey("4.20.60", entity.getCpsPlanDetail());
//				String cpsLx = entity.getCpsLx();
//				String tzsj = entity.getTzsj();
//				String tznr = entity.getTznr();
//				String tzsb = entity.getTzsb();
//				if(StringUtils.isNotBlank(tzsb)) {
//					String[] sb = tzsb.split(",");
//					if(StringUtil.isNotBlank(tzsj)){
//						for(int i = 0;i < sb.length;i++) {
//							if("0".equals(sb[i])){
//								insertDsq(tzsj);
//							}else if("1".equals(sb[i])) {
//								insertDsqGb(tzsj);
//							}
//						}
//					}
//				}
//				
//				
//				yrzqService.insertOnedayDutyDB(userId, cpsCusNumber, cpsDrpmntId, cpsDrpmntName,
//						startTime, endTime, "0", title, cpsLx, tzsj, tznr, zbrText, zbrVal);
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	@Transactional
	@Override
	public void insertDsq(String date) throws ParseException{
		ScheduleJob scheduleJob = new ScheduleJob();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(date));
		String hour = Integer.toString(c.get(Calendar.HOUR_OF_DAY)); 
		String minute = Integer.toString(c.get(Calendar.MINUTE)); 
		String second = Integer.toString(c.get(Calendar.SECOND));
		String tzsj = second + " " + minute + " " + hour + " * * ?";
		scheduleJob.setName("一日执勤");
		scheduleJob.setConcurrent(false);
		scheduleJob.setCronExpression(tzsj);
		scheduleJob.setTargetBean("rcsService");
		scheduleJob.setTargetMethod("startCallDsq");
		scheduleJob.setStartTime(new Date());
		scheduleJob.setPriority(1);
		scheduleJob.setDescription("");
		scheduleJob.setCalendarName("");
		this.scheduleJobService.insert(scheduleJob);
	}
	@Transactional
	@Override
	public void insertDsqGb(String date) throws ParseException{
		ScheduleJob scheduleJob = new ScheduleJob();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(date));
		String hour = Integer.toString(c.get(Calendar.HOUR_OF_DAY)); 
		String minute = Integer.toString(c.get(Calendar.MINUTE)); 
		String second = Integer.toString(c.get(Calendar.SECOND));
		String tzsj = second + " " + minute + " " + hour + " * * ?";
		scheduleJob.setName("一日执勤");
		scheduleJob.setConcurrent(false);
		scheduleJob.setCronExpression(tzsj);
		scheduleJob.setTargetBean("broadcastPlayService");
		scheduleJob.setTargetMethod("startPlayDsq");
		scheduleJob.setStartTime(new Date());
		scheduleJob.setPriority(1);
		scheduleJob.setDescription("");
		scheduleJob.setCalendarName("");
		this.scheduleJobService.insert(scheduleJob);
	}
	
	public String getDate(String sxsj, String cpsScheduleTime) {
//		String time = "";
		try {
//			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			time = sdf.format(date);
			String str = sxsj + " 08:00:00";
			String str2 = sxsj + " " + cpsScheduleTime;
//			int i = -2;
//			int j = -2;
			int l = -2;
//			if(StringUtils.isNotBlank(startTime)) {
//				i = sdf.parse(time).compareTo(sdf.parse(startTime));
//			}
//			if(StringUtils.isNotBlank(endTime)) {
//				j = sdf.parse(time).compareTo(sdf.parse(endTime));
//			}
//			if((StringUtils.isBlank(endTime) && (i == 1 || i == 0)) 
//					|| ((i == 1 || i == 0) && (j == -1 || j == 0))){
			l = sdf1.parse(str).compareTo(sdf1.parse(str2));
			if(l == 1) {
				Calendar c = Calendar.getInstance();  
		        c.setTime(sdf.parse(sxsj));  
		        c.add(Calendar.DAY_OF_MONTH, 1);        
		        sxsj = sdf.format(c.getTime());
			}
//			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sxsj;
	}

	@Override
	public Map<String, Object> getZbr(String cpsCusNumber, String cpsDrpmntId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cpsCusNumber", cpsCusNumber);
		map.put("cpsDrpmntId", cpsDrpmntId);
		return jhrcMapper.getZbr(map);
	}
	
	
}
