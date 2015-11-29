package com.momix.sdk.parser.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD, ElementType.METHOD})
public @interface XmlAttribute {
    /**
     * Name of the XML Schema attribute. By default, the XML Schema
     * attribute name is derived from the JavaBean property name.
     *
     */
    String name() default "";
    /**
     * list field name
     * @return
     */
    String listName() default "";
    /**
     * Specifies if the XML Schema attribute is optional or
     * required. If true, then the JavaBean property is mapped to a
     * XML Schema attribute that is required. Otherwise it is mapped
     * to a XML Schema attribute that is optional.
     *
     */
     boolean required() default false;

    /**
     * Specifies the XML target namespace of the XML Schema
     * attribute.
     *
     */
    String namespace() default "" ;
}