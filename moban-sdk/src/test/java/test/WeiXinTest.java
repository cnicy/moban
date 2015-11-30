package test;

import com.momix.sdk.common.exception.SdkException;
import com.momix.sdk.net.http.api.MyHttp;
import com.momix.sdk.net.http.api.impl.MyHttpURLConnection;
import com.momix.sdk.weixin.mp.api.*;
import com.momix.sdk.weixin.mp.api.impl.WxMessageInMemoryDuplicateChecker;
import com.momix.sdk.weixin.mp.api.impl.WxMpServiceImpl;
import com.momix.sdk.weixin.mp.bean.WxMpMessage;

/**
 * Created by rono on 2015/11/30.
 */
public class WeiXinTest {
    public static void main(String[] args) {
        WxMpConfig config =  WxConfigSingletonInstance.getWxMpConfig();
        MyHttp http = new MyHttpURLConnection();
        WxMpService wxService = new WxMpServiceImpl(http,config);

        WxMpMessageHandler textHanlder = new TextMessageHandler();
        WxMpMessageHandler imgHandler  = new ImgMessageHandler();
        WxMessageDuplicateChecker checker = new WxMessageInMemoryDuplicateChecker();

        try {
            WxMpMessageRouter router = new WxMpMessageRouter();
            router.setWxMpService(wxService);
            router.setWxMessageDuplicateChecker(checker);

            router.buildRule().msgType("text").async(false).event("click").handler(textHanlder).end()
            .buildRule().msgType("img").event("cl").async(false).handler(imgHandler).end()
            .buildRule().msgType("img").event("click").async(false).handler(imgHandler).end();

            WxMpMessage msg = new WxMpMessage();
            msg.setMsgType("img");
            msg.setMsgId(111L);
            msg.setEvent("click");
            router.route(msg);
           // String accessToken =  wxService.getAccessToken();
           // System.out.println(Thread.currentThread().getId()+"  "+accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
