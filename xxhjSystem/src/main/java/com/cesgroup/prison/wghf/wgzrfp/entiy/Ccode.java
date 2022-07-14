package com.cesgroup.prison.wghf.wgzrfp.entiy;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
 * Description: 北京局 监狱网格分布 实体类
 * @author monkey
 *
 * 2019年3月16日
 */


@Entity
@Table(name = "T_C_CODE" , schema = "AUTH")
public class Ccode extends StringIDEntity{
	
	private static final long serialVersionUID = 1L;
	
	    private String id;

	    private String parentId;

	    private String name;

	    private String codeKey;

	    private Integer orderNumber;

	    private String isValid;

	    private String isGroup;

	    private String type;

	    private String groupKey;

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id == null ? null : id.trim();
	    }

	    public String getParentId() {
	        return parentId;
	    }

	    public void setParentId(String parentId) {
	        this.parentId = parentId == null ? null : parentId.trim();
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name == null ? null : name.trim();
	    }

	    public String getCodeKey() {
	        return codeKey;
	    }

	    public void setCodeKey(String codeKey) {
	        this.codeKey = codeKey == null ? null : codeKey.trim();
	    }

	    public Integer getOrderNumber() {
	        return orderNumber;
	    }

	    public void setOrderNumber(Integer orderNumber) {
	        this.orderNumber = orderNumber;
	    }

	    public String getIsValid() {
	        return isValid;
	    }

	    public void setIsValid(String isValid) {
	        this.isValid = isValid == null ? null : isValid.trim();
	    }

	    public String getIsGroup() {
	        return isGroup;
	    }

	    public void setIsGroup(String isGroup) {
	        this.isGroup = isGroup == null ? null : isGroup.trim();
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type == null ? null : type.trim();
	    }

	    public String getGroupKey() {
	        return groupKey;
	    }

	    public void setGroupKey(String groupKey) {
	        this.groupKey = groupKey == null ? null : groupKey.trim();
	    }


}
