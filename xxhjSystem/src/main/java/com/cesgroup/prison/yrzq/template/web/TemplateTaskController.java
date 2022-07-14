package com.cesgroup.prison.yrzq.template.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.yrzq.template.dao.TemplateTaskDao;
import com.cesgroup.prison.yrzq.template.entity.TemplateTask;
import com.cesgroup.prison.yrzq.template.service.TemplateTaskService;

/**
 * Description: 执勤模板与执勤任务项关联关系控制器类
 * @author lincoln.cheng
 *
 * 2019年1月14日
 */
@Controller
@RequestMapping("/templateTask")
public class TemplateTaskController extends BaseEntityDaoServiceCRUDController<TemplateTask, String, TemplateTaskDao, TemplateTaskService> {
	
}
