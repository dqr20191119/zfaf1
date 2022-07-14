package com.cesgroup.prison.zbgl.mbsz.dto;

/**
 *@ClassName DutyPoliceDto
 *@Description TODO
 *@Author lh
 *@Date 2020/8/30 20:59
 *
 **/
public class DutyPoliceDto {

    private String dutyDate;
    private String week;
    private String zhz;
    private String zZbz;
    private String zZby;
    private String zhZbz;
    private String zhZby;
    private String wZbz;
    private String wZby;

    public String getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(String dutyDate) {
        this.dutyDate = dutyDate;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getZhz() {
        return zhz;
    }

    public void setZhz(String zhz) {
        this.zhz = zhz;
    }

    public String getzZbz() {
        return zZbz;
    }

    public void setzZbz(String zZbz) {
        this.zZbz = zZbz;
    }

    public String getzZby() {
        return zZby;
    }

    public void setzZby(String zZby) {
        this.zZby = zZby;
    }

    public String getZhZbz() {
        return zhZbz;
    }

    public void setZhZbz(String zhZbz) {
        this.zhZbz = zhZbz;
    }

    public String getZhZby() {
        return zhZby;
    }

    public void setZhZby(String zhZby) {
        this.zhZby = zhZby;
    }

    public String getwZbz() {
        return wZbz;
    }

    public void setwZbz(String wZbz) {
        this.wZbz = wZbz;
    }

    public String getwZby() {
        return wZby;
    }

    public void setwZby(String wZby) {
        this.wZby = wZby;
    }


    @Override
    public String toString() {
        return "DutyPoliceDto{" +
                "dutyDate='" + dutyDate + '\'' +
                ", week='" + week + '\'' +
                ", zhz='" + zhz + '\'' +
                ", zZbz='" + zZbz + '\'' +
                ", zZby='" + zZby + '\'' +
                ", zhZbz='" + zhZbz + '\'' +
                ", zhZby='" + zhZby + '\'' +
                ", wZbz='" + wZbz + '\'' +
                ", wZby='" + wZby + '\'' +
                '}';
    }
}
