package com.cream.helper.obj.entity.account;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cream.helper.utils.str.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@TableName("t_account_setup")
public class AccountSetup {
    @TableId("account_id")
    private final long accountId;
    @Setter
    @TableField("default_server")
    private String defaultServer;
    /**
     * 默认过滤取消
     */
    @Setter
    @TableField("default_filter_cancel_msg")
    private String defaultFilterCancelMsg;
    /**
     * 自定义过滤消息
     */
    @Setter
    @TableField("custom_filter_msg")
    private String customFilterMsg;

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


    public Set<Integer> getDefaultFilterCancelMsgId() {
        Collection<Integer> msgIds = StringUtil.douHaoDisJoin(this.defaultFilterCancelMsg, Integer.class);
        return new HashSet<>(msgIds);
    }

    public void setDefaultFilterCancelMsgId(Set<Integer> msgIds) {
        this.defaultFilterCancelMsg = StringUtil.douHaoJoin(msgIds);
    }

    public Set<Integer> getCustomFilterMsgId() {
        Collection<Integer> msgIds = StringUtil.douHaoDisJoin(this.customFilterMsg, Integer.class);
        return new HashSet<>(msgIds);
    }

    public void setCustomFilterMsgId(Set<Integer> msgIds) {
        this.customFilterMsg = StringUtil.douHaoJoin(msgIds);
    }
}
