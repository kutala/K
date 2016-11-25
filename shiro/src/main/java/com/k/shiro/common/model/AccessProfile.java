package com.k.shiro.common.model;

import java.sql.Timestamp;
import java.util.List;

public class AccessProfile  extends PageSetting{

	public final static String ADMINISTRATOR = "Administrator";
	
	private int accessProfileId;

	private String displayName;

	private int participantId;

	private Timestamp lastModified;
	
	private List<Permission> permissionList;
	
	private List<Integer> permissionIdList;
	
	private int participantType;
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParticipantType() {
		return participantType;
	}

	public void setParticipantType(int participantType) {
		this.participantType = participantType;
	}

	public List<Integer> getPermissionIdList() {
		return permissionIdList;
	}

	public void setPermissionIdList(List<Integer> permissionIdList) {
		this.permissionIdList = permissionIdList;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public int getAccessProfileId() {
		return accessProfileId;
	}

	public void setAccessProfileId(int accessProfileId) {
		this.accessProfileId = accessProfileId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}
}