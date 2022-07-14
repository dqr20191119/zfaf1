package com.cesgroup.prison.zfxx.zftaf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zftaf.dao.ZfTafDao;
import com.cesgroup.prison.zfxx.zftaf.entity.ZfTaf;
import com.cesgroup.prison.zfxx.zftaf.service.ZfTafService;

/**
 * Description: 罪犯同案犯控制器类
 * @author lincoln.cheng
 *
 * 2019年1月14日
 */
@Controller
@RequestMapping("/zfxx/zfTaf")
public class ZfTafController extends BaseEntityDaoServiceCRUDController<ZfTaf, String, ZfTafDao, ZfTafService> {
	
}
