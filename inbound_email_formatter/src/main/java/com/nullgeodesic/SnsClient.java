package com.nullgeodesic;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SnsClient {

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final AmazonSNS snsClient;
	
	public SnsClient() {
		final AmazonSNSClientBuilder builder = AmazonSNSClientBuilder.standard();
		builder.setRegion(Regions.US_WEST_2.getName());
		builder.setCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AKIAI7YTGYPAE34HAV5A","rMJ7qVTY/xnxTNKFkVu3fvh9UU6Zn+dA1D+zKk2E")));
		this.snsClient = builder.build();
	}

	public void publish(Object object) {
		snsClient.publish("arn:aws:sns:us-west-2:202214144554:sendtext", serialize(object));
	}
	
	private String serialize(final Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (final JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	

}
