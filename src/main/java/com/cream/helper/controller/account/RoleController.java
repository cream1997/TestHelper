package com.cream.helper.controller.account;

import com.cream.helper.obj.bo.Role;
import com.cream.helper.obj.vo.Result;
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
    public Result<List<Role>> fetchRoleList(long userId) {
        if (userId == 0) {
            return Result.fail("请先登录");
        }
        return roleLoginService.fetchRoleList(userId);
    }

    @PostMapping("/createRole")
    public Result<Role> createRole(@RequestBody Role role) {
        if (role.getUserId() == 0) {
            return Result.fail("请先登录");
        }
        return roleLoginService.createRole(role);
    }
}
