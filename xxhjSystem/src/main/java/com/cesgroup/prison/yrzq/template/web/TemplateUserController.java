package com.cesgroup.prison.yrzq.template.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.yrzq.template.dao.TemplateUserDao;
import com.cesgroup.prison.yrzq.template.entity.TemplateUser;
import com.cesgroup.prison.yrzq.template.service.TemplateUserService;

/**
 * Description: 执勤模板与执勤民警关联关系控制器类
 * @author lincoln.cheng
 *
 * 2019年1月14日
 */
@Controller
@RequestMapping("/templateUser")
public class TemplateUserController extends BaseEntityDaoServiceCRUDController<TemplateUser, String, TemplateUserDao, TemplateUserService> {
	
}
