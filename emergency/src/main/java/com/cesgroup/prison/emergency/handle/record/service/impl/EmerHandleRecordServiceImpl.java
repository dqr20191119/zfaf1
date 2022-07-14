package com.cesgroup.prison.emergency.handle.record.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ces.sdk.system.bean.OrgInfo;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecord;
import com.cesgroup.prison.alarm.emerRecord.service.EmerRecordService;
import com.cesgroup.prison.alarm.plan.dao.AlarmPlanMasterMapper;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanMasterEntity;
import com.cesgroup.prison.alarm.record.dao.AlarmMapper;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.alarm.record.service.AlarmService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.dao.AffixMapper;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.group.entity.EmerGroup;
import com.cesgroup.prison.emergency.handle.record.dao.EmerHandleRecordDao;
import com.cesgroup.prison.emergency.handle.record.entity.EmerHandleRecord;
import com.cesgroup.prison.emergency.handle.record.service.EmerHandleRecordService;
import com.cesgroup.prison.emergency.handle.recordGroup.dao.EmerHandleRecordGroupDao;
import com.cesgroup.prison.emergency.handle.recordGroup.entity.EmerHandleRecordGroup;
import com.cesgroup.prison.emergency.handle.recordMember.dao.EmerHandleRecordMemberDao;
import com.cesgroup.prison.emergency.handle.recordMember.entity.EmerHandleRecordMember;
import com.cesgroup.prison.emergency.handle.recordStep.dao.EmerHandleRecordStepDao;
import com.cesgroup.prison.emergency.handle.recordStep.entity.EmerHandleRecordStep;
import com.cesgroup.prison.emergency.handle.recordStep.service.EmerHandleRecordStepService;
import com.cesgroup.prison.emergency.member.entity.EmerMember;
import com.cesgroup.prison.emergency.preplan.entity.EmerPreplan;
import com.cesgroup.prison.emergency.preplan.service.EmerPreplanService;
import com.cesgroup.prison.emergency.step.entity.EmerStep;
import com.cesgroup.prison.jfsb.dao.AlertorMapper;
import com.cesgroup.prison.jfsb.entity.AlertorEntity;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.yjct.dao.GzzcyMapper;

/**
 * 应急预案业务操作类
 */
@Service("EmerHandleRecordService")
@Transactional
public class EmerHandleRecordServiceImpl extends BaseDaoService<EmerHandleRecord, String, EmerHandleRecordDao> implements EmerHandleRecordService {
    /**
     * 附件DAO
     */
    @Autowired
    private AffixMapper affixMapper;
    /**
     * 报警器DAO
     */
    @Autowired
    private AlertorMapper alertorMapper;
    /**
     * 报警记录DAO
     */
    @Autowired
    private AlarmMapper alarmMapper;
    /**
     * 报警预案DAO
     */
    @Autowired
    private AlarmPlanMasterMapper alarmPlanMasterMapper;
    /**
     * 应急处置记录的处置步骤DAO
     */
    @Autowired
    private EmerHandleRecordStepDao emerHandleRecordStepDao;
    /**
     * 应急处置记录的工作组DAO
     */
    @Autowired
    private EmerHandleRecordGroupDao emerHandleRecordGroupDao;
    /**
     * 应急处置记录的工作组成员DAO
     */
    @Autowired
    private EmerHandleRecordMemberDao emerHandleRecordMemberDao;
    /**
     * 应急预案Service
     */
    @Autowired
    private EmerPreplanService emerPreplanService;
    /**
     * 应急处置记录的处置步骤Service
     */
    @Autowired
    private EmerHandleRecordStepService emerHandleRecordStepService;
    @Autowired
    private EmerHandleRecordDao emerHandleRecordDao;
    @Resource
    private AlarmService alarmService;
    @Resource
    private EmerRecordService emerRecordService;

    @Resource
    private GzzcyMapper gzzcyMapper;

    @Override
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException {
        try {
            Page<Map<String, Object>> page = this.getDao().findWithPage(paramMap, pageable);
            return page;
        } catch (Exception e) {
            throw new ServiceException("分页查询应急处置记录发生异常，原因：" + e);
        }
    }

    @Override
    public EmerHandleRecord saveOrUpdate(EmerHandleRecord emerHandleRecord) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if(user == null) {
            throw new ServiceException("用户未登录");
        }
        if(emerHandleRecord.getAlarmPlanId() == null || "".equals(emerHandleRecord.getAlarmPlanId())) {
            throw new ServiceException("报警预案ID为空，启动应急预案失败！");
        }
        if(emerHandleRecord.getAlarmRecordId() == null || "".equals(emerHandleRecord.getAlarmRecordId())) {
            throw new ServiceException("报警记录ID为空，启动应急预案失败！");
        }
        if(emerHandleRecord.getPreplanId() == null || "".equals(emerHandleRecord.getPreplanId())) {
            throw new ServiceException("应急预案ID为空，启动应急预案失败！");
        }

        //1 根据报警预案编号，查询报警预案信息
        AlarmPlanMasterEntity alarmPlan = null;
        try {
            alarmPlan = this.alarmPlanMasterMapper.findById(emerHandleRecord.getAlarmPlanId());
        } catch (Exception e) {
            throw new ServiceException("根据报警预案ID，查询报警预案信息发生异常");
        }
        if(alarmPlan == null) {
            throw new ServiceException("根据报警预案ID[" + emerHandleRecord.getAlarmPlanId() + "]查询到的报警预案为空，启动应急预案失败！");
        }
        emerHandleRecord.setAlarmPlanId(alarmPlan.getId());
        emerHandleRecord.setAlarmPlanName(alarmPlan.getPmaPlanName());

        //2 根据报警记录编号，查询报警记录信息、报警设备信息
        //2.1 报警记录
        AlarmRecordEntity alarmRecord = null;
        try {
            alarmRecord = this.alarmMapper.findById(emerHandleRecord.getAlarmRecordId());
        } catch (Exception e) {
            throw new ServiceException("根据报警记录ID，查询报警记录信息发生异常");
        }
        if(alarmRecord == null) {
            throw new ServiceException("根据报警记录ID[" + emerHandleRecord.getAlarmRecordId() + "]查询到的报警记录为空，启动应急预案失败！");
        }
        emerHandleRecord.setAlarmRecordId(alarmRecord.getId());

        if(alarmRecord.getArdReceiveAlarmPolice()==""||alarmRecord.getArdReceiveAlarmPolice()==null){
            try {
                Date date = new Date();
                alarmRecord.setArdReceiveTime(date);// 接警时间
                alarmRecord.setArdReceiveAlarmPoliceId(user.getUserId()); // 接警人
                alarmRecord.setArdReceiveAlarmPolice(user.getRealName());
                this.alarmMapper.update(alarmRecord);
            } catch (Exception e) {
                throw new ServiceException("更新报警记录发生异常");
            }
        }

        //2.2 报警设备
        AlertorEntity alertor = null;
        try {
            alertor = this.alertorMapper.findById(alarmRecord.getArdAlertorIdnty());
        } catch (Exception e) {
            throw new ServiceException("根据报警设备ID，查询报警设备信息发生异常");
        }
        if(alertor == null) {
            throw new ServiceException("根据报警设备ID[" + alarmRecord.getArdAlertorIdnty() + "]查询到的报警设备为空，启动应急预案失败！");
        }
        emerHandleRecord.setAlarmAreaId(alertor.getAbdAreaId());
        emerHandleRecord.setAlarmAreaName(alertor.getAbdArea());

        //3 根据应急预案编号，查询预案详细信息(包括预案处置步骤、工作组、工作组成员信息)
        EmerPreplan preplan = null;
        try {
            preplan = this.emerPreplanService.queryDetailById(emerHandleRecord.getPreplanId());
        } catch (Exception e) {
            throw new ServiceException("根据应急预案ID，查询应急预案信息发生异常");
        }
        if(preplan == null) {
            throw new ServiceException("根据应急预案ID[" + emerHandleRecord.getPreplanId() + "]查询到的应急预案详细信息为空，启动应急预案失败！");
        }
        emerHandleRecord.setPreplanName(preplan.getName());
        emerHandleRecord.setPreplanSource(preplan.getSource());
        emerHandleRecord.setPreplanRemarks(preplan.getRemarks());
        emerHandleRecord.setCusNumber(preplan.getCusNumber());

        // 声明指令操作实类体
        EmerHandleRecord emerHandleRecordOperate = null;
        if(emerHandleRecord.getId() != null && !"".equals(emerHandleRecord.getId())) {
            try {
                emerHandleRecordOperate = this.getDao().findById(emerHandleRecord.getId());
            } catch (Exception e) {
                throw new ServiceException("根据应急处置记录ID查询应急处置记录失败");
            }
        }
        if(emerHandleRecordOperate == null) {
            emerHandleRecordOperate = new EmerHandleRecord();
            emerHandleRecordOperate.setId(CommonUtil.createUUID());
            emerHandleRecordOperate.setCusNumber(emerHandleRecord.getCusNumber());
            emerHandleRecordOperate.setAlarmRecordId(emerHandleRecord.getAlarmRecordId());
            emerHandleRecordOperate.setPreplanId(emerHandleRecord.getPreplanId());
            emerHandleRecordOperate.setPreplanName(emerHandleRecord.getPreplanName());
            emerHandleRecordOperate.setPreplanSource(emerHandleRecord.getPreplanSource());
            emerHandleRecordOperate.setPreplanRemarks(emerHandleRecord.getPreplanRemarks());
            emerHandleRecordOperate.setAlarmPlanId(emerHandleRecord.getAlarmPlanId());
            emerHandleRecordOperate.setAlarmPlanName(emerHandleRecord.getAlarmPlanName());
            emerHandleRecordOperate.setAlarmAreaId(emerHandleRecord.getAlarmAreaId());
            emerHandleRecordOperate.setAlarmAreaName(emerHandleRecord.getAlarmAreaName());
            emerHandleRecordOperate.setStartTime(new Date());
            emerHandleRecordOperate.setRecordStatus("1");
            emerHandleRecordOperate.setStatus("0");
            Integer showOrder = this.getDao().findMaxShowOrderByCusNumber(emerHandleRecordOperate.getCusNumber());
            emerHandleRecordOperate.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
            emerHandleRecordOperate.setCreateUserId(user.getUserId());
            emerHandleRecordOperate.setCreateTime(new Date());
            emerHandleRecordOperate.setUpdateUserId(user.getUserId());
            emerHandleRecordOperate.setUpdateTime(new Date());

            try {
                this.getDao().insert(emerHandleRecordOperate);
            } catch (Exception e) {
                throw new ServiceException("新增应急处置记录发生异常");
            }
        } else {
            emerHandleRecordOperate.setCusNumber(emerHandleRecord.getCusNumber());
            emerHandleRecordOperate.setAlarmRecordId(emerHandleRecord.getAlarmRecordId());
            emerHandleRecordOperate.setPreplanId(emerHandleRecord.getPreplanId());
            emerHandleRecordOperate.setPreplanName(emerHandleRecord.getPreplanName());
            emerHandleRecordOperate.setPreplanSource(emerHandleRecord.getPreplanSource());
            emerHandleRecordOperate.setPreplanRemarks(emerHandleRecord.getPreplanRemarks());
            emerHandleRecordOperate.setAlarmPlanId(emerHandleRecord.getAlarmPlanId());
            emerHandleRecordOperate.setAlarmPlanName(emerHandleRecord.getAlarmPlanName());
            emerHandleRecordOperate.setAlarmAreaId(emerHandleRecord.getAlarmAreaId());
            emerHandleRecordOperate.setAlarmAreaName(emerHandleRecord.getAlarmAreaName());
            emerHandleRecordOperate.setStartTime(new Date());
            emerHandleRecordOperate.setRecordStatus("1");
            emerHandleRecordOperate.setStatus("0");
            emerHandleRecordOperate.setUpdateUserId(user.getUserId());
            emerHandleRecordOperate.setUpdateTime(new Date());
            try {
                this.getDao().update(emerHandleRecordOperate);
            } catch (Exception e) {
                throw new ServiceException("更新应急处置记录发生异常");
            }
        }

        //4 根据应急记录实体类、处置步骤、工作组、工作组成员，生成应急处置记录相关的处置步骤、工作组、成员列表
        Map<String, Object> map = this.getDataMap(emerHandleRecordOperate, preplan.getEmerStepList());
        //4.1 应急处置记录的处置步骤
        List<EmerHandleRecordStep> emerHandleRecordStepList = map != null && map.get("emerHandleRecordStepList") != null ? (List<EmerHandleRecordStep>) map.get("emerHandleRecordStepList") : null;
        //4.2 应急处置记录的工作组
        List<EmerHandleRecordGroup> emerHandleRecordGroupList = map != null && map.get("emerHandleRecordGroupList") != null ? (List<EmerHandleRecordGroup>) map.get("emerHandleRecordGroupList") : null;
        //4.3 应急处置记录的工作组成员
        List<EmerHandleRecordMember> emerHandleRecordMemberList = map != null && map.get("emerHandleRecordMemberList") != null ? (List<EmerHandleRecordMember>) map.get("emerHandleRecordMemberList") : null;

        //5 保存应急处置记录相关的处置步骤、工作组、成员
        //5.1 保存应急处置记录的处置步骤
        if(emerHandleRecordStepList != null && emerHandleRecordStepList.size() > 0) {
            for(EmerHandleRecordStep emerHandleRecordStep : emerHandleRecordStepList) {
                emerHandleRecordStep.setHandleStatus("0");
                emerHandleRecordStep.setStatus("0");
                Integer showOrder = this.emerHandleRecordStepDao.findMaxShowOrderByCusNumberAndRecordId(emerHandleRecordOperate.getCusNumber(), emerHandleRecordStep.getRecordId());
                emerHandleRecordStep.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
                emerHandleRecordStep.setUpdateUserId(user.getUserId());
                emerHandleRecordStep.setUpdateTime(new Date());

                try {
                    this.emerHandleRecordStepDao.insert(emerHandleRecordStep);
                } catch (Exception e) {
                    throw new ServiceException("保存应急处置记录的处置步骤发生异常");
                }
            }
        }
        //5.2 保存应急处置记录的工作组
        if(emerHandleRecordGroupList != null && emerHandleRecordGroupList.size() > 0) {
            for(EmerHandleRecordGroup emerHandleRecordGroup : emerHandleRecordGroupList) {
                emerHandleRecordGroup.setGroupNoticeStatus("0");
                emerHandleRecordGroup.setStatus("0");
                Integer showOrder = this.emerHandleRecordGroupDao.findMaxShowOrderByCusNumberAndRecordIdAndRecordStepId(emerHandleRecordGroup.getCusNumber(), emerHandleRecordGroup.getRecordId(), emerHandleRecordGroup.getRecordStepId());
                emerHandleRecordGroup.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
                emerHandleRecordGroup.setUpdateUserId(user.getUserId());
                emerHandleRecordGroup.setUpdateTime(new Date());

                try {
                    this.emerHandleRecordGroupDao.insert(emerHandleRecordGroup);
                } catch (Exception e) {
                    throw new ServiceException("保存应急处置记录的工作组发生异常");
                }
            }
        }
        //5.3 保存应急处置记录的工作组成员
        if(emerHandleRecordMemberList != null && emerHandleRecordMemberList.size() > 0) {
            for(EmerHandleRecordMember emerHandleRecordMember : emerHandleRecordMemberList) {
                emerHandleRecordMember.setCallStatus("0");
                emerHandleRecordMember.setStatus("0");
                Integer showOrder = this.emerHandleRecordMemberDao.findMaxShowOrderByCusNumberAndRecordIdAndRecordGroupId(emerHandleRecordMember.getCusNumber(), emerHandleRecordMember.getRecordId(), emerHandleRecordMember.getRecordGroupId());
                emerHandleRecordMember.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
                emerHandleRecordMember.setUpdateUserId(user.getUserId());
                emerHandleRecordMember.setUpdateTime(new Date());

                try {
                    this.emerHandleRecordMemberDao.insert(emerHandleRecordMember);
                } catch (Exception e) {
                    throw new ServiceException("保存应急处置记录的工作组成员发生异常");
                }
            }
        }
        return emerHandleRecordOperate;
    }

    @Override
    public EmerHandleRecord queryById(String id) throws ServiceException {
        //1 根据主键ID，查询应急处置记录
        EmerHandleRecord emerHandleRecord = null;
        try {
            emerHandleRecord = this.getDao().findById(id);
        } catch (Exception e) {
            throw new ServiceException("根据主键ID，查询应急处置记录发生异常");
        }

        //2 根据监狱/单位编号查询监狱/单位信息
        if(emerHandleRecord != null) {
            OrgInfo orgInfo = null;
            try {
                orgInfo = AuthSystemFacade.getOrgByOrgCode(emerHandleRecord.getCusNumber());
            } catch (Exception e) {
                throw new ServiceException("根据单位编号，查询单位信息发生异常");
            }
            if(orgInfo != null) {
                emerHandleRecord.setCusName(orgInfo.getOrganizeName());
            }
        }
        return emerHandleRecord;
    }

    @Override
    public void updateRecordStatus(EmerHandleRecord emerHandleRecord) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }
        if(emerHandleRecord == null || emerHandleRecord.getId() == null || "".equals(emerHandleRecord.getId())) {
            throw new ServiceException("应急处置记录为空");
        }
        if(emerHandleRecord.getRecordStatus() == null || "".equals(emerHandleRecord.getRecordStatus())) {
            throw new ServiceException("应急处置记录状态为空");
        }
        //1 查询应急处置记录的处置步骤
        EmerHandleRecord emerHandleRecordOperate = null;
        try {
            emerHandleRecordOperate = this.getDao().findById(emerHandleRecord.getId());
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录ID，查询应急处置记录信息发生异常，原因：" + e);
        }
        if(emerHandleRecordOperate == null) {
            throw new ServiceException("根据应急处置记录ID，查询到的应急处置记录信息为空");
        }

        //2 更新应急处置记录
        emerHandleRecordOperate.setRecordStatus(emerHandleRecord.getRecordStatus());
        emerHandleRecordOperate.setEndTime(new Date());
        emerHandleRecordOperate.setUpdateUserId(user.getUserId());
        emerHandleRecordOperate.setUpdateTime(new Date());

        try {
            this.getDao().update(emerHandleRecordOperate);
            //一条预案完成，更改其他预案状态为4
            this.closeYuAn(emerHandleRecordOperate);
        } catch (Exception e) {
            throw new ServiceException("更新应急处置记录发生异常");
        }

        //3 更新报警记录(如果应急处置记录为2，将对应的报警记录状态更新为3)
        if("2".equals(emerHandleRecord.getRecordStatus())) {
            AlarmRecordEntity alarmRecordEntity = null;
            try {
                alarmRecordEntity = this.alarmMapper.findById(emerHandleRecordOperate.getAlarmRecordId());
            } catch (Exception e) {
                throw new ServiceException("根据报警记录编号[" + emerHandleRecordOperate.getAlarmRecordId() + "]查询报警记录发生异常，原因：" + e);
            }
            if(alarmRecordEntity == null) {
                logger.info("根据报警记录编号[" + emerHandleRecordOperate.getAlarmRecordId() + "]查询到的报警记录为空，不对报警记录处置状态进行更新");
                return;
            }
            Date date = new Date();
            alarmRecordEntity.setArdOprtnSttsIndc("3");
            alarmRecordEntity.setArdOprtr(user.getUserName());
            alarmRecordEntity.setArdOprtrId(user.getUserId());
            alarmRecordEntity.setArdFinishSurePolice(user.getUserName());
            alarmRecordEntity.setArdFinishSurePoliceId(user.getUserId());
            alarmRecordEntity.setArdOprtnFinishTime(new Date());
            alarmRecordEntity.setArdOprtnTime(new Date());
            alarmRecordEntity.setArdUpdtTime(new Date());
            alarmRecordEntity.setArdUpdtUserId(user.getUserId());

            try {
                this.alarmMapper.update(alarmRecordEntity);
            } catch (Exception e) {
                throw new ServiceException("更新报警记录发生异常");
            }
        }
    }

    @Override
    public EmerHandleRecord queryDetailById(String id) throws ServiceException {
        //1 根据主键ID，查询应急处置记录
        EmerHandleRecord emerHandleRecord = null;
        try {
            emerHandleRecord = this.getDao().findById(id);
        } catch (Exception e) {
            throw new ServiceException("根据主键ID，查询应急处置记录发生异常");
        }
        if(emerHandleRecord == null) {
            throw new ServiceException("根据主键ID，查询应急处置记录为空");
        }

        //2 根据监狱/单位编号查询监狱/单位信息
        if(emerHandleRecord != null) {
            OrgInfo orgInfo = null;
            try {
                orgInfo = AuthSystemFacade.getOrgByOrgCode(emerHandleRecord.getCusNumber());
            } catch (Exception e) {
                throw new ServiceException("根据单位编号，查询单位信息发生异常");
            }
            if(orgInfo != null) {
                emerHandleRecord.setCusName(orgInfo.getOrganizeName());
            }
        }

        //3 根据应急处置记录ID，查询关联的应急处置步骤列表
        List<EmerHandleRecordStep> handleStepList = this.emerHandleRecordStepService.queryDetailListByRecordId(id);
        if(handleStepList != null && handleStepList.size() > 0) {
            emerHandleRecord.setHandleStepList(handleStepList);
        }

        //4 根据应急处置记录ID，查询关联的人员登记表附件列表
        List<AffixEntity> rydjbAffixList = null;
        try {
            AffixEntity affixEntity = new AffixEntity();
            affixEntity.setYwId(id);
            affixEntity.setFileType("3");

            rydjbAffixList = this.affixMapper.findAllList(affixEntity);
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录ID，查询关联的人员登记表附件列表发生异常");
        }
        if(rydjbAffixList != null && rydjbAffixList.size() > 0) {
            emerHandleRecord.setRydjbAffixList(rydjbAffixList);
        }
        return emerHandleRecord;
    }

    /**
     * 根据应急处置记录、应急处置步骤，将应急处置步骤及其关联的工作组、工作组成员转化为应急处置记录的处置步骤、工作组、工作组成员，并存入map
     * @param emerHandleRecord
     * @param emerStepList
     * @return
     */
    private Map<String, Object> getDataMap(EmerHandleRecord emerHandleRecord, List<EmerStep> emerStepList) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(emerStepList != null && emerStepList.size() > 0) {
            List<EmerHandleRecordStep> emerHandleRecordStepList = new ArrayList<EmerHandleRecordStep>();
            List<EmerHandleRecordGroup> emerHandleRecordGroupList = new ArrayList<EmerHandleRecordGroup>();
            List<EmerHandleRecordMember> emerHandleRecordMemberList = new ArrayList<EmerHandleRecordMember>();

            for(EmerStep emerStep : emerStepList) {
                EmerHandleRecordStep emerHandleRecordStep = new EmerHandleRecordStep();
                emerHandleRecordStep.setId(CommonUtil.createUUID());
                emerHandleRecordStep.setCusNumber(emerHandleRecord.getCusNumber());
                emerHandleRecordStep.setRecordId(emerHandleRecord.getId());
                emerHandleRecordStep.setStepName(emerStep.getName());

                emerHandleRecordStepList.add(emerHandleRecordStep);

                Map<String, Object> map = this.getDataMap(emerHandleRecordStep, emerStep.getEmerGroupList());
                List<EmerHandleRecordGroup> tempEmerHandleRecordGroupList = map != null && map.get("emerHandleRecordGroupList") != null ? (List<EmerHandleRecordGroup>) map.get("emerHandleRecordGroupList") : null;
                List<EmerHandleRecordMember> tempEmerHandleRecordMemberList = map != null && map.get("emerHandleRecordMemberList") != null ? (List<EmerHandleRecordMember>) map.get("emerHandleRecordMemberList") : null;
                if(tempEmerHandleRecordGroupList != null && tempEmerHandleRecordGroupList.size() > 0) {
                    emerHandleRecordGroupList.addAll(tempEmerHandleRecordGroupList);
                }
                if(tempEmerHandleRecordMemberList != null && tempEmerHandleRecordMemberList.size() > 0) {
                    emerHandleRecordMemberList.addAll(tempEmerHandleRecordMemberList);
                }
            }
            if(emerHandleRecordStepList != null && emerHandleRecordStepList.size() > 0) {
                resultMap.put("emerHandleRecordStepList", emerHandleRecordStepList);
            }
            if(emerHandleRecordGroupList != null && emerHandleRecordGroupList.size() > 0) {
                resultMap.put("emerHandleRecordGroupList", emerHandleRecordGroupList);
            }
            if(emerHandleRecordMemberList != null && emerHandleRecordMemberList.size() > 0) {
                resultMap.put("emerHandleRecordMemberList", emerHandleRecordMemberList);
            }
        }

        return resultMap;
    }

    /**
     * 根据应急处置记录的处置步骤、应急工作组列表，将应急工作组及工作组成员转化成应急处置记录的工作组、成员列表，并存入map
     * @param emerHandleRecordStep
     * @param emerGroupList
     * @return
     */
    private Map<String, Object> getDataMap(EmerHandleRecordStep emerHandleRecordStep, List<EmerGroup> emerGroupList) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(emerGroupList != null && emerGroupList.size() > 0) {
            List<EmerHandleRecordGroup> emerHandleRecordGroupList = new ArrayList<EmerHandleRecordGroup>();
            List<EmerHandleRecordMember> emerHandleRecordMemberList = new ArrayList<EmerHandleRecordMember>();
            for (EmerGroup emerGroup : emerGroupList) {
                EmerHandleRecordGroup emerHandleRecordGroup = new EmerHandleRecordGroup();
                emerHandleRecordGroup.setId(CommonUtil.createUUID());
                emerHandleRecordGroup.setCusNumber(emerHandleRecordStep.getCusNumber());
                emerHandleRecordGroup.setRecordId(emerHandleRecordStep.getRecordId());
                emerHandleRecordGroup.setRecordStepId(emerHandleRecordStep.getId());
                emerHandleRecordGroup.setGroupName(emerGroup.getName());
                emerHandleRecordGroup.setGroupNotice(emerGroup.getNotice());
                emerHandleRecordGroup.setGetMemberWay(emerGroup.getGetMemberWay());
                emerHandleRecordGroup.setSource(emerGroup.getSource());

                emerHandleRecordGroupList.add(emerHandleRecordGroup);

                Map<String, Object> map = this.getDataMap(emerHandleRecordGroup, emerGroup.getEmerMemberList());
                List<EmerHandleRecordMember> tempEmerHandleRecordMemberList = map != null && map.get("emerHandleRecordMemberList") != null ? (List<EmerHandleRecordMember>) map.get("emerHandleRecordMemberList") : null;
                if(tempEmerHandleRecordMemberList != null && tempEmerHandleRecordMemberList.size() > 0) {
                    emerHandleRecordMemberList.addAll(tempEmerHandleRecordMemberList);
                }
            }
            if(emerHandleRecordGroupList != null && emerHandleRecordGroupList.size() > 0) {
                resultMap.put("emerHandleRecordGroupList", emerHandleRecordGroupList);
            }
            if(emerHandleRecordMemberList != null && emerHandleRecordMemberList.size() > 0) {
                resultMap.put("emerHandleRecordMemberList", emerHandleRecordMemberList);
            }
        }
        return resultMap;
    }

    /**
     * 根据应急处置记录的工作组、应急人员列表，将应急人员列表转化为应急处置记录的应急人员列表，并将结果集存入map返回
     * @param emerHandleRecordGroup
     * @param emerMemberList
     * @return
     */
    private Map<String, Object> getDataMap(EmerHandleRecordGroup emerHandleRecordGroup, List<EmerMember> emerMemberList) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(emerMemberList != null && emerMemberList.size() > 0) {
            List<EmerHandleRecordMember> emerHandleRecordMemberList = new ArrayList<EmerHandleRecordMember>();
            for(EmerMember emerMember : emerMemberList) {
                EmerHandleRecordMember emerHandleRecordMember = new EmerHandleRecordMember();
                emerHandleRecordMember.setId(CommonUtil.createUUID());
                emerHandleRecordMember.setCusNumber(emerHandleRecordGroup.getCusNumber());
                emerHandleRecordMember.setRecordId(emerHandleRecordGroup.getRecordId());
                emerHandleRecordMember.setRecordGroupId(emerHandleRecordGroup.getId());
                emerHandleRecordMember.setMemberJobNo(emerMember.getJobNo());
                emerHandleRecordMember.setMemberName(emerMember.getName());
                emerHandleRecordMember.setCallNo(emerMember.getCallNo());
                emerHandleRecordMember.setUnitName(emerMember.getUnitName());
                emerHandleRecordMember.setDeptName(emerMember.getDeptName());
                emerHandleRecordMember.setSource(emerMember.getSource());

                emerHandleRecordMemberList.add(emerHandleRecordMember);
            }
            if(emerHandleRecordMemberList != null && emerHandleRecordMemberList.size() > 0) {
                resultMap.put("emerHandleRecordMemberList", emerHandleRecordMemberList);
            }
        }
        return resultMap;
    }



    @Override
    public Map<String,Object> queryYjyaReport(String id) {
        Map<String, Object> map =new HashMap<String, Object>();
        EmerHandleRecord emerHandleRecords = this.queryDetailById(id);
        map.put("YjyabgEntity",emerHandleRecords);
        return map;
    }

    @Override
    public AlarmRecordEntity queryAREByid(String id) {
        return this.getDao().queryAREByid(id);
    }

    @Override
    public EmerHandleRecord updateExperience(EmerHandleRecord experience) {
        this.getDao().update(experience);
        return experience;
    }

    /**
     * 一条预案完成，更改其他预案状态为4
     */
    private void closeYuAn(EmerHandleRecord emerHandleRecord){
        List<EmerRecord> emerRecords= alarmService.queryQdyaByid(emerHandleRecord.getAlarmRecordId());
        for (EmerRecord emerRecord : emerRecords) {
            if(!emerRecord.getId().equals(emerHandleRecord.getId())){
                emerRecord.setRecordStatus("4");
                emerRecordService.update(emerRecord);
            }

        }
    }
}
