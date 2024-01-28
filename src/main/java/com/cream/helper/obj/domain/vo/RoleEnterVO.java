package com.cream.helper.obj.domain.vo;


import com.cream.helper.obj.pojo.Position;
import lombok.Getter;

@Getter
public class RoleEnterVO {
    private final RoleItemVO roleItem;
    private final long serverTime;
    private final Position position;

    public RoleEnterVO(RoleItemVO roleItem, long serverTime, Position position) {
        this.roleItem = roleItem;
        this.serverTime = serverTime;
        this.position = position;
    }
}
