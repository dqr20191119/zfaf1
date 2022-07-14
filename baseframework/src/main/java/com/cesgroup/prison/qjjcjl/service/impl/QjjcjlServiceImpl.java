package com.cesgroup.prison.qjjcjl.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.exception.RESTHttpClientException;
import com.cesgroup.prison.httpclient.clearCheck.ClearCheckHttpClient;
import com.cesgroup.prison.httpclient.clearCheck.dto.ClearDto;
import com.cesgroup.prison.qjjcjl.service.QjjcjlService;
import com.github.pagehelper.PageInfo;

@Service("QjjcjlService")
@Transactional
public class QjjcjlServiceImpl implements QjjcjlService {

	@Override
	public PageInfo<ClearDto> getIndex(PageRequest pageRequest,String sTime, String eTime, String statues, String qjName) {

		PageInfo<ClearDto> clearPageInfo = new PageInfo<ClearDto>();
		try {
			clearPageInfo = ClearCheckHttpClient.entityQjjcjl("", "", pageRequest,sTime,eTime,statues,qjName);

		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		return clearPageInfo;
	}

}
