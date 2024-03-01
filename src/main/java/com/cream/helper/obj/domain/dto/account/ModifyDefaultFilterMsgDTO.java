package com.cream.helper.obj.domain.dto.account;

import lombok.Data;

import java.util.List;

@Data
public class ModifyDefaultFilterMsgDTO {
    private final long accountId;
    private final List<Integer> cancelFilterMsgId;

    public ModifyDefaultFilterMsgDTO(long accountId, List<Integer> cancelFilterMsgId) {
        this.accountId = accountId;
        this.cancelFilterMsgId = cancelFilterMsgId;
    }
}
