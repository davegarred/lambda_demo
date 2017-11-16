package com.nullgeodesic;

public class SmsMessage {
	
	private String phoneNumber;
	private String message;
	
	public static SmsMessage smsMessage(String phoneNumber, String message) {
		final SmsMessage smsMessage = new SmsMessage();
		smsMessage.phoneNumber = phoneNumber;
		smsMessage.message = message;
		return smsMessage;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
