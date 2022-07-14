package com.cesgroup.prison.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.common.entity.AffixEntity;

public interface AffixService extends IBaseCRUDService<AffixEntity, String> {

	public AffixEntity getById(String id);
	
	public List<AffixEntity> findAllList(AffixEntity affixEntity);
	
	public List<AffixEntity> upload(MultipartFile[] file, AffixEntity affixEntity, HttpServletRequest request) throws Exception;
}
