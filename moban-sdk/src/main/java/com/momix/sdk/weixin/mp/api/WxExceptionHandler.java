package com.momix.sdk.weixin.mp.api;

import com.momix.sdk.common.exception.SdkException;

/**
 * WxErrorException处理器
 */
public interface WxExceptionHandler {

  public void handle(SdkException e);
}