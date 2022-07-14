package com.cesgroup.prison.zbgl.zbbp.service;

import java.util.Date;
import java.util.List;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.zbgl.zbbp.dto.ZbbpDto;
import com.cesgroup.prison.zbgl.zbbp.entity.ZbbpEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ZbbpService extends IBaseCRUDService<ZbbpEntity, String> {
	
	public List<ZbbpEntity> findAllList(ZbbpEntity zbbpEntity);
	
	public void saveDutyData(ZbbpEntity zbbpEntity) throws Exception;

	public void deleteByConditions(ZbbpEntity zbbpEntity);
	/**
	 * 回退之前排班的次数 
	 * @param dutyModelDepartmentId 为模板详情表的id
	 */
	public void updateRyPbCount(String dutyModelDepartmentId);
	/**
	 * 打印值班表
	 * @param zbbpDto
	 * @return
	 */
	public AjaxResult writerZbpById(ZbbpDto zbbpDto);

    /**
     * 导出值班模板
     * @param zbbpDto
     * @return
     */
    AjaxResult exportZbmbExcel(ZbbpDto zbbpDto);

    /**
     * 导入值班数据
     * @param file
     * @param request
     * @return
     */
    AjaxResult importZbData(MultipartFile file, ZbbpDto zbbpDto,String dmdName);
}
