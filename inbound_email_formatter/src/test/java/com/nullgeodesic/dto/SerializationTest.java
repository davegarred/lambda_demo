package com.nullgeodesic.dto;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationTest {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void test() throws IOException {
		final Map<Object, Object> savedSerialization = savedSerialization("InboundEmail");
		final InboundEmail email = new InboundEmail(savedSerialization);
		assertEquals("janedoe@example.com", email.source);
		assertEquals("Test Subject", email.subject);
	}
	
    @SuppressWarnings("unchecked")
	protected Map<Object,Object> savedSerialization(String target) throws IOException {
        final String fileName = format("src/test/resources/json/%s.json",target);
        try (final FileInputStream in = new FileInputStream(fileName)) {
            return objectMapper.readValue(in, Map.class);
        }
    }
}
