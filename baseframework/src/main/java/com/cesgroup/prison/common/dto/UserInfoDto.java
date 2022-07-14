package com.cesgroup.prison.common.dto;

import java.io.Serializable;

/**
 * 用户信息DTO
 */
public class UserInfoDto implements Serializable {
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户所属机构编码
     */
    private String userOrgCode;
    /**
     * 用户所属机构名称
     */
    private String userOrgName;
    /**
     * 用户所属单位编码
     */
    private String userUnitCode;
    /**
     * 用户所属单位名称
     */
    private String userUnitName;
    /**
     * 警务通
     */
    private String policeAffair;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserOrgCode() {
        return userOrgCode;
    }

    public void setUserOrgCode(String userOrgCode) {
        this.userOrgCode = userOrgCode;
    }

    public String getUserOrgName() {
        return userOrgName;
    }

    public void setUserOrgName(String userOrgName) {
        this.userOrgName = userOrgName;
    }

    public String getUserUnitCode() {
        return userUnitCode;
    }

    public void setUserUnitCode(String userUnitCode) {
        this.userUnitCode = userUnitCode;
    }

    public String getUserUnitName() {
        return userUnitName;
    }

    public void setUserUnitName(String userUnitName) {
        this.userUnitName = userUnitName;
    }

    public String getPoliceAffair() {
        return policeAffair;
    }

    public void setPoliceAffair(String policeAffair) {
        this.policeAffair = policeAffair;
    }
}
