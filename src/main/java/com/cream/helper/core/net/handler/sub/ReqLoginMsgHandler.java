package com.cream.helper.core.net.handler.sub;

import com.cream.helper.core.net.handler.base.MsgHandler;
import com.cream.helper.core.net.msg.base.Message;
import com.cream.helper.core.net.msg.req.ReqLoginMsg;
import com.cream.helper.core.net.msg.res.ResLoginMsg;
import com.cream.helper.core.net.proto.clazz.CommonProto;
import com.cream.helper.mapper.LocalUserMapper;
import com.cream.helper.mapper.mock.MockRoleMapper;
import com.cream.helper.obj.domain.bo.Role;
import com.cream.helper.obj.entity.account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReqLoginMsgHandler extends MsgHandler<ReqLoginMsg> {

    private final MockRoleMapper roleMapper;
    private final LocalUserMapper localUserMapper;

    @Autowired
    public ReqLoginMsgHandler(MockRoleMapper roleMapper,
                              LocalUserMapper localUserMapper) {
        this.roleMapper = roleMapper;
        this.localUserMapper = localUserMapper;
    }

    @Override
    public Message<?> handle(ReqLoginMsg msg) {
        CommonProto.LoginReq data = msg.getData();
        String username = data.getUsername();
        User user = localUserMapper.getUser(username);
        if (user == null) {
            // todo
            throw new RuntimeException();
        }
        Long uid = user.getId();
        List<Role> roleList = roleMapper.getRoleList(uid);

        CommonProto.LoginRes.Builder resDataBuilder = CommonProto.LoginRes.newBuilder()
                .setUid(user.getId());
        if (roleList != null) {
            for (Role role : roleList) {
                resDataBuilder.addRole(CommonProto.Role.newBuilder()
                        .setRid(role.getId())
                        .setRoleName(role.getName())
                        .setLevel(role.getLevel())
                        .setCareer(role.getCareer() + "")
                        .build());
            }
        }
        return new ResLoginMsg(resDataBuilder::build);
    }
}
