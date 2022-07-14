package com.cesgroup.prison.zbgl.gwgl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.gwgl.dto.DutyJobDto;
import com.cesgroup.prison.zbgl.gwgl.entity.GwglEntity;

public interface GwglMapper extends BaseDao<GwglEntity, String> {

	public GwglEntity getById(String id);

	public Page<GwglEntity> findList(GwglEntity gwglEntity, PageRequest pageRequest);

	public List<GwglEntity> findAllList(GwglEntity gwglEntity); 
	
	public void updateStatusByIds(List<String> idList);

	/**
	 * 根据部门编码，查询部门的值班岗位列表
	 * @param deptCode
	 * @return
	 */
	public List<DutyJobDto> findDutyJobDtoListByDeptCode(@Param("deptCode") String deptCode);
}
