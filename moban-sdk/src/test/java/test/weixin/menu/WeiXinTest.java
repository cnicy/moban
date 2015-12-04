package test.weixin.menu;

import com.momix.sdk.common.exception.SdkException;
import com.momix.sdk.net.http.api.SdkHttp;
import com.momix.sdk.net.http.api.impl.SdkHttpURLConnection;
import com.momix.sdk.parser.api.Parser;
import com.momix.sdk.parser.json.JsonParser;
import com.momix.sdk.parser.xml.XmlParser;
import com.momix.sdk.weixin.mp.api.*;
import com.momix.sdk.weixin.mp.api.impl.WxMessageInMemoryDuplicateChecker;
import com.momix.sdk.weixin.mp.api.impl.WxMpConfigInMemory;
import com.momix.sdk.weixin.mp.api.impl.WxMpServiceImpl;
import com.momix.sdk.weixin.mp.bean.*;

/**
 * Created by rono on 2015/11/30.
 */
public class WeiXinTest {
    public static void main(String[] args) throws SdkException {
        WxOauthAccessToken tokean = test123(WxOauthAccessToken.class);
        System.out.println(tokean.getOpenid());
    }

    public static <T> T test123(Class<T> res){
        String s= "{\"access_token\":\"OezXcEiiBSKSxW0eoylIeG6GQ5czNfAgrZVxdultRogtnblKDqXQLrC_-0VIkxLy3qkDigE6SM6eO16ykZ2iAMhMJxYDudvDP1yIjvpykfbNIeFyelul1muVUCcnlC0F-Q94n8r6F3e1lsmYZ4lXHg\",\"expires_in\":7200,\"refresh_token\":\"OezXcEiiBSKSxW0eoylIeG6GQ5czNfAgrZVxdultRogtnblKDqXQLrC_-0VIkxLysElq3GGcn4AIzrDefYbZybJvfLZ0bu5HpF3WhAqJGK7x04Z85SnkYYYipvkDfipQKSdpF-uX_1KGdGQLJph2gw\",\"openid\":\"oazO5uASD-Pii2Dl0fmXSJ2msUTI\",\"scope\":\"snsapi_base\"}";
        try {
            return (T)new JsonParser().from(s, WxOauthAccessToken.class);
        } catch (SdkException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void testQrcode() throws SdkException {
        WxMpConfig config = new WxMpConfigInMemory();
        config.setAppId("wx44ae247ca0e9ace2");
        config.setSecret("107f66f6ef35d7273e348d41c9765128");
        config.setToken("59cc6b8faab59a224fbfa26243f5c8e8");

        SdkHttp http = new SdkHttpURLConnection();
        Parser jsonParser = new JsonParser();
        WxMpService wxService = new WxMpServiceImpl(http,config,jsonParser,null);

        WxQrcode qrcode = new WxQrcode();
        qrcode.setAction_name("QR_LIMIT_SCENE");

        WxQrcode.ActionInfo actionInfo = new WxQrcode.ActionInfo();
        qrcode.setAction_info(actionInfo);

        WxQrcode.ActionInfo.Sence sence = new WxQrcode.ActionInfo.Sence();
        sence.setScene_id(10086);
        // sence.setScene_str("sence-str");

        actionInfo.setScene(sence);

        WxQrcodeTicket ticket =  wxService.qrcodeCreate(qrcode);
        System.out.println(new JsonParser().to(ticket));
    }

    public static void test(){
        String s = "<xml><ToUserName><![CDATA[gh_a4473d322004]]></ToUserName><FromUserName><![CDATA[ocSa0uHlO4eiuQ_dwg3b8Zkdcr4Q]]></FromUserName><CreateTime>1448965608</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[unsubscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>";
        try {
           WxMpMessage msg =  new XmlParser().from(s,WxMpMessage.class);
            System.out.println(msg.toString());
        } catch (SdkException e) {
            e.printStackTrace();
        }
    }

    public static void test2() throws SdkException {
        WxMpConfig config = new WxMpConfigInMemory();
        config.setAppId("wx44ae247ca0e9ace2");
        config.setSecret("107f66f6ef35d7273e348d41c9765128");
        config.setToken("59cc6b8faab59a224fbfa26243f5c8e8");

        SdkHttp http = new SdkHttpURLConnection();
        Parser jsonParser = new JsonParser();
        WxMpService wxService = new WxMpServiceImpl(http,config,jsonParser,null);

        WxMpMessageHandler textHanlder = new TextMessageHandler();
        WxMpMessageHandler imgHandler  = new ImgMessageHandler();
        WxMessageDuplicateChecker checker = new WxMessageInMemoryDuplicateChecker();

        WxMpMessageRouter router = new WxMpMessageRouter();
        router.setWxMpService(wxService);
        router.setWxMessageDuplicateChecker(checker);
    }

    public static void test1(){
        WxMpConfig config = new WxMpConfigInMemory();
        config.setAppId("wx44ae247ca0e9ace2");
        config.setSecret("107f66f6ef35d7273e348d41c9765128");
        config.setToken("59cc6b8faab59a224fbfa26243f5c8e8");

        SdkHttp http = new SdkHttpURLConnection();
        Parser jsonParser = new JsonParser();
        WxMpService wxService = new WxMpServiceImpl(http,config,jsonParser,null);

        WxMpMessageHandler textHanlder = new TextMessageHandler();
        WxMpMessageHandler imgHandler  = new ImgMessageHandler();
        WxMessageDuplicateChecker checker = new WxMessageInMemoryDuplicateChecker();

        String timestamp ="1448962377";
        String nonce = "1169445664";
        String sign  = "26b5a3ea3c8efbc63678ffba0dbfb61743dbc2c9";

        System.out.println(wxService.checkSignature(timestamp, nonce, sign));
        try {

//            WxMenu menu = new WxMenu();
//            WxMenu.WxButton btn = new WxMenu.WxButton();
//            btn.setName("name");
//            // wxService.menuCreate(menu);
//            WxMenu wxMenu =  wxService.menuGetAll();
//            System.out.println(wxMenu);



            WxMpMessageRouter router = new WxMpMessageRouter();
            router.setWxMpService(wxService);
            router.setWxMessageDuplicateChecker(checker);

            router.buildRule().msgType("text").async(false).event("click").handler(textHanlder).end()
                    .buildRule().msgType("img").event("cl").async(false).handler(imgHandler).end()
                    .buildRule().msgType("img").event("click").async(false).handler(imgHandler).end();



            WxMpMessage msg = new WxMpMessage();
            msg.setMsgType("img");
            //msg.setMsgId("111");
            msg.setEvent("click");
            router.route(msg);
            String accessToken =  wxService.getAccessToken();
            System.out.println(Thread.currentThread().getId()+"  "+accessToken);
        } catch (SdkException e){
            System.out.println(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
