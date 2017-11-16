package com.nullgeodesic;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiFormatter implements RequestHandler<APIGatewayProxyRequestEvent,APIGatewayProxyResponseEvent> {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final SnsClient snsClient = new SnsClient();
	
	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
		final LambdaLogger logger = context.getLogger();
		final String body = event.getBody();
		logger.log("Inbound body=" + body);
		final SmsMessage message = formatSmsMessage(body);
		try {
			logger.log("object to publish: " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(message));
		} catch (final JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		snsClient.publish(message);

		return response();
	}

	private SmsMessage formatSmsMessage(String body) {
		try {
			return objectMapper.readValue(body, SmsMessage.class);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static APIGatewayProxyResponseEvent response() {
		final APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		response.setStatusCode(200);
		return response;
	}

}
