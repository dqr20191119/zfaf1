package com.cesgroup.prison.ysjg.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name = "ZFINFO.T_YSJG_EXPENSE")
public class ExpenseEntity  extends StringIDEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ysjgId;
	
	private String funds;
	
	private String type;

	 
	public String getYsjgId() {
		return ysjgId;
	}

	public void setYsjgId(String ysjgId) {
		this.ysjgId = ysjgId;
	}

	public String getFunds() {
		return funds;
	}

	public void setFunds(String funds) {
		this.funds = funds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
