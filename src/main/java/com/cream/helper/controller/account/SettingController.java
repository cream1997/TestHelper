package com.cream.helper.controller.account;

import com.cream.helper.obj.domain.dto.account.SetDefaultServerDTO;
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
}
