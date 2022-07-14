package com.cesgroup.prison.outsider.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.outsider.dao.OutsiderMapper;
import com.cesgroup.prison.outsider.entity.Outsider;
import com.cesgroup.prison.outsider.service.OutsiderService;

@Controller
@RequestMapping(value = "/outsider")
public class OutsiderController extends BaseEntityDaoServiceCRUDController<Outsider, String, OutsiderMapper, OutsiderService> {
	
}
