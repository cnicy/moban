package com.momix.sdk.weixin.mp.bean;

import java.io.Serializable;

/**
 * Created by rono on 2015/12/5.
 */
public class WxJsapiTicket implements Serializable{
    private String ticket;

    private int expires_in = -1;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
