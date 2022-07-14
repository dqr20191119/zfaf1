package com.cesgroup.prison.zbgl.jjb.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.zbgl.jjb.dao.JjbMapper;
import com.cesgroup.prison.zbgl.jjb.dao.JjbRzglMapper;
import com.cesgroup.prison.zbgl.jjb.dto.DutyDataDto;
import com.cesgroup.prison.zbgl.jjb.entity.JjbEntity;
import com.cesgroup.prison.zbgl.jjb.entity.JjbRzglEntity;
import com.cesgroup.prison.zbgl.jjb.service.JjbRzglService;
import com.cesgroup.prison.zbgl.jjb.service.JjbService;
import com.hp.hpl.sparta.ParseException;

/**
* @author lihong
* @date 创建时间：2020年5月15日 上午9:58:37
* 类说明:
*/
@Service
public class JjbServiceImpl extends BaseDaoService<JjbEntity, String, JjbMapper> implements JjbService  {
	@Resource
	JjbRzglMapper jjMapper ;
	
	@Override
	public Page<JjbEntity> findList(JjbEntity jjbEntity, PageRequest pageRequest) {
		return this.getDao().findList(jjbEntity,pageRequest);
	}

	@Override
	@Transactional
	public void updateById(JjbEntity jjbEntity) {
		this.getDao().updateById(jjbEntity);
	}

	@Override
	@Transactional
	public AjaxResult inItDutyData() {
		/**
		 * 1.在交接班主表中查询是否已经有当日是否有班次  若有就不插入数据
		 * 2.如果没有去值班编查 找当天的值班信息  
		 * 3.若有当日的值班信息 查询出当日最早班次的信息  插入到交班表中
		 * 4.如没有,返回给前端消息提醒让其去排班
		 */
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		JjbEntity jjb = new JjbEntity();
		jjb.setDutyDate(DateUtils.getCurrentDate(false));
		List<JjbEntity> jjbList = this.getDao().selectByEntity(jjb);
		if(jjbList.size()<=0) {
			DutyDataDto dutyData = new DutyDataDto();
			dutyData.setCusNumber(user.getCusNumber());
			dutyData.setDeptNumber(user.getDprtmntCode());
			dutyData.setDutyDate(new Date());
			dutyData.setJobName("值班长");
			List<DutyDataDto> zbzDutyDatas = this.getDao().selectNowDayDutyData(dutyData);
			dutyData.setJobName("值班员");
			List<DutyDataDto> zbyDutyDatas = this.getDao().selectNowDayDutyData(dutyData);
			if(zbzDutyDatas.size()<=0) {
				return AjaxResult.success("没有值班长数据,请先排班");
			}else {
				for (int i = 0; i < zbzDutyDatas.size(); i++) {
					  JjbEntity jjbEntity = new JjbEntity();
					  jjbEntity.setCjTime(DateUtils.getCurrentDate(false));
					  jjbEntity.setCusNumber(zbzDutyDatas.get(i).getCusNumber());
					  jjbEntity.setDeptNumber(zbzDutyDatas.get(i).getDeptNumber());
					  jjbEntity.setDutyDate(DateUtils.formatDate(zbzDutyDatas.get(i).getDutyDate()));
					  jjbEntity.setStartTime(zbzDutyDatas.get(i).getSartTime());
					  jjbEntity.setEndTime(zbzDutyDatas.get(i).getEndTime());
					  jjbEntity.setOrderName(zbzDutyDatas.get(i).getOrderName());
					  jjbEntity.setDutyTime(zbzDutyDatas.get(i).getSartTime()+"-"+zbzDutyDatas.get(i).getEndTime());
					  jjbEntity.setZbzId(zbzDutyDatas.get(i).getZbrId());
					  jjbEntity.setZbzName(zbzDutyDatas.get(i).getZbrName());
					  jjbEntity.setZbyId(zbyDutyDatas.get(i).getZbrId());
					  jjbEntity.setZbyName(zbyDutyDatas.get(i).getZbrName());
					  jjbEntity.setZt("1");//待值班
					  this.getDao().insert(jjbEntity);
				}
			}
		}else {
			return AjaxResult.success("true");
		}
		
		return AjaxResult.success("值班数据初始化成功");
	}

	@Override
	public AjaxResult checkIsZbry(String id) {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String flag = "false";
		JjbEntity jjb = this.getDao().selectOne(id);
		String[] zbzNames = jjb.getZbzName().split(",");
		String[] zbyNames = jjb.getZbyName().split(",");
		//比较值班长
		for(String zbzName :zbzNames) {
			if(user.getRealName().equals(zbzName)) {
				flag = "true";
				return AjaxResult.success(flag);
			}
		}
		//比较值班员
		for(String zbyName :zbyNames) {
			if(user.getRealName().equals(zbyName)) {
				flag = "true";
				return AjaxResult.success(flag);
			}
		}
		return AjaxResult.success(flag);
	}

	@Override
	public Page<JjbRzglEntity> findJjbRzList(String id, PageRequest pageRequest) {
		JjbRzglEntity  jjbrz  =  new JjbRzglEntity();
		jjbrz.setYwId(id);
		return jjMapper.findList(jjbrz,pageRequest);
	}
	/**
	 * 交接班提交
	 */
	@Override
	@Transactional
	public AjaxResult jjbTj(JjbEntity jjbEntity) {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		jjbEntity.setUpdateTime(DateUtils.getCurrentDate(true));
		jjbEntity.setGxrId(user.getUserId());
		jjbEntity.setGxrName(user.getUserName());
		if("1".equals(jjbEntity.getParam())) {//交班提交
			jjbEntity.setJbTime(DateUtils.getCurrentDate(true));
			this.getDao().updateById(jjbEntity);
		}else {//接班提交
			this.getDao().updateById(jjbEntity);
			//改变下一个班为值班状态  如当前班次为最后一个班  则初始化明天的数据
			JjbEntity jjb = this.getDao().selectOne(jjbEntity.getId());
			JjbEntity jjbNext = new  JjbEntity();
			jjbNext.setDutyDate(jjb.getDutyDate());
			jjbNext.setStartTime(jjb.getStartTime());
			List<JjbEntity> nextOrder = this.getDao().getNextOrder(jjbNext);//下个班次
			if(nextOrder.size()<=0) {//最后一个班次
				//在交接班主表中查询是否有明天的数据  有就不初始化数据  因为前台做过明天是否有值班长的效验  这里不做效验,只明天的数据
				JjbEntity nextDayJjb = new JjbEntity();
				nextDayJjb.setCusNumber(jjb.getCusNumber());
				nextDayJjb.setDeptNumber(jjb.getDeptNumber());
				try {
					nextDayJjb.setDutyDate(sdf.format(CommonUtil.addDate(sdf.parse(jjb.getDutyDate()), 1)));
				} catch (Exception e) {
					e.printStackTrace();
				}
				List<JjbEntity> nextDayList = this.getDao().selectByEntity(nextDayJjb);//明天的数据
				if(nextDayList.size() <= 0 ) {//交接班主表中没有明天的数据  插入排班的数据
					//初始化明天的班次数据
					DutyDataDto dutyData = new DutyDataDto();
					dutyData.setCusNumber(user.getCusNumber());
					dutyData.setDeptNumber(user.getDprtmntCode());
					try {
						dutyData.setDutyDate(CommonUtil.addDate(sdf.parse(jjb.getDutyDate()), 1));
					} catch (Exception e) {
						e.printStackTrace();
					}
					dutyData.setJobName("值班长");
					List<DutyDataDto> zbzDutyDatas = this.getDao().selectNowDayDutyData(dutyData);
					dutyData.setJobName("值班员");
					List<DutyDataDto> zbyDutyDatas = this.getDao().selectNowDayDutyData(dutyData);
					if(zbzDutyDatas.size()<=0) {
						return AjaxResult.success("明天没有排班,请先排班");
					}else {
						for (int i = 0; i < zbzDutyDatas.size(); i++) {
							  JjbEntity jjbEntity2 = new JjbEntity();
							  jjbEntity2.setCjTime(DateUtils.getCurrentDate(false));
							  jjbEntity2.setCusNumber(zbzDutyDatas.get(i).getCusNumber());
							  jjbEntity2.setDeptNumber(zbzDutyDatas.get(i).getDeptNumber());
							  jjbEntity2.setDutyDate(DateUtils.formatDate(zbzDutyDatas.get(i).getDutyDate()));
							  jjbEntity2.setStartTime(zbzDutyDatas.get(i).getSartTime());
							  jjbEntity2.setEndTime(zbzDutyDatas.get(i).getEndTime());
							  jjbEntity2.setOrderName(zbzDutyDatas.get(i).getOrderName());
							  jjbEntity2.setDutyTime(zbzDutyDatas.get(i).getSartTime()+"-"+zbzDutyDatas.get(i).getEndTime());
							  jjbEntity2.setZbzId(zbzDutyDatas.get(i).getZbrId());
							  jjbEntity2.setZbzName(zbzDutyDatas.get(i).getZbrName());
							  jjbEntity2.setZbyId(zbyDutyDatas.get(i).getZbrId());
							  jjbEntity2.setZbyName(zbyDutyDatas.get(i).getZbrName());
							  jjbEntity2.setZt("1");//待值班
							  this.getDao().insert(jjbEntity2);
						}
					}
				}
			}
		}
		return AjaxResult.success();
	}

	@Override
	public AjaxResult checkIsZbz(JjbEntity jjbEntity) {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String flag = "false";
		JjbEntity jjb = this.getDao().selectOne(jjbEntity.getId());
		if("1".equals(jjbEntity.getParam())) {//交班  查询用户是不是值班长
			String[] zbzs = jjb.getZbzName().split(",");
			for(String zbz:zbzs) {
				if(zbz.equals(user.getRealName())) {
					flag = "true";
					return AjaxResult.success(flag);
				}
			}
		}else {//接班
			//如当前班次为最后一个班  查询明天的数据
			JjbEntity jjbNext = new  JjbEntity();
			jjbNext.setDutyDate(jjb.getDutyDate());
			jjbNext.setStartTime(jjb.getStartTime());
			List<JjbEntity> nextOrder = this.getDao().getNextOrder(jjbNext);
			if(nextOrder.size() <=0) {//说明为最后一个班
				DutyDataDto dutyData = new DutyDataDto();
				dutyData.setCusNumber(jjb.getCusNumber());
				dutyData.setDeptNumber(jjb.getDeptNumber());
				try {
					dutyData.setDutyDate(CommonUtil.addDate(sdf.parse(jjb.getDutyDate()), 1));
				} catch (Exception e) {
					e.printStackTrace();
				}
				dutyData.setJobName("值班长");
				List<DutyDataDto> zbzDutyDatas = this.getDao().selectNowDayDutyData(dutyData);//下个班次 已经排序 取第一条
				if(zbzDutyDatas.size() > 0) {
					String[] zbzNames = zbzDutyDatas.get(0).getZbrName().split(",");
					for(String zbzName:zbzNames) {
						if(zbzName.equals(user.getRealName())){//判断用户是否是下个班次的值班长
							flag = "true";
							return AjaxResult.success(flag);
						}
					}
				}else {
					return AjaxResult.success("明天没有排班,无法确定下班个的值班长为谁,请先排班!");
				}
			}else {//不为最后一个班次  区第一条 为下个班次
				String[] zbzNames = nextOrder.get(0).getZbzName().split(",");
				for(String zbzName:zbzNames) {
					if(zbzName.equals(user.getRealName())) {
						flag = "true";
						return AjaxResult.success(flag);
					}
				}
			}
			
		}
		return AjaxResult.success(flag);
	}


}
