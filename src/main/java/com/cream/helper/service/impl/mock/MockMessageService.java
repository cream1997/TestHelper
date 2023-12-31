package com.cream.helper.service.impl.mock;

import com.cream.helper.annotation.MockComponent;
import com.cream.helper.core.net.bo.Message;
import com.cream.helper.obj.vo.Result;
import com.cream.helper.service.IMessageService;

@MockComponent
public class MockMessageService implements IMessageService {
    @Override
    public Result<String> acceptRequest(long rid, Message message) {
        // todo 检查登录
        return null;
    }

    @Override
    public String fetchAllReqTemplate() {
        return null;
    }

    @Override
    public String fetchAllResTemplate() {
        return null;
    }

    @Override
    public String fetchResMsg(long rid) {
        return null;
    }
}
