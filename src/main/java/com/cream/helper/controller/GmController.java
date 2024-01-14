package com.cream.helper.controller;

import com.cream.helper.core.exe.constant.gm.GmCmdType;
import com.cream.helper.obj.Ret;
import com.cream.helper.obj.dto.ExeGmReq;
import com.cream.helper.service.impl.GmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class GmController {

    private final GmService gmService;

    @Autowired
    public GmController(GmService gmService) {
        this.gmService = gmService;
    }


    @PostMapping("/exeGmCmd")
    public Ret<String> exeGmCmd(@RequestBody ExeGmReq exeGmReq) {
        return gmService.exeGmCmd(exeGmReq);
    }

    /**
     * 获取所有可用的GM命令
     */
    @PostMapping("/fetchAllGmCmd")
    public Ret<Collection<GmCmdType>> fetchAllGmCommand() {
        return gmService.fetchAllGmCommand();
    }
}
