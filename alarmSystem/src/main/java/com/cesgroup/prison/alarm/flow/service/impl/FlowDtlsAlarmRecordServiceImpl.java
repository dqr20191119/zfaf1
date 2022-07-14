package com.cesgroup.prison.alarm.flow.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.util.StringUtil;
import com.cesgroup.prison.alarm.flow.dao.FlowDtlsAlarmRecordMapper;
import com.cesgroup.prison.alarm.flow.dao.FlowDtlsMapper;
import com.cesgroup.prison.alarm.flow.entity.FlowDtlsAlarmRecordEntity;
import com.cesgroup.prison.alarm.flow.service.FlowDtlsAlarmRecordService;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FlowDtlsAlarmRecordServiceImpl
 * @Description TODO
 * @Author lh
 * @Date 2020/6/17 17:01
 **/
@Service
public class FlowDtlsAlarmRecordServiceImpl extends BaseDaoService<FlowDtlsAlarmRecordEntity, String, FlowDtlsAlarmRecordMapper> implements FlowDtlsAlarmRecordService {

    @Resource
    private FlowDtlsMapper flowDtlsMapper ;

    @Override
    @Transactional
    public AjaxResult saveOrUpdate(FlowDtlsAlarmRecordEntity flowDtlsAlarmRecordEntity) {
        UserBean userBean = AuthSystemFacade.getLoginUserInfo();
        flowDtlsAlarmRecordEntity.setHfdaUpdateId(userBean.getUserId());
        flowDtlsAlarmRecordEntity.setHfdaUpdateName(userBean.getUserName());
        flowDtlsAlarmRecordEntity.setHfdaUpdateTime(DateUtils.getCurrentDate());
        if(StringUtil.isNull(flowDtlsAlarmRecordEntity.getId())){
            //插入
            flowDtlsAlarmRecordEntity.setId(IdUtil.simpleUUID());
            this.getDao().insert(flowDtlsAlarmRecordEntity);
        }else{
            //修改
            this.getDao().updateById(flowDtlsAlarmRecordEntity);
        }
        return AjaxResult.success("插入数据成功");
    }

    @Override
    public FlowDtlsAlarmRecordEntity ListBylowDtlsAlarmRecord(FlowDtlsAlarmRecordEntity flowDtlsAlarmRecordEntity) {
        List<FlowDtlsAlarmRecordEntity> flowDtlsAlarmRecordEntities =  this.getDao().selectByEntity(flowDtlsAlarmRecordEntity);
        if(flowDtlsAlarmRecordEntities.size() > 0 ){
            return flowDtlsAlarmRecordEntities.get(0);
        }
        return null;
    }

    @Override
    public AjaxResult ListByAlarmRecordId(String recordId) {
        FlowDtlsAlarmRecordEntity flowDtlsAlarmRecordEntity = new FlowDtlsAlarmRecordEntity();
        flowDtlsAlarmRecordEntity.setHfdaAlertRecordDtlsId(recordId);
        List<FlowDtlsAlarmRecordEntity> flowDtlsAlarmRecordEntities = this.getDao().selectByEntity(flowDtlsAlarmRecordEntity);
        if(flowDtlsAlarmRecordEntities.size() > 0 ){
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("flowId",flowDtlsAlarmRecordEntities.get(0).getHfdaFlowId());
            List<Map<String, Object>> flowDtls = flowDtlsMapper.findFlowDtls(paramMap);
            return AjaxResult.success(flowDtls);
        }
        return AjaxResult.error();
    }
}
