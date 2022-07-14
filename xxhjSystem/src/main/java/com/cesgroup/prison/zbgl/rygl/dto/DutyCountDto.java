package com.cesgroup.prison.zbgl.rygl.dto;

import java.io.Serializable;

/**
 *@ClassName DutyCountDto
 *@Description TODO
 *@Author lh
 *@Date 2020/8/24 18:22
 *
 **/
public class DutyCountDto implements Serializable {

    private static final long serialVersionUID = -7255207407482708135L;



    private String cusNumber;

    private String id;

    private String name;
    /**
     * 全年值班数
     */
    private Integer yearCount;
    /**
     * 中班值班数
     */
    private Integer middleCount;
    /**
     * 工作日值班数
     */
    private Integer workDayCount;
    /**
     * 节假日
     */
    private Integer HolidaysCount;
    /**
     * 晚班值班数
     */
    private Integer nightCount;

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Integer getYearCount() {
        return yearCount;
    }

    public void setYearCount(Integer yearCount) {
        this.yearCount = yearCount;
    }

    public Integer getMiddleCount() {
        return middleCount;
    }

    public void setMiddleCount(Integer middleCount) {
        this.middleCount = middleCount;
    }

    public Integer getWorkDayCount() {
        return workDayCount;
    }

    public void setWorkDayCount(Integer workDayCount) {
        this.workDayCount = workDayCount;
    }

    public Integer getHolidaysCount() {
        return HolidaysCount;
    }

    public void setHolidaysCount(Integer holidaysCount) {
        HolidaysCount = holidaysCount;
    }

    public Integer getNightCount() {
        return nightCount;
    }

    public void setNightCount(Integer nightCount) {
        this.nightCount = nightCount;
    }


    @Override
    public String toString() {
        return "DutyCountDto{" +
                "cusNumber=" + cusNumber +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", yearCount=" + yearCount +
                ", middleCount=" + middleCount +
                ", workDayCount=" + workDayCount +
                ", HolidaysCount=" + HolidaysCount +
                ", nightCount=" + nightCount +
                '}';
    }
}
