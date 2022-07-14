
package com.cesgroup.prison.dagl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.mybatis.entity.StringIDEntity;

@Entity
@Table(name = "T_FW_Atth")
public class Atth extends StringIDEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件大小
	 */
	private String fileSize;
	/**
	 * 文件类型
	 */
	private String fileType;
	/**
	 * 创建人
	 */
	private String createUser;

	/**
	 * 创建时间
	 */
	private String createDate;

	/**
	 * 关联条目id
	 */
	private String ownerId;
	
	/**
	 * 附件
	 */
	private byte[] atthFile;
	
	
	public String getFileName() {
		return fileName;
	}

	@Column(name = "file_name")
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	@Column(name = "file_size")
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	@Column(name = "file_type")
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getCreateUser() {
		return createUser;
	}

	@Column(name = "create_user")
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	@Column(name = "create_date")
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getOwnerId() {
		return ownerId;
	}

	@Column(name = "owner_id")
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public byte[] getAtthFile() {
		return atthFile;
	}

	public void setAtthFile(byte[] atthFile) {
		this.atthFile = atthFile;
	}
	
	
}
