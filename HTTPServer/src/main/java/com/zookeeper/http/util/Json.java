package com.zookeeper.http.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {
	private static ObjectMapper myObjectMapper = defaultObjectMapper();
	
	public static ObjectMapper defaultObjectMapper() {
		ObjectMapper om = new ObjectMapper();
		// Make the parsing not crash in case one property missing
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return om;
	}
	
	public static JsonNode parse(String jsonSrc) throws IOException {
		System.out.println(myObjectMapper.readTree(jsonSrc));
		return myObjectMapper.readTree(jsonSrc);
	}
	
	public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException, IllegalArgumentException {
		return myObjectMapper.treeToValue(node, clazz);
	}
	
	public static JsonNode toJson(Object obj) {
		return myObjectMapper.valueToTree(obj);
	}
	
	public static String stringify(JsonNode node) throws JsonProcessingException {
		return generateJson(node, false);
	}
	
	public static String stringifyPretty(JsonNode node) throws JsonProcessingException {
		return generateJson(node, true);
	}
	
	public static String generateJson(Object o, boolean pretty) throws JsonProcessingException {
		ObjectWriter objectWriter = myObjectMapper.writer();
		if (pretty) {
			objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
		}
			
		return objectWriter.writeValueAsString(o);
	}
}
