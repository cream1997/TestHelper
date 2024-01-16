package com.cream.helper.obj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServerItem {

    private String serverName;
    private String ip;
    private int port;
}
