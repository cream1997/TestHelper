package com.cream.helper.controller.account;

import com.cream.helper.obj.Ret;
import com.cream.helper.obj.domain.vo.ServerVO;
import com.cream.helper.obj.domain.vo.user.LoginUserInfoVO;
import com.cream.helper.obj.domain.vo.user.LoginUserVO;
import com.cream.helper.obj.domain.vo.user.UserVO;
import com.cream.helper.service.IGameLoginService;
import com.cream.helper.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;
    private final IGameLoginService gameLoginService;

    @Autowired
    public UserController(UserService userService, IGameLoginService gameLoginService) {
        this.userService = userService;
        this.gameLoginService = gameLoginService;
    }

    @PostMapping("/registerUser")
    public Ret<String> registerUser(@RequestBody UserVO userVO) {
        userService.register(userVO.getAccountId(), userVO.getUsername(), userVO.getPassword());
        return Ret.ok("注册成功");
    }


    @PostMapping("/loginUser")
    public Ret<LoginUserInfoVO> loginUser(@RequestBody LoginUserVO loginUserVO) {
        UserVO userVO = loginUserVO.getUserVO();
        ServerVO serverVO = loginUserVO.getServerVO();
        return Ret.ok(userService.login(userVO, serverVO));
    }

    @PostMapping("/userLogout")
    public void userLogout(@RequestBody long uid) {
        userService.logout(uid);
    }


    @PostMapping("/fetchUserAccounts")
    public Ret<List<UserVO>> fetchUserAccounts(@RequestBody long accountId) {
        return Ret.ok(userService.fetchUserAccounts(accountId));
    }

    /**
     * 获取服务器列表
     */
    @PostMapping("/fetchServerList")
    public Ret<List<ServerVO>> fetchServerList() {
        return Ret.ok(gameLoginService.fetchServerList());
    }

    @PostMapping("/unBindUser")
    public Ret<String> unBindUser(@RequestBody String username) {
        userService.unBindUser(username);
        return Ret.ok("删除成功");
    }
}
