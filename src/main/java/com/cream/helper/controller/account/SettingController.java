package com.cream.helper.controller.account;

import com.cream.helper.obj.domain.dto.account.ModifyFilterMsgDTO;
import com.cream.helper.obj.domain.dto.account.SetDefaultServerDTO;
import com.cream.helper.obj.domain.dto.msg.MsgFilterSettingDTO;
import com.cream.helper.obj.domain.dto.msg.UpdateFilterSettingDTO;
import com.cream.helper.obj.domain.vo.account.setting.MsgFilterSettingVO;
import com.cream.helper.service.impl.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SettingController {

    private final SettingService settingService;

    @Autowired
    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @PostMapping("/getDefaultServer")
    public String getDefaultServer(@RequestBody long accountId) {
        return settingService.getDefaultServer(accountId);
    }

    @PostMapping("/setDefaultServer")
    public void setDefaultServer(@RequestBody SetDefaultServerDTO setDefaultServerDTO) {
        settingService.setDefaultServer(setDefaultServerDTO);
    }

    @PostMapping("/getDefaultFilterMsg")
    public MsgFilterSettingVO getDefaultFilterMsg(@RequestBody long accountId) {
        return settingService.getDefaultFilterMsg(accountId);
    }

    @PostMapping("/getCustomerFilterMsg")
    public MsgFilterSettingVO getCustomerFilterMsg(@RequestBody long accountId) {
        return settingService.getCustomerFilterMsg(accountId);
    }

    @PostMapping("/modifyDefaultFilterMsg")
    public MsgFilterSettingVO modifyDefaultFilterMsg(ModifyFilterMsgDTO modifyDto) {
        // 先修改
        settingService.modifyDefaultFilterMsg(modifyDto);
        // 然后全覆盖返回
        return settingService.getDefaultFilterMsg(modifyDto.getAccountId());
    }

    @PostMapping("/modifyCustomFilterMsg")
    public MsgFilterSettingVO modifyCustomFilterMsg(ModifyFilterMsgDTO modifyDto) {
        // 先修改
        settingService.modifyCustomFilterMsg(modifyDto);
        // 然后全覆盖返回
        return settingService.getCustomerFilterMsg(modifyDto.getAccountId());
    }

    @PostMapping("/changeMsgFilterSetting")
    public MsgFilterSettingDTO changeMsgFilterSetting(@RequestBody UpdateFilterSettingDTO updateDTO) {
        return settingService.changeMsgFilterSetting(updateDTO);
    }
}
