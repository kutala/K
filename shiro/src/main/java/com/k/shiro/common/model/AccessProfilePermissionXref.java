package com.k.shiro.common.model;

import java.sql.Date;

public class AccessProfilePermissionXref {
	
	private int accessProfilePermissionXrefId;
	
	private Date lastModified;
	
	private int accessProfileId;
	
	private int permissionId;

	public int getAccessProfilePermissionXrefId() {
		return accessProfilePermissionXrefId;
	}

	public void setAccessProfilePermissionXrefId(int accessProfilePermissionXrefId) {
		this.accessProfilePermissionXrefId = accessProfilePermissionXrefId;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public int getAccessProfileId() {
		return accessProfileId;
	}

	public void setAccessProfileId(int accessProfileId) {
		this.accessProfileId = accessProfileId;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

}
