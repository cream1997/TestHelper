package com.cream.helper.obj.entity.mock;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("t_mock_remote_user")
public class MockRemoteUser {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private long id;
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
}
