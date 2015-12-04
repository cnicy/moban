package com.momix.sdk.weixin.mp.commons;

public class WxConsts {
  ///////////////////////
  // 微信推送过来的消息的类型，和发送给微信xml格式消息的消息类型
  ///////////////////////
  public static final String MSG_TYPE_TEXT = "text";
  public static final String MSG_TYPE_IMAGE = "image";
  public static final String MSG_TYPE_VOICE = "voice";
  public static final String MSG_TYPE_VIDEO = "video";
  public static final String MSG_TYPE_NEWS = "news";
  public static final String MSG_TYPE_MUSIC = "music";
  public static final String MSG_TYPE_LOCATION = "location";
  public static final String MSG_TYPE_LINK = "link";
  public static final String MSG_TYPE_EVENT = "event";
  public static final String XML_TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";
  ///////////////////////
  // 微信端推送过来的事件类型
  ///////////////////////
  public static final String EVENTT_SUBSCRIBE = "subscribe";
  public static final String EVENTT_UNSUBSCRIBE = "unsubscribe";
  public static final String EVENTT_SCAN = "scan";
  public static final String EVENT_VIEW = "VIEW";
}
