package com.cesgroup.prison.zfxx.zflj.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zflj.dao.ZfLjDao;
import com.cesgroup.prison.zfxx.zflj.entity.ZfLj;
import com.cesgroup.prison.zfxx.zflj.service.ZfLjService;

/**
 * Description: 离监控制器类
 * @author lincoln.cheng
 *
 * 2019年1月14日
 */
@Controller
@RequestMapping("/zfxx/zfLj")
public class ZfLjController extends BaseEntityDaoServiceCRUDController<ZfLj, String, ZfLjDao, ZfLjService> {
	
}
