package com.lecko.msgdata.service;

import java.util.List;
import java.util.Map;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.core.ClientException;
import com.microsoft.graph.models.Message;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.GraphServiceClient;
import com.microsoft.graph.requests.MessageCollectionPage;
import com.microsoft.graph.requests.MessageCollectionRequestBuilder;
import com.microsoft.graph.requests.UserCollectionPage;
import com.microsoft.graph.requests.UserCollectionRequestBuilder;

import okhttp3.Request;

public class GraphAPIService {
	
	private static final int TOP_USERS = 999;
	private static final int TOP_MESSAGES = 999;
	
	private static GraphServiceClient<Request> graphClient;
	private static TokenCredentialAuthProvider authProvider;
	
	public static void init(Map<String, String> configs) {
		ClientSecretCredential credential = new ClientSecretCredentialBuilder()
				.clientId(configs.get("clientId"))
				.clientSecret(configs.get("clientSecret"))
				.tenantId(configs.get("tenant"))
				.build();
		
		authProvider = new TokenCredentialAuthProvider(credential);
		
		graphClient = GraphServiceClient.builder()
				.authenticationProvider(authProvider)
				.buildClient();
	}
	
	public static void retrieveEmails(EmailServiceInterface emailService) throws GraphAPIServiceException {
		if(emailService == null) {
			throw new GraphAPIServiceException("No EmailService");
		}
		
		if(graphClient == null || authProvider == null) {
			throw new GraphAPIServiceException("Service not initialized");
		}
		
		try {
			UserCollectionPage usersPage = graphClient.users()
					.buildRequest()
					.select("id,displayName,mail")
					.top(TOP_USERS)
					.get();
			
			while(usersPage != null) {
				List<User> users = usersPage.getCurrentPage();
				
				persistEmailsToDB(emailService, users);
				
				UserCollectionRequestBuilder nextPage = usersPage.getNextPage();
				if(nextPage == null) {
					break;
				}
				else {
					usersPage = nextPage.buildRequest().get();
				}
			}
		} catch (ClientException e) {
			// TODO: handle exception
		}
	}
	
	private static void persistEmailsToDB(EmailServiceInterface emailService, List<User> users) {
		for(User user : users) {
			if(user.mail != null) {
				try {
					MessageCollectionPage messagesPage = graphClient.users(user.id)
							.messages()
							.buildRequest()
							.select("id, sentDateTime")
							.top(TOP_MESSAGES)
							.get();
					
					while(messagesPage != null) {
						List<Message> messages = messagesPage.getCurrentPage();
						
						for(Message message : messages) {
							emailService.insertEmail(message, user);
						}
						
						MessageCollectionRequestBuilder nextPage = messagesPage.getNextPage();
						if(nextPage == null) {
							break;
						}
						else {
							messagesPage = nextPage.buildRequest().get();
						}
					}
					
				} catch (ClientException e) {
					// TODO: handle exception
				}
			}
		}
	}
}
