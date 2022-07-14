package com.cesgroup.prison.aqfh.wlryclfh.service.impl;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.prison.aqfh.wlryclfh.dao.WlryclfhMapper;
import com.cesgroup.prison.aqfh.wlryclfh.entity.WlclEntity;
import com.cesgroup.prison.aqfh.wlryclfh.entity.WlryEntity;
import com.cesgroup.prison.aqfh.wlryclfh.entity.WlryclfhEntity;
import com.cesgroup.prison.aqfh.wlryclfh.service.WlryclfhService;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName WlryclfhServiceImpl
 * @Description TODO
 * @Author lh
 * @Date 2020/6/10 14:03
 **/
@Service
public class WlryclfhServiceImpl extends BaseDaoService<WlryclfhEntity, String, WlryclfhMapper> implements WlryclfhService {
    @Override
    public Page<WlryclfhEntity> findList(WlryclfhEntity wlryclfhEntity, PageRequest pageRequest) {
        return this.getDao().findList(wlryclfhEntity,pageRequest);
    }

    @Override
    @Transactional
    public void updateById(WlryclfhEntity wlryclfhEntity) {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        wlryclfhEntity.setFhmjId(user.getUserId());
        wlryclfhEntity.setFhsj(DateUtils.getCurrentDate(true));
        wlryclfhEntity.setFhmjName(user.getUserName());
        this.getDao().updateById(wlryclfhEntity);
    }

    @Override
    @Transactional
    public AjaxResult inItDutyData() {
        //先查询当天的外来车辆和人员情况
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        WlryclfhEntity wlryclfhEntity = new WlryclfhEntity();
        wlryclfhEntity.setJjsj(new Date());
        wlryclfhEntity.setCusNumber(user.getCusNumber());
        List<WlryclfhEntity> todayWlryclDatas = this.getDao().getTodayWlryclData(wlryclfhEntity);
        if(todayWlryclDatas.size()>0){
            for(WlryclfhEntity todayWlryclData:todayWlryclDatas){
                //用id去复核表中查询看有不有数据
                WlryclfhEntity entity = this.getDao().selectOne(todayWlryclData.getId());
                if(entity != null){
                    //有数据就不插入
                }else{
                    //反之插入数据
                    todayWlryclData.setZt("0");
                    this.getDao().insert(todayWlryclData);
                }
            }
        }
        return AjaxResult.success();
    }

    @Override
    public Page<WlryEntity> selectWlryByYwId(WlryEntity wlryEntity, PageRequest pageRequest) {
        return this.getDao().selectWlryByYwId(wlryEntity,pageRequest);
    }

    @Override
    public Page<WlclEntity> selectWlclByYwId(WlclEntity wlclEntity, PageRequest pageRequest) {
        return this.getDao().selectWlclByYwId(wlclEntity,pageRequest);
    }
}
