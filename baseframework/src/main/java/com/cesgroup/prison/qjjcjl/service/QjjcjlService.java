package com.cesgroup.prison.qjjcjl.service;

import org.springframework.data.domain.PageRequest;

import com.cesgroup.prison.httpclient.clearCheck.dto.ClearDto;
import com.github.pagehelper.PageInfo;

public interface QjjcjlService {

	public PageInfo<ClearDto> getIndex(PageRequest pageRequest, String sTime, String eTime, String statues, String qjName);

}
