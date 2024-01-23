package com.cream.helper.obj.domain.bo;


import com.cream.helper.obj.domain.vo.RoleItemVO;
import lombok.Getter;

@Getter
public class RoleEnterInfo {
    private final RoleItemVO roleItem;
    private final Object ext;

    public RoleEnterInfo(RoleItemVO roleItem, Object ext) {
        this.roleItem = roleItem;
        this.ext = ext;
    }
}
