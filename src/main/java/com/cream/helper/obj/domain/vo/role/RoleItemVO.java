package com.cream.helper.obj.domain.vo.role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleItemVO {
    private final long uid;
    private final long rid;
    private final String roleName;
    private final int level;
    private final String career;
}
