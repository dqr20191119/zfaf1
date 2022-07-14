package com.cesgroup.prison.yrzq.template.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.yrzq.template.dao.TemplateDao;
import com.cesgroup.prison.yrzq.template.entity.Template;
import com.cesgroup.prison.yrzq.template.service.TemplateService;

/**
 * Description: 执勤模板控制器类
 * @author lincoln.cheng
 *
 * 2019年1月14日
 */
@Controller
@RequestMapping("/template")
public class TemplateController extends BaseEntityDaoServiceCRUDController<Template, String, TemplateDao, TemplateService> {
	
}
