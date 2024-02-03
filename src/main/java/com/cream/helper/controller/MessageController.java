package com.cream.helper.controller;

import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.vo.MsgVO;
import com.cream.helper.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    private final IMessageService messageService;

    @Autowired
    public MessageController(IMessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/sendRequest")
    public Ret<String> sendRequest(long rid, MsgVO msgVO) {
        return messageService.sendRequest(rid, msgVO);
    }


    /**
     * 解释见{@link IMessageService#heartBeat )}
     */
    @PostMapping("/heartBeat")
    public Ret<List<MsgVO>> heartBeat(@RequestBody long uid) {
        return messageService.heartBeat(uid);
    }

    /**
     * 抓取请求消息模板
     */
    @PostMapping("/fetchAllReqTemplate")
    public Ret<List<Message<?>>> fetchAllReqTemplate() {
        return messageService.fetchAllReqTemplate();
    }

    @PostMapping("/searchMsgTemplate")
    public Ret<List<MsgVO>> searchMsgTemplate(@RequestBody String msgNameKey) {
        List<MsgVO> result = messageService.searchMsgTemplate(msgNameKey).stream()
                .map(MsgVO::new)
                .collect(Collectors.toList());
        return Ret.ok(result);
    }

    /**
     * 抓取相应消息模板
     */
    @PostMapping("/fetchAllResTemplate")
    public Ret<List<Message<?>>> fetchAllResTemplate() {
        return messageService.fetchAllResTemplate();
    }
}
