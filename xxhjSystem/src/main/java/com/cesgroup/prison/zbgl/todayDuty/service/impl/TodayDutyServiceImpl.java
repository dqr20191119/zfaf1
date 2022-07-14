package com.cesgroup.prison.zbgl.todayDuty.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.zbgl.gwgl.dao.GwglMapper;
import com.cesgroup.prison.zbgl.gwgl.dto.DutyJobDto;
import com.cesgroup.prison.zbgl.todayDuty.dao.TodayDutyDao;
import com.cesgroup.prison.zbgl.todayDuty.dto.TodayDutyDto;
import com.cesgroup.prison.zbgl.todayDuty.entity.TodayDuty;
import com.cesgroup.prison.zbgl.todayDuty.service.TodayDutyService;

/**
 * 今日值班业务操作实现类
 * 
 * @author lincoln.cheng
 *
 */
@Service(value="todayDutyService")
public class TodayDutyServiceImpl extends BaseDaoService<TodayDuty, String, TodayDutyDao> implements TodayDutyService {
	/**
	 * 岗位管理Mapper
	 */
	@Resource
	private GwglMapper gwglMapper;
	
	@Override
	public Page<Map<String, Object>> queryWithPage(Map<String, Object> param, Pageable pageable) {
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			if(user != null) {
				// 监狱编号
				param.put("cusNumber", user.getCusNumber());
			}
            return this.getDao().findWithPage(param, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public List<TodayDutyDto> queryByCategoryNameAndModelNameAndOrderNameLike(Map<String, Object> paramMap) {
		return this.getDao().findByCategoryNameAndModeNameAndOrderNameLike(paramMap);
	}

	@Override
	public List<TodayDutyDto> filterByIsWeekend(List<TodayDutyDto> dutyDtoList) {
		List<TodayDutyDto> newDutyDtoList = new ArrayList<TodayDutyDto>();
		if(dutyDtoList != null) {
			boolean isWeekend = Util.isWeekend(new Date());
			for(TodayDutyDto dutyDto : dutyDtoList) {
				if(isWeekend) {
					if(this.isWeekendDuty(dutyDto)) {
						newDutyDtoList.add(dutyDto);
					}
				} else {
					if(!this.isWeekendDuty(dutyDto)) {
						newDutyDtoList.add(dutyDto);
					}
				}
			}
		}
		return newDutyDtoList;
	}

	/**
	 * 根据上级部门名称，查询下级直属各部门的今日值班数据
	 */
	@Override
	public List<Map<String, Object>> queryByCategoryNameLikeAndModeNameLikeAndParentDeptName(String categoryName, String modeName, String parentDeptName) {
		List<Map<String, Object>> resultMapList = new ArrayList<Map<String, Object>>();
		
		// 根据机构名称，查询下级机构列表
		List<OrgEntity> orgList = AuthSystemFacade.getAllChildrenOrgInfoByOrgName(parentDeptName);
		
		// 循环机构列表，获取各个机构的今日值班数据
		if(orgList != null && orgList.size() > 0) {
			Map<String, Object> paramMap = new HashMap<>();
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			if(user != null) {
				// 监狱编号
				paramMap.put("cusNumber", user.getCusNumber());
			}
			paramMap.put("categoryName", categoryName);
			paramMap.put("modeName", modeName);
			paramMap.put("dutyDate", Util.toStr(Util.DF_DATE));
			
			for(OrgEntity org : orgList) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				
				paramMap.put("deptCode", org.getOrgKey());
				
				// 值班人员列表
				List<TodayDutyDto> dutyList = this.getDao().findByCategoryNameLikeAndModeNameLikeAndDeptCode(paramMap);

				// 根据当天时间是否周末来过滤值班人员
				// List<TodayDutyDto> newDutyList = this.filterByIsWeekend(dutyList);
				
				map.put("deptInfo", org);
				map.put("dutyList", dutyList);
				resultMapList.add(map);
			}
		}
		
		return resultMapList;
	}
	
	/**
	 * 根据部门编码获取部门的今日值班列表，并按照值班岗位分组
	 * @throws BusinessLayerException 
	 */
	@Override
	public List<Map<String, Object>> queryByDeptCodeGroupByDutyJob(String deptCode) throws BusinessLayerException {
		List<Map<String, Object>> resultMapList = new ArrayList<Map<String, Object>>();
		try {
			// 根据部门编码，获取本部门的值班岗位列表
			List<DutyJobDto> dutyJobDtoList = this.gwglMapper.findDutyJobDtoListByDeptCode(deptCode);
			
			// 根据部门值班岗位，查询该部门各值班岗位的值班人员列表
			if(dutyJobDtoList != null && dutyJobDtoList.size() > 0) {
				Map<String, Object> paramMap = new HashMap<>();
				UserBean user = AuthSystemFacade.getLoginUserInfo();
				if(user != null) {
					// 监狱编号
					paramMap.put("cusNumber", user.getCusNumber());
				}
				paramMap.put("deptCode", deptCode);
				paramMap.put("dutyDate", Util.toStr(Util.DF_DATE));
				
				for(DutyJobDto dutyJobDto : dutyJobDtoList) {
					Map<String, Object> map = new LinkedHashMap<String, Object>();
					
					paramMap.put("dutyJobId", dutyJobDto.getId());
					
					// 值班人员列表
					List<TodayDutyDto> dutyList = this.getDao().findByDeptCodeAndDutyJobId(paramMap);
	
					// 根据当天时间是否周末来过滤值班人员
					// List<TodayDutyDto> newDutyList = this.filterByIsWeekend(dutyList);
					
					map.put("dutyJob", dutyJobDto);
					map.put("dutyList", dutyList);
					resultMapList.add(map);
				}
			}
		} catch (Exception e) {
			throw new BusinessLayerException("据部门编码获取部门的今日值班列表，并按照值班岗位分组发生异常");
		}
		return resultMapList;
	}

	/**
	 * 值班数据是否周末或节假日值班
	 * 
	 * @param dutyDto
	 * @return
	 */
	private boolean isWeekendDuty(TodayDutyDto dutyDto) {
		if(dutyDto != null) {
			String cdjJobName = Util.notNull(dutyDto.getCdjJobName()) ? dutyDto.getCdjJobName() : "";
			if(cdjJobName.contains("周末") || cdjJobName.contains("节假日")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<TodayDutyDto> getTodayDuty(String cusNumber, String orderName) throws Exception {
		String dutyDate =DateUtils.getCurrentDate(false);
		return this.getDao().getTodayDuty(cusNumber,orderName,dutyDate);
	}

	@Override
	public Page<Map<String, Object>> getTodayDutyListWithPage(Map<String, Object> param, Pageable pageable) throws Exception {
		
		return this.getDao().getTodayDutyListWithPage(param, pageable);
	}

}
