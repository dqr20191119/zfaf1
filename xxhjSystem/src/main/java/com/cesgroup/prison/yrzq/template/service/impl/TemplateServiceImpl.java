package com.cesgroup.prison.yrzq.template.service.impl;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yrzq.template.dao.TemplateDao;
import com.cesgroup.prison.yrzq.template.entity.Template;
import com.cesgroup.prison.yrzq.template.service.TemplateService;

/**
 * Description: 执勤模板业务访问接口实现类
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
@Service("templateService")
public class TemplateServiceImpl extends BaseDaoService<Template, String, TemplateDao> implements TemplateService {
	
}
