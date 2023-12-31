package com.cream.helper.obj.entity.account;

import com.baomidou.mybatisplus.annotation.*;
import com.cream.helper.obj.entity.mock.MockRemoteUser;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@TableName("t_user")
public class User {
    @TableId(value = "id")
    private final long id;
    @TableField(value = "username")
    private final String username;
    @TableField("password")
    private final String password;
    @Setter
    @TableLogic
    @TableField("deleted")
    private boolean deleted;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Timestamp createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;

    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(MockRemoteUser mockRemoteUser) {
        this(mockRemoteUser.getId(), mockRemoteUser.getUsername(), mockRemoteUser.getPassword());
    }
}
