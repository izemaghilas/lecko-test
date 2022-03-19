package com.lecko.msgdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import com.lecko.msgdata.model.Email;
import com.lecko.msgdata.model.EmailPerDate;
import com.lecko.msgdata.model.User;
import com.lecko.msgdata.repository.EmailRepository;
import com.microsoft.graph.models.Message;

@Service
public class EmailService implements EmailServiceInterface {
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<EmailPerDate> getNumberOfEmailsPerDate() {
		Aggregation aggregation = Aggregation.newAggregation(
				Aggregation.group("sentDate").count().as("numberOfEmails"),
				Aggregation.project("numberOfEmails").and("sentDate").previousOperation(),
				Aggregation.sort(Sort.Direction.ASC, "sentDate")
			);
	
		AggregationResults<EmailPerDate> result = mongoTemplate.aggregate(aggregation, Email.class, EmailPerDate.class);
		
		return result.getMappedResults();
	}

	@Override
	public void insertEmail(Message graphMessage, com.microsoft.graph.models.User graphUser) {
		User user = new User(graphUser.id, graphUser.displayName, graphUser.mail);
		emailRepository.insert(
				new Email(
						graphMessage.id, 
						graphMessage.sentDateTime.toLocalDate(),
						user
					)
				);
	}

}
