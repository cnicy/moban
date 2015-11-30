package com.momix.sdk.parser.api;

import com.momix.sdk.parser.exception.ApiException;
import com.momix.sdk.common.utils.string.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class Converters {
	// Field 对象的缓存，减少遍历开销
	private static final Map<String, Field> fieldCache = new ConcurrentHashMap<String, Field>();
	
	public static boolean CHECK_TYPE = true; // 解析包的时候是否做类型判断
	
	public static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static String DATE_TIME_ZONE = "GMT+8";
	
	public static <T> T reader(Class<T> clazz,ObjectReader reader)throws ApiException {
		T rsp = null;
		try{
			rsp = clazz.newInstance();
			// java 内省机制或者对象属性
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			// 属性列表
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			if(null!=pds && pds.length>0){
				for(PropertyDescriptor pd:pds){
					Method writerMethod = pd.getWriteMethod();
					if(null==writerMethod)
						continue; // 属性为只读
					
					// 根据字段名称或者 Field对象
					String fieldName = pd.getName();
					String listFieldName = fieldName;
					Field field = getField(clazz,fieldName);
					if(null==field)
						continue;
					
					// 如果有自定义注解的话，那么使用注解名称
					XmlAttribute xa = field.getAnnotation(XmlAttribute.class);
					if(null!=xa){
						fieldName = xa.name();
						listFieldName = xa.listName();
					}
					
					// 节点数据不存在
					if(!reader.hasReturnField(fieldName)){
						if(null==listFieldName || !reader.hasReturnField(listFieldName)){
							continue; 
						}
					}
					
					Class<?> typeClass = field.getType();
					Object value = reader.getPrimitiveObject(fieldName);
					if(null!=value){
						// String
						if(String.class.isAssignableFrom(typeClass)){ // 判断两个类是否相同
							if(value instanceof String){
								writerMethod.invoke(rsp, value.toString());
							}else if(CHECK_TYPE){
									throw new ApiException("field:"+fieldName+" value is not a string!");
							}else
								writerMethod.invoke(rsp, value.toString());
						// Long
						}else if(Long.class.isAssignableFrom(typeClass)){
							if(value instanceof Long){
								writerMethod.invoke(rsp,(Long) value);
							}else if(CHECK_TYPE){
								throw new ApiException("field:"+fieldName+" value is not a Number(Long)!");
							}else if(StringUtils.isNumeric(value))
								writerMethod.invoke(rsp,Long.valueOf(value.toString()));
						// Integer
						}else if(Integer.class.isAssignableFrom(typeClass)){
							if (value instanceof Integer) {
								writerMethod.invoke(rsp, (Integer) value);
							} else if(CHECK_TYPE){
								throw new ApiException("field:"+fieldName+" value is not a Number(Integer)!");
							}else if(StringUtils.isNumeric(value))
								writerMethod.invoke(rsp,Integer.valueOf(value.toString()));
						// Double
						}else if (Double.class.isAssignableFrom(typeClass)){
							if (value instanceof Double) {
								writerMethod.invoke(rsp, (Double) value);
							} else if(CHECK_TYPE){
								throw new ApiException("field:"+fieldName+" value is not a Number(Double)!");
							}else if(StringUtils.isNumeric(value))
								writerMethod.invoke(rsp,Double.valueOf(value.toString()));
						// Number
						}else if(Number.class.isAssignableFrom(typeClass)){
							if (value instanceof Number) {
								writerMethod.invoke(rsp, (Number) value);
							} else if(CHECK_TYPE){
								throw new ApiException("field:"+fieldName+" value is not a Number!");
							}
						// Boolean							
						}else if (Boolean.class.isAssignableFrom(typeClass)){
							if (value instanceof Boolean) {
								writerMethod.invoke(rsp, (Boolean) value);
							} else if(CHECK_TYPE){
								throw new ApiException("field:"+fieldName+" value is not a Boolean!");
							}else
								writerMethod.invoke(rsp, value.toString());
						// Date
						}else if(Date.class.isAssignableFrom(typeClass)){
							DateFormat format = new SimpleDateFormat(DATE_FORMAT);
							format.setTimeZone(TimeZone.getTimeZone(DATE_TIME_ZONE));
							if(value instanceof String) {
								writerMethod.invoke(rsp, format.parse(value.toString()));
							}
						}else if(List.class.isAssignableFrom(typeClass)){
							/**
							 * see http://developer.51cto.com/art/201103/250028.htm
							 */
							Type fieldType = field.getGenericType(); //  处理泛型类型 
							if (fieldType instanceof ParameterizedType) {
								// 处理多级泛型  
								ParameterizedType paramType = (ParameterizedType) fieldType;
								Type[] genericTypes = paramType.getActualTypeArguments();
								if (null !=genericTypes && genericTypes.length > 0) {
									if (genericTypes[0] instanceof Class<?>) {
										Class<?> subType = (Class<?>) genericTypes[0];
										List<?> listObjs = reader.getListObjects(listFieldName, fieldName, subType);
										if (listObjs != null) {
											writerMethod.invoke(rsp, listObjs);
										}
									}
								}
							}
						}else{
							// 对象
							Object obj = reader.getObject(fieldName, typeClass);
							if (obj != null) {
								writerMethod.invoke(rsp, obj);
							}
						}
					}
				}
			}
		}catch(Exception e){
			throw new ApiException(e.getMessage(),e);
		}
		return rsp;
	}
	
	public static void reader(Object obj,ObjectWriter writer) throws ApiException{
		Class<?> clazz = obj.getClass();
		try{
			// java 内省机制或者对象属性
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			// 属性列表
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			if(null!=pds && pds.length>0){
				for(PropertyDescriptor pd:pds){
					Method readMethod = pd.getReadMethod();
					if(null==readMethod)
						continue;
					
					// 根据字段名称或者 Field对象
					String itemName = pd.getName();
					String listName = itemName;
					Field field = getField(clazz,itemName);
					if(null==field)
						continue;
					
					// 空值不参与构造参数
					Object value = readMethod.invoke(obj);
					if(null==value)
						continue;
					
					// 如果有自定义注解的话，那么使用注解名称
					XmlAttribute xa = field.getAnnotation(XmlAttribute.class);
					if(null!=xa){
						itemName = xa.name();
						listName = xa.listName();
					}
					
					Class<?> typeClass = field.getType();
					// String
					if(String.class.isAssignableFrom(typeClass)){
						writer.setPrimitiveObject(itemName, value.toString());
					// Integer
					}else if(Integer.class.isAssignableFrom(typeClass)){
						writer.setPrimitiveObject(itemName, value.toString());
					// Long
					}else if(Long.class.isAssignableFrom(typeClass)){
						writer.setPrimitiveObject(itemName, value.toString());
					// Double
					}else if(Double.class.isAssignableFrom(typeClass)){
						writer.setPrimitiveObject(itemName, value.toString());
					// Number
					}else if(Number.class.isAssignableFrom(typeClass)){
						writer.setPrimitiveObject(itemName, value.toString());
					// Boolean
					}else if(Boolean.class.isAssignableFrom(typeClass)){
						writer.setPrimitiveObject(itemName, value.toString());
					// Date
					}else if(Date.class.isAssignableFrom(typeClass)){
						DateFormat format = new SimpleDateFormat(DATE_FORMAT);
						format.setTimeZone(TimeZone.getTimeZone(DATE_TIME_ZONE));
						writer.setPrimitiveObject(itemName, format.parse(value.toString()));
					// List
					}else if(List.class.isAssignableFrom(typeClass)){
						Type fieldType = field.getGenericType(); //  处理泛型类型 
						if (fieldType instanceof ParameterizedType) {
							// 处理多级泛型  
							ParameterizedType paramType = (ParameterizedType) fieldType;
							// 泛型项类型如：List<Item> Item 为泛型项类型
							Type[] genericTypes = paramType.getActualTypeArguments();
							if (null !=genericTypes && genericTypes.length > 0) {
								if (genericTypes[0] instanceof Class<?>) {
									Class<?> subType = (Class<?>) genericTypes[0];
									writer.setListObjects(listName, itemName, value,subType);
								}
							}
						}
					}else{
						writer.setObject(itemName,value);
					}
					
				}
			}
		}catch(Exception e){
			throw new ApiException(e.getMessage(),e);
		}
	}
	
	private static Field getField(Class<?> clazz,final String name) throws NoSuchFieldException, SecurityException{
		// 每个类可能存在相同名称字段，加以区分
		String key = clazz.getName() + "_" + name;
		Field field = fieldCache.get(key);
		if(null==field){
			try {
				field = clazz.getDeclaredField(name);
			} catch (NoSuchFieldException e) {
				Class<?> superClazz = clazz.getSuperclass();
				if(null!=superClazz)
					getField(superClazz,name);
			}
		}
		// 缓存field
		if(null!=field)
			fieldCache.put(key, field);
		
		return field;
	}
}
