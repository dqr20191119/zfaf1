package com.cesgroup.prison.code.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name="AUTH.T_C_CODE")
public class Code extends StringIDEntity {
	
	private String id;				//code主键[ ID(id) ]
	private String parentId;		//父编码ID[ 父编码ID(parentId) ]
	private String name;			//编码值[ 名称(name) ]
	private String codeKey;			//编码KEY名称[ 键(key) ]
	private int orderNumber;		//排序号(orderNumber)
	private int isValid;			//是否有效(isValid)
	private int isGroup;			//是否组(isGroup)
	private String type;			//类型(type)
	private String groupKey;		//编码组 编码KEY名称
	
	@Transient
	public List<Code> codeList;		//子类编码集合
	@Transient
	private boolean runFlag = true;	//遍历标志
	
	
	public Code() {
	}
	public Code(String codeKey) {
		this.codeKey = codeKey;
	}
	public Code(String id , String parentId ,  String name ,String codeKey , int orderNumber , int isValid , int isGroup , String type) {
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.codeKey = codeKey;
		this.orderNumber = orderNumber;
		this.isValid = isValid;
		this.isGroup = isGroup;
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCodeKey() {
		return codeKey;
	}
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Code> getCodeList() {
		return codeList;
	}
	public void setCodeList(Code code) {
		if(this.getCodeList()==null)
			this.codeList = new ArrayList<Code>();
		if(code != null){
			this.codeList.add(code);
		}
	}
	public void setCodeList(List<Code> codeList) {
		if(this.getCodeList()==null)
			this.codeList = new ArrayList<Code>();
		if(codeList != null){
			this.codeList.addAll(codeList);
		}
	}	
	public boolean isRunFlag() {
		return runFlag;
	}
	public void setRunFlag(boolean runFlag) {
		this.runFlag = runFlag;
	}
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
	public int getIsGroup() {
		return isGroup;
	}
	public void setIsGroup(int isGroup) {
		this.isGroup = isGroup;
	}
	public String getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}
}
