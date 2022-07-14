package com.cesgroup.prison.xxhj.zfsjsb.sjsb.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.zfsjsb.sjsb.entity.SjsbEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface SjsbService extends IBaseCRUDService<SjsbEntity, String> {

    /**
     * 分页查询所有
     * @param entity
     * @param pageable
     * @return
     */
     Page<Map<String, Object>> listAll(SjsbEntity entity, Pageable pageable);

     Page<Map<String, Object>> findByTimeAndDprtmntId(Map<String, Object> paramMap) ;

     void deleteInfo(List<SjsbEntity> list);

     void addInfo(Map<String, Object> paramMap, HttpServletRequest request) throws Exception ;

     Page<Map<String, Object>> getCntByTime(Map<String, Object> paramMap) ;

     Map<String, Object> getReportDprt(Map<String, Object> paramMap,  HttpServletRequest request) ;

}
