package test.weixin.menu;

import com.momix.sdk.common.exception.SdkException;
import com.momix.sdk.weixin.mp.api.WxMpMessageHandler;
import com.momix.sdk.weixin.mp.api.WxMpService;
import com.momix.sdk.weixin.mp.api.WxSessionManager;
import com.momix.sdk.weixin.mp.bean.WxMpMessage;
import com.momix.sdk.weixin.mp.bean.WxMpOutMessage;

import java.util.Map;

/**
 * Created by rono on 2015/11/30.
 */
public class TextMessageHandler implements WxMpMessageHandler {
    @Override
    public WxMpOutMessage handler(WxMpMessage wxMpMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager wxSessionManager) throws SdkException {
        System.out.println("text handler");
        return null;
    }
}
