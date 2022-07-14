package com.cesgroup.prison.orgMapping.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
 * 中信与大旗机构编码映射表
 * 
 * @author lincoln.cheng
 *
 */
@Entity
@Table(name = "T_CESCODE_CONTRAST_DAQI")
public class CescodeContrastDaqi extends StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 中信编码
	 */
    private String cesKey;
    /**
     * 中信名称
     */
    private String cesName;
    /**
     * 大旗编码
     */
    private String daqiKey;
    /**
     * 大旗名称
     */
    private String daqiName;
    
	public String getCesKey() {
		return cesKey;
	}
	public void setCesKey(String cesKey) {
		this.cesKey = cesKey;
	}
	public String getCesName() {
		return cesName;
	}
	public void setCesName(String cesName) {
		this.cesName = cesName;
	}
	public String getDaqiKey() {
		return daqiKey;
	}
	public void setDaqiKey(String daqiKey) {
		this.daqiKey = daqiKey;
	}
	public String getDaqiName() {
		return daqiName;
	}
	public void setDaqiName(String daqiName) {
		this.daqiName = daqiName;
	}
}