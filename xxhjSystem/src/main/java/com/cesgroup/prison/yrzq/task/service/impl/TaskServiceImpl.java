package com.cesgroup.prison.yrzq.task.service.impl;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.yrzq.task.dao.TaskDao;
import com.cesgroup.prison.yrzq.task.entity.Task;
import com.cesgroup.prison.yrzq.task.service.TaskService;

/**
 * Description: 执勤任务项业务访问接口实现类
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
@Service("taskService")
public class TaskServiceImpl extends BaseDaoService<Task, String, TaskDao> implements TaskService {
	
}
