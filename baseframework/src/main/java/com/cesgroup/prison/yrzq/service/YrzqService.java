package com.cesgroup.prison.yrzq.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yrzq.entity.YrzqEntity;


public interface YrzqService extends IBaseCRUDService<YrzqEntity, String> {

	public YrzqEntity getById(String id);

	public List<Map<String, Object>> findList(YrzqEntity yrzqEntity);
	
	public Page<YrzqEntity> findListPage(YrzqEntity yrzqEntity, PageRequest pageRequest);
	
	public Page<YrzqEntity> findAllListPage(YrzqEntity yrzqEntity, PageRequest pageRequest);
	
	public List<Map<String, Object>> searchYrzq(String prisonId, String departId, String pDate);

	public void saveOrUpdate(String id,String mark,String fxcjId,String zbrz) throws Exception;

	public void deleteByIds(String ids);

	public void insertOnedayDutyDB(String userId, String cpsCusNumber, String cpsDrpmntId, String cpsDrpmntName, String startTime,String endTime, String dataType, String title, String cpsLx, String tzsj, String tznr, String zbr, String zbrId);

	public Page<YrzqEntity> findDataList(YrzqEntity yrzqEntity, PageRequest pageRequest);

	
	public Page<YrzqEntity> searchSwdbPage(YrzqEntity yrzqEntity, PageRequest pageRequest);
	
	public Page<YrzqEntity> searchSwdbList(String departId, String dateTime, String state, PageRequest pageRequest);

	
	public List<Map<String, Object>> openCamare(String xlId);
	
	public List<Map<String, Object>> getYwyth();
	
	public List<Map<String, Object>> getsbxx();
	
	public Map<String, Object> getFxcj(String id);
	
	public String getMj(String departId);
	
	public String searchZbrz(String departId, String sxsj);
	
	public String getZbrz(String departId);
	
	public void updateZbr(Map<String, Object> map);
}
