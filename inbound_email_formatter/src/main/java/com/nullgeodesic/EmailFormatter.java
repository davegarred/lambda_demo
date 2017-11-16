package com.nullgeodesic;

import static com.nullgeodesic.SmsMessage.smsMessage;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nullgeodesic.dto.InboundEmail;

public class EmailFormatter implements RequestHandler<Map<Object,Object>,String> {

	private final SnsClient snsClient;
	
	public EmailFormatter() {
		this.snsClient = new SnsClient();
	}
	
	@Override
	public String handleRequest(Map<Object,Object> event, Context context) {
		final LambdaLogger logger = context.getLogger();
		final InboundEmail email = new InboundEmail(event);
		logger.log("Email from: " + email.source);
		logger.log("Email subject: " + email.subject);
		
		final SmsMessage message = formatSmsMessage(email);
		snsClient.publish(message);
		return null;
	}

	private SmsMessage formatSmsMessage(final InboundEmail email) {
		final int firstSpace = email.subject.indexOf(' ');
		final SmsMessage message = smsMessage(email.subject.substring(0, firstSpace), email.subject.substring(firstSpace + 1));
		return message;
	}

}
