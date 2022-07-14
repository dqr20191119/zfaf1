package com.cesgroup.prison.zbgl.rlwh.service.impl;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.zbgl.rlwh.dao.ZbRlwhMapper;
import com.cesgroup.prison.zbgl.rlwh.entity.ZbRlwhEntity;
import com.cesgroup.prison.zbgl.rlwh.service.ZbRlwhService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *@ClassName ZbRlwhServiceImpl
 *@Description TODO
 *@Author lh
 *@Date 2020/8/18 14:51
 *
 **/
@Service
public class ZbRlwhServiceImpl extends BaseDaoService<ZbRlwhEntity, String, ZbRlwhMapper> implements ZbRlwhService {
    @Override
    public Page<ZbRlwhEntity> findList(ZbRlwhEntity zbRlwhEntity, PageRequest pageRequest) {
        return this.getDao().findList(zbRlwhEntity,pageRequest);
    }

    @Override
    public List<ZbRlwhEntity> getInitRlwhData(String startDate, String endDate) {
        //标识日期是否是双休
        List<ZbRlwhEntity> list = new ArrayList<>();
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            String dateDiff = CommonUtil.getDateDiff(sf.parse(startDate), sf.parse(endDate));
            for (int i = 0; i < Integer.valueOf(dateDiff) + 1; i++) {
                ZbRlwhEntity zbRlwhEntity = new ZbRlwhEntity();
                zbRlwhEntity.setDutyDate(sf.format(CommonUtil.addDate(sf.parse(startDate), i)));
                String weekDay = Util.getWeekDay(sf.format(CommonUtil.addDate(sf.parse(startDate), i)));
                if("星期日".equals(weekDay) || "星期六".equals(weekDay) ){
                    zbRlwhEntity.setFlag("1");
                }else{
                    zbRlwhEntity.setFlag("0");
                }
                zbRlwhEntity.setWeekDay(weekDay);
                list.add(zbRlwhEntity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    @Transactional
    public void deleteByIds(String ids) {
        String[] idAds = ids.split(",");
        for(String id:idAds){
            this.getDao().deleteById(id);
        }

    }

    @Override
    @Transactional
    public void updateById(ZbRlwhEntity zbRlwhEntity) {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        zbRlwhEntity.setCreateId(user.getUserId());
        zbRlwhEntity.setCreateName(user.getUserName());
        zbRlwhEntity.setCreateTime(DateUtils.getCurrentDate());
        this.getDao().updateById(zbRlwhEntity);
    }

    @Override
    @Transactional
    public void saveDate(String rlwhDatas) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ZbRlwhEntity> zbRlwhEntities = new  ArrayList<ZbRlwhEntity>();
        List<ZbRlwhEntity> list = new ArrayList<ZbRlwhEntity>();
        try {
            zbRlwhEntities =  objectMapper.readValue(rlwhDatas, new TypeReference<List<ZbRlwhEntity>>(){});
            UserBean user = AuthSystemFacade.getLoginUserInfo();
            for(ZbRlwhEntity zbRlwhEntity:zbRlwhEntities){
                zbRlwhEntity.setCreateTime(DateUtils.getCurrentDate());
                zbRlwhEntity.setCreateName(user.getUserName());
                zbRlwhEntity.setCreateId(user.getUserId());
                list.add(zbRlwhEntity);
            }
            this.getDao().insert(list);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public AjaxResult checkIsBetweenStartDateAndEndDate(String startDate, String endDate) {
        String result = "false";
        ZbRlwhEntity zbRlwhEntity = new ZbRlwhEntity();
        zbRlwhEntity.setStartDate(startDate);
        zbRlwhEntity.setEndDate(endDate);
        List<ZbRlwhEntity> zbRlwhEntities = this.getDao().selectByEntityOrderBydutyDate(zbRlwhEntity);
        if(zbRlwhEntities.size() >0){
            result = "true";
        }
        return AjaxResult.success(result);
    }

}
