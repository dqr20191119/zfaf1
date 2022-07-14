package com.cesgroup.prison.yrzq.task.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.yrzq.task.dao.TaskDao;
import com.cesgroup.prison.yrzq.task.entity.Task;
import com.cesgroup.prison.yrzq.task.service.TaskService;

/**
 * Description: 执勤任务项控制器类
 * @author lincoln.cheng
 *
 * 2019年1月14日
 */
@Controller
@RequestMapping("/task")
public class TaskController extends BaseEntityDaoServiceCRUDController<Task, String, TaskDao, TaskService> {
	
}
