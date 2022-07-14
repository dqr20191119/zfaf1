package com.cesgroup.prison.viewPeople.vo;

import com.cesgroup.prison.viewPeople.entity.ViewPeople;

public class ViewPeopleVo extends ViewPeople {
    private String vprDprtmntId;
    private String vprDprtmntName;

    public String getVprDprtmntId() {
        return vprDprtmntId;
    }

    public void setVprDprtmntId(String vprDprtmntId) {
        this.vprDprtmntId = vprDprtmntId;
    }

    public String getVprDprtmntName() {
        return vprDprtmntName;
    }

    public void setVprDprtmntName(String vprDprtmntName) {
        this.vprDprtmntName = vprDprtmntName;
    }
}
