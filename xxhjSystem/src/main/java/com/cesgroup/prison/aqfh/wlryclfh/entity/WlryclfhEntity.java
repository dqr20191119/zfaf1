package com.cesgroup.prison.aqfh.wlryclfh.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName WlryclfhEntity
 * @Description TODO
 * @Author lh
 * @Date 2020/6/10 14:02
 **/
@Table(name = "CDS_AQFH_WLRYCLFH_BASE")
@Entity
public class WlryclfhEntity  extends StringIDEntity {


    private static final long serialVersionUID = 5312595047702493376L;
    private String cusNumber;
    /**
     * 带领民警id
     */
    private String dlmjId ;
    /**
     * 带领民警姓名
     */
    private String dlmjName ;
    /**
     * 进监事由
     */
    private String jjsy ;
    /**
     * 进监时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jjsj ;
    /**
     * 复核状态 0.未复核 1.已复核
     */
    private String zt ;
    /**
     * 类型 1.外来人员 2.外来车辆
     */
    private String lx ;
    /**
     * 离开时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lksj ;
    private String url ;
    /**
     * 复核时间
     */
    private String fhsj ;
    /**
     * 复核人id
     */
    private String fhmjId ;
    /**
     * 复核人姓名
     */
    private String fhmjName ;

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getDlmjId() {
        return dlmjId;
    }

    public String getDlmjName() {
        return dlmjName;
    }

    public String getJjsy() {
        return jjsy;
    }

    public Date getJjsj() {
        return jjsj;
    }

    public String getZt() {
        return zt;
    }

    public String getLx() {
        return lx;
    }

    public Date getLksj() {
        return lksj;
    }

    public String getUrl() {
        return url;
    }

    public String getFhsj() {
        return fhsj;
    }

    public String getFhmjId() {
        return fhmjId;
    }

    public String getFhmjName() {
        return fhmjName;
    }

    public void setDlmjId(String dlmjId) {
        this.dlmjId = dlmjId;
    }

    public void setDlmjName(String dlmjName) {
        this.dlmjName = dlmjName;
    }

    public void setJjsy(String jjsy) {
        this.jjsy = jjsy;
    }

    public void setJjsj(Date jjsj) {
        this.jjsj = jjsj;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public void setLksj(Date lksj) {
        this.lksj = lksj;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFhsj(String fhsj) {
        this.fhsj = fhsj;
    }

    public void setFhmjId(String fhmjId) {
        this.fhmjId = fhmjId;
    }

    public void setFhmjName(String fhmjName) {
        this.fhmjName = fhmjName;
    }
}


