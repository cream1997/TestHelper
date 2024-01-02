package com.cream.helper.obj.entity.mock;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_mock_remote_user")
public class MockRemoteUser {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;

    public MockRemoteUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
