package com.cesgroup.prison.location.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.location.entity.PoliceLocation;

/**
 * 民警在监位置业务服务类
 * 
 * @author lincoln.cheng
 *
 */
@Service
public interface PoliceLocationService extends IBaseCRUDService<PoliceLocation, String> {
	/**
	 * 分页查询在监民警信息类表
	 * @param param
	 * @param pageable
	 * @return
	 */
    Page<Map<String, Object>> queryWithPage(Map<String, Object> param, Pageable pageable);
    
    /**
     * 分页查询各监区生物识别数据
     * @param param
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> querySwsbWithPage(Map<String, Object> param, Pageable pageable);
}
