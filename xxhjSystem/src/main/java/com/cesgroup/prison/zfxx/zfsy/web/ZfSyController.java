package com.cesgroup.prison.zfxx.zfsy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zfsy.dao.ZfSyDao;
import com.cesgroup.prison.zfxx.zfsy.entity.ZfSy;
import com.cesgroup.prison.zfxx.zfsy.service.ZfSyService;

/**
 * Description: 收押控制器类
 * @author lincoln.cheng
 *
 * 2019年1月14日
 */
@Controller
@RequestMapping("/zfxx/zfSy")
public class ZfSyController extends BaseEntityDaoServiceCRUDController<ZfSy, String, ZfSyDao, ZfSyService> {
	
}
