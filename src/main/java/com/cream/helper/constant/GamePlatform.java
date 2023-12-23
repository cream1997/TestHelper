package com.cream.helper.constant;

public enum GamePlatform {
    Engine("", 0),
    Qj5("", 0),
    ;


    public final String ip;
    public final int port;

    GamePlatform(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}
