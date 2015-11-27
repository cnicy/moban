package Test;

import com.momix.sdk.weixin.mp.api.WxMpConfig;
import com.momix.sdk.weixin.mp.api.impl.defaults.WxMpConfigInMemory;

/**
 * Created by rono on 2015/11/27.
 */
public class WxConfigSingletonInstance {
    private WxConfigSingletonInstance(){}

    public static WxMpConfig getWxMpConfig(){
        return Singleton.WX_MP_CONFIG;
    }

    private static class Singleton{
        private static final WxMpConfig WX_MP_CONFIG;
        static {
            WX_MP_CONFIG = new WxMpConfigInMemory();
            WX_MP_CONFIG.setAppId("wx44ae247ca0e9ace2");
            WX_MP_CONFIG.setSecret("107f66f6ef35d7273e348d41c9765128");
        }
    }
}
