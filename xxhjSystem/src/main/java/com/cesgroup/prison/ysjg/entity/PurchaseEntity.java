package com.cesgroup.prison.ysjg.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name = "ZFINFO.T_YSJG_PURCHASE")
public class PurchaseEntity  extends StringIDEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ysjgId;
	
	private String name;
	
	private String amount;

 
	public String getYsjgId() {
		return ysjgId;
	}

	public void setYsjgId(String ysjgId) {
		this.ysjgId = ysjgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	
}
