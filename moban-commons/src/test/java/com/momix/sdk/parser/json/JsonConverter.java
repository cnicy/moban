package com.momix.sdk.parser.json;

import com.google.gson.GsonBuilder;
import com.momix.sdk.parser.api.Converter;
import com.momix.sdk.parser.exception.ApiException;

public class JsonConverter implements Converter {
	@Override
	public <T> T fromResponse(String text, Class<T> clazz) throws ApiException {
		// TODO Auto-generated method stub
		return new GsonBuilder().disableHtmlEscaping().create().fromJson(text,clazz);
	}

	@Override
	public <T> String toResponse(Object obj) throws ApiException {
		return new GsonBuilder().disableHtmlEscaping().create().toJson(obj);
	}
}
