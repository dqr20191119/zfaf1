package com.cesgroup.prison.yrzq.template.service.impl;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yrzq.template.dao.TemplateUserDao;
import com.cesgroup.prison.yrzq.template.entity.TemplateUser;
import com.cesgroup.prison.yrzq.template.service.TemplateUserService;

/**
 * Description: 执勤模板与执勤民警关联关系业务访问接口实现类
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
@Service("templateUserService")
public class TemplateUserServiceImpl extends BaseDaoService<TemplateUser, String, TemplateUserDao> implements TemplateUserService {
	
}
