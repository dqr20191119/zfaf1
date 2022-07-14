package com.cesgroup.prison.xqdjjl.service;

import org.springframework.data.domain.PageRequest;

import com.cesgroup.prison.httpclient.moodRecord.dto.MoodRecordDto;
import com.github.pagehelper.PageInfo;

public interface XqdjjlServiceNew {

	public PageInfo<MoodRecordDto> getIndex(PageRequest pageRequest, String sTime, String eTime, String statues, String zfName, String zfMood);

}
