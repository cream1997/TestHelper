package com.cream.helper.core.net.msg;

import com.cream.helper.utils.NullUtil;
import com.google.protobuf.GeneratedMessageV3;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class MessagePool {

    /**
     * 玩家的响应消息队列
     * k:rid
     */
    private final Map<Long, ArrayBlockingQueue<Message<? extends GeneratedMessageV3>>> rid2ResponseQueue = new ConcurrentHashMap<>();


    public void addResponse(long rid, Message<? extends GeneratedMessageV3> message) {
        ArrayBlockingQueue<Message<? extends GeneratedMessageV3>> responseQueue = rid2ResponseQueue
                .computeIfAbsent(rid, k -> new ArrayBlockingQueue<>(100));
        responseQueue.add(message);
    }

    public List<Message<? extends GeneratedMessageV3>> fetchAllResponse(long rid) {
        ArrayBlockingQueue<Message<? extends GeneratedMessageV3>> responseQueue = rid2ResponseQueue.get(rid);
        if (NullUtil.isEmpty(responseQueue)) {
            return Collections.emptyList();
        }
        List<Message<? extends GeneratedMessageV3>> result = new ArrayList<>();
        while (!responseQueue.isEmpty()) {
            try {
                result.add(responseQueue.poll(100, TimeUnit.MILLISECONDS));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
