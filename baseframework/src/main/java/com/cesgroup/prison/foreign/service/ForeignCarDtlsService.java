package com.cesgroup.prison.foreign.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.foreign.dto.ForeignCarDtlsDto;
import com.cesgroup.prison.foreign.entity.ForeignCarDtls;
import com.cesgroup.prison.foreign.entity.ForeignRegister;

@Service
public interface ForeignCarDtlsService extends IBaseCRUDService<ForeignCarDtls, String> {
	/**
	 * 分页查询外来车辆进出信息
	 * @param paramMap
	 * @param pageRequest
	 * @return
	 * @throws Exception 
	 */
	Page<ForeignCarDtlsDto> queryWithPageByCondition(Map<String, Object> paramMap, PageRequest pageRequest) throws Exception;
	
    /**
     * 保存外来车辆及其登记信息
     * 
     * @param foreignCarDtls
     * @param foreignRegister
     * @return
     * @throws Exception
     */
    ForeignCarDtls saveForeignCarDtls(ForeignCarDtls foreignCarDtls, ForeignRegister foreignRegister) throws Exception;
    
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteByIds(List<String> ids);
    
	/**
	 * 根据ID查询
	 * @param map
	 * @param pageRequest
	 * @return
	 */
	Map<String, Object> findById(String id);
	
	/**
	 * 根据车牌号查询
	 * @param map
	 * @param pageRequest
	 * @return
	 * @throws Exception 
	 */
	Map<String, Object> findByCarLcnsIdnty(String carLcnsIdnty) throws Exception;
}
