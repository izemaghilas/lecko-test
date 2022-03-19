package com.lecko.msgdata.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "emails")
public class Email {
	
	@Id
	private String id;
	private String msGraphId;
	private LocalDate sentDate;
	
	private User user;
	
	public Email(String msGraphId, LocalDate sentDate, User user) {
		this.msGraphId = msGraphId;
		this.sentDate = sentDate;
		this.user = user;
	}

	public String getMsGraphId() {
		return msGraphId;
	}

	public void setMsGraphId(String msGraphId) {
		this.msGraphId = msGraphId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public LocalDate getSentDate() {
		return sentDate;
	}

	public void setSentDate(LocalDate sentDate) {
		this.sentDate = sentDate;
	}
	
}
