package com.momix.sdk.weixin.mp.commons;

/**
 * Created by rono on 2015/11/27.
 */
public class WxHttpUrl {
    /**http://mp.weixin.qq.com/wiki/2/88b2bf1265a707c031e51f26ca5e6512.html*/
    public static final String access_token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    public static String ACCESS_TOKEN(final String appId,final String appSecret){
        return String.format(access_token,appId,appSecret);
    }
}
