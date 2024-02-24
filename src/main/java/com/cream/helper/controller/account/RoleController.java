package com.cream.helper.controller.account;

import com.cream.helper.config.configuration.exception.RunErr;
import com.cream.helper.obj.domain.bo.Role;
import com.cream.helper.obj.domain.bo.RoleHeartInfo;
import com.cream.helper.obj.domain.vo.role.RoleEnterVO;
import com.cream.helper.obj.domain.vo.role.RoleItemVO;
import com.cream.helper.service.IRoleLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    private final IRoleLoginService roleLoginService;

    @Autowired
    public RoleController(IRoleLoginService roleLoginService) {
        this.roleLoginService = roleLoginService;
    }

    @PostMapping("/createRole")
    public RoleItemVO createRole(@RequestBody RoleItemVO roleItemVO) {
        if (roleItemVO.getUid() == 0) {
            throw new RunErr("请先登录");
        }
        return roleLoginService.createRole(roleItemVO);
    }

    @PostMapping("/deleteRole")
    public RoleItemVO deleteRole(@RequestBody RoleItemVO roleItemVO) {
        if (roleItemVO.getUid() == 0) {
            throw new RunErr("请先登录");
        }
        return roleLoginService.deleteRole(roleItemVO);
    }

    @PostMapping("/enterRole")
    public RoleEnterVO enterRole(@RequestBody RoleItemVO roleItem) {
        if (roleItem.getUid() == 0) {
            throw new RunErr("请先登录");
        }
        return roleLoginService.enterRole(roleItem);
    }

    @PostMapping("/exitRole")
    public Role exitRole(@RequestBody Role role) {
        if (role.getUserId() == 0) {
            throw new RunErr("请先登录");
        }
        return roleLoginService.exitRole(role);
    }

    @PostMapping("/roleHeart")
    public RoleHeartInfo roleHeart(@RequestBody Role role) {
        if (role.getUserId() == 0) {
            throw new RuntimeException("心跳异常");
        }
        return roleLoginService.heart(role);
    }
}
