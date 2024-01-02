package com.cream.helper.constant;

import com.cream.helper.utils.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum GamePlatform {
    Engine("", 0),
    Qj5("", 0),
    MockServer("127.0.0.1", 1111),
    ;


    public final String ip;
    public final int port;

    public String getUserLoginUrl() {
        return "http://" + ip + ":" + port + "/user/login";
    }

    public String getUserRegisterUrl() {
        return "http://" + ip + ":" + port + "/user/register";
    }

    GamePlatform(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public static GamePlatform of(String platformName) {
        for (GamePlatform gamePlatform : GamePlatform.values()) {
            if (gamePlatform.name().equals(platformName)) {
                return gamePlatform;
            }
        }
        log.error("未找到对应的游戏平台: {} {}", platformName, Util.getStackTrace());
        return null;
    }
}
