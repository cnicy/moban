package com.momix.sdk.weixin.mp.api.impl;

import com.momix.sdk.weixin.mp.api.WxMpConfig;
import sun.java2d.pipe.PixelToParallelogramConverter;

/**
 * 默认采用默认存储方式，存储到内存，如果是分布式系统，已改自行实现
 * Created by rono on 2015/11/27.
 */
public class WxMpConfigInMemory implements WxMpConfig{
    protected  volatile long access_token_expires_time;    // access_token过期时间
    protected  volatile long jsapiTicketExpiresTime;    // jsp_ticket 过期时间
    protected  volatile String access_token;
    protected  volatile String jsapiTicket;
    protected  volatile String appId;
    protected  volatile String secret;
    protected  volatile String token;

    public WxMpConfigInMemory() {
        System.out.println("(*^__^*) ……>>>>>>>>>>>>>>>>>>>>>>>>> WxMpConfigInMemory init...............");
    }

    @Override
    public String getAccessToken() {
        return access_token;
    }

    @Override
    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.access_token_expires_time;
    }

    @Override
    public void expireAccessToken() {
        access_token_expires_time = 0;
    }

    @Override
    public void updateAccessToken(String accessToken, int expiresIn) {
        this.access_token = accessToken;
        this.access_token_expires_time = System.currentTimeMillis() + (expiresIn-200) * 1000L;
    }

    @Override
    public String getJsapiTicket() {
        return jsapiTicket;
    }

    @Override
    public boolean isJsapiTicketExpired() {
        return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
    }

    @Override
    public void expireJsapiTicket() {
        this.jsapiTicketExpiresTime =0;
    }

    @Override
    public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
        this.jsapiTicket = jsapiTicket;
        this.jsapiTicketExpiresTime = System.currentTimeMillis() + (expiresInSeconds-200) * 1000L;
    }

    @Override
    public String getAppId() {
        return this.appId;
    }

    @Override
    public String getSecret() {
        return this.secret;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
