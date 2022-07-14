package com.cesgroup.prison.yjct.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.yjct.entity.YjjlEntity;

public interface YjjlService extends IBaseCRUDService<YjjlEntity, String> {
	
	public YjjlEntity getById(String id);

	public Page<YjjlEntity> findList(YjjlEntity yjjlEntity, PageRequest pageRequest);

	public YjjlEntity saveOrUpdate(YjjlEntity yjjlEntity);

	public void saveOrUpdateEventReport(YjjlEntity yjjlEntity);
	
	public List<YjjlEntity> tj(YjjlEntity yjjlEntity);

	public String updateRecordStatus(YjjlEntity yjjlEntity);

	public Map<String, Object> findEventRecord(String id);
	
	/**
	 * 功能描述：根据预警记录ID与工作组Id查询预案名称、应急记录状态、工作组名称
	 * 主要是为了调用应急预案融合通信组织数据
	 * @param yjjlId
	 * @param gzzId
	 * @return
	 */
	public Map<String, String> queryYjjlStatusAndYjyaNameAndGzzName(String yjjlId, String gzzId);
}
