package com.cesgroup.prison.foreignerPeos.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
*外来人员登记
*/
@Entity
@Table(name = "T_OUTSIDER_WLRY")
public class ForeignerPeosEntity extends  StringIDEntity {
    private String id;

    private String cusNumber;

    private String cName;

    private String cSex;

    private String cSfzbh;

    private String dCsrq;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String dDjrq;

    private String cSy;

    private String cCph;

    private String cCxys;

    private String cZp;

    private String cSpld;

    private String cCzry;

    private String cCzryjh;

    private String cBm;

    private String cQy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber == null ? null : cusNumber.trim();
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcSex() {
        return cSex;
    }

    public void setcSex(String cSex) {
        this.cSex = cSex == null ? null : cSex.trim();
    }

    public String getcSfzbh() {
        return cSfzbh;
    }

    public void setcSfzbh(String cSfzbh) {
        this.cSfzbh = cSfzbh == null ? null : cSfzbh.trim();
    }

    public String getdCsrq() {
        return dCsrq;
    }

    public void setdCsrq(String dCsrq) {
        this.dCsrq = dCsrq == null ? null : dCsrq.trim();
    }

    public String getdDjrq() {
        return dDjrq;
    }

    public void setdDjrq(String dDjrq) {
        this.dDjrq = dDjrq == null ? null : dDjrq.trim();
    }

    public String getcSy() {
        return cSy;
    }

    public void setcSy(String cSy) {
        this.cSy = cSy == null ? null : cSy.trim();
    }

    public String getcCph() {
        return cCph;
    }

    public void setcCph(String cCph) {
        this.cCph = cCph == null ? null : cCph.trim();
    }

    public String getcCxys() {
        return cCxys;
    }

    public void setcCxys(String cCxys) {
        this.cCxys = cCxys == null ? null : cCxys.trim();
    }

    public String getcZp() {
        return cZp;
    }

    public void setcZp(String cZp) {
        this.cZp = cZp == null ? null : cZp.trim();
    }

    public String getcSpld() {
        return cSpld;
    }

    public void setcSpld(String cSpld) {
        this.cSpld = cSpld == null ? null : cSpld.trim();
    }

    public String getcCzry() {
        return cCzry;
    }

    public void setcCzry(String cCzry) {
        this.cCzry = cCzry == null ? null : cCzry.trim();
    }

    public String getcCzryjh() {
        return cCzryjh;
    }

    public void setcCzryjh(String cCzryjh) {
        this.cCzryjh = cCzryjh == null ? null : cCzryjh.trim();
    }

    public String getcBm() {
        return cBm;
    }

    public void setcBm(String cBm) {
        this.cBm = cBm == null ? null : cBm.trim();
    }

    public String getcQy() {
        return cQy;
    }

    public void setcQy(String cQy) {
        this.cQy = cQy == null ? null : cQy.trim();
    }
}