package com.cesgroup.prison.point.service;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.point.dao.PointMapper;
import com.cesgroup.prison.point.entity.Point;
import com.cesgroup.prison.point.vo.PointVo;
import com.cesgroup.prison.region.entity.Region;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.view.entity.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PointService extends BaseService<Point, String> {

    @Autowired
    private PointMapper pointapper;

    /**
     * 保存点位信息  已存在就对这个设备进行执行更新操作
     * @param pointVo
     * @return
     */
    @Transactional(readOnly = false)
    public Map<String,Object> savePoint(PointVo pointVo) {
        Map<String,Object> map=new HashMap<String,Object>();
        try {
    		UserBean userBean= AuthSystemFacade.getLoginUserInfo();
    		String userId = userBean.getUserId();
            int ifHave=pointapper.getCountById(pointVo);
            if(ifHave>0) {//此摄像头已经存在执行更新操作
                pointVo.setAlpUpdtUserId(userId);
                pointapper.updateAll(pointVo);
            }else {//不存在执行添加操作
                pointVo.setAlpCrteUserId(userId);
                pointVo.setAlpUpdtUserId(userId);
                pointVo.setId(CommonUtil.createUUID().replace("-", ""));
                pointapper.insertAll(pointVo);
            }
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success",false);
        }
        return map;
    }

    public Page<PointVo> findByPage(PageRequest pageRequest, PointVo pointVo) {
        return pointapper.findByPage(pageRequest,pointVo);
    }



/*    public Map<String,Object> destoryPoint(String id) {
        Map<String,Object> map=new HashMap<String,Object>();
        try {
            pointapper.destoryPoint(id);
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success",false);
        }
        return map;
    }*/
    @Transactional(readOnly = false)
    public int destoryPoint(String id) {
        return pointapper.destoryPoint(id);
    }
    /**
     * 根据区域id和设备类型获取设备信息
     * @param pointVo
     * @return
     */
    public List<PointVo> getAllDevice(PointVo pointVo) {
        List<PointVo> allData=null;
        //符合摄像头类型 调用摄像头接口
        if(pointVo.getAlpDeviceType().equals(CommonConstant.CAMERA_DEVICE_TYPE))
            allData=pointapper.getAllCamera(pointVo);
        //调用报警
        if(pointVo.getAlpDeviceType().equals(CommonConstant.ALERTOR_DEVICE_TYPE))
            allData=pointapper.getAllAlarm(pointVo);
        //调用门禁
        if(pointVo.getAlpDeviceType().equals(CommonConstant.DOOR_DEVICE_TYPE))
            allData=pointapper.getAllDoor(pointVo);
        //调用广播
        if(pointVo.getAlpDeviceType().equals(CommonConstant.BROADCAST_DEVICE_TYPE))
            allData=pointapper.getAllBroadcast(pointVo);
        //对讲分机
        if(pointVo.getAlpDeviceType().equals(CommonConstant.TALK_DEVICE_TYPE))
            allData=pointapper.getAllDeviceOther(pointVo);
        //对讲主机
        if(pointVo.getAlpDeviceType().equals(CommonConstant.TALK_SERVER_DEVICE_TYPE))
            allData=pointapper.getAllDeviceMain(pointVo);
        //地图标签
        if(pointVo.getAlpDeviceType().equals(CommonConstant.LABEL_DEVICE_TYPE))
            allData=pointapper.getAllTabs(pointVo);
        return allData;
    }
    /**
     * 根据设备id和监狱编号 获取此设备的点位信息
     * @param pointVo
     * @return
     */
    public List<PointVo> getDevicePointById(PointVo pointVo) {
        return pointapper.getDevicePointById(pointVo);
    }
    /**
     * 所有设备信息  sql中进行拼接数据维护
     * @param pointVo
     * @return
     */
    public Page<PointVo> getAllTypeDevice(PageRequest pageRequest, PointVo pointVo) {
        return pointapper.getAllTypeDevice(pageRequest,pointVo);
    }

    public List<View> getViewByGrandId(PointVo pointVo) {
        return pointapper.getViewByGrandId(pointVo);
    }
    /**
     * @Author mxz
     * date:2017-12-29
     * @Param
     * 1、根据区域id，设备类型,监狱编号 获取此区域下的所有点位
     * 2、是否显示子区域下的所有点位 0、不显示1、显示
     * */
    public List<PointVo> getAllPointByGrandAndType(String cusNumber,String grandId,String deviceType,int ifShowChild) {
        List<PointVo> allPoint=new ArrayList<PointVo>();
        List<String> allArandId=new ArrayList<String>();
        PointVo pointVo=new PointVo();
        try {
            if(ifShowChild==0){
                allArandId.add(grandId);
                pointVo.setStrList(allArandId);
                pointVo.setAlpCusNumber(cusNumber);
                if(deviceType!=null&&deviceType!=""){
                    String[] arr=deviceType.split(",");
                    pointVo.setAlpDeviceStr(java.util.Arrays.asList(arr));
                }else{
                    pointVo.setAlpDeviceStr(null);
                }
                allPoint=pointapper.getAllPointByGrandAndType(pointVo);
            }else{
                allArandId=pointapper.getAllGrandByThis(cusNumber,grandId);//得到此区域和此区域下的所有子区域
                pointVo.setStrList(allArandId);
                pointVo.setAlpCusNumber(cusNumber);
                if(deviceType!=null&&deviceType!=""){
                    String[] arr=deviceType.split(",");
                    pointVo.setAlpDeviceStr(java.util.Arrays.asList(arr));
                }else
                    pointVo.setAlpDeviceStr(null);
                allPoint=pointapper.getAllPointByGrandAndType(pointVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allPoint;
    }
    /**
     * @Author mxz
     * date:2017-12-29
     * @Param
     * 1、根据区域id，设备类型,监狱编号 获取此区域下的所有点位
     * 2、是否显示子区域下的所有点位 0、不显示1、显示
     * */
    public List<PointVo> getAllPointByObj(PointVo pointVo) {
        return getAllPointByGrandAndType(pointVo.getAlpCusNumber(),pointVo.getAlpGrandId(),pointVo.getAlpDeviceType(),pointVo.getIfShowChild());
    }


    /**
     * 分页查询监舍信息
     * @param pageRequest
     * @param pointVo
     * @return
     */
    public Page<PointVo> findByDormPage(PageRequest pageRequest, PointVo pointVo) {
        return pointapper.findByDormPage(pageRequest,pointVo);
    }
}
