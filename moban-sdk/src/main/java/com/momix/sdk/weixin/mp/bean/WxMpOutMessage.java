package com.momix.sdk.weixin.mp.bean;

import com.momix.sdk.parser.api.XmlAttribute;

import java.io.Serializable;

/**
 * Created by rono on 2015/11/29.
 */
public class WxMpOutMessage implements Serializable{
    @XmlAttribute(name="ToUserName")
    protected String toUserName;
    @XmlAttribute(name="FromUserName")
    protected String fromUserName;
    @XmlAttribute(name="CreateTime")
    protected Long createTime;
    @XmlAttribute(name="MsgType")
    protected String msgType;
    @XmlAttribute(name="Content")
    protected String content;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
