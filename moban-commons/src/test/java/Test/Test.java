package Test;

import com.momix.sdk.net.http.api.MyHttp;
import com.momix.sdk.net.http.api.impl.MyHttpURLConnection;
import com.momix.sdk.parser.exception.ApiException;
import com.momix.sdk.weixin.mp.api.WxMpAuthService;
import com.momix.sdk.weixin.mp.api.WxMpConfig;
import com.momix.sdk.weixin.mp.api.impl.WxMpAuthServiceImpl;
import com.momix.sdk.weixin.mp.exceptions.WxException;

/**
 * Created by rono on 2015/11/27.
 */
public class Test {
    public static void main(String[] args) throws ApiException {
        WxMpConfig config =  WxConfigSingletonInstance.getWxMpConfig();
        MyHttp http = new MyHttpURLConnection();
        WxMpAuthService wxService = new WxMpAuthServiceImpl(http,config);
        try {
            String accessToken =  wxService.getAccessToken();
            System.out.println(Thread.currentThread().getId()+"  "+accessToken);
        } catch (WxException e) {
            e.printStackTrace();
        }

//        for(int i=0;i<10;i++){
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    WxMpConfig config =  WxConfigSingletonInstance.getWxMpConfig();
//                    MyHttp http = new MyHttpURLConnection();
//                    WxMpAuthService wxService = new WxMpAuthServiceImpl(http,config);
//                    try {
//                        String accessToken =  wxService.getAccessToken();
//                        System.out.println(Thread.currentThread().getId()+"  "+accessToken);
//                    } catch (WxException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
    }
}
