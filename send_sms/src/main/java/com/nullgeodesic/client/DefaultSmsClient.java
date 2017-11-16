package com.nullgeodesic.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

public class DefaultSmsClient implements SmsClient {

	private static final Logger LOGGER = LogManager.getLogger(SmsClient.class);
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	public void sendMessage(String phoneNumber, String message) {
		LOGGER.info("Sending message to {} with content {}", phoneNumber, message);
	}

}
