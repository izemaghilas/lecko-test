package com.lecko.msgdata.service;

import java.util.List;

import com.lecko.msgdata.model.EmailPerDate;
import com.microsoft.graph.models.Message;
import com.microsoft.graph.models.User;

public interface EmailServiceInterface {
	
	public void insertEmail(Message graphMessage, User graphUser);
	
	public List<EmailPerDate> getNumberOfEmailsPerDate();
}
