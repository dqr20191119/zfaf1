package com.cesgroup.prison.bfcf.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "CDS_BF_CF_RECORD")
public class BfCfREcordEntity extends StringIDEntity {

	/**
	 * "ID"    VARCHAR2(50) NOT NULL,
                "TPYE"  VARCHAR2(50)         ,
                "JY_ID" VARCHAR2(50)         ,
                "CREATE_TIME" TIMESTAMP(6)   ,
                "FQ_NAME" VARCHAR2(50)       ,
                "FQ_BH"   VARCHAR2(50)       ,
                "BJQ_ID"  VARCHAR2(50)       ,
                STATUS
	 */
	private static final long serialVersionUID = 1L;
	private String type;//1.布防 2. 撤防
	private String jyId;
	private String createTime;
	private String fqName;//防区名称
	private String fqBh;//防区编号
	private String bjqId;//报警器id
	private  String status;//状态 0失败 1.成功
	private String  msgId;
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getJyId() {
		return jyId;
	}
	public void setJyId(String jyId) {
		this.jyId = jyId;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getFqName() {
		return fqName;
	}
	public void setFqName(String fqName) {
		this.fqName = fqName;
	}
	public String getFqBh() {
		return fqBh;
	}
	public void setFqBh(String fqBh) {
		this.fqBh = fqBh;
	}
	public String getBjqId() {
		return bjqId;
	}
	public void setBjqId(String bjqId) {
		this.bjqId = bjqId;
	}
	
}
