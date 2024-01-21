package com.cream.helper.core.net.common;

import com.cream.helper.config.configuration.exception.CommonError;
import com.cream.helper.core.net.common.msg.base.Message;
import com.cream.helper.utils.Times;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class MsgPool {

    private static final int CAPACITY = 500;
    private static final long TIMEOUT_MS = 1500;
    /**
     * 取消息加锁，保证取指定消息时，不会被fetchAll抢先
     */
    private final ReentrantLock fetchLock = new ReentrantLock();

    /**
     * 玩家的响应消息队列, 先进先出的单向队列
     */
    private final LinkedBlockingQueue<Message<?>> resMsgQueue = new LinkedBlockingQueue<>(CAPACITY);


    public void addResMsg(Message<?> message) {
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
        fetchLock.lock();
        try {
            while (!resMsgQueue.isEmpty()) {
                Message<?> message = resMsgQueue.poll();
                if (message == null) {
                    log.error("取出消息为空，请检查");
                } else {
                    result.add(message);
                }
            }
        } finally {
            fetchLock.unlock();
        }
        return result;
    }

    public <T extends Message<?>> T fetchResMsg(Class<T> msgClass) throws CommonError {
        long startTime = Times.now();
        fetchLock.lock();
        try {
            for (; ; ) {
                if (Times.now() - startTime > TIMEOUT_MS) {
                    throw new CommonError("从队列中取出消息超时，请检查");
                }
                for (Message<?> message : resMsgQueue) {
                    if (msgClass.isInstance(message)) {
                        if (!resMsgQueue.remove(message)) {
                            log.error("从队列中移除消息失败，请检查");
                        }
                        return msgClass.cast(message);
                    }
                }
            }
        } finally {
            fetchLock.unlock();
        }
    }
}
