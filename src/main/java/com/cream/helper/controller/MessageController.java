package com.cream.helper.controller;

import com.cream.helper.core.net.msg.Message;
import com.cream.helper.obj.Ret;
import com.cream.helper.service.IMessageService;
import com.google.protobuf.GeneratedMessageV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    private final IMessageService messageService;

    @Autowired
    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/sendRequest")
    public Ret<String> sendRequest(long rid, Message message) {
        return messageService.sendRequest(rid, message);
    }


    /**
     * 解释见{@link IMessageService#fetchAllResMsg )}
     */
    @PostMapping("/fetchAllResMsg")
    public Ret<List<Message<? extends GeneratedMessageV3>>> fetchAllResMsg(long rid) {
        return messageService.fetchAllResMsg(rid);
    }

    /**
     * 抓取请求消息模板
     */
    @PostMapping("/fetchAllReqTemplate")
    public Ret<List<Message<? extends GeneratedMessageV3>>> fetchAllReqTemplate() {
        return messageService.fetchAllReqTemplate();
    }

    /**
     * 抓取相应消息模板
     */
    @PostMapping("/fetchAllResTemplate")
    public Ret<List<Message<? extends GeneratedMessageV3>>> fetchAllResTemplate() {
        return messageService.fetchAllResTemplate();
    }
}
