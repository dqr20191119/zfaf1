package com.cesgroup.prison.zbgl.rygl.dto;

import java.io.Serializable;

public class JyzzxxDto implements Serializable {

    private static final long serialVersionUID = 7571422827817472993L;
    private String cusNumber;
    private String cusName;

    public String getCusNumber() {
        return cusNumber;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }
}
