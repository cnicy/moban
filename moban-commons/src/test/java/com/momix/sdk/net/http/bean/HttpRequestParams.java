package com.momix.sdk.net.http.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by rono on 2015/11/27.
 */
public class HttpRequestParams implements Serializable{
    private String uri;
    private String requestParams=null;
    private Map<String,String> urlParams=null;
    private Map<String,String> heards =null;
    private int connTimeOut;
    private int readTimeOut;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, String> getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(Map<String, String> urlParams) {
        this.urlParams = urlParams;
    }

    public Map<String, String> getHeards() {
        return heards;
    }

    public void setHeards(Map<String, String> heards) {
        this.heards = heards;
    }

    public int getConnTimeOut() {
        return connTimeOut;
    }

    public void setConnTimeOut(int connTimeOut) {
        this.connTimeOut = connTimeOut;
    }

    public int getReadTimeOut() {
        return readTimeOut;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }

    public void setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
    }
}
