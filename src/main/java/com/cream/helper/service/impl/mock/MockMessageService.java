package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.core.net.msg.Message;
import com.cream.helper.core.net.msg.MessageTemplatePool;
import com.cream.helper.obj.vo.Result;
import com.cream.helper.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@MockComponent
public class MockMessageService implements IMessageService {

    private final MessageTemplatePool messageTemplatePool;

    @Autowired
    public MockMessageService(MessageTemplatePool messageTemplatePool) {
        this.messageTemplatePool = messageTemplatePool;
    }

    @Override
    public Result<String> acceptRequest(long rid, Message message) {
        // todo 检查登录
        return null;
    }

    @Override
    public Result<List<Message>> fetchAllReqTemplate() {
        return Result.success(messageTemplatePool.getAllReqTemplate());
    }

    @Override
    public Result<List<Message>> fetchAllResTemplate() {
        return Result.success(messageTemplatePool.getAllResTemplate());
    }

    @Override
    public Result<Message> fetchResMsg(long rid) {
        return null;
    }
}
