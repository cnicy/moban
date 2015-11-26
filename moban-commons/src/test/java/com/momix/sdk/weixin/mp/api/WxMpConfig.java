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
    public String getAccessToken();

    /**
     * 当前token是否已经过期，根据过期时间进行对比
     * @return
     */
    public boolean isAccessTokenExpired();

    /**
     * 强制过期accesstoken，也就是将access_token_expire置0
     */
    public void expireAccessToken();

    /**
     * 确保线程安全
     * @param accessToken
     * @param expiresIn
     */
    public void updateAccessToken(String accessToken, int expiresIn);

    /**+
     * 获取jsapi_ticket
     * @return
     */
    public String getJsapiTicket();

    /**
     * 判断jsapi_ticket是否过期
     * @return
     */
    public boolean isJsapiTicketExpired();

    /**
     * 强制过期jsapi_ticket
     */
    public void expireJsapiTicket();

    /**
     * 更新jsapi_ticket 确保线程安全
     * @param jsapiTicket
     * @param expiresInSeconds
     */
    public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds);

    /**
     * appid
     * @return
     */
    public String getAppId();

    /**
     * appsccret
     * @return
     */
    public String getSecret();

    /**
     * 设置appid
     * @return
     */
    public String setAppId();

    /**
     * 设置appsecret
     * @return
     */
    public String setSecret();
}
