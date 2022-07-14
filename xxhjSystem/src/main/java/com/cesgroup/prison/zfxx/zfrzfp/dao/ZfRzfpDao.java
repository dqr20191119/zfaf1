package com.cesgroup.prison.zfxx.zfrzfp.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfrzfp.entity.ZfRzfp;

/**
 * Description: 罪犯认罪服判 类别 dao层类
 * @author monkey
 *
 * 2019年3月3日
 */
public interface ZfRzfpDao  extends BaseDao<ZfRzfp,String> {

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
    int insertBatch(List<ZfRzfp> list);
    
}
