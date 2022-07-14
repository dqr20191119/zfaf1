package com.cesgroup.prison.screen.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName ScreenPlanWindowNewEntity
 * @Description 电视墙预案关联窗口
 * @Author lh
 * @Date 2020/7/3 16:36
 **/
@Entity
@Table(name = "CDS_SCREEN_PLAN_WINDOW")
public class ScreenPlanWindowNewEntity extends StringIDEntity {

    private static final long serialVersionUID = -4921312606458392867L;
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
    private String creatTime;
    private String cusNumber;

    public String getTvwallId() {
        return tvwallId;
    }

    public void setTvwallId(String tvwallId) {
        this.tvwallId = tvwallId;
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

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }
}
