package com.cream.helper.controller;

import com.cream.helper.service.GmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class GmController {

    private final GmService gmService;

    @Autowired
    public GmController(GmService gmService) {
        this.gmService = gmService;
    }


    @PostMapping("/exeGmCmd")
    public String exeGmCmd(long rid, String cmd, Map<String, String> params) {
        return gmService.exeGmCmd(rid, cmd, params);
    }

    /**
     * 获取所有可用的GM命令
     */
    @PostMapping("/fetchAllGmCmd")
    public String fetchAllGmCommand() {
        return gmService.fetchAllGmCommand();
    }
}
