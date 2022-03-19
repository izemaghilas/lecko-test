package com.lecko.msgdata;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.lecko.msgdata.service.EmailService;
import com.lecko.msgdata.service.GraphAPIService;

@SpringBootApplication
public class MsgdataApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(MsgdataApplication.class, args);
	}

	@Autowired
	private Environment env;
	
	@Autowired
	private EmailService emailService;
	
	//@Override
	public void run(String... args) throws Exception {
		Map<String, String> configs = new HashMap<String, String>();
		configs.put("clientId", env.getProperty("GRAPH_API_CLIENT_ID"));
		configs.put("clientSecret", env.getProperty("GRAPH_API_CLIENT_SECRET"));
		configs.put("tenant", env.getProperty("GRAPH_API_TENANT"));
		
		GraphAPIService.init(configs);
		GraphAPIService.retrieveEmails(emailService);
		
	}
}
