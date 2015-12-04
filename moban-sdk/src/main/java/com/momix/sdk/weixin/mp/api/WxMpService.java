package com.momix.sdk.weixin.mp.api;

import com.momix.sdk.common.exception.SdkException;
import com.momix.sdk.weixin.mp.bean.*;

/**
 * 基础信息
 * Created by rono on 2015/11/26.
 */
public interface WxMpService {
    // region 验证信息
    /**
     * <a href="http://mp.weixin.qq.com/wiki/index.php?title=验证消息真实性">验证推送过来的消息的正确性</a>
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    boolean checkSignature(String timestamp, String nonce, String signature);
    /**
     * 获取access_token, 不强制刷新access_token
     * @see #getAccessToken(boolean)
     * @return
     * @throws SdkException
     */
    String getAccessToken() throws SdkException;

    /**
     * <pre>
     * 获取access_token，本方法线程安全
     * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
     * 另：本service的所有方法都会在access_token过期是调用此方法
     * 程序员在非必要情况下尽量不要主动调用此方法,请调用getAccessToken()
     * <a href="http://mp.weixin.qq.com/wiki/2/88b2bf1265a707c031e51f26ca5e6512.html">获取access token</a>
     * </pre>
     * @param forceRefresh 强制刷新
     * @return
     */
    String getAccessToken(boolean forceRefresh) throws SdkException;

    /**
     * 获得jsapi_ticket,不强制刷新jsapi_ticket
     * @see #getJsapiTicket(boolean)
     * @return
     */
    String getJsapiTicket() throws SdkException;

    /**
     * <pre>
     * 获得jsapi_ticket
     * 获得时会检查jsapiToken是否过期，如果过期了，那么就刷新一下，否则就什么都不干
     *  <a href="http://mp.weixin.qq.com/wiki/7/1c97470084b73f8e224fe6d9bab1625b.html#.E8.8E.B7.E5.8F.96api_ticket">获取api_ticket</a>
     * </pre>
     * @param forceRefresh 强制刷新
     * @return
     * @throws SdkException
     */
    String getJsapiTicket(boolean forceRefresh) throws SdkException;
    /**
     * <pre>
     * <a href="http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E9.99.84.E5.BD.951-JS-SDK.E4.BD.BF.E7.94.A8.E6.9D.83.E9.99.90.E7.AD.BE.E5.90.8D.E7.AE.97.E6.B3.95">创建调用jsapi时所需要的签名</a>
     * </pre>
     * @param url       url
     * @return
     */
    WxJsapiSignature createJsapiSignature(String url) throws SdkException;
    // endregion

    // region 菜单信息

    /**
     * 创建菜单
     * @param wxMenu
     * @throws SdkException
     */
    void menuCreate(WxMenu wxMenu)throws SdkException;

    /**
     * 删除菜单
     * @throws SdkException
     */
    void menuDelete()throws SdkException;

    /**
     * 获取我的微信菜单
     * @return
     * @throws SdkException
     */
    WxMenu menuGetAll()throws SdkException;
    // endregion

    // region 账号管理

    /**
     * 创建二维码ticket
     * @param qrcode
     * @throws SdkException
     */
    WxQrcodeTicket qrcodeCreate(WxQrcode qrcode)throws SdkException;
    // endregion

    // region 发送消息
    void messageTemplateSend(WxMessageTemplate template)throws SdkException;
    // endregion

    // region 用户管理

    /**
     * <pre>
     * 网页授权中的access_token，时间很短，最好是每次都重新获取,网页授权中的access_token，不能复用
     *     <a href="http://mp.weixin.qq.com/wiki/9/01f711493b5a02f24b04365ac5d8fd95.html#.E7.AC.AC.E4.BA.8C.E6.AD.A5.EF.BC.9A.E9.80.9A.E8.BF.87code.E6.8D.A2.E5.8F.96.E7.BD.91.E9.A1.B5.E6.8E.88.E6.9D.83access_token">获取网页授权的access_token</a>
     * </pre>
     * @param code
     * @return
     */
    WxOauthAccessToken oauthAccessToken(final String code);
    // endregion
}
