package com.nullgeodesic;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LogInput implements RequestHandler<Map<Object,Object>,ApiGatewayResponse> {

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public ApiGatewayResponse handleRequest(Map<Object,Object> event, Context context) {
		final LambdaLogger logger = context.getLogger();
		final ApiGatewayResponse apiGatewayResponse = new ApiGatewayResponse();
		try {
			final String val = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(event);
			logger.log(val);
			final String response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(apiGatewayResponse);
			logger.log(response);
		} catch (final JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return apiGatewayResponse;
	}

}
