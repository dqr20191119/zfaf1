package com.cesgroup.prison.zfjcj.vo;

import com.cesgroup.prison.zfjcj.entity.ZfjcjEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class ZfjcjVo extends ZfjcjEntity {
    private String jyid;

    private String zfbh;

    private String zfxm;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String ljrq;

    private String ljlx;

    private String bgmj;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String pirStartTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String pirEndTime;

    public String getZfbh() {
        return zfbh;
    }

    public void setZfbh(String zfbh) {
        this.zfbh = zfbh;
    }

    public String getZfxm() {
        return zfxm;
    }

    public void setZfxm(String zfxm) {
        this.zfxm = zfxm;
    }

    public String getLjrq() {
        return ljrq;
    }

    public void setLjrq(String ljrq) {
        this.ljrq = ljrq;
    }

    public String getLjlx() {
        return ljlx;
    }

    public void setLjlx(String ljlx) {
        this.ljlx = ljlx;
    }

    public String getBgmj() {
        return bgmj;
    }

    public void setBgmj(String bgmj) {
        this.bgmj = bgmj;
    }

    public String getPirStartTime() {
        return pirStartTime;
    }

    public void setPirStartTime(String pirStartTime) {
        this.pirStartTime = pirStartTime;
    }

    public String getPirEndTime() {
        return pirEndTime;
    }

    public void setPirEndTime(String pirEndTime) {
        this.pirEndTime = pirEndTime;
    }

    public String getJyid() {
        return jyid;
    }

    public void setJyid(String jyid) {
        this.jyid = jyid;
    }
}
