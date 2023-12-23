package com.cream.helper.controller;

import com.cream.helper.net.Message;
import com.cream.helper.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    public String acceptRequest(long rid, Message message) {
        return messageService.acceptRequest(rid, message);
    }


    /**
     * 解释见{@link MessageService#fetchResMsg)}
     */
    public String fetchResMsg(long rid) {
        return messageService.fetchResMsg(rid);
    }

    /**
     * 抓取请求消息模板
     */
    public String fetchAllReqTemplate() {
        return messageService.fetchAllReqTemplate();
    }

    /**
     * 抓取相应消息模板
     */
    public String fetchAllResTemplate() {
        return messageService.fetchAllResTemplate();
    }
}
