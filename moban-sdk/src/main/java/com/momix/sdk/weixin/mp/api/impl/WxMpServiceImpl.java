package com.momix.sdk.weixin.mp.api.impl;

import com.momix.sdk.common.exception.SdkError;
import com.momix.sdk.common.exception.SdkException;
import com.momix.sdk.common.utils.string.StringUtils;
import com.momix.sdk.net.http.api.MyHttp;
import com.momix.sdk.net.http.bean.HttpRequestParams;
import com.momix.sdk.net.http.bean.HttpResponseParam;
import com.momix.sdk.parser.json.JsonParser;
import com.momix.sdk.weixin.mp.api.WxMpConfig;
import com.momix.sdk.weixin.mp.api.WxMpService;
import com.momix.sdk.weixin.mp.bean.WxAccessToken;
import com.momix.sdk.weixin.mp.bean.WxJsapiSignature;
import com.momix.sdk.weixin.mp.bean.WxMenu;
import com.momix.sdk.weixin.mp.commons.SignUtil;
import com.momix.sdk.weixin.mp.commons.WxHttpUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 微信接口业务的默认实现
 * Created by rono on 2015/11/27.
 */
public class WxMpServiceImpl implements WxMpService {
    private static final Logger logger = LoggerFactory.getLogger(WxMpServiceImpl.class);

    private WxMpConfig wxMpConfig;
    /** 全局的是否正在刷新access token的 */
    protected final Object globalAccessTokenRefreshLock = new Object();
    /** 全局的是否正在刷新jsapi_ticket的锁 */
    protected final Object globalJsapiTicketRefreshLock = new Object();
    /**网络请求*/
    private MyHttp http;

    public WxMpServiceImpl() {
        logger.debug("WxMpService init...");
    }

    public WxMpServiceImpl(MyHttp http, WxMpConfig wxMpConfig) {
        this.http = http;
        this.wxMpConfig = wxMpConfig;
    }
    // region 验证信息
    @Override
    public boolean checkSignature(String signature,String timestamp, String nonce) {
        try{
            return SignUtil.checkSignature(wxMpConfig.getAccessToken(),signature,timestamp,nonce);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String getAccessToken() throws SdkException {
        return getAccessToken(false);
    }

    @Override
    public String getAccessToken(boolean forceRefresh) throws SdkException {
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

                        SdkError error = new JsonParser().from(res.getContent(),SdkError.class);
                        if(null!=error && error.getErrcode() !=0){
                            throw new SdkException(error);
                        }
                        WxAccessToken wxAccessToken = WxAccessToken.fromJson(res.getContent());
                        wxMpConfig.updateAccessToken(wxAccessToken.getAccess_token(), wxAccessToken.getExpires_in());
                    }catch (SdkException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return wxMpConfig.getAccessToken();
    }

    @Override
    public String getJsapiTicket() throws SdkException {
        return null;
    }

    @Override
    public String getJsapiTicket(boolean forceRefresh) throws SdkException {
        return null;
    }

    @Override
    public WxJsapiSignature createJsapiSignature(String url) throws SdkException {
        return null;
    }
    // endregion

    // region 菜单信息
    @Override
    public void menuCreate(WxMenu wxMenu) throws SdkException {
        String url = WxHttpUrl.MENU_CREATE(wxMpConfig.getAccessToken());

    }

    // endregion


    public WxMpConfig getWxMpConfig() {
        return wxMpConfig;
    }

    public void setWxMpConfig(WxMpConfig wxMpConfig) {
        this.wxMpConfig = wxMpConfig;
    }

    public MyHttp getHttp() {
        return http;
    }

    public void setHttp(MyHttp http) {
        this.http = http;
    }


}
