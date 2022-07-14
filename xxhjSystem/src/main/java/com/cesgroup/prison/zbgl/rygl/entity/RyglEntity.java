package com.cesgroup.prison.zbgl.rygl.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "CDS_DUTY_ZBRYGL")
public class RyglEntity extends StringIDEntity {


    private static final long serialVersionUID = -4618906037546445376L;

    /**
     * 单位编号
     */
      private String cusNumber;
    @Transient
      private String year;
    /**
     * 警察姓名
     */
    private String name;
    /**
     * 警号
     */
    private String policeBh;
    /**
     * 出生日期
     */
      private String csrq;
    /**
     * 性别 0 男 1.女
     */
    private String sex;
    /**
     * 职务级别
     */
    private String jobLevel;
    /**
     * 年龄
     */
      private String nl;
    /**
     * 职务
     */
    private String position;
    /**
     * 人员状态  1.在编2.借调 3.退休4.培训5.病假 6.其他
     */
      private String ryzt;
    /**
     * 部门编号
     */
      private String deptNumber;
      private String createTime;
      private String createName;
      private String updateTime;
      private String updateName;
      /**
       * 值班岗位
       */
      private String job;
      /**
       * 排班次数
       */
      private Integer pbCount;

    /**
     * 排班顺序
     */
    private Integer pbOrder;
    /**
     * 指挥长值中班顺序
     */
    private Integer zhzMidOrder;



    private String startDate;
    private String endDate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 借调开始日期
     */
    private String jdStartDate;
    /**
     * 借调结束日期
     */
    private String jdEndDate;





    /**
     * 全年值班数
     */
    @Transient
    private Integer yearCount;

    /**
     * 中班值班数
     */
    @Transient
    private Integer middleCount;
    /**
     * 工作日值班数
     */
    @Transient
    private Integer workDayCount;
    /**
     * 节假日
     */
    @Transient
    private Integer holidaysCount;

    /**
     * 法定节假日
     */
    @Transient
    private Integer fddayCount;

    /**
     * 晚班值班数
     */
    @Transient
    private Integer nightCount;
    /**
     * 不参与值班的情况
     */
    @Transient
    private String noDuty;
    /**
     * 插入类型 0表示不插入排序  1表示插入原排序中
     */
    @Transient
    private String insertType;
    /**
     * 值班人员id
     * 插入这个人 afterInsertId人之后
     */
    @Transient
    private String afterInsertId;

    public Integer getFddayCount() {
        return fddayCount;
    }

    public void setFddayCount(Integer fddayCount) {
        this.fddayCount = fddayCount;
    }

    public String getJdStartDate() {
        return jdStartDate;
    }

    public void setJdStartDate(String jdStartDate) {
        this.jdStartDate = jdStartDate;
    }

    public String getJdEndDate() {
        return jdEndDate;
    }

    public void setJdEndDate(String jdEndDate) {
        this.jdEndDate = jdEndDate;
    }

    public String getAfterInsertId() {
        return afterInsertId;
    }

    public void setAfterInsertId(String afterInsertId) {
        this.afterInsertId = afterInsertId;
    }

    public String getInsertType() {
        return insertType;
    }

    public void setInsertType(String insertType) {
        this.insertType = insertType;
    }

    public String getNoDuty() {
        return noDuty;
    }

    public void setNoDuty(String noDuty) {
        this.noDuty = noDuty;
    }

    public Integer getZhzMidOrder() {
        return zhzMidOrder;
    }

    public void setZhzMidOrder(Integer zhzMidOrder) {
        this.zhzMidOrder = zhzMidOrder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getYearCount() {
        return yearCount;
    }

    public void setYearCount(Integer yearCount) {
        this.yearCount = yearCount;
    }

    public Integer getMiddleCount() {
        return middleCount;
    }

    public void setMiddleCount(Integer middleCount) {
        this.middleCount = middleCount;
    }

    public Integer getWorkDayCount() {
        return workDayCount;
    }

    public void setWorkDayCount(Integer workDayCount) {
        this.workDayCount = workDayCount;
    }

    public Integer getHolidaysCount() {
        return holidaysCount;
    }

    public void setHolidaysCount(Integer holidaysCount) {
        this.holidaysCount = holidaysCount;
    }

    public Integer getNightCount() {
        return nightCount;
    }

    public void setNightCount(Integer nightCount) {
        this.nightCount = nightCount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setPbOrder(Integer pbOrder) {
        this.pbOrder = pbOrder;
    }

    public Integer getPbOrder() {
        return pbOrder;
    }

    public Integer getPbCount() {
		return pbCount;
	}

	public void setPbCount(Integer pbCount) {
		this.pbCount = pbCount;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setDeptNumber(String deptNumber) {
        this.deptNumber = deptNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoliceBh(String policeBh) {
        this.policeBh = policeBh;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRyzt(String ryzt) {
        this.ryzt = ryzt;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getDeptNumber() {
        return deptNumber;
    }

    

    public String getCusNumber() {
        return cusNumber;
    }

    public String getName() {
        return name;
    }

    public String getPoliceBh() {
        return policeBh;
    }

    public String getCsrq() {
        return csrq;
    }

    public String getSex() {
        return sex;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public String getNl() {
        return nl;
    }

    public String getPosition() {
        return position;
    }

    public String getRyzt() {
        return ryzt;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    @Override
    public String toString() {
        return "RyglEntity{" +
                "cusNumber='" + cusNumber + '\'' +
                ", name='" + name + '\'' +
                ", policeBh='" + policeBh + '\'' +
                ", csrq='" + csrq + '\'' +
                ", sex='" + sex + '\'' +
                ", jobLevel='" + jobLevel + '\'' +
                ", nl='" + nl + '\'' +
                ", position='" + position + '\'' +
                ", ryzt='" + ryzt + '\'' +
                ", deptNumber='" + deptNumber + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createName='" + createName + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateName='" + updateName + '\'' +
                ", job='" + job + '\'' +
                ", pbCount=" + pbCount +
                ", pbOrder=" + pbOrder +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
