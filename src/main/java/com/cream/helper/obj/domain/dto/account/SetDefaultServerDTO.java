package com.cream.helper.obj.domain.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SetDefaultServerDTO {
    private final long accountId;
    private final String defaultServer;
}
