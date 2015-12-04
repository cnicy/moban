package com.momix.sdk.weixin.mp.api.impl;

import com.momix.sdk.common.exception.SdkError;
import com.momix.sdk.common.exception.SdkException;
import com.momix.sdk.common.utils.string.StringUtils;
import com.momix.sdk.net.http.api.SdkHttp;
import com.momix.sdk.net.http.bean.HttpRequestParams;
import com.momix.sdk.net.http.bean.HttpResponseParam;
import com.momix.sdk.parser.api.Parser;
import com.momix.sdk.parser.json.JsonParser;
import com.momix.sdk.weixin.mp.api.WxMpConfig;
import com.momix.sdk.weixin.mp.api.WxMpService;
import com.momix.sdk.weixin.mp.bean.*;
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
    private SdkHttp sdkHttp;
    /** json转换器*/
    private Parser jsonParser;
    /** xml转换器*/
    private Parser xmlParser;

    public WxMpServiceImpl() {
        System.out.println("(*^__^*) ……>>>>>>>>>>>>>>>>>>>>>>>>> WxMpServiceImpl init...............");
    }

    public WxMpServiceImpl(SdkHttp http, WxMpConfig wxMpConfig,Parser jsonParser,Parser xmlParser) {
        this.sdkHttp = http;
        this.wxMpConfig = wxMpConfig;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }
    // region 验证信息
    @Override
    public boolean checkSignature(String timestamp, String nonce, String signature) {
        try{
            return SignUtil.checkSignature(wxMpConfig.getToken(), signature, timestamp, nonce);
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
                        HttpResponseParam res = sdkHttp.get(req);

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
        try {
            String url = WxHttpUrl.MENU_CREATE(getAccessToken());
            post(url, wxMenu, wxMenu.getClass(), jsonParser);
        } catch (SdkException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void menuDelete() throws SdkException {
        try {
            String url = WxHttpUrl.MENU_DELETE(getAccessToken());
            get(url, null, jsonParser);
        } catch (SdkException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WxMenu menuGetAll() throws SdkException {
        try {
            String url = WxHttpUrl.MENU_GETLALL(getAccessToken());
            return get(url, WxMenu.class, jsonParser);
        } catch (SdkException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    // endregion

    // region   账号管理
    @Override
    public WxQrcodeTicket qrcodeCreate(WxQrcode qrcode) throws SdkException {
        try {
            String url = WxHttpUrl.QRCODE_CREATE(getAccessToken());
            return post(url,qrcode,WxQrcodeTicket.class,jsonParser);
        } catch (SdkException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // endregion

    // region 消息管理
    @Override
    public void messageTemplateSend(WxMessageTemplate template) throws SdkException {

        try {
            String url = WxHttpUrl.MESSAGE_TEMPLATE(getAccessToken());
            post(url,template,null,jsonParser);
        } catch (SdkException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // endregion

    // region 用户管理

    /**
     * 网页授权中的access_token，时间很短，最好是每次都重新获取,网页授权中的access_token，不能复用
     * @param code
     * @return
     */
    @Override
    public WxOauthAccessToken oauthAccessToken(String code) {
        try {
            String url = WxHttpUrl.OAUTH_ACCESS_TOKEN(wxMpConfig.getAppId(),wxMpConfig.getSecret(),code);
            return get(url,WxOauthAccessToken.class,jsonParser);
        } catch (SdkException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    // endregion



    // region 微信网络请求
    public <T, E> T post(String url, E req, Class<T> res, Parser parser) throws SdkException, IOException {
        HttpRequestParams params = new HttpRequestParams();
        params.setUri(url);
        params.setRequestParams(parser.to(req));
        HttpResponseParam resParams = sdkHttp.post(params);
        SdkError sdkError = parser.from(resParams.getContent(), SdkError.class);
        if(null!=sdkError && sdkError.getErrcode() !=0){
            // token 过期
            if (sdkError.getErrcode() == 42001 || sdkError.getErrcode() == 40001) {
                String access_token =  getAccessToken(true);
                url = StringUtils.replaceHttpParams(url, "access_token", access_token);
                return post(url,req,res,parser);
            }
            throw new SdkException(sdkError);
        }else if(null!=resParams.getContent() && null!=res){
            return (T)parser.from(resParams.getContent(),res);
        }
        return null;
    }

    public <T> T get(String url,Class<T> res, Parser parser) throws SdkException, IOException {
        HttpRequestParams params = new HttpRequestParams();
        params.setUri(url);
        HttpResponseParam resParams = sdkHttp.get(params);
        SdkError sdkError = parser.from(resParams.getContent(), SdkError.class);
        if(null!=sdkError && sdkError.getErrcode() !=0){
            // token 过期
            if (sdkError.getErrcode() == 42001 || sdkError.getErrcode() == 40001) {
                String access_token =  getAccessToken(true);
                url = StringUtils.replaceHttpParams(url, "access_token", access_token);
                return get(url, res, parser);
            }
            throw new SdkException(sdkError);
        }else if(null!=resParams.getContent() && null!=res){
            return (T)parser.from(resParams.getContent(),res);
        }
        return null;
    }
    // endregion

    public WxMpConfig getWxMpConfig() {
        return wxMpConfig;
    }

    public void setWxMpConfig(WxMpConfig wxMpConfig) {
        this.wxMpConfig = wxMpConfig;
    }

    public SdkHttp getSdkHttp() {
        return sdkHttp;
    }

    public void setSdkHttp(SdkHttp sdkHttp) {
        this.sdkHttp = sdkHttp;
    }

    public Parser getJsonParser() {
        return jsonParser;
    }

    public void setJsonParser(Parser jsonParser) {
        this.jsonParser = jsonParser;
    }

    public Parser getXmlParser() {
        return xmlParser;
    }

    public void setXmlParser(Parser xmlParser) {
        this.xmlParser = xmlParser;
    }
}
