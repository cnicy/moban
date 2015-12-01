package com.momix.sdk.weixin.mp.bean;

import java.io.Serializable;

/**
 * 二维码返回参数
 * Created by rono on 2015/12/1.
 */
public class QrcodeTicket implements Serializable {
    private String ticket;
    private Integer expire_seconds;
    private String url;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(Integer expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
