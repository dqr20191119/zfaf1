package com.cesgroup.prison.alarm.dateD.service.impl;

import com.alibaba.fastjson.JSON;
import com.cesgroup.prison.alarm.dateD.service.AlarmDataService;
import com.cesgroup.prison.alarm.plan.dao.AlarmPlanItemDtlsMapper;
import com.cesgroup.prison.alarm.plan.dao.AlarmPlanMasterMapper;
import com.cesgroup.prison.alarm.plan.dao.AlertPlanRltnMapper;
import com.cesgroup.prison.alarm.plan.dao.PlanDeviceRltnMapper;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanItemDtlsEntity;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanMasterEntity;
import com.cesgroup.prison.alarm.plan.entity.AlertPlanRltnEntity;
import com.cesgroup.prison.alarm.plan.entity.PlanDeviceRltnEntity;
import com.cesgroup.prison.alarm.record.dao.AlarmMapper;
import com.cesgroup.prison.jfsb.dao.AlertorMapper;
import com.cesgroup.prison.jfsb.dao.CameraMapper;
import com.cesgroup.prison.jfsb.dao.DoorMapper;
import com.cesgroup.prison.jfsb.dao.TalkBackBaseMapper;
import com.cesgroup.prison.jfsb.entity.AlertorEntity;
import com.cesgroup.prison.jfsb.entity.Camera;
import com.cesgroup.prison.jfsb.entity.DoorEntity;
import com.cesgroup.prison.jfsb.entity.TalkBackBaseEntity;
import com.cesgroup.prison.jfsb.service.ICameraService;
import com.cesgroup.prison.screen.dao.ScreenPlanMapper;
import com.cesgroup.prison.screen.entity.ScreenPlanEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AlarmDataServiceImpl implements AlarmDataService {

    @Autowired
    private AlertorMapper bjqMapper;

    @Autowired
    private CameraMapper sxMapper;

    @Autowired
    private DoorMapper mjMapper;

    @Autowired
    private ScreenPlanMapper dpMapper;

    @Autowired
    private TalkBackBaseMapper djMapper;

    @Autowired
    private AlarmPlanItemDtlsMapper yaItmeMapper;

    @Autowired
    private AlarmPlanMasterMapper yaMapper;

    @Autowired
    private AlertPlanRltnMapper yaBjqMapper;

    @Autowired
    private PlanDeviceRltnMapper yaSbMapper;

    @Autowired
    private AlarmMapper alarmMapper;

    @Autowired
    private ICameraService service;

    @Override
    public void loadAlertorData(String cusNumber, String dvcType, String areaId) {
        Date date = new Date();
        String user = "admin";
        if (dvcType.equals("9")) {// 对讲
            TalkBackBaseEntity entity = new TalkBackBaseEntity();
            entity.setTbdCusNumber(cusNumber);
            entity.setTbdAreaId(areaId);
            List<TalkBackBaseEntity> list = djMapper.selectByEntity(entity);
            if (!list.isEmpty()) {
                for (TalkBackBaseEntity talkBackBaseEntity : list) {
                    AlertorEntity alertorEntity = new AlertorEntity();
                    alertorEntity.setAbdActionIndc("1");

                    if (talkBackBaseEntity.getTbdName().endsWith("监舍")) {
                        int jsIndex = talkBackBaseEntity.getTbdName().indexOf("监舍");
                        String js = talkBackBaseEntity.getTbdName().substring(jsIndex - 3, jsIndex);
                        alertorEntity.setAbdAddrs(js);
                    }

                    alertorEntity.setAbdAreaId(talkBackBaseEntity.getTbdAreaId());
                    alertorEntity.setAbdArea(talkBackBaseEntity.getTbdArea());
                    alertorEntity.setAbdCrteTime(date);
                    alertorEntity.setAbdCrteUserId(user);
                    alertorEntity.setAbdUpdtTime(date);
                    alertorEntity.setAbdUpdtUserId(user);
                    alertorEntity.setAbdCusNumber(talkBackBaseEntity.getTbdCusNumber());
                    alertorEntity.setAbdHostIdnty(talkBackBaseEntity.getId());
                    alertorEntity.setAbdSttsIndc("0");// 正常
                    alertorEntity.setAbdIdnty("djbjq" + talkBackBaseEntity.getTbdIdnty());
                    alertorEntity.setAbdName(talkBackBaseEntity.getTbdName());
                    alertorEntity.setAbdTypeIndc(dvcType);
                    bjqMapper.insert(alertorEntity);
                }


            }
        }

        if (dvcType.equals("6")) {// 摄像

        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void loadPlanData(HttpServletRequest request, HttpServletResponse response) {
        Date date = new Date();
        String user = "admin";

        String cusNumber = request.getParameter("cusNumber");
        List<String> dpIds = (List<String>) (JSON.parse(request.getParameter("dpIds")));//大屏
        List<String> bjqIds = (List<String>) (JSON.parse(request.getParameter("bjqIds")));//报警器
        String mjIds = request.getParameter("mjIds");//门禁
        String djIds = request.getParameter("djIds");//对讲
        String sxjIds = request.getParameter("sxjIds");//摄像机

        if (bjqIds != null && !bjqIds.isEmpty()) {
            for (String bjsId : bjqIds) {
                AlertorEntity alertorEntity = bjqMapper.selectOne(bjsId);
                // 创建预案
                AlarmPlanMasterEntity planMasterEntity = new AlarmPlanMasterEntity();
                planMasterEntity.setPmaCusNumber(cusNumber);
                planMasterEntity.setPmaCrteTime(date);
                planMasterEntity.setPmaCrteUserId(user);
                planMasterEntity.setPmaUpdtTime(date);
                planMasterEntity.setPmaUpdtUserId(user);
                planMasterEntity.setPmaRemark("【" + alertorEntity.getAbdArea() + "】报警预案");

                String planName = null;
                if ("9".equals(alertorEntity.getAbdTypeIndc())) {// 对讲报警器
                    planName = "【对讲】" + alertorEntity.getAbdName() + "报警预案";
                } else {
                    planName = alertorEntity.getAbdName();
                }
                planMasterEntity.setPmaPlanName(planName);
                yaMapper.insert(planMasterEntity); // 预案主表创建
                String planId = planMasterEntity.getId();

				/*
                 * 1 "门禁"; 2 "摄像机"; 3 "广播"; 4 "大屏"; 5 "对讲";
				 */

				//大屏
                if (dpIds != null && !dpIds.isEmpty()) {
                    AlarmPlanItemDtlsEntity itemDtlsEntity = new AlarmPlanItemDtlsEntity();// 预案item
                    itemDtlsEntity.setPidCrteTime(date);
                    itemDtlsEntity.setPidCrteUserId(user);
                    itemDtlsEntity.setPidCusNumber(cusNumber);
                    itemDtlsEntity.setPidUpdtTime(date);
                    itemDtlsEntity.setPidUpdtUserId(user);
                    itemDtlsEntity.setPidPlanId(planId);
                    itemDtlsEntity.setPidSttsIndc("1");
                    itemDtlsEntity.setPidItemId("4");
                    yaItmeMapper.insert(itemDtlsEntity);
                    for (int i = 0; i < dpIds.size(); i++) {
                        ScreenPlanEntity entity = dpMapper.selectOne(dpIds.get(i));
                        if (entity != null) {
                            PlanDeviceRltnEntity planDeviceRltnEntity = new PlanDeviceRltnEntity();// 预案关联设备
                            planDeviceRltnEntity.setPdrCrteTime(date);
                            planDeviceRltnEntity.setPdrCrteUserId(user);
                            planDeviceRltnEntity.setPdrUpdtTime(date);
                            planDeviceRltnEntity.setPdrUpdtUserId(user);
                            planDeviceRltnEntity.setPdrCusNumber(cusNumber);
                            planDeviceRltnEntity.setPdrPlanId(planId);
                            planDeviceRltnEntity.setPdrItemId("4");
                            planDeviceRltnEntity.setPdrDvcIdnty(entity.getId());
                            planDeviceRltnEntity.setPdrDvcName(entity.getSplPlanName());
                            if (entity.getSplPlanName().equals("报警预案")) {
                                planDeviceRltnEntity.setPdrOutoIndc("1");
                            } else {
                                planDeviceRltnEntity.setPdrOutoIndc("0");
                            }
                            planDeviceRltnEntity.setPdrExecOrder((i + 1) + "");
                            yaSbMapper.insert(planDeviceRltnEntity);
                        }
                    }
                }

                //门禁
                if (StringUtils.isNotBlank(mjIds)) {
                    AlarmPlanItemDtlsEntity itemDtlsEntity = new AlarmPlanItemDtlsEntity();// 预案item
                    itemDtlsEntity.setPidCrteTime(date);
                    itemDtlsEntity.setPidCrteUserId(user);
                    itemDtlsEntity.setPidCusNumber(cusNumber);
                    itemDtlsEntity.setPidUpdtTime(date);
                    itemDtlsEntity.setPidUpdtUserId(user);
                    itemDtlsEntity.setPidPlanId(planId);
                    itemDtlsEntity.setPidSttsIndc("1");
                    itemDtlsEntity.setPidItemId("1");
                    yaItmeMapper.insert(itemDtlsEntity);
                    String[] mjs = mjIds.split(",");
                    for (int i = 0; i < mjs.length; i++) {
                        DoorEntity doorEntity = mjMapper.selectOne(mjs[i]);
                        if (doorEntity != null) {
                            PlanDeviceRltnEntity planDeviceRltnEntity = new PlanDeviceRltnEntity();// 预案关联设备
                            planDeviceRltnEntity.setPdrCrteTime(date);
                            planDeviceRltnEntity.setPdrCrteUserId(user);
                            planDeviceRltnEntity.setPdrUpdtTime(date);
                            planDeviceRltnEntity.setPdrUpdtUserId(user);
                            planDeviceRltnEntity.setPdrCusNumber(cusNumber);
                            planDeviceRltnEntity.setPdrPlanId(planId);
                            planDeviceRltnEntity.setPdrItemId("1");
                            planDeviceRltnEntity.setPdrDvcIdnty(doorEntity.getId());
                            planDeviceRltnEntity.setPdrDvcName(doorEntity.getDcbName());
                            planDeviceRltnEntity.setPdrOutoIndc("0");
                            planDeviceRltnEntity.setPdrExecOrder((i + 1) + "");
                            yaSbMapper.insert(planDeviceRltnEntity);
                        }
                    }
                }

                //对讲
                if (StringUtils.isNotBlank(djIds)) {
                    AlarmPlanItemDtlsEntity itemDtlsEntity = new AlarmPlanItemDtlsEntity();// 预案item
                    itemDtlsEntity.setPidCrteTime(date);
                    itemDtlsEntity.setPidCrteUserId(user);
                    itemDtlsEntity.setPidCusNumber(cusNumber);
                    itemDtlsEntity.setPidUpdtTime(date);
                    itemDtlsEntity.setPidUpdtUserId(user);
                    itemDtlsEntity.setPidPlanId(planId);
                    itemDtlsEntity.setPidSttsIndc("1");
                    itemDtlsEntity.setPidItemId("5");
                    yaItmeMapper.insert(itemDtlsEntity);
                    String[] djs = djIds.split(",");
                    for (int i = 0; i < djs.length; i++) {
                        TalkBackBaseEntity backBaseEntity = djMapper.selectOne(djs[i]);
                        if (backBaseEntity != null) {
                            PlanDeviceRltnEntity planDeviceRltnEntity = new PlanDeviceRltnEntity();// 预案关联设备
                            planDeviceRltnEntity.setPdrCrteTime(date);
                            planDeviceRltnEntity.setPdrCrteUserId(user);
                            planDeviceRltnEntity.setPdrUpdtTime(date);
                            planDeviceRltnEntity.setPdrUpdtUserId(user);
                            planDeviceRltnEntity.setPdrCusNumber(cusNumber);
                            planDeviceRltnEntity.setPdrPlanId(planId);
                            planDeviceRltnEntity.setPdrItemId("5");
                            planDeviceRltnEntity.setPdrDvcIdnty(backBaseEntity.getTbdIdnty());
                            planDeviceRltnEntity.setPdrDvcName(backBaseEntity.getTbdName());
                            planDeviceRltnEntity.setPdrOutoIndc("0");
                            planDeviceRltnEntity.setPdrExecOrder((i + 1) + "");
                            yaSbMapper.insert(planDeviceRltnEntity);
                        }
                    }

                }

                //摄像机
                if (StringUtils.isNotBlank(sxjIds)) {
                    AlarmPlanItemDtlsEntity itemDtlsEntity = new AlarmPlanItemDtlsEntity();// 预案item
                    itemDtlsEntity.setPidCrteTime(date);
                    itemDtlsEntity.setPidCrteUserId(user);
                    itemDtlsEntity.setPidCusNumber(cusNumber);
                    itemDtlsEntity.setPidUpdtTime(date);
                    itemDtlsEntity.setPidUpdtUserId(user);
                    itemDtlsEntity.setPidPlanId(planId);
                    itemDtlsEntity.setPidSttsIndc("1");
                    itemDtlsEntity.setPidItemId("2");
                    yaItmeMapper.insert(itemDtlsEntity);

                    int autogs = 0;
                    int pdrExecOrder = 1;
                    //查询摄像机关联的对讲设备
                    List<Map<String,String>> cameraList = alarmMapper.findCameraByTalkBack(bjsId);
                    if(cameraList.size() > 0){
                        for(Map<String,String> m : cameraList){
                            String cameraId = m.get("ID").toString();
                            String cameraName = m.get("CBD_NAME").toString();
                            PlanDeviceRltnEntity planDeviceRltnEntity = new PlanDeviceRltnEntity();// 预案关联设备
                            planDeviceRltnEntity.setPdrCrteTime(date);
                            planDeviceRltnEntity.setPdrCrteUserId(user);
                            planDeviceRltnEntity.setPdrUpdtTime(date);
                            planDeviceRltnEntity.setPdrUpdtUserId(user);
                            planDeviceRltnEntity.setPdrCusNumber(cusNumber);
                            planDeviceRltnEntity.setPdrPlanId(planId);
                            planDeviceRltnEntity.setPdrItemId("2");
                            planDeviceRltnEntity.setPdrDvcIdnty(cameraId);
                            planDeviceRltnEntity.setPdrDvcName(cameraName);
                            planDeviceRltnEntity.setPdrOutoIndc("1");
                            planDeviceRltnEntity.setPdrExecOrder(pdrExecOrder+ "");
                            yaSbMapper.insert(planDeviceRltnEntity);
                            pdrExecOrder++;
                        }
                        autogs = cameraList.size();
                    }

                    String[] sxjs = sxjIds.split(",");
                    for (int i = 0; i < sxjs.length; i++) {
                        Camera entity = sxMapper.selectOne(sxjs[i]);
                        if (entity != null) {
                            PlanDeviceRltnEntity planDeviceRltnEntity = new PlanDeviceRltnEntity();// 预案关联设备
                            planDeviceRltnEntity.setPdrCrteTime(date);
                            planDeviceRltnEntity.setPdrCrteUserId(user);
                            planDeviceRltnEntity.setPdrUpdtTime(date);
                            planDeviceRltnEntity.setPdrUpdtUserId(user);
                            planDeviceRltnEntity.setPdrCusNumber(cusNumber);
                            planDeviceRltnEntity.setPdrPlanId(planId);
                            planDeviceRltnEntity.setPdrItemId("2");
                            planDeviceRltnEntity.setPdrDvcIdnty(entity.getId());
                            planDeviceRltnEntity.setPdrDvcName(entity.getCbdName());
                            String outo= "0";
                            //自动打开个数超过16个,后续都设置成手工打开
                            if(autogs <= 16){
                                outo = "1";
                                autogs++;
                                if (entity.getCbdName().indexOf("楼梯") > 0 || entity.getCbdName().indexOf("办公室") > 0) {
                                    outo = "0";
                                    autogs --;
                                }
                            }
                            planDeviceRltnEntity.setPdrOutoIndc(outo);
                            planDeviceRltnEntity.setPdrExecOrder(pdrExecOrder + "");
                            yaSbMapper.insert(planDeviceRltnEntity);
                            pdrExecOrder++;
                        }
                    }
                }

                //判断报警器是否来邦的对讲
                switch (alertorEntity.getAbdTypeIndc()) {
                    case "6":

                        break;
                    case "9":
                        TalkBackBaseEntity backBaseEntity = djMapper.selectOne(alertorEntity.getAbdHostIdnty());
                        if (backBaseEntity != null) {
                            PlanDeviceRltnEntity planDeviceRltnEntity = new PlanDeviceRltnEntity();// 预案关联设备
                            planDeviceRltnEntity.setPdrCrteTime(date);
                            planDeviceRltnEntity.setPdrCrteUserId(user);
                            planDeviceRltnEntity.setPdrUpdtTime(date);
                            planDeviceRltnEntity.setPdrUpdtUserId(user);
                            planDeviceRltnEntity.setPdrCusNumber(cusNumber);
                            planDeviceRltnEntity.setPdrPlanId(planId);
                            planDeviceRltnEntity.setPdrItemId("5");
                            planDeviceRltnEntity.setPdrDvcIdnty(backBaseEntity.getTbdIdnty());
                            planDeviceRltnEntity.setPdrDvcName(backBaseEntity.getTbdName());
                            planDeviceRltnEntity.setPdrOutoIndc("0");
                            // planDeviceRltnEntity.setPdrExecOrder("0");
                            yaSbMapper.insert(planDeviceRltnEntity);

                            String dvcName = alertorEntity.getAbdName();
                            String js = "监舍";
                            int index = dvcName.indexOf(js);
                            if (index > 0 && index - 3 > 0) {
                                Camera camera_param = new Camera();
                                camera_param.setCbdCusNumber(cusNumber);
                                camera_param.setCbdAreaId(alertorEntity.getAbdAreaId());

                                //六号监舍
                                if (alertorEntity.getAbdAreaId().indexOf("65060118") != -1) {
                                    String type = dvcName.substring(index - 4, index - 3);
                                    camera_param.setCbdName(dvcName.substring(index - 2, index) + js);
                                    PageRequest pageRequest = new PageRequest(20, 20);
                                    Page<Map<String, String>> page = service.searchCamera(camera_param, pageRequest);
                                    List<Map<String, String>> list = page.getContent();
                                    if (!list.isEmpty()) {

                                        for (Map<String, String> map : list) {
                                            if (map.get("CBD_NAME").indexOf(type) == -1) {
                                                continue;
                                            }
                                            PlanDeviceRltnEntity planDeviceRltnEntity1 = new PlanDeviceRltnEntity();// 预案关联设备
                                            planDeviceRltnEntity1.setPdrCrteTime(date);
                                            planDeviceRltnEntity1.setPdrCrteUserId(user);
                                            planDeviceRltnEntity1.setPdrUpdtTime(date);
                                            planDeviceRltnEntity1.setPdrUpdtUserId(user);
                                            planDeviceRltnEntity1.setPdrCusNumber(cusNumber);
                                            planDeviceRltnEntity1.setPdrPlanId(planId);
                                            planDeviceRltnEntity1.setPdrItemId("2");
                                            planDeviceRltnEntity1.setPdrDvcIdnty(map.get("ID"));
                                            planDeviceRltnEntity1.setPdrDvcName(map.get("CBD_NAME"));
                                            planDeviceRltnEntity1.setPdrOutoIndc("1");
                                            planDeviceRltnEntity.setPdrExecOrder("0");//此处需要修改
                                            yaSbMapper.insert(planDeviceRltnEntity1);
                                        }

                                    }

                                } else {
                                    camera_param.setCbdName(dvcName.substring(index - 3, index) + js);
                                    PageRequest pageRequest = new PageRequest(20, 20);
                                    Page<Map<String, String>> page = service.searchCamera(camera_param, pageRequest);
                                    List<Map<String, String>> list = page.getContent();
                                    if (!list.isEmpty() && list.size() == 1) {
                                        PlanDeviceRltnEntity planDeviceRltnEntity1 = new PlanDeviceRltnEntity();// 预案关联设备
                                        planDeviceRltnEntity1.setPdrCrteTime(date);
                                        planDeviceRltnEntity1.setPdrCrteUserId(user);
                                        planDeviceRltnEntity1.setPdrUpdtTime(date);
                                        planDeviceRltnEntity1.setPdrUpdtUserId(user);
                                        planDeviceRltnEntity1.setPdrCusNumber(cusNumber);
                                        planDeviceRltnEntity1.setPdrPlanId(planId);
                                        planDeviceRltnEntity1.setPdrItemId("2");
                                        planDeviceRltnEntity1.setPdrDvcIdnty(list.get(0).get("ID"));
                                        planDeviceRltnEntity1.setPdrDvcName(list.get(0).get("CBD_NAME"));
                                        planDeviceRltnEntity1.setPdrOutoIndc("1");
                                        planDeviceRltnEntity.setPdrExecOrder("0");
                                        yaSbMapper.insert(planDeviceRltnEntity1);
                                    }
                                }
                            }

                        }
                        // TODO
                        break;
                    default:
                        break;
                }

                AlertPlanRltnEntity alertPlanRltnEntity = new AlertPlanRltnEntity();
                alertPlanRltnEntity.setAprUpdtTime(date);
                alertPlanRltnEntity.setAprCrteUserId(user);
                alertPlanRltnEntity.setAprCrteTime(date);
                alertPlanRltnEntity.setAprCrteUserId(user);
                alertPlanRltnEntity.setAprCusNumber(cusNumber);
                alertPlanRltnEntity.setAprBrandIndc(alertorEntity.getAbdBrandIndc());
                alertPlanRltnEntity.setAprDvcIdnty(alertorEntity.getAbdIdnty());
                alertPlanRltnEntity.setAprDvcName(alertorEntity.getAbdName());
                alertPlanRltnEntity.setAprDvcTypeIndc(alertorEntity.getAbdTypeIndc());
                alertPlanRltnEntity.setAprPlanId(planId);
                yaBjqMapper.insert(alertPlanRltnEntity);
            }
        }

    }
}
