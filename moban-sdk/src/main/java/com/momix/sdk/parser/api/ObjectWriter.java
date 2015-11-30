package com.momix.sdk.parser.api;

import com.momix.sdk.common.exception.SdkException;

/**
 * 对象写入
 */
public interface ObjectWriter {
	/**
	 * 写单个基本对象。
	 * @param name	     对象名称
	 * @param value	     对象值
	 */
	public void setPrimitiveObject(String name, Object value)throws SdkException;
	/**
	 * 写入对象
	 * @param name 对象名称
	 * @param subValue 对象值  
	 * @throws SdkException
	 */
	public void setObject(String name, Object subValue) throws SdkException;
	/**
	 * 写入多个对象的值。
	 * @param listName 列表名称
	 * @param itemName 嵌套项名称
	 * @throws SdkException
	 */
	public void setListObjects(String listName, String itemName, Object subValues, Class<?> subType)throws SdkException;
}