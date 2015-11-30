package com.momix.sdk.parser.xml;

import com.momix.sdk.common.exception.SdkException;
import com.momix.sdk.parser.api.Parser;

public class XmlParser implements Parser {
	@Override
	public <T> T from(String text, Class<T> clazz) throws SdkException {
		return new XmlConverter().fromResponse(text, clazz);
	}

	@Override
	public <T> String to(Object obj) throws SdkException {
		return new XmlConverter().toResponse(obj);
	}
}
