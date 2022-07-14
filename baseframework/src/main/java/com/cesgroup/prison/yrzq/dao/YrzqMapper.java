package com.cesgroup.prison.yrzq.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.yrzq.entity.YrzqEntity;

public interface YrzqMapper extends BaseDao<YrzqEntity, String> {
	
	public YrzqEntity getById(String id);
	
	public void deleteByIds(List<String> idList);

	public List<Map<String, Object>> findList(YrzqEntity yrzqEntity);

	public Page<YrzqEntity> findDataList(YrzqEntity yrzqEntity, PageRequest pageRequest);
	
	public List<Map<String, Object>> searchYrzq(Map<String, Object> map);
	
	public Page<YrzqEntity> findListPage(YrzqEntity yrzqEntity, PageRequest pageRequest);
	
	public Page<YrzqEntity> findAllListPage(YrzqEntity yrzqEntity, PageRequest pageRequest);
	
	public void updateIsTimeout(String id);
	
	public void updateFxcj(String id);
	
	public void updateRwjs(String id);
	
	public void updateRwxf(String id);
	
	public Page<YrzqEntity> searchSwdbPage(YrzqEntity yrzqEntity, PageRequest pageRequest);
	
	public Page<YrzqEntity> searchSwdbList(Map<String, Object> map, PageRequest pageRequest);
	
	public List<Map<String, Object>> openCamare(String xlId);
	
	public Map<String, Object> getFxcj(String id);
	
	public List<Map<String, Object>> getMj(String departId);
	
	public List<Map<String, Object>> getJb(YrzqEntity yrzqEntity);
	
	/**
	 * 根据部门编号、状态、标题查询一日执勤数据，并按照生效时间倒序排列
	 * 
	 * @param departId
	 * @param state
	 * @param title
	 * @return
	 */
	public List<Map<String, Object>> findByDepartIdAndStateAndTitleOrderBySxsjDesc(@Param("departId") String departId, @Param("state") String state, @Param("title") String title);
	
	//查询执勤情况
	public List<Map<String, Object>> searchZbrz(@Param("departId")String departId, @Param("sxsj")String sxsj);
	
	public List<Map<String, Object>> getZbrz(@Param("departId")String departId);
	//查询当天接班情况
	public List<Map<String, Object>> getJieb(YrzqEntity yrzqEntity);
	
	public void updateZbr(YrzqEntity yrzqEntity);
	
}
