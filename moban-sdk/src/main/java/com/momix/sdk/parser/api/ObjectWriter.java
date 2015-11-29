package com.momix.sdk.parser.api;

import com.momix.sdk.parser.exception.ApiException;

/**
 * 对象写入
 */
public interface ObjectWriter {
	/**
	 * 写单个基本对象。
	 * @param name	     对象名称
	 * @param value	     对象值
	 */
	public void setPrimitiveObject(String name, Object value);
	/**
	 * 写入对象
	 * @param name 对象名称
	 * @param subValue 对象值  
	 * @throws ApiException
	 */
	public void setObject(String name, Object subValue) throws ApiException;
	/**
	 * 写入多个对象的值。
	 * @param listName 列表名称
	 * @param itemName 嵌套项名称
	 * @throws ApiException
	 */
	public void setListObjects(String listName, String itemName, Object subValues, Class<?> subType)throws ApiException;
}