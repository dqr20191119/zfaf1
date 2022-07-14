package com.cesgroup.prison.zbgl.pbtbhz.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.zbgl.pbtbhz.entity.JyzbBaseEntity;
import com.cesgroup.prison.zbgl.pbtbhz.entity.JyzbDetailEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
* @author lihong
* @date 创建时间：2020年5月15日 上午10:55:36
* 类说明:
*/
public interface JyzbBaseService extends IBaseCRUDService<JyzbBaseEntity, String> {
	Page<JyzbBaseEntity> findList(JyzbBaseEntity jyzbBaseEntity, PageRequest pageRequest);

	void deleteById(String id) throws Exception;

	void updateById(JyzbBaseEntity jyzbBaseEntity);

	List<JyzbDetailEntity> getJyzbDetailByYwID(JyzbBaseEntity jyzbBaseEntity);

	void saveOrUpdate(JyzbBaseEntity jyzbBaseEntity);

	AjaxResult checkJyzbByzbYf(JyzbBaseEntity jyzbBaseEntity);

	Page<JyzbDetailEntity> getZbDetailByDay(String dutyDate,String zbYf,PageRequest pageRequest);

	AjaxResult writerByDutyDate(String dutyDate,String zbYf);


    AjaxResult importFile(MultipartFile file, HttpServletRequest request);
}
