package com.nullgeodesic;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SendSms implements RequestHandler<SNSEvent,String> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String handleRequest(SNSEvent event, Context context) {
		final LambdaLogger logger = context.getLogger();
		final SmsMessage message =  deserializeMessage(event);
		logger.log("Send sms message to: " + message.getPhoneNumber());
		logger.log("Send sms message with text: " + message.getMessage());
//		try {
//			logger.log(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(event));
//		} catch (final JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}

	private SmsMessage deserializeMessage(SNSEvent event) {
		try {
			final String message = event.getRecords().get(0).getSNS().getMessage();
			return this.objectMapper.readValue(message, SmsMessage.class);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}


}
