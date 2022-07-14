package com.cesgroup.prison.xqdjjl.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.exception.RESTHttpClientException;
import com.cesgroup.prison.httpclient.moodRecord.MoodRecordClient;
import com.cesgroup.prison.httpclient.moodRecord.dto.MoodRecordDto;
import com.cesgroup.prison.xqdjjl.service.XqdjjlServiceNew;
import com.github.pagehelper.PageInfo;

@Service("XqdjjlServiceNew")
@Transactional
public class XqdjjlServiceNewImpl implements XqdjjlServiceNew {

	@Override
	public PageInfo<MoodRecordDto> getIndex(PageRequest pageRequest,String sTime, String eTime, String statues, String zfName, String zfMood) {

		PageInfo<MoodRecordDto> MRInfo = new PageInfo<MoodRecordDto>();
		try {
			MRInfo = MoodRecordClient.entityQjjcjl("", "", pageRequest,sTime,eTime,statues,zfName,zfMood);

		} catch (RESTHttpClientException e) {
			e.printStackTrace();
		}
		return MRInfo;
	}

}
