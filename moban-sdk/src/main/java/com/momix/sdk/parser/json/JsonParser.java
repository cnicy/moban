package com.momix.sdk.parser.json;

import com.google.gson.GsonBuilder;
import com.momix.sdk.common.exception.SdkException;
import com.momix.sdk.parser.api.Parser;

public class JsonParser implements Parser {
	@Override
	public <T> T from(String text, Class<T> clazz) throws SdkException {
		return new GsonBuilder().disableHtmlEscaping().create().fromJson(text,clazz);
	}

	@Override
	public <T> String to(Object obj) throws SdkException {
		return new GsonBuilder().disableHtmlEscaping().create().toJson(obj);
	}
}
