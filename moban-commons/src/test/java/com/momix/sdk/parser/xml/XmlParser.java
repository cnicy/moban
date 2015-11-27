package com.momix.sdk.parser.xml;

import com.momix.sdk.parser.api.Parser;
import com.momix.sdk.parser.exception.ApiException;

public class XmlParser implements Parser {
	@Override
	public <T> T from(String text, Class<T> clazz) throws ApiException {
		return new XmlConverter().fromResponse(text, clazz);
	}

	@Override
	public <T> String to(Object obj) throws ApiException {
		return new XmlConverter().toResponse(obj);
	}
}
