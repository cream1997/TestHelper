package com.cream.helper.obj.domain.bo;


import lombok.Getter;

@Getter
public class RoleEnterInfo {
    private final Role role;
    private final Object ext;

    public RoleEnterInfo(Role role, Object ext) {
        this.role = role;
        this.ext = ext;
    }
}
