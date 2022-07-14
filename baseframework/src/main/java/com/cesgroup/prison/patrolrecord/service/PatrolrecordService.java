package com.cesgroup.prison.patrolrecord.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.patrolrecord.entity.PatrolrecordEntity;

public interface PatrolrecordService  extends IBaseCRUDService<PatrolrecordEntity, String>, IMessageProcess {

}
