package com.cream.helper.obj.domain.dto.msg;

import com.cream.helper.obj.domain.vo.account.setting.MsgFilterSettingVO;
import lombok.Data;

@Data
public class MsgFilterSettingDTO {
    private final MsgFilterSettingVO defaultSetting;
    private final MsgFilterSettingVO customSetting;

    public MsgFilterSettingDTO(MsgFilterSettingVO defaultSetting, MsgFilterSettingVO customSetting) {
        this.defaultSetting = defaultSetting;
        this.customSetting = customSetting;
    }
}
