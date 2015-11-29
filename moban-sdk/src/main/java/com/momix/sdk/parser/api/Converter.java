package com.momix.sdk.parser.api;

import com.momix.sdk.parser.exception.ApiException;

/**
 *	转换器，内部使用
 */
public interface Converter {
	/**
	 * 字符串转换成对象
	 * @param text
	 * @param clazz
	 * @return
	 * @throws ApiException
	 */
	public <T> T fromResponse(String text, Class<T> clazz)throws ApiException, com.momix.sdk.parser.exception.ApiException;
	/**
	 * 对象转换成字符串
	 * @param clazz
	 * @return
	 * @throws ApiException
	 */
	public <T> String toResponse(Object obj)throws ApiException, com.momix.sdk.parser.exception.ApiException;
}