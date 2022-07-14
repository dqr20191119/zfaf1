package com.cesgroup.prison.zbgl.mbsz.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.prison.zbgl.mbbm.entity.MbbmEntity;
import com.cesgroup.prison.zbgl.mbsz.dto.DutyPoliceDto;
import com.cesgroup.prison.zbgl.mbxq.entity.MbxqEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.zbgl.mbsz.entity.MbszEntity;

public interface MbszService extends IBaseCRUDService<MbszEntity, String> {

	MbszEntity getById(String id);

	Page<MbszEntity> findList(MbszEntity mbszEntity, PageRequest pageRequest);

	List<MbszEntity> findAllList(MbszEntity mbszEntity);

	void saveOrUpdate(MbszEntity mbszEntity) throws Exception;

	void deleteById(String id);

	void updateSfqyById(String id, String sfqy);

	AjaxResult searchSfqy(String cusNumber,String id);

	Map<String, List<Map<String,Object>>> getAutoZbData(String mojStartDate,String mojEndDate);

	List<Map<String, Object>> getPrintData(List<MbxqEntity> mbxqList, String id);

	/**
	 * 统计男值班长连续值班信息
	 * @param orderData
	 * @return
	 */
	String countcontinueDuty(List<Map<String, Object>> orderData,String startDate,String endDate);

	/**
	 * 查询本次未参与值班的人员情况
	 * @param mojStartDate
	 * @param mojEndDate
	 * @return
	 */
	String selectNodutyByStartDateAndEndDate(String mojStartDate, String mojEndDate);

	/**
	 * 省局新自动排班
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Map<String, List<Map<String,Object>>> getAutoZbDataNew(String startDate, String endDate);

	void updateLastDutyFlagNew(MbbmEntity mbbm) throws Exception;

}
