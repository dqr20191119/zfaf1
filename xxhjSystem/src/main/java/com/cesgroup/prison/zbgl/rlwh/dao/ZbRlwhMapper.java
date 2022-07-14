package com.cesgroup.prison.zbgl.rlwh.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.rlwh.entity.ZbRlwhEntity;
import com.cesgroup.prison.zbgl.rygl.entity.RyglEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *@ClassName ZbRlwhMapper
 *@Description TODO
 *@Author lh
 *@Date 2020/8/18 14:50
 *
 **/
public interface ZbRlwhMapper extends BaseDao<ZbRlwhEntity, String> {
    Page<ZbRlwhEntity> findList(ZbRlwhEntity zbRlwhEntity, Pageable pageable);

    void deleteById(String id);

    void updateById(ZbRlwhEntity zbRlwhEntity);

    List<ZbRlwhEntity> selectByEntityOrderBydutyDate(ZbRlwhEntity zbRlwhEntity);

    List<ZbRlwhEntity> selectDateByYear(@Param("year") String year, @Param("flag") String flag);

    /**
     * 查询某日期之前的最大节假日或工作日 flag 为0 是工作日  为1是节假日
     * @param date
     * @param flag
     * @return
     */
    ZbRlwhEntity selectMaxDutyDateByDate(@Param("date")String date,@Param("flag") String flag );


    List<ZbRlwhEntity> findByDateBetween(String begin, String end);

}
