package com.cesgroup.prison.foreign.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.foreign.dto.ForeignCarDtlsDto;
import com.cesgroup.prison.foreign.entity.ForeignCarDtls;

public interface ForeignCarDtlsMapper extends BaseDao<ForeignCarDtls, String> {
	/**
     * 添加出入车辆信息
     * @param record
     */
    void insertCarInfo(ForeignCarDtls record);

    /**
     * 
     * @param id
     * @return
     */
    int deleteByFpdRegisterId(String id);
    
    /**
     * 分页查询车辆信息
     * @param map
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> listCar(Map<String, Object> map, Pageable pageable);
    
    void deleteByIds(List<String> ids);
    
    Map<String, Object> findCarByCarIdnty(Map<String, Object> param);
    
    Page<Map<String, Object>> getCarList(Map<String, Object> param, Pageable pageable);
    
    /**
     * 分页查询外来车辆信息
     * @param map
     * @param pageRequest
     * @return
     */
	Page<Map<String, Object>> findByPage(Map<String, Object> map, PageRequest pageRequest);

	/**
	 * 根据查询条件，分页查询外来车辆信息
	 * 
	 * @param paramMap
	 * @return
	 */
	public Page<ForeignCarDtlsDto> findWithPageByCondition(Map<String, Object> paramMap, Pageable pageable);

	
}