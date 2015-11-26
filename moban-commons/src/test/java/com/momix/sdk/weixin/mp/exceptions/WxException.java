package com.momix.sdk.weixin.mp.exceptions;

import java.io.Serializable;

/**
 * <a href="http://mp.weixin.qq.com/wiki/17/fa4e1434e57290788bde25603fa2fcbd.html">微信全局返回码说明</a>
 * Created by rono on 2015/11/26.
 */
public class WxException implements Serializable {
    private int errorCode;

    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}