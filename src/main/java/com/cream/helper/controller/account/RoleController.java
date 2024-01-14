package com.cream.helper.controller.account;

import com.cream.helper.obj.Ret;
import com.cream.helper.obj.bo.Role;
import com.cream.helper.obj.bo.RoleEnterInfo;
import com.cream.helper.obj.bo.RoleHeartInfo;
import com.cream.helper.service.IRoleLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    private final IRoleLoginService roleLoginService;

    @Autowired
    public RoleController(IRoleLoginService roleLoginService) {
        this.roleLoginService = roleLoginService;
    }

    @PostMapping("/fetchRoleList")
    public Ret<List<Role>> fetchRoleList(long userId) {
        if (userId == 0) {
            return Ret.err("请先登录");
        }
        return roleLoginService.fetchRoleList(userId);
    }

    @PostMapping("/createRole")
    public Ret<Role> createRole(@RequestBody Role role) {
        if (role.getUserId() == 0) {
            return Ret.err("请先登录");
        }
        return roleLoginService.createRole(role);
    }

    @PostMapping("/deleteRole")
    public Ret<Role> deleteRole(@RequestBody Role role) {
        if (role.getUserId() == 0) {
            return Ret.err("请先登录");
        }
        return roleLoginService.deleteRole(role);
    }

    @PostMapping("/enterRole")
    public Ret<RoleEnterInfo> enterRole(@RequestBody Role role) {
        if (role.getUserId() == 0) {
            return Ret.err("请先登录");
        }
        return roleLoginService.enterRole(role);
    }

    @PostMapping("/exitRole")
    public Ret<Role> exitRole(@RequestBody Role role) {
        if (role.getUserId() == 0) {
            return Ret.err("请先登录");
        }
        return roleLoginService.exitRole(role);
    }

    @PostMapping("/roleHeart")
    public Ret<RoleHeartInfo> roleHeart(@RequestBody Role role) {
        if (role.getUserId() == 0) {
            throw new RuntimeException("心跳异常");
        }
        return roleLoginService.heart(role);
    }
}
