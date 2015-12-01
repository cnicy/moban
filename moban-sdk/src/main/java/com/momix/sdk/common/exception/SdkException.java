package com.momix.sdk.common.exception;

/**
 * sdk异常处理类
 * Created by rono on 2015/11/30.
 */
public class SdkException extends Exception{
    private SdkError sdkError;

    public SdkException(SdkError error){
        this.sdkError = error;
    }

    public SdkException(Exception e){
        this.sdkError = new SdkError(e.getMessage());
    }

    public SdkException(String msg){
        this.sdkError = new SdkError(msg);
    }

    public SdkException(final int errcode,final String errmsg){
        this.sdkError = new SdkError(errcode,errmsg);
    }

    public SdkException(String message, Throwable cause) {
        super(message, cause);
    }

    public SdkError getSdkError() {
        return sdkError;
    }

    public void setSdkError(SdkError sdkError) {
        this.sdkError = sdkError;
    }

    public String toString(){
        if(null!=sdkError)
            return sdkError.toString();
        return null;
    }
}
