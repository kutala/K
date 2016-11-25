package com.k.shiro.common.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


public class Credential extends PageSetting{
	
//	private int credentialsId;

	private Timestamp lastModified;

	private String displayName;

	private String emailAddress;

	private String userId;

	private String password;
	
//	private int participantId;
	
	private String status;

	private int unSuccessfulAttempts;

	private int forgotAttempts;

	private int accessLevel;

	private Date effectiveFrom;

	private Date effectiveTo;

	private Timestamp firstSuccessfulLogin;

	private Timestamp lastSuccessfulLogin;

	private Date forcePasswordChange;

	private int parentCredentialsId;

	private List<AccessProfile> accessProfileList;

	private List<String> accessProfileDisplayNameList;

	private List<Permission> permisssionList;

	private List<String> accessNameList;

	private List<Integer> accessProfileIdList;
	
	private String participantEmail;
	
	private String participantCellPhone;
	
	private String cellPhone;
	
	private String newPassword;
	
	private String confirmPassword;
	
	public String getParticipantCellPhone() {
		return participantCellPhone;
	}

	public void setParticipantCellPhone(String participantCellPhone) {
		this.participantCellPhone = participantCellPhone;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getParticipantEmail() {
		return participantEmail;
	}

	public void setParticipantEmail(String participantEmail) {
		this.participantEmail = participantEmail;
	}

	public List<Integer> getAccessProfileIdList() {
		return accessProfileIdList;
	}

	public void setAccessProfileIdList(List<Integer> accessProfileIdList) {
		this.accessProfileIdList = accessProfileIdList;
	}

	public List<AccessProfile> getAccessProfileList() {
		return accessProfileList;
	}

	public void setAccessProfileList(List<AccessProfile> accessProfileList) {
		this.accessProfileList = accessProfileList;
	}

	public List<Permission> getPermisssionList() {
		return permisssionList;
	}

	public void setPermisssionList(List<Permission> permisssionList) {
		this.permisssionList = permisssionList;
	}

//	public int getCredentialsId() {
//		return credentialsId;
//	}
//
//	public void setCredentialsId(int credentialsId) {
//		this.credentialsId = credentialsId;
//	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public int getParticipantId() {
//		return participantId;
//	}
//
//	public void setParticipantId(int participantId) {
//		this.participantId = participantId;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUnSuccessfulAttempts() {
		return unSuccessfulAttempts;
	}

	public void setUnSuccessfulAttempts(int unSuccessfulAttempts) {
		this.unSuccessfulAttempts = unSuccessfulAttempts;
	}

	public int getForgotAttempts() {
		return forgotAttempts;
	}

	public void setForgotAttempts(int forgotAttempts) {
		this.forgotAttempts = forgotAttempts;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public Timestamp getFirstSuccessfulLogin() {
		return firstSuccessfulLogin;
	}

	public void setFirstSuccessfulLogin(Timestamp firstSuccessfulLogin) {
		this.firstSuccessfulLogin = firstSuccessfulLogin;
	}

	public Timestamp getLastSuccessfulLogin() {
		return lastSuccessfulLogin;
	}

	public void setLastSuccessfulLogin(Timestamp lastSuccessfulLogin) {
		this.lastSuccessfulLogin = lastSuccessfulLogin;
	}

	public Date getForcePasswordChange() {
		return forcePasswordChange;
	}

	public void setForcePasswordChange(Date forcePasswordChange) {
		this.forcePasswordChange = forcePasswordChange;
	}

	public int getParentCredentialsId() {
		return parentCredentialsId;
	}

	public void setParentCredentialsId(int parentCredentialsId) {
		this.parentCredentialsId = parentCredentialsId;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public List<String> getAccessProfileDisplayNameList() {
		return accessProfileDisplayNameList;
	}

	public void setAccessProfileDisplayNameList(List<String> accessProfileDisplayNameList) {
		this.accessProfileDisplayNameList = accessProfileDisplayNameList;
	}

	public List<String> getAccessNameList() {
		return accessNameList;
	}

	public void setAccessNameList(List<String> accessNameList) {
		this.accessNameList = accessNameList;
	}

}
