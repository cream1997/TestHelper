package com.cream.helper.obj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleListItemVO {
    private final long rid;
    private final String roleName;
    private final int level;
    private final String career;
}
