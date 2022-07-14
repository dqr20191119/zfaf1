package com.cesgroup.prison.aqfh.wlryclfh.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.aqfh.wlryclfh.entity.WlclEntity;
import com.cesgroup.prison.aqfh.wlryclfh.entity.WlryEntity;
import com.cesgroup.prison.aqfh.wlryclfh.entity.WlryclfhEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * @ClassName WlryclfhMapper
 * @Description TODO
 * @Author lh
 * @Date 2020/6/10 14:03
 **/
public interface WlryclfhMapper extends BaseDao<WlryclfhEntity, String> {
    Page<WlryclfhEntity> findList(WlryclfhEntity wlryclfhEntity, PageRequest pageRequest);

    void updateById(WlryclfhEntity wlryclfhEntity);


    List<WlryclfhEntity> getTodayWlryclData(WlryclfhEntity wlryclfhEntity);

    List<WlryclfhEntity> selectBywlryclfh(WlryclfhEntity wlryclfhEntity);

    Page<WlryEntity>  selectWlryByYwId(WlryEntity wlryEntity,PageRequest pageRequest);

    Page<WlclEntity>  selectWlclByYwId(WlclEntity wlclEntity,PageRequest pageRequest);
}
