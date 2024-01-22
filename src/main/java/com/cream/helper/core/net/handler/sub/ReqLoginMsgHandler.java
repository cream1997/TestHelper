package com.cream.helper.core.net.handler.sub;

import cn.hutool.core.util.IdUtil;
import com.cream.helper.core.net.handler.base.MsgHandler;
import com.cream.helper.core.net.msg.ReqLoginMsg;
import com.cream.helper.core.net.msg.ResLoginMsg;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.proto.clazz.CommonProto;

public class ReqLoginMsgHandler extends MsgHandler<ReqLoginMsg> {
    @Override
    public Message<?> handle(ReqLoginMsg message) {
        // todo
        CommonProto.Role role1 = CommonProto.Role.newBuilder()
                .setRid(12345)
                .setRoleName("张三")
                .setLevel(33)
                .setCareer("法师").build();
        CommonProto.Role role2 = CommonProto.Role.newBuilder()
                .setRid(3333)
                .setRoleName("李四")
                .setLevel(55)
                .setCareer("战士").build();
        CommonProto.LoginRes loginRes = CommonProto.LoginRes.newBuilder()
                .setUid(IdUtil.getSnowflakeNextId())
                .addRole(role1)
                .addRole(role2)
                .build();
        ResLoginMsg resLoginMsg = new ResLoginMsg(() -> loginRes);
        return resLoginMsg;
    }
}
