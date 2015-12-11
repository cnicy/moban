package com.momix.sdk.weixin.mp.api.impl;

import com.momix.sdk.weixin.mp.api.WxMpConfig;

/**
 * 默认采用默认存储方式，存储到内存，如果是分布式系统，已改自行实现
 * Created by rono on 2015/11/27.
 */
public class WxMpConfigInMemory implements WxMpConfig{
    protected  volatile long accessTokenExpiresTime;    // access_token过期时间
    protected  volatile long jsapiTicketExpiresTime;    // jsp_ticket 过期时间
    protected  volatile String accessToken;
    protected  volatile String jsapiTicket;
    protected  volatile String appId;
    protected  volatile String secret;
    protected  volatile String token;
    protected  volatile String wxRedirectUrl;

    public WxMpConfigInMemory() {
        System.out.println("(*^__^*) ……>>>>>>>>>>>>>>>>>>>>>>>>> WxMpConfigInMemory init...............");
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.accessTokenExpiresTime;
    }

    @Override
    public void expireAccessToken() {
        accessTokenExpiresTime = 0;
    }

    @Override
    public void updateAccessToken(String accessToken, int expiresIn) {
        this.accessToken = accessToken;
        this.accessTokenExpiresTime = System.currentTimeMillis() + (expiresIn-200) * 1000L;
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

    @Override
    public String getWxRedirectUrl() {
        return wxRedirectUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setWxRedirectUrl(String wxRedirectUrl) {
        this.wxRedirectUrl = wxRedirectUrl;
    }
}
