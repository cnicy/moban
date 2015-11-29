package com.momix.sdk.weixin.mp.api;

import com.momix.sdk.weixin.mp.exceptions.WxException;

/**
 * WxErrorException处理器
 */
public interface WxExceptionHandler {

  public void handle(WxException e);
}