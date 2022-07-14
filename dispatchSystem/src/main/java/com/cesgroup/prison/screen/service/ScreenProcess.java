package com.cesgroup.prison.screen.service;


import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.fm.util.MsgTypeConst;
import com.cesgroup.prison.screen.bean.TvWallEntity;
import com.cesgroup.prison.screen.bean.TvWallTasksEntity;
import com.cesgroup.prison.screen.dao.*;
import com.cesgroup.prison.screen.entity.ScreenPlanNewEntity;
import com.cesgroup.prison.screen.entity.ScreenPlanWindowNewEntity;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * 电网信息处理
 *
 * @author monkey
 */
@Service
@Transactional
public class ScreenProcess implements IMessageProcess {
    /**
     * 日志处理类
     */
    private final Logger logger = LoggerFactory.getLogger(ScreenProcess.class);

    /**
     * 电网DAO
     */
    @Resource
    private TvWallTasksMapper tvWallTasksMapper;

    @Resource
    private TvWallMapper tvWallMapper;
    @Resource
    private ScreenPlanNewMapper screenPlanNewMapper;
    @Resource
    private ScreenPlanWindowNewMapper screenPlanWindowNewMapper;
    /**
     * Tvwall_id : 1
     * Scene_id : 8
     * Wnd_id : 1
     */

    private String Tvwall_id;
    private String Scene_id;
    private String Wnd_id;

    /**
     * 电视墙登记
     */
    @Transactional
    @Override
    public void processMessage(String cusNumber, JSONObject jsonObject) {
        // 将普通的JSONObject对象转化为JsonObject
        JsonObject gsonObject = Util.fromJson(JSONObject.toJSONString(jsonObject), JsonObject.class);
        // 从gsonObject中取出header，并转化为MsgHeader兑现
        MsgHeader msgHead = gsonObject != null ? Util.fromJson(gsonObject.get("header"), MsgHeader.class) : null;

        // 从msgHead中获取msgType消息类型
        String msgType = msgHead != null ? msgHead.getMsgType() : "";

        if (MsgTypeConst.SCREEN_IN.equals(msgType)) {
            logger.info("收到电视墙信息：" + jsonObject.toJSONString());
            try {
                // 从gsonObject中获取body
                JsonArray body = gsonObject.getAsJsonArray("body");
                if (body == null) {
                    return;
                }
                for (int i = 0; i < body.size(); i++) {
                    JsonObject tvWallJson = body.get(i).getAsJsonObject();
                    if (tvWallJson == null) {
                        continue;
                    }
                    TvWallEntity tvWall = null;
                    tvWall = this.convertJsonToTvWall(tvWallJson, cusNumber);
                    String code = tvWall.getCode();
                    JsonArray tasks = null;
                    List<TvWallTasksEntity> tasksList = null;
                    if (tvWallJson.getAsJsonArray("Tasks") != null && tvWall != null) {
                        tasks = tvWallJson.getAsJsonArray("Tasks");
                        tasksList = this.convertJsonArrayToTasks(tvWall, tasks);

                    }
                    List<TvWallEntity> existTvWall = tvWallMapper.findByCusNumberAndCode(cusNumber, code);
                    if (existTvWall.size() == 0) {
                        if (tvWall != null) {
                            for (TvWallTasksEntity taskEntity : tasksList) {
                                if (taskEntity != null) {
                                    tvWallTasksMapper.insert(taskEntity);

                                }
                            }
                            tvWallMapper.insert(tvWall);
                        }
                    } else {
                        // 判断新数据与旧数据之间的时间差，10分钟以内的不更新
                        long seconds = Util.secondsBetween(tvWall.getUpdateTime(), existTvWall.get(0).getUpdateTime());
                        if (seconds < 60 * 10) {
                            logger.info("CusNumber = " + cusNumber + "，name = " + tvWall.getName() + "距离的上次更新时间为" + seconds + "秒，在10分钟以内，不作更新");

                        } else {
                            BeanUtils.copyProperties(tvWall, existTvWall.get(0), "id", "createTime");
                            tvWallMapper.update(existTvWall.get(0));
                        }
                        if (tasksList != null) {
                            for (TvWallTasksEntity tvWallTask : tasksList) {
                                this.insertOrUpdate(tvWallTask, cusNumber);
                            }
                        }
                    }


                }

            } catch (Exception e) {
                logger.error("更新或插入电视墙数据发生异常，异常原因：" + e);
            }
        } else if (MsgTypeConst.SCENE_ALL_INFO.equals(msgType)) {
            logger.info("收到电视墙预案信息：" + jsonObject.toJSONString());
            try {
                // 从gsonObject中获取body
                JsonObject body = gsonObject.getAsJsonObject("body");
                if (body == null) {
                    return;
                }
                //1.先删除监狱所有的预案信息  再插入所有的预案信息
                screenPlanNewMapper.deleteAll(cusNumber);
                List<ScreenPlanNewEntity> screenPlanNewEntityList = this.convertJsonObjectToScreenPlanNewEntity(body, cusNumber);
                for (ScreenPlanNewEntity screenPlan : screenPlanNewEntityList) {
                    screenPlan.setCreateTime(Util.getCurrentDateTime());
                    screenPlanNewMapper.insert(screenPlan);
                }
            } catch (Exception e) {
                logger.error("更新或插入电视墙预案数据发生异常，异常原因：" + e);
            }
        } else if (MsgTypeConst.TVWALL_WINDOWS.equals(msgType)) {
            //插入电视墙预案窗口数据
            logger.info("收到电视墙预案窗口信息：" + jsonObject.toJSONString());
            try {
                // 从gsonObject中获取body
                JsonArray body = gsonObject.getAsJsonArray("body");
                if (body == null) {
                    return;
                }
                //先判断该数据在数据库中是否可以查到如果查到不更新 没有就插入
                /**
                 * 传过来的消息格式
                 * {
                 *
                 * "Tvwall_id": 1,
                 * "Scene_id": 8,
                 * "Wnd_id": "1"
                 * },
                 * {
                 * "Tvwall_id": 1,
                 * "Scene_id": 8,
                 * "Wnd_id": "2"
                 * },....
                 */
                List<ScreenPlanWindowNewEntity> screenPlanWindowNewEntities = this.convertJsonObjectToScreenPlanWindowCameraEntity(body, cusNumber);
                if(screenPlanWindowNewEntities.size() > 0){
                    screenPlanWindowNewMapper.deleteByCusNumberAndScreenPlan(cusNumber,screenPlanWindowNewEntities.get(0).getScreenPlanId());
                    for(ScreenPlanWindowNewEntity screenPlanWindowNewEntity:screenPlanWindowNewEntities){
                        screenPlanWindowNewEntity.setCreatTime(Util.getCurrentDateTime());
                        screenPlanWindowNewMapper.insert(screenPlanWindowNewEntity);
                    }
                }
            } catch (Exception e) {
                logger.error("更新或插入电视墙预案窗口数据发生异常，异常原因：" + e);
            }
        }else if(MsgTypeConst.SCREEN_PLAN_QH.equals(msgType)){
            //插入电视墙预案窗口数据
            logger.info("收到电视墙预案切换返回信息：" + jsonObject.toJSONString());
            try {
                // 从gsonObject中获取body
                JsonObject body = gsonObject.getAsJsonObject("body");
                if (body == null) {
                    return;
                }
                String status = body.get("Status").getAsString();
                if("200".equals(status)){//预案切换成功
                    String screenPlanId = body.get("CurScene_id").getAsString();
                    ScreenPlanNewEntity screenPlanNewEntity = new ScreenPlanNewEntity();
                    screenPlanNewEntity.setStatus("1");
                    screenPlanNewEntity.setId(screenPlanId);
                    screenPlanNewMapper.updateById(screenPlanNewEntity);
                    //把其他预案状态更新为0
                    ScreenPlanNewEntity sp= new ScreenPlanNewEntity();
                    sp.setCusNumber(cusNumber);
                    sp.setStatus("0");
                    sp.setId(screenPlanId);
                    screenPlanNewMapper.updateInfo(sp);
                }
            } catch (Exception e) {
                logger.error("更新电视墙预案状态发生异常，异常原因：" + e);
            }
        }

    }

    private List<ScreenPlanWindowNewEntity> convertJsonObjectToScreenPlanWindowCameraEntity(JsonArray body, String cusNumber){
        List<ScreenPlanWindowNewEntity> screenPlanWindowNewEntities = new ArrayList<>();
        for (int i = 0; i < body.size(); i++) {
            ScreenPlanWindowNewEntity screenPlanWindowNewEntity = new ScreenPlanWindowNewEntity();
            JsonElement screenAreaWindowJson = body.get(i);
            if (screenAreaWindowJson != null) {
                JsonObject asJsonObject = screenAreaWindowJson.getAsJsonObject();
                JsonElement tvwallId = asJsonObject.get("Tvwall_id");
                if (tvwallId!=null && !"".equals(tvwallId.getAsString())) {
                    screenPlanWindowNewEntity.setTvwallId(tvwallId.getAsString());
                }

                JsonElement sceneId = asJsonObject.get("Scene_id");
                if (sceneId!=null && !"".equals(sceneId.getAsString())) {
                     screenPlanWindowNewEntity.setScreenPlanId(sceneId.getAsString());
                }
                JsonElement wndId = asJsonObject.get("Wnd_id");
                if (wndId!=null && !"".equals(wndId.getAsString())) {
                     screenPlanWindowNewEntity.setWindowId(wndId.getAsString());
                }
                 screenPlanWindowNewEntity.setCusNumber(cusNumber);
                screenPlanWindowNewEntities.add(screenPlanWindowNewEntity);
            }
        }

        return screenPlanWindowNewEntities;
    }



    private List<ScreenPlanNewEntity> convertJsonObjectToScreenPlanNewEntity(JsonObject body,String cusNumber){
        List<ScreenPlanNewEntity> screenPlanNewEntityList = new ArrayList<>();
        JsonArray screenPlanList = body.getAsJsonArray("Scene_list");
        for (int i = 0;i<screenPlanList.size();i++){
            ScreenPlanNewEntity screenPlanNewEntity = new ScreenPlanNewEntity();
            JsonElement tvwallId = body.get("Tvwall_id");
            if(tvwallId !=null && !"".equals(tvwallId.getAsString())){
                screenPlanNewEntity.setTywallId(tvwallId.getAsString());
            }
            JsonElement tvwallName = body.get("Tvwall_name");
            if(tvwallName !=null && !"".equals(tvwallName.getAsString())){
                screenPlanNewEntity.setTywallName(tvwallName.getAsString());
            }
            JsonElement curScreenPlanId = body.get("CurScreen_id");//当前使用的预案id
            JsonElement screenLplanJson= screenPlanList.get(i);
            if(screenLplanJson!=null){
                JsonElement screenPlanId  = screenLplanJson.getAsJsonObject().get("Scene_id");
                if(screenPlanId!=null && !"".equals(screenPlanId.getAsString())){
                    screenPlanNewEntity.setId(screenPlanId.getAsString());
                    if(curScreenPlanId!=null && !"".equals(curScreenPlanId.getAsString())){
                        if(curScreenPlanId.getAsString().equals(screenPlanId.getAsString())){//设置预案的使用状态 0未使用  1.在使用
                            screenPlanNewEntity.setStatus("1");
                        }else{
                            screenPlanNewEntity.setStatus("0");
                        }
                    }
                }
                JsonElement screenPlanName = screenLplanJson.getAsJsonObject().get("Scene_name");
                if(screenPlanName!=null && !"".equals(screenPlanName.getAsString())){
                    screenPlanNewEntity.setScreenPlanName(screenPlanName.getAsString());
                }
            }

            screenPlanNewEntity.setCusNumber(cusNumber);
            screenPlanNewEntityList.add(screenPlanNewEntity);
        }
        return screenPlanNewEntityList;
    }



    private void insertOrUpdate(TvWallTasksEntity tvWallTask, String cusNumber) {
        String code = tvWallTask.getCode();
        String tvWallId = tvWallTask.getTvWallId();
        List<TvWallTasksEntity> existTask = this.tvWallTasksMapper.findByCusNumberAndCodeAndTvWallId(cusNumber, code, tvWallId);
        if (existTask.size() == 0) {
            tvWallTask.setCreateTime(new Date());
            this.tvWallTasksMapper.insert(tvWallTask);
        } else {
            long seconds = Util.secondsBetween(tvWallTask.getUpdateTime(), existTask.get(0).getUpdateTime());
            if(seconds < 60 * 10) {
                logger.info("CusNumber = " + cusNumber + "，name = " + tvWallTask.getName() + "距离的上次更新时间为" + seconds + "秒，在10分钟以内，不作更新");
                return;
            }
            BeanUtils.copyProperties(tvWallTask, existTask.get(0), "id","createTime");
            existTask.get(0).setUpdateTime(new Date());
            this.tvWallTasksMapper.update(existTask.get(0));
        }
    }

    private List<TvWallTasksEntity> convertJsonArrayToTasks(TvWallEntity tvWall, JsonArray tasks) {
        List<TvWallTasksEntity> tasksList = new ArrayList<TvWallTasksEntity>();
        for (int i = 0; i < tasks.size(); i++) {
            JsonElement tasksEntityJson = tasks.get(i);
            if (tasksEntityJson == null) {
                continue;
            } else {
                TvWallTasksEntity tasksEntity = this.convertJsonToTvWallTask(tasksEntityJson.getAsJsonObject(), tvWall);
                if (tasksEntity == null) {
                    continue;
                } else {
                    tasksList.add(tasksEntity);
                }
            }

        }
        return tasksList;
    }

    private TvWallTasksEntity convertJsonToTvWallTask(JsonObject tasksEntityJson, TvWallEntity tvWall) {
        TvWallTasksEntity tasksEntity = new TvWallTasksEntity();
        JsonElement id = tasksEntityJson.get("Id");
        JsonElement name = tasksEntityJson.get("Name");
        JsonElement des = tasksEntityJson.get("Des");
        if (id != null && !id.getAsString().equals("")) {
            tasksEntity.setCode(id.getAsString());
        } else {
            return null;
        }
        if (name != null && !name.getAsString().equals("")) {
            tasksEntity.setName(name.getAsString());
        }
        if (des != null && !des.getAsString().equals("")) {
            tasksEntity.setDes(des.getAsString());
        }
        tasksEntity.setTvWallId(tvWall.getCode());
        tasksEntity.setCusNumber(tvWall.getCusNumber());
        tasksEntity.setCreateTime(new Date());
        tasksEntity.setUpdateTime(new Date());
        return tasksEntity;
    }

    private TvWallEntity convertJsonToTvWall(JsonObject tvWallJson, String cusNumber) {
        TvWallEntity entity = new TvWallEntity();
        JsonElement tvWallId = tvWallJson.get("TvWallId");
        JsonElement state = tvWallJson.get("State");
        JsonElement name = tvWallJson.get("Name");
        if (tvWallId != null && !tvWallId.getAsString().equals("")) {
            entity.setCode(tvWallId.getAsString());
        } else {
            return null;
        }
        if (state != null && !state.getAsString().equals("")) {
            entity.setState(state.getAsString());
        }
        if (name != null && !name.getAsString().equals("")) {
            entity.setName(name.getAsString());
        }
        entity.setCusNumber(cusNumber);
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        return entity;
    }


    public String getTvwall_id() {
        return Tvwall_id;
    }

    public void setTvwall_id(String Tvwall_id) {
        this.Tvwall_id = Tvwall_id;
    }

    public String getScene_id() {
        return Scene_id;
    }

    public void setScene_id(String Scene_id) {
        this.Scene_id = Scene_id;
    }

    public String getWnd_id() {
        return Wnd_id;
    }

    public void setWnd_id(String Wnd_id) {
        this.Wnd_id = Wnd_id;
    }
}
