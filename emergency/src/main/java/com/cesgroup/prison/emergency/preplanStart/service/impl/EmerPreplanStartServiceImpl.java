package com.cesgroup.prison.emergency.preplanStart.service.impl;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.preplanStart.dao.EmerPreplanStartDao;
import com.cesgroup.prison.emergency.preplanStart.dto.EmerPreplanStartDto;
import com.cesgroup.prison.emergency.preplanStart.entity.EmerPreplanStart;
import com.cesgroup.prison.emergency.preplanStart.service.EmerPreplanStartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 应急预案业务操作类
 */
@Service
@Transactional
public class EmerPreplanStartServiceImpl extends BaseDaoService<EmerPreplanStart, String, EmerPreplanStartDao> implements EmerPreplanStartService {

    @Override
    public List<EmerPreplanStartDto> queryByCondition(Map<String, Object> paramMap) throws ServiceException {
        try {
            // 按条件查询应急预案列表
            List<EmerPreplanStartDto> preplanStartDtoList = this.getDao().findEmerPreplanExistsCdsAlertRecordDtlsByCondition(paramMap);

            return preplanStartDtoList;
        } catch (Exception e) {
            throw new ServiceException("查询应急预案信息发生异常，原因：" + e);
        }
    }

}
