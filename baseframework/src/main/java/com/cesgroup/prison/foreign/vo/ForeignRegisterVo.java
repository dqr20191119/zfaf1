package com.cesgroup.prison.foreign.vo;

import com.cesgroup.prison.foreign.entity.ForeignCarDtls;
import com.cesgroup.prison.foreign.entity.ForeignPeopleDtls;
import com.cesgroup.prison.foreign.entity.ForeignRegister;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ForeignRegisterVo extends ForeignRegister {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private List<ForeignPeopleDtls> foreignPeopleDtlsList;


    private List<ForeignCarDtls> foreignCarDtlsList;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date frTimeStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date frTimeEnd;


    public List<ForeignPeopleDtls> getForeignPeopleDtlsList() {
        return foreignPeopleDtlsList;
    }

    public void setForeignPeopleDtlsList(List<ForeignPeopleDtls> foreignPeopleDtlsList) {
        this.foreignPeopleDtlsList = foreignPeopleDtlsList;
    }

    public List<ForeignCarDtls> getForeignCarDtlsList() {
        return foreignCarDtlsList;
    }

    public void setForeignCarDtlsList(List<ForeignCarDtls> foreignCarDtlsList) {
        this.foreignCarDtlsList = foreignCarDtlsList;
    }

    public Date getFrTimeStart() {
        return frTimeStart;
    }

    public void setFrTimeStart(Date frTimeStart) {
        this.frTimeStart = frTimeStart;
    }

    public Date getFrTimeEnd() {
        return frTimeEnd;
    }

    public void setFrTimeEnd(Date frTimeEnd) {
        this.frTimeEnd = frTimeEnd;
    }
}
