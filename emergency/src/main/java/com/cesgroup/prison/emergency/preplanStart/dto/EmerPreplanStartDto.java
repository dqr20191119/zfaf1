package com.cesgroup.prison.emergency.preplanStart.dto;

import java.io.Serializable;

public class EmerPreplanStartDto implements Serializable {
    /**
     * 监狱/单位编号
     */
    private String cusNumber;
    /**
     * 应急预案编号
     */
    private String preplanId;
    /**
     * 应急预案名称
     */
    private String preplanName;

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getPreplanId() {
        return preplanId;
    }

    public void setPreplanId(String preplanId) {
        this.preplanId = preplanId;
    }

    public String getPreplanName() {
        return preplanName;
    }

    public void setPreplanName(String preplanName) {
        this.preplanName = preplanName;
    }
}
