package com.cream.helper.obj.domain.vo.user;


import com.cream.helper.obj.entity.account.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserVO {
    /**
     * 平台账号id
     */
    private final long accountId;
    private final String username;
    private final String password;

    public UserVO(User user) {
        this.accountId = user.getAccountId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
