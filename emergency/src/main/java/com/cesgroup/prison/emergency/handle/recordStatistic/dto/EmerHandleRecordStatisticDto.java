package com.cesgroup.prison.emergency.handle.recordStatistic.dto;

import java.io.Serializable;

public class EmerHandleRecordStatisticDto implements Serializable {
    /**
     * 监狱/单位编号
     */
    private String cusNumber;
    /**
     * 应急预案名称
     */
    private String preplanName;
    /**
     * 应急处置记录的数量
     */
    private Integer counts;

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getPreplanName() {
        return preplanName;
    }

    public void setPreplanName(String preplanName) {
        this.preplanName = preplanName;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }
}
