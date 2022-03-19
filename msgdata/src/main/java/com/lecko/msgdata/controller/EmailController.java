package com.lecko.msgdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lecko.msgdata.model.EmailPerDate;
import com.lecko.msgdata.service.EmailService;

@RestController
@RequestMapping("api/v1/emails")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@CrossOrigin(origins = "*")
	@GetMapping("number-of-emails-evolution")
	public List<EmailPerDate> getNumberOfEmailsEvolution(){
		return emailService.getNumberOfEmailsPerDate();
	}
}
