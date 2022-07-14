package com.cesgroup.prison.alarm.plan.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.exception.PersistenceException;
import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.plan.entity.AlarmEmerPlanRltn;

/**
 * 报警预案与应急预案关联关系表DAO
 *
 * AlarmEmerPlanRltnMapper
 *
 * @author cheng.jie
 * @created Create Time: Wed May 27 16:34:26 CST 2020
 */
public interface AlarmEmerPlanRltnMapper extends BaseDao<AlarmEmerPlanRltn, String> {
    /**
     * 查询最大排序号
     *
     * @param cusNumber 单位编号
     * @param alarmPlanId  报警预案编号
     * @return
     * @throws PersistenceException
     */
    Integer findMaxShowOrderByCusNumberAndAlarmPlanId(@Param("cusNumber") String cusNumber, @Param("alarmPlanId") String alarmPlanId) throws PersistenceException;


    /**
     * 根据报警预案ID，查询报警预案与应急预案关联关系数据
     * @param alarmPlanId
     * @return
     * @throws PersistenceException
     */
    List<AlarmEmerPlanRltn> findByAlarmPlanId(@Param("alarmPlanId") String alarmPlanId) throws PersistenceException;

    List<Map<String,Object>> getemerPlanNameByAlarmPlanId(@Param("alarmPlanId") String alarmPlanId);
}