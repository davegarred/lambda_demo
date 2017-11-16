package com.nullgeodesic;

import java.util.HashMap;
import java.util.Map;

public class ApiGatewayResponse {

	public final int statusCode = 200;
	public final String body = "{}";
	public final Map<String,String> headers = headers();
	public final boolean isBase64Encoded = false;
	
	public Map<String,String> headers() {
		final Map<String,String> headers = new HashMap<>();
		return headers;
	}
}
