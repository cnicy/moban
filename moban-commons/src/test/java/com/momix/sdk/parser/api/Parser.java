package com.momix.sdk.parser.api;

import com.momix.sdk.parser.exception.ApiException;

/**
 * 格式转换器，如xml、json转换
 */
public interface Parser {
	/**
	 * 格式字符串转成对象
	 * @param text
	 * @return
	 * @throws ApiException
	 */
	public <T> T from(String text,Class<T> clazz)throws ApiException;
	/**
	 * 
	 * @param t
	 * @return
	 * @throws ApiException
	 */
	public <T> String to(Object obj)throws ApiException;
}