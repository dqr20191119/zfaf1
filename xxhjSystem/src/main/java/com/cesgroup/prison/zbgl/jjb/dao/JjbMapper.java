package com.cesgroup.prison.zbgl.jjb.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.jjb.dto.DutyDataDto;
import com.cesgroup.prison.zbgl.jjb.entity.JjbEntity;

/**
* @author lihong
* @date 创建时间：2020年5月15日 上午9:56:37
* 类说明:
*/
public interface JjbMapper extends BaseDao<JjbEntity, String> {

	Page<JjbEntity> findList(JjbEntity jjbEntity, PageRequest pageRequest);

	void updateById(JjbEntity jjbEntity);
	/**
	 * 查询值班信息
	 * @param dutyDataDto
	 * @return
	 */
	List<DutyDataDto>  selectNowDayDutyData(DutyDataDto dutyDataDto);
	/**
	 *获取下个班次
	 * @param jjbEntity
	 * @return
	 */
	List<JjbEntity> getNextOrder(JjbEntity jjbEntity);
}
