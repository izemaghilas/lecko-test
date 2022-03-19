package com.lecko.msgdata.model;

import java.time.LocalDate;

public class EmailPerDate {
	
	private LocalDate sentDate;
	private int numberOfEmails;
	
	public LocalDate getSentDate() {
		return sentDate;
	}
	public void setSentDate(LocalDate sentDate) {
		this.sentDate = sentDate;
	}
	public int getNumberOfEmails() {
		return numberOfEmails;
	}
	public void setNumberOfEmails(int numberOfEmails) {
		this.numberOfEmails = numberOfEmails;
	}
	
	
}
