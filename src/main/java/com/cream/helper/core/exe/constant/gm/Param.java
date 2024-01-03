package com.cream.helper.core.exe.constant.gm;

/**
 * 同包内访问
 */
public class Param {
    public final String desc;
    /**
     * 值类型
     */
    public ParamType paramType;

    public Param(String desc, ParamType paramType) {
        this.desc = desc;
        this.paramType = paramType;
    }
}
