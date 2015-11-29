package com.momix.sdk.weixin.mp.bean;

import java.util.Enumeration;

public interface WxSession {

  public Object getAttribute(String name);

  public Enumeration<String> getAttributeNames();

  public void setAttribute(String name, Object value);

  public void removeAttribute(String name);

  public void invalidate();

}