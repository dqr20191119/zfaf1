package com.cesgroup.prison.common.service.impl;
 
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.dao.AffixMapper;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.service.AffixService;

@Service("affixService")
public class AffixServiceImpl extends BaseDaoService<AffixEntity, String, AffixMapper> implements AffixService {
	
	@Resource
	private AffixMapper affixMapper;

	@Override
	public AffixEntity getById(String id) {
		
		return affixMapper.getById(id);
	}

	@Override
	public List<AffixEntity> findAllList(AffixEntity affixEntity) {
		
		return affixMapper.findAllList(affixEntity);
	}
 
	@Override
	@Transactional
	public List<AffixEntity> upload(MultipartFile[] file, AffixEntity affixEntity, HttpServletRequest request) throws Exception {
			
		List<AffixEntity> affixList = new ArrayList<AffixEntity>();		
		if(file != null && file.length > 0) {			
			for(int i = 0; i < file.length; i++) {	
				String fileOriName = file[i].getOriginalFilename();
				
				// 保存附件
				affixEntity.setFileName(fileOriName);
				affixEntity.setFileExts(fileOriName.substring(fileOriName.lastIndexOf(".") + 1));
				affixEntity.setFileSize(String.valueOf(file[i].getSize()));
				affixEntity.setFilePath(null);
				affixEntity.setImage(null);
				affixEntity.setYwId(null);
				affixEntity.setScrq(new Date());
				affixEntity.setScrId(AuthSystemFacade.getLoginUserInfo().getUserId());
				
				// 文件路径
				String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + affixEntity.getFileExts();
				String realPath = CommonConstant.jggzUploadsRootPath + File.separator + CommonConstant.uploadGlobalPath;								
				String folderName = new SimpleDateFormat("yyyy/MM").format(new Date());
				String folderdir = realPath + File.separator + folderName;
				File folder = new File(folderdir);
				if(!folder.exists()) {
					folder.mkdirs();
				}
								
				affixEntity.setLinkUrl("/" + CommonConstant.uploadGlobalPath + "/" + folderName + "/" + fileName);
				affixMapper.insert(affixEntity);		// 保存到数据库
				affixList.add(affixEntity);
				
				// 保存到磁盘
 				file[i].transferTo(new File(folder.getAbsolutePath() + File.separator + fileName));	
			}
		}
		
		return affixList;
	}
}
