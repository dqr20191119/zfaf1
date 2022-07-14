package com.cesgroup.prison.alarm.flow.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @ClassName FlowDtlsAlarmRecordEntity
 * @Description 报警流程详情关联报警记录表
 * @Author lh
 * @Date 2020/6/17 16:51
 **/
@Entity
@Table(name = "CDS_HANDLE_FLOW_DTLS_ALARMRECORD")
public class FlowDtlsAlarmRecordEntity extends StringIDEntity {


    private static final long serialVersionUID = 3905411881494450732L;
    private String id;
    /**
     * 报警流程id
     */
    private String hfdaFlowId;
    /**
     * 报警流程详情id
     */
    private String hfdaFlowDtlsId;
    /**
     * 报警记录id
     */
    private String hfdaAlertRecordDtlsId;
    /**
     * 现场情况
     */
    private String hfdaXcqk;
    /**
     * 处置情况
     */
    private String hfdaCzqk;
    /**
     * 处置结果
     */
    private String hfdaCzjg;
    /**
     * 备注
     */
    private String hfdaBz;
    /**
     * 更新时间
     */
    private String hfdaUpdateTime;
    /**
     * 更新人id
     */
    private String hfdaUpdateId;
    /**
     * 更新人姓名
     */
    private String hfdaUpdateName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHfdaFlowId() {
        return hfdaFlowId;
    }

    public void setHfdaFlowId(String hfdaFlowId) {
        this.hfdaFlowId = hfdaFlowId;
    }

    public String getHfdaFlowDtlsId() {
        return hfdaFlowDtlsId;
    }

    public void setHfdaFlowDtlsId(String hfdaFlowDtlsId) {
        this.hfdaFlowDtlsId = hfdaFlowDtlsId;
    }

    public String getHfdaAlertRecordDtlsId() {
        return hfdaAlertRecordDtlsId;
    }

    public void setHfdaAlertRecordDtlsId(String hfdaAlertRecordDtlsId) {
        this.hfdaAlertRecordDtlsId = hfdaAlertRecordDtlsId;
    }

    public String getHfdaXcqk() {
        return hfdaXcqk;
    }

    public void setHfdaXcqk(String hfdaXcqk) {
        this.hfdaXcqk = hfdaXcqk;
    }

    public String getHfdaCzqk() {
        return hfdaCzqk;
    }

    public void setHfdaCzqk(String hfdaCzqk) {
        this.hfdaCzqk = hfdaCzqk;
    }

    public String getHfdaCzjg() {
        return hfdaCzjg;
    }

    public void setHfdaCzjg(String hfdaCzjg) {
        this.hfdaCzjg = hfdaCzjg;
    }

    public String getHfdaBz() {
        return hfdaBz;
    }

    public void setHfdaBz(String hfdaBz) {
        this.hfdaBz = hfdaBz;
    }

    public String getHfdaUpdateTime() {
        return hfdaUpdateTime;
    }

    public void setHfdaUpdateTime(String hfdaUpdateTime) {
        this.hfdaUpdateTime = hfdaUpdateTime;
    }

    public String getHfdaUpdateId() {
        return hfdaUpdateId;
    }

    public void setHfdaUpdateId(String hfdaUpdateId) {
        this.hfdaUpdateId = hfdaUpdateId;
    }

    public String getHfdaUpdateName() {
        return hfdaUpdateName;
    }

    public void setHfdaUpdateName(String hfdaUpdateName) {
        this.hfdaUpdateName = hfdaUpdateName;
    }
}
