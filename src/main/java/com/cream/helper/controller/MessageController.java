package com.cream.helper.controller;

import com.cream.helper.core.net.Message;
import com.cream.helper.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/acceptRequest")
    public String acceptRequest(long rid, Message message) {
        return messageService.acceptRequest(rid, message);
    }


    /**
     * 解释见{@link MessageService#fetchResMsg)}
     */
    @PostMapping("/fetchResMsg")
    public String fetchResMsg(long rid) {
        return messageService.fetchResMsg(rid);
    }

    /**
     * 抓取请求消息模板
     */
    @PostMapping("/fetchAllReqTemplate")
    public String fetchAllReqTemplate() {
        return messageService.fetchAllReqTemplate();
    }

    /**
     * 抓取相应消息模板
     */
    @PostMapping("/fetchAllResTemplate")
    public String fetchAllResTemplate() {
        return messageService.fetchAllResTemplate();
    }
}
