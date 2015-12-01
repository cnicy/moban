package com.momix.sdk.weixin.mp.api;

import com.momix.sdk.weixin.mp.bean.WxMpMessage;
import com.momix.sdk.weixin.mp.bean.WxMpOutMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 微信消息路由器，通过代码化的配置，把来自微信的消息交给handler处理
 *  该实例类应该是单例
 * 说明：
 * 1. 配置路由规则时要按照从细到粗的原则，否则可能消息可能会被提前处理
 * 2. 默认情况下消息只会被处理一次，除非使用 {@link WxMpMessageRouterRule#next()}
 * 3. 规则的结束必须用{@link WxMpMessageRouterRule#end()}或者{@link WxMpMessageRouterRule#next()}，否则不会生效
 *
 * 使用方法：
 * WxMpMessageRouter router = new WxMpMessageRouter();
 * router
 *   .rule()
 *       .msgType("MSG_TYPE").event("EVENT").eventKey("EVENT_KEY").content("CONTENT")
 *       .interceptor(interceptor, ...).handler(handler, ...)
 *   .end()
 *   .rule()
 *       // 另外一个匹配规则
 *   .end()
 * ;
 * </pre>
 * Created by rono on 2015/11/29.
 */
public class WxMpMessageRouter {
    private boolean isCheckMessageDeplicate = true;
    /** 业务处理类 */
    private WxMpService wxMpService;
    /** session管理类*/
    private WxSessionManager wxSessionManager;
    /** 异常处理类*/
    private WxExceptionHandler wxExceptionHandler;
    // 代码重复检测,确保单例
    private WxMessageDuplicateChecker wxMessageDuplicateChecker;
    // 路由规则列表
    private List<WxMpMessageRouterRule> rulesList = new ArrayList<WxMpMessageRouterRule>();

    public WxMpMessageRouter() {
        System.out.println("(*^__^*) ……>>>>>>>>>>>>>>>>>>>>>>>>> WxMpMessageRouter init...............");
    }

    /**
     * 配置路由规则时要按照从细到粗的原则，否则可能消息可能会被提前处理
     * @return
     */
    public WxMpMessageRouterRule buildRule(){
        return new WxMpMessageRouterRule(this);
    }

    public WxMpOutMessage route(final WxMpMessage wxMpMessage){
        if(isCheckMessageDeplicate && wxMessageDuplicateChecker.isDuplicate(wxMpMessage)){
            return null;    //  重复消息
        }
        // 消息规则规则匹配列表
        final List<WxMpMessageRouterRule> matchRules = new ArrayList<WxMpMessageRouterRule>();
        // 收集消息匹配信息,遍历验证进来的消息是否匹配当前规则
        for (final WxMpMessageRouterRule rule : rulesList) {
            if(rule.match(wxMpMessage)){
                matchRules.add(rule); // 当前消息和路由匹配
                if(!rule.hashNext())
                    break;  // 已经没有下一条路由规则了，退出循环
            }
        }
        if (matchRules.size() == 0) {
            return null;
        }

        WxMpOutMessage res = null; // 处理结果，只有一个（返回最后一个非异步的消息处理结果）
        // 遍历匹配的handler 逐个处理
        for(final WxMpMessageRouterRule matchRule : matchRules){
            if(matchRule.isAsync()){ // 异步处理
                // TODO
            }else{ // 同步处理
                res = matchRule.execute(wxMpMessage,wxMpService,wxSessionManager,wxExceptionHandler);
                // TODO
            }
        }
        return null;
    }

    /**
     * 是否做消息重复性检测
     * @param isCheckMessageDeplicate
     */
    public void setIsCheckMessageDeplicate(boolean isCheckMessageDeplicate) {
        this.isCheckMessageDeplicate = isCheckMessageDeplicate;
    }

    /**
     * 添加路由规则
     * @param rule
     */
    public void addMessageRouterRule(WxMpMessageRouterRule rule){
        this.rulesList.add(rule);
    }

    public WxMpService getWxMpService() {
        return wxMpService;
    }

    public void setWxMpService(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }

    public WxSessionManager getWxSessionManager() {
        return wxSessionManager;
    }

    public void setWxSessionManager(WxSessionManager wxSessionManager) {
        this.wxSessionManager = wxSessionManager;
    }

    public WxExceptionHandler getWxExceptionHandler() {
        return wxExceptionHandler;
    }

    public void setWxExceptionHandler(WxExceptionHandler wxExceptionHandler) {
        this.wxExceptionHandler = wxExceptionHandler;
    }

    public WxMessageDuplicateChecker getWxMessageDuplicateChecker() {
        return wxMessageDuplicateChecker;
    }

    public void setWxMessageDuplicateChecker(WxMessageDuplicateChecker wxMessageDuplicateChecker) {
        this.wxMessageDuplicateChecker = wxMessageDuplicateChecker;
    }
}
