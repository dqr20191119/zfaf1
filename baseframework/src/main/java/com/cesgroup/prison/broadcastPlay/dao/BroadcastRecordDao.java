package com.cesgroup.prison.broadcastPlay.dao;
import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.broadcastPlay.entity.BroadcastRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface BroadcastRecordDao extends BaseDao<BroadcastRecord, String> {
    /**
     * 根据主键ID查询广播播放记录
     * @param id
     * @return
     */
    BroadcastRecord findById(@Param("id") String id);
    
    /**
     * 分页查询
     * @param map
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> findWithPageByCondition(Map<String, Object> map, Pageable pageable);
}