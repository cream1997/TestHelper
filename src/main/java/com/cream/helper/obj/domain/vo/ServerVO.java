package com.cream.helper.obj.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServerVO {

    private final String name;
    private final String ip;
    private final int port;
}
