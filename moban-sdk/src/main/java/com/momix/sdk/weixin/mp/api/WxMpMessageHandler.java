package com.momix.sdk.weixin.mp.api;

import com.momix.sdk.weixin.mp.bean.WxMpMessage;
import com.momix.sdk.weixin.mp.bean.WxMpOutMessage;
import com.momix.sdk.weixin.mp.exceptions.WxException;

import java.util.Map;

/**
 * 消息处理器接口
 * Created by rono on 2015/11/29.
 */
public interface WxMpMessageHandler {
    public WxMpOutMessage handler(WxMpMessage wxMpMessage,Map<String,Object> context,
                                  WxMpService wxMpService,WxSessionManager wxSessionManager)throws WxException;
}
