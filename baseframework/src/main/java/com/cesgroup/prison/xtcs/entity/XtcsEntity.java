package com.cesgroup.prison.xtcs.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="T_XTCS")
public class XtcsEntity extends StringIDEntity {
    /**
     * 参数编码
     */
    @NotNull
    private String csbm;
    /**
     * 参数名称
     */
    @NotNull
    private String csmc;
    /**
     * 参数值
     */
    @NotNull
    private String csz;
    /**
     * 备注说明
     */
    private String bz;
    /**
     * 数据状态(0:有效; 1:无效;)
     */
    @NotNull
    private String sjzt;
    /**
     * 数据排序
     */
    private Long sjpx;
    /**
     * 创建人ID
     */
    private String cjrid;
    /**
     * 创建人
     */
    private String cjr;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date cjrq;
    /**
     * 更新人ID
     */
    private String gxrid;
    /**
     * 更信任
     */
    private String gxr;
    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gxrq;

    public String getCsbm() {
        return csbm;
    }

    public void setCsbm(String csbm) {
        this.csbm = csbm;
    }

    public String getCsmc() {
        return csmc;
    }

    public void setCsmc(String csmc) {
        this.csmc = csmc;
    }

    public String getCsz() {
        return csz;
    }

    public void setCsz(String csz) {
        this.csz = csz;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSjzt() {
        return sjzt;
    }

    public void setSjzt(String sjzt) {
        this.sjzt = sjzt;
    }

    public Long getSjpx() {
        return sjpx;
    }

    public void setSjpx(Long sjpx) {
        this.sjpx = sjpx;
    }

    public String getCjrid() {
        return cjrid;
    }

    public void setCjrid(String cjrid) {
        this.cjrid = cjrid;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public Date getCjrq() {
        return cjrq;
    }

    public void setCjrq(Date cjrq) {
        this.cjrq = cjrq;
    }

    public String getGxrid() {
        return gxrid;
    }

    public void setGxrid(String gxrid) {
        this.gxrid = gxrid;
    }

    public String getGxr() {
        return gxr;
    }

    public void setGxr(String gxr) {
        this.gxr = gxr;
    }

    public Date getGxrq() {
        return gxrq;
    }

    public void setGxrq(Date gxrq) {
        this.gxrq = gxrq;
    }
}
