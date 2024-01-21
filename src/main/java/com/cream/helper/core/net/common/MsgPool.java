package com.cream.helper.core.net.common;

import com.cream.helper.core.net.common.msg.base.Message;
import com.google.protobuf.GeneratedMessageV3;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class MsgPool {

    private static final int CAPACITY = 500;

    /**
     * 玩家的响应消息队列, 先进先出的单向队列
     */
    private final LinkedBlockingQueue<Message<? extends GeneratedMessageV3>> resMsgQueue = new LinkedBlockingQueue<>(CAPACITY);


    public void addResMsg(Message<? extends GeneratedMessageV3> message) {
        // 消息添加到队列尾部
        try {
            // 将元素插入队列尾部，等待指定时间，如果队列仍然满，则返回 false。
            if (!resMsgQueue.offer(message, 2, TimeUnit.SECONDS)) {
                log.error("放入消息时，队列已满，请检查系统");
            }
        } catch (InterruptedException e) {
            log.error("放入消息发生异常", e);
        }
    }

    public List<Message<? extends GeneratedMessageV3>> fetchAllResMsg() {
        if (resMsgQueue.isEmpty()) {
            return Collections.emptyList();
        }
        List<Message<? extends GeneratedMessageV3>> result = new ArrayList<>();
        while (!resMsgQueue.isEmpty()) {
            Message<? extends GeneratedMessageV3> message = resMsgQueue.poll();
            if (message == null) {
                log.error("取出消息为空，请检查");
            } else {
                result.add(message);
            }
        }
        return result;
    }

    public <T extends Message<?>> T fetchResMsg(Class<T> msgClass) {
        T result = null;
        while (result == null) {
            for (Message<? extends GeneratedMessageV3> message : resMsgQueue) {
                if (msgClass.isInstance(message)) {
                    result = msgClass.cast(message);
                    break;
                }
            }
        }
        if (!resMsgQueue.remove(result)) {
            log.error("从队列中移除消息失败，请检查");
        }
        return result;
    }
}
