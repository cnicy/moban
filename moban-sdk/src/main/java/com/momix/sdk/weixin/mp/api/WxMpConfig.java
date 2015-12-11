package com.momix.sdk.weixin.mp.api;

/**
 * 微信客户端配置信息存储，该配置是一个接口，选择合适的存储方式存储
 * Created by rono on 2015/11/26.
 */
public interface WxMpConfig {
    /**
     * 获取微信accesstoken
     * @return
     */
    String getAccessToken();

    /**
     * 当前token是否已经过期，根据过期时间进行对比
     * @return
     */
    boolean isAccessTokenExpired();

    /**
     * 强制过期accesstoken，也就是将access_token_expire置0
     */
    void expireAccessToken();

    /**
     * 确保线程安全
     * @param accessToken
     * @param expiresIn
     */
    void updateAccessToken(String accessToken, int expiresIn);

    /**+
     * 获取jsapi_ticket
     * @return
     */
    String getJsapiTicket();

    /**
     * 判断jsapi_ticket是否过期
     * @return
     */
    boolean isJsapiTicketExpired();

    /**
     * 强制过期jsapi_ticket
     */
    void expireJsapiTicket();

    /**
     * 更新jsapi_ticket 确保线程安全
     * @param jsapiTicket
     * @param expiresInSeconds
     */
    void updateJsapiTicket(String jsapiTicket, int expiresInSeconds);

    /**
     * appid
     * @return
     */
    String getAppId();

    /**
     * appsccret
     * @return
     */
    String getSecret();

    /**
     * 设置appid
     * @return
     */
    void setAppId(String appId);

    /**
     * 获取微信公众号中设置的 token
     * @return
     */
    String getToken();

    /**
     * 设置token
     * @param token
     */
    void setToken(String token);
    /**
     * 设置appsecret
     * @return
     */
    void setSecret(String secret);

    /**
     * 微信回调地址
     * @return
     */
    String getWxRedirectUrl();
}
