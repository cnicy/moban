package com.momix.sdk.weixin.mp.exceptions;

import com.momix.sdk.weixin.mp.bean.WxError;

/**
 * <a href="http://mp.weixin.qq.com/wiki/17/fa4e1434e57290788bde25603fa2fcbd.html">微信全局返回码说明</a>
 * Created by rono on 2015/11/26.
 */
public class WxException  extends Exception{
    private WxError exError;

    public WxException(WxError exError) {
        this.exError = exError;
    }

    public WxError getExError() {
        return exError;
    }
}