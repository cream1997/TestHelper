package com.cream.helper.service;

import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.vo.MsgVO;

import java.util.List;

public interface IMessageService {

    /**
     * 接受请求
     */
    Ret<String> sendRequest(long rid, MsgVO msgVO);

    /**
     * 抓取所有请求消息模板
     */
    Ret<List<Message<?>>> fetchAllReqTemplate();

    /**
     * 抓取所有响应消息模板
     */
    Ret<List<Message<?>>> fetchAllResTemplate();

    /**
     * 抓取响应消息（消息内容）
     * 因为Http的请求响应逻辑是一个请求对应一个响应，而真正游戏的网络模式是Tcp长连接，
     * 不能一个请求对应一个回来处理，采用循环主动拉取的方式获取所有响应消息，模拟长连接
     */
    Ret<List<MsgVO>> heartBeat(long rid);
}
