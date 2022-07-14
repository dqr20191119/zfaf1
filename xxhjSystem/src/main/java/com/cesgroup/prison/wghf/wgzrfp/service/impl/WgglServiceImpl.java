package com.cesgroup.prison.wghf.wgzrfp.service.impl;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;

import com.cesgroup.prison.wghf.wgzrfp.dao.WgglDao;

import com.cesgroup.prison.wghf.wgzrfp.entiy.Wggl;

import com.cesgroup.prison.wghf.wgzrfp.service.WgglService;
@Service("wgglService")
public class WgglServiceImpl extends BaseDaoService<Wggl, String, WgglDao> implements WgglService{

}
