package com.k.shiro.common.model;

import java.sql.Date;

public class Permission extends PageSetting{
	
	private int permissionId;

	private Date lastModifiedTime;

	private String accessName;

	private String name;

	private String description;

	private int participantType;

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public String getAccessName() {
		return accessName;
	}

	public void setAccessName(String accessName) {
		this.accessName = accessName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParticipantType() {
		return participantType;
	}

	public void setParticipantType(int participantType) {
		this.participantType = participantType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

}
