package com.cesgroup.prison.common.web;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.dao.AffixMapper;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.common.service.AffixService;

/**
 * 文件管理
 * 
 */
@Controller
@RequestMapping(value = "/common/affix")
public class AffixController extends BaseEntityDaoServiceCRUDController<AffixEntity, String, AffixMapper, AffixService> {
	
	private final Logger logger = LoggerFactory.getLogger(AffixController.class);  
	
	@Resource
	private AffixService affixService;
	
	/**
	 * 上传文件
	 * @param file
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody	
	public String upload(@RequestParam(value = "uploadFile", required = false) MultipartFile[] file, AffixEntity affixEntity, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
				 							
		List<AffixEntity> affixList = affixService.upload(file, affixEntity, request); 					
		return JSON.toJSONString(affixList);
	}
	
	/**
	 * 删除文件
	 * @param file
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody	
	public void delete(AffixEntity affixEntity, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
				 							
		affixService.delete(affixEntity.getId());				
	}
	
	/**
	 * 下载文件
	 * @param file
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody	
	public void download(AffixEntity affixEntity, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
				 							
		affixEntity = affixService.getById(affixEntity.getId());
		
		response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(affixEntity.getFileName(), "utf-8"));
        response.addHeader("Content-Length", affixEntity.getFileSize());
        response.setContentType("application/octet-stream");
        
        String linkUrl = affixEntity.getLinkUrl();
		if(linkUrl != null && !"".equals(linkUrl)) {
			
			String realPath = CommonConstant.jggzUploadsRootPath;	//系统文件全局上传根路径
			if(linkUrl.indexOf(CommonConstant.ftpVideoPlanPath) != -1 || linkUrl.indexOf(CommonConstant.ftpVideoImgPath) != -1 
					|| linkUrl.indexOf(CommonConstant.ftpVideoRecordPath) != -1) {
				
				// 查询证据附件
				realPath = CommonConstant.systemRootAbsoultPath;
			}
			
			realPath = realPath + affixEntity.getLinkUrl().replaceAll("/", "\\\\");
			IOUtils.copy(new FileInputStream(new File(realPath)), response.getOutputStream());
		} else {
			IOUtils.write(affixEntity.getImage(), response.getOutputStream());
		}				
	}
}
