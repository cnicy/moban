package com.momix.sdk.weixin.mp.api;

/**
 * Created by rono on 2015/11/26.
 */
public interface WxMpService {
    /**
     * <a href="http://mp.weixin.qq.com/wiki/index.php?title=验证消息真实性">验证推送过来的消息的正确性</a>
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    public boolean checkSignature(String timestamp, String nonce, String signature);

}
