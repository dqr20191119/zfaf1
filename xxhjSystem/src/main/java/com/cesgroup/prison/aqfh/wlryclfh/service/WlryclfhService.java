package com.cesgroup.prison.aqfh.wlryclfh.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.aqfh.wlryclfh.entity.WlclEntity;
import com.cesgroup.prison.aqfh.wlryclfh.entity.WlryEntity;
import com.cesgroup.prison.aqfh.wlryclfh.entity.WlryclfhEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @ClassName WlryclfhService
 * @Description TODO
 * @Author lh
 * @Date 2020/6/10 14:03
 **/
public interface WlryclfhService extends IBaseCRUDService<WlryclfhEntity, String> {
    Page<WlryclfhEntity> findList(WlryclfhEntity wlryclfhEntity, PageRequest pageRequest);

    void updateById(WlryclfhEntity wlryclfhEntity);

    AjaxResult inItDutyData();

    Page<WlryEntity>  selectWlryByYwId(WlryEntity wlryEntity, PageRequest pageRequest);

    Page<WlclEntity>  selectWlclByYwId(WlclEntity wlclEntity, PageRequest pageRequest);

}
