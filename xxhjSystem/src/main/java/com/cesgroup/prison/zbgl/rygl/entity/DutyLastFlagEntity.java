package com.cesgroup.prison.zbgl.rygl.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Table;
import java.util.Date;

/**
 *@ClassName DutyLastFlagEntity
 *@Description 省局值班最后值班标记表
 *@Author lh
 *@Date 2020/11/26 16:22
 *
 **/
@Table(name = "CDS_DUTY_LAST_FLAG")
public class DutyLastFlagEntity extends StringIDEntity {

    /**
     * 指挥长最后值班人员id
     */
    private String zhzId;
    /**
     * 指挥长值中班最后值班人员id
     */
    private String zhzMidId;
    /**
     * 女值班长节假日最后值班人员id
     */
    private String wzbzHId;
    /**
     * 女值班长工作日最后值班人员id
     */
    private String wzbzWId;
    /**
     * 女值班员节假日最后值班人员id
     */
    private String wzbyHId;
    /**
     * 女值班员工作日最后值班人员id
     */
    private String wzbyWId;
    /**
     * 男值班长节假日最后值班人员id
     */
    private String mzbzHId;
    /**
     * 男值班长工作日中班最后值班人员id
     */
    private String mzbzWMidId;
    /**
     * 男值班长工作日晚班最后值班人员id
     */
    private String mzbzWNightId;
    /**
     * 男值班员节假日最后值班人员
     */
    private String mzbyHId;
    /**
     * 男值班员工作日最后值班人员
     */
    private String mzbyWId;
    /**
     *保存状态:0未保存1.已保存
     */
    private String saveZt;
    /**
     * 工作日
     */
    private String workday;
    /**
     * 节假日
     */
    private String holiday;
    /**
     * 更新日期
     */
    private Date gxDate;
    /**
     * 更新人id
     */
    private String gxrId;
    /**
     * 更新人
     */
    private String gxr;
    private String cusNumber;

    /**
     * 女值班长法定节假日最后值班人员id
     */
    private String wzbzFId;
    /**
     * 女值班员法定节假日最后值班人员id
     */
    private String wzbyFId;
    /**
     * 男值班长工作日最后值班人员id
     */
    private String mzbzWId;
    /**
     * 男值班员法定节假日最后值班人员id
     */
    private String mzbzFId;
    /**
     * 男值班员法定节假日最后值班人员id
     */
    private String mzbyFId;



    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getZhzId() {
        return zhzId;
    }

    public void setZhzId(String zhzId) {
        this.zhzId = zhzId;
    }

    public String getZhzMidId() {
        return zhzMidId;
    }

    public void setZhzMidId(String zhzMidId) {
        this.zhzMidId = zhzMidId;
    }

    public String getWzbzHId() {
        return wzbzHId;
    }

    public void setWzbzHId(String wzbzHId) {
        this.wzbzHId = wzbzHId;
    }

    public String getWzbzWId() {
        return wzbzWId;
    }

    public void setWzbzWId(String wzbzWId) {
        this.wzbzWId = wzbzWId;
    }

    public String getWzbyHId() {
        return wzbyHId;
    }

    public void setWzbyHId(String wzbyHId) {
        this.wzbyHId = wzbyHId;
    }

    public String getWzbyWId() {
        return wzbyWId;
    }

    public void setWzbyWId(String wzbyWId) {
        this.wzbyWId = wzbyWId;
    }

    public String getMzbzHId() {
        return mzbzHId;
    }

    public void setMzbzHId(String mzbzHId) {
        this.mzbzHId = mzbzHId;
    }

    public String getMzbzWMidId() {
        return mzbzWMidId;
    }

    public void setMzbzWMidId(String mzbzWMidId) {
        this.mzbzWMidId = mzbzWMidId;
    }

    public String getMzbzWNightId() {
        return mzbzWNightId;
    }

    public void setMzbzWNightId(String mzbzWNightId) {
        this.mzbzWNightId = mzbzWNightId;
    }

    public String getMzbyHId() {
        return mzbyHId;
    }

    public void setMzbyHId(String mzbyHId) {
        this.mzbyHId = mzbyHId;
    }

    public String getMzbyWId() {
        return mzbyWId;
    }

    public void setMzbyWId(String mzbyWId) {
        this.mzbyWId = mzbyWId;
    }

    public String getSaveZt() {
        return saveZt;
    }

    public void setSaveZt(String saveZt) {
        this.saveZt = saveZt;
    }

    public String getWorkday() {
        return workday;
    }

    public void setWorkday(String workday) {
        this.workday = workday;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public Date getGxDate() {
        return gxDate;
    }

    public void setGxDate(Date gxDate) {
        this.gxDate = gxDate;
    }

    public String getGxrId() {
        return gxrId;
    }

    public void setGxrId(String gxrId) {
        this.gxrId = gxrId;
    }

    public String getGxr() {
        return gxr;
    }

    public void setGxr(String gxr) {
        this.gxr = gxr;
    }

    public String getWzbzFId() {
        return wzbzFId;
    }

    public void setWzbzFId(String wzbzFId) {
        this.wzbzFId = wzbzFId;
    }

    public String getWzbyFId() {
        return wzbyFId;
    }

    public void setWzbyFId(String wzbyFId) {
        this.wzbyFId = wzbyFId;
    }

    public String getMzbzWId() {
        return mzbzWId;
    }

    public void setMzbzWId(String mzbzWId) {
        this.mzbzWId = mzbzWId;
    }

    public String getMzbzFId() {
        return mzbzFId;
    }

    public void setMzbzFId(String mzbzFId) {
        this.mzbzFId = mzbzFId;
    }

    public String getMzbyFId() {
        return mzbyFId;
    }

    public void setMzbyFId(String mzbyFId) {
        this.mzbyFId = mzbyFId;
    }
}
