package com.cream.helper.controller;

import com.cream.helper.core.net.msg.Message;
import com.cream.helper.obj.vo.Result;
import com.cream.helper.service.IMessageService;
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

    @PostMapping("/acceptRequest")
    public Result<String> acceptRequest(long rid, Message message) {
        return messageService.acceptRequest(rid, message);
    }


    /**
     * 解释见{@link IMessageService#fetchResMsg)}
     */
    @PostMapping("/fetchResMsg")
    public Result<Message> fetchResMsg(long rid) {
        return messageService.fetchResMsg(rid);
    }

    /**
     * 抓取请求消息模板
     */
    @PostMapping("/fetchAllReqTemplate")
    public Result<List<Message>> fetchAllReqTemplate() {
        return messageService.fetchAllReqTemplate();
    }

    /**
     * 抓取相应消息模板
     */
    @PostMapping("/fetchAllResTemplate")
    public Result<List<Message>> fetchAllResTemplate() {
        return messageService.fetchAllResTemplate();
    }
}
