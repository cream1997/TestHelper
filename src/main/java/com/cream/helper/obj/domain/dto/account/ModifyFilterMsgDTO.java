package com.cream.helper.obj.domain.dto.account;

import lombok.Data;

import java.util.List;

@Data
public class ModifyFilterMsgDTO {
    private final long accountId;
    private final List<Integer> modifyMsgId;

    public ModifyFilterMsgDTO(long accountId, List<Integer> modifyMsgId) {
        this.accountId = accountId;
        this.modifyMsgId = modifyMsgId;
    }
}
