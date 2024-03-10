package com.cream.helper.obj.domain.dto.msg;

import com.cream.helper.obj.domain.vo.account.setting.FilterMsgVO;
import lombok.Data;

@Data
public class UpdateFilterSettingDTO {
    private long accountId;
    private boolean resetDefaultFilter;
    private boolean clearCustomFilter;
    private FilterMsgVO filterMsgVO;
}
