package com.nullgeodesic.dto;

import static java.lang.String.format;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nullgeodesic.EmailFormatter;

public class EmailFormatterTest {

	private final EmailFormatter parser = new EmailFormatter();
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	private final Context context = Mockito.mock(Context.class);
	private final LambdaLogger logger = Mockito.mock(LambdaLogger.class);
	
	@Test
	public void test() throws IOException {
		Mockito.when(context.getLogger()).thenReturn(logger);
		
		parser.handleRequest(savedSerialization("InboundEmail"), context);
	}
	
    @SuppressWarnings("unchecked")
	protected Map<Object,Object> savedSerialization(String target) throws IOException {
        final String fileName = format("src/test/resources/json/%s.json",target);
        try (final FileInputStream in = new FileInputStream(fileName)) {
            return objectMapper.readValue(in, Map.class);
        }
    }
}
