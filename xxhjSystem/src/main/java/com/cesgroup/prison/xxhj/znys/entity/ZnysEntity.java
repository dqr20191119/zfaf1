package com.cesgroup.prison.xxhj.znys.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ZnysEntity extends  StringIDEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String name;

    private String keyName;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date getKeyTime;

    private String getKeyPerson;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date returnKeyTime;

    private String statue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName == null ? null : keyName.trim();
    }

    public Date getGetKeyTime() {
        return getKeyTime;
    }

    public void setGetKeyTime(Date getKeyTime) {
        this.getKeyTime = getKeyTime;
    }

    public String getGetKeyPerson() {
        return getKeyPerson;
    }

    public void setGetKeyPerson(String getKeyPerson) {
        this.getKeyPerson = getKeyPerson == null ? null : getKeyPerson.trim();
    }

    public Date getReturnKeyTime() {
        return returnKeyTime;
    }

    public void setReturnKeyTime(Date returnKeyTime) {
        this.returnKeyTime = returnKeyTime;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue == null ? null : statue.trim();
    }
}