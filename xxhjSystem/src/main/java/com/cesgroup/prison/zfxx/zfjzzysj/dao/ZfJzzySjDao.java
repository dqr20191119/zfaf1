package com.cesgroup.prison.zfxx.zfjzzysj.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfjzzysj.entity.ZfJzzySj;

/**
 * Description: 罪犯收监情况-狱外就诊/住院 dao层类
 * @author monkey
 *
 * 2019年3月5日
 */
public interface ZfJzzySjDao extends BaseDao<ZfJzzySj, String> {

    /**
     * 备份所有数据
     * @return
     */
    int insertHisBySource();
    
    /**
     * 删除所有数据
     * @return
     */
    int deleteAll();
    
    /**
     * 批量新增
     * @param list
     * @return
     */
    int insertBatch(List<ZfJzzySj> list);
    
}
