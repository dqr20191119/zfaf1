package com.cesgroup.prison.emergency.preplanStart.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.preplanStart.dto.EmerPreplanStartDto;
import com.cesgroup.prison.emergency.preplanStart.entity.EmerPreplanStart;

import java.util.List;
import java.util.Map;

/**
 * 应急预案业务访问接口
 */
public interface EmerPreplanStartService extends IBaseCRUDService<EmerPreplanStart, String> {
    /**
     * 分页查询应急预案信息
     * @param paramMap
     * @return
     * @throws ServiceException
     */
    public List<EmerPreplanStartDto> queryByCondition(Map<String, Object> paramMap) throws ServiceException;
}
