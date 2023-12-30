package com.cream.helper.obj.entity.account;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@TableName("t_user")
public class User {
    @TableId(value = "id")
    private final int id;
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

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
