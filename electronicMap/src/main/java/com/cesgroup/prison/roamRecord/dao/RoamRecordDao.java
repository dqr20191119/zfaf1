package com.cesgroup.prison.roamRecord.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.roamRecord.entity.RoamRecord;

/**
 * 三维巡视记录数据操作类
 * 
 * @author lincoln.cheng
 *
 */
public interface RoamRecordDao extends BaseDao<RoamRecord, String> {

    /**
     * 分页查询三维巡视
     * @param map
     * @param pageable
     * @return
     */
	Page<Map<String, Object>> findWithPage(Map<String, Object> map, Pageable pageable);

}
