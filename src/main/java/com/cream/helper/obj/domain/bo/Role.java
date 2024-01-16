package com.cream.helper.obj.domain.bo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@TableName("t_mock_role")
public class Role {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("user_id")
    private final long userId;

    @TableField("name")
    private final String name;

    @Setter
    @TableField("level")
    private int level;

    @TableField("career")
    private final int career;

    @Setter
    @TableLogic
    @TableField("deleted")
    private boolean deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Timestamp createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;

    public Role(long userId, String name, int level, int career) {
        this.userId = userId;
        this.name = name;
        this.level = level;
        this.career = career;
    }
}
