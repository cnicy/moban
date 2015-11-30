package com.momix.sdk.weixin.mp.commons;

/**
 * Created by rono on 2015/11/27.
 */
public class WxHttpUrl {
    /**http://mp.weixin.qq.com/wiki/2/88b2bf1265a707c031e51f26ca5e6512.html*/
    public static final String access_token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    /**http://mp.weixin.qq.com/wiki/6/95cade7d98b6c1e1040cde5d9a2f9c26.html*/
    public static final String menu_create  ="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";

    /** 获取access_token url*/
    public static String ACCESS_TOKEN(final String appId,final String appSecret){
        return String.format(access_token,appId,appSecret);
    }
    /** 菜单创建*/
    public static String MENU_CREATE(final String access_token){
        return String.format(menu_create,access_token);
    }


}
