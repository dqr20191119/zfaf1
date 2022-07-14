package com.cesgroup.prison.ysjg.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name = "ZFINFO.T_YSJG_DIE")
public class DieEntity  extends StringIDEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ysjgId;
	
	private String actualProportion;
	
	private String type;
	
	private String standardProportion;

	 

	public String getYsjgId() {
		return ysjgId;
	}

	public void setYsjgId(String ysjgId) {
		this.ysjgId = ysjgId;
	}

	public String getActualProportion() {
		return actualProportion;
	}

	public void setActualProportion(String actualProportion) {
		this.actualProportion = actualProportion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStandardProportion() {
		return standardProportion;
	}

	public void setStandardProportion(String standardProportion) {
		this.standardProportion = standardProportion;
	}
	
	
	
}
