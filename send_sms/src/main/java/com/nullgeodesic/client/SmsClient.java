package com.nullgeodesic.client;

public interface SmsClient {

	void sendMessage(String phoneNumber, String message);
	
}
