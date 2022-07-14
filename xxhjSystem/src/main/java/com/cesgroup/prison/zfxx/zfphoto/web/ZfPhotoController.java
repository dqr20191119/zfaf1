package com.cesgroup.prison.zfxx.zfphoto.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zfphoto.dao.ZfPhotoDao;
import com.cesgroup.prison.zfxx.zfphoto.entity.ZfPhoto;
import com.cesgroup.prison.zfxx.zfphoto.service.ZfPhotoService;

/**
 * Description: 罪犯照片控制器类
 * @author lincoln.cheng
 *
 * 2019年1月14日
 */
@Controller
@RequestMapping("/zfxx/zfPhoto")
public class ZfPhotoController extends BaseEntityDaoServiceCRUDController<ZfPhoto, String, ZfPhotoDao, ZfPhotoService> {
	
}
