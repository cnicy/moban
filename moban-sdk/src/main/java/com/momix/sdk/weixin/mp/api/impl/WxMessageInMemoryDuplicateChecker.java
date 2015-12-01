package com.momix.sdk.weixin.mp.api.impl;

import com.momix.sdk.weixin.mp.api.WxMessageDuplicateChecker;
import com.momix.sdk.weixin.mp.bean.WxMpMessage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <pre>
 * 默认消息重复检查器
 * 将每个消息id保存在内存里，每隔5秒清理已经过期的消息id，每个消息id的过期时间是15秒
 * </pre>
 * Created by rono on 2015/11/29.
 */
public class WxMessageInMemoryDuplicateChecker implements WxMessageDuplicateChecker{

    /**
     * 一个消息ID在内存的过期时间：15秒
     */
    private final Long timeToLive;

    /**
     * 每隔多少周期检查消息ID是否过期：5秒
     */
    private final Long clearPeriod;

    /**
     * 消息id->消息时间戳的map
     */
    private final ConcurrentHashMap<String, Long> msgId2Timestamp = new ConcurrentHashMap<String, Long>();

    /**
     * 后台清理线程是否已经开启
     */
    private final AtomicBoolean backgroundProcessStarted = new AtomicBoolean(false);

    /**
     * WxMsgIdInMemoryDuplicateChecker构造函数
     * <pre>
     * 一个消息ID在内存的过期时间：15秒
     * 每隔多少周期检查消息ID是否过期：5秒
     * </pre>
     */
    public WxMessageInMemoryDuplicateChecker() {
        this.timeToLive = 15 * 1000L;
        this.clearPeriod = 5 * 1000L;
        System.out.println("(*^__^*) ……>>>>>>>>>>>>>>>>>>>>>>>>> WxMessageInMemoryDuplicateChecker init...............");
    }

    /**
     * WxMsgIdInMemoryDuplicateChecker构造函数
     * @param timeToLive 一个消息ID在内存的过期时间：毫秒
     * @param clearPeriod 每隔多少周期检查消息ID是否过期：毫秒
     */
    public WxMessageInMemoryDuplicateChecker(Long timeToLive, Long clearPeriod) {
        this.timeToLive = timeToLive;
        this.clearPeriod = clearPeriod;
    }


    @Override
    public boolean isDuplicate(String messageId) {
        if (messageId == null) {
            return false;
        }
        chechProcess();
        Long timestamp = msgId2Timestamp.putIfAbsent(messageId, System.currentTimeMillis());
        if (timestamp == null) {
            // 第一次接收到这个消息
            return false;
        }
        System.out.println(messageId+" is duplicate");
        return true;
    }

    @Override
    public boolean isDuplicate(WxMpMessage wxMpMessage) {
        String messageId = "";
        if (wxMpMessage.getMsgId() == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(wxMpMessage.getCreateTime()).append("-")
                    .append(wxMpMessage.getFromUserName()).append("-")
                    .append(wxMpMessage.getEvent()).append("-")
                    .append(wxMpMessage.getEventKey());
            messageId = sb.toString();
        }else{
            messageId =String.valueOf(wxMpMessage.getMsgId());
        }
        return isDuplicate(messageId);
    }

    /**
     * 清除线程
     */
    private void chechProcess(){
        // 竞争锁
        if (backgroundProcessStarted.getAndSet(true)) {
            return;
        }
        // 启动一个线程 不断清除过期数据
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(clearPeriod);
                        Long now = System.currentTimeMillis();
                        for (Map.Entry<String, Long> entry : msgId2Timestamp.entrySet()) {
                            if (now - entry.getValue() > timeToLive) {
                                msgId2Timestamp.entrySet().remove(entry);
                                System.out.println("remove msgId "+entry+ " size "+msgId2Timestamp.size());
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }
}
