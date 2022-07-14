package com.cesgroup.prison.outsider.service.impl;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.outsider.dao.OutsiderMapper;
import com.cesgroup.prison.outsider.entity.Outsider;
import com.cesgroup.prison.outsider.service.OutsiderService;

@Service("outsiderService")
public class OutsiderServiceImpl extends BaseDaoService<Outsider, String, OutsiderMapper> implements OutsiderService {

}
