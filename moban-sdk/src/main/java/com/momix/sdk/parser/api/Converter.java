package com.momix.sdk.parser.api;

import com.momix.sdk.common.exception.SdkException;

/**
 *	转换器，内部使用
 */
public interface Converter {
	/**
	 * 字符串转换成对象
	 * @param text
	 * @param clazz
	 * @param <T>
	 * @return
	 * @throws SdkException
	 */
	public <T> T fromResponse(String text, Class<T> clazz)throws SdkException;

	/**
	 * 对象转换成字符串
	 * @param obj
	 * @param <T>
	 * @return
	 * @throws SdkException
	 */
	public <T> String toResponse(Object obj)throws SdkException;
}