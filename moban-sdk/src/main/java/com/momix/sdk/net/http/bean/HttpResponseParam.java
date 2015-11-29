package com.momix.sdk.net.http.bean;

import java.io.Serializable;

/**
 * Created by rono on 2015/11/27.
 */
public class HttpResponseParam implements Serializable{
    /** 返回码*/
    private Integer statusCode;
    /** 返回消息码*/
    private String statusMsg;
    /** 返回字符串*/
    private String content;
    /** 返回字符数组*/
    private byte[] contents;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}
