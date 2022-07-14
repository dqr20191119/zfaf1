package com.cesgroup.prison.zbgl.todayDuty.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.zbgl.todayDuty.dto.TodayDutyDto;
import com.cesgroup.prison.zbgl.todayDuty.entity.TodayDuty;

/**
 * 今日值班业务操作类
 * 
 * @author lincoln.cheng
 *
 */
public interface TodayDutyService extends IBaseCRUDService<TodayDuty, String> {

	/**
	 * 分页查询今日值班数据
	 * 
	 * @param param
	 * @param pageable
	 * @return
	 */
    Page<Map<String, Object>> queryWithPage(Map<String, Object> param, Pageable pageable);
    
	/**
	 * 根据类型名称、模板名称、班次名称，查询今日值班信息
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<TodayDutyDto> queryByCategoryNameAndModelNameAndOrderNameLike(Map<String, Object> paramMap);

	/**
	 * 根据类型名称、模板名称、班次名称，查询今日值班信息
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<TodayDutyDto> filterByIsWeekend(List<TodayDutyDto> dutyDtoList);

	/**
	 * 根据类别名称like、模板名称like、上级部门名称，查询下级各部门的今日值班列表
	 * 
	 * @param categoryName
	 * @param modeName
	 * @param parentDeptName
	 * @return
	 */
	public List<Map<String, Object>> queryByCategoryNameLikeAndModeNameLikeAndParentDeptName(String categoryName, String modeName, String parentDeptName);

	/**
	 * 根据部门编码获取部门的今日值班列表，并按照值班岗位分组
	 * 
	 * @param deptCode
	 * @return
	 */
	public List<Map<String, Object>> queryByDeptCodeGroupByDutyJob(String deptCode) throws BusinessLayerException;

	List<TodayDutyDto> getTodayDuty(String cusNumber, String orderName) throws Exception;

	Page<Map<String, Object>> getTodayDutyListWithPage(Map<String, Object> param, Pageable pageable) throws Exception;
}
