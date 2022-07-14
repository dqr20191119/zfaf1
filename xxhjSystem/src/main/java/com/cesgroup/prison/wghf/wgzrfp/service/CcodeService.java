package com.cesgroup.prison.wghf.wgzrfp.service;

import java.util.List;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.TreeDto;
import com.cesgroup.framework.exception.BusinessLayerException;

import com.cesgroup.prison.wghf.wgzrfp.entiy.Ccode;


public interface CcodeService extends IBaseCRUDService<Ccode, String>{

	List<TreeDto> initCcodeTree()throws BusinessLayerException;;

}
