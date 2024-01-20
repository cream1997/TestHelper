package com.cream.helper.service;

import com.cream.helper.core.net.msg.Message;
import com.cream.helper.obj.Ret;
import com.google.protobuf.GeneratedMessageV3;

import java.util.List;

public interface IMessageService {

    /**
     * 接受请求
     */
    Ret<String> sendRequest(long rid, Message<GeneratedMessageV3> message);

    /**
     * 抓取所有请求消息模板
     */
    Ret<List<Message<? extends GeneratedMessageV3>>> fetchAllReqTemplate();

    /**
     * 抓取所有响应消息模板
     */
    Ret<List<Message<? extends GeneratedMessageV3>>> fetchAllResTemplate();

    /**
     * 抓取响应消息（消息内容）
     * 因为Http的请求响应逻辑是一个请求对应一个响应，而真正游戏的网络模式是Tcp长连接，
     * 不能一个请求对应一个回来处理，采用循环主动拉取的方式获取所有响应消息，模拟长连接
     */
    Ret<List<Message<? extends GeneratedMessageV3>>> fetchAllResMsg(long rid);
}
