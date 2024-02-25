package com.cream.helper.core.net.common;

import com.cream.helper.config.configuration.exception.Err;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.utils.Times;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class MsgPool {

    private static final int CAPACITY = 500;
    private static final long TIMEOUT_MS = 1500;
    /**
     * 玩家的响应消息队列, 先进先出的单向队列
     */
    private final LinkedBlockingQueue<Message<?>> resMsgQueue = new LinkedBlockingQueue<>(CAPACITY);

    private final AtomicInteger msgSerialNum = new AtomicInteger(0);

    public void addResMsg(Message<?> message) {
        message.setSerialNum(msgSerialNum.incrementAndGet());
        // 消息添加到队列尾部
        try {
            // 将元素插入队列尾部，等待指定时间，如果队列仍然满，则返回 false。
            if (!resMsgQueue.offer(message, TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                log.error("放入消息时，队列已满，请检查系统");
            }
        } catch (InterruptedException e) {
            log.error("放入消息发生异常", e);
        }
    }

    public List<Message<?>> fetchAllResMsg() {
        if (resMsgQueue.isEmpty()) {
            return Collections.emptyList();
        }
        List<Message<?>> result = new ArrayList<>();
        while (!resMsgQueue.isEmpty()) {
            Message<?> message = resMsgQueue.poll();
            if (message == null) {
                log.error("取出消息为空，请检查");
            } else {
                result.add(message);
            }
        }
        return result;
    }

    public <T extends Message<?>> T fetchMsgNotDel(Class<T> msgClass, int msgSerialNum) throws Err {
        long startTime = Times.now();
        for (; ; ) {
            if (Times.now() - startTime > TIMEOUT_MS) {
                throw new Err("从队列中取出指定消息超时，请检查");
            }
            for (Message<?> message : resMsgQueue) {
                if (msgClass.isInstance(message) && message.getSerialNum() > msgSerialNum) {
                    return msgClass.cast(message);
                }
            }
        }
    }

    public int getMsgSerialNum() {
        return msgSerialNum.get();
    }
}
