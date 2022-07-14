package com.cesgroup.prison.zbgl.rygl.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cesgroup.prison.zbgl.rygl.dto.DutyCountDto;
import com.cesgroup.prison.zbgl.rygl.entity.DutyFlagEntity;
import com.cesgroup.prison.zbgl.rygl.entity.RyztHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.zbgl.rygl.dto.JyzzxxDto;
import com.cesgroup.prison.zbgl.rygl.entity.RyglEntity;

public interface RyglService extends IBaseCRUDService<RyglEntity, String> {
	
	RyglEntity getById(String id);

	Page<RyglEntity> findList(RyglEntity ryglEntity, Pageable pageable) throws Exception;


	Integer deleteByIds(String ids);

	List<JyzzxxDto>listSjzzxx();

	AjaxResult importFile(MultipartFile file, HttpServletRequest request);

	AjaxResult writerByIds(String ids);
	/**
	 * 通过监狱id 和部门id 查询值班人员信息
	 * @param ryglEntity
	 * @return
	 */
	List<RyglEntity> selectList(RyglEntity ryglEntity);

	List<RyglEntity> checkIsZhz(RyglEntity ry);

    /**
     * 根据监狱编号查询 人员信息
     * @param cusNumber
     * @return
     */
    List<Map<String,Object>> getUserInfoByCusNumber(String cusNumber);

    void updateById(RyglEntity ryglEntity);

    RyglEntity getMaxPbOrder();

    /**
     * 查询排班次数
     * @param map   cusNumber,orderName,type 不为空 传入List集合 key 为list
     * @return  DUTY_COUNT
     */
    List<DutyCountDto> dutyCount(Map<String,Object> map);

    /**
     * 根据id 更新标记表
     * @param dutyFlagEntity
     */
    void updateDutyFlagById(DutyFlagEntity dutyFlagEntity);

    List<DutyFlagEntity> seachDutyFlagData(String cusNumber);

    /**
     * 提前一个月进行消息提醒  状态为 4 5 6
     * @return
     */
    String remindMessageByRyzy();


    void insertRyztHistory(RyztHistoryEntity ryztHistoryEntity);

    Integer tZJob(RyglEntity ryglEntity);

    /**
     * 将本值班人员之后的人的值班顺序后移
     * @param ryglEntity1
     */
    void afterMove(RyglEntity ryglEntity1);
}
