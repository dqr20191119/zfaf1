package com.cesgroup.prison.zbgl.rygl.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Table;

/**
 *@ClassName DutyFlagEntity
 *@Description 值班初始标记表
 *@Author lh
 *@Date 2020/8/20 11:49
 *
 **/

@Table(name = "CDS_DUTY_FLAG")
public class DutyFlagEntity extends StringIDEntity {


    /**
     * 机构号
     */
    private String cusNumber;
    /**
     * 指挥长标记 存人员id 其他一样
     */
    private String zhzFlag;
    /**
     * 女值班长类别工作日最后的值班人员id
     */
    private String wzbzWFlag;

    /**
     * 女值班长类别节假日最后的值班人员id
     */
    private String wzbzHFlag;

    /**
     * 女值班员类别工作日最后的值班人员id
     */
    private String wzbyWFlag;

    /**
     * 女值班员类别节假日最后的值班人员id
     */
    private String wzbyHFlag;

    /**
     * 男值班长类别工作日最后的值班人员id
     */
    private String mzbzWFlag;

    /**
     * 男值班长类别节假日最后的值班人员id
     */
    private String mzbzHFlag;

    /**
     * 男值班员类别工作日最后的值班人员id
     */
    private String mzbyWFlag;

    /**
     * 男值班员类别节假日最后的值班人员id
     */
    private String mzbyHFlag;

    /**
     * 指挥长值中班值班长的人员id
     */
    private String zhzZbFlag;

    /**
     * 指挥长值班的最后标记时间
     */
    private String zhzFlagDutyDate;
    /**
     * 女值班长值班的最后标记时间
     */
    private String wzbzFlagDutyDate;
    /**
     * 女值班员值班的最后标记时间
     */
    private String wzbyFlagDutyDate;
    /**
     * 男值班长的最后标记时间
     */
    private String mzbzFlagDutyDate;
    /**
     * 男值班员的最后标记时间
     */
    private String mzbyFlagDutyDate;
    /**
     * 指挥长值中班值班长的最后标记时间
     */
    private String zhzZbFlagDutyDate;
    /**
     * 男值班长工作日值班晚的最后标记人员
     */
    private String mzbzWNFlag;

    public String getMzbzWNFlag() {
        return mzbzWNFlag;
    }

    public void setMzbzWNFlag(String mzbzWNFlag) {
        this.mzbzWNFlag = mzbzWNFlag;
    }

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getZhzFlag() {
        return zhzFlag;
    }

    public void setZhzFlag(String zhzFlag) {
        this.zhzFlag = zhzFlag;
    }

    public String getWzbzWFlag() {
        return wzbzWFlag;
    }

    public void setWzbzWFlag(String wzbzWFlag) {
        this.wzbzWFlag = wzbzWFlag;
    }

    public String getWzbzHFlag() {
        return wzbzHFlag;
    }

    public void setWzbzHFlag(String wzbzHFlag) {
        this.wzbzHFlag = wzbzHFlag;
    }

    public String getWzbyWFlag() {
        return wzbyWFlag;
    }

    public void setWzbyWFlag(String wzbyWFlag) {
        this.wzbyWFlag = wzbyWFlag;
    }

    public String getWzbyHFlag() {
        return wzbyHFlag;
    }

    public void setWzbyHFlag(String wzbyHFlag) {
        this.wzbyHFlag = wzbyHFlag;
    }

    public String getMzbzWFlag() {
        return mzbzWFlag;
    }

    public void setMzbzWFlag(String mzbzWFlag) {
        this.mzbzWFlag = mzbzWFlag;
    }

    public String getMzbzHFlag() {
        return mzbzHFlag;
    }

    public void setMzbzHFlag(String mzbzHFlag) {
        this.mzbzHFlag = mzbzHFlag;
    }

    public String getMzbyWFlag() {
        return mzbyWFlag;
    }

    public void setMzbyWFlag(String mzbyWFlag) {
        this.mzbyWFlag = mzbyWFlag;
    }

    public String getMzbyHFlag() {
        return mzbyHFlag;
    }

    public void setMzbyHFlag(String mzbyHFlag) {
        this.mzbyHFlag = mzbyHFlag;
    }

    public String getZhzZbFlag() {
        return zhzZbFlag;
    }

    public void setZhzZbFlag(String zhzZbFlag) {
        this.zhzZbFlag = zhzZbFlag;
    }

    public String getZhzFlagDutyDate() {
        return zhzFlagDutyDate;
    }

    public void setZhzFlagDutyDate(String zhzFlagDutyDate) {
        this.zhzFlagDutyDate = zhzFlagDutyDate;
    }

    public String getWzbzFlagDutyDate() {
        return wzbzFlagDutyDate;
    }

    public void setWzbzFlagDutyDate(String wzbzFlagDutyDate) {
        this.wzbzFlagDutyDate = wzbzFlagDutyDate;
    }

    public String getWzbyFlagDutyDate() {
        return wzbyFlagDutyDate;
    }

    public void setWzbyFlagDutyDate(String wzbyFlagDutyDate) {
        this.wzbyFlagDutyDate = wzbyFlagDutyDate;
    }

    public String getMzbzFlagDutyDate() {
        return mzbzFlagDutyDate;
    }

    public void setMzbzFlagDutyDate(String mzbzFlagDutyDate) {
        this.mzbzFlagDutyDate = mzbzFlagDutyDate;
    }

    public String getMzbyFlagDutyDate() {
        return mzbyFlagDutyDate;
    }

    public void setMzbyFlagDutyDate(String mzbyFlagDutyDate) {
        this.mzbyFlagDutyDate = mzbyFlagDutyDate;
    }

    public String getZhzZbFlagDutyDate() {
        return zhzZbFlagDutyDate;
    }

    public void setZhzZbFlagDutyDate(String zhzZbFlagDutyDate) {
        this.zhzZbFlagDutyDate = zhzZbFlagDutyDate;
    }
}
