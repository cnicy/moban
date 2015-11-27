package com.momix.sdk.weixin.mp.api.impl.defaults;

import com.momix.sdk.net.http.api.MyHttp;
import com.momix.sdk.net.http.bean.HttpRequestParams;
import com.momix.sdk.net.http.bean.HttpResponseParam;
import com.momix.sdk.parser.exception.ApiException;
import com.momix.sdk.weixin.mp.api.WxMpConfig;
import com.momix.sdk.weixin.mp.api.WxMpAuthService;
import com.momix.sdk.weixin.mp.bean.WxAccessToken;
import com.momix.sdk.weixin.mp.bean.WxError;
import com.momix.sdk.weixin.mp.bean.WxJsapiSignature;
import com.momix.sdk.weixin.mp.commons.WxHttpUrl;
import com.momix.sdk.weixin.mp.exceptions.WxException;

import java.io.IOException;

/**
 * 微信接口业务的默认实现
 * Created by rono on 2015/11/27.
 */
public class WxMpAuthServiceImpl implements WxMpAuthService {
    private WxMpConfig wxMpConfig;
    /** 全局的是否正在刷新access token的 */
    protected final Object globalAccessTokenRefreshLock = new Object();
    /** 全局的是否正在刷新jsapi_ticket的锁 */
    protected final Object globalJsapiTicketRefreshLock = new Object();
    /**网络请求*/
    private MyHttp http;

    public WxMpAuthServiceImpl(MyHttp http, WxMpConfig wxMpConfig) {
        this.http = http;
        this.wxMpConfig = wxMpConfig;
    }

    @Override
    public boolean checkSignature(String timestamp, String nonce, String signature) {
        return false;
    }

    @Override
    public String getAccessToken() throws WxException {
        return getAccessToken(false);
    }

    @Override
    public String getAccessToken(boolean forceRefresh) throws WxException {
        if(forceRefresh){
            wxMpConfig.expireAccessToken(); // 强制过期access_token
        }
        if (wxMpConfig.isAccessTokenExpired()){ // 过期就重新获取
            synchronized (globalAccessTokenRefreshLock){
                if(wxMpConfig.isAccessTokenExpired()){ // 防止多线程重复刷新
                    try {
                        String url = WxHttpUrl.ACCESS_TOKEN(wxMpConfig.getAppId(),wxMpConfig.getSecret());
                        HttpRequestParams req = new HttpRequestParams();
                        req.setUri(url);
                        HttpResponseParam res = http.get(req);

                        WxError error = WxError.fromJson(res.getContent());
                        if(null!=error && error.getErrcode() !=0){
                            throw new WxException(error);
                        }
                        WxAccessToken wxAccessToken = WxAccessToken.fromJson(res.getContent());
                        wxMpConfig.updateAccessToken(wxAccessToken.getAccess_token(), wxAccessToken.getExpires_in());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }catch (ApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return wxMpConfig.getAccessToken();
    }

    @Override
    public String getJsapiTicket() throws WxException {
        return null;
    }

    @Override
    public String getJsapiTicket(boolean forceRefresh) throws WxException {
        return null;
    }

    @Override
    public WxJsapiSignature createJsapiSignature(String url) throws WxException {
        return null;
    }
}
