package com.cream.helper.obj.entity.account;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@TableName("t_account_setup")
public class AccountSetup {
    @TableId("account_id")
    private final long accountId;
    @Setter
    @TableField("default_server")
    private String defaultServer;


    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Timestamp createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;

    public AccountSetup(long accountId) {
        this.accountId = accountId;
    }

    public AccountSetup() {
        this.accountId = 8866;
    }
}
