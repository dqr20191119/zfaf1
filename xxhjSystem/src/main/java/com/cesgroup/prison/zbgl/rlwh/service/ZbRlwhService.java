package com.cesgroup.prison.zbgl.rlwh.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.zbgl.rlwh.entity.ZbRlwhEntity;
import com.cesgroup.prison.zbgl.rygl.entity.RyglEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 *@ClassName ZbRlwhService
 *@Description TODO
 *@Author lh
 *@Date 2020/8/18 14:51
 *
 **/
public interface ZbRlwhService  extends IBaseCRUDService<ZbRlwhEntity, String> {
    Page<ZbRlwhEntity> findList(ZbRlwhEntity zbRlwhEntity, PageRequest pageRequest);

    List<ZbRlwhEntity> getInitRlwhData(String startDate, String endDate);

    void deleteByIds(String ids);

    void updateById(ZbRlwhEntity zbRlwhEntity);

    void saveDate(String rlwhDatas);

    AjaxResult checkIsBetweenStartDateAndEndDate(String startDate, String endDate);
}
