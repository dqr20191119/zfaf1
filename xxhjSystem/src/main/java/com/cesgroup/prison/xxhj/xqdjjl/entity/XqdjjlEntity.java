package com.cesgroup.prison.xxhj.xqdjjl.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

public class XqdjjlEntity extends  StringIDEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String id;

    private String name;

    private String peoNum;

    private String prisonNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPeoNum() {
        return peoNum;
    }

    public void setPeoNum(String peoNum) {
        this.peoNum = peoNum == null ? null : peoNum.trim();
    }

    public String getPrisonNum() {
        return prisonNum;
    }

    public void setPrisonNum(String prisonNum) {
        this.prisonNum = prisonNum == null ? null : prisonNum.trim();
    }

    public Date getStTime() {
        return stTime;
    }

    public void setStTime(Date stTime) {
        this.stTime = stTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}