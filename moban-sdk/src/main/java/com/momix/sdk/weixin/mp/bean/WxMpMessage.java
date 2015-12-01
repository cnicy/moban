package com.momix.sdk.weixin.mp.bean;

import com.momix.sdk.parser.api.XmlAttribute;

import java.io.Serializable;

/**
 * 微信消息处理
 * Created by rono on 2015/11/29.
 */
public class WxMpMessage implements Serializable{
    //*** 开发者微信号
    @XmlAttribute(name="ToUserName")
    private String toUserName;
    //*** 发送方帐号（一个OpenID）
    @XmlAttribute(name="FromUserName")
    private String fromUserName;
    //** 创建时间
    @XmlAttribute(name="CreateTime")
    private String createTime;
    //** 消息类型
    @XmlAttribute(name="MsgType")
    private String msgType;
    //** 内容
    @XmlAttribute(name="Content")
    private String content;
    //** 消息ID
    @XmlAttribute(name="MsgId")
    private String msgId;
    //** 事件类型
    @XmlAttribute(name="Event")
    private String event;
    // 事件key
    @XmlAttribute(name="EventKey")
    private String eventKey;

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
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
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
    public String getMsgId() {
        return msgId;
    }
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    public String getEvent() {
        return event;
    }
    public void setEvent(String event) {
        this.event = event;
    }
    public String getEventKey() {
        return eventKey;
    }
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
