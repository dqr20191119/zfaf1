package com.cesgroup.prison.alarm.record.enums;

public enum AlarmLevelEnum {

    YJ("一级", "1"),
    EJ("二级", "2"),
    SJ("三级", "3");

    private String status;

    private String number;

    public String getStatus() {
        return status;
    }

    public String getNumber() {
        return number;
    }

    AlarmLevelEnum(String status, String number) {
        this.status = status;
        this.number = number;
    }

    public static String getStatusEnum(String number) {
        String status = "二级";
        for (AlarmLevelEnum statusEnum : values()) {
            if (statusEnum.getNumber().equals(number)) {
                status = statusEnum.getStatus();
            }
        }
        return status;
    }
}
