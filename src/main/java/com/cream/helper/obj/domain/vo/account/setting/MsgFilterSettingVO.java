package com.cream.helper.obj.domain.vo.account.setting;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MsgFilterSettingVO {
    private final List<FilterMsgVO> allReqFilterMsg = new ArrayList<>();
    private final List<FilterMsgVO> allResFilterMsg = new ArrayList<>();

    public void addReqFilterMsg(FilterMsgVO filterMsgVO) {
        this.allReqFilterMsg.add(filterMsgVO);
    }

    public void addResFilterMsg(FilterMsgVO filterMsgVO) {
        this.allResFilterMsg.add(filterMsgVO);
    }
}
