package com.lecko.msgdata.model;

public class User {
	private String msGraphId;
	private String displayName;
	private String mail;
	
	public User(String msGraphId, String displayName, String mail) {
		this.msGraphId = msGraphId;
		this.displayName = displayName;
		this.mail = mail;
	}

	public String getMsGraphId() {
		return msGraphId;
	}

	public void setMsGraphId(String msGraphId) {
		this.msGraphId = msGraphId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
