package com.cream.helper.core.net.handler.sub;

import com.cream.helper.core.net.handler.base.MsgHandler;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.msg.req.ReqEnterRoleMsg;
import com.cream.helper.core.net.msg.res.ResEnterRoleMsg;
import com.cream.helper.core.net.proto.clazz.CommonProto;
import com.cream.helper.mapper.mock.MockRoleMapper;
import com.cream.helper.obj.domain.bo.Role;
import com.cream.helper.utils.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReqEnterRoleMsgHandler extends MsgHandler<ReqEnterRoleMsg> {

    private final MockRoleMapper roleMapper;

    @Autowired
    public ReqEnterRoleMsgHandler(MockRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public Message<?> handle(ReqEnterRoleMsg msg) {
        CommonProto.EnterRoleReq data = msg.getData();
        long uid = data.getUid();
        long rid = data.getRid();
        Role role = roleMapper.getRole(rid);

        CommonProto.Role roleProto = CommonProto.Role.newBuilder()
                .setUid(uid)
                .setRid(rid)
                .setRoleName(role.getName())
                .setLevel(role.getLevel())
                .setCareer(role.getCareer() + "")
                .build();
        CommonProto.Position position = CommonProto.Position.newBuilder()
                .setMapName("勇者大陆")
                .setMapLine(1)
                .setXy(CommonProto.XY.newBuilder()
                        .setX(22).setY(33))
                .build();
        CommonProto.EnterRoleRes build = CommonProto.EnterRoleRes.newBuilder()
                .setUid(uid)
                .setRole(roleProto)
                .setServerTime(Times.now())
                .setPosition(position)
                .build();
        return new ResEnterRoleMsg(() -> build);
    }
}
