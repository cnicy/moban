package com.momix.sdk.parser.json;

import com.google.gson.GsonBuilder;
import com.momix.sdk.parser.api.Parser;
import com.momix.sdk.parser.exception.ApiException;

public class JsonParser implements Parser {
	@Override
	public <T> T from(String text, Class<T> clazz) throws ApiException {
		return new GsonBuilder().disableHtmlEscaping().create().fromJson(text,clazz);
	}

	@Override
	public <T> String to(Object obj) throws ApiException {
		return new GsonBuilder().disableHtmlEscaping().create().toJson(obj);
	}
}
