package com.cesgroup.prison.patrolrecord.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.patrolrecord.dao.PatrolrecordMapper;
import com.cesgroup.prison.patrolrecord.entity.PatrolrecordEntity;
import com.cesgroup.prison.patrolrecord.service.PatrolrecordService;

@Controller
@RequestMapping(value = "/patrolrecord")
public class PatrolrecordController  extends BaseEntityDaoServiceCRUDController<PatrolrecordEntity, String, PatrolrecordMapper, PatrolrecordService> {
	
	private final Logger logger = LoggerFactory.getLogger(PatrolrecordController.class);
	
}
