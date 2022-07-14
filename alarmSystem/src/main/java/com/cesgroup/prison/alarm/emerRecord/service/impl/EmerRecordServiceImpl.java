package com.cesgroup.prison.alarm.emerRecord.service.impl;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.alarm.emerRecord.dao.EmerRecordDao;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecord;
import com.cesgroup.prison.alarm.emerRecord.service.EmerRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: 01 超图版
 * @description:
 * @author: Mr.li
 * @create: 2019-11-27 14:57
 */
@Service
@Transactional
public class EmerRecordServiceImpl extends BaseDaoService<EmerRecord, String, EmerRecordDao> implements EmerRecordService {
}