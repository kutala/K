package com.k.shiro.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtils {

	public static String toJSON(final Object value) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(value);
		} catch (final IOException e) {
			throw new RuntimeException(e);// NOPMD
		}
	}

	public static <T> T readValue(final String json, final Class<T> cls) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, cls);
		} catch (final IOException e) {
			throw new RuntimeException(e);// NOPMD
		}
	}

	public static <T> T readValue(final InputStream is, final Class<T> cls) {
		return readValue(getJSON(is), cls);
	}

	private static String getJSON(final InputStream is) {
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			final StringBuilder builder = new StringBuilder();
			String str = null;
			while ((str = br.readLine()) != null) {
				builder.append(str);
			}
			return builder.toString();
		} catch (final Exception e) {
			throw new RuntimeException(e);// NOPMD
		}
	}

	public static <T> T readValue(final String json, final TypeReference<T> type) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, type);
		} catch (final IOException e) {
			throw new RuntimeException(e);// NOPMD
		}
	}

	public static <T> T readValue(final InputStream is, final TypeReference<T> type) {
		return readValue(getJSON(is), type);
	}

	public static Object readJsonValue(String json, String key) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, Map.class).get(key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> readJsonToMap(String json) {
		final ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, Map.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
