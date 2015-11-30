package com.momix.sdk.parser.xml;

import com.momix.sdk.common.exception.SdkException;
import com.momix.sdk.parser.api.Converter;
import com.momix.sdk.parser.api.Converters;
import com.momix.sdk.parser.api.ObjectReader;
import com.momix.sdk.parser.api.ObjectWriter;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class XmlConverter implements Converter {
	@Override
	public <T> T fromResponse(String text, Class<T> clazz) throws SdkException {
		Element root = XmlUtils.getRootElementFromString(text);
		return getModelFromXML(root,clazz);
	}

	@Override
	public String toResponse(Object obj) throws SdkException {
		Element root = XmlUtils.createRootElement("xml");
		setModelFromObject(obj,root);
		return XmlUtils.nodeToString(root);
	}

	private <T> T getModelFromXML(final Element element,Class<T> clazz) throws SdkException{
		return Converters.reader(clazz, new ObjectReader() {
			/**
			 * 判断xml节点中是否存在name属性
			 */
			@Override
			public boolean hasReturnField(Object name) {
				return null != XmlUtils.getChildElements(element, (String) name);
			}

			@Override
			public Object getPrimitiveObject(Object name) {
				return XmlUtils.getChildElementValue(element, (String) name);
			}

			@Override
			public Object getObject(Object name, Class<?> type) throws SdkException {
				Element childE = XmlUtils.getChildElement(element, (String) name);
				if (childE != null) {
					// 递归遍历获取对象数据
					return getModelFromXML(childE, type);
				} else {
					return null; // 递归结束条件
				}
			}

			/**
			 * <ListName>
			 <ItemName>
			 <Item><![CDATA[item1]]></Item>
			 </ItemName>
			 <ItemName>
			 <Item>item2</Item>
			 </ItemName>
			 </ListName>
			 */
			@Override
			public List<?> getListObjects(Object listName, Object itemName, Class<?> subType) throws SdkException {
				List<Object> list = null;
				// 获取list节点
				Element listE = XmlUtils.getChildElement(element, (String) listName);
				if (null != listE) {
					list = new ArrayList<Object>();
					List<Element> itemEs = XmlUtils.getChildElements(listE, (String) itemName);
					for (Element itemE : itemEs) {
						Object obj = null;
						final String value = XmlUtils.getElementValue(itemE);

						if (String.class.isAssignableFrom(subType)) {
							obj = value;
						} else if (Integer.class.isAssignableFrom(subType)) {
							obj = Integer.valueOf(value);
						} else if (Long.class.isAssignableFrom(subType)) {
							obj = Long.valueOf(value);
						} else if (Double.class.isAssignableFrom(subType)) {
							obj = Double.valueOf(value);
						} else if (Float.class.isAssignableFrom(subType)) {
							obj = Float.valueOf(value);
						} else if (Boolean.class.isAssignableFrom(subType)) {
							obj = Boolean.valueOf(value);
						} else if (Date.class.isAssignableFrom(subType)) {
							DateFormat format = new SimpleDateFormat(Converters.DATE_FORMAT);
							try {
								obj = format.parse(value);
							} catch (ParseException e) {
								throw new SdkException(e);
							}
						} else {
							// 其他嵌套类型，再次递归获取
							obj = getModelFromXML(itemE, subType);
						}
						if (null != obj)
							list.add(obj);
					}
				}
				return list;
			}
		});
	}
	private void setModelFromObject(final Object obj,final Element element) throws SdkException{
		Converters.reader(obj, new ObjectWriter() {
			@Override
			public void setPrimitiveObject(String name, Object value) {
				XmlUtils.appendElement(element, name,(String)value);
			}
			@Override
			public void setObject(String name,Object subValue) throws SdkException {
				Element childE = XmlUtils.createRootElement(name);
				// 递归获取对象下面的数据
				if(null !=subValue){
					setModelFromObject(subValue,childE);
					// 创建一个xml element对象，并写入到root中
					XmlUtils.appendElement(element, childE);
				}else
					return ; // 递归结束条件
			}
			@Override
			public void setListObjects(String listName, String itemName,
									   Object subValues, Class<?> subType) throws SdkException {
				Element listE = XmlUtils.createRootElement(listName);

				if(null!=listE){
					if(subValues instanceof List){
						Iterator<?> iter = ((List<?>) subValues).iterator();
						while(iter.hasNext()){
							Object value = iter.next();
							Element childE = null;
							if (String.class.isAssignableFrom(subType)) {
								XmlUtils.appendElement(listE, itemName, value.toString());

							}else if (Integer.class.isAssignableFrom(subType)) {
								XmlUtils.appendElement(listE, itemName, value.toString());

							} else if (Long.class.isAssignableFrom(subType)) {
								XmlUtils.appendElement(listE, itemName, value.toString());

							}else if (Float.class.isAssignableFrom(subType)) {
								XmlUtils.appendElement(listE, itemName, value.toString());

							}else if (Double.class.isAssignableFrom(subType)) {
								XmlUtils.appendElement(listE, itemName, value.toString());

							}  else if (Boolean.class.isAssignableFrom(subType)) {
								XmlUtils.appendElement(listE, itemName, value.toString());

							}else{
								// 其他嵌套类型，再次递归获取
								childE = XmlUtils.createRootElement(itemName);
								setModelFromObject(value,childE);
								XmlUtils.appendElement(listE, childE);
							}
						}
					}
					XmlUtils.appendElement(element, listE);
				}
			}
		});
	}
}