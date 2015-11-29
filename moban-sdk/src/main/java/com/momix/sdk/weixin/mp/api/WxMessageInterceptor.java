package com.momix.sdk.weixin.mp.api;

import com.momix.sdk.weixin.mp.api.WxMpService;
import com.momix.sdk.weixin.mp.api.WxSessionManager;
import com.momix.sdk.weixin.mp.bean.WxMpMessage;

import java.util.Map;

/**
 * 微信消息拦截器
 * Created by rono on 2015/11/29.
 */
public interface WxMessageInterceptor {
    /**
     * 拦截微信信息
     * @param wxMpMessage
     * @param context       用户在handler和interceptor之间传递消息
     * @param wxMpService
     * @param wxSessionManager
     * @return
     */
    public boolean intercept(WxMpMessage wxMpMessage,Map<String,Object> context,
                             WxMpService wxMpService,WxSessionManager wxSessionManager);
}
