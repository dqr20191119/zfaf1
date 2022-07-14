package com.cesgroup.prison.screen.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName ScreenPlanNewEntity
 * @Description 电视墙预案
 * @Author lh
 * @Date 2020/7/1 17:23
 **/
@Entity
@Table(name = "CDS_SCREEN_PLAN_NEW")
public class ScreenPlanNewEntity extends StringIDEntity {

    private static final long serialVersionUID = 6393287474471729585L;

    /**
     * 电视墙预案名称
     */
    private String  screenPlanName;
    /**
     * 电视墙id
     */
    private String tywallId;
    /**
     * 电视墙名称
     */
    private String tywallName;
    /**
     * 监狱编号
     */
    private String cusNumber;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 预案当前使用状态  0.未使用 1.使用中
     */
    private String status;



    public String getScreenPlanName() {
        return screenPlanName;
    }

    public void setScreenPlanName(String screenPlanName) {
        this.screenPlanName = screenPlanName;
    }

    public String getTywallId() {
        return tywallId;
    }

    public void setTywallId(String tywallId) {
        this.tywallId = tywallId;
    }

    public String getTywallName() {
        return tywallName;
    }

    public void setTywallName(String tywallName) {
        this.tywallName = tywallName;
    }

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
