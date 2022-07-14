package com.cesgroup.framework.dto;

import java.io.Serializable;

/**
 * Description: 树形结构数据Dto
 * @author lincoln.cheng
 *
 * 2019年1月16日
 */
public class TreeDto implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 默认树的根节点
     */
    public static final String ROOT_ID = "root";
    /**
     * 默认树的编码
     */
    public static final String ROOT_CODE = "root";
    /**
     * 默认树的名称
     */
    public static final String ROOT_NAME = "根节点";
    /**
     * 唯一标识
     */
    private String id;
    /**
     * 编码
     */
    private String code;
    /**
     * 展示名称
     */
    private String name;
    /**
     * 父节点
     */
    private String pId;
    /**
     * 是否打开
     */
    private Boolean open;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
}
