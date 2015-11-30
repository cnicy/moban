package com.momix.sdk.weixin.mp.api;

import com.momix.sdk.common.exception.SdkException;
import com.momix.sdk.weixin.mp.bean.WxMpMessage;
import com.momix.sdk.weixin.mp.bean.WxMpOutMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>路由规则定义
 * 从4个值匹配路由规则
 * msgType、event、eventKey、content
 * Created by rono on 2015/11/29.
 * <pre/>
 */
public class WxMpMessageRouterRule implements Serializable{
    private final WxMpMessageRouter wxMpMessageRouter;

    private String msgType;     // 消息类型

    private String event;       // 事件类型

    private String eventKey;    // 事件key

    private String content;     // 消息内容

    private boolean async = true;   // 异步处理标识

    private boolean hasNextFlag = false; // 当前路由规则完成之后 是否还有下一条路由规则
    // 当前路由相关的处理器
    private List<WxMpMessageHandler> handlerList = new ArrayList<WxMpMessageHandler>();
    // 当前路由相关的拦截器
    private List<WxMessageInterceptor> interceptors = new ArrayList<WxMessageInterceptor>();

    // 持有一个router实例，方便向router添加路由规则
    public WxMpMessageRouterRule(WxMpMessageRouter wxMpMessageRouter) {
        this.wxMpMessageRouter = wxMpMessageRouter;
    }

    /**
     * 验证当前传进来的消息是否匹配当前规则,配置路由规则时要按照从细到粗的原则，否则可能消息可能会被提前处理
     * @param wxMpMessage
     * @return
     */
    protected boolean match(WxMpMessage wxMpMessage){
            return (
                    (null==this.msgType || this.msgType.toLowerCase()
                            .equals((null!=wxMpMessage.getMsgType()?wxMpMessage.getMsgType().toLowerCase():null)))
                    &&(null==this.event || this.event.toLowerCase()
                            .equals((null!=wxMpMessage.getEvent()?wxMpMessage.getEvent().toLowerCase():null)))
                    &&(null==this.eventKey || this.eventKey.toLowerCase()
                            .equals((null!=wxMpMessage.getEventKey()?wxMpMessage.getEventKey().toLowerCase():null)))
                    &&(null==this.content||this.content.toLowerCase()
                            .equals((null!=wxMpMessage.getContent()?wxMpMessage.getContent().toLowerCase():null)))
                   );
    }

    public WxMpOutMessage execute(WxMpMessage wxMpMessage,WxMpService wxMpService,
                                  WxSessionManager wxSessionManager,WxExceptionHandler wxExceptionHandler){
        try{
            Map<String, Object> context = new HashMap<String, Object>();
            // 经过拦截器
            if(null!=interceptors && interceptors.size()>0){
                // 如果拦截器不通过
                for (WxMessageInterceptor interceptor : this.interceptors) {
                    if (!interceptor.intercept(wxMpMessage, context, wxMpService, wxSessionManager)) {
                        return null;
                    }
                }
            }
            // 消息经过handler处理
            WxMpOutMessage res = null;
            for(WxMpMessageHandler handler:handlerList){
                res = handler.handler(wxMpMessage,context,wxMpService,wxSessionManager); // 返回最后handler的结果
            }
            return res;
        }catch (SdkException e) {
            wxExceptionHandler.handle(e);
        }
        return null;
    }

    // region   构造器模式，构造新的路由规则

    /**
     * 当前还有消息还可以匹配下一条路由规则
     * @return
     */
    public WxMpMessageRouter next(){
        this.hasNextFlag = true;
        this.wxMpMessageRouter.addMessageRouterRule(this);
        return this.wxMpMessageRouter;
    }

    /**
     * 当前消息已经没有下一条路由规则了
     * @return
     */
    public WxMpMessageRouter end(){
        this.hasNextFlag = false;
        this.wxMpMessageRouter.addMessageRouterRule(this);
        return this.wxMpMessageRouter;
    }

    /**
     *  是否异步处理消息（默认是：true）
     * @param async
     * @return
     */
    public WxMpMessageRouterRule async(boolean async){
        this.async = async;
        return this;
    }

    /**
     * 消息类型
     * @param msgType
     * @return
     */
    public WxMpMessageRouterRule msgType(String msgType){
        this.msgType =msgType;
        return this;
    }

    /**
     * 事件类型
     * @param event
     * @return
     */
    public WxMpMessageRouterRule event(String event){
        this.event = event;
        return this;
    }

    /**
     * 事件key
     * @param eventKey
     * @return
     */
    public WxMpMessageRouterRule eventKey(String eventKey){
        this.eventKey = eventKey;
        return this;
    }

    /**
     * 内容
     * @param content
     * @return
     */
    public WxMpMessageRouterRule content(String content){
        this.content = content;
        return this;
    }

    public WxMpMessageRouterRule handler(WxMpMessageHandler... handlers){
        for(WxMpMessageHandler handler:handlers){
            if(null!=handler)
                handlerList.add(handler);
        }
        return this;
    }


    /**
     * 设置拦截器
     * @param wxMessageInterceptors
     * @return
     */
    public WxMpMessageRouterRule intercept(WxMessageInterceptor... wxMessageInterceptors){
        if(null!=wxMessageInterceptors && wxMessageInterceptors.length>0){
            for(WxMessageInterceptor interceptor:wxMessageInterceptors){
                this.interceptors.add(interceptor);
            }
        }
        return this;
    }

    // endregion

    public boolean hashNext(){
        return this.hasNextFlag;
    }

    public boolean isAsync() {
        return async;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
