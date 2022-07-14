package com.cesgroup.prison.zfxx.zfdqzt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zfdqzt.dao.ZfDqztDao;
import com.cesgroup.prison.zfxx.zfdqzt.entity.ZfDqzt;
import com.cesgroup.prison.zfxx.zfdqzt.service.ZfDqztService;

/**
 * Description: 罪犯当前状态控制器类
 * @author lincoln.cheng
 *
 * 2019年1月14日
 */
@Controller
@RequestMapping("/zfxx/zfDqzt")
public class ZfDqztController extends BaseEntityDaoServiceCRUDController<ZfDqzt, String, ZfDqztDao, ZfDqztService> {
	
}
