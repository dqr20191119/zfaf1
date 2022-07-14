package com.cesgroup.prison.alarm.record.enums;

public enum AlarmTypeEnum {

    RGBJ("人工报警", "0"),
    WLBJQ("网络报警器", "1"),
    GYDW("高压电网", "2"),
    ZJHW("周界红外", "3"),
    MD("门灯", "4"),
    SFW("蛇腹网", "5"),
    SXJ("摄像机", "6"),
    XFSB("消防设备", "7"),
    ZJGQ("周界光纤", "8"),
    DJSB("对讲设备", "9"),
    DZWL("电子围栏", "10"),
    QYRQ("区域入侵", "11"),
    XWFX("行为分析", "12"),
    IOBJ("IO报警", "13"),
    JJBJ("尖叫报警", "14"),
    YJGJ("一键告警", "21"),
    FKZL("访客滞留", "22"),
    YYTL("押运脱离", "23"),
    ZDQYGJ("重点区域告警", "24");

    private String status;

    private String number;

    public String getStatus() {
        return status;
    }

    public String getNumber() {
        return number;
    }

    AlarmTypeEnum(String status, String number) {
        this.status = status;
        this.number = number;
    }

    public static String getStatusEnum(String number) {
        String status = "人工报警";
        for (AlarmTypeEnum statusEnum : values()) {
            if (statusEnum.getNumber().equals(number)) {
                status = statusEnum.getStatus();
            }
        }
        return status;
    }
}
