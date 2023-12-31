package com.cream.helper.service.impl;

import com.cream.helper.core.net.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    /**
     * 接受请求
     */
    public String acceptRequest(long rid, Message message) {
        return null;
    }


    /**
     * 抓取所有请求消息模板
     */
    public String fetchAllReqTemplate() {
        return null;
    }

    /**
     * 抓取所有响应消息模板
     */
    public String fetchAllResTemplate() {
        return null;
    }

    /**
     * 抓取响应消息（消息内容）
     * 因为Http的请求响应逻辑是一个请求对应一个响应，而真正游戏的网络模式是Tcp长连接，
     * 不能一个请求对应一个回来处理，采用循环主动拉取的方式获取所有响应消息，模拟长连接
     */
    public String fetchResMsg(long rid) {
        return null;
    }
}
