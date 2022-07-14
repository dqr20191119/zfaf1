package com.cesgroup.prison.common.web;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.dao.CommonEvidenceMapper;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.common.entity.CommonEvidenceEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.service.CommonEvidenceService;
import com.cesgroup.prison.utils.DataUtils;

/**
 * 证据管理
 * 
 */
@Controller
@RequestMapping(value = "/common/evidence")
public class CommonEvidenceController extends BaseEntityDaoServiceCRUDController<CommonEvidenceEntity, String, CommonEvidenceMapper, CommonEvidenceService> {
	
	private final Logger logger = LoggerFactory.getLogger(CommonEvidenceController.class);  
	
	@Resource
	private CommonEvidenceService commonEvidenceService;
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public CommonEvidenceEntity getById(String id, HttpServletRequest request,
			HttpServletResponse response) {
		 			
		return commonEvidenceService.getById(id);
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(CommonEvidenceEntity commonEvidenceEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		
		commonEvidenceEntity.setEinCusNumber(user.getOrgCode());
		Page<CommonEvidenceEntity> pageInfo = (Page<CommonEvidenceEntity>) commonEvidenceService.findList(commonEvidenceEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	/**
	 * 下载文件
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody	
	public void download(CommonEvidenceEntity commonEvidenceEntity, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
				 							
		commonEvidenceEntity = commonEvidenceService.getById(commonEvidenceEntity.getId());
		
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(commonEvidenceEntity.getEinFileName(), "utf-8"));
        response.setContentType("application/octet-stream");
        
        String ftpPath = commonEvidenceEntity.getEinFtpPrefix() + "\\" + commonEvidenceEntity.getEinFtpPath() + "\\" + commonEvidenceEntity.getEinFileName();
		if(ftpPath != null && !"".equals(ftpPath)) {
			 
			String	realPath = CommonConstant.systemRootAbsoultPath;
			realPath = realPath + "\\\\" + ftpPath.replace("\\", "\\\\");
			
			response.addHeader("Content-Length", new File(realPath).length() + "");
			IOUtils.copy(new FileInputStream(new File(realPath)), response.getOutputStream());
		}				
	}
}
