package com.cream.helper.obj.entity.account;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;

@Getter
@TableName("t_account")
public class Account {
    // 雪花算法生成id
    @TableId(type = IdType.ASSIGN_ID)
    @TableField("id")
    private long id;

    @TableField("username")
    private final String username;

    @TableField("password")
    private final String password;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private final long createTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField("deleted")
    private boolean deleted;


    public Account(String username, String password, long createTime) {
        this.username = username;
        this.password = password;
        this.createTime = createTime;
    }
}
