package com.momix.sdk.weixin.mp.bean;

import java.io.Serializable;

/**
 * Created by rono on 2015/11/29.
 */
public class WxMpOutMessage implements Serializable{
    protected String ToUserName;

    protected String FromUserName;

    protected Long CreateTime;

    protected String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
