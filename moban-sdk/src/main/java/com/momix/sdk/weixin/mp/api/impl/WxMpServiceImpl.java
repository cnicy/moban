package com.momix.sdk.weixin.mp.api.impl;

import com.momix.sdk.common.exception.SdkError;
import com.momix.sdk.common.exception.SdkException;
import com.momix.sdk.common.utils.string.RandomUtils;
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
import com.momix.sdk.weixin.mp.commons.WxSignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        return getJsapiTicket(false);
    }

    /**
     * <a href="http://mp.weixin.qq.com/wiki/7/1c97470084b73f8e224fe6d9bab1625b.html#.E9.99.84.E5.BD.951-JS-SDK.E4.BD.BF.E7.94.A8.E6.9D.83.E9.99.90.E7.AD.BE.E5.90.8D.E7.AE.97.E6.B3.95">获取jsapi ticket</a>
     * @param forceRefresh 强制刷新
     * @return
     * @throws SdkException
     */
    @Override
    public String getJsapiTicket(boolean forceRefresh) throws SdkException {
        try {
            if(forceRefresh){
                wxMpConfig.expireJsapiTicket();
            }
            if(wxMpConfig.isJsapiTicketExpired()){
                synchronized (globalJsapiTicketRefreshLock){
                    if(wxMpConfig.isJsapiTicketExpired()){
                        final String url = WxHttpUrl.JSAPI_TICKET(getAccessToken());
                        HttpRequestParams req = new HttpRequestParams();
                        req.setUri(url);
                        HttpResponseParam res = sdkHttp.get(req);
                        SdkError error = new JsonParser().from(res.getContent(), SdkError.class);
                        if(null!=error && error.getErrcode() !=0){
                            throw new SdkException(error);
                        }
                        WxJsapiTicket jsapiTicket = new JsonParser().from(res.getContent(),WxJsapiTicket.class);
                        wxMpConfig.updateJsapiTicket(jsapiTicket.getTicket(),jsapiTicket.getExpires_in());
                    }
                }
            }
        } catch (SdkException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wxMpConfig.getJsapiTicket();
    }

    @Override
    public WxJsapiSignature createJsapiSignature(String url) throws SdkException {
        long timestamp = System.currentTimeMillis() / 1000;
        String noncestr = RandomUtils.getRandomStr();
        String jsapiTicket = getJsapiTicket(false);

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("jsapi_ticket=").append(jsapiTicket).append("&");
            sb.append("noncestr=").append(noncestr).append("&");
            sb.append("timestamp=").append(timestamp).append("&");
            sb.append("url=").append(url);

            String signature = SignUtil.getSHA1Sinture(sb.toString());

            WxJsapiSignature jsapiSignature = new WxJsapiSignature();
            jsapiSignature.setAppid(wxMpConfig.getAppId());
            jsapiSignature.setTimestamp(timestamp);
            jsapiSignature.setNoncestr(noncestr);
            jsapiSignature.setUrl(url);
            jsapiSignature.setSignature(signature);
            return jsapiSignature;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public WxOauthAccessToken oauthAccessToken(String code)throws SdkException {
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

    // region 微信支付
    @Override
    public WxPayBrandWCPayRequest getWxPayBrandWCPayRequest(final WxMpPrePayUnifiedorderReq req)throws SdkException {
        WxMpPrePayUnifiedorderRes res = getPrePayUnifiedorder(req);
        final String prepay_id =res.getPrepay_id();
        final long timestamp = System.currentTimeMillis() / 1000;
        final String noncestr = RandomUtils.getRandomStr();
        final String sign_type = "MD5";

        Map<String, String> payInfo = new HashMap<>();
        payInfo.put("appId", wxMpConfig.getAppId());
        // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
        payInfo.put("timeStamp",timestamp + "");
        payInfo.put("nonceStr", noncestr + "");
        payInfo.put("package", "prepay_id=" + prepay_id);
        payInfo.put("signType", sign_type);
        String finalSign = WxSignUtils.createSign(payInfo, wxMpConfig.getMerchantKey());
        payInfo.put("paySign", finalSign);

        WxPayBrandWCPayRequest result = new WxPayBrandWCPayRequest();
        result.setAppId(wxMpConfig.getAppId());
        result.setNonceStr(noncestr);
        result.setPackages(payInfo.get("package"));
        result.setPaySign(finalSign);
        result.setSignType(sign_type);
        result.setTimeStamp(timestamp+"");

        return result;
    }

    @Override
    public WxMpPrePayUnifiedorderRes getPrePayUnifiedorder(final WxMpPrePayUnifiedorderReq req) throws SdkException {
        try {
            String url = WxHttpUrl.PAT_UNIFIEDORDER();
            String nonce_str = System.currentTimeMillis() + "";

            Map<String,String> parameters = new HashMap<>();
            parameters.put("appid",wxMpConfig.getAppId());
            parameters.put("mch_id",wxMpConfig.getMerchantId());
            parameters.put("nonce_str",nonce_str);

            WxMpPrePayUnifiedorderReq.toMap(req,parameters);
            String sign = WxSignUtils.createSign(parameters,wxMpConfig.getMerchantKey());
            parameters.put("sign", sign);

            StringBuilder requestParams = new StringBuilder("<xml>");
            for (Map.Entry<String, String> para : parameters.entrySet()) {
                requestParams.append(String.format("<%s>%s</%s>", para.getKey(), para.getValue(), para.getKey()));
            }
            requestParams.append("</xml>");
            HttpRequestParams params = new HttpRequestParams();
            params.setUri(url);
            params.setRequestParams(requestParams.toString());
            // 发起http请求
            HttpResponseParam resParams = sdkHttp.post(params);
            WxMpPrePayUnifiedorderRes res = xmlParser.from(resParams.getContent(),WxMpPrePayUnifiedorderRes.class);
            if(null!=res.getResult_code() && "SUCCESS".equals(res.getResult_code())){
                return res;
            }else
                throw new SdkException(res.getReturn_msg());
        } catch (SdkException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // endregion
    @Override
    public byte[] getTemporaryMedia(String mediaId) throws SdkException {
        final String url = WxHttpUrl.MEDIA_GET(getAccessToken(),mediaId);
        HttpRequestParams params = new HttpRequestParams();
        params.setUri(url);
        try {
            HttpResponseParam responseParam =  sdkHttp.getByte(params);
            if(null!=responseParam.getContents() && responseParam.getContents().length>0){
                return responseParam.getContents();
            } else{
                SdkError sdkError = jsonParser.from(responseParam.getContent(), SdkError.class);
                throw  new SdkException(sdkError);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw new SdkException("网络异常！");
        }
    }
    // region 文件、素材


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
