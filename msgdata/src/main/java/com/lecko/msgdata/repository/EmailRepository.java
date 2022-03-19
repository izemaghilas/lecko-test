package com.lecko.msgdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lecko.msgdata.model.Email;

public interface EmailRepository extends MongoRepository<Email, String>{
	
}
