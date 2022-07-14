package com.cesgroup.prison.emergency.preplanStart.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.emergency.preplanStart.dto.EmerPreplanStartDto;
import com.cesgroup.prison.emergency.preplanStart.entity.EmerPreplanStart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 应急预案数据访问对象
 */
public interface EmerPreplanStartDao extends BaseDao<EmerPreplanStart, String> {

    /**
     * 分页查询应急预案数据
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> findWithPage(Map<String, Object> paramMap, Pageable pageable);

    /**
     * 根据条件，查询报警记录可用的报警预案列表
     * @param paramMap
     * @return
     */
    List<EmerPreplanStartDto> findEmerPreplanExistsCdsAlertRecordDtlsByCondition(Map<String, Object> paramMap);
}