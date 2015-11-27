package com.momix.sdk.weixin.mp.bean;

import com.momix.sdk.parser.exception.ApiException;
import com.momix.sdk.parser.json.JsonParser;

import java.io.Serializable;

/**
 * <a href="http://mp.weixin.qq.com/wiki/17/fa4e1434e57290788bde25603fa2fcbd.html">微信全局返回码说明</a>
 * Created by rono on 2015/11/27.
 */
public class WxError implements Serializable{
    private int errcode;

    private String errmsg;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public static WxError fromJson(final String json) throws ApiException {
        return new JsonParser().from(json,WxError.class);
    }
}
