package com.momix.sdk.weixin.mp.commons;

/**
 * Created by rono on 2015/11/27.
 */
public class WxHttpUrl {
    /**http://mp.weixin.qq.com/wiki/2/88b2bf1265a707c031e51f26ca5e6512.html*/
    protected static final String access_token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    // region 菜单
    /**http://mp.weixin.qq.com/wiki/6/95cade7d98b6c1e1040cde5d9a2f9c26.html*/
    protected static final String menu_create  = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
    /**http://mp.weixin.qq.com/wiki/11/51aa2be3cc267a4947216a44b2e25187.html*/
    protected static final String menu_delete  = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";
    /**http://mp.weixin.qq.com/wiki/2/07112acf4bb9a19d50c8ae08515a2a6a.html*/
    protected static final String menu_get     = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";
    // endregion

    /** 获取access_token url*/
    public static String ACCESS_TOKEN(final String appId,final String appSecret){
        return String.format(access_token,appId,appSecret);
    }

    // region
    /** 菜单创建*/
    public static String MENU_CREATE(final String access_token){
        return String.format(menu_create,access_token);
    }
    /**菜单删除*/
    public static String MENU_DELETE(final String access_token){
        return String.format(menu_delete,access_token);
    }
    /** 获取菜单*/
    public static String MENU_GETLALL(final String access_token){
        return String.format(menu_get,access_token);
    }
    // endregion
}
