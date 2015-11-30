package com.momix.sdk.parser.json;

import com.google.gson.GsonBuilder;
import com.momix.sdk.common.exception.SdkException;
import com.momix.sdk.parser.api.Converter;

public class JsonConverter implements Converter {
	@Override
	public <T> T fromResponse(String text, Class<T> clazz) throws SdkException {
		// TODO Auto-generated method stub
		return new GsonBuilder().disableHtmlEscaping().create().fromJson(text,clazz);
	}

	@Override
	public <T> String toResponse(Object obj) throws SdkException {
		return new GsonBuilder().disableHtmlEscaping().create().toJson(obj);
	}
}
