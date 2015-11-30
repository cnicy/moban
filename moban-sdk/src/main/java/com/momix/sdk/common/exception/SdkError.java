package com.momix.sdk.common.exception;

import java.io.Serializable;

/**
 * sdk错误处理封装
 * Created by rono on 2015/11/30.
 */
public class SdkError implements Serializable{
    private int errcode;

    private String errmsg;

    public SdkError() {
    }

    public SdkError(String errmsg) {
        this.errmsg = errmsg;
    }

    public SdkError(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

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
}
