package com.cream.helper.controller.account;

import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.bo.Role;
import com.cream.helper.obj.domain.bo.RoleEnterInfo;
import com.cream.helper.obj.domain.bo.RoleHeartInfo;
import com.cream.helper.obj.domain.vo.RoleItemVO;
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
    public Ret<RoleItemVO> createRole(@RequestBody RoleItemVO roleItemVO) {
        if (roleItemVO.getUid() == 0) {
            return Ret.err("请先登录");
        }
        return roleLoginService.createRole(roleItemVO);
    }

    @PostMapping("/deleteRole")
    public Ret<RoleItemVO> deleteRole(@RequestBody RoleItemVO roleItemVO) {
        if (roleItemVO.getUid() == 0) {
            return Ret.err("请先登录");
        }
        return roleLoginService.deleteRole(roleItemVO);
    }

    @PostMapping("/enterRole")
    public Ret<RoleEnterInfo> enterRole(@RequestBody RoleItemVO roleItem) {
        if (roleItem.getUid() == 0) {
            return Ret.err("请先登录");
        }
        return roleLoginService.enterRole(roleItem);
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
