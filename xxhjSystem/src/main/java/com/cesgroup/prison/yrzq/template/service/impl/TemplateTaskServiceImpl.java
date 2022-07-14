package com.cesgroup.prison.yrzq.template.service.impl;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yrzq.template.dao.TemplateTaskDao;
import com.cesgroup.prison.yrzq.template.entity.TemplateTask;
import com.cesgroup.prison.yrzq.template.service.TemplateTaskService;

/**
 * Description: 执勤模板与执勤任务项关联关系业务访问接口实现类
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
@Service("templateTaskService")
public class TemplateTaskServiceImpl extends BaseDaoService<TemplateTask, String, TemplateTaskDao> implements TemplateTaskService {
	
}
