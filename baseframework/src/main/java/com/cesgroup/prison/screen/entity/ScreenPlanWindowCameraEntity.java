package com.cesgroup.prison.screen.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName ScreenPlanWindowCameraEntity
 * @Description 电视墙预案窗口关联摄像头表
 * @Author lh
 * @Date 2020/7/2 10:41
 **/
@Entity
@Table(name = "CDS_SCREEN_PLAN_WINDOW_CAMERA")
public class ScreenPlanWindowCameraEntity extends StringIDEntity {

    private static final long serialVersionUID = 2845628706363454171L;

    /**
     * 电视墙id
     */
    private String tvwallId;


    /**
     * 电视墙预案id
     */
    private String screenPlanId;
    /**
     * 窗口id
     */
    private String windowId;
    /**
     * 摄像头id
     */
    private String cameraId;
    /**
     * 区域id
     */
    private String areaId;
    /**
     * 摄像头名称
     */
    private String cameraName;
    /**
     * 排序
     */
    private String sqlNum;
    /**
     * 摄像头所在平台索引
     */
    private String cameraPlatformIdnty;
    /**
     * 创建时间
     */
    private String creatTime;
    /**
     * 创建人id
     */
    private String creatId;
    /**
     * 创建人姓名
     */
    private String creatName;
    /**
     * 更新人id
     */
    private String updateId;
    /**
     * 更新人姓名
     */
    private String updateName;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 监狱编号
     */
    private String cusNumber;


    public String getTvwallId() {
        return tvwallId;
    }

    public void setTvwallId(String tvwallId) {
        this.tvwallId = tvwallId;
    }

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getScreenPlanId() {
        return screenPlanId;
    }

    public void setScreenPlanId(String screenPlanId) {
        this.screenPlanId = screenPlanId;
    }

    public String getWindowId() {
        return windowId;
    }

    public void setWindowId(String windowId) {
        this.windowId = windowId;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getSqlNum() {
        return sqlNum;
    }

    public void setSqlNum(String sqlNum) {
        this.sqlNum = sqlNum;
    }

    public String getCameraPlatformIdnty() {
        return cameraPlatformIdnty;
    }

    public void setCameraPlatformIdnty(String cameraPlatformIdnty) {
        this.cameraPlatformIdnty = cameraPlatformIdnty;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCreatId() {
        return creatId;
    }

    public void setCreatId(String creatId) {
        this.creatId = creatId;
    }

    public String getCreatName() {
        return creatName;
    }

    public void setCreatName(String creatName) {
        this.creatName = creatName;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
